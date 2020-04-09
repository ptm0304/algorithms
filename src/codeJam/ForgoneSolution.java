package codeJam;

import java.util.Scanner;

public class ForgoneSolution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();
        for (int tc = 1; tc <= nt; tc++) {
            String nStr = sc.next();
            StringBuilder n1 = new StringBuilder();
            StringBuilder n2 = new StringBuilder();
            for (int i = 0; i < nStr.length(); i++) {
                if (nStr.charAt(i) == '4') {
                    n1.append('2');
                    n2.append('2');
                }
                else {
                    n1.append(nStr.charAt(i));
                    n2.append('0');
                }
            }
            System.out.println("Case #" + tc + ": " + parseInt(n1.toString()) + " " + parseInt(n2.toString()));
        }
    }

    static String parseInt(String str) {
        int ind = 0;
        while (str.charAt(ind++) == '0') {}

        return str.substring(ind - 1);
    }
}