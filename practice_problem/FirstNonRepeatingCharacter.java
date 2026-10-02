public class FirstNonRepeatingCharacter {

    /**
     * Finds and returns the first non-repeating character in a string.
     */
    public static char findFirstNonRepeatingChar(String text) {
        if (text == null || text.isEmpty()) {
            return '\0';
        }

        int[] frequency = new int[256];
        for (int i = 0; i < text.length(); i++) {
            frequency[text.charAt(i)]++;
        }

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (frequency[c] == 1) {
                return c;
            }
        }
        return '\0';
    }

    public static void checkAndPrint(String text) {
        char result = findFirstNonRepeatingChar(text);
        if (result != '\0') {
            System.out.printf("Input: \"%s\" -> First Non-Repeating Character: '%c'%n", text, result);
        } else {
            System.out.printf("Input: \"%s\" -> No Non-Repeating Character Found%n", text);
        }
    }

    public static void main(String[] args) {
        checkAndPrint("swiss");
        checkAndPrint("aabbcc");
    }
}
