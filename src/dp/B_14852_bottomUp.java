package dp;

import java.util.Scanner;

public class B_14852_bottomUp {
    final static int mod = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // n이 2보다 작거나 같을 경우를 대비해서 n + 3 크기로 만듬
        int[] dp1 = new int[n + 3]; // 평평한 경우의 수를 저장할 배열
        int[] dp2 = new int[n + 3]; // 한쪽이 튀어나온 경우의 수를 저장할 배열
        dp1[1] = 2;
        dp1[2] = 7;
        dp2[1] = 2;
        dp2[2] = 6;

        for (int i = 3; i <= n; i++) {
            // 튀어나오는 경우: 1칸짜리 한개만 놓는경우 + 이미 튀어나온애 가로로 채우는 경우
            dp2[i] = ((dp1[i - 1] * 2) % mod + dp2[i - 1]) % mod;

            // 평평해지는 경우: 세로로 놓는 경우 + 가로로 두개 놓는 경우 + 한개 채우는경우 - 1칸짜리로 두개 채워졌을경우(위에서 곱하기 2 했기 때문에)
            // 여기서 세로로 놓는 경우와 1칸짜리로 두개 채워지는 경우 모두 dp1[i-1]이기 때문에 가로로 두개놓는경우 + 한개 채우는경우만 계산
            dp1[i] = (dp1[i - 2] + dp2[i]) % mod;
        }

        System.out.println(dp1[n]);
    }
}
