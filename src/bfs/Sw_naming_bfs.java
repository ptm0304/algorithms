package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Sw_naming_bfs {
    static int n, m;
    static String[] map;
    static char[] dp;
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int nt = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= nt; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            map = new String[n];
            dp = new char[n + m - 1];
            for (int i = 0; i < n; i++) {
                map[i] = br.readLine();
            }

            Helper start = new Helper(0, 0, null);
            Deque<Helper> q = new LinkedList<>();
            q.offer(start);
            boolean[][] visited = new boolean[n][m];
            while (!q.isEmpty()) {
                Helper curr = q.poll();
                if (dp[curr.col + curr.row] != '\u0000' && map[curr.row].charAt(curr.col) > dp[curr.col + curr.row]) {
                    continue;
                }
                if (dp[curr.col + curr.row] != '\u0000' &&  map[curr.row].charAt(curr.col) == dp[curr.col + curr.row]) {
                    if (visited[curr.row][curr.col]) continue;
                }
                if ((curr.col + curr.row) != 0 && map[curr.prev.row].charAt(curr.prev.col) > dp[curr.col + curr.row - 1]) {
                    continue;
                }
                dp[curr.row + curr.col] = map[curr.row].charAt(curr.col);
                if (curr.row == n - 1 && curr.col == m - 1) {
                    break;
                }
                visited[curr.row][curr.col] = true;

                char prev = 'z' + 1;

                for (int di = 0; di < 2; di++) {
                    int nextRow = curr.row + dy[di];
                    int nextCol = curr.col + dx[di];
                    if (nextRow < n && nextCol < m) {
                        map[nextRow].charAt(nextCol);
                        if (dp[nextRow + nextCol] == '\u0000' ||
                                map[nextRow].charAt(nextCol) <= dp[nextRow + nextCol]) {
                            if (prev == map[nextRow].charAt(nextCol)) {
                                q.offer(new Helper(nextRow, nextCol, curr));
                            }
                            else if (prev > map[nextRow].charAt(nextCol)) {
                                if (prev != 'z' + 1) {
                                    q.removeLast();
                                }
                                q.offer(new Helper(nextRow, nextCol, curr));
                            }
                            prev = map[nextRow].charAt(nextCol);
                        }
                    }
                }
            }
            sb.append("#" + tc + " " + String.valueOf(dp) + '\n');
        }
        System.out.println(sb.toString());
    }
    static class Helper {
        int row, col;
        Helper prev;

        public Helper(int row, int col, Helper prev) {
            this.row = row;
            this.col = col;
            this.prev = prev;
        }
    }
}

