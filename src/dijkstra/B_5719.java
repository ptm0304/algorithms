package dijkstra;

import java.util.*;

public class B_5719 {
    final static int INF = 999999;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            if (m == 0 && n == 0) break;
            int s = sc.nextInt();
            int d = sc.nextInt();
            ArrayList<Edge>[] edges = new ArrayList[n];
            ArrayList<Edge>[] reverseEdges = new ArrayList[n];
            int[] costs = new int[n];

            for (int i = 0; i < n; i++) {
                edges[i] = new ArrayList<>();
                reverseEdges[i] = new ArrayList<>();
                costs[i] = INF;
            }

            for (int i = 0; i < m; i++) {
                int start = sc.nextInt();
                int dest = sc.nextInt();
                int price = sc.nextInt();
                edges[start].add(new Edge(dest, price));
                reverseEdges[dest].add(new Edge(start, price));
            }

            dijkstra(edges, costs, s, d);

            removeShortestPaths(reverseEdges, edges, costs, d);

            Arrays.fill(costs, INF);
            dijkstra(edges, costs, s, d);

            System.out.println(costs[d] == INF ? -1 : costs[d]);
        }
    }

    static void dijkstra(ArrayList<Edge>[] edges, int[] costs, int s, int d) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.offer(new Vertex(s, 0));
        while (!pq.isEmpty()) {
            Vertex v = pq.poll();
            if (costs[v.id] > v.price) {
                costs[v.id] = v.price;
            }
            else continue;
            if (v.id == d) return;
            for (Edge e : edges[v.id]) {
                if (costs[e.d] > v.price + e.cost) {
                    pq.offer(new Vertex(e.d, v.price + e.cost));
                }
            }
        }
    }

    static void removeShortestPaths(ArrayList<Edge>[] reverseEdges, ArrayList<Edge>[] edges, int[] costs, int d) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(d);
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (Edge e : reverseEdges[curr]) {
                if (costs[e.d] == costs[curr] - e.cost) {
                    for (Edge ee : edges[e.d]) {
                        if (ee.d == curr) {
                            ee.cost = INF;
                            break;
                        }
                    }
                    q.offer(e.d);
                }
            }
        }
    }
}
class Vertex implements Comparable<Vertex> {
    int id;
    int price;

    public Vertex(int id, int price) {
        this.id = id;
        this.price = price;
    }

    @Override
    public int compareTo(Vertex v) {
        return this.price - v.price;
    }
}

class Edge {
    int d, cost;

    public Edge(int d, int cost) {
        this.d = d;
        this.cost = cost;
    }
}