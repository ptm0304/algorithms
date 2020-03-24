package past;

import java.util.ArrayList;
import java.util.Scanner;

public class B_17837 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int numChess = sc.nextInt();

        int[] dx = new int[] {1, -1, 0, 0};
        int[] dy = new int[] {0, 0, -1, 1};

        int[][] map = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        Chess[] chessInfo = new Chess[numChess + 1];
        int[][] chessMap = new int[size][size];

        for (int i = 1; i <= numChess; i++) {
            int row = sc.nextInt() - 1;
            int col = sc.nextInt() - 1;
            int dir = sc.nextInt() - 1;
            chessInfo[i] = new Chess(i, row, col, dir);
            chessMap[row][col] = i;
        }
        // 방향 0 부터 우좌상하
        // 0 흰색 1 빨간색 2 파란색


        int turn = 1;

        outer:
        while (turn <= 1000) {
            for (int i = 1; i <= numChess; i++) {
                Chess currChess = chessInfo[i];
                int nextRow = currChess.row + dy[currChess.dir];
                int nextCol = currChess.col + dx[currChess.dir];

                if (nextRow < 0 || nextRow >= size || nextCol < 0 || nextCol >= size ||
                        map[nextRow][nextCol] == 2) {
                    switch (currChess.dir) {
                        case 0:
                        case 2:
                            currChess.dir++;
                            break;
                        case 1:
                        case 3:
                            currChess.dir--;
                            break;
                    }
                    int nextRow1 = currChess.row + dy[currChess.dir];
                    int nextCol1 = currChess.col + dx[currChess.dir];
                    // 반대 방향이 끝이거나 파란색일시 방향만 돌리고 끝
                    if (nextRow1 >= 0 && nextCol1 >= 0 && nextRow1 < size &&
                            nextCol1 < size && map[nextRow1][nextCol1] != 2) {
                        nextRow = nextRow1;
                        nextCol = nextCol1;
                        if (map[nextRow][nextCol] == 1) {
                            if (currChess.below != null) {
                                currChess.below.above = null;
                                chessMap[currChess.row][currChess.col] = currChess.below.index;
                            } else {
                                chessMap[currChess.row][currChess.col] = 0;
                            }
                            currChess.below = null;

                            /////////////////////////원래 있던 판에서 이동

                            Chess trav = currChess;
                            ArrayList<Chess> list = new ArrayList<>();

                            while (trav != null) {
                                list.add(trav);
                                trav.row = nextRow;
                                trav.col = nextCol;
                                trav = trav.above;
                            }

                            currChess.above = null;
                            for (int x = 1; x < list.size(); x++) {
                                Chess curr = list.get(x);
                                curr.above = list.get(0);
                                list.get(x - 1).below = curr;
                            }
                            list.get(list.size() - 1).below = null;

                            if (chessMap[nextRow][nextCol] != 0) { // 맵에 이미 다른 말이 있을시
                                Chess other = chessInfo[chessMap[nextRow][nextCol]];
                                other.above = list.get(list.size() - 1);
                                list.get(list.size() - 1).below = other;
                            }
                            chessMap[nextRow][nextCol] = list.get(0).index;
                        }
                        else {
                            if (currChess.below != null) {
                                currChess.below.above = null;
                                chessMap[currChess.row][currChess.col] = currChess.below.index;
                            } else {
                                chessMap[currChess.row][currChess.col] = 0;
                            }
                            currChess.below = null;

                            Chess trav = currChess;

                            while (trav.above != null) {
                                trav.row = nextRow;
                                trav.col = nextCol;
                                trav = trav.above;
                            }
                            trav.row = nextRow;
                            trav.col = nextCol;

                            if (chessMap[nextRow][nextCol] != 0) { // 맵에 이미 다른 말이 있을시
                                Chess other = chessInfo[chessMap[nextRow][nextCol]];
                                other.above = currChess;
                                currChess.below = other;
                            }
                            chessMap[nextRow][nextCol] = trav.index;
                        }

                    }
                }
                else if (map[nextRow][nextCol] == 0) { // 흰색
                    if (currChess.below != null) {
                        currChess.below.above = null;
                        chessMap[currChess.row][currChess.col] = currChess.below.index;
                    } else {
                        chessMap[currChess.row][currChess.col] = 0;
                    }
                    currChess.below = null;

                    Chess trav = currChess;

                    while (trav.above != null) {
                        trav.row = nextRow;
                        trav.col = nextCol;
                        trav = trav.above;
                    }
                    trav.row = nextRow;
                    trav.col = nextCol;

                    if (chessMap[nextRow][nextCol] != 0) { // 맵에 이미 다른 말이 있을시
                        Chess other = chessInfo[chessMap[nextRow][nextCol]];
                        other.above = currChess;
                        currChess.below = other;
                    }
                    chessMap[nextRow][nextCol] = trav.index;
                }
                else if (map[nextRow][nextCol] == 1) { // 빨간색
                    if (currChess.below != null) {
                        currChess.below.above = null;
                        chessMap[currChess.row][currChess.col] = currChess.below.index;
                    } else {
                        chessMap[currChess.row][currChess.col] = 0;
                    }
                    currChess.below = null;

                    /////////////////////////원래 있던 판에서 이동

                    Chess trav = currChess;
                    ArrayList<Chess> list = new ArrayList<>();

                    while (trav != null) {
                        list.add(trav);
                        trav.row = nextRow;
                        trav.col = nextCol;
                        trav = trav.above;
                    }

                    currChess.above = null;
                    for (int x = 1; x < list.size(); x++) {
                        Chess curr = list.get(x);
                        curr.above = list.get(x - 1);
                        list.get(x - 1).below = curr;
                    }
                    list.get(list.size() - 1).below = null;

                    if (chessMap[nextRow][nextCol] != 0) { // 맵에 이미 다른 말이 있을시
                        Chess other = chessInfo[chessMap[nextRow][nextCol]];
                        other.above = list.get(list.size() - 1);
                        list.get(list.size() - 1).below = other;
                    }
                    chessMap[nextRow][nextCol] = list.get(0).index;
                }

                int cnt = 0;
                Chess trav = chessInfo[chessMap[currChess.row][currChess.col]];
                while (trav != null) {
                    cnt++;
                    trav = trav.below;
                }
                if (cnt >= 4) {
                    break outer;
                }
            }
            turn++;
        }
        System.out.println(turn > 1000 ? "-1" : turn);
    }
}

class Chess {
    int index;
    int row;
    int col;
    int dir;
    Chess above = null;
    Chess below = null;

    public Chess(int index, int row, int col, int dir) {
        this.index = index;
        this.row = row;
        this.col = col;
        this.dir = dir;
    }
}