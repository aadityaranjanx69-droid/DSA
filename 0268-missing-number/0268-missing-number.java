class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int actualSum = 0;
        int sum = (n * (n + 1)) / 2;
        for(int i = 0; i < nums.length; i++){
            actualSum += nums[i];
        }
        int target = sum - actualSum;
        return target;
    }
}