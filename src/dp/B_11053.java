package dp;

import java.util.Arrays;
import java.util.Scanner;

public class B_11053 {
    static int[] seq;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        seq = new int[n];
        Arrays.fill(seq, Integer.MAX_VALUE);
        seq[0] = sc.nextInt();
        for (int i = 1; i < n; i++) {
            int curr = sc.nextInt();
            binaryPush(0, n - 1, curr);
        }
        int len = 0;
        for (int i = 0; i < n; i++) {
            if (seq[i] == Integer.MAX_VALUE) break;
            len++;
        }

        System.out.println(len);
    }

    static void binaryPush(int low, int high, int num) {
        int ind = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (seq[mid] > num) {
                high = mid - 1;
            }
            else if (seq[mid] == num) {
                ind = mid;
                break;
            }
            else {
                ind = mid + 1;
                low = mid + 1;
            }
        }
        seq[ind] = num;
    }
}
