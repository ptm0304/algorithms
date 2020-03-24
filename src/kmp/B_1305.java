package kmp;

import java.util.Scanner;

public class B_1305 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String line = sc.next();
        int[] pi = new int[n];
        int j = 0;
        for (int i = 1; i < n; i++) {
            while (j > 0 && line.charAt(i) != line.charAt(j)) {
                j = pi[j - 1];
            }
            if (line.charAt(j) == line.charAt(i)) {
                pi[i] = ++j;
            }
        }
        System.out.println(n - pi[n-1]);
    }
}
