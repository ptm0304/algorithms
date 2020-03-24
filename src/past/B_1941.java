package past;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B_1941 {
    static char[][] map = new char[5][5];

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int ans = 0;

    static int maxInd = 24;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[5][5];

        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) {
                map[i][j] = line.charAt(j);
            }
        }

//        System.out.println(Arrays.toString(calcCoordFromInd(8)));
        comb(0, 0, new int[7]);


        System.out.println(ans);
    }

    static void comb(int mapInd, int combInd, int[] combination) {
        if (combInd == 7) {
            int cntMyTeam = 0;
            for (int i : combination) {
                int[] coord = calcCoordFromInd(i);
                if (map[coord[0]][coord[1]] == 'S') {
                    cntMyTeam++;
                }
            }
//            System.out.println(cntMyTeam);
            if (cntMyTeam < 4) return;

            Queue<Integer> q = new LinkedList<>();
            q.offer(combination[0]);
            boolean[] visited = new boolean[25];
            int cnt = 0;
            while (!q.isEmpty()) {
                int curr = q.poll();
                if (visited[curr]) continue;
                boolean isMember = false;
                for (int i = 0; i < combination.length; i++) {
                    if (curr == combination[i]) isMember = true;
                }
                if (!isMember) continue;
                visited[curr] = true;
                cnt++;
                int[] currCoord = calcCoordFromInd(curr);
                for (int di = 0; di < 4; di++) {
                    int neighborRow = currCoord[0] + dy[di];
                    int neighborCol = currCoord[1] + dx[di];
                    if (neighborRow >= 0 && neighborCol >= 0 && neighborCol < 5 && neighborRow < 5) {
                        int neighborInd = calcInd(neighborRow, neighborCol);
                        if (neighborInd >= 0 && neighborInd < 25) q.offer(neighborInd);
                    }
                }

            }
            if (cnt == 7) {
//                System.out.println(Arrays.toString(combination));
                ans++;
            }
            return;
        }
        if (mapInd > maxInd) return;
        for (int i = mapInd; i <= maxInd; i++) {
            combination[combInd] = i;
            comb(i + 1, combInd + 1, combination);
        }
    }

    static int calcInd(int row, int col) {
        return col + row * 5;
    }

    static int[] calcCoordFromInd(int ind) {
        return new int[] {ind / 5, ind % 5};
    }

}
