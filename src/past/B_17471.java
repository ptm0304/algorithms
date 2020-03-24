package past;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_17471 {
    static boolean[] visited;
    static ArrayList[] edges;
    static int[] pops;
    static int numAreas;
    static int minDiff = Integer.MAX_VALUE;
    static int totalPop = 0;
    static boolean[] excludes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        numAreas = Integer.parseInt(br.readLine().trim());
        pops = new int[numAreas];
        visited = new boolean[numAreas];
        excludes = new boolean[numAreas];
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < numAreas; i++) {
            pops[i] = Integer.parseInt(st.nextToken());
            totalPop += pops[i];
        }

        edges = new ArrayList[numAreas];
        for (int i = 0; i < numAreas; i++) {
            edges[i] = new ArrayList<Integer>();
            st = new StringTokenizer(br.readLine().trim());
            int numEdges = Integer.parseInt(st.nextToken());
            for (int j = 0; j < numEdges; j++) {
                edges[i].add(Integer.parseInt(st.nextToken()) - 1);
            }
        }

        combination(-1, new ArrayList<Integer>(), 0);

        System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);
    }

    static void combination(int ind, ArrayList<Integer> group, int groupPop) {
        if (group.size() > numAreas - 1) return;
        if (ind > -1 && canMakeAnotherGroup() && canMakeGroup()) {
            minDiff = Math.min(minDiff, Math.abs(totalPop - 2 * groupPop));
        }

        for (int i = ind + 1; i < numAreas; i++) {
            group.add(i);
            visited[i] = true;
            combination(i, group, groupPop + pops[i]);
            group.remove(group.size() - 1);
            visited[i] = false;
        }
    }

    static boolean canMakeGroup() {
        int startingArea = -1;
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                startingArea = i;
                break;
            }
        }
        if (startingArea == -1) return false; // no areas selected to make group
        Queue<Integer> q = new LinkedList<>();
        q.offer(startingArea);
        boolean[] visitedBfs = visited.clone();

        while (!q.isEmpty()) {
            int curr = q.poll();
            if (!visitedBfs[curr]) continue;
            visitedBfs[curr] = false;

            ArrayList<Integer> neighbors = edges[curr];
            for (Integer neighbor : neighbors) {
                q.offer(neighbor);
            }
        }

        for (int i = 0; i < numAreas; i++) {
            if (visitedBfs[i]) return false; // unvisited area exists
        }
        return true;
    }

    static boolean canMakeAnotherGroup() {
        int startingArea = -1;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                startingArea = i;
                break;
            }
        }
        if (startingArea == -1) return false; // no areas left to make another group
        Queue<Integer> q = new LinkedList<>();
        q.offer(startingArea);
        boolean[] visitedBfs = visited.clone();

        while (!q.isEmpty()) {
            int curr = q.poll();
            if (visitedBfs[curr]) continue;
            visitedBfs[curr] = true;

            ArrayList<Integer> neighbors = edges[curr];
            for (Integer neighbor : neighbors) {
                q.offer(neighbor);
            }
        }

        for (int i = 0; i < numAreas; i++) {
            if (!visitedBfs[i]) return false; // unvisited area exists
        }
        return true;
    }
}
