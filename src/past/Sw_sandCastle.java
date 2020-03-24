package past;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Sw_sandCastle {
    static int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nt = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= nt; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            char[][] map = new char[h][w];
            for (int r = 0; r < h; r++) {
                String line = br.readLine().trim();
                for (int c = 0; c < w; c++) {
                    map[r][c] = line.charAt(c);
                }
            }
            int[][] map2 = new int[h][w];
            ArrayList<int[]> removeIdx = new ArrayList<>();
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == '.') {
                        map2[i][j] = Integer.MAX_VALUE;
                    }
                    else {
                        int power = map[i][j] - '0';
                        for (int di = 0; di < 8; di++) {
                            int nextRow = i + dy[di];
                            int nextCol = j + dx[di];
                            if (nextRow >= 0 && nextCol >= 0 && nextRow < h && nextCol < w) {
                                if (map[nextRow][nextCol] == '.') power--;
                            }
                        }
                        if (power <= 0) {
                            removeIdx.add(new int[] {i, j});
                        }
                        map2[i][j] = power;
                    }
                }
            }

            int cnt = 0;
            while (!removeIdx.isEmpty()) {
                cnt++;
                ArrayList<int[]> temp = new ArrayList<>();
                for (int[] idx : removeIdx) {
                    map2[idx[0]][idx[1]] = Integer.MAX_VALUE;
                    for (int di = 0; di < 8; di++) {
                        int nextRow = idx[0] + dy[di];
                        int nextCol = idx[1] + dx[di];
                        if (nextRow >= 0 && nextCol >= 0 && nextRow < h && nextCol < w) {
                            map2[nextRow][nextCol]--;
                            if (map2[nextRow][nextCol] == 0) {
                                temp.add(new int[] {nextRow, nextCol});
                            }
                        }
                    }
                }
                removeIdx = temp;
            }
            System.out.println("#" + tc + " " + cnt);
        }
    }

}
