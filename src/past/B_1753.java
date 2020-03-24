//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.PriorityQueue;
//import java.util.StringTokenizer;
//
//public class B_1753 {
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int nv = Integer.parseInt(st.nextToken());
//        int ne = Integer.parseInt(st.nextToken());
//        int start = Integer.parseInt(br.readLine());
//        int[] costs = new int[nv + 1];
//        ArrayList<Edge>[] edges = new ArrayList[nv + 1];
//        for (int i = 0; i < ne; i++) {
//            st = new StringTokenizer(br.readLine());
//            int from = Integer.parseInt(st.nextToken());
//            int to = Integer.parseInt(st.nextToken());
//            int cost = Integer.parseInt(st.nextToken());
//            if (edges[from] == null) edges[from] = new ArrayList<>();
//            edges[from].add(new Edge(to, cost));
//        }
//
//        Arrays.fill(costs, Integer.MAX_VALUE);
//
//        PriorityQueue<Vertex> q = new PriorityQueue<>();
//        q.offer(new Vertex(start, 0));
//        costs[start] = 0;
//
//        int cnt = 0;
//        boolean[] visited = new boolean[nv + 1];
//        while (!q.isEmpty()) {
//            Vertex curr = q.poll();
//            if (visited[curr.n]) continue;
//            visited[curr.n] = true;
//            cnt++;
//            if (cnt == nv) break;
//            if (edges[curr.n] == null) continue;
//            for (Edge edge : edges[curr.n]) {
//                if (costs[edge.to] > curr.cost + edge.cost) {
//                    q.offer(new Vertex(edge.to, curr.cost + edge.cost));
//                    costs[edge.to] = curr.cost + edge.cost;
//                }
//            }
//        }
//        StringBuilder sb = new StringBuilder();
//        for (int i = 1; i <= nv; i++) {
//            sb.append((costs[i] == Integer.MAX_VALUE? "INF" : costs[i]) + "\n");
//        }
//        System.out.println(sb.toString());
//    }
//}
////
////class Vertex implements Comparable<Vertex> {
////    int n;
////    Integer cost;
////
////    public Vertex(int n, int cost) {
////        this.n = n;
////        this.cost = cost;
////    }
////
////
////    @Override
////    public int compareTo(Vertex o) {
////        return this.cost.compareTo(o.cost);
////    }
////}
//
//class Edge {
//    int to, cost;
//
//    public Edge(int to, int cost) {
//        this.to = to;
//        this.cost = cost;
//    }
//}
