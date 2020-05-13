package bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B_1194 {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        char[][] map = new char[n][m];
        int[] start = {0,0,0};
        for (int i = 0; i < n; i++) {
            String str = sc.next();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == '0') {
                    start = new int[] {i,j,0};
                    map[i][j] = '.';
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        boolean[][][] visited = new boolean[n][m][64];
        int ans = -1;
        int steps = 0;

        bfs:
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                if (visited[curr[0]][curr[1]][curr[2]]) continue;
                visited[curr[0]][curr[1]][curr[2]] = true;

                char currChar = map[curr[0]][curr[1]];
                if (currChar == '1') {
                    ans = steps;
                    break bfs;
                }
                else if (currChar >= 'a' && currChar <= 'f') {
                    curr[2] = (curr[2] | (1 << (currChar - 'a')));
                }
                else if (currChar >= 'A' && currChar <= 'F') {
                    if ((curr[2] & (1 << (Character.toLowerCase(currChar) - 'a'))) == 0) {
                        continue;
                    }
                }
                else if (currChar == '#') {
                    continue;
                }

                for (int di = 0; di < 4; di++) {
                    int nextRow = curr[0] + dy[di];
                    int nextCol = curr[1] + dx[di];
                    if (nextRow >= 0 && nextCol >= 0 && nextRow < n && nextCol < m) {
                        q.offer(new int[] {nextRow, nextCol, curr[2]});
                    }
                }
            }
            steps++;
        }

        System.out.println(ans);
    }
}
