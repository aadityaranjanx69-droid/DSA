class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length()) return false;
        
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        
        for(int i = 0; i < s1.length(); i++){
            count1[s1.charAt(i) - 'a']++;
            count2[s2.charAt(i) - 'a']++;
        }
        
        for(int i = s1.length(); i < s2.length(); i++){
            if(Arrays.equals(count1, count2)) return true;
            count2[s2.charAt(i) - 'a']++;
            count2[s2.charAt(i - s1.length()) - 'a']--;
        }
        
        return Arrays.equals(count1, count2);
    }
}