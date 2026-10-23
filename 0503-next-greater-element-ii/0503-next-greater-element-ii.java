
import java.util.Stack;

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            answer[i] = -1;
        }

        for (int i = 2 * n - 1; i >= 0; i--) {
            int index = i % n;

            while (!stack.isEmpty() && stack.peek() <= nums[index]) {
                stack.pop();
            }

            if (i < n && !stack.isEmpty()) {
                answer[index] = stack.peek();
            }

            stack.push(nums[index]);
        }

        return answer;
    }
}
