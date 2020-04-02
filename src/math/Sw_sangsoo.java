package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sw_sangsoo {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nt = Integer.parseInt(br.readLine());

        for (int tc = 1 ; tc <= nt; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[] lessons = new int[n];
            for (int i = 0; i < n; i++) {
                lessons[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(lessons);
            double ans = 0;
            int pow = 1;
            for (int i = n - 1; i >= n - k; i--) {
                ans += (double) lessons[i] / Math.pow(2, pow++);
            }
            System.out.println(ans);
        }
    }
}
