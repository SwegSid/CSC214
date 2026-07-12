
public class Cheese {

    private final String cheeseId;
    private final String milkTypeEn;
    private final String milkTreatmentTypeEn;
    private final String organic;
    private final String moisturePercent;
    private final String flavourEn;
    private final String[] rawFields;

    public Cheese(String[] rawFields, int cheeseIdIdx, int milkTypeIdx,
                  int milkTreatmentIdx, int organicIdx, int moistureIdx, int flavourIdx) {
        this.rawFields = rawFields;
        this.cheeseId = safeGet(rawFields, cheeseIdIdx);
        this.milkTypeEn = safeGet(rawFields, milkTypeIdx);
        this.milkTreatmentTypeEn = safeGet(rawFields, milkTreatmentIdx);
        this.organic = safeGet(rawFields, organicIdx);
        this.moisturePercent = safeGet(rawFields, moistureIdx);
        this.flavourEn = safeGet(rawFields, flavourIdx);
    }

    private static String safeGet(String[] arr, int idx) {
        if (idx < 0 || idx >= arr.length) {
            return "";
        }
        return arr[idx].trim();
    }

    public String getCheeseId() {
        return cheeseId;
    }

    public String getMilkTypeEn() {
        return milkTypeEn;
    }

    public String getMilkTreatmentTypeEn() {
        return milkTreatmentTypeEn;
    }

    public String getOrganic() {
        return organic;
    }

    public String getMoisturePercent() {
        return moisturePercent;
    }

    public String getFlavourEn() {
        return flavourEn;
    }

    public String[] getRawFields() {
        return rawFields;
    }

    /**
     * Trys to parse the CheeseId as an integer.
     * Returns -1 if the value is missing or not a valid integer.
     */
    public int getCheeseIdAsInt() {
        try {
            return Integer.parseInt(cheeseId);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Trys to parse MoisturePercent as a double.
     * Returns null if the value is missing or not a valid number.
     */
    public Double getMoisturePercentAsDouble() {
        try {
            if (moisturePercent.isEmpty()) {
                return null;
            }
            return Double.parseDouble(moisturePercent);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * trys to parse Organic as an integer (0 = not organic, 1 = organic).
     * Returns -1 if the value is missing or invalid.
     */
    public int getOrganicAsInt() {
        try {
            return Integer.parseInt(organic);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
