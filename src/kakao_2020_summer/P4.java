package kakao_2020_summer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class P4 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[][]{{0,1,1},{0,0,0},{0,0,0}}));
    }

    private static class Solution {
        int[] dx = {0,0,-1,1};
        int[] dy = {-1,1,0,0};
        public int solution(int[][] board) {
            int answer = Integer.MAX_VALUE;
            int[][] price = new int[board.length][board.length];

            PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[2] - o2[2];
                }
            });

            for (int i = 0; i < board.length; i++) {
                Arrays.fill(price[i], Integer.MAX_VALUE);
            }

            pq.offer(new int[] {0,0,0,-1,-1}); // row, col, price, prevR, prevC
            price[0][0] = 0;

            while (!pq.isEmpty()) {
                int[] curr = pq.poll();

//                System.out.println(Arrays.toString(curr));

                if (curr[0] == board.length - 1 && curr[1] == board.length - 1) {
                    answer = Math.min(answer,curr[2]);
//                    break;
                }

                for (int di = 0; di < 4; di++) {
                    int nextRow = curr[0] + dy[di];
                    int nextCol = curr[1] + dx[di];
                    if (nextRow < 0 || nextCol < 0 || nextRow >= board.length || nextCol >= board.length) continue;
                    if (board[nextRow][nextCol] == 1) continue;
                    int nextPrice = 0;
                    if (curr[3] == -1) {
                        nextPrice = curr[2] + 100;
                    }
                    else {
                        if (Math.abs(curr[3] - curr[0]) == Math.abs(curr[0] - nextRow)) { // 직선
                            nextPrice = curr[2] + 100;
                        }
                        else {
                            nextPrice = curr[2] + 600;
                        }
                    }
                    if (price[nextRow][nextCol] >= nextPrice - 600) {
                        price[nextRow][nextCol] = Math.min(nextPrice, price[nextRow][nextCol]);
                        pq.offer(new int[] {nextRow, nextCol, nextPrice, curr[0], curr[1]});
                    }
                }

            }
            return answer;
        }
    }
}
