import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) graph[e[0]].add(new int[]{e[1], e[2]});

        final long INF = Long.MAX_VALUE;
       
        long[][] dist = new long[n][k + 1];
        for (long[] row : dist) Arrays.fill(row, INF);

      
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        dist[0][1] = 0;
        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1];
            int run = (int) cur[2];

            if (d > dist[u][run]) continue;        
            if (u == n - 1) return (int) d;         

            for (int[] edge : graph[u]) {
                int v = edge[0];
                int w = edge[1];
                int newRun = labels.charAt(v) == labels.charAt(u) ? run + 1 : 1;
                if (newRun > k) continue;           
                long nd = d + w;
                if (nd < dist[v][newRun]) {
                    dist[v][newRun] = nd;
                    pq.offer(new long[]{nd, v, newRun});
                }
            }
        }
        return -1;
    }
}