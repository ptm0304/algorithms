package past;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Baek_2496 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // 너비
        int m = sc.nextInt();  // 높이
        int t = sc.nextInt();  // 광석수
        int k = sc.nextInt();  // 대각선 길이

        int[][] stones = new int[t][2];

        for (int i = 0; i < t; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            stones[i] = new int[] {y, x};
        }

        Arrays.sort(stones, new Comparator<int[]>() {
            @Override
            //arguments to this method represent the arrays to be sorted
            public int compare(int[] o1, int[] o2) {
                //get the item ids which are at index 0 of the array
                Integer itemIdOne = o1[1];
                Integer itemIdTwo = o2[1];
                // sort on item id
                return itemIdOne.compareTo(itemIdTwo);
            }
        });

        int max = 0;
        int[] coords = {0, 0};
        boolean[] visited = new boolean[100];

        outer:
        for (int i = 0; i < t; i++) {
            if (visited[i]) continue;
            int[] stone = stones[i];
            visited[i] = true;
            int increasingSlope1 = stone[1] - stone[0]; // 더 작은 값
            int increasingSlope2 = increasingSlope1 + k;
            int increasingSlope0 = increasingSlope1 - k; // 더더 작은
            for (int j = 0; j < k + 1; j += 2) {
                int decreasingSlope1 = stone[1] + stone[0] - j; // 더 작은 값
                int decreasingSlope2 = decreasingSlope1 + k;
                if (decreasingSlope1 < 0) break;
                int cnt1 = 1;
                int cnt2 = 1;
                for (int x = 0; x < stones.length; x++) {
                    if (x == i) continue;
                    int[] stoneToCheck = stones[x];
                    int currIncreasingSlope = stoneToCheck[1] - stoneToCheck[0];
                    int currDecreasingSlope = stoneToCheck[1] + stoneToCheck[0];
                    if (currIncreasingSlope >= increasingSlope1 && currIncreasingSlope <= increasingSlope2 &&
                            currDecreasingSlope >= decreasingSlope1 && currDecreasingSlope <= decreasingSlope2) {
                        visited[x] = true;
                        cnt1++;
                    }
                    if (currIncreasingSlope >= increasingSlope0 && currIncreasingSlope <= increasingSlope1 &&
                            currDecreasingSlope >= decreasingSlope1 && currDecreasingSlope <= decreasingSlope2) {
                        cnt2++;
                        visited[x] = true;
                    }
                }
                if (max < cnt1) {
                    max = cnt1;
                    coords[0] = stone[0] - j / 2; // y 좌표
                    coords[1] = stone[1] - j / 2 + k / 2; // x 좌표
                    if (cnt1 == t) {
                        break outer;
                    }
                }
                if (max < cnt2) {
                    max = cnt2;
                    coords[1] = stone[1] - j / 2; // x 좌표
                    coords[0] = stone[0] - j / 2 + k / 2; // y 좌표
                    if (cnt2 == t) {
                        break outer;
                    }
                }
            }

            int decreasingSlope1 = stone[1] + stone[0]; // 더 작은 값
            int decreasingSlope2 = increasingSlope1 + k;
            int decreasingSlope0 = increasingSlope1 - k; // 더더 작은
            for (int j = 0; j < k + 1; j += 2) {
                int increasingSlope01 = stone[1] - stone[0] - j; // 더 작은 값
                int increasingSlope02 = decreasingSlope1 + k;
                if (decreasingSlope1 < 0) break;
                int cnt1 = 1;
                int cnt2 = 1;
                for (int x = 0; x < stones.length; x++) {
                    if (x == i) continue;
                    int[] stoneToCheck = stones[x];
                    int currIncreasingSlope = stoneToCheck[1] - stoneToCheck[0];
                    int currDecreasingSlope = stoneToCheck[1] + stoneToCheck[0];
                    if (currIncreasingSlope >= increasingSlope01 && currIncreasingSlope <= increasingSlope02 &&
                            currDecreasingSlope >= decreasingSlope1 && currDecreasingSlope <= decreasingSlope2) {
                        visited[x] = true;
                        cnt1++;
                    }
                    if (currIncreasingSlope >= increasingSlope01 && currIncreasingSlope <= increasingSlope02 &&
                            currDecreasingSlope >= decreasingSlope0 && currDecreasingSlope <= decreasingSlope1) {
                        cnt2++;
                        visited[x] = true;
                    }
                }
                if (max < cnt1) {
                    max = cnt1;
                    coords[0] = stone[0] + j / 2; // y 좌표
                    coords[1] = stone[1] - j / 2 + k / 2; // x 좌표
                    if (cnt1 == t) {
                        break outer;
                    }
                }
                if (max < cnt2) {
                    max = cnt2;
                    coords[0] = stone[0] + j / 2 - k / 2; // y 좌표
                    coords[1] = stone[1] - j / 2; // x 좌표
                    if (cnt2 == t) {
                        break outer;
                    }
                }
            }
        }
        System.out.println(coords[1] + " " + coords[0]);
        System.out.println(max);
    }
}
