package past;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

// Bellman Ford's algorithm
public class B_1738 {
    static int n;
    static int m;
    static int[][] mEdges;
    static Vertex[] mVertices;
    static Vertex[] mVertices2;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        mEdges = new int[n + 1][n + 1];
        mVertices = new Vertex[n + 1];
        mVertices2 = new Vertex[n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(mEdges[i], Integer.MIN_VALUE);
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            mEdges[from][to] = Math.max(mEdges[from][to], cost);
        }

        Vertex start = new Vertex(0, 0);
        mVertices[1] = start;
        bellmanFords();

        Vertex start2 = new Vertex(0, 0);
        mVertices2[1] = start2;
        bellmanFords2();

        if (mVertices[n] == null) {
            System.out.println(-1);
        }
        else {
            visited = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                if (mVertices[i] == null) continue;
                if (mVertices2[i].cost != mVertices[i].cost) {
                    if (dfs(i)) {
                        System.out.println(-1);
                        System.exit(0);
                    }
                }
            }
            ArrayList<Integer> ans = new ArrayList<>();
            int curr = n;
            while (curr != 0) {
                ans.add(curr);
                curr = mVertices[curr].from;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = ans.size() - 1; i >= 0; i--) {
                sb.append(ans.get(i) + " ");
            }
            System.out.println(sb.toString());
        }
    }

    static boolean dfs(int v) {
        if (v == n) {
            return true;
        }
        if (visited[v]) return false;
        visited[v] = true;
        for (int i = 1; i <= n; i++) {
            if (mEdges[v][i] > Integer.MIN_VALUE) {
                if (dfs(i)) return true;
            }
        }
        return false;
    }

    static void bellmanFords() {
        HashSet<Integer> changedVertices = new HashSet<>();
        changedVertices.add(1);
        for (int i = 0; i < n - 1; i++) {
            HashSet<Integer> temp = new HashSet<>();
            for (Integer from : changedVertices) {
                for (int to = 1; to <= n; to++) {
                    if (mEdges[from][to] != Integer.MIN_VALUE) {
                        int newCost = mVertices[from].cost + mEdges[from][to];
                        if (mVertices[to] == null || mVertices[to].cost < newCost) {
                            mVertices[to] = new Vertex(to, newCost);
                            mVertices[to].from = from;
                            temp.add(to);
                        }
                    }
                }
            }
            changedVertices = temp;
        }
    }

    static void bellmanFords2() {
        HashSet<Integer> changedVertices = new HashSet<>();
        changedVertices.add(1);
        for (int i = 0; i < 2 * n; i++) {
            HashSet<Integer> temp = new HashSet<>();
            for (Integer from : changedVertices) {
                for (int to = 1; to <= n; to++) {
                    if (mEdges[from][to] != Integer.MIN_VALUE) {
                        int newCost = mVertices2[from].cost + mEdges[from][to];
                        if (mVertices2[to] == null || mVertices2[to].cost < newCost) {
                            mVertices2[to] = new Vertex(to, newCost);
                            temp.add(to);
                        }
                    }
                }
            }
            changedVertices = temp;
        }
    }
}

class Vertex {
    int cost;
    int from;

    public Vertex(int from, int cost) {
        this.from = from;
        this.cost = cost;
    }
}