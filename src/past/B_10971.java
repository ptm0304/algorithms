package past;

import java.util.Scanner;

public class B_10971 {
    static int n;
    static int[][] paths;
    static int min = Integer.MAX_VALUE;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        visited = new boolean[n];
        paths = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                paths[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            visited[i] = true;
            perm(i, i, 0, 0);
            visited[i] = false;
        }

        System.out.println(min);
    }

    static void perm(int prev, int start, int dist, int sum) {
        if (dist == n - 1) {
            if (paths[prev][start] != 0) {
                min = Math.min(min, sum + paths[prev][start]);
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visited[i] || paths[prev][i] == 0) continue;
            visited[i] = true;
            perm(i, start, dist + 1, sum + paths[prev][i]);
            visited[i] = false;
        }
    }
}
