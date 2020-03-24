package past;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_14503 {
    static int N, M, r, c, d;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    static char[] dirs = {'N', 'E', 'S', 'W'};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine().trim());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        boolean[][] isWall = new boolean[N][M];
        boolean[][] cleaned = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < M; j++) {
                int val = Integer.parseInt(st.nextToken());
                isWall[i][j] = val == 1 ? true : false;
            }
        }

        int cleanedAreas = 0;
        outer:
        while (true) {
            if (!cleaned[r][c]) {
                cleanedAreas++;
                cleaned[r][c] = true;
            }
            for (int i = 0; i < 4; i++) {
                int leftDir = (d + 3) % 4;
                int leftRow = r + dy[leftDir];
                int leftCol = c + dx[leftDir];
                if (leftRow < 0 || leftCol < 0 || leftRow >= N || leftCol >= M ||
                        cleaned[leftRow][leftCol] || isWall[leftRow][leftCol]) {
                    d = leftDir;
                    continue;
                }
                else {
                    r = leftRow;
                    c = leftCol;
                    d = leftDir;
                    continue outer;
                }
            }
            int backRow = r + dy[(d + 2) % 4];
            int backCol = c + dx[(d + 2) % 4];
            if (backRow < 0 || backCol < 0 || backRow >= N || backCol >= M || isWall[backRow][backCol]) {
                break;
            }
            r = backRow;
            c = backCol;
        }
        System.out.println(cleanedAreas);
    }
}
