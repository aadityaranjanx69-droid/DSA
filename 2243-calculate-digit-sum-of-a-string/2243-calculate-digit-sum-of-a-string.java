class Solution {
    public String digitSum(String s, int k) {
        while (s.length() > k) {
            String next = "";
            for (int i = 0; i < s.length(); i += k) {
                String group = s.substring(i, Math.min(i + k, s.length()));
                int sum = 0;
                for (int j = 0; j < group.length(); j++) {
                    sum += Integer.parseInt(String.valueOf(group.charAt(j)));
                }
                next = next + sum;
            }
            s = next;
        }
        return s;
    }
}