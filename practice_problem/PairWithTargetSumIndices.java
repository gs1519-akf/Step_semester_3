import java.util.*;

public class PairWithTargetSumIndices {

    /**
     * Problem 4: Pair With Target Sum (Unsorted Array) with Indices
     * Time Complexity: O(n)
     * Additional Space Complexity: O(n) using HashMap
     */
    public static int[] findPairIndices(int[] nums, int target) {
        if (nums == null || nums.length < 2) return null;

        Map<Integer, Integer> numToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (numToIndex.containsKey(complement)) {
                return new int[]{numToIndex.get(complement), i};
            }
            numToIndex.put(nums[i], i);
        }
        return null;
    }

    public static boolean hasPairWithSum(int[] nums, int target) {
        return findPairIndices(nums, target) != null;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] pair1 = findPairIndices(nums1, target1);
        System.out.println("nums = [2, 7, 11, 15], target = 9 -> Output: " + hasPairWithSum(nums1, target1));
        if (pair1 != null) {
            System.out.printf("Matched pair: index %d (%d) + index %d (%d) = %d%n",
                    pair1[0], nums1[pair1[0]], pair1[1], nums1[pair1[1]], target1);
        }

        int[] nums2 = {3, 4, 6};
        int target2 = 20;
        System.out.println("nums = [3, 4, 6], target = 20 -> Output: " + hasPairWithSum(nums2, target2));
    }
}
