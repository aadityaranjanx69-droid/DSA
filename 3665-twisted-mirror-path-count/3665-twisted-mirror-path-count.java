class Solution {
    public int uniquePaths(int[][] grid) {
        final int MOD = 1_000_000_007;
        int m = grid.length, n = grid[0].length;

        long[][] R = new long[m + 1][n + 1]; 
        long[][] D = new long[m + 1][n + 1]; 
        R[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    long total = (R[i][j] + D[i][j]) % MOD;
                    if (i == m - 1 && j == n - 1) return (int) total;
                    R[i][j + 1] = (R[i][j + 1] + total) % MOD;
                    D[i + 1][j] = (D[i + 1][j] + total) % MOD;
                } else {
                    
                    D[i + 1][j] = (D[i + 1][j] + R[i][j]) % MOD;

                    R[i][j + 1] = (R[i][j + 1] + D[i][j]) % MOD;
                }
            }
        }
        return (int) ((R[m - 1][n - 1] + D[m - 1][n - 1]) % MOD);
    }
}