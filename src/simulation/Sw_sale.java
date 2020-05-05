package simulation;

import java.util.Arrays;
import java.util.Scanner;

public class Sw_sale {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();
        for (int tc = 1; tc <= nt; tc++) {
            long[] arr = new long[sc.nextInt()];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = sc.nextInt();
            }
            Arrays.sort(arr);
            long ans = 0;
            int x = 0;
            for (int i = arr.length - 1; i >= 0; i--) {
                if (x == 2) {
                    x = 0;
                    continue;
                }
                x++;
                ans += arr[i];
            }
            System.out.println("#" + tc + " " + ans);
        }
    }
}
