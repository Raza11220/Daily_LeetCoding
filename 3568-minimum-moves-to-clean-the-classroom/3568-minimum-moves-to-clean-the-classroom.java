import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int[][] id = new int[m][n];

        int sx = 0, sy = 0;
        int cnt = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    sx = i;
                    sy = j;
                } else if (ch == 'L') {
                    id[i][j] = cnt++;
                }
            }
        }

        if (cnt == 0) {
            return 0;
        }

        boolean[][][][] vis =
                new boolean[m][n][energy + 1][1 << cnt];

        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{sx, sy, energy, (1 << cnt) - 1});

        vis[sx][sy][energy][(1 << cnt) - 1] = true;

        int[] dir = {-1, 0, 1, 0, -1};

        int steps = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            while (size-- > 0) {

                int[] cur = q.poll();

                int x = cur[0];
                int y = cur[1];
                int e = cur[2];
                int mask = cur[3];

                if (mask == 0) {
                    return steps;
                }

                if (e == 0 && classroom[x].charAt(y) != 'R') {
                    continue;
                }

                if (classroom[x].charAt(y) == 'R') {
                    e = energy;
                }

                for (int k = 0; k < 4; k++) {

                    int nx = x + dir[k];
                    int ny = y + dir[k + 1];

                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                        continue;
                    }

                    char ch = classroom[nx].charAt(ny);

                    if (ch == 'X') {
                        continue;
                    }

                    int ne = e - 1;

                    if (ne < 0) {
                        continue;
                    }

                    if (ch == 'R') {
                        ne = energy;
                    }

                    int nmask = mask;

                    if (ch == 'L') {
                        nmask &= ~(1 << id[nx][ny]);
                    }

                    if (!vis[nx][ny][ne][nmask]) {
                        vis[nx][ny][ne][nmask] = true;
                        q.offer(new int[]{nx, ny, ne, nmask});
                    }
                }
            }

            steps++;
        }

        return -1;
    }
}