package kakao_2018_blind;

// 카카오 2018 블라인드 [3차] n진수 게
public class Prog_17687 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(2,4,2,1));
        System.out.println(new Solution().solution(16,16,2,1));
        System.out.println(new Solution().solution(16,16,2,2));

    }

    private static class Solution {
        // 진법 n, 미리 구할 숫자의 갯수 t, 게임에 참가하는 인원 m, 튜브의 순서 p
        public String solution(int n, int t, int m, int p) {
            StringBuilder answer = new StringBuilder(t);
            String numStr = "0";
            int num = 0;
            while (t > 0) {
                for (int i = 0; i < m; i++) {
                    if (numStr.length() == 0) { // 새로운 번호
                        numStr = Integer.toString(++num, n);
                    }
                    String x = numStr.substring(0, 1); // 이번턴에 불러야할 숫자
                    numStr = numStr.substring(1);
                    if (i == p - 1) { // 튜브 차례
                        t--;
                        answer.append(x);
                    }
                }
            }
            return answer.toString().toUpperCase();
        }
    }
}
