package past;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sw_shortestPath {
    static int N;
    static int[] home;
    static int[] company;
    static int[][] customers;
    static boolean[] visited;
    static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nt = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= nt; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            company = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())} ;
            home = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())} ;
            customers = new int[N][2];
            for (int i = 0; i < N; i++) {
                customers[i][0] = Integer.parseInt(st.nextToken());
                customers[i][1] = Integer.parseInt(st.nextToken());
            }
            visited = new boolean[N];
            min = Integer.MAX_VALUE;
            helper(0, 0, home);
            System.out.println("#" + tc + " " + min);
        }
    }

    static void helper(int ind, int dist, int[] lastLoc) {
        if (ind == N) {
            dist += calcDist(lastLoc, company);
            min = Math.min(min, dist);
        }
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;

            helper(ind + 1, dist + calcDist(lastLoc, customers[i]), customers[i]);

            visited[i] = false;
        }
    }

    static int calcDist(int[] v1, int[] v2) {
        return Math.abs(v1[0] - v2[0]) + Math.abs(v1[1] - v2[1]);
    }
}
