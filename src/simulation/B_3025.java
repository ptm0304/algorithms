package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_3025{
    static int[][] parent;

    static int find(int r, int c) {
        int p = parent[r][c];
        if (p == r) {
            return p;
        }
        return parent[r][c] = find(p, c);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        char[][] map = new char[r][c];
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        // 초기화 disjoint set
        parent = new int[r][c];
        for (int i = 0; i < c; i++) {
            int p = r - 1;
            for (int j = r - 1; j >= 0; j--) {
                if (map[j][i] == '.') {
                    parent[j][i] = p;
                }
                else {
                    parent[j][i] = j - 1;
                    p = j - 1;
                }
            }
        }

        // 시뮬레이션
        int nStones = Integer.parseInt(br.readLine());
        int[] commands = new int[nStones];
        for (int i = 0; i < nStones; i++) {
            commands[i] = Integer.parseInt(br.readLine()) - 1;
        }

        for (int command: commands) {
            int col = command;
            int row = find(0, col);
            while (true) {
                // 화산탄이 굳게 되는 경우
                if (row + 1 == r || map[row + 1][col] == 'X') {
                    map[row][col] = 'O';
                    parent[row][col] = row - 1;
                    if (parent[row - 1][col] >= row) {
                        parent[row - 1][col] = row - 1;
                    }
                    break;
                }
                else {
                    // 왼쪽으로 굴러 떨어지는 경우
                    if (col - 1 >= 0 && map[row][col - 1] == '.' && map[row + 1][col - 1] == '.') {
                        row = find(row + 1, col - 1);
                        col = col - 1;
                    }
                    // 오른쪽으로 굴러 떨어지는 경우
                    else if (col + 1 < c && map[row][col + 1] == '.' && map[row + 1][col + 1] == '.') {
                        row = find(row + 1, col + 1);
                        col = col + 1;
                    }
                    // 굳는 경우
                    else {
                        map[row][col] = 'O';
                        parent[row][col] = row - 1;
                        if (parent[row - 1][col] >= row) {
                            parent[row - 1][col] = row - 1;
                        }
                        break;
                    }
                }
            }
        }


        // 출력
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                out.append(map[i][j]);
            }
            out.append("\n");
        }
        System.out.println(out);
    }
//	}


}