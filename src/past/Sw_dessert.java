package past;

import java.util.Scanner;

public class Sw_dessert {
    static int n;
    static int[][] map;
    static int[] dx = {1, -1, -1, 1};
    static int[] dy = {1, 1, -1, -1};
    static int max;
    static boolean[] familiarTaste;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();
        for (int tc = 1; tc <= nt; tc++) {
            n = sc.nextInt();
            map = new int[n][n];
            max = -1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                }
            }
            familiarTaste = new boolean[101];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    familiarTaste[map[i][j]] = true;
                    findPath(new int[]{i,j}, new int[]{i + dy[0], j + dx[0]}, 0, 1);
                    familiarTaste[map[i][j]] = false;
                }
            }
            System.out.println("#" + tc + " " + max);
        }
    }

    static void findPath(int[] start, int[] curr, int dir, int cnt) {
        if (curr[0] == start[0] && curr[1] == start[1]) {
            max = Math.max(max, cnt);
            return;
        }
        if (curr[0] < 0 || curr[1] < 0 || curr[0] >= n || curr[1] >= n) return;
        if (familiarTaste[map[curr[0]][curr[1]]]) return;
        familiarTaste[map[curr[0]][curr[1]]] = true;
        findPath(start, new int[] {curr[0] + dy[dir], curr[1] + dx[dir]}, dir, cnt + 1);
        if (dir < 3) {
            findPath(start, new int[] {curr[0] + dy[dir + 1], curr[1] + dx[dir + 1]}, dir + 1, cnt + 1);
        }
        familiarTaste[map[curr[0]][curr[1]]] = false;
    }
}
