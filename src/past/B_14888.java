package past;

import java.util.Arrays;
import java.util.Scanner;

public class B_14888 {
    static int N;
    static int[] nums;
    static int numPlus;
    static int numMinus;
    static int numTimes;
    static int numDivides;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        nums = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }

        numPlus = sc.nextInt();
        numMinus = sc.nextInt();
        numTimes = sc.nextInt();
        numDivides = sc.nextInt();

        calculate(nums[0], 1);

        System.out.println(max);
        System.out.println(min);
    }

    static void calculate(long sum, int index) {
        if (index == N) {
            min = Math.min(min, (int) sum);
            max = Math.max(max, (int) sum);
            return;
        }
        if (numPlus > 0) {
            numPlus--;
            calculate(sum + nums[index], index + 1);
            numPlus++;
        }
        if (numMinus > 0) {
            numMinus--;
            calculate(sum - nums[index], index + 1);
            numMinus++;
        }
        if (numTimes > 0) {
            numTimes--;
            calculate(sum * nums[index], index + 1);
            numTimes++;
        }
        if (numDivides > 0) {
            numDivides--;
            calculate(sum / nums[index], index + 1);
            numDivides++;
        }
    }
}
