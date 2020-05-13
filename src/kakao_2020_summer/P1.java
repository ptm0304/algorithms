package kakao_2020_summer;

public class P1 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[] {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5},
                "right"));
    }
    private static class Solution {
        public String solution(int[] numbers, String hand) {
            String answer = "";
            int[] lPos = {3, 0};
            int[] rPos = {3, 2};
            int[][] nInd = {{3, 1}, {0,0}, {0,1}, {0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2}};

            for (int i = 0; i < numbers.length; i++) {
                int curr = numbers[i];
                if (curr == 1 || curr == 4 || curr == 7) {
                    lPos = nInd[curr];
                    answer += "L";
                }
                else if (curr == 3 || curr == 6 || curr == 9) {
                    rPos = nInd[curr];
                    answer += "R";
                }
                else {
                    int[] nPos = nInd[curr];
                    int lDist = Math.abs(lPos[0]-nPos[0]) + Math.abs(lPos[1]-nPos[1]);
                    int rDist = Math.abs(rPos[0]-nPos[0]) + Math.abs(rPos[1]-nPos[1]);
                    if (lDist > rDist) {
                        rPos = nInd[curr];
                        answer += "R";
                    }
                    else if (lDist < rDist) {
                        lPos = nInd[curr];
                        answer += "L";
                    }
                    else {
                        if (hand.equals("right")) {
                            rPos = nInd[curr];
                            answer += "R";
                        }
                        else {
                            lPos = nInd[curr];
                            answer += "L";
                        }
                    }
                }
            }
            return answer;
        }
    }
}
