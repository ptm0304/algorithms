package swPrep.day0312;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Sw_3378 {
    static ArrayList<int[]> conditions;
    static HashSet<Integer> sCands;
    static HashSet<Integer> mCands;
    static HashSet<Integer> lCands;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nt = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= nt; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            conditions = new ArrayList<>();
            sCands = new HashSet<>();
            mCands = new HashSet<>();
            lCands = new HashSet<>();


            int nSmall = 0;
            int nMedium = 0;
            int nLarge = 0;

            for (int i = 0; i < p ; i++) {
                String line = br.readLine();
                int nIndent = 0;
                int index = 0;
                for (int j = 0; j < line.length(); j++) {
                    char c = line.charAt(j);
                    if (c == '.') {
                        nIndent++;
                        continue;
                    }
                    conditions.add(new int[] {nSmall, nMedium, nLarge, nIndent});
                    index = j;
                    break;
                }
                for (int j = index; j < line.length(); j++) {
                    char c = line.charAt(j);
                    if (c == '(') {
                        nSmall++;
                    }
                    else if (c == ')') {
                        nSmall--;
                    }
                    else if (c == '{') {
                        nMedium++;
                    }
                    else if (c == '}') {
                        nMedium--;
                    }
                    else if (c == '[') {
                        nLarge++;
                    }
                    else if (c == ']') {
                        nLarge--;
                    }
                }
            }

            perm(0, new int[3]);

            String[] myCode = new String[q];
            for (int i = 0; i < q; i++) {
                myCode[i] = br.readLine();
            }

            nSmall = 0;
            nMedium = 0;
            nLarge = 0;

            System.out.print("#" + tc + " ");
            for (int i = 0; i < q; i++) {
                if (!checkConditions(nSmall, nMedium, nLarge)) {
                    int cnt = 0;
                    if (nSmall > 0) {
                        if (sCands.size() != 1) {
                            cnt = -1;
                        }
                        else {
                            cnt += sCands.iterator().next() * nSmall;
                        }
                    }
                    if (cnt != -1 && nLarge > 0) {
                        if (lCands.size() != 1) {
                            cnt = -1;
                        }
                        else {
                            cnt += lCands.iterator().next() * nSmall;
                        }
                    }
                    if (cnt != -1 && nMedium > 0) {
                        if (mCands.size() != 1) {
                            cnt = -1;
                        }
                        else {
                            cnt += mCands.iterator().next() * nSmall;
                        }
                    }
                    System.out.print(cnt + " ");
                }
                String line = myCode[i];
                for (int j = 0; j < line.length(); j++) {
                    char c = line.charAt(j);
                    if (c == '(') {
                        nSmall++;
                    }
                    else if (c == ')') {
                        nSmall--;
                    }
                    else if (c == '{') {
                        nMedium++;
                    }
                    else if (c == '}') {
                        nMedium--;
                    }
                    else if (c == '[') {
                        nLarge++;
                    }
                    else if (c == ']') {
                        nLarge--;
                    }
                }
            }
            System.out.println();

        }
    }

    static boolean checkConditions(int nSmall, int nMedium, int nLarge) {
        outer:
        for (int[] condition : conditions) {
            ArrayList<Integer> list = new ArrayList<>();
            if (condition[0] != nSmall) {
                if (condition[0] == 0) continue;
                if (nSmall % condition[0] == 0) {
                    list.add(nSmall / condition[0]);
                }
            }
            else if (nSmall != 0) {
                list.add(1);
            }
            if (condition[1] != nMedium) {
                if (condition[1] == 0) continue;
                if (nMedium % condition[1] == 0) {
                    list.add(nMedium / condition[1]);
                }
            }
            else if (nMedium != 0) {
                list.add(1);
            }
            if (condition[2] != nLarge) {
                if (condition[2] == 0) continue;
                if (nLarge % condition[2] == 0) {
                    list.add(nLarge / condition[2]);
                }
            }
            else if (nLarge != 0) {
                list.add(1);
            }

            for (int i = 1; i < list.size(); i++) {
                if (list.get(i - 1) != list.get(i)) continue outer;
            }
            if (list.isEmpty()) {
                System.out.print(0 + " ");
            }
            else {
                System.out.print(list.get(0) * condition[3] + " ");
            }
            return true;

        }
        return false;
    }
    static void perm(int index, int[] perm) {
        if (index == 3) {
            for (int[] condition : conditions) {
                int calc = perm[0] * condition[0] + perm[1] * condition[1] +
                        perm[2] * condition[2];
                if (calc != condition[3]) return;
            }
            sCands.add(perm[0]);
            mCands.add(perm[1]);
            lCands.add(perm[2]);
            return;
        }
        for (int i = 1; i <= 20; i++) {
            perm[index] = i;
            perm(index + 1, perm);
        }
    }
}
