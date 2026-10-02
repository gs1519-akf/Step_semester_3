public class BankTransactionReferenceValidator {

    /**
     * Trims spaces and uppercases first 3 characters.
     */
    public static String normalizeReference(String raw) {
        if (raw == null) return null;
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    /**
     * Validates 14-char reference: 3 letters + 6 date digits + 5 seq digits.
     */
    public static String validateAndFormat(String reference) {
        if (reference == null || reference.length() != 14) {
            return "Invalid: wrong length";
        }

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: non-digit body";
            }
        }

        String bankCode = reference.substring(0, 3);
        String dd = reference.substring(3, 5);
        String mm = reference.substring(5, 7);
        String yy = reference.substring(7, 9);
        String seq = reference.substring(9, 14);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(bankCode).append("] DATE: ")
          .append(dd).append("/").append(mm).append("/").append(yy)
          .append(" | SEQ: ").append(seq);
        return sb.toString();
    }

    public static void process(String raw) {
        String norm = normalizeReference(raw);
        String result = validateAndFormat(norm);
        System.out.printf("Input: \"%s\" -> Output: %s%n", raw, result);
    }

    public static void main(String[] args) {
        process(" hdf03022600042 ");
        process("12F03022600042");
    }
}
