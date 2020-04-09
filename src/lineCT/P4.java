package lineCT;

import java.util.*;

public class P4 {
    public static void main(String[] args) {
        String[][] datasource = new String[12][];
        datasource[0] = new String[] {"doc1", "t1", "t2", "t3"};
        datasource[1] = new String[] {"doc2", "t0", "t2", "t3"};
        datasource[2] = new String[] {"doc3", "t1", "t6", "t7"};
        datasource[3] = new String[] {"doc4", "t1", "t2", "t4"};
        datasource[4] = new String[] {"doc5", "t1", "t2", "t4"};
        datasource[5] = new String[] {"doc6", "t1", "t2", "t4"};
        datasource[6] = new String[] {"doc7", "t1", "t2", "t4"};
        datasource[7] = new String[] {"doc8", "t1", "t2", "t4"};
        datasource[8] = new String[] {"doc9", "t1", "t2", "t4"};
        datasource[9] = new String[] {"doc10", "t1", "t2", "t4"};
        datasource[10] = new String[] {"doc11", "t6", "t100", "t8"};
        datasource[11] = new String[] {"doc12", "t1", "t2", "t4"};
        System.out.println(Arrays.toString(solution(datasource, new String[] {"t1", "t2", "t3"})));
    }

    public static String[] solution(String[][] dataSource, String[] tags) {
        ArrayList<String> answer = new ArrayList<>();
        HashSet<String> tagsSet = new HashSet<>();
        for (int i = 0; i < tags.length; i++) {
            tagsSet.add(tags[i]);
        }
        PriorityQueue<String[]> pq = new PriorityQueue<>(new Comparator<String[]>() {
            @Override
            public int compare(String[] strings, String[] t1) {
                int n1 = 0;
                int n2 = 0;
                for (int i = 1; i < strings.length; i++) {
                    if (tagsSet.contains(strings[i])) n1++;
                }
                for (int i = 1; i < t1.length; i++) {
                    if (tagsSet.contains(t1[i])) n2++;
                }
                if (n1 > n2) return -1;
                else if (n2 > n1) return 1;
                else {
                    return -strings[0].compareTo(strings[1]);
                }
            }
        });
        for (String[] doc : dataSource) {
            pq.offer(doc);
        }
        int cnt = 0;
        while (!pq.isEmpty()) {
            if (cnt == 10) break;
            String[] curr = pq.poll();
            int n = 0;
            for (int i = 1; i < curr.length; i++) {
                if (tagsSet.contains(curr[i])) n++;
            }
            if (n == 0) break;
            answer.add(curr[0]);
        }
        return answer.toArray(new String[answer.size()]);
    }
}
