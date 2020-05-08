package kakao_2018_blind;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// 카카오 2018 블라인드 [3차] 방금그곡 KMP 풀이
public class Prog_17683 {
    public static void main(String[] args) throws ParseException {
        System.out.println(new Solution().solution("ABC", new String[] {
                "12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"
        }));
    }
    private static class Solution {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        public String solution(String m, String[] musicinfos) throws ParseException {
            String answer = "";
            long ansDur = 0;

            m = formatMusic(m);

            for (int i = 0; i < musicinfos.length; i++) {
                String[] curr = musicinfos[i].split(",");
                Date start = formatter.parse(curr[0]);
                Date end = formatter.parse(curr[1]);
                long duration = end.getTime() / 60000 - start.getTime() / 60000;
                StringBuilder actualMusic = new StringBuilder();
                int ind = 0;

                String formattedMusic = formatMusic(curr[3]);
                for (int j = 0; j < duration; j++) {
                    if (ind == formattedMusic.length()) ind = 0;
                    actualMusic.append(formattedMusic.charAt(ind++));
                }

                int n = m.length();
                int[] table = new int[n];

                int j = 0;
                for (int k = 1; k < n; k++) {
                    while (j > 0 && m.charAt(j) != m.charAt(k)) {
                        j = table[j - 1];
                    }
                    if ( m.charAt(k) ==  m.charAt(j)) {
                        table[k] = ++j;
                    }
                }

                j = 0;

                outer:
                for (int k = 0; k < actualMusic.length(); k++) {
                    while (j > 0 &&  m.charAt(j) !=  actualMusic.charAt(k)) {
                        j = table[j - 1];
                    }
                    if ( m.charAt(j) ==  actualMusic.charAt(k)) {
                        if (j == n - 1) {
                            if (ansDur < duration) {
                                answer = curr[2];
                                ansDur = duration;
                            }
                            break outer;
                        }
                        else {
                            j++;
                        }
                    }
                }

            }
            if (answer.equals("")) answer = "(None)";
            return answer;
        }

        String formatMusic(String m) {
            m =m.replaceAll("C#", "I");
            m =m.replaceAll("D#", "J");
            m =m.replaceAll("F#", "K");
            m =m.replaceAll("G#", "L");
            m =m.replaceAll("A#", "M");
            return m;
        }
    }
}
