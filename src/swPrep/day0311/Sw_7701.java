package swPrep.day0311;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Sw_7701 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();
        for (int tc = 1; tc <= nt; tc++) {
            ArrayList<String>[] namebySize = new ArrayList[51];
            for (int i = 1; i <= 50; i++) {
                namebySize[i] = new ArrayList<>();
            }
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                String name = sc.next();
                namebySize[name.length()].add(name);
            }
            for (int i = 1; i <= 50; i++) {
                Collections.sort(namebySize[i]);
            }
            System.out.println("#" + tc);
            for (int i = 1; i <= 50; i++) {
                String prev = "";
                for (String name : namebySize[i]) {
                    if (name.equals(prev)) continue;
                    prev = name;
                    System.out.println(name);
                }
            }
        }
    }
}
