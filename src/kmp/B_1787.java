package kmp;

import java.util.Arrays;
import java.util.Scanner;

public class B_1787 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String line = sc.next();
        int[] table = new int[n];
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = -1;
        }

        int j = 0;
        long ans = 0;
        for (int i = 1; i < n; i++) {
            while (j > 0 && line.charAt(i) != line.charAt(j)) {
                j = table[j - 1];
            }
            if (line.charAt(j) == line.charAt(i)) {
                table[i] = ++j;
            }
        }

        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;
            j = table[i];
            while (j > 0) {
                if (dp[j - 1] != - 1) {
                    min = Math.min(min, dp[j - 1]);
                    break;
                }
                min = Math.min(min, j);
                j = table[j - 1];
            }
            if (min != Integer.MAX_VALUE) {
                ans += (i - min + 1);
                dp[i] = min;
            }
        }
        System.out.println(ans);
    }
}
