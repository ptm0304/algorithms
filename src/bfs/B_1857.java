package bfs;

import java.util.PriorityQueue;
import java.util.Scanner;

public class B_1857 {
    static int dx[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
    static int dy[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
    static int[][] map;
    static long[][][] memo;
    static boolean[][] visited;
    static PriorityQueue<Helper> pq;
    static boolean foundDest;
    static int numCushions;
    static int n;
    static int m;
    static int qSize;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];
        memo = new long[n][m][n * m];


        Helper start = new Helper(0,0,0);
        int[] dest = new int[2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 3) {
                    start = new Helper(i, j, 0);
                    memo[i][j][0] = 1;
                }
                else if (map[i][j] == 4) {
                    dest = new int[]{i,j};
                }
            }
        }

        pq = new PriorityQueue<>();
        pq.offer(start);
        visited = new boolean[n][m];
        foundDest = false;
        numCushions = 0;
        map[start.row][start.col] = 1;

        while (!pq.isEmpty()) {
            qSize = pq.size();
            if (foundDest) break;
            for (int i = 0; i < qSize; i++) {
                Helper curr = pq.poll();
                boolean[][] sVisited = new boolean[n][m];
                if (map[start.row][start.col] == 1) sVisited[start.row][start.col] = true;
                for (int di = 0; di < 8; di++) {
                    int nextRow = curr.row + dy[di];
                    int nextCol = curr.col + dx[di];
                    if (nextRow >= 0 && nextCol >= 0 && nextRow < n && nextCol < m) {
                        if (map[nextRow][nextCol] == 4) {
                            memo[nextRow][nextCol][curr.nCushions + 1] += memo[curr.row][curr.col][curr.nCushions];
                            foundDest = true;
                            numCushions = curr.nCushions;
                        }
                        else if (map[nextRow][nextCol] == 1) {
                            findNeighbors(sVisited, nextRow, nextCol, curr.nCushions, memo[curr.row][curr.col][curr.nCushions]);
                        }
                        else if (map[nextRow][nextCol] == 0) {
                            memo[nextRow][nextCol][curr.nCushions + 1] += memo[curr.row][curr.col][curr.nCushions];
                            if (!visited[nextRow][nextCol]){
                                visited[nextRow][nextCol] = true;
                                pq.offer(new Helper(nextRow, nextCol, curr.nCushions + 1));
                            }
                        }
                    }
                }
            }
        }

        if (!foundDest) System.out.println(-1);
        else {
            System.out.println(numCushions);
            System.out.println(memo[dest[0]][dest[1]][numCushions + 1]);
        }
    }

    static void findNeighbors(boolean[][] sVisited, int row, int col, int nCushions, long nCases) {
        if (sVisited[row][col]) return;
        sVisited[row][col] = true;

        for (int di = 0; di < 8; di++) {
            int nextRow = row + dy[di];
            int nextCol = col + dx[di];
            if (nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= m) continue;
            if (map[nextRow][nextCol] == 1) {
                findNeighbors(sVisited, nextRow, nextCol, nCushions, nCases);
            }
            if (map[nextRow][nextCol] == 0 && !sVisited[nextRow][nextCol]) {
                memo[nextRow][nextCol][nCushions + 1] += nCases;
                sVisited[nextRow][nextCol] = true;
                if (!visited[nextRow][nextCol]){
                    visited[nextRow][nextCol] = true;
                    pq.offer(new Helper(nextRow, nextCol, nCushions + 1));
                }
            }
            if (map[nextRow][nextCol] == 4) {
                memo[nextRow][nextCol][nCushions + 1] += nCases;
                foundDest = true;
                numCushions = nCushions;
            }
        }
    }
    static class Helper implements Comparable<Helper> {
        int nCushions;
        int row, col;

        public Helper(int row, int col, int nCushions) {
            this.row = row;
            this.col = col;
            this.nCushions = nCushions;
        }


        @Override
        public int compareTo(Helper o) {
            return this.nCushions - o.nCushions;
        }
    }
}
