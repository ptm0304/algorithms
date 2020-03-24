package past;

import java.util.Scanner;

public class Sw_quiz {
    final static long LIMIT = 1000000007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();
        for (int tc = 1; tc <= nt; tc++) {
            long n = sc.nextInt();
            long ans = 0;
            for (long i = 1; i <= n; i++) {
                ans += calcSigma(i, i);
                ans %= LIMIT;
            }
            System.out.println(ans);
        }
    }

    static long calcSigma(long n, long p) {
        if (p % 2 == 0) {
            if (p == 2) {
                return (n * n) % LIMIT;
            }
            long temp = calcSigma(n, p / 2);
            return (temp * temp) % LIMIT;
        }
        else {
            if (p == 1) {
                return n;
            }
            long temp = calcSigma(n, p / 2);
            return  (((temp * temp) % LIMIT) * n) % LIMIT;
        }
    }
}
