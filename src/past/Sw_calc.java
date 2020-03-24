package past;

import java.util.ArrayList;
import java.util.Scanner;

public class Sw_calc {
    static ArrayList<Integer> nums;
    static int x;
    static int min;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();
        for (int tc = 1; tc <= nt; tc++) {
            min = Integer.MAX_VALUE;
            nums = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                if (sc.nextInt() == 1) {
                    nums.add(i);
                }
            }
            x = sc.nextInt();
            if (x == 1 || x == 0) {
                if (nums.contains(x)) {
                    System.out.println("#" + tc + " " + 2);
                }
                else {
                    System.out.println("#" + tc + " " + -1);
                }
                continue;
            }
            dfs(x, 0, 0);
            System.out.println("#" + tc + " " + (min == Integer.MAX_VALUE ? -1 : min));
        }
    }

    static void dfs(int x, int num, int cnt) {
        if (x == 1) {
            min = Math.min(min, cnt);
            return;
        }
        if (cnt >= min) return;
        if (num > x) return;
        if (num != 0 && num != 1 && x % num == 0) {
            dfs(x / num, 0, cnt + 1);
        }
        for (Integer digit : nums) {
            if (digit != 0 || num != 0) {
                dfs(x, num * 10 + digit, cnt + 1);
            }
        }
    }
}
