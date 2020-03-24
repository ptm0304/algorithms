package past;

import java.util.*;

public class Baek_1260 {
    static boolean[] visited;
    static ArrayList[] edges;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nVertex = sc.nextInt();
        int nEdge = sc.nextInt();
        int startInd = sc.nextInt();
        edges = new ArrayList[nVertex + 1];

        for (int i = 0; i < nEdge; i++) {
            int ind1 = sc.nextInt();
            int ind2 = sc.nextInt();
            if (edges[ind1] == null) {
                edges[ind1] = new ArrayList<Integer>();
            }
            if (edges[ind2] == null) {
                edges[ind2] = new ArrayList<Integer>();
            }
            edges[ind1].add(ind2);
            edges[ind2].add(ind1);
        }

        for (ArrayList<Integer> edge : edges) {
            if (edge == null) continue;
            Collections.sort(edge);
        }

        // dfs
        visited = new boolean[nVertex + 1];

        dfs(startInd);
        System.out.println();


        // bfs
        visited = new boolean[nVertex + 1];

        Queue<Integer> q = new LinkedList<>();
        q.offer(startInd);

        while (!q.isEmpty()) {
            int curr = q.poll();
            if (visited[curr]) continue;
            visited[curr] = true;
            System.out.print(curr + " ");
            ArrayList<Integer> neighbors = edges[curr];
            if (neighbors == null) continue;
            for (Integer neighbor : neighbors) {
                q.offer(neighbor);
            }
        }


    }

    static void dfs(int currInd) {
        if (visited[currInd]) return;
        visited[currInd] = true;
        System.out.print(currInd + " ");
        ArrayList<Integer> neighbors = edges[currInd];
        if (neighbors == null) return;
        for (Integer neighbor : neighbors) {
            dfs(neighbor);
        }
    }
}
