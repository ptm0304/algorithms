package kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_11585 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue<Character> roulette = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            roulette.offer(st.nextToken().charAt(0));
        }
        st = new StringTokenizer(br.readLine());

        char[] pattern = new char[n];
        for (int i = 0; i < n; i++) {
            pattern[i] = st.nextToken().charAt(0);
        }

        int[] table = new int[n];
        int j = 0;
        for (int i = 1; i < n; i++) {
            while (j > 0 && pattern[j] != pattern[i]) {
                j = table[j - 1];
            }
            if (pattern[j] == pattern[i]) {
                table[i] = ++j;
            }
        }

        int ans = 0;
        int cnt = 0;
        j = 0;
        while (cnt < 2 * n - 1) {
            char curr = roulette.poll();
            roulette.offer(curr);
            cnt++;
            while (j > 0 && pattern[j] != curr) {
                j = table[j - 1];
            }
            if (pattern[j] == curr) {
                if (j == n - 1) {
                    ans++;
                    j = table[j];
                }
                else {
                    j++;
                }
            }
        }

        int gcd = gcd(ans, n);
        System.out.print((ans / gcd) + "/" + (n / gcd));
    }

    static int gcd(int a, int b){
        while(b!=0){
            int r = a%b;
            a= b;
            b= r;
        }
        return a;
    }
}
