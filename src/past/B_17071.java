package past;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B_17071 {
    static int[] dx = {-1, 1};
    static boolean[][] visited = new boolean[500001][2];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        Queue<int[]> q = new LinkedList<>();
        int[] start = {n, k, 0};
        q.offer(start);
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (visited[curr[1]][curr[2] % 2]){
                System.out.println(curr[2]);
                System.exit(0);
            }
            if (curr[0] == curr[1]) {
                System.out.println(curr[2]);
                System.exit(0);
            }
            if (visited[curr[0]][curr[2] % 2]) continue;
            visited[curr[0]][curr[2] % 2] = true;
            int time = curr[2] + 1;
            int nextK = curr[1] + time;
            if (nextK > 500000) continue;

            for (int di = 0; di < 2; di++) {
                int nextN = curr[0] + dx[di];
                if (nextN <= 500000 && nextN >= 0) {
                    q.offer(new int[] {nextN, nextK, time});
                }
            }

            int nextN = curr[0] * 2;
            if (nextN <= 500000) {
                q.offer(new int[] {nextN, nextK, time});
            }
        }

        System.out.println(-1);
    }
}
