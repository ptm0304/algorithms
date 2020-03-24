package past;

import java.util.Scanner;

public class Sw_blacktree {
    static boolean[][] edges;
    static boolean[] visited;
    static long[][] memo;
    final static int BLACK = 0;
    final static int WHITE = 1;
    static int n;

    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();
        for (int tc = 1; tc<= nt; tc++) {
            n = sc.nextInt();
            edges = new boolean[n + 1][n + 1];
            visited = new boolean[n + 1];
            memo = new long[n + 1][2];
            for (int i = 0; i < n - 1; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                edges[a][b] = true;
                edges[b][a] = true;
            }
            long ans = helper(1, BLACK) + helper(1, WHITE);
            System.out.println("#" + tc + " " + ans % 1000000007);

        }
    }

    static long helper(int idx, int color) {
        if (memo[idx][color] != 0) return memo[idx][color];
        long ans = 1;
        visited[idx] = true;
        for (int i = 1; i <=n; i++) {
            if (visited[i] || !edges[idx][i]) continue;
            if (color == WHITE) {
                ans *= (helper(i, BLACK) + helper(i, WHITE));
            }
            else {
                ans *= helper(i, WHITE);
            }
            ans %= 1000000007;
        }
        memo[idx][color] = ans;
        visited[idx] = false;
        return ans;
    }
}