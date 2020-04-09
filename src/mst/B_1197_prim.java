package mst;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

// 백준 1997번 최소스패닝트리 프림 풀이
public class B_1197_prim {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();

        // edge 배열 초기화
        ArrayList<Edge>[] edges = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < e; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int price = sc.nextInt();
            edges[v1].add(new Edge(v2, price));
            edges[v2].add(new Edge(v1, price));
        }

        // PriorityQueue 사용 prim 알고리즘
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[v + 1]; // 방문한 정점인지 확인
        int cnt = 0; // 연결한 간선수 카운트

        // 1번 노드부터 시작
        for (Edge edge : edges[1]) {
            pq.offer(edge);
        }
        visited[1] = true;

        int ans = 0;
        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            if (visited[curr.to]) continue;
            visited[curr.to] = true;
            ans += curr.price;
            cnt++;

            if (cnt == v - 1) break;

            // 현재 연결한 정점의 방문하지 않은 이웃 정점들에 대한 간선 정보를 모두 pq에 삽입
            for (Edge edge : edges[curr.to]) {
                if (visited[edge.to]) continue;
                pq.offer(edge);
            }
        }

        System.out.println(ans);
    }

    private static class Edge implements Comparable<Edge>{
        int to, price;

        public Edge(int to, int price) {
            this.to = to;
            this.price = price;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.price - edge.price;
        }
    }
}
