package past;

import java.util.PriorityQueue;
import java.util.Scanner;

public class B_16236 {
    static int n;
    static int[][] map;
    static int sharkSize = 2;
    static int time = 0;
    final static int[] sharkPos = new int[2];
    static int cntEat = 0;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 9) {
                    sharkPos[0] = i;
                    sharkPos[1] = j;
                    map[i][j] = 0;
                }
            }
        }

        outer:
        while (true) {
            PriorityQueue<Location> q = new PriorityQueue<>();
            q.offer(new Location(sharkPos[0], sharkPos[1], 0));
            boolean visited[][] = new boolean[n][n];
            while (!q.isEmpty()) {
                Location curr = q.poll();
                if (visited[curr.row][curr.col]) continue;
                if (map[curr.row][curr.col] > sharkSize) continue;
                if (map[curr.row][curr.col] != 0 && map[curr.row][curr.col] < sharkSize) {
                    cntEat++;
                    if (sharkSize == cntEat) {
                        sharkSize++;
                        cntEat = 0;
                    }
                    sharkPos[0] = curr.row;
                    sharkPos[1] = curr.col;
                    time += curr.time;
                    map[curr.row][curr.col] = 0;
                    continue outer;
                }
                visited[curr.row][curr.col] = true;
                for (int di = 0; di < 4; di++) {
                    int nextRow = curr.row + dy[di];
                    int nextCol = curr.col + dx[di];
                    if (nextRow >= 0 && nextCol >=0 && nextRow < n && nextCol < n) {
                        q.offer(new Location(nextRow, nextCol, curr.time + 1));
                    }
                }
            }
            break;
        }
        System.out.println(time);
    }
    static class Location implements Comparable<Location> {
        int row, col, time;

        public Location(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }

        @Override
        public int compareTo(Location o) {
            if (o.time < this.time) {
                return 1;
            }
            else if (o.time > this.time) {
                return -1;
            }
            else {
                if (this.row > o.row) return 1;
                else if (this.row < o.row) return -1;
                else {
                    if (this.col > o.col) return 1;
                    else return -1;
                }
            }
        }
    }
}
