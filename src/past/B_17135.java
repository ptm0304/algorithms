package past;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_17135 {

    static int[][] attacked;
    static boolean[][] map;
    static int N;
    static int M;
    static int D;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken()); // row
        M = Integer.parseInt(st.nextToken()); // col
        D = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < M; j++) {
                map[i][j] = st.nextToken().equals("1") ? true : false;
                // true if enemy
            }
        }

        combination(0, 0, new int[3]);

        System.out.println(max);

    }

    static void combination(int steps, int index, int[] archers) {
        if (steps == 3) {
            int archerRow = N; // 성위치
            attacked = new int[N][M];
            int numDeadEnemies = 0;
            int turn = 1;

            while (archerRow > 0) {
                for (Integer archerCol : archers) {
                    if (canAttack(archerRow, archerCol, turn)) numDeadEnemies++;
                }
                turn++;
//				System.out.println(archerRow + ", " + numDeadEnemies);
                archerRow--;
            }
            max = Math.max(max, numDeadEnemies);
            return;
        }
        if (index == M) {
            return;
        }
        for (int i = index; i < M; i++) {
            archers[steps] = i;
            combination(steps + 1, i + 1, archers);
        }
    }

    static boolean canAttack(int archerRow, int archerCol, int turn) {
//		System.out.println("turn: " + turn + ", Archer position: " + archerRow + ", " + archerCol);
        for (int i = 1; i <= D; i++) {
            int checkRow = archerRow - 1;
            int checkCol = archerCol - i + 1; // 가장 왼쪽

            // 왼쪽에서 가운데까지
            while (checkCol <= archerCol) {
                if (checkRow < 0 || checkCol < 0 || checkCol >= M) {
                    checkRow--;
                    checkCol++;
                    continue;
                }
                if (map[checkRow][checkCol]) {

                    if (attacked[checkRow][checkCol] == 0) {
//						System.out.println("check: " + checkRow + ", " + checkCol);
                        attacked[checkRow][checkCol] = turn;
                        return true;
                    }
                    else if (attacked[checkRow][checkCol] == turn){
                        return false;
                    }
                }
                checkRow--;
                checkCol++;
            }
            checkRow++;
            checkCol--;

            // 가운데에서 오른쪽까지
            while (checkRow < archerRow) {
                if (checkRow < 0 || checkCol < 0 || checkCol >= M) {
                    checkRow++;
                    checkCol++;
                    continue;
                }
                if (map[checkRow][checkCol]) {
                    if (attacked[checkRow][checkCol] == 0) {
//						System.out.println("check: " + checkRow + ", " + checkCol);
                        attacked[checkRow][checkCol] = turn;
                        return true;
                    }
                    else if (attacked[checkRow][checkCol] == turn){
                        return false;
                    }
                }
                checkRow++;
                checkCol++;
            }
        }
        return false;
    }
}