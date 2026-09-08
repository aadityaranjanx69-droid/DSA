class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int one = 0;
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 1){
                one++;
            }
            if(nums[i] == 0){
                if(one > count){
                    count = one;
                }
                one = 0;
            }
        }
        if(one > count){
            count = one;
        }
        return count;
    }
}