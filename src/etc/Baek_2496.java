package etc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Baek_2496 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 너비
        int m = sc.nextInt(); // 높이
        int t = sc.nextInt(); // 광석수
        int k = sc.nextInt(); // 대각선 길이

        int[][] stones = new int[t][2];

//		for (pair<int,int> i : diamonds) { x = stone1.row, y = stone2.col
//			if(x <= i.first && i.first <= x + k && y <= i.second && i.second <= y + k) cnt++;
//		}

        for (int i = 0; i < t; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            stones[i] = new int[] { y, x };
        }

        int max = 1;
        int[] coords = { stones[0][1], stones[0][0] };

        int dx[] = {0, 1, 0, 1};
        int dy[] = {0, 0, 1, 1};

        for (int i = 0; i < t; i++) {
            int[] stone1 = stones[i]; // 기준돌1
            for (int j = 0; j < t; j++) {
                int[] stone2 = stones[j]; // 기준돌2

                int colDouble = stone1[1] - stone1[0] + stone2[1] + stone2[0]; // stone1's increasing + stone2's decreasing (x-coord)
                int rowDouble = stone1[0] - stone1[1] + stone2[0] + stone2[1]; // stone1's increasing + stone2's decreasing (y-coord)
                if (colDouble % 2 == 1 || rowDouble % 2 == 1) {
                    for (int a = 0; a < 4; a++) {
                        int standardCol = colDouble / 2 + dx[a];
                        int standardRow = rowDouble / 2 + dy[a];
                        int increasingSlope1 = standardCol - standardRow; // 작은 슬로프
                        int increasingSlope2 = increasingSlope1 + k; // 큰 슬로프
                        int decreasingSlope1 = standardCol + standardRow;
                        int decreasingSlope2 = decreasingSlope1 + k;

                        int cnt = 0;

                        for (int x = 0; x < t; x++) {
                            int[] target = stones[x];
                            int currIncreasingSlope = target[1] - target[0];
                            int currDecreasingSlope = target[1] + target[0];
                            if (currIncreasingSlope >= increasingSlope1 && currIncreasingSlope <= increasingSlope2
                                    && currDecreasingSlope >= decreasingSlope1 && currDecreasingSlope <= decreasingSlope2) {
                                cnt++;
                            }
                        }

                        if (cnt > max) {
                            int col = standardCol + k /2;
                            int row = standardRow;
                            coords[0] = row;
                            coords[1] = col;
                            max = cnt;
                        }
                    }
                }
                else {
                    int standardCol = colDouble / 2;
                    int standardRow = rowDouble / 2;
                    int increasingSlope1 = standardCol - standardRow; // 작은 슬로프
                    int increasingSlope2 = increasingSlope1 + k; // 큰 슬로프
                    int decreasingSlope1 = standardCol + standardRow;
                    int decreasingSlope2 = decreasingSlope1 + k;

                    int cnt = 0;

                    for (int x = 0; x < t; x++) {
                        int[] target = stones[x];
                        int currIncreasingSlope = target[1] - target[0];
                        int currDecreasingSlope = target[1] + target[0];
                        if (currIncreasingSlope >= increasingSlope1 && currIncreasingSlope <= increasingSlope2
                                && currDecreasingSlope >= decreasingSlope1 && currDecreasingSlope <= decreasingSlope2) {
                            cnt++;
                        }
                    }

                    if (cnt > max) {
                        int col = standardCol + k /2;
                        int row = standardRow;
                        coords[0] = row;
                        coords[1] = col;
                        max = cnt;
                    }
                }
            }
        }
        System.out.println(coords[1] + " " + coords[0]);
        System.out.println(max);
    }
}
