public class FactoryFloorGridSummary {

    public static class SummaryResult {
        public final int totalDefects;
        public final int row;
        public final int col;

        public SummaryResult(int totalDefects, int row, int col) {
            this.totalDefects = totalDefects;
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "(" + totalDefects + ", (" + row + ", " + col + "))";
        }
    }

    /**
     * Calculates total defect count across factory stations and identifies peak coordinate.
     * Time Complexity: O(m * n)
     * Space Complexity: O(1)
     */
    public static SummaryResult factoryGridSummary(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return new SummaryResult(0, -1, -1);
        }

        int total = 0;
        int maxVal = Integer.MIN_VALUE;
        int maxRow = 0;
        int maxCol = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                int val = grid[r][c];
                total += val;
                if (val > maxVal) {
                    maxVal = val;
                    maxRow = r;
                    maxCol = c;
                }
            }
        }

        return new SummaryResult(total, maxRow, maxCol);
    }

    public static void main(String[] args) {
        int[][] floorGrid = {
            {5, 8, 3},
            {12, 4, 9},
            {2, 15, 6}
        };

        SummaryResult res = factoryGridSummary(floorGrid);
        System.out.println("Output: " + res);
    }
}
