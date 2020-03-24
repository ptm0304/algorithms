package past;

import java.util.Scanner;

public class B_10844 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n + 1];
        if (n == 1) {
            System.out.println(9);
            System.exit(0);
        }
        if (n == 2) {
            System.out.println(17);
            System.exit(0);
        }
        dp[1] = 9;
        dp[2] = 17;
        int cntOnes = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] * 2 - cntOnes) % 1000000000;
            cntOnes *= 2;
        }

        System.out.println(dp[n]);

    }
}
