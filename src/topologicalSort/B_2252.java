package topologicalSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B_2252 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<Integer>[] edges = new ArrayList[n + 1];
        int[] nLinks = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            edges[s].add(e);
            nLinks[e]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (nLinks[i] == 0) q.offer(i);
        }
        boolean[] visited = new boolean[n + 1];
        ArrayList<Integer> ans = new ArrayList<>();

        while (!q.isEmpty()) {
            int curr = q.poll();
            if (visited[curr]) continue;
            visited[curr] = true;
            ans.add(curr);
            for (Integer neighbor : edges[curr]) {
                nLinks[neighbor]--;
                if (nLinks[neighbor] == 0) {
                    q.offer(neighbor);
                }
            }
        }
        ans.stream().forEach(e -> System.out.println(e));
    }
}
