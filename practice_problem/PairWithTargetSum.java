import java.util.*;

public class PairWithTargetSum {

    /**
     * Problem 3: Pair With Target Sum (Unsorted)
     * Optimal Approach: HashSet Complement Lookup
     * Time Complexity: O(n)
     * Additional Space Complexity: O(n)
     */
    public static boolean hasPairWithSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        Set<Integer> seen = new HashSet<>();
        for (int x : nums) {
            int complement = target - x;
            if (seen.contains(complement)) {
                return true;
            }
            seen.add(x);
        }
        return false;
    }

    /**
     * Brute-Force Approach: Check every possible pair
     * Time Complexity: O(n^2)
     * Additional Space Complexity: O(1)
     */
    public static boolean hasPairWithSumBruteForce(int[] nums, int target) {
        if (nums == null || nums.length < 2) return false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("nums = [2, 7, 11, 15], target = 9");
        System.out.println("Expected Output: true (because 2 + 7 = 9)");
        System.out.println("Actual Output:   " + hasPairWithSum(nums1, target1));

        System.out.println();
        int[] nums2 = {3, 4, 6};
        int target2 = 20;
        System.out.println("nums = [3, 4, 6], target = 20");
        System.out.println("Expected Output: false");
        System.out.println("Actual Output:   " + hasPairWithSum(nums2, target2));
    }
}
