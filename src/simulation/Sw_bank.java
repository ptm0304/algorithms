package simulation;

import java.util.Scanner;

// SW expert 정식이의 은행업무
public class Sw_bank {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();

        outer:
        for (int tc = 1; tc <= nt; tc++) {
            String bin = sc.next();
            String tern = sc.next();
            for (int bi = 0; bi < bin.length(); bi++) {
                char bc = (bin.charAt(bi) == '0') ? '1' : '0';
                String newBin = bin.substring(0, bi) + bc + bin.substring(bi + 1);
                for (int ti = 0; ti < tern.length(); ti++) {
                    char ternChar = tern.charAt(ti);
                    for (int i = 0; i < 3; i++) {
                        if (ternChar-'0' != i) {
                            String newTern = tern.substring(0, ti) + i + tern.substring(ti + 1);
                            Long ans = Long.parseLong(newBin, 2);
                            if (ans == Long.parseLong(newTern, 3)) {
                                System.out.println("#" + tc + " " + ans);
                                continue outer;
                            }
                        }
                    }
                }
            }
        }
    }
}
