import java.util.*;

public class StopWordWordFrequencyReport {

    private static final Set<String> STOP_WORDS = new HashSet<>(
            Arrays.asList("the", "was", "and", "a", "is", "of", "in")
    );

    /**
     * Filters stop words, counts frequencies, and prints words in descending order of frequency.
     * 
     * @param feedback The input text paragraph.
     */
    public static void printFilteredWordFrequency(String feedback) {
        if (feedback == null || feedback.trim().isEmpty()) {
            return;
        }

        // Normalize: lowercase, strip punctuation like periods and commas
        String cleaned = feedback.toLowerCase().replace(".", "").replace(",", "");
        String[] words = cleaned.trim().split("\\s+");

        Map<String, Integer> freqMap = new HashMap<>();
        for (String w : words) {
            if (w.isEmpty() || STOP_WORDS.contains(w)) {
                continue;
            }
            freqMap.put(w, freqMap.getOrDefault(w, 0) + 1);
        }

        // Sort entries by count in descending order
        List<Map.Entry<String, Integer>> list = new ArrayList<>(freqMap.entrySet());
        list.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        for (Map.Entry<String, Integer> entry : list) {
            System.out.printf("%s: %d%n", entry.getKey(), entry.getValue());
        }
    }

    public static void main(String[] args) {
        String feedback = "The mentor was great, the session was great and clear.";
        printFilteredWordFrequency(feedback);
    }
}
