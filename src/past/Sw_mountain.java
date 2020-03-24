package past;

import java.util.Scanner;

public class Sw_mountain {
    final static int EQUAL = 0;
    final static int INCREASING = 1;
    final static int DECREASING = 2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();
        for (int tc = 1; tc <= nt; tc++) {
            int n = sc.nextInt();
            int[] mountains = new int[n];
            for (int i = 0; i < n; i++) {
                mountains[i] = sc.nextInt();
            }
            int cntInc = 0;
            int cntDec = 0;
            int ans = 0;
            int status = EQUAL;
            for (int i = 1; i < n; i++) {
                if (mountains[i - 1] < mountains[i]) { // increasing
                    if (status == DECREASING) {
                        ans += cntInc * cntDec;
                        cntDec = 0;
                        cntInc = 0;
                    }
                    status = INCREASING;
                    cntInc++;
                }
                else if (mountains[i - 1] == mountains[i]) {
                    if (status == DECREASING) {
                        ans += cntInc * cntDec;
                    }
                    status = EQUAL;
                    cntInc = 0;
                    cntDec = 0;
                }
                else {
                    if (status != EQUAL) {
                        cntDec++;
                    }
                    status = DECREASING;
                }
            }
            if (status == DECREASING) {
                ans += cntDec * cntInc;
            }
            System.out.println("#" + tc + " " + ans);
        }
    }
}
