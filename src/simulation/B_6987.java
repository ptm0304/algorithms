package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 6987번 월드컵
public class B_6987 {
    static int[] wins;
    static int[] ties;
    static int[] loss;
    static int[] g1 = {0,0,0,0,0,1,1,1,1,2,2,2,3,3,4};
    static int[] g2 = {1,2,3,4,5,2,3,4,5,3,4,5,4,5,5};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        outer:
        for (int i = 0; i < 4; i++) {
            StringTokenizer st= new StringTokenizer(br.readLine());
            wins = new int[6];
            ties = new int[6];
            loss = new int[6];
            for (int j = 0; j < 6; j++) {
                wins[j] = Integer.parseInt(st.nextToken());
                ties[j] = Integer.parseInt(st.nextToken());
                loss[j] = Integer.parseInt(st.nextToken());
                if (wins[j] + ties[j] + loss[j] != 5) {
                    System.out.print(0 + " ");
                    continue outer;
                }
            }
            System.out.print((simGame(0) ? 1 : 0) + " ");
        }
    }

    static boolean simGame(int game) {
        if (game == 15) {
            return true;
        }
        int t1 = g1[game];
        int t2 = g2[game];

        if (wins[t1] > 0 && loss[t2] > 0) { // t1의 승리가 가능한 경우
            wins[t1]--;
            loss[t2]--;
            if (simGame(game + 1)) {
                return true;
            }
            wins[t1]++;
            loss[t2]++;
        }
        if (loss[t1] > 0 && wins[t2] > 0) { // t2의 승리가 가능한 경우
            wins[t2]--;
            loss[t1]--;
            if (simGame(game + 1)) {
                return true;
            }
            wins[t2]++;
            loss[t1]++;
        }
        if (ties[t1] > 0 && ties[t2] > 0) { // 무승부가 가능한 경우
            ties[t1]--;
            ties[t2]--;
            if (simGame(game + 1)) {
                return true;
            }
            ties[t1]++;
            ties[t2]++;
        }
        return false;
    }
}
