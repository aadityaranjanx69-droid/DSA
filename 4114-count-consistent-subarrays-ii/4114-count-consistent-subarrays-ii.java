class Solution {
    public long countConsistent(int[] nums) {
        int n = nums.length;
        int[] last = new int[1_000_001];
        java.util.Arrays.fill(last, -1);

        int[] val = new int[40], lo = new int[40], hi = new int[40];
        int sz = 0;
        long ans = 0;

        for (int r = 0; r < n; r++) {
            int x = nums[r];
            last[x] = r;

            for (int i = 0; i < sz; i++) val[i] &= x;

            val[sz] = x;
            lo[sz] = r;
            hi[sz] = r;
            sz++;

            int k = 0;
            for (int i = 0; i < sz; i++) {
                if (k > 0 && val[k - 1] == val[i]) {
                    hi[k - 1] = hi[i];
                } else {
                    val[k] = val[i];
                    lo[k] = lo[i];
                    hi[k] = hi[i];
                    k++;
                }
            }
            sz = k;

            for (int i = 0; i < sz; i++) {
                int p = last[val[i]];
                if (p >= lo[i]) {
                    ans += Math.min(hi[i], p) - lo[i] + 1;
                }
            }
        }
        return ans;
    }
}