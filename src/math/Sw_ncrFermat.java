package math;

import java.util.Scanner;

public class Sw_ncrFermat {
    private static final int p = 1234567891;

    public static void main(String[] args) {
        long[] facts = new long[1000001];
        facts[1] = 1;
        for (int i = 2; i <= 1000000; i++) {
            facts[i] = (i * facts[i - 1]) % p;
        }
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();
        for (int tc = 1 ; tc <= nt; tc++) {
            int n = sc.nextInt();
            int r = sc.nextInt();

            int ans = (int) (((facts[n] * power(facts[r], p - 2, p)) % p *
                                power(facts[n - r], p - 2, p)) % p);
            System.out.println("#" + tc + " " + ans);
        }
    }

    static long power(long x, int power, int p) {
        long res = 1;

        x = x % p;

        while (power > 0) {
            if (power % 2 == 1) {
                res = (res * x) % p;
            }

            power = power / 2;
            x = (x * x) % p;
        }

        return res;
    }
}
