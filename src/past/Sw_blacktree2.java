package past;

import java.util.*;

public class Sw_blacktree2 {

    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();
        for (int tc = 1; tc<= nt; tc++) {
            int n = sc.nextInt();
            ArrayList<Integer>[] parents = new ArrayList[n + 1];
            for (int i = 0; i < n - 1; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                if (parents[a] == null) {
                    parents[a] = new ArrayList<>();
                }
                if (parents[b] == null) {
                    parents[b] = new ArrayList<>();
                }
                parents[a].add(b);
                parents[b].add(a);
            }
            Queue<Integer> q = new LinkedList<>();
            long[][] dp = new long[n + 1][2];
            for (int i = 1; i <= n; i++) {
                dp[i] = new long[] {1, 1};
                if (parents[i].size() == 1) {
                    q.offer(i);
                }
            }

            long ans = 0;
            boolean[] visited = new boolean[n + 1];
            while (!q.isEmpty()) {
                int curr = q.poll();
                ans = dp[curr][0] + dp[curr][1];
                for (Integer parent : parents[curr]) {
                    int idx = parents[parent].indexOf(curr);
                    if (idx != -1) {
                        parents[parent].remove(idx);
                    }
                    dp[parent][0] *= (dp[curr][0] + dp[curr][1]) % 1000000007;
                    dp[parent][1] *= (dp[curr][0]);
                    dp[parent][0] %= 1000000007;
                    dp[parent][1] %= 1000000007;
                    if (!visited[parent]) {
                        q.offer(parent);
                    }
                    visited[parent] = true;
                }
            }
            System.out.println("#" + tc + " " + ans % 1000000007);
        }
    }
}
