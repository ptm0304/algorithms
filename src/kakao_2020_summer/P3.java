package kakao_2020_summer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class P3 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(new String[]
                {"ZZZ", "YYY", "NNNN", "YYY", "BBB"})));
    }
    private static class Solution {
        HashSet<String> totalGems;
        public int[] solution(String[] gems) {
            int[] answer = {1, gems.length};

            totalGems = new HashSet<>();
            for (String g: gems) {
                totalGems.add(g);
            }

            int min = Integer.MAX_VALUE;

            int low = totalGems.size();
            int high = gems.length;

            while (low <= high) {
                int mid = (low + high) / 2;

                HashMap<String, Integer> map = new HashMap<>();

                boolean possible = false;
                for (int i = 0; i < gems.length; i++) {
                    if (map.keySet().size() == totalGems.size()) {
                        possible = true;
                        if (min > mid) {
                            answer = new int[] {i - mid + 1 , i};
                        }
                        break;
                    }
                    if (map.containsKey(gems[i])) {
                        map.put(gems[i], map.get(gems[i]) + 1);
                    }
                    else {
                        map.put(gems[i], 1);
                    }
                    if (i - mid >= 0) {
                        map.put(gems[i - mid], map.get(gems[i - mid]) - 1);
                        if (map.get(gems[i - mid]) == 0) {
                            map.remove(gems[i - mid]);
                        }
                    }
                }
                if (possible) {
                    min = Math.min(min, mid);
                    high = mid - 1;
                }
                else {
                    low = mid + 1;
                }
            }

            return answer;
        }
    }
}
