package past;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class B_17822 {
    static int N; // 원판수
    static int M; // 원판에 적힌 숫자수
    static int T; // 회전수
    static int map[][];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            //번호가 xi의 배수인 원판을 di방향으로 ki칸 회전시킨다. di가 0인 경우는 시계 방향, 1인 경우는 반시계 방향이다.
            for (int plateInd = x; plateInd <= N; plateInd += x) { // x의 배수인 원판마다
                int realInd = plateInd - 1;
                if (d == 0) {
                    rotateClockWise(map[realInd], k);
                }
                else {
                    rotateCounterClockWise(map[realInd], k);
                }
            }

            //인접하면서 수가 같은 것을 모두 찾는다.
            HashSet<int[]> numsToBeRemoved = new HashSet<>();
            for (int plateInd = 0; plateInd < N; plateInd++) {
                for (int numInd = 0; numInd < M; numInd++) {
                    int[][] neighbors = getNeighbors(new int[] {plateInd, numInd});
                    for (int ni = 0; ni < 4; ni++) {
                        int[] neighbor = neighbors[ni];
                        if (neighbor == null) continue;
                        if (map[plateInd][numInd] == 0) continue;
                        if (map[neighbor[0]][neighbor[1]] == map[plateInd][numInd]) {
                            numsToBeRemoved.add(new int[] {neighbor[0], neighbor[1]});
                            numsToBeRemoved.add(new int[] {plateInd, numInd});
                        }
                    }
                }
            }
            //그러한 수가 있는 경우에는 원판에서 인접하면서 같은 수를 모두 지운다.
            if (!numsToBeRemoved.isEmpty()) {
                for (int[] num : numsToBeRemoved) {
                    map[num[0]][num[1]] = 0;
                }
            }
            //없는 경우에는 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다.
            else {
                int sum = 0;
                int cntNums = 0;
                for (int plateInd = 0; plateInd < N; plateInd++) {
                    for (int numInd = 0; numInd < M; numInd++) {
                        sum += map[plateInd][numInd];
                        if (map[plateInd][numInd] != 0) cntNums++;
                    }
                }
                double avg = (double)sum / cntNums;
                for (int plateInd = 0; plateInd < N; plateInd++) {
                    for (int numInd = 0; numInd < M; numInd++) {
                        if (map[plateInd][numInd] == 0) continue;
                        if (map[plateInd][numInd] > avg) {
                            map[plateInd][numInd]--;
                        } else if (map[plateInd][numInd] < avg) {
                            map[plateInd][numInd]++;
                        }
                    }
                }
            }
        }
        int sum = 0;
        for (int plateInd = 0; plateInd < N; plateInd++) {
            for (int numInd = 0; numInd < M; numInd++) {
                sum += map[plateInd][numInd];
            }
        }

//        printMap();
        System.out.println(sum);

    }

    static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void rotateClockWise(int[] plate, int k) {
        for (int ik = 0; ik < k; ik ++) {
            int temp = plate[M - 1];
            for (int i = M - 1; i > 0; i--) {
                plate[i] = plate[i - 1];
            }
            plate[0] = temp;
        }
    }

    static void rotateCounterClockWise(int[] plate, int k) {
        for (int ik = 0; ik < k; ik ++) {
            int temp = plate[0];
            for (int i = 0; i < M - 1; i++) {
                plate[i] = plate[i + 1];
            }
            plate[M - 1] = temp;
        }
    }

    static int[][] getNeighbors(int[] pos) {
        int[][] ans = new int[4][2];
        //(i, j)는 (i, j-1), (i, j+1)과 인접하다. (2 ≤ j ≤ M-1)
        ans[0] = new int[] {pos[0], (pos[1] - 1 + M) % M};
        ans[1] = new int[] {pos[0], (pos[1] + 1) % M};
        //(i, j)는 (i-1, j), (i+1, j)와 인접하다. (2 ≤ i ≤ N-1)
        ans[2] = pos[0] - 1 >= 0 ? new int[] {pos[0] - 1, pos[1]} : null;
        ans[3] = pos[0] + 1 < N ? new int[] {pos[0] + 1, pos[1]} : null;

        return ans;
    }
}
