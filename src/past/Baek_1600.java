package past;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baek_1600 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int colLen = sc.nextInt();
        int rowLen = sc.nextInt();
        int[][] map = new int[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        int[] start = new int[]{0, 0, k, 0};
        boolean[][][] visited = new boolean[rowLen][colLen][k + 1];
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (visited[curr[0]][curr[1]][curr[2]]) continue;
            if (curr[0] == rowLen - 1 && curr[1] == colLen - 1) {
                System.out.println(curr[3]);
                System.exit(0);
            }
            visited[curr[0]][curr[1]][curr[2]] = true;
            if (map[curr[0]][curr[1]] == 1) continue;
            // left
            if (curr[1] - 1 >= 0) {
                q.offer(new int[] {curr[0], curr[1] - 1, curr[2], curr[3] + 1});
            }
            // right
            if (curr[1] + 1 < colLen) {
                q.offer(new int[] {curr[0], curr[1] + 1, curr[2], curr[3] + 1});
            }
            // up
            if (curr[0] - 1 >= 0) {
                q.offer(new int[] {curr[0] - 1, curr[1], curr[2], curr[3] + 1});
            }
            // down
            if (curr[0] + 1 < rowLen) {
                q.offer(new int[] {curr[0] + 1, curr[1], curr[2], curr[3] + 1});
            }

            // 말 움직임
            if (curr[2] > 0) {
                // 왼위 대각선1
                if (curr[0] - 1 >= 0 && curr[1] - 2 >= 0) {
                    q.offer(new int[] {curr[0] - 1, curr[1] - 2, curr[2] - 1, curr[3] + 1});
                }
                // 왼위 대각선2
                if (curr[0] - 2 >= 0 && curr[1] - 1 >= 0) {
                    q.offer(new int[] {curr[0] - 2, curr[1] - 1, curr[2] - 1, curr[3] + 1});
                }
                // 왼아래 대각선1
                if (curr[0] + 1 < rowLen && curr[1] - 2 >= 0) {
                    q.offer(new int[] {curr[0] + 1, curr[1] - 2, curr[2] - 1, curr[3] + 1});
                }
                // 왼아래 대각선2
                if (curr[0] + 2 < rowLen && curr[1] - 1 >= 0) {
                    q.offer(new int[] {curr[0] + 2, curr[1] - 1, curr[2] - 1, curr[3] + 1});
                }
                // 오른아래 대각선1
                if (curr[0] + 1 < rowLen && curr[1] + 2 < colLen) {
                    q.offer(new int[] {curr[0] + 1, curr[1] + 2, curr[2] - 1, curr[3] + 1});
                }
                // 오른아래 대각선2
                if (curr[0] + 2 < rowLen && curr[1] + 1 < colLen) {
                    q.offer(new int[] {curr[0] + 2, curr[1] + 1, curr[2] - 1, curr[3] + 1});
                }
                // 오른위 대각선1
                if (curr[0] - 1 >= 0 && curr[1] + 2 < colLen) {
                    q.offer(new int[] {curr[0] - 1, curr[1] + 2, curr[2] - 1, curr[3] + 1});
                }
                // 오른위 대각선2
                if (curr[0] - 2 >= 0 && curr[1] + 1 < colLen) {
                    q.offer(new int[] {curr[0] - 2, curr[1] + 1, curr[2] - 1, curr[3] + 1});
                }
            }
        }
        System.out.println("-1");
    }
}
