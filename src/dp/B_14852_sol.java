package dp;

import java.util.Scanner;

public class B_14852_sol {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        long[] dp = new long[N+1];
        long[] sum = new long[N+1];

        final long MOD = 1000000007;

        dp[0] = sum[0] = 1;
        for(int i=1; i<=N; i++) {
            dp[i] += (2*dp[i-1])%MOD; // 1센티 전상태*2가지
            dp[i]%=MOD; // 모듈러때매 틀리기 싫으니까 겁나 자주하자
            dp[i] += (i-2)>=0 ? (3*dp[i-2])%MOD : 0; // 2센티 전 상태*3가지
            dp[i]%=MOD;
            dp[i] += (i-3)>=0 ? (2*sum[i-3])%MOD : 0; // 3,4,5... 센티들 *2가지
            dp[i]%=MOD;
            sum[i] = (sum[i-1]%MOD) + (dp[i]%MOD); // 방금dp[i]계산 끝났으니 sum[i]도 sum[i-1]+dp[i]해서 계산해두자
        }
        System.out.println(dp[N]);
//		System.out.println(Arrays.toString(dp));
//		System.out.println(Arrays.toString(dp_sum));
    }
}
