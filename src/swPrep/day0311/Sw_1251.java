package swPrep.day0311;

import java.util.*;

public class Sw_1251 {
    static int[] parents;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();
        for (int tc = 1; tc <= nt; tc++) {
            int n = sc.nextInt();
            long[][] islands = new long[n][2];

            for (int i = 0; i < n; i++) {
                islands[i][0] = sc.nextLong();
            }

            for (int i = 0; i < n; i++) {
                islands[i][1] = sc.nextLong();
            }

            double e = sc.nextDouble();

            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
            PriorityQueue<double[]> edges = new PriorityQueue<>(new Comparator<double[]>() {
                @Override
                public int compare(double[] o1, double[] o2) {
                    return  Double.compare(o1[2], o2[2]);
                }
            });

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) continue;
                    long l = (islands[i][0] - islands[j][0]) * (islands[i][0] - islands[j][0]) +
                            (islands[i][1] - islands[j][1]) * (islands[i][1] - islands[j][1]);
                    double cost = e * l;
                    edges.add(new double[] {i, j, cost});
                }
            }

            int cnt = 0;
            double sum = 0;
            while (cnt < n - 1) {
                double[] edge = edges.poll();
                int p1 = find((int) edge[0]);
                int p2 = find((int) edge[1]);
                if (p1 != p2) {
                    sum += edge[2];
                    cnt++;
                    parents[p1] = Math.min(p1, p2);
                    parents[p2] = Math.min(p1, p2);
                }
            }
            long ans = Math.round(sum);
            System.out.println("#" + tc + " " + ans);
         }
    }

    static int find(int i) {
        while (parents[i] != i) {
            i = parents[i];
        }
        return i;
    }
}
