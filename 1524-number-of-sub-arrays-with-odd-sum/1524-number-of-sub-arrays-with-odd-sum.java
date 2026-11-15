class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        int odd = 0;
        int even = 1;
        int sum = 0;
        int result = 0;

        for (int num : arr) {
            sum += num;
            if (sum % 2 == 0) {
                result = (result + odd) % MOD;
                even++;
            } else {
                result = (result + even) % MOD;
                odd++;
            }
        }

        return result;
    }
}