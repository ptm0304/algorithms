package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B_16973 {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<int[]> walls = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (sc.nextInt() == 1) {
                    walls.add(new int[] {i, j});
                }
            }
        }

        int h = sc.nextInt() -1;
        int w = sc.nextInt() -1;
        int[] start = {sc.nextInt() - 1, sc.nextInt() - 1};
        int[] end = {sc.nextInt() - 1, sc.nextInt() - 1};

        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        boolean[][] visited = new boolean[n][m];
        int steps = -1;

        while (!q.isEmpty()) {
            int size = q.size();
            steps++;
            loop:
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                if (visited[curr[0]][curr[1]]) continue;
                visited[curr[0]][curr[1]] = true;
                for (int[] wall : walls) {
                    if (wall[0] >= curr[0] && wall[0] <= curr[0] + h &&
                    wall[1] >= curr[1] && wall[1] <= curr[1] + w) {
                        continue loop;
                    }
                }
                if (curr[0] == end[0] && curr[1] == end[1]) {
                    System.out.print(steps);
                    System.exit(0);
                }
                for (int di = 0; di < 4; di++) {
                    int nextRow = curr[0] + dy[di];
                    int nextCol = curr[1] + dx[di];
                    if (checkBounds(nextRow, nextCol, n, m) &&
                            checkBounds(nextRow + h, nextCol + w, n, m)) {
                        q.offer(new int[] {nextRow, nextCol});
                    }
                }
            }
        }
        System.out.println(-1);
    }

    static boolean checkBounds(int row, int col, int height, int width) {
        if (row >= 0 && row < height && col >= 0 && col < width) return true;
        return false;
    }
}



