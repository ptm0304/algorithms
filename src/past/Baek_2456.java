package past;

import java.util.Arrays;
import java.util.Scanner;

public class Baek_2456 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Candidate cand1 = new Candidate(1);
        Candidate cand2 = new Candidate(2);
        Candidate cand3 = new Candidate(3);

        for (int i = 0; i < n; i++) {
            int score1 = sc.nextInt();
            int score2 = sc.nextInt();
            int score3 = sc.nextInt();
            cand1.scores[score1]++;
            cand2.scores[score2]++;
            cand3.scores[score3]++;
            cand1.scores[0] += score1;
            cand2.scores[0] += score2;
            cand3.scores[0] += score3;
        }
        Candidate[] arr = new Candidate[] {cand1, cand2, cand3};
        Arrays.sort(arr);
        if (arr[0].scores[0] == arr[1].scores[0] && arr[0].scores[1] == arr[1].scores[1] &&
        arr[0].scores[2] == arr[1].scores[2]) {
            System.out.println("0 " + arr[0].scores[0]);
        }
        else {
            System.out.println(arr[0].num + " " + arr[0].scores[0]);
        }
    }
}

class Candidate implements Comparable<Candidate> {
    int[] scores;
    int num;

    public Candidate(int num) {
        this.num = num;
        scores = new int[4];
    }
    @Override
    public int compareTo(Candidate c) {
        if (c.scores[0] > this.scores[0]) {
            return 1;
        }
        else if (c.scores[0] < this.scores[0]) {
            return -1;
        }
        else {
            if (c.scores[1] > this.scores[1]) {
                return 1;
            }
            else if (c.scores[1] < this.scores[1]) {
                return -1;
            }
            else {
                if (c.scores[2] > this.scores[2]) {
                    return 1;
                }
                else if (c.scores[2] < this.scores[2]) {
                    return -1;
                }
                else {
                    return 0;
                }
            }
        }
    }
}
