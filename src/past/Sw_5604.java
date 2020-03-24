package past;

import java.util.Scanner;

public class Sw_5604 {
    static long[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        dp = new long[17];
        dp[0] = 45;
        for (int i = 1; i < 17; i++) {
            dp[i] = dp[i - 1] * 10 + getSummation(9) * (long) Math.pow(10, i);
        }

        int nt = sc.nextInt();
        for (int tc = 1; tc <= nt; tc++) {
            long a = sc.nextLong();
            long b = sc.nextLong();

            long ans1 = getFromZero(a - 1);
            long ans2 = getFromZero(b);

            if (a == 0) {
                System.out.println("#" + tc + " " + (ans2));
            }
            else {
                System.out.println("#" + tc + " " + (ans2 - ans1));
            }

        }
    }

    static long getFromZero(long n) {
        String aStr = Long.toString(n);

        long ans = 0;
        long adder = 0;

        int digit = aStr.length() - 1;
        for (int i = 0; i < aStr.length() - 1; i++) {
            long num = aStr.charAt(i) - '0';
            ans += dp[digit - 1] * num + getSummation(num - 1) * (long) Math.pow(10, digit)
                    + adder * (long) (num * Math.pow(10, digit));
            adder += num;
            digit--;
        }
        for (int i = 0; i <= aStr.charAt(aStr.length() - 1) - '0'; i++) {
            ans += adder + i;
        }

        return ans;
    }

    static long getSummation(long i) {
        int sum = 0;
        for (int x = 1; x <= i; x++) {
            sum += x;
        }
        return sum;
    }
}
