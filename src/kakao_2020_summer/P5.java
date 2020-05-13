package kakao_2020_summer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class P5 {

    private static class Solution {
        // 방 개수 n, 동굴의 각 통로들이 연결하는 두 방의 번호가 담긴 2차원 배열 path,
        // 프로도가 정한 방문 순서가 담긴 2차원 배열 order
        public boolean solution(int n, int[][] path, int[][] order) {
            boolean answer = false;
            int[] nLink = new int[n];
            int[] link = new int[n];

            for (int i = 0; i < order.length; i++) {
                int[] curr = order[i];
                link[curr[0]] = curr[1];
                nLink[curr[1]]++;
            }

            ArrayList<Integer>[] edges = new ArrayList[n];
            for (int i = 0; i < path.length; i++) {
                int[] curr = path[i];
                if (edges[curr[0]] == null) {
                    edges[curr[0]] = new ArrayList<>();
                }
                if (edges[curr[1]] == null) {
                    edges[curr[1]] = new ArrayList<>();
                }
                edges[curr[0]].add(curr[1]);
                edges[curr[1]].add(curr[0]);
            }

            Queue<Integer> q = new LinkedList<>();
            q.offer(0);

            HashSet<Integer> waitList = new HashSet<>();
            boolean[] visited = new boolean[n];
            int cnt = 0;
            while (!q.isEmpty()) {
                int curr = q.poll();
                if (nLink[curr] != 0) break;
                if (visited[curr]) continue;
                visited[curr] = true;
                cnt++;
                nLink[link[curr]] = 0;
                if (waitList.contains(link[curr])) {
                    q.offer(link[curr]);
                    waitList.remove(link[curr]);
                }
                if (cnt == n) {
                    answer = true;
                    break;
                }
                for (Integer neighbor: edges[curr]) {
                    if (nLink[neighbor] != 0) {
                        waitList.add(neighbor);
                    }
                    else {
                        q.offer(neighbor);
                    }
                }
            }
            return answer;
        }
    }
}
