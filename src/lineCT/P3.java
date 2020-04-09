package lineCT;

import java.util.LinkedList;
import java.util.Queue;

public class P3 {
    public static void main(String[] args) {
        System.out.println(solution("1100111", 1));
    }
    public static int solution(String road, int n) {
        int answer = -1;

        StringBuilder roadNum = new StringBuilder();
        int nRoad = 0;
        int nFix = 0;
        for (int i = 0; i < road.length(); i++) {
            if (road.charAt(i) == '1') {
                nRoad++;
                if (nFix < 0) {
                    roadNum.append(nFix + " ");
                }
                nFix = 0;
            }
            else {
                nFix--;
                if (nRoad > 0) {
                    roadNum.append(nRoad + " ");
                }
                nRoad = 0;
            }
        }
        if (nRoad > 0) {
            roadNum.append(nRoad + " ");
        }
        if (nFix < 0) {
            roadNum.append(nFix + " ");
        }
        //////////////////////
        String[] arr = roadNum.toString().split(" ");
        Queue<int[]> indices = new LinkedList<>();
        int currAns = 0;
        for (int i = 0; i < arr.length; i++) {
            int currNum = Integer.parseInt(arr[i]);
            if (currNum < 0) {
                if (n > 0) {
                    indices.offer(new int[] {i, 1});
                    currAns++;
                    n--;
                }
                else {
                    if (!indices.isEmpty()) {
                        int[] num = indices.poll();
                        if (num[1] == 1 && num[0] > 0) {
                            currAns -= Integer.parseInt(arr[num[0] - 1]);
                        }
                        indices.offer(new int[] {i, 1});
                    }
                    else {
                        currAns = 0;
                    }
                }
                for (int j = 1; j < -currNum; j++) {
                    if (n > 0) {
                        indices.offer(new int[] {i, -1});
                        currAns++;
                        n--;
                    }
                    else {
                        if (!indices.isEmpty()) {
                            int[] num = indices.poll();
                            if (num[1] == 1 && num[0] > 0) {
                                currAns -= Integer.parseInt(arr[num[0] - 1]);
                            }
                            indices.offer(new int[] {i, -1});
                        }
                    }
                }
            }
            else {
                currAns += currNum;
            }
            answer = Math.max(currAns, answer);
        }
        return answer;
    }
}
