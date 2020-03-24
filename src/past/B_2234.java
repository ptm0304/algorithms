package past;

import java.util.*;

public class B_2234 {
    final static int[] WALLS = {1, 2, 4, 8};
    final static int[] dx = {-1, 0, 1, 0};
    final static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] map = new int[m][n];
        int[][] rooms = new int[m][n];
        int[] roomSizes = new int[n * m + 1];
        int maxRoomSize = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        int roomIdx = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    int roomSize = 0;
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{i,j});
                    while(!q.isEmpty()) {
                        int[] curr = q.poll();
                        if (rooms[curr[0]][curr[1]] != 0) continue;
                        rooms[curr[0]][curr[1]] = roomIdx;
                        roomSize++;
                        for (int di = 0; di < 4; di++) {
                            if ((map[curr[0]][curr[1]] & WALLS[di]) == 0) {
                                int nextRow = curr[0] + dy[di];
                                int nextCol = curr[1] + dx[di];
                                if (nextRow >= 0 && nextCol >= 0 && nextRow < m && nextCol < n) {
                                    q.offer(new int[] {nextRow, nextCol});
                                }
                            }
                        }
                    }
                    maxRoomSize = Math.max(maxRoomSize, roomSize);
                    roomSizes[roomIdx] = roomSize;
                    roomIdx++;
                }
            }
        }

        int maxRoomRemove = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int di = 0; di < 4; di++) {
                    int nextRow = i + dy[di];
                    int nextCol = j + dx[di];
                    if (nextRow >= 0 && nextCol >=0 && nextRow < m && nextCol < n) {
                        if (rooms[nextRow][nextCol] != rooms[i][j]) {
                            maxRoomRemove = Math.max(maxRoomRemove, roomSizes[rooms[nextRow][nextCol]] +
                                    roomSizes[rooms[i][j]]);
                        }
                    }
                }
            }
        }
        System.out.println(roomIdx - 1);
        System.out.println(maxRoomSize);
        System.out.println(maxRoomRemove);
    }

}
