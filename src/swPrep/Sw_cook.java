package swPrep;

import java.util.Scanner;

public class Sw_cook {
    static int n;
    static int[][] synergy;
    static int min;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();
        for (int tc = 1; tc<=nt; tc++) {
            n = sc.nextInt();
            synergy = new int[n][n];
            min = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    synergy[i][j] = sc.nextInt();
                }
            }

            comb(0, 0, new int[n / 2]);

            System.out.println("#" + tc + " " + min);
        }
    }

    static void comb(int combInd, int ingInd, int[] comb) {
        if (combInd == n / 2) {
            int[] comb2 = getAnotherComb(comb);
            int diff = Math.abs(calcSynergy(comb) - calcSynergy(comb2));
            min = Math.min(min, diff);
            return;
        }
        if (ingInd == n) {
            return;
        }
        for (int i = ingInd; i < n; i++) {
            comb[combInd] = i;
            comb(combInd + 1, i + 1, comb);
        }
    }

    static int calcSynergy(int[] comb) {
        int sum = 0;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                if (i == j) continue;
                sum += synergy[comb[i]][comb[j]];
            }
        }
        return sum;
    }

    static int[] getAnotherComb(int[] comb) {
        int[] ans = new int[n / 2];
        int ind = 0;
        for (int i = 0; i < comb[0]; i++) {
            ans[ind++] = i;
        }
        for (int i = 1; i < n / 2; i++) {
            if (comb[i] - comb[i - 1] > 1) {
                for (int j = comb[i - 1] + 1; j < comb[i]; j++) {
                    ans[ind++] = j;
                }
            }
        }
        for (int i = comb[n / 2 - 1] + 1; i < n; i++) {
            ans[ind++] = i;
        }
        return ans;
    }
}
