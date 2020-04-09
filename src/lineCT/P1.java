package lineCT;

public class P1 {
    public static void main(String[] args) {
        String str = "({{([[[)]}})";
        System.out.println(solution(str));
    }

    public static int solution(String inputString) {
        int nSmall = 0;
        int nMedium = 0;
        int nLarge = 0;
        int nArrow = 0;
        int nParenthesis = 0;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            switch (c) {
                case '(':
                    nSmall++;
                    break;
                case '{':
                    nMedium++;
                    break;
                case '[':
                    nLarge++;
                    break;
                case '<':
                    nArrow++;
                    break;
                case ')':
                    nParenthesis++;
                    nSmall--;
                    break;
                case '}':
                    nParenthesis++;
                    nMedium--;
                    break;
                case ']':
                    nParenthesis++;
                    nLarge--;
                    break;
                case '>':
                    nParenthesis++;
                    nArrow--;
                    break;
            }
            if (nSmall < 0 || nMedium < 0 || nLarge < 0 || nArrow < 0) return -1;
        }
        if (nSmall != 0 || nMedium != 0 || nLarge != 0 || nArrow != 0) return -1;
        return nParenthesis;
    }
}
