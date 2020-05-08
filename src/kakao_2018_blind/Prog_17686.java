package kakao_2018_blind;

import java.util.Arrays;
import java.util.Comparator;

// 카카오 2018 블라인드 기출 [3차] 파일명 정렬
public class Prog_17686 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(new String[]
                {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"})));
    }
    private static class Solution {
        public String[] solution(String[] files) {
            Arrays.sort(files, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    int[] ind1 = getNumInd(o1);
                    int[] ind2 = getNumInd(o2);
                    if (o1.substring(0, ind1[0]).toLowerCase().
                            compareTo(o2.substring(0, ind2[0]).toLowerCase()) != 0) {
                        return o1.substring(0, ind1[0]).toLowerCase().
                                compareTo(o2.substring(0, ind2[0]).toLowerCase());
                    }
                    int i1 = 0, i2 = 0;
                    if (ind1[0] < o1.length()) {
                        i1 = Integer.parseInt(o1.substring(ind1[0], ind1[1]));
                    }
                    if (ind2[0] < o2.length()) {
                        i2 = Integer.parseInt(o2.substring(ind2[0], ind2[1]));
                    }
                    return i1 - i2;
                }
            });
            return files;
        }
        int[] getNumInd(String s) {
            int start = s.length();
            for (int i = 0; i < s.length(); i++) {
                if (Character.isDigit(s.charAt(i))) {
                    start = i;
                    break;
                }
            }
            int end = start;
            while (end < s.length() && Character.isDigit(s.charAt(end))){
                end++;
            }
            return new int[]{start, end};
        }
    }
}
