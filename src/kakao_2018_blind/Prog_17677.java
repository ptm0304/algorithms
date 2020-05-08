package kakao_2018_blind;

import java.util.HashMap;

// 카카오 2018 블라인드 채용 [1차] 뉴스 클러스터링
public class Prog_17677 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution("FRANCE", "french"));
    }

    private static class Solution {
        HashMap<String, Integer> str1Map;
        HashMap<String, Integer> str2Map;
        public int solution(String str1, String str2) {
            str1 = str1.toLowerCase();
            str2 = str2.toLowerCase();

            str1Map = new HashMap<>();
            str2Map = new HashMap<>();

            initMap(str1, str1Map);
            initMap(str2, str2Map);

            final int[] intersection = new int[1];
            final int[] union = new int[1];
            str1Map.keySet().stream().forEach(k -> {
                if (str2Map.containsKey(k)) {
                    if (str2Map.get(k) > str1Map.get(k)) {
                        intersection[0] += str1Map.get(k);
                    }
                    else if (str2Map.get(k) < str1Map.get(k)) {
                        union[0] += str1Map.get(k);
                    }
                }
                else {
                    union[0] += str1Map.get(k);
                }
            });

            str2Map.keySet().stream().forEach(k -> {
                if (str1Map.containsKey(k)) {
                    if (str1Map.get(k) > str2Map.get(k)) {
                        intersection[0] += str2Map.get(k);
                    }
                    else if (str1Map.get(k) < str2Map.get(k)){
                        union[0] += str2Map.get(k);
                    }
                    else {
                        intersection[0] += str2Map.get(k);
                        union[0] += str2Map.get(k);
                    }
                }
                else {
                    union[0] += str2Map.get(k);
                }
            });

            return (intersection[0] == union[0])? 65536 : (int)(((double) intersection[0] / union[0]) * 65536);
        }

        public void initMap(String str, HashMap<String, Integer> map) {
            for (int i = 0; i < str.length() - 1; i++) {
                String substr = str.substring(i, i + 2);
                if (!substr.matches("[a-zA-Z]{2}")) continue;
                if (map.containsKey(substr)) {
                    map.put(substr, map.get(substr) + 1);
                }
                else {
                    map.put(substr, 1);
                }
            }
        }
    }

}
