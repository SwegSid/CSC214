

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CheeseAnalyzer, covering the base acceptance criteria
 * calculations as well as all five portfolio add-ons.
 *
 * A small in-memory CSV fixture is written to a temp file before each
 * test so every calculation can be verified against known values.
 */
class CheeseAnalyzerTest {

    @TempDir
    Path tempDir;

    private Path csvPath;

    private static final String HEADER =
            "CheeseId,CheeseNameEn,MilkTypeEn,MilkTreatmentTypeEn,Organic,MoisturePercent,FlavourEn";

    // Fixture data intentionally includes:
    //  - a mix of pasteurized/raw milk treatments
    //  - organic cheeses above and below the 41.0% moisture threshold
    //  - cow as the most common milk type, with goat/ewe/buffalo minorities
    //  - a gap in CheeseId sequence (200, 201, 203, 205 -> missing 202, 204)
    //  - flavour text with "lactic" embedded in different sentence positions,
    //    plus a decoy ("galactic") that should NOT match
    //  - one row with missing/blank values that should be skipped, not crash
    private static final String[] ROWS = {
            "200,Cheddar,Cow milk,Pasteurized milk,0,38.0,Sharp and nutty",
            "201,Brie,Cow milk,Raw milk,1,45.0,Sharp, lactic",
            "203,Chevre,Goat milk,Pasteurized milk,1,42.5,Mild and lactic flavor with a touch of hazelnut",
            "205,Feta,Ewe milk,Raw milk,0,40.0,Tangy and salty",
            "206,Buffalo Mozz,Buffalo milk,Pasteurized milk,1,35.0,Delicately galactic taste",
            "207,Gouda,Cow milk,Pasteurized milk,0,,Smooth and creamy",
            "208,Swiss,Cow milk,,1,50.0,Nutty with a lactic finish"
    };

    @BeforeEach
    void setUp() throws IOException {
        csvPath = tempDir.resolve("cheese_data.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvPath.toFile()))) {
            writer.write(HEADER);
            writer.newLine();
            for (String row : ROWS) {
                writer.write(row);
                writer.newLine();
            }
        }
    }

    @AfterEach
    void tearDown() {
        // TempDir is cleaned up automatically by JUnit
    }

    private Analyzer loadAnalyzer() throws IOException {
        Analyzer analyzer = new Analyzer(csvPath.toString());
        analyzer.loadData();
        return analyzer;
    }

    // ----- loadData -----

    @Test
    void loadData_readsAllValidRows() throws IOException {
        Analyzer analyzer = loadAnalyzer();
        assertEquals(ROWS.length, analyzer.getTotalCheeseCount());
    }

    // ----- countPasteurizedAndRaw -----

    @Test
    void countPasteurizedAndRaw_countsCorrectly() throws IOException {
        Analyzer analyzer = loadAnalyzer();
        int[] result = analyzer.countPasteurizedAndRaw();
        // Pasteurized: 200, 203, 206, 207 = 4
        // Raw: 201, 205 = 2
        // 208 has blank treatment -> skipped
        assertEquals(4, result[0], "Pasteurized count mismatch");
        assertEquals(2, result[1], "Raw count mismatch");
    }

    // ----- countOrganicHighMoisture -----

    @Test
    void countOrganicHighMoisture_countsOnlyOrganicAboveThreshold() throws IOException {
        Analyzer analyzer = loadAnalyzer();
        // Organic==1: 201(45.0), 203(42.5), 206(35.0), 208(50.0)
        // Above 41.0%: 201, 203, 208 -> 3 (206 is organic but only 35.0%)
        assertEquals(3, analyzer.countOrganicHighMoisture());
    }

    @Test
    void countOrganicHighMoisture_skipsMissingMoisture() throws IOException {
        // Row 207 is organic=0 with blank moisture; ensure no crash and it's excluded
        Analyzer analyzer = loadAnalyzer();
        assertDoesNotThrow(analyzer::countOrganicHighMoisture);
    }

    // ----- mostCommonMilkType -----

