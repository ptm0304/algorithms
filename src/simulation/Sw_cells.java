package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Sw_cells {
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    public static void main(String[] args) throws Exception {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        int nt = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= nt; tc++) {
            PriorityQueue<Cell> aliveCells = new PriorityQueue<>();
            HashMap<String, int[]> map = new HashMap<>(); // row,col : {plantTime, power}
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    int x = Integer.parseInt(st.nextToken());
                    if (x != 0) {
                        aliveCells.add(new Cell(i, j, x, x, x));
                        map.put(i + "," + j, new int[]{0, x});
                    }
                }
            }

            int ans = aliveCells.size();
            for (int time = 1; time < k; time++) {
                if (aliveCells.isEmpty()) break;
                while (!aliveCells.isEmpty()) {
                    Cell curr = aliveCells.poll();
                    ans--;
                    if (curr.awakeTime != time) {
                        aliveCells.add(curr);
                        ans++;
                        break;
                    }
                    if (map.get(curr.row +","+curr.col)[1] != curr.power) {
                        ans++;
                        continue;
                    }
                    if (curr.lifeTime > 1) {
                        ans++;
                        aliveCells.add(new Cell(curr.row, curr.col, time + 1, curr.power, curr.lifeTime - 1));
                    }
//                    System.out.println(curr);
                    for (int di = 0; di < 4; di++) {
                        int nextRow = curr.row + dy[di];
                        int nextCol = curr.col + dx[di];
                        String posStr = nextRow +","+nextCol;
                        Cell newCell = new Cell(nextRow, nextCol, time + curr.power + 1, curr.power, curr.power);
                        if (map.containsKey(posStr)) {
                            if (map.get(posStr)[0] == time && map.get(posStr)[1] < newCell.power) {
                                map.put(posStr, new int[]{time, newCell.power});
                                aliveCells.add(newCell);
                            }
                        }
                        else {
                            ans++;
                            map.put(posStr, new int[] {time, newCell.power});
                            aliveCells.add(newCell);
                        }
                    }
                }
            }

            System.out.println("#" + tc + " " + ans);
        }
    }

    private static class Cell implements Comparable<Cell> {
        int row, col, awakeTime, power, lifeTime;

        public Cell(int row, int col, int awakeTime, int power, int lifeTime) {
            this.row = row;
            this.col = col;
            this.awakeTime = awakeTime;
            this.power = power;
            this.lifeTime = lifeTime;
        }

        @Override
        public int compareTo(Cell o) {
            return this.awakeTime - o.awakeTime;
        }

        @Override
        public String toString() {
            return "Cell{" +
                    "row=" + row +
                    ", col=" + col +
                    ", awakeTime=" + awakeTime +
                    ", power=" + power +
                    '}';
        }
    }
}
