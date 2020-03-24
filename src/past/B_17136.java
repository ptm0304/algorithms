package past;

import java.util.Arrays;
import java.util.Scanner;

public class B_17136 {

    static int[] numPapersLeft = {0, 5, 5, 5, 5, 5}; // 남은 색종이 갯수
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean[][] map = new boolean[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = (sc.nextInt() == 1) ? true : false;
            }
        }

        helper(map, 0, 0);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void printMap(boolean[][] map) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    static void helper(boolean[][] map, int startRow, int startCol) {
//        System.out.println("startRow: " + startRow + ", startCol: " + startCol + ", papers: " +
//                Arrays.toString(numPapersLeft));
//        printMap(map);

        for (int i = startRow; i < 10; i++) {
            for (int j = (i == startRow) ? startCol : 0; j < 10; j++) {
                if (map[i][j]) {
//                    System.out.println("paper row: " + i + ", paper col: " + j);
                    for (int size = 1; size <= 5; size++) {
                        if (canCoverPaper(map, size, i, j)) {
                            reverseArea(map, size, i, j);
                            numPapersLeft[size]--;
                            helper(map, i, j);
                            reverseArea(map, size, i, j);
                            numPapersLeft[size]++;
                        }
                    }
                    return;
                }
            }
        }
        int papersLeft = 0;
        for (int cnt : numPapersLeft) {
            papersLeft += cnt;
        }
        ans = Math.min(ans, (25 - papersLeft));
    }

    static boolean canCoverPaper(boolean[][] map, int size, int row, int col) {
        if (numPapersLeft[size] <= 0) return false;
        if (row + size > 10 || col + size > 10) return false;
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (!map[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    static void reverseArea(boolean[][] map, int size, int row, int col) {
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                map[i][j] = !map[i][j];
            }
        }
    }
}
