package past;

import java.io.*;

public class TestGen {
    public static void main(String[] args) throws FileNotFoundException {
        int n = 50;
        int m = 50;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("input.txt")));
        try{
            bw.write(1 + "\n");
            bw.write(n + " " + m + "\n");
            for (int i = 0; i < n; i++) {
                for (int j = 0 ; j < m; j++) {
                    bw.write((char) ('a' + (int)(Math.random() * 26)));
                }
                bw.write("\n");
            }
            bw.flush();
            bw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
