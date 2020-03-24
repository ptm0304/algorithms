package past;

import java.util.ArrayList;
import java.util.Scanner;

public class Sw_blacktree3 {
    static ArrayList<Integer>[] edges;
    static boolean[] visited;
    static long[][] memo;
    static int n;

    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();
        for (int tc = 1; tc<= nt; tc++) {
            n = sc.nextInt();
            edges = new ArrayList[n + 1];
            visited = new boolean[n + 1];
            memo = new long[n + 1][2];
            for (int i = 0; i < n - 1; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                if (edges[a] == null) {
                    edges[a] = new ArrayList<>();
                }
                if (edges[b] == null) {
                    edges[b] = new ArrayList<>();
                }
                edges[a].add(b);
                edges[b].add(a);
            }
            for (int i = 1; i <= n; i++) {
                memo[i] = new long[] {1, 1};
            }
            long ans = helper(1);
            System.out.println("#" + tc + " " + ans % 1000000007);
        }
    }

    static long helper(int idx) {
        if (visited[idx]) return memo[idx][0] + memo[idx][1];
        visited[idx] = true;
        for (Integer neighbor: edges[idx]) {
            if (visited[neighbor]) continue;
            helper(neighbor);
            memo[idx][0] *= (memo[neighbor][0] + memo[neighbor][1]) % 1000000007;
            memo[idx][1] *= memo[neighbor][0];
            memo[idx][0] %= 1000000007;
            memo[idx][1] %= 1000000007;
        }
        return memo[idx][0] + memo[idx][1];
    }
}