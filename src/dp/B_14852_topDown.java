package dp;

import java.util.Scanner;

public class B_14852_topDown {
    static long[] memo1; // 평평한 메모
    static long[] memo2; // 튀어나온 메모
    static int n;
    static int mod = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        memo1 = new long[n + 1];
        memo2 = new long[n + 1];

        System.out.println(calculate1(1));

    }

    static long calculate1(int ind) { // 평평한 경우
        if (ind == n + 1) return 1;
        if (ind == n + 2) return 0;
        if (memo1[ind] != 0) {
            return memo1[ind];
        }
        // 세로로 놓는 경우 + 가로로 두개 놓는 경우 + 가로로 1개 놓고 1칸짜리 한개 놓는 경우 + 1칸짜리 한개만 놓는경우
        memo1[ind] = (((calculate1(ind + 1) + calculate1(ind + 2)) % mod +
                calculate2(ind + 1)) % mod + calculate2(ind)) % mod;
        return memo1[ind];
    }

    static long calculate2(int ind) { // 튀어나온 경우
        if (ind == n + 1 || ind == n + 2) return 0;
        if (memo2[ind] != 0) {
            return memo2[ind];
        }
        // 한개 채우는경우 + 가로로 채우는 경우
        memo2[ind] =  (calculate1(ind + 1) + calculate2(ind + 1)) % mod;
        return memo2[ind];
    }

}
