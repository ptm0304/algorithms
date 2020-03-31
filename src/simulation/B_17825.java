package simulation;

import java.util.Arrays;
import java.util.Scanner;

public class B_17825 {
    static Base[] pan;
    static int[] diceRes;
    static int[] malPos = new int[4];
    static int max = Integer.MIN_VALUE;
    final static int DEST = 32;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        createPan();
        diceRes = new int[10];
        for (int i = 0; i < 10; i++) {
            diceRes[i] = sc.nextInt();
        }
        perm(0, 0);
        System.out.println(max);
    }

    static void perm(int index, int pts) {
        if (index == 10) {
            max = Math.max(max, pts);
            return;
        }
        int steps = diceRes[index];
        outer:
        for (int i = 0; i < 4; i++) {
            if (malPos[i] == DEST) continue;
            int originalPos = malPos[i];
            malPos[i] = pan[malPos[i]].blue == 0 ?
                    pan[malPos[i]].red : pan[malPos[i]].blue ;
            if (malPos[i] != DEST) {
                for (int j = 1; j < steps; j++) {
                    malPos[i] = pan[malPos[i]].red;
                    if (malPos[i] == DEST) break;
                }
            }
            if (malPos[i] != DEST) {
                for (int j = 0; j < 4; j++) {
                    if (i == j) continue;
                    if (malPos[i] == malPos[j]) {
                        malPos[i] = originalPos;
                        continue outer;
                    }
                }
            }
            perm(index + 1, pan[malPos[i]].point + pts);
            malPos[i] = originalPos;
        }
    }

    static void createPan() {
        pan = new Base[33];
        for (int i = 0; i < 33; i++) {
            pan[i] = new Base();
        }
        pan[0].point = 0; // start

        // surrounding course
        int pt = 2;
        for (int i = 1; i <= 20; i++) {
            pan[i].point = pt;
            pan[i - 1].red = i;
            pt += 2;
        }
        pan[20].red = 32; //dest

        pan[5].blue = 21;
        pt = 13;
        for (int i = 21; i <= 23; i++) {
            pan[i].red = i + 1;
            pan[i].point = pt;
            pt += 3;
        }

        pt = 25;
        for (int i = 24; i <= 25; i++) {
            pan[i].point = pt;
            pan[i].red = i + 1;
            pt += 5;
        }
        pan[26].point = pt;
        pan[26].red = 20;

        pan[10].blue = 27;
        pan[27].point = 22;
        pan[27].red = 28;
        pan[28].point = 24;
        pan[28].red = 24;

        pan[15].blue = 29;
        pan[29].red = 30;
        pan[29].point = 28;
        pan[30].red = 31;
        pan[30].point = 27;
        pan[31].red = 24;
        pan[31].point = 26;
    }

    private static class Base {
        int point;
        int red;
        int blue;
    }
}
