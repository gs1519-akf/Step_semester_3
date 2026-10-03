public class WarehouseGridSummary {

    public static class Coordinate {
        public final int row;
        public final int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "(" + row + ", " + col + ")";
        }
    }

    public static class SummaryResult {
        public final int totalItems;
        public final Coordinate maxCoordinate;

        public SummaryResult(int totalItems, Coordinate maxCoordinate) {
            this.totalItems = totalItems;
            this.maxCoordinate = maxCoordinate;
        }

        @Override
        public String toString() {
            return "(" + totalItems + ", " + maxCoordinate + ")";
        }
    }

    /**
     * Problem 2: Warehouse Grid Summary
     * Iterates through a 2D warehouse grid to compute total count and peak bin coordinate.
     * Ties broken by first encountered in row-major order.
     * 
     * Time Complexity: O(m * n) where m is number of rows and n is number of columns.
     * Additional Space Complexity: O(1) beyond input grid.
     */
    public static SummaryResult warehouseSummary(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return new SummaryResult(0, new Coordinate(0, 0));
        }

        int total = 0;
        int maxVal = Integer.MIN_VALUE;
        int maxRow = 0;
        int maxCol = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                int count = grid[r][c];
                total += count;
                // Strictly greater maintains the first encountered bin on ties
                if (count > maxVal) {
                    maxVal = count;
                    maxRow = r;
                    maxCol = c;
                }
            }
        }

        return new SummaryResult(total, new Coordinate(maxRow, maxCol));
    }

    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };

        System.out.println("Grid: [[4, 9, 2], [7, 1, 6], [3, 12, 5]]");
        System.out.println("Expected Output: (49, (2, 1))");
        System.out.println("Actual Output:   " + warehouseSummary(grid));
    }
}
