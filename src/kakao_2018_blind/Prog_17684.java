package kakao_2018_blind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// 카카오 2018 블라인드 [3차] 압축
public class Prog_17684 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution("KAKAO")));
        System.out.println(Arrays.toString(new Solution().solution("TOBEORNOTTOBEORTOBEORNOT")));
    }
    private static class Solution {
        public int[] solution(String msg) {
            ArrayList<Integer> answer = new ArrayList<>();
            HashMap<String, Integer> dict = new HashMap<>();
            char c = 'A';
            for (int i = 1; i <= 26; i++) {
                dict.put(String.valueOf(c++), i);
            }
            int ind = 27;
            for (int i = 0; i < msg.length();) {
                int out = 0;
                int cnt = 1;
                String in = msg.substring(i, i + cnt);
                while (dict.containsKey(in)) {
                    out = dict.get(in);
                    cnt++;
                    if (i + cnt > msg.length()) break;
                    in = msg.substring(i, i + cnt);
                }
                answer.add(out);
                dict.put(in, ind++);
                i+=(cnt-1);
            }

            int[] ans = new int[answer.size()];
            for (int i = 0; i < ans.length; i++) {
                ans[i] = answer.get(i);
            }
            return ans;
        }
    }
}

// [20, 15, 2, 5, 15, 18, 14, 15, 20, 27, 29, 31, 36, 30, 32, 34]
