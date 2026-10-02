public class TypingSpeedTestAccuracyChecker {

    /**
     * Compares original and typed passages character by character and reports accuracy and first mismatch.
     * 
     * @param original The reference passage.
     * @param typed The user's typed passage.
     */
    public static void checkTypingAccuracy(String original, String typed) {
        if (original == null || typed == null) {
            System.out.println("Invalid input strings.");
            return;
        }

        int total = original.length();
        if (total != typed.length()) {
            System.out.println("Strings must be of equal length for comparison.");
            return;
        }

        if (total == 0) {
            System.out.println("Matched: 0/0 | Accuracy: 100.00% | No Mismatches");
            return;
        }

        int matches = 0;
        int firstMismatchPos = -1;
        char origChar = ' ';
        char typedChar = ' ';

        for (int i = 0; i < total; i++) {
            char o = original.charAt(i);
            char t = typed.charAt(i);
            if (o == t) {
                matches++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i + 1; // 1-based index
                origChar = o;
                typedChar = t;
            }
        }

        double accuracy = ((double) matches / total) * 100.0;
        if (firstMismatchPos != -1) {
            System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | First Mismatch at position %d ('%c' vs '%c')%n",
                    matches, total, accuracy, firstMismatchPos, origChar, typedChar);
        } else {
            System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | No Mismatches%n", matches, total, accuracy);
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1 ---");
        checkTypingAccuracy("hello world", "hello worlt");

        System.out.println("\n--- Test Case 2 ---");
        checkTypingAccuracy("coding", "coding");
    }
}
