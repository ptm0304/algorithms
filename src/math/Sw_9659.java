package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Sw_9659 {
    static int[][] data;
    static int x;
    final static int MOD = 998244353;
    static long[] dp;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();
        for (int tc = 1; tc <= nt; tc++) {
            int n = sc.nextInt();
            data = new int[n + 1][3];
            dp = new long[n + 1];
            for (int i = 2; i <= n; i++) {
                data[i][0] = sc.nextInt();
                data[i][1] = sc.nextInt();
                data[i][2] = sc.nextInt();
            }
            int m = sc.nextInt();
            ArrayList<Long> ans = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                x = sc.nextInt();
                Arrays.fill(dp, -1);
                ans.add(calculate(n));
            }
            System.out.print("#" + tc + " ");
            ans.stream().forEach(e -> System.out.print(e + " "));
            System.out.println();
        }
    }

    static long calculate(int ind) {
        if (dp[ind] != -1) {
            return dp[ind];
        }
        long ans = 0;
        if (ind == 0) {
            ans = functionZero();
        }
        else if (ind == 1) {
            ans =  functionOne(x);
        }
        else {
            switch (data[ind][0]) {
                case 1 :
                    ans =  (calculate(data[ind][1]) + calculate(data[ind][2])) % MOD;
                    break;
                case 2 :
                    ans =  (data[ind][1] * calculate(data[ind][2])) % MOD;
                    break;
                case 3 :
                    ans =  (calculate(data[ind][1]) * calculate(data[ind][2])) % MOD;
                    break;
            }    
        }
        dp[ind] = ans;
        return ans;
    }

    static int functionZero() {
        return 1;
    }

    static int functionOne(int x) {
        return x;
    }
}
