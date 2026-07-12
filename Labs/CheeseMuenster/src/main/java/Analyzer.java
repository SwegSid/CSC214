

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Pattern;


public class Analyzer {

    private static final Pattern LACTIC_PATTERN =
            Pattern.compile("\\blactic\\b", Pattern.CASE_INSENSITIVE);

    private final String inputFile;

    private String[] headers;
    private final List<Cheese> cheeses = new ArrayList<>();
    private final List<String[]> rawRows = new ArrayList<>();


    private int cheeseIdIdx;
    private int milkTypeIdx;
    private int milkTreatmentIdx;
    private int organicIdx;
    private int moistureIdx;
    private int flavourIdx;

    public Analyzer(String inputFile) {
        this.inputFile = inputFile;
    }


    //Reads the CSV file, Must be called before running any calculations.

    public void loadData() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String headerLine = reader.readLine();
            if (headerLine == null) {
                throw new IOException("CSV file is empty: " + inputFile);
            }
            headers = Reader.parseLine(headerLine);

            cheeseIdIdx = Reader.findColumnIndex(headers, "CheeseId");
            milkTypeIdx = Reader.findColumnIndex(headers, "MilkTypeEn");
            milkTreatmentIdx = Reader.findColumnIndex(headers, "MilkTreatmentTypeEn");
            organicIdx = Reader.findColumnIndex(headers, "Organic");
            moistureIdx = Reader.findColumnIndex(headers, "MoisturePercent");
            flavourIdx = Reader.findColumnIndex(headers, "FlavourEn");

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] fields = Reader.parseLine(line);
                rawRows.add(fields);
                cheeses.add(new Cheese(fields, cheeseIdIdx, milkTypeIdx,
                        milkTreatmentIdx, organicIdx, moistureIdx, flavourIdx));
            }
        }
    }

    // ----- Base Acceptance Criteria -----

    /** Returns pasteurizedCount, rawCount based on MilkTreatmentTypeEn. */
    public int[] countPasteurizedAndRaw() {
        int pasteurized = 0;
        int raw = 0;
        for (Cheese c : cheeses) {
            String treatment = c.getMilkTreatmentTypeEn().toLowerCase();
            if (treatment.isEmpty()) {
                continue;
            }
            if (treatment.contains("pasteuriz")) {
                pasteurized++;
            } else if (treatment.contains("raw")) {
                raw++;
            }
        }
        return new int[] { pasteurized, raw };
    }

    /** Counts organic cheeses with moisture > 41.0%. */
    public int countOrganicHighMoisture() {
        int count = 0;
        for (Cheese c : cheeses) {
            int organic = c.getOrganicAsInt();
            Double moisture = c.getMoisturePercentAsDouble();
            if (organic == 1 && moisture != null && moisture > 41.0) {
                count++;
            }
        }
        return count;
    }

    /** Returns the most common milk type (cow, goat, ewe, or buffalo). */
    public String mostCommonMilkType() {
        Map<String, Integer> counts = new HashMap<>();
        for (Cheese c : cheeses) {
            String type = c.getMilkTypeEn();
            if (type.isEmpty()) {
                continue;
            }
            counts.put(type, counts.getOrDefault(type, 0) + 1);
        }

        String best = "Unknown";
        int bestCount = -1;
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > bestCount) {
                bestCount = entry.getValue();
                best = entry.getKey();
            }
        }
        return best;
    }

    // ----- Add-Ons -----

    /** (1 Credit) Writes all data rows except the header to a new CSV. */
    public void writeWithoutHeaders(String outputPath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            for (String[] row : rawRows) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        }
    }

    /** (2 Credits) Writes all data but omits the CheeseId column. */
    public void writeWithoutIds(String outputPath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            writer.write(String.join(",", removeColumn(headers, cheeseIdIdx)));
            writer.newLine();
            for (String[] row : rawRows) {
                writer.write(String.join(",", removeColumn(row, cheeseIdIdx)));
                writer.newLine();
            }
        }
    }

    private String[] removeColumn(String[] row, int idxToRemove) {
        if (idxToRemove < 0 || idxToRemove >= row.length) {
            return row;
        }
        String[] result = new String[row.length - 1];
        int j = 0;
        for (int i = 0; i < row.length; i++) {
            if (i != idxToRemove) {
                result[j++] = row[i];
            }
        }
        return result;
    }

    /** (2 Credits) Calculates the average MoisturePercent across all valid rows. */
    public double averageMoisturePercent() {
        double sum = 0.0;
        int count = 0;
        for (Cheese c : cheeses) {
            Double moisture = c.getMoisturePercentAsDouble();
            if (moisture != null) {
                sum += moisture;
                count++;
            }
        }
        return count == 0 ? 0.0 : sum / count;
    }

    /** (2 Credits) Finds every CheeseId missing between the min and max IDs present. */
    public List<Integer> findMissingIds() {
        TreeSet<Integer> presentIds = new TreeSet<>();
        for (Cheese c : cheeses) {
            int id = c.getCheeseIdAsInt();
            if (id != -1) {
                presentIds.add(id);
            }
        }

        List<Integer> missing = new ArrayList<>();
        if (presentIds.isEmpty()) {
            return missing;
        }

        int min = presentIds.first();
        int max = presentIds.last();
        for (int id = min; id <= max; id++) {
            if (!presentIds.contains(id)) {
                missing.add(id);
            }
        }
        return missing;
    }

    /**
     * (5 Credits) Counts cheeses whose FlavourEn description contains the
     * whole word "lactic" anywhere in the sentence, using a
     * regex word-boundary match so substrings like
     * "galactic" don't trip up code.
     */
    public int countLacticCheeses() {
        int count = 0;
        for (Cheese c : cheeses) {
            String flavour = c.getFlavourEn();
            if (!flavour.isEmpty() && LACTIC_PATTERN.matcher(flavour).find()) {
                count++;
            }
        }
        return count;
    }

    public int getTotalCheeseCount() {
        return cheeses.size();
    }
}
