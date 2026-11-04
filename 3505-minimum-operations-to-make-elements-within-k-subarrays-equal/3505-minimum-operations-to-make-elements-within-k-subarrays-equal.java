import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        int[] vals = Arrays.stream(nums).distinct().sorted().toArray();
        int m = vals.length;
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[i] = Arrays.binarySearch(vals, nums[i]) + 1;
        }

        long[] cntBit = new long[m + 1];
        long[] sumBit = new long[m + 1];

        int highest = Integer.highestOneBit(m);
        long[] cost = new long[n + 1];
        long windowSum = 0;

        for (int i = 0; i < n; i++) {
            for (int t = rank[i]; t <= m; t += t & -t) {
                cntBit[t] += 1;
                sumBit[t] += nums[i];
            }
            windowSum += nums[i];
            if (i >= x) {
                for (int t = rank[i - x]; t <= m; t += t & -t) {
                    cntBit[t] -= 1;
                    sumBit[t] -= nums[i - x];
                }
                windowSum -= nums[i - x];
            }

            if (i >= x - 1) {
                long kk = (x + 1) / 2;
                int pos = 0;
                for (int pw = highest; pw > 0; pw >>= 1) {
                    if (pos + pw <= m && cntBit[pos + pw] < kk) {
                        pos += pw;
                        kk -= cntBit[pos];
                    }
                }
                long med = vals[pos];

                long cntLe = 0, sumLe = 0;
                for (int t = pos + 1; t > 0; t -= t & -t) {
                    cntLe += cntBit[t];
                    sumLe += sumBit[t];
                }
                cost[i + 1] = med * cntLe - sumLe + (windowSum - sumLe) - med * (x - cntLe);
            }
        }

        final long INF = Long.MAX_VALUE / 4;
        long[] prev = new long[n + 1];
        for (int j = 1; j <= k; j++) {
            long[] cur = new long[n + 1];
            Arrays.fill(cur, INF);
            for (int i = j * x; i <= n; i++) {
                long best = cur[i - 1];
                if (prev[i - x] < INF) {
                    best = Math.min(best, prev[i - x] + cost[i]);
                }
                cur[i] = best;
            }
            prev = cur;
        }
        return prev[n];
    }
}