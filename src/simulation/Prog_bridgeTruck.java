package simulation;

import java.util.LinkedList;
import java.util.Queue;

public class Prog_bridgeTruck {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(2, 10, new int[] {7,4,5,6}));
    }

    private static class Solution {
        public int solution(int bridge_length, int weight, int[] truck_weights) {
            Queue<int[]> bridge = new LinkedList<>();

            int truckInd = 0;
            int time = 0;
            int weightLeft = weight - truck_weights[truckInd];
            bridge.offer(new int[] {truck_weights[truckInd++], time++ + bridge_length});
            while (!bridge.isEmpty()) {
                if (bridge.peek()[1] == time) {
                    int[] curr = bridge.poll();
                    weightLeft += curr[0];
                }
                if (truckInd < truck_weights.length) {
                    if (weightLeft >= truck_weights[truckInd]) {
                        weightLeft -= truck_weights[truckInd];
                        bridge.offer(new int[] {truck_weights[truckInd++], time++ + bridge_length});
                    }
                    else {
                        time = bridge.peek()[1];
                    }
                }
                else {
                    time++;
                }
            }
            return time;
        }
    }
}
