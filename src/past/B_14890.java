package past;

import java.util.Scanner;

public class B_14890 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int cnt = 0;
        // row calc
        outer:
        for (int r = 0; r < n; r++) {
            int constLen = 1;
            for (int c = 1; c < n; c++) {
                if (map[r][c - 1] == map[r][c]) {
                    constLen++;
                }
                else if (map[r][c - 1] + 1 ==  map[r][c]) {
                    if (constLen < l) continue outer;
                    constLen = 1;
                }
                else if (map[r][c - 1] - 1 ==  map[r][c]){
                    if (constLen < 0) continue outer;
                    constLen = -(l - 1);
                }
                else {
                    continue outer;
                }
            }
            if (constLen < 0) continue outer;
            cnt++;
        }

        // col calc
        outer:
        for (int c = 0; c < n; c++) {
            int constLen = 1;
            for (int r = 1; r < n; r++) {
                if (map[r - 1][c] == map[r][c]) {
                    constLen++;
                }
                else if (map[r - 1][c] + 1 == map[r][c]) {
                    if (constLen < l) continue outer;
                    constLen = 1;
                }
                else if (map[r - 1][c] - 1 == map[r][c]){
                    if (constLen < 0) continue outer;
                    constLen = -(l - 1);
                }
                else {
                    continue outer;
                }
            }
            if (constLen < 0) continue outer;
            cnt++;
        }
        System.out.println(cnt);
    }
}
