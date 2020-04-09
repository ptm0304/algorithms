package lineCT;

public class P2 {
    public static void main(String[] args) {
        String ans = "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
        String[] sheets = {"1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111211111111111",
                "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111113111111111322",
                "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111113111111111322"};
        System.out.println(solution(ans, sheets));
    }
    public static int solution(String answer_sheet, String[] sheets) {
        int answer = -1;
        for (int i = 0; i < sheets.length; i++){
            for (int j = i + 1; j < sheets.length; j++) {
                int nConsecutive = 0;
                int nCurrConsecutive = 0;
                int nSuspect = 0;
                for (int k = 0; k < answer_sheet.length(); k++) {
                    if (sheets[i].charAt(k) != answer_sheet.charAt(k) &&
                            sheets[i].charAt(k) == sheets[j].charAt(k)) {
                        nSuspect++;
                        nCurrConsecutive++;
                    }
                    else {
                        nConsecutive = Math.max(nConsecutive, nCurrConsecutive);
                        nCurrConsecutive = 0;
                    }
                }
                nConsecutive = Math.max(nConsecutive, nCurrConsecutive);
                int ans = nSuspect + nConsecutive * nConsecutive;
                answer = Math.max(ans, answer);
            }
        }
        return answer;
    }
}
