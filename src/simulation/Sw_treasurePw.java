package simulation;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

// [모의 SW 역량테스트] 보물상자 비밀번호
public class Sw_treasurePw {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();
        for (int tc = 1; tc <= nt; tc++) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            String str = sc.next();
            TreeSet<String> set = new TreeSet<>(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o2.compareTo(o1);
                }
            });

            int len = n / 4;
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < 3; j++) {
                    int ind = j * len + i;
                    String substr = str.substring(ind, ind + len);
                    set.add(substr);
                }
                String substr = str.substring(3 * len + i) + str.substring(0, i);
                set.add(substr);
            }
            Iterator<String> e = set.iterator();
            String ans = "";
            while (k > 0) {
                k--;
                ans = e.next();
            }
            System.out.println("#" + tc + " " + Integer.parseInt(ans, 16));
        }
    }
}
