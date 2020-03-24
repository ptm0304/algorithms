package disjointSet;

import java.util.Scanner;

public class B_1976 {
    static int[] parent;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = sc.nextInt();
                if (j > i && x == 1) {
                    union(i, j);
                }
            }
        }
        int prev = -1;
        for (int i = 0; i < m; i++) {
            int v = sc.nextInt() - 1;
            int p = find(v);
            if (prev != -1 && p != prev) {
                System.out.println("NO");
                System.exit(0);
            }
            prev = p;
        }
        System.out.println("YES");
    }

    static int find(int v) {
        if (parent[v] == v) {
            return v;
        }
        return find(parent[v]);
    }

    static void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        parent[p1] = Math.min(p1, p2);
        parent[p2] = Math.min(p1, p2);
    }
}
