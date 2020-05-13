package kakao_2018_blind;

//2018 KAKAO BLIND RECRUITMENT
//[1차] 비밀지도
public class Prog_17681 {
    private static class Solution {
        public String[] solution(int n, int[] arr1, int[] arr2) {
            String[] map = new String[n];

            for (int i = 0; i < n; i++) {
                int code = (arr1[i] | arr2[i]);
                String binum = Integer.toString(code, 2);
                map[i] = "";
                for (int j = 0; j < n - binum.length(); j++) {
                    map[i] += " ";
                }
                for (int j = 0; j < binum.length(); j++) {
                    map[i] += (binum.charAt(j) == '1')? "#" : " ";
                }

            }

            return map;
        }
    }
}
