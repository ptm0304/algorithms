package swPrep.day0311;

import java.util.Scanner;

public class Sw_4530 {
    static long[] dp;
    public static void main(String[] args) {
        dp = new long[12];
        dp[0] = 1;
        for (int i = 1; i < 12; i++) {
            dp[i] = (long) Math.pow(10, i) + dp[i - 1] * 9;
        }
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();
        for (int tc = 1; tc <= nt; tc++) {
            long a = sc.nextLong();
            long b = sc.nextLong();
            long ans = b - a;
            if (a < 0 && b > 0) {
                ans = ans - 1 - getNumFours(-a) - getNumFours(b);
            }
            else {
                if (a < 0) {
                    long temp = -a;
                    a = -b;
                    b = temp;
                }
                ans = ans - (getNumFours(b - 1) - getNumFours(a));
            }
            System.out.println("#" + tc + " " + ans);
        }
    }

    static long getNumFours(long n) {
        String nStr = Long.toString(n);
        long ans = 0;
        int index = 0;
        for (int digit = nStr.length() - 1; digit > 0; digit--) {
            int num = nStr.charAt(index++) - '0';
            if (num == 0) continue;
            if (num > 4) {
                ans += Math.pow(10, digit);
                ans += (num - 1) * dp[digit - 1];
            }
            else if (num == 4) {
                ans += (num - 1) * dp[digit - 1];
                ans += Long.parseLong(nStr.substring(index + 1));
                return ans;
            }
            else {
                ans += num * dp[digit - 1];
            }
        }
        if (nStr.charAt(nStr.length() - 1) - '0' >= 4) ans++;
        return ans;
    }
}
