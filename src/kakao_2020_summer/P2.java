package kakao_2020_summer;

import java.util.ArrayList;
import java.util.Arrays;

public class P2 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution("100-200*300-500+20"));
    }

    private static class Solution {
        String[] ops = {"+", "-", "*"};
        ArrayList<String> opsExp;
        ArrayList<String> numsExp;
        long ans;

        public long solution(String expression) {
            numsExp = new ArrayList<String>(Arrays.asList(expression.split("[-+*]")));
            String[] opsExpTemp = expression.split("[\\d]");

            opsExp = new ArrayList<>();

            for (int i = 0; i < opsExpTemp.length; i++) {
                if (opsExpTemp[i].length() > 0) {
                    opsExp.add(opsExpTemp[i]);
                }
            }

            perm(new char[3], 0);

            return ans;
        }

        void calc(char[] result) {
            ArrayList<String> tempOps = (ArrayList<String>) opsExp.clone();
            ArrayList<String> tempNums = (ArrayList<String>) numsExp.clone();
            
            for (int i = 0; i < 3; i++) {
                char currOp = result[i];
                int ind = 0;
                while (ind < tempOps.size()) {
                    if (tempOps.get(ind).charAt(0) == currOp) {
                        if (currOp == '+') {
                            long res = Long.parseLong(tempNums.get(ind)) +
                                    Long.parseLong(tempNums.get(ind + 1));
                            tempNums.set(ind, Long.toString(res));
                        }
                        else if (currOp == '-'){
                            long res = Long.parseLong(tempNums.get(ind)) -
                                    Long.parseLong(tempNums.get(ind + 1));
                            tempNums.set(ind, Long.toString(res));
                        }
                        else {
                            long res = Long.parseLong(tempNums.get(ind)) *
                                    Long.parseLong(tempNums.get(ind + 1));
                            tempNums.set(ind, Long.toString(res));
                        }
                        tempNums.remove(ind + 1);
                        tempOps.remove(ind);
                    }
                    else {
                        ind++;
                    }
                }
            }

            ans = Math.max(ans, Math.abs(Long.parseLong(tempNums.get(0))));
        }

        void perm(char[] result, int ind) {
            if (ind == 3) {
                calc(result);
                return;
            }
            for (int i = 0; i < 3; i++) {
                boolean contains = false;
                for (int j = 0; j < ind; j++) {
                    if (result[j] == ops[i].charAt(0)) {
                        contains = true;
                    }
                }
                if (contains) continue;
                result[ind] = ops[i].charAt(0);
                perm(result, ind + 1);
            }
        }
    }
}
