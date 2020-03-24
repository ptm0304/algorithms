package divideConquer;

import java.util.*;

public class B_2261 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[] {sc.nextInt(), sc.nextInt()};
        }
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        System.out.println(divideConquer(0, n - 1, arr));

    }

    static int divideConquer(int low, int high, int[][] arr) {
        if (high - low == 0) {
            return Integer.MAX_VALUE;
        }
        if (high - low <= 1) {
            return getDist(arr[low], arr[high]);
        }
        int mid = (low + high) / 2;
        int left = divideConquer(low, mid - 1, arr);
        int right = divideConquer(mid + 1, high, arr);
        int min = Math.min(left, right);

        ArrayList<int[]> list = new ArrayList<>();

        for (int i = low; i <= high; i++) {
            if ((arr[i][0] - arr[mid][0]) * (arr[i][0] - arr[mid][0]) >= min) continue;
            list.add(arr[i]);
        }

        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                int d = list.get(i)[1] - list.get(j)[1];
                if (d * d >= min) break;
                min = Math.min(min, getDist(list.get(i), list.get(j)));
            }
        }

        return min;
    }

    static int getDist(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
}
