package bfs;

import java.util.*;

// 백준 2146 다리 만들기
public class B_2146 {

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int[][] map;
    static int n;
    static ArrayList<int[]>[] bounds = new ArrayList[5003];
    static int nIslands;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        initIslands();

        for (int i = 2; i < 2 + nIslands; i++) {
            findShortestBridge(i);
        }

        System.out.println(ans);
    }

    public static void findShortestBridge(int islandInd) {
        Queue<int[]> q = new LinkedList<>();
        for (int[] b : bounds[islandInd]) {
            q.offer(b);
        }
        boolean[][] visited = new boolean[n][n];
        int dist = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                if (visited[curr[0]][curr[1]]) continue;
                visited[curr[0]][curr[1]] = true;

                for (int di = 0; di < 4; di++) {
                    int nextR = curr[0] + dy[di];
                    int nextC = curr[1] + dx[di];
                    if (nextR >= 0 && nextC >= 0 && nextR < n && nextC < n) {
                        if (map[nextR][nextC] == 0) {
                            q.offer(new int[] {nextR, nextC});
                        }
                        else if (map[nextR][nextC] != islandInd) {
                            ans = Math.min(ans, dist);
                            return;
                        }
                    }
                }
            }
            dist++;
        }
    }

    public static void initIslands() {
        int islandsInd = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    setIslandNum(i, j, islandsInd++);
                    nIslands++;
                }
            }
        }
    }

    public static void setIslandNum(int row, int col, int islandInd) {
        map[row][col] = islandInd;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{row, col});
        while (!q.isEmpty()) {
            int[] curr = q.poll();

            for (int di = 0; di < 4; di++) {
                int nextR = curr[0] + dy[di];
                int nextC = curr[1] + dx[di];
                if (nextR >= 0 && nextC >= 0 && nextR < n && nextC < n) {
                    if (map[nextR][nextC] == 1) {
                        map[nextR][nextC] = islandInd;
                        q.offer(new int[]{nextR, nextC});
                    }
                    else if (map[nextR][nextC] == 0) {
                        if (bounds[islandInd] == null) bounds[islandInd] = new ArrayList<>();
                        bounds[islandInd].add(new int[] {curr[0], curr[1]});
                    }
                }
            }
        }
    }
}
