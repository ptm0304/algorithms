package topologicalSort;

import java.util.Scanner;

public class Sw_heightOrder {
    static boolean[][] edge;
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();
        for (int tc = 1; tc <= nt; tc++) {
            n = sc.nextInt();
            int m = sc.nextInt();
            edge = new boolean[n+1][n+1];
            for (int i = 0; i < m; i++) {
                int s = sc.nextInt();
                int t = sc.nextInt();
                edge[s][t] = true;
            }

            for (int i = 1; i <=n; i++) {
                for (int j = 1; j <= n; j++) {
                    for (int k = 1; k <= n; k++) {
                        if (edge[j][i] && edge[i][k]) {
                            edge[j][k] = true;
                        }
                    }
                }
            }

            int ans = 0;
            for (int i = 1; i <= n ; i++) {
                int cnt = 0;
                for (int j = 1; j <= n; j++) {
                    if (i == j) continue;
                    if (edge[i][j] || edge[j][i]) cnt++;
                }
                if (cnt == n - 1) ans++;
            }

            System.out.println("#" + tc + " " + ans);
        }
    }
}
