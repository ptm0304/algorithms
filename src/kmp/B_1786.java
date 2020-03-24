package kmp;

import java.util.ArrayList;
import java.util.Scanner;

public class B_1786 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] t = sc.nextLine().toCharArray(); // long String
        char[] p = sc.nextLine().toCharArray(); // short String
        int n = p.length;
        int[] table = new int[n];

        int j = 0;
        for (int i = 1; i < n; i++) {
            while (j > 0 && p[j] != p[i]) {
                j = table[j - 1];
            }
            if (p[i] == p[j]) {
                table[i] = ++j;
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        j = 0;
        for (int i = 0; i < t.length; i++) {
            while (j > 0 && p[j] != t[i]) {
                j = table[j - 1];
            }
            if (p[j] == t[i]) {
                if (j == n - 1) {
                    ans.add(i - j + 1);
                    j = table[j];
                }
                else {
                    j++;
                }
            }
        }
        System.out.println(ans.size());
        for (Integer i : ans) {
            System.out.print(i + " ");
        }
    }
}
