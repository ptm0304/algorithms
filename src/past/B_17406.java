package past;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_17406 {
    static int[][] rotations;
    static int N;
    static int M;
    static int minArr = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken()); // row length
        M = Integer.parseInt(st.nextToken()); // col length
        int K = Integer.parseInt(st.nextToken()); // number of possible rotations

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rotations = new int[K][3];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine().trim());
            rotations[i][0] = Integer.parseInt(st.nextToken()) - 1; // index starts from 0
            rotations[i][1] = Integer.parseInt(st.nextToken()) - 1;
            rotations[i][2] = Integer.parseInt(st.nextToken());
        }

        int[] perms = new int[K];
        for (int i = 0; i < K; i++) {
            perms[i] = i;
        }

        permutation(map, 0, perms, new int[K]);

        System.out.println(minArr);
    }

    static void rotateMapInOrder(int[][] map, int[] orders) {
        for (int i = 0; i < orders.length; i++) {
            rotateMap(map, rotations[orders[i]]);
        }
        calcMap(map);
    }

    static void rotateMap(int[][] map, int[] rotation) {
        int r = rotation[0];
        int c = rotation[1];
        int s = rotation[2];

        for (int layer = s; layer > 0; layer--) {
            int temp = map[r - layer][c - layer]; // save first number
            // left line shift
            for (int i = r - layer; i < r + layer; i++) {
                map[i][c - layer] = map[i + 1][c - layer];
            }
            // bottom line shift
            for (int i = c - layer; i < c + layer; i++) {
                map[r + layer][i] = map[r + layer][i + 1];
            }
            // right line shift
            for (int i = r + layer; i > r - layer; i--) {
                map[i][c + layer] = map[i - 1][c + layer];
            }
            // top line shift
            for (int i = c + layer; i > c - layer + 1; i--) {
                map[r - layer][i] = map[r - layer][i - 1];
            }
            // first number copy
            map[r - layer][c - layer + 1] = temp;
        }

//        printMap(map);
    }

    static void calcMap(int[][] map) {
        for (int row = 0; row < N; row++) {
            int sum = 0;
            for (int col = 0; col < M; col++) {
                sum += map[row][col];
            }
            minArr = Math.min(minArr, sum);
        }
    }

    static void printMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }

    static void permutation(int[][] map, int ind, int[] left, int[] result) {
        if (ind == result.length) {
            int[][] tempMap = new int[N][M];
            for (int i = 0; i < N; i++) { // copy map
                tempMap[i] = map[i].clone();
            }

            // rotate
            rotateMapInOrder(tempMap, result);
        }
        for (int i = 0; i < left.length; i++) {
            if (left[i] == -1) continue;
            result[ind] = left[i];
            left[i] = -1;
            permutation(map, ind + 1, left, result);
            left[i] = result[ind];
        }
    }
}