    @Test
    void mostCommonMilkType_returnsCow() throws IOException {
        Analyzer analyzer = loadAnalyzer();
        // Cow milk: 200, 201, 207, 208 = 4 (most common)
        assertEquals("Cow milk", analyzer.mostCommonMilkType());
    }

    // ----- averageMoisturePercent -----

    @Test
    void averageMoisturePercent_computesCorrectAverage() throws IOException {
        Analyzer analyzer = loadAnalyzer();
        // Valid moisture values: 38.0, 45.0, 42.5, 40.0, 35.0, 50.0 (207 is blank, skipped)
        double expectedAvg = (38.0 + 45.0 + 42.5 + 40.0 + 35.0 + 50.0) / 6.0;
        assertEquals(expectedAvg, analyzer.averageMoisturePercent(), 0.0001);
    }

    // ----- findMissingIds -----

    @Test
    void findMissingIds_findsGapsInSequence() throws IOException {
        Analyzer analyzer = loadAnalyzer();
        // IDs present: 200,201,203,205,206,207,208 -> range 200-208
        // Missing: 202, 204
        List<Integer> missing = analyzer.findMissingIds();
        assertEquals(List.of(202, 204), missing);
    }

    // ----- countLacticCheeses -----

    @Test
    void countLacticCheeses_matchesWholeWordOnly() throws IOException {
        Analyzer analyzer = loadAnalyzer();
        // Should match: 201 ("Sharp, lactic"), 203 ("Mild and lactic flavor..."),
        //               208 ("Nutty with a lactic finish")
        // Should NOT match: 206 ("Delicately galactic taste") - substring only
        assertEquals(3, analyzer.countLacticCheeses());
    }

    // ----- writeWithoutHeaders -----

    @Test
    void writeWithoutHeaders_omitsHeaderRow() throws IOException {
        Analyzer analyzer = loadAnalyzer();
        Path outputPath = tempDir.resolve("cheese_without_headers.csv");
        analyzer.writeWithoutHeaders(outputPath.toString());

        List<String> lines = Files.readAllLines(outputPath);
        assertEquals(ROWS.length, lines.size());
        assertFalse(lines.get(0).contains("CheeseId"), "Header row should not be present");
        assertTrue(lines.get(0).startsWith("200,"), "First line should be first data row");
    }

    // ----- writeWithoutIds -----

    @Test
    void writeWithoutIds_omitsCheeseIdColumn() throws IOException {
        Analyzer analyzer = loadAnalyzer();
        Path outputPath = tempDir.resolve("cheese_without_ids.csv");
        analyzer.writeWithoutIds(outputPath.toString());

        List<String> lines = Files.readAllLines(outputPath);
        // Header + all data rows
        assertEquals(ROWS.length + 1, lines.size());

        String headerLine = lines.get(0);
        assertFalse(headerLine.contains("CheeseId"), "Header should not contain CheeseId column");

        // First data row was "200,Cheddar,Cow milk,Pasteurized milk,0,38.0,Sharp and nutty"
        // After removing CheeseId, it should start with the cheese name
        String firstDataLine = lines.get(1);
        assertTrue(firstDataLine.startsWith("Cheddar,"), "CheeseId value should be stripped from data row");
    }

    // ----- Edge cases -----

    @Test
    void loadData_emptyFileThrowsIOException() throws IOException {
        Path emptyPath = tempDir.resolve("empty.csv");
        Files.createFile(emptyPath);
        Analyzer analyzer = new Analyzer(emptyPath.toString());
        assertThrows(IOException.class, analyzer::loadData);
    }

    @Test
    void findMissingIds_emptyDatasetReturnsEmptyList() throws IOException {
        Path onlyHeaderPath = tempDir.resolve("only_header.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(onlyHeaderPath.toFile()))) {
            writer.write(HEADER);
            writer.newLine();
        }
        Analyzer analyzer = new Analyzer(onlyHeaderPath.toString());
        analyzer.loadData();
        assertTrue(analyzer.findMissingIds().isEmpty());
    }
}
