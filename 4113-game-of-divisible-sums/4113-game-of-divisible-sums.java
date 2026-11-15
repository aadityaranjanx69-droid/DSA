class Solution {
    public long maxDifference(int[] nums) {
        int n = nums.length;
        long[] f = new long[n + 2];

        for (int i = n - 1; i >= 0; i--) {
            long s1 = nums[i];
            long best = (s1 % 3 == 0) ? s1 - f[i + 1] : s1 + f[i + 1];

            if (i + 1 < n) {
                long s2 = s1 + nums[i + 1];
                long v = (s2 % 3 == 0) ? s2 - f[i + 2] : s2 + f[i + 2];
                best = Math.max(best, v);
            }
            f[i] = best;
        }
        return f[0];
    }
}