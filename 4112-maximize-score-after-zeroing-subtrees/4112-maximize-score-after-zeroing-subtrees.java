class Solution {
    public int maxScore(int[] parent, int[] op, int[] score) {
        int n = parent.length;
        int[] head = new int[n], nxt = new int[n], cnt = new int[n];
        java.util.Arrays.fill(head, -1);
        for (int i = 1; i < n; i++) {
            nxt[i] = head[parent[i]];
            head[parent[i]] = i;
            cnt[parent[i]]++;
        }

        int[] order = new int[n];
        int qh = 0, qt = 0;
        order[qt++] = 0;
        while (qh < qt) {
            int v = order[qh++];
            for (int c = head[v]; c != -1; c = nxt[c]) order[qt++] = c;
        }

        long[] sum = new long[n];
        long[] minDp = new long[n];
        java.util.Arrays.fill(minDp, Long.MAX_VALUE);

        long dp = 0;
        for (int k = n - 1; k >= 0; k--) {
            int v = order[k];
            if (cnt[v] == 0) {
                dp = score[v];
            } else {
                long loss = (op[v] == 1) ? minDp[v] : Math.min(0L, minDp[v]);
                dp = score[v] + sum[v] - loss;
            }
            if (v != 0) {
                int p = parent[v];
                sum[p] += dp;
                if (dp < minDp[p]) minDp[p] = dp;
            }
        }
        return (int) dp;
    }
}