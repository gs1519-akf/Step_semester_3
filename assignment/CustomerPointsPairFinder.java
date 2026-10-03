import java.util.*;

public class CustomerPointsPairFinder {

    /**
     * Finds indices of two distinct entries whose rewards points sum to target.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int[] findRewardPointPair(int[] points, int target) {
        if (points == null || points.length < 2) return null;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            int complement = target - points[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(points[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] points = {150, 450, 300, 850};
        int target = 600;

        int[] result = findRewardPointPair(points, target);
        if (result != null) {
            System.out.printf("Pair found at indices: [%d, %d] (Values: %d + %d = %d)%n",
                    result[0], result[1], points[result[0]], points[result[1]], target);
        } else {
            System.out.println("No matching pair found.");
        }
    }
}
