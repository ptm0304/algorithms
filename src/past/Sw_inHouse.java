package past;

import java.util.*;

public class Sw_inHouse {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nt = sc.nextInt();
        for (int tc = 1; tc <= nt; tc++) {
            int k = sc.nextInt();
            HashMap<String, ArrayList<String>> edges = new HashMap<>();
            HashMap<String, Integer> playerColors = new HashMap<>();
            HashMap<String, Boolean> visited = new HashMap<>();

            for (int i = 0; i < k; i++) {
                String s1 = sc.next();
                String s2 = sc.next();
                if (edges.get(s1) == null) {
                    edges.put(s1, new ArrayList<>());
                }
                if (edges.get(s2) == null) {
                    edges.put(s2, new ArrayList<>());
                }
                edges.get(s1).add(s2);
                edges.get(s2).add(s1);
                playerColors.put(s1, -1);
                playerColors.put(s2, -1);
                visited.put(s1, false);
                visited.put(s2, false);
            }
            int cnt = 0;

            boolean canMake = true;

            outer:
            while (cnt < playerColors.keySet().size()) {
                Iterator<String> names = playerColors.keySet().iterator();
                String root = null;
                while (names.hasNext()) {
                    String name = names.next();
                    if (playerColors.get(name) == -1) {
                        root = name;
                        break;
                    }
                }
                if (root == null) break;
                Queue<String> q = new LinkedList<>();
                q.offer(root);
                playerColors.put(root, 0);
                while (!q.isEmpty()) {
                    String curr = q.poll();
                    if (visited.get(curr)) continue;
                    visited.put(curr, true);
                    cnt++;
                    int currColor = playerColors.get(curr);
                    for (String neighbor : edges.get(curr)) {
                        if (currColor == playerColors.get(neighbor)){
                            canMake = false;
                            break outer;
                        }
                        playerColors.put(neighbor, (currColor + 1) % 2);
                        q.offer(neighbor);
                    }
                }
            }


            if (canMake) {
                System.out.println("#" + tc + " " + "Yes");
            }
            else {
                System.out.println("#" + tc + " " + "No");
            }
        }
    }
}
