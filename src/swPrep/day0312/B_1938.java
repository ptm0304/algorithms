package swPrep.day0312;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B_1938 {
    final static int HORIZONTAL = 1; // 가로
    final static int VERTICAL = 0; // 세로
    final static int[] dx = {0,0,-1,1};
    final static int[] dy = {-1,1,0,0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[][] map = new char[n][n];
        boolean[][][] visited = new boolean[n][n][2];

        for (int i = 0; i < n; i++) {
            String line = sc.next();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int[] start = {};
        int[] dest = {};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 'B' && start.length == 0) {
                    if (i < n - 1 && map[i + 1][j] == 'B') {
                        start = new int[]{i + 1, j, VERTICAL};
                    }
                    else if (j < n - 1 && map[i][j + 1] == 'B') {
                        start = new int[]{i, j + 1, HORIZONTAL};
                    }
                }
                else if (map[i][j] == 'E' && dest.length == 0) {
                    if (i < n - 1 && map[i + 1][j] == 'E') {
                        dest = new int[]{i + 1, j, VERTICAL};
                    }
                    else if (j < n - 1 && map[i][j + 1] == 'E') {
                        dest = new int[]{i, j + 1, HORIZONTAL};
                    }
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        int cnt = 0;
        int ans = 0;
        bfs: while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] curr =  q.poll();
                if (visited[curr[0]][curr[1]][curr[2]]) continue;
                if (curr[0] == dest[0] && curr[1] == dest[1] && curr[2] == dest[2]) {
                    ans = cnt;
                    break bfs;
                }
                visited[curr[0]][curr[1]][curr[2]] = true;
                checkTurn:
                if (curr[0] > 0 && curr[1] > 0 && curr[0] < n - 1 && curr[1] < n - 1) {
                    for (int ii = curr[0] - 1; ii <= curr[0] + 1; ii++) {
                        for (int jj = curr[1] - 1; jj <= curr[1] + 1; jj++) {
                            if (map[ii][jj] == '1') break checkTurn;
                        }
                    }
                    q.offer(new int[] {curr[0], curr[1], (curr[2] + 1) % 2});
                }
                for (int di = 0; di < 4; di++) {
                    int nextRow = curr[0] + dy[di];
                    int nextCol = curr[1] + dx[di];
                    if (curr[2] == HORIZONTAL && nextRow >= 0 && nextRow < n &&
                            nextCol > 0 && nextCol < n - 1 && map[nextRow][nextCol] != '1' &&
                    map[nextRow][nextCol - 1] != '1' && map[nextRow][nextCol + 1] != '1') {
                        q.offer(new int[]{nextRow, nextCol, HORIZONTAL});
                    }
                    if (curr[2] == VERTICAL && nextCol >= 0 && nextCol < n &&
                            nextRow > 0 && nextRow < n - 1 && map[nextRow][nextCol] != '1' &&
                    map[nextRow + 1][nextCol] != '1' && map[nextRow - 1][nextCol] != '1') {
                        q.offer(new int[]{nextRow, nextCol, VERTICAL});
                    }
                }
            }
            cnt++;
        }
        System.out.println(ans);
    }
}
