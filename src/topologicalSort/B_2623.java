package topologicalSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B_2623 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // n singers;
        int m = sc.nextInt(); // n pds
        ArrayList<Integer>[] edges = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }
        int[] nLink = new int[n + 1];
        for (int i = 0; i < m; i++) {
            int nn = sc.nextInt();
            if (nn == 0) continue;
            int prev = sc.nextInt();
            for (int j = 0; j < nn - 1; j++) {
                int curr = sc.nextInt();
                edges[prev].add(curr);
                nLink[curr]++;
                prev = curr;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        // 위상정렬 초기화
        for (int i = 1; i <= n; i++) {
            if (nLink[i] == 0) q.offer(i);
        }

        boolean[] visited = new boolean[n+1];
        ArrayList<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            int curr = q.poll();
            if (visited[curr]) continue;
            ans.add(curr);
            visited[curr] = true;
            for (Integer next : edges[curr]) {
                nLink[next]--;
            }
            for (int i = 1; i <= n; i++) {
                if (nLink[i] == 0 && !visited[i]) {
                    q.offer(i);
                }
            }
        }
        if (ans.size() != n) System.out.println(0);
        else ans.stream().forEach(e -> System.out.println(e));
    }
}
