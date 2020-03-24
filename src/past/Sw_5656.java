package past;

import java.util.Arrays;
import java.util.Scanner;

public class Sw_5656 {
    static int n;
    static int w;
    static int h;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int min;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();
        for (int tc = 1; tc <= nt; tc++) {
            n = sc.nextInt();
            w = sc.nextInt();
            h = sc.nextInt();
            map = new int[h][w];
            min = Integer.MAX_VALUE;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    map[i][j] = sc.nextInt();
                }
            }
            for (int i = 0; i < w; i++) {
                perm(0, i);
            }

            System.out.println("#" + tc + " " + min);
        }
    }

    static void perm(int ind, int pos) {
        if (ind == n) {
            countBlocks();
            return;
        }
        // copy original map
        int[][] temp = new int[h][];
        for (int i = 0; i < h; i++) {
            temp[i] = map[i].clone();
        }
        // explode
        visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            if (map[i][pos] > 0) {
                explode(i, pos);
                break;
            }
        }
        // shift blocks
        shiftBlocks();

        for (int i = 0; i < w; i++) {
            perm(ind + 1, i);
        }
        map = temp;
    }

    static void countBlocks() {
        int cnt = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] != 0) {
                    cnt++;
                }
            }
        }
        min = Math.min(min, cnt);
    }

    static void shiftBlocks() {
        for (int c = 0; c < w; c++) {
            //////
            int shiftPos = -1;
            for (int r = h - 1; r >= 0; r--) {
                if (map[r][c] != 0) {
                    if (shiftPos != -1) {
                        map[shiftPos][c] = map[r][c];
                        map[r][c] = 0;
                        shiftPos--;
                    }
                }
                else if (shiftPos == -1) {
                    shiftPos = r;
                }
            }
            //////
        }
    }

    static void explode(int r, int c) {
        if (visited[r][c]) return;
        visited[r][c] = true;
        int cnt = map[r][c] - 1;
        for (int di = 0; di < 4; di++) {
            for (int i = 1; i <= cnt; i++) {
                int nextRow = r + dy[di] * i;
                int nextCol = c + dx[di] * i;
                if (nextRow >= 0 && nextCol >= 0 && nextRow < h && nextCol < w) {
                    explode(nextRow, nextCol);
                }
                else {
                    break;
                }
            }
        }
        map[r][c] = 0;
    }

    static void printMap() {
        for (int i = 0; i < h; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
}
