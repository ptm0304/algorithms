package past;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baek_1726 {
    static int m;
    static int n;
    static int[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt(); // 세로 길이
        n = sc.nextInt(); // 가로 길이
        map = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        int[] start = new int[4];
        int[] dest = new int[3];
        for (int i = 0; i < 3; i++) {
            start[i] = sc.nextInt() - 1;
        }
        for (int i = 0; i < 3; i++) {
            dest[i] = sc.nextInt() - 1;
        }
        boolean[][][] visited = new boolean[m][n][4];
        Queue<int[]> q = new LinkedList();
        q.offer(start);
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (visited[curr[0]][curr[1]][curr[2]]) {
                continue;
            }
            if (dest[0] == curr[0] && dest[1] == curr[1] && dest[2] == curr[2]) {
                System.out.println(curr[3]);
                break;
            }
            visited[curr[0]][curr[1]][curr[2]] = true;
            addNextMoves(q, curr);
        }

    }
    static void addNextMoves(Queue<int[]> q, int[] curr) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        // 바라보는 방향으로 1칸 ~ 3칸 이동
        int nextY = curr[0];
        int nextX = curr[1];
        for (int i = 0; i < 3; i++) {
            nextY += dy[curr[2]];
            nextX += dx[curr[2]];
            if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < m && map[nextY][nextX] == 0) {
                q.offer(new int[] {nextY, nextX, curr[2], curr[3] + 1});
            }
            else {
                break;
            }
        }
        // 90도로 왼쪽/오른쪽으로 회전
        q.offer(new int[] {curr[0], curr[1], (2 * (curr[2] / 2) + 2) % 4, curr[3] + 1});
        q.offer(new int[] {curr[0], curr[1], (2 * (curr[2] / 2) + 3) % 4, curr[3] + 1});
    }
}
