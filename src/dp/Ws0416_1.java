package dp;

public class Ws0416_1 {
    public static void main(String[] args) {
        int[] f = new int[100];
        f[1] = 1;
        f[2] = 2;
        f[3] = 4;

        for (int i = 4; i <= 8; i++) {
            f[i] = f[i - 1] + f[i - 2] + f[i - 3];
        }

        System.out.println(f[8]);
    }
}
