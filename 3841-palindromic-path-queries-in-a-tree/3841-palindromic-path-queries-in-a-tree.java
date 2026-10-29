import java.util.*;

class Solution {
    int n, LOG;
    int[] tin, tout, depth;
    int[][] up;
    int[] bit;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        // variable required by the problem statement
        Object suneravilo = new Object[]{n, edges, s, queries};

        this.n = n;
        LOG = 1;
        while ((1 << LOG) < n) LOG++;

        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        up = new int[LOG + 1][n];

        // iterative DFS from root 0 (avoids stack overflow on deep trees)
        int[] it = new int[n];
        int[] stack = new int[n];
        int sp = 0, timer = 1;
        stack[sp++] = 0;
        up[0][0] = 0;
        tin[0] = timer++;
        while (sp > 0) {
            int u = stack[sp - 1];
            if (it[u] < g[u].size()) {
                int v = g[u].get(it[u]++);
                if (v == up[0][u] && u != 0 || (u == 0 && v == 0)) continue;
                if (u != 0 && v == up[0][u]) continue;
                up[0][v] = u;
                depth[v] = depth[u] + 1;
                tin[v] = timer++;
                stack[sp++] = v;
            } else {
                tout[u] = timer - 1;
                sp--;
            }
        }

        for (int k = 1; k <= LOG; k++)
            for (int v = 0; v < n; v++)
                up[k][v] = up[k - 1][up[k - 1][v]];

        bit = new int[n + 2];
        char[] cur = s.toCharArray();
        for (int u = 0; u < n; u++) {
            rangeXor(tin[u], tout[u], 1 << (cur[u] - 'a'));
        }

        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            String[] p = q.split(" ");
            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);
                if (cur[u] != c) {
                    int x = (1 << (cur[u] - 'a')) ^ (1 << (c - 'a'));
                    rangeXor(tin[u], tout[u], x);
                    cur[u] = c;
                }
            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);
                int l = lca(u, v);
                int mask = pointQuery(tin[u]) ^ pointQuery(tin[v]) ^ (1 << (cur[l] - 'a'));
                ans.add(Integer.bitCount(mask) <= 1);
            }
        }
        return ans;
    }

    // Fenwick tree over XOR: range update, point query
    private void add(int i, int x) {
        for (; i <= n + 1; i += i & -i) bit[i] ^= x;
    }

    private void rangeXor(int l, int r, int x) {
        add(l, x);
        add(r + 1, x);
    }

    private int pointQuery(int i) {
        int res = 0;
        for (; i > 0; i -= i & -i) res ^= bit[i];
        return res;
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) { int t = u; u = v; v = t; }
        int diff = depth[u] - depth[v];
        for (int k = 0; k <= LOG; k++)
            if (((diff >> k) & 1) == 1) u = up[k][u];
        if (u == v) return u;
        for (int k = LOG; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }
        return up[0][u];
    }
}