package past;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class B_14502 {
    static int N;
    static int M;
    static int[][] map;
    static int nWalls;
    static ArrayList<int[]> combinations;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int maxSafeArea = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        combinations = new ArrayList<>();

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) nWalls++;
            }
        }

        combination(0, 0);

        System.out.println(maxSafeArea);
    }

    static void combination(int row, int col) {
        if (combinations.size() == 3) {
            for (int[] wall : combinations) {
                map[wall[0]][wall[1]] = 1;
            }
            boolean[][] visited = new boolean[N][M];
            int virusCnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visited[i][j]) continue;
                    if (map[i][j] == 2) {
                        Queue<int[]> q = new LinkedList<>();
                        q.offer(new int[]{i, j});
                        while (!q.isEmpty()) {
                            int[] curr = q.poll();
                            if (visited[curr[0]][curr[1]]) continue;
                            visited[curr[0]][curr[1]] = true;
                            virusCnt++;

                            for (int di = 0; di < 4; di++) {
                                int nextRow = curr[0] + dy[di];
                                int nextCol = curr[1] + dx[di];
                                if (nextRow >= 0 && nextCol >= 0 && nextRow < N && nextCol < M &&
                                map[nextRow][nextCol] != 1) {
                                    q.offer(new int[] {nextRow, nextCol});
                                }
                            }
                        }
                    }
                }
            }

            maxSafeArea = Math.max(maxSafeArea, N * M - nWalls - 3 - virusCnt);
            for (int[] wall : combinations) {
                map[wall[0]][wall[1]] = 0;
            }
            return;
        }
        for (int r = row; r < N; r++) {
            for (int c = (r == row)? col : 0; c < M; c++) {
                if (map[r][c] != 0) continue;
                combinations.add(new int[] {r, c});
                int nextRow = r;
                int nextCol = c + 1;
                if (nextCol == M) {
                    nextCol = 0;
                    nextRow++;
                }
                combination(nextRow, nextCol);
                combinations.remove(combinations.size() - 1);
            }
        }
    }
}
