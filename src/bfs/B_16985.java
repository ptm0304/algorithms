package bfs;

import java.util.*;

public class B_16985 {
    static int[][][] original_maze;
    static int[][][] maze;
    static int ans = Integer.MAX_VALUE;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[] dz = {-1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        original_maze = new int[5][5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    original_maze[i][j][k] = sc.nextInt();
                }
            }
        }

        permOrder(0, new int[5], new boolean[5]);

        System.out.print(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    // 판 놓는 순서
    static void permOrder(int ind, int[] order, boolean[] visited) {
        if (ans == 12) return;
        if (ind == 5) {
            maze = new int[5][5][5];
            for (int i = 0; i < 5; i++) {
                maze[i] = original_maze[order[i]];
                perm(0);
            }
            return;
        }
        for (int i = 0; i < 5; i++) {
            if (visited[i]) continue;
            order[ind] = i;
            visited[i] = true;
            permOrder(ind + 1, order, visited);
            visited[i] = false;
        }
    }

    // 판 회전하는 모든 경우의 수
    static void perm(int mazeInd) {
        if (mazeInd == 5) {
            if (maze[0][0][0] == 0) return; // 입구가 막혔을 경우 바로 리턴한다
            boolean[][][] visited = new boolean[5][5][5];
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[] {0, 0, 0});

            // 만들어 3차원 미로에 대한 bfs
            int step = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] curr = q.poll();
                    if (curr[0] == 4 && curr[1] == 4 && curr[2] == 4) {
                        ans = Math.min(ans, step);
                        break;
                    }

                    for (int di = 0; di < 4; di++) {
                        int nextRow = curr[1] + dy[di];
                        int nextCol = curr[2] + dx[di];
                        if (nextRow < 5 && nextCol < 5 && nextRow >= 0 && nextCol >= 0
                                && maze[curr[0]][nextRow][nextCol] == 1 &&
                                !visited[curr[0]][nextRow][nextCol]) {
                            q.offer(new int[] {curr[0], nextRow, nextCol});
                            visited[curr[0]][nextRow][nextCol] = true;
                        }
                    }

                    for (int di = 0; di < 2; di++) {
                        int nextZ = curr[0] + dz[di];
                        if (nextZ >= 0 && nextZ < 5 && !visited[nextZ][curr[1]][curr[2]] &&
                           maze[nextZ][curr[1]][curr[2]] == 1) {
                            q.offer(new int[] {nextZ, curr[1], curr[2]});
                            visited[nextZ][curr[1]][curr[2]] = true;
                        }
                    }
                }
                step++;
            }
            return;
        }
        perm(mazeInd + 1);
        for (int i = 0; i < 3; i++) {
            maze[mazeInd] = rotate(mazeInd);
            perm(mazeInd + 1);
        }
    }

    static int[][] rotate(int mazeInd) {
        int[][] rotated = new int[5][5];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                stack.push(maze[mazeInd][j][i]);
            }
            for (int j = 0; j < 5; j++) {
                rotated[i][j] = stack.pop();
            }
        }
        return rotated;
    }

    static void printMaze() {
        System.out.println("maze: ");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    System.out.print(maze[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
