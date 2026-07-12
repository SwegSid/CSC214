
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Entry point. Runs the full cheese analysis pipeline:
 *   1. Load cheese_data.csv
 *   2. Perform all calculations (base + add-ons)
 *   3. Write output.txt, cheese_without_headers.csv,
 *      cheese_without_ids.csv, and missing_ids.txt
 */
public class Main {

    private static final String INPUT_FILE = "cheese_data.csv";
    private static final String OUTPUT_FILE = "output.txt";
    private static final String NO_HEADERS_FILE = "cheese_without_headers.csv";
    private static final String NO_IDS_FILE = "cheese_without_ids.csv";
    private static final String MISSING_IDS_FILE = "missing_ids.txt";

    public static void main(String[] args) {
        Analyzer analyzer = new Analyzer(INPUT_FILE);

        try {
            analyzer.loadData();
            System.out.println("Loaded " + analyzer.getTotalCheeseCount() + " cheese records.");

            // Base acceptance criteria calculations
            int[] treatmentCounts = analyzer.countPasteurizedAndRaw();
            int organicHighMoisture = analyzer.countOrganicHighMoisture();
            String mostCommonMilk = analyzer.mostCommonMilkType();

            // Add-on calculations
            double avgMoisture = analyzer.averageMoisturePercent();
            int lacticCount = analyzer.countLacticCheeses();
            List<Integer> missingIds = analyzer.findMissingIds();

            // Write main output.txt
            writeOutputFile(treatmentCounts, organicHighMoisture, mostCommonMilk,
                    avgMoisture, lacticCount);

            // Add-on files
            analyzer.writeWithoutHeaders(NO_HEADERS_FILE);
            analyzer.writeWithoutIds(NO_IDS_FILE);
            writeMissingIdsFile(missingIds);

            System.out.println("Analysis complete! Check output.txt for results.");

        } catch (IOException e) {
            System.err.println("Error processing cheese data: " + e.getMessage());
        }
    }

    private static void writeOutputFile(int[] treatmentCounts, int organicHighMoisture,
                                        String mostCommonMilk, double avgMoisture,
                                        int lacticCount) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {
            writer.write("=== Canadian Cheese Directory Analysis ===");
            writer.newLine();
            writer.newLine();

            writer.write("Pasteurized milk cheeses: " + treatmentCounts[0]);
            writer.newLine();
            writer.write("Raw milk cheeses: " + treatmentCounts[1]);
            writer.newLine();
            writer.newLine();

            writer.write("Organic cheeses with moisture > 41.0%: " + organicHighMoisture);
            writer.newLine();
            writer.newLine();

            writer.write("Most common milk type: " + mostCommonMilk);
            writer.newLine();
            writer.newLine();

            writer.write(String.format("Average moisture percent: %.2f%%", avgMoisture));
            writer.newLine();
            writer.newLine();

            writer.write("Cheeses described as \"lactic\": " + lacticCount);
            writer.newLine();
        }
    }

    private static void writeMissingIdsFile(List<Integer> missingIds) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MISSING_IDS_FILE))) {
            writer.write("Missing Cheese IDs (" + missingIds.size() + " total):");
            writer.newLine();
            for (int id : missingIds) {
                writer.write(String.valueOf(id));
                writer.newLine();
            }
        }
    }
}
