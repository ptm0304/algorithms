package past;

import java.util.Scanner;

public class B_11404 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nCities = sc.nextInt();
        int nBus = sc.nextInt();
        long[][] edges = new long[nCities + 1][nCities + 1];
        for (int i = 1; i <= nCities; i++) {
            for (int j = 1; j <= nCities; j++) {
                if (i == j) continue;
                edges[i][j] = Long.MAX_VALUE;
            }
        }
        for (int i = 0; i < nBus; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();
            edges[from][to] = Math.min(edges[from][to], cost);
        }

        for (int trans = 1; trans <= nCities; trans++) {
            for (int start = 1; start <= nCities; start++) {
                for (int dest = 1; dest <= nCities; dest++) {
                    if (edges[start][trans] == Long.MAX_VALUE || edges[trans][dest] == Long.MAX_VALUE) continue;
                    edges[start][dest] = Math.min(edges[start][dest], edges[start][trans] + edges[trans][dest]);
                }
            }
        }

        for (int i = 1; i <= nCities; i++) {
            for (int j = 1; j <= nCities; j++) {
                if (edges[i][j] == Long.MAX_VALUE) {
                    System.out.print(0 + " ");
                    continue;
                }
                System.out.print(edges[i][j] + " ");
            }
            System.out.println();
        }
    }
}
