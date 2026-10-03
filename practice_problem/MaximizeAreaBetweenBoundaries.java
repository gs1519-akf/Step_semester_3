public class MaximizeAreaBetweenBoundaries {

    /**
     * Problem 5: Maximize Area Between Two Boundaries (Container With Most Water)
     * Two-Pointer Inward Search:
     * Starts with pointers at ends and moves the pointer with shorter height inward.
     * 
     * Time Complexity: O(n)
     * Additional Space Complexity: O(1)
     * 
     * Comparison with Brute-Force:
     * Brute-Force checks all n*(n-1)/2 pairs taking O(n^2) time, which times out on large inputs.
     * The two-pointer approach eliminates sub-optimal candidate pairs in linear time.
     */
    public static int maxContainerArea(int[] heights) {
        if (heights == null || heights.length < 2) {
            return 0;
        }

        int maxArea = 0;
        int left = 0;
        int right = heights.length - 1;

        while (left < right) {
            int width = right - left;
            int minHeight = Math.min(heights[left], heights[right]);
            int area = minHeight * width;

            if (area > maxArea) {
                maxArea = area;
            }

            // Move the pointer at the shorter boundary inward
            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int area = maxContainerArea(heights);

        System.out.println("heights = [1, 8, 6, 2, 5, 4, 8, 3, 7]");
        System.out.println("Expected Output: 49");
        System.out.println("Actual Output:   " + area);
    }
}
