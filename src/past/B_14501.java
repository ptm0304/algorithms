package past;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_14501 {
    static int max = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] schedule = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            schedule[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        int[] dp = new int[N];
        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dp[i]);
            if (schedule[i][0] + i <= N) {
                dp[i] = Math.max(max, dp[i]) + schedule[i][1];
            }
            if (schedule[i][0] + i < N) {
                dp[schedule[i][0] + i] = Math.max(dp[i], dp[schedule[i][0] + i]);
            }
        }
        Arrays.sort(dp);
        System.out.println(dp[N - 1]);
    }

}
