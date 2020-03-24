package bfs;

import java.util.*;

public class Baduuuk2 {
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static ArrayList<Integer> groupList1 = new ArrayList<>(); // 하나로 막히는 경우
    static int group2Max = 0;

    static boolean[][] visited;
    static int nRow;
    static int nCol;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        nRow = sc.nextInt();
        nCol = sc.nextInt();

        visited = new boolean[nRow][nCol];
        map = new int[nRow][nCol];
        ArrayList<int[]> enemyCoords = new ArrayList<>();
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nCol; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 2) {
                    enemyCoords.add(new int[] {i, j});
                }
            }
        }

        for (int[] enemy : enemyCoords) {
            for (int di = 0; di < 4; di++) {
                int neighborRow = enemy[0] + dy[di];
                int neighborCol = enemy[1] + dx[di];
                if (neighborRow >= 0 && neighborCol >= 0 && neighborCol < nCol && neighborRow < nRow &&
                        map[neighborRow][neighborCol] == 0) {
                    bfs(new int[] {neighborRow, neighborCol}, 3);
                }
            }
        }

        Collections.sort(groupList1);
        int max1 = 0;
        if (groupList1.size() > 1) {
            max1 = groupList1.get(groupList1.size() - 1) + groupList1.get(groupList1.size() - 2);
        }
        else if (groupList1.size() == 1) {
            max1 = groupList1.get(0);
        }

        System.out.println(Math.max(max1, group2Max));
    }

    static int bfs(int[] start, int steps) {
        ArrayList<Integer> groupTwo = new ArrayList<>(); // start외에 하나 더 막아야 막히는 경우
        int cntDeads = 0; // start 하나만 막았을때 죽는 상대편 수

        map[start[0]][start[1]] = steps;
        for (int di = 0; di < 4; di++) {
            int neighborRow = start[0] + dy[di];
            int neighborCol = start[1] + dx[di];
            if (neighborRow >= 0 && neighborCol >= 0 && neighborCol < nCol && neighborRow < nRow &&
                    map[neighborRow][neighborCol] == 2) {
                Queue<int[]> q = new LinkedList<int[]>();

                int cnt = 0;
                q = new LinkedList<int[]>();
                q.offer(new int[] {neighborRow, neighborCol});
                boolean explode = false;

                int cntZeros = 0;

                while (!q.isEmpty()) {
                    int[] curr = q.poll();
                    if (visited[curr[0]][curr[1]]) continue;
                    if (map[curr[0]][curr[1]] == 2) {
                        if (!explode) {
                            cnt++;
                        }

                        visited[curr[0]][curr[1]] = true;
                        for (int di3 = 0; di3 < 4; di3++) {
                            int nextRow = curr[0] + dy[di3];
                            int nextCol = curr[1] + dx[di3];
                            if (nextRow < 0 || nextCol < 0 ||
                                    nextRow >= nRow || nextCol >= nCol ||
                                    map[nextRow][nextCol] == 1) {
                                continue;
                            }
                            q.offer(new int[] {nextRow, nextCol});
                        }
                    }
                    else if (map[curr[0]][curr[1]] == 0) {
                        cntZeros++;
                        if (cntZeros > 1) {
                            cnt = 0;
                            explode = true;
                        }
                        else {
                            cnt += bfs(curr, steps + 1);
                        }
                    }
                    else if (map[curr[0]][curr[1]] != 1 && map[curr[0]][curr[1]] < steps - 1) {
                        cntZeros++;
                        if (cntZeros > 1) {
                            cnt = 0;
                            explode = true;
                        }
                    }
                }
                if (!explode) {
                    if (cntZeros == 0) { // start 한개로만 막힌 경우
                        cntDeads += cnt;
                    }
                    else { // 두개 막았을때
                        groupTwo.add(cnt);
                    }
                }
            }
        }
        if (cntDeads > 0) groupList1.add(cntDeads);
        for (Integer deadsTwo : groupTwo) {
            group2Max = Math.max(group2Max, deadsTwo + cntDeads);
        }
        return cntDeads;
    }

}