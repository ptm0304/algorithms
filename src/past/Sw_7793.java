package past;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sw_7793 {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nt = Integer.parseInt(br.readLine());
        outer:
        for (int tc = 1; tc <= nt; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            char[][] map = new char[n][m];
            int[] pos = {};
            ArrayList<int[]> demonPos = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String line = br.readLine();
                for (int j = 0; j < m; j++) {
                    map[i][j] = line.charAt(j);
                    if (map[i][j] == 'S') {
                        map[i][j] = '.';
                        pos = new int[] {i, j};
                    }
                    else if (map[i][j] == '*') {
                        demonPos.add(new int[] {i, j});
                    }
                }
            }
            Queue<int[]> q = new LinkedList<>();
            q.offer(pos);
            boolean[][] visited = new boolean[n][m];
            int time = 0;
            System.out.print("#" + tc + " ");
            while(!q.isEmpty()) {
                ArrayList<int[]> temp = new ArrayList<>();
                for (int i = 0; i < demonPos.size(); i++) {
                    int dr = demonPos.get(i)[0];
                    int dc = demonPos.get(i)[1];
                    for (int di = 0; di < 4; di++) {
                        int nr = dr + dy[di];
                        int nc = dc + dx[di];
                        if (nr >= 0 && nc >= 0 && nr < n && nc < m && map[nr][nc] == '.'){
                            map[nr][nc] = '*';
                            temp.add(new int[] {nr, nc});
                        }
                    }
                }
                demonPos = temp;

                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] curr = q.poll();
                    if (map[curr[0]][curr[1]] == 'D') {
                        System.out.println(time);
                        continue outer;
                    }
                    if (visited[curr[0]][curr[1]]) continue;
                    visited[curr[0]][curr[1]] = true;
                    for (int di = 0; di < 4; di++) {
                        int nextRow = curr[0] + dy[di];
                        int nextCol = curr[1] + dx[di];
                        if (nextRow >= 0 && nextCol >= 0 && nextRow < n && nextCol < m &&
                                (map[nextRow][nextCol] == '.' || map[nextRow][nextCol] == 'D')) {
                            q.offer(new int[] {nextRow, nextCol});
                        }
                    }

                }
                time++;
            }
            System.out.println("GAME OVER");
        }
    }

    static void printMap(char[][] map) {
        for (int i = 0; i < map.length; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
}
