public class ExamHallSeatDuplicationChecker {

    /**
     * Checks for duplicate seat numbers using only arrays and loops.
     * 
     * @param seatNumbers Array of seat numbers assigned to students.
     */
    public static void checkDuplicateSeats(int[] seatNumbers) {
        if (seatNumbers == null || seatNumbers.length <= 1) {
            System.out.println("No Duplicate Seats Found");
            return;
        }

        boolean duplicateFound = false;
        // Keep track of printed duplicates to avoid printing the same number repeatedly
        int n = seatNumbers.length;
        boolean[] alreadyReported = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (alreadyReported[i]) {
                continue;
            }
            boolean isDup = false;
            for (int j = i + 1; j < n; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    isDup = true;
                    alreadyReported[j] = true;
                }
            }
            if (isDup) {
                System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                duplicateFound = true;
            }
        }

        if (!duplicateFound) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1 ---");
        int[] hall1 = {101, 102, 103, 102, 105};
        checkDuplicateSeats(hall1);

        System.out.println("\n--- Test Case 2 ---");
        int[] hall2 = {101, 102, 103, 104, 105};
        checkDuplicateSeats(hall2);
    }
}
