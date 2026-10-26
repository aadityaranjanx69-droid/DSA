class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int count = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long total = grid[i][j];
                    grid[i][j] = 0; // mark visited

                    java.util.ArrayDeque<int[]> stack = new java.util.ArrayDeque<>();
                    stack.push(new int[]{i, j});

                    while (!stack.isEmpty()) {
                        int[] cur = stack.pop();
                        for (int[] d : dirs) {
                            int nx = cur[0] + d[0];
                            int ny = cur[1] + d[1];
                            if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] > 0) {
                                total += grid[nx][ny];
                                grid[nx][ny] = 0;
                                stack.push(new int[]{nx, ny});
                            }
                        }
                    }

                    if (total % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}