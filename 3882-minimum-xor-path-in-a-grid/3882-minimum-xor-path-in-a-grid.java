class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] dp = new boolean[n][1024];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int v = grid[i][j];
                boolean[] cur = new boolean[1024];

                if (i == 0 && j == 0) {
                    cur[v] = true;
                } else {
                    for (int x = 0; x < 1024; x++) {
                        boolean fromAbove = i > 0 && dp[j][x];      // old dp[j] = row above
                        boolean fromLeft = j > 0 && dp[j - 1][x];   // new dp[j-1] = cell to the left
                        if (fromAbove || fromLeft) {
                            cur[x ^ v] = true;
                        }
                    }
                }
                dp[j] = cur;
            }
        }

        for (int x = 0; x < 1024; x++) {
            if (dp[n - 1][x]) return x;
        }
        return -1;
    }
}