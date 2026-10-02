public class WordReversalEncoder {

    /**
     * Reverses each word in a sentence individually while maintaining word order.
     * 
     * @param sentence Space-separated words.
     * @return Formatted string with each word reversed.
     */
    public static String reverseEachWord(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return "";
        }

        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            StringBuilder reversedWord = new StringBuilder();
            String w = words[i];
            for (int j = w.length() - 1; j >= 0; j--) {
                reversedWord.append(w.charAt(j));
            }
            result.append(reversedWord);
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String input = "hello club";
        String encoded = reverseEachWord(input);
        System.out.println("Input: \"" + input + "\"");
        System.out.println("Output: " + encoded);
    }
}
