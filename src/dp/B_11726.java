package dp;

import java.util.Scanner;

public class B_11726 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] f = new int[1001];
        f[1] = 1;
        f[2] = 2;

        for (int i = 3; i <= n; i++) {
            f[i] = (f[i-1] + f[i-2]) % 10007;
        }

        System.out.println(f[n]);
    }
}
