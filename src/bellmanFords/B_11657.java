package bellmanFords;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class B_11657 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] costs1 = new int[n + 1];
        int[] costs2 = new int[n + 1];

        ArrayList<Edge>[] edges = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            edges[i] = new ArrayList<>();
            costs1[i] = Integer.MAX_VALUE;
            costs2[i] = Integer.MAX_VALUE;
        }
        costs1[1] = 0;
        costs2[1] = 0;
        for (int i = 0; i < m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int cost = sc.nextInt();
            edges[start].add(new Edge(cost, end));
        }

        HashSet<Integer> changedVertices = new HashSet<>();
        changedVertices.add(1);
        for (int i = 0; i < n - 1; i++) {
            HashSet<Integer> temp = new HashSet<>();
            for (Integer v : changedVertices) {
                for (Edge e : edges[v]) {
                    int newCost = costs1[v] + e.cost;
                    if (costs1[e.dest] > newCost) {
                        costs1[e.dest] = newCost;
                        temp.add(e.dest);
                    }
                }
            }
            changedVertices = temp;
        }

        changedVertices = new HashSet<>();
        changedVertices.add(1);
        for (int i = 0; i < 2 * n - 1; i++) {
            HashSet<Integer> temp = new HashSet<>();
            for (Integer v : changedVertices) {
                for (Edge e : edges[v]) {
                    int newCost = costs2[v] + e.cost;
                    if (costs2[e.dest] > newCost) {
                        costs2[e.dest] = newCost;
                        temp.add(e.dest);
                    }
                }
            }
            changedVertices = temp;
        }

        for (int i = 1; i <= n; i++) {
            if (costs1[i] != costs2[i]) {
                System.out.println(-1);
                System.exit(0);
            }
        }

        for (int i = 2; i <= n; i++) {
            System.out.println(costs1[i] == Integer.MAX_VALUE ? -1 : costs1[i]);
        }
    }
}

class Edge {
    int cost;
    int dest;

    public Edge(int cost, int dest) {
        this.cost = cost;
        this.dest = dest;
    }
}
