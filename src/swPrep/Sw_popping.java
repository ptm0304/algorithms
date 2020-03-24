package swPrep;

import java.util.Scanner;

public class Sw_popping {
    static int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};
    static char[][] map;
    static boolean[][] visited;
    static int n;
    static int nSafe;
    static int nClicks;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();
        for (int tc = 1; tc <= nt; tc++) {
            n = sc.nextInt();
            nSafe = 0;
            nClicks = 0;

            visited = new boolean[n][n];
            map = new char[n][n];
            for (int i = 0; i < n; i++) {
                String line = sc.next();
                for (int j = 0; j < n; j++) {
                    map[i][j] = line.charAt(j);
                    if (map[i][j] == '.') nSafe++;
                }
            }

            outer:
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (nSafe == 0) break outer;
                    if (map[i][j] == '.' && !visited[i][j]) {
                        int cnt = 0;
                        for (int di = 0; di < 8; di++) {
                            int nr = i + dy[di];
                            int nc = j + dx[di];
                            if (nr < 0 || nc < 0 || nr >= n || nc >= n) {
                                cnt++;
                                continue;
                            }
                            if (map[nr][nc] == '.') {
                                cnt++;
                            }
                        }
                        if (cnt == 8) {
                            nClicks++;
                            nSafe--;
                            visited[i][j] = true;
                            for (int di = 0; di < 8; di++) {
                                int nr = i + dy[di];
                                int nc = j + dx[di];
                                if (nr >= 0 && nc >= 0 && nr < n && nc < n) {
                                    dfs(nr, nc);
                                }
                            }
                        }
                    }
                }
            }

            System.out.println("#" + tc + " " + (nClicks + nSafe));

        }
    }

    static void dfs (int row, int col) {
        if (visited[row][col]) return;
        visited[row][col] = true;
        nSafe--;

        int cnt = 0;
        for (int di = 0; di < 8; di++) {
            int nr = row + dy[di];
            int nc = col + dx[di];
            if (nr < 0 || nc < 0 || nr >= n || nc >= n) {
                cnt++;
                continue;
            }
            if (map[nr][nc] == '.') {
                cnt++;
            }
        }
        if (cnt == 8) {
            for (int di = 0; di < 8; di++) {
                int nr = row + dy[di];
                int nc = col + dx[di];
                if (nr >= 0 && nc >= 0 && nr < n && nc < n) {
                    dfs(nr, nc);
                }
            }
        }
    }
}
