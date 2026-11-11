class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long common = Math.min(need1, need2);
        long total = common * Math.min((long) costBoth, (long) cost1 + cost2);
        if (need1 > need2) {
            total += (long) (need1 - need2) * Math.min(cost1, costBoth);
        } else if (need2 > need1) {
            total += (long) (need2 - need1) * Math.min(cost2, costBoth);
        }
        return total;
    }
}