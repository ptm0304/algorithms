package kakao_2019_winter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class Prog_64064 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(
                new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[] {"*rodo", "*rodo", "******"}));
    }

    private static class Solution {
        HashSet<String> ans;
        public int solution(String[] user_id, String[] banned_id) {
            ans = new HashSet<>();

            ArrayList<String>[] possible_id = new ArrayList[banned_id.length];
            for (int i = 0; i < banned_id.length; i++) {
                String ban = banned_id[i];
                ban = ban.replace("*", ".");

                possible_id[i] = new ArrayList<>();
                for (String user : user_id) {
                    if(user.matches(ban)){
                        possible_id[i].add(user);
                    }
                }
            }
            brute_force(possible_id, 0, new TreeSet<>());

            return ans.size();
        }

        void brute_force(ArrayList<String>[] possible_id, int ind, TreeSet<String> prevIdSet) {
            if (ind == possible_id.length) {
                ans.add(prevIdSet.toString());
                return;
            }
            ArrayList<String> curr = possible_id[ind];
            for (String str : curr) {
                if (prevIdSet.contains(str)) continue;
                prevIdSet.add(str);
                brute_force(possible_id, ind + 1, prevIdSet);
                prevIdSet.remove(str);
            }
        }
    }
}
