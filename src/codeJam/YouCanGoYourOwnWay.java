package codeJam;

import java.util.HashSet;
import java.util.Scanner;

public class YouCanGoYourOwnWay {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();
        for (int tc = 1; tc <= nt; tc++) {
            int n = sc.nextInt();
            sc.nextLine();
            String lPath = sc.nextLine().trim(); // Lydia's path
            System.out.println("Case #" + tc +": " + invertLydiaPath(lPath));
        }
    }

    static String invertLydiaPath(String lydiaPath) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lydiaPath.length(); i++) {
            switch (lydiaPath.charAt(i)) {
                case 'S':
                    sb.append('E');
                    break;
                case 'E':
                    sb.append('S');
                    break;
            }
        }
        return sb.toString();
    }
}
