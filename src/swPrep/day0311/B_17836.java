package swPrep.day0311;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B_17836 {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int t = sc.nextInt();
        int[][] map = new int[n][m];
        boolean[][][] visited = new boolean[n][m][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0, map[0][0] == 2 ? 1 : 0});
        int time = 0;
        boolean foundPrincess = false;
        bfs:
        while(!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                if (visited[curr[0]][curr[1]][curr[2]]) continue;
                if (curr[0] == n - 1 && curr[1] == m - 1) {
                    foundPrincess = true;
                    break bfs;
                }
                visited[curr[0]][curr[1]][curr[2]] = true;
                for (int di = 0; di < 4; di++) {
                    int nextRow = curr[0] + dy[di];
                    int nextCol = curr[1] + dx[di];
                    if (nextRow >= 0 && nextCol >= 0 && nextRow < n && nextCol < m) {
                        if (curr[2] == 1 || map[nextRow][nextCol] != 1) {
                            q.offer(new int[] {nextRow, nextCol,
                                    (map[nextRow][nextCol] == 2 ? 1 : curr[2])});
                        }
                    }
                }
            }
            time++;
            if (time > t) break;
        }

        if (foundPrincess) {
            System.out.println(time);
        }
        else {
            System.out.println("Fail");
        }
    }
}
