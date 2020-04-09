package codeJam;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

public class Cryptopangrams {
    final static char[] alphabets = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U',
    'V','W','X','Y','Z'};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();
        for (int tc = 1; tc <= nt; tc++) {
            BigInteger N = new BigInteger(sc.next());
            int L = sc.nextInt();
            BigInteger[] arr = new BigInteger[L];
            for (int i = 0; i < L; i++) {
                arr[i] = new BigInteger(sc.next());
            }
            TreeSet<BigInteger> set = new TreeSet<>(); // set to save primes
            BigInteger p = gcd(arr[0], arr[1]); // gcd of first and second element == represents second letter of pangram
            BigInteger p1 = arr[0].divide(p);
            set.add(p);
            set.add(p1);
            BigInteger[] pangram = new BigInteger[L + 1];
            pangram[0] = p1;
            pangram[1] = p;

            for (int i = 1; i < L; i++) {
                p1 = arr[i].divide(p);
                set.add(p1);
                pangram[i + 1] = p1;
                p = p1;
            }

            HashMap<BigInteger, Character> map = new HashMap<>();
            final int[] ind = {0};
            set.stream().forEach(bigInteger -> {
                map.put(bigInteger, alphabets[ind[0]++]);
            });

            System.out.print("Case #" + tc +": ");
            for (BigInteger bi : pangram) {
                System.out.print(map.get(bi));
            }
            System.out.println();
        }
    }

    static BigInteger gcd(BigInteger i1, BigInteger i2) {
        if (i2.equals(BigInteger.ZERO)) {
            return i1;
        }
        BigInteger r = i1.mod(i2);
        return gcd(i2, r);
    }
}
