package mst;

import java.util.Arrays;
import java.util.Scanner;

// 백준 1997번 최소스패닝트리
public class B_1197 {
    static int parent[];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        Edge[] edges = new Edge[e];
        parent = new int[v + 1];

        // edge 배열 초기화
        for (int i = 0; i < e; i++) {
            edges[i] = new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }

        // 가중치 순으로 정렬
        Arrays.sort(edges);

        // union find를 위한 parent 배열 초기화
        for (int i = 0; i < v; i++) {
            parent[i] = i;
        }

        int ans = 0; // 만들어진 MST의 최종 가중치
        int cnt = 0; // 연결된 간선의 수 count
        for (Edge ed : edges) {
            int p1 = find(ed.v1);
            int p2 = find(ed.v2);
            if (p1 == p2) { // 같은 그룹이라면 사이클을 생성하기 때문에 연결시키지 않는다
                continue;
            }
            cnt++;
            ans += ed.price;
            // 연결시킨 간선의 parent 업데이트
            parent[p1] = Math.min(p1,p2);
            parent[p2] = Math.min(p1, p2);

            if (cnt == v - 1) break; // mst의 간선의 수는 정점의 갯수 - 1 과 같다
        }

        System.out.println(ans);

    }

    static int find(int v) {
        if (parent[v] == v) return v;
        return parent[v] = find(parent[v]);
    }

    private static class Edge implements Comparable<Edge> {
        int price, v1, v2;

        public Edge(int v1, int v2, int price) {
            this.price = price;
            this.v1 = v1;
            this.v2 = v2;
        }

        public int compareTo(Edge e) {
            return this.price - e.price;
        }
    }
}
