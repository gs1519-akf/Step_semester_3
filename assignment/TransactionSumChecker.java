import java.util.*;

public class TransactionSumChecker {

    /**
     * Optimal Approach: Uses a HashSet to check complement in O(n) time and O(n) space.
     */
    public static boolean hasTransactionPairWithSum(int[] transactions, int target) {
        if (transactions == null || transactions.length < 2) {
            return false;
        }

        Set<Integer> seen = new HashSet<>();
        for (int val : transactions) {
            int complement = target - val;
            if (seen.contains(complement)) {
                return true;
            }
            seen.add(val);
        }
        return false;
    }

    /**
     * Brute-Force Approach: Compares all pairs in O(n^2) time and O(1) space.
     */
    public static boolean hasTransactionPairBruteForce(int[] transactions, int target) {
        if (transactions == null || transactions.length < 2) return false;
        for (int i = 0; i < transactions.length; i++) {
            for (int j = i + 1; j < transactions.length; j++) {
                if (transactions[i] + transactions[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] batch1 = {250, 700, 350, 1500};
        int target1 = 950;
        System.out.println("Batch 1 target 950: " + hasTransactionPairWithSum(batch1, target1));

        int[] batch2 = {300, 400, 600};
        int target2 = 2000;
        System.out.println("Batch 2 target 2000: " + hasTransactionPairWithSum(batch2, target2));
    }
}
