package past;

import java.util.ArrayList;
import java.util.Scanner;

public class Sw_hamster {
    static int N; // 우리수
    static int X; // 각 우리 햄스터 최대수
    static int M; // 기록 수
    static ArrayList<int[]> records;
    static String ansStr;
    static int[] ans;
    static int ansNum;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();
        for (int tc = 1; tc <= nt; tc++) {
            N = sc.nextInt();
            X = sc.nextInt();
            M = sc.nextInt();
            records = new ArrayList<>();
            for (int m = 0; m < M; m++) {
                records.add(new int[] {sc.nextInt(), sc.nextInt(), sc.nextInt()}); // l, r, s
            }
            ans = null;
            ansStr = null;
            ansNum = -1;
            perm(0, new int[N], 0);
            System.out.println("#" + tc + " " + (ansStr == null? -1 : ansStr));
        }
    }

    static void perm(int idx, int[] perm, int permSum) {
        if (idx == perm.length) {
            for (int[] record : records) {
                int sum = 0;
                for (int i = record[0] - 1; i < record[1]; i++) {
                    sum += perm[i];
                }
                if (sum != record[2]) return;
            }
            if (permSum < ansNum) return;
            if (permSum == ansNum) {
                if (ans == null || compare(perm, ans) >= 0) return;
            }
            StringBuilder permToStr = new StringBuilder();
            for (Integer i : perm) {
                permToStr.append(i + " ");
            }
            ansNum = permSum;
            ansStr = permToStr.toString().trim();
            ans = perm;
            return;
        }
        for (int i = 0; i <= X; i++) {
            perm[idx] = i;
            perm(idx + 1, perm, permSum + i);
        }
    }

    static int compare(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] == arr2[i]) continue;
            if (arr1[i] > arr2[i]) return 1;
            else return -1;
        }
        return 0;
    }
}
