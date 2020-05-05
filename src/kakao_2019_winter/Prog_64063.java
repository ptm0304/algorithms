package kakao_2019_winter;

import java.util.Arrays;
import java.util.HashMap;

// 프로그래머스 카카오 기출 호텔 방 배정
// 시간복잡도 = O(n*log(n)) = 200000 * log(200000) = 4*10^6
public class Prog_64063 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(10, new long[] {1,3,4,1,3,1})));
    }

    private static class Solution {
        // 가능한 방의수가 10^12 이라서 배열에 다 안들어가기 때문에 HashMap으로 대체
        HashMap<Long, Long> parent;

        // 배정가능한 방번호를 log(n) 시간안에 찾기 위해 Dosjoing Set 사용
        public long[] solution(long k, long[] room_number) {
            long[] answer = new long[room_number.length];
            parent = new HashMap<>();
            for (int i = 0; i < room_number.length; i++) {
                long desiredRoom = room_number[i];
                long registeredRoom = find(desiredRoom); // 가능한 방번호 찾기
                answer[i] = registeredRoom;
                union(registeredRoom, registeredRoom + 1); // 현재 배정된 방과 다음 방 union으로 연결
            }
            return answer;
        }

        Long find(Long number) {
            // parent가 지정되어 있지 않거나 자기자신이 parent라면 현재 방 지정
            if (!parent.containsKey(number) || parent.get(number) == number) {
                return number;
            }
            Long p = find(parent.get(number));
            parent.put(number, p);
            return p;
        }

        void union(Long n1, Long n2) {
            Long p1 = find(n1);
            Long p2 = find(n2);
            parent.put(n1, Math.max(p1, p2));
            parent.put(n2, Math.max(p1, p2));
            parent.put(p1, Math.max(p1, p2));
            parent.put(p2, Math.max(p1, p2));
        }
    }
}
