package kakao_2018_blind;


import java.util.Arrays;

// 카카오 2018 블라인드 채용 [1차] 셔틀버스
public class Prog_17678 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(1, 1, 5, new String[]{"08:00", "08:01", "08:02", "08:03"}));
    }
    private static class Solution {
        // n 셔틀 운영 횟수
        // t 운행 간격
        // m 셔틀 최대 인원
        public String solution(int n, int t, int m, String[] timetable) {
            String answer = "";

            int[] tt = timeTableToInt(timetable);

            int passengerInd = 0;
            int time = 9 * 60 - t;
            for (int i = 0; i < n - 1; i++) {
                time += t;
                int nBoard = 0;
                while (nBoard < m && passengerInd < tt.length && tt[passengerInd] <= time) {
                    nBoard++;
                    passengerInd++;
                }
            }

            // 마지막 버스
            time += t;
            int nBoard = 0;
            while (nBoard < m && passengerInd < tt.length && tt[passengerInd] <= time) {
                nBoard++;
                passengerInd++;
            }
            //마지막 버스가 꽉 찬다면 마지막 버스의 마지막 크루보다 1분 일찍
            if (nBoard == m) {
                answer = timeIntToString(tt[passengerInd - 1] - 1);
            }
            //마지막 버스에 사람이 남는다면 마지막 버스 도착시간
            else {
                answer = timeIntToString(time);
            }
            return answer;
        }

        String timeIntToString(int time) {
            int hour = time / 60;
            int min = time % 60;
            String hStr = Integer.toString(hour);
            String mStr = Integer.toString(min);

            if (hour < 10) {
                hStr = "0" + hStr;
            }
            if (min < 10) {
                mStr = "0" + mStr;
            }
            return hStr + ":" + mStr;
        }

        int[] timeTableToInt(String[] timetable) {
            int[] tt = new int[timetable.length];
            for (int i = 0; i < timetable.length; i++) {
                String[] time = timetable[i].split(":");
                tt[i] = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            }
            Arrays.sort(tt);
            return tt;
        }
    }
}
