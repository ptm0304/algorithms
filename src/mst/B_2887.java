package mst;

import java.util.*;

public class B_2887 {
    static int[] parent;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Node[] nodesByX = new Node[n];
        Node[] nodesByY = new Node[n];
        Node[] nodesByZ = new Node[n];
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            parent[i] = i;
            Node node = new Node(x, y, z, i);
            nodesByX[i] = node;
            nodesByY[i] = node;
            nodesByZ[i] = node;
        }

        Arrays.sort(nodesByX, new Comparator<Node>() {
            @Override
            public int compare(Node node, Node t1) {
                return node.x - t1.x;
            }
        });

        Arrays.sort(nodesByY, new Comparator<Node>() {
            @Override
            public int compare(Node node, Node t1) {
                return node.y - t1.y;
            }
        });

        Arrays.sort(nodesByZ, new Comparator<Node>() {
            @Override
            public int compare(Node node, Node t1) {
                return node.z - t1.z;
            }
        });

        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            edges.add(new Edge(nodesByX[i].num, nodesByX[i + 1].num, Math.abs(nodesByX[i].x - nodesByX[i + 1].x)));
            edges.add(new Edge(nodesByY[i].num, nodesByY[i + 1].num, Math.abs(nodesByY[i].y - nodesByY[i + 1].y)));
            edges.add(new Edge(nodesByZ[i].num, nodesByZ[i + 1].num, Math.abs(nodesByZ[i].z - nodesByZ[i + 1].z)));
        }

        edges.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge edge, Edge t1) {
                return edge.price - t1.price;
            }
        });


        int ans = 0;
        int cnt = 0;

        for (int i = 0; i < edges.size(); i++) {
            Edge e = edges.get(i);
            int p1 = find(e.from);
            int p2 = find(e.to);
            if (p1 == p2) continue;
            cnt++;
            ans += e.price;
            parent[p1] = Math.min(p1, p2);
            parent[p2] = Math.min(p1, p2);
            if (cnt == n - 1) break;
        }

        System.out.println(ans);
    }

    static int find(int v) {
        if (parent[v] == v) return v;
        return parent[v] = find(parent[v]);
    }

    private static class Edge {
        int from, to, price;

        public Edge(int from, int to, int price) {
            this.from = from;
            this.to = to;
            this.price = price;
        }
    }

    private static class Node {
        int num, x, y, z;

        public Node(int x, int y, int z, int num) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.num = num;
        }
    }
}
