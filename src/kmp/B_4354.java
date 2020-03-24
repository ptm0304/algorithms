package kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_4354 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        while (!line.equals(".")) {
            int n = line.length();
            int[] table = new int[n];
            int j = 0;
            for (int i = 1; i < n; i++) {
                while (j > 0 && line.charAt(j) != line.charAt(i)) {
                    j = table[j - 1];
                }
                if (line.charAt(i) == line.charAt(j)) {
                    table[i] = ++j;
                }
            }
            if (n % (n - table[n - 1]) == 0) {
                System.out.println(n / (n - table[n - 1]));
            }
            else {
                System.out.println(1);
            }
            line = br.readLine();
        }
    }
}
