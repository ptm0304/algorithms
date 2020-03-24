package past;

import java.util.ArrayList;
import java.util.Scanner;

public class B_1038 {
    static ArrayList<Long>[][] dp = new ArrayList[10][10];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n < 10) {
            System.out.println(n);
            System.exit(0);
        }

        dp[0] = new ArrayList[10];
        dp[0][0] = new ArrayList<Long>();
        dp[0][1] = new ArrayList<Long>();
        dp[0][2] = new ArrayList<Long>();
        dp[0][3] = new ArrayList<Long>();
        dp[0][4] = new ArrayList<Long>();
        dp[0][5] = new ArrayList<Long>();
        dp[0][6] = new ArrayList<Long>();
        dp[0][7] = new ArrayList<Long>();
        dp[0][8] = new ArrayList<Long>();
        dp[0][9] = new ArrayList<Long>();

        dp[0][0].add(0L);
        dp[0][1].add(1L);
        dp[0][2].add(2L);
        dp[0][3].add(3L);
        dp[0][4].add(4L);
        dp[0][5].add(5L);
        dp[0][6].add(6L);
        dp[0][7].add(7L);
        dp[0][8].add(8L);
        dp[0][9].add(9L);

        int digit = 1;
        int num = 1;

        int count = 10;
        while (digit <= 9 && num <= 9) {
            dp[digit][num] = new ArrayList<Long>();
            long adder = ((long) Math.pow(10, digit)) * (long)num;
            for (int i = 0; i < num; i++) {
                if (dp[digit - 1][i] == null) continue;
                for (int j = 0; j < dp[digit - 1][i].size(); j++) {
                    dp[digit][num].add(adder + dp[digit-1][i].get(j));
                    count++;
                    if (count == n + 1) {
                        System.out.println(adder + (long) dp[digit-1][i].get(j));
                        System.exit(0);
                    }
                }
            }
            num++;
            if (num == 10) {
                digit++;
                num = 0;
            }
        }

        System.out.println(-1);


    }
}
