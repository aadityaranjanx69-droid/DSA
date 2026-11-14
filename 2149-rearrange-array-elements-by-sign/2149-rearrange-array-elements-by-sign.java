class Solution {
    public int[] rearrangeArray(int[] nums) {

        int[] answer = new int[nums.length];

        int positiveIndex = 0;
        int negativeIndex = 1;

        int i = 0;

        while (i < nums.length) {

            if (nums[i] > 0) {
                answer[positiveIndex] = nums[i];
                positiveIndex += 2;
            }
            else {
                answer[negativeIndex] = nums[i];
                negativeIndex += 2;
            }

            i++;
        }

        return answer;
    }
}