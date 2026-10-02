public class MovieReviewWordLengthProfiler {

    /**
     * Splits review into individual words and classifies each as Short, Medium, or Long.
     * 
     * @param review Raw text of the movie review.
     */
    public static void classifyWordLengths(String review) {
        if (review == null || review.trim().isEmpty()) {
            System.out.println("Short: 0 | Medium: 0 | Long: 0");
            return;
        }

        String[] words = review.trim().split("\\s+");
        int shortCount = 0;   // 1 - 4 letters
        int mediumCount = 0;  // 5 - 8 letters
        int longCount = 0;    // 9+ letters

        for (String word : words) {
            // Strip any surrounding punctuation for pure letter length
            String cleanWord = word.replaceAll("[^a-zA-Z]", "");
            int len = cleanWord.isEmpty() ? word.length() : cleanWord.length();
            if (len >= 1 && len <= 4) {
                shortCount++;
            } else if (len >= 5 && len <= 8) {
                mediumCount++;
            } else if (len >= 9) {
                longCount++;
            }
        }

        System.out.printf("Short: %d | Medium: %d | Long: %d%n", shortCount, mediumCount, longCount);
    }

    public static void main(String[] args) {
        String sample = "This movie was absolutely fantastic and thrilling";
        classifyWordLengths(sample);
    }
}
