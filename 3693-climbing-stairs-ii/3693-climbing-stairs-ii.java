class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int k = 1; k <= 3 && i - k >= 0; k++) {
                dp[i] = Math.min(dp[i], dp[i - k] + costs[i - 1] + k * k);
            }
        }
        return dp[n];
    }
}