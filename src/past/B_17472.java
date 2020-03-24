package past;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class B_17472 {
    static ArrayList<int[]> edges = new ArrayList<>();
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // find islands
        int islandIndex = 2; // starts from 2
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[] {i, j});
                    boolean[][] visited = new boolean[N][M];
                    while (!q.isEmpty()) {
                        int[] curr = q.poll();
                        if (visited[curr[0]][curr[1]]) continue;
                        visited[curr[0]][curr[1]] = true;
                        map[curr[0]][curr[1]] = islandIndex;

                        for (int di = 0; di < 4; di++) {
                            int nextRow = curr[0] + dy[di];
                            int nextCol = curr[1] + dx[di];
                            if (nextRow >= 0 && nextCol >= 0 && nextRow < N && nextCol < M &&
                                    map[nextRow][nextCol] == 1) {
                                q.offer(new int[] {nextRow, nextCol});
                            }
                        }
                    }
                    islandIndex++;
                }
            }
        }

//        for (int i = 0; i < map.length; i++) {
//            for (int j = 0; j < map[i].length; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }

        int[][] minBridge = new int[islandIndex][islandIndex ];
        for (int i = 0; i < islandIndex; i++) {
            for (int j = 0; j < islandIndex; j++) {
                minBridge[i][j] = Integer.MAX_VALUE;
            }
        }

        // save edges
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] != 0) {
                    int currIsland = map[r][c];
                    for (int di = 0; di < 4; di++) {
                        int nextRow = r + dy[di];
                        int nextCol = c + dx[di];

                        if (nextRow >= 0 && nextCol >= 0 && nextRow < N && nextCol < M &&
                                map[nextRow][nextCol] == 0) {
                            for (int dist = 2; dist < Math.max(N, M); dist++) {
                                int destRow = r + dy[di] * dist;
                                int destCol = c + dx[di] * dist;

                                if (destRow < 0 || destCol < 0 || destRow >= N || destCol >= M) break;
                                if (map[destRow][destCol] !=0) {
                                    if (dist == 2 || map[destRow][destCol] == currIsland) {
                                        break;
                                    }
                                    minBridge[currIsland][map[destRow][destCol]] = Math.min(
                                            minBridge[currIsland][map[destRow][destCol]], dist - 1
                                    );
                                    edges.add(new int[] {dist - 1, currIsland, map[destRow][destCol]});
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        edges.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] > o2[0]) {
                    return 1;
                }
                else if (o1[0] < o2[0]) {
                    return -1;
                }
                return 0;
            }
        });

//        for (int[] edge : edges) {
//            System.out.println(Arrays.toString(edge));
//        }

        int nBridges = 0;
        int cost = 0;

        mst:
        for (int[] currBridge : edges) {
            if (minBridge[currBridge[1]][currBridge[2]] == -1) continue;
            // check if they're in the same group
            boolean[] visited = new boolean[islandIndex];
            Queue<Integer> q = new LinkedList<>();
            q.offer(currBridge[1]);

            while (!q.isEmpty()) {
                int currIsland = q.poll();
                if (visited[currIsland]) continue;
                visited[currIsland] = true;

                for (int i = 2; i < islandIndex; i++) {
                    if (minBridge[currIsland][i] == -1) {
                        q.offer(i);
                    }
                }
            }

            boolean[] visited2 = new boolean[islandIndex];
            q = new LinkedList<>();
            q.offer(currBridge[2]);
            while (!q.isEmpty()) {
                int currIsland = q.poll();
                if (visited[currIsland]) continue mst;
                if (visited2[currIsland]) continue;
                visited2[currIsland] = true;

                for (int i = 2; i < islandIndex; i++) {
                    if (minBridge[currIsland][i] == -1) {
                        q.offer(i);
                    }
                }
            }

            nBridges++;
//            System.out.println("bridge: " + minBridge[currBridge[1]][currBridge[2]] + ", data: " +
//                    currBridge[1] + "-" + currBridge[2]);
            cost += minBridge[currBridge[1]][currBridge[2]];
            minBridge[currBridge[1]][currBridge[2]] = -1;
            minBridge[currBridge[2]][currBridge[1]] = -1;
        }

        if (nBridges == islandIndex - 3) System.out.println(cost);
        else System.out.println(-1);

    }
}
