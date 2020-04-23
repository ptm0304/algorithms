package simulation;

import java.util.Arrays;
import java.util.Scanner;

public class B_16234 {
    static int[][] pops;
    static Country[][] countries;
    static int n, l, r;
    static int ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        l = sc.nextInt();
        r = sc.nextInt();
        countries = new Country[n][n];
        pops = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pops[i][j] = sc.nextInt();
            }
        }

        while (movePop());

        System.out.println(ans);
    }

    static boolean movePop() {

        boolean moved = false;
        countries = new Country[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                countries[i][j] = new Country(i, j, pops[i][j]);
                if (i - 1 >= 0) {
                    int diff = Math.abs(pops[i][j] - pops[i - 1][j]);
                    if (diff >= l && diff <= r) {
                        join(countries[i - 1][j], countries[i][j]);
                        moved = true;
                    }
                }
                if (j - 1 >= 0){
                    int diff = Math.abs(pops[i][j] - pops[i][j - 1]);
                    if (diff >= l && diff <= r) {
                        join(countries[i][j - 1], countries[i][j]);
                        moved = true;
                    }
                }
            }
        }
        if (moved) {
            ans++;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    Country p = find(countries[i][j]);
                    pops[i][j] = p.sumPop / p.numChildren;
                }
            }
//            print();
        }

        return moved;
    }

    static void print() {
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(pops[i]));
        }
    }

    static void join(Country c1, Country c2) {
        Country p1 = find(c1);
        Country p2 = find(c2);
        if ((p1.col + p1.row * n) < (p2.col + p2.row * n)) {
            p2.parentCol = p1.col;
            p2.parentRow = p1.row;
            p1.numChildren += p2.numChildren;
            p1.sumPop += p2.sumPop;
        }
        else if ((p1.col + p1.row * n) > (p2.col + p2.row * n)) {
            p1.parentCol = p2.col;
            p1.parentRow = p2.row;
            p2.numChildren += p1.numChildren;
            p2.sumPop += p1.sumPop;
        }
    }

    static Country find(Country c1) {
        if (c1.parentRow == c1.row && c1.parentCol == c1.col) {
            return c1;
        }
        else {
            Country p = find(countries[c1.parentRow][c1.parentCol]);
            c1.parentRow = p.row;
            c1.parentCol = p.col;
            return p;
        }
    }

    private static class Country {
        int numChildren;
        int parentRow;
        int parentCol;
        int sumPop;
        int row, col;

        public Country(int row, int col, int sumPop) {
            this.row = row;
            this.col = col;
            this.parentRow = row;
            this.parentCol = col;
            this.sumPop = sumPop;
            this.numChildren = 1;
        }
    }
}
