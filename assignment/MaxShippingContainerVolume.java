public class MaxShippingContainerVolume {

    /**
     * Calculates the maximum cargo area between two container barriers using Two Pointers.
     * Formula: min(heights[i], heights[j]) * (j - i)
     * Time Complexity: O(n)
     * Auxiliary Space Complexity: O(1)
     */
    public static int maxCargoCapacity(int[] containerWallHeights) {
        if (containerWallHeights == null || containerWallHeights.length < 2) {
            return 0;
        }

        int maxCapacity = 0;
        int left = 0;
        int right = containerWallHeights.length - 1;

        while (left < right) {
            int width = right - left;
            int hLeft = containerWallHeights[left];
            int hRight = containerWallHeights[right];
            int currentCapacity = Math.min(hLeft, hRight) * width;

            maxCapacity = Math.max(maxCapacity, currentCapacity);

            if (hLeft < hRight) {
                left++;
            } else {
                right--;
            }
        }

        return maxCapacity;
    }

    public static void main(String[] args) {
        int[] barriers = {2, 5, 8, 3, 6, 7, 4};
        int result = maxCargoCapacity(barriers);
        System.out.println("Barriers: [2, 5, 8, 3, 6, 7, 4]");
        System.out.println("Max Cargo Capacity: " + result);
    }
}
