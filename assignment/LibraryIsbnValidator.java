public class LibraryIsbnValidator {

    /**
     * Normalizes a raw ISBN code by trimming spaces and uppercasing first 3 characters.
     */
    public static String normalizeCode(String raw) {
        if (raw == null) return null;
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    /**
     * Validates normalized code (3 letters + 4 year digits + 6 catalog digits) and builds formatted output.
     */
    public static String validateAndFormat(String code) {
        if (code == null || code.length() != 13) {
            return "Invalid: wrong length";
        }

        // Validate first 3 characters are letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        // Validate remaining 10 characters are digits
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: non-digit body";
            }
        }

        String pubCode = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(pubCode).append("] YEAR: ").append(year).append(" | CATALOG: ").append(catalog);
        return sb.toString();
    }

    public static void processCode(String raw) {
        String normalized = normalizeCode(raw);
        String formatted = validateAndFormat(normalized);
        System.out.printf("Input: \"%s\" -> Output: %s%n", raw, formatted);
    }

    public static void main(String[] args) {
        processCode(" pen2026004251 ");
        processCode("12N2026004251");
    }
}
