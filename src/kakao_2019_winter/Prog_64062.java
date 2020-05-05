package kakao_2019_winter;

// 프로그래머스 카카오 기출 징검다리 건너기
// 시간복잡도: 20만 log(2억) == 5 * 10^6
public class Prog_64062 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(
                new int[] {2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
    }

    private static class Solution {
        public int solution(int[] stones, int k) {
            int answer = 0;
            int high = 0;

            // 돌을 밟을 수 있는 최대수가 건널수 있는 학생의 최대수가 된다
            for (int i = 0; i < stones.length; i++) {
                high = Math.max(high, stones[i]);
            }

            int low = 0;

            // 건널 수 있는 학생 수의 기준으로 이분탐색
            while (high >= low) {
                int mid = (low + high) / 2;
                int dist = 0;
                boolean possible = true;

                // 밟을수 있는 횟수 - 건너는 학생 수 < 0 이 연속으로 나타나는 거리가 k보다 크거나 같으면 불가능
                for (int i = 0; i < stones.length; i++) {
                    if (stones[i] - mid < 0) {
                        dist++;
                    }
                    else {
                        dist = 0;
                    }
                    if (dist == k) {
                        possible = false;
                        break;
                    }
                }
                if (possible) {
                    answer = Math.max(answer, mid);
                    low = mid + 1;
                }
                else {
                    high = mid - 1;
                }
            }
            return answer;
        }
    }
}
