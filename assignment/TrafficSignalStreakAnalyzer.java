public class TrafficSignalStreakAnalyzer {

    /**
     * Scans through a sequence of signal readings and reports the longest continuous streak of identical characters.
     * 
     * @param signalLog String of signal readings, e.g. "RRGGGYRR".
     */
    public static void findLongestStreak(String signalLog) {
        if (signalLog == null || signalLog.isEmpty()) {
            System.out.println("Signal log is empty.");
            return;
        }

        char longestColor = signalLog.charAt(0);
        int maxStreak = 1;

        char currentColor = signalLog.charAt(0);
        int currentStreak = 1;

        for (int i = 1; i < signalLog.length(); i++) {
            char c = signalLog.charAt(i);
            if (c == currentColor) {
                currentStreak++;
            } else {
                if (currentStreak > maxStreak) {
                    maxStreak = currentStreak;
                    longestColor = currentColor;
                }
                currentColor = c;
                currentStreak = 1;
            }
        }

        if (currentStreak > maxStreak) {
            maxStreak = currentStreak;
            longestColor = currentColor;
        }

        System.out.printf("Longest Streak: '%c' repeated %d times%n", longestColor, maxStreak);
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1 ---");
        findLongestStreak("RRGGGYRR");

        System.out.println("\n--- Test Case 2 ---");
        findLongestStreak("RRRRYYGG");
    }
}
