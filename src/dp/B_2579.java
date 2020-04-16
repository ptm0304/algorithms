package dp;

import java.util.Arrays;
import java.util.Scanner;

public class B_2579 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] stairs = new int[n]; // score of each stair
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            stairs[i] = sc.nextInt();
        }

        if (n <= 2) {
            int ans = 0;
            for (int i = 0; i < n; i++) {
                ans += stairs[i];
            }
            System.out.println(ans);
            System.exit(0);
        }
        dp[0] = stairs[0];
        dp[1] = stairs[1];
        dp[2] = stairs[0] + stairs[2];

        for (int i = 3; i < n; i++) {
            dp[i] = dp[i - 2] + stairs[i];
            dp[i] = Math.max(dp[i], dp[i - 3] + stairs[i - 2] + stairs[i]);
        }

        System.out.println(Math.max(dp[n - 1], dp[n - 2] + stairs[n - 1]));
    }
}
