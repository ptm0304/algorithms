package kakao_2019_winter;

// 카카오 겨울 인턴십 튜플

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Prog_64065 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                new Solution().solution("{{1,2,3},{2,1},{1,2,4,3},{2}}")));
    }
    private static class Solution {
        public int[] solution(String s) {
            int[] answer = {};
            ArrayList<int[]> tupleList = toIntArrayList(s);
            tupleList.sort(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1.length - o2.length;
                }
            });

            answer = new int[tupleList.size()];
            boolean[] visited = new boolean[100001];
            for (int i = 0; i < tupleList.size(); i++) {
                int[] curr = tupleList.get(i);
                for (int x : curr) {
                    if (!visited[x]) {
                        answer[i] = x;
                        visited[x] = true;
                        break;
                    }
                }
            }
            return answer;
        }

        ArrayList<int[]> toIntArrayList(String s) {
            String[] strs = s.substring(1, s.length() - 1).split("},\\{");
            strs[0] = strs[0].substring(1);
            strs[strs.length - 1] = strs[strs.length - 1].substring(0, strs[strs.length - 1].length() - 1);

            ArrayList<int[]> ans = new ArrayList<>();

            for (String str : strs) {
                ans.add(toIntArray(str));
            }

            return ans;
        }

        int[] toIntArray(String s) {
            String[] strs = s.split(",");
            int[] ans = new int[strs.length];
            for (int i = 0; i < strs.length; i++) {
                ans[i] = Integer.parseInt(strs[i]);
            }
            return ans;
        }
    }
}
