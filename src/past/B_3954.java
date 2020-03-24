package past;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_3954 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nt = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= nt; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int memSize = Integer.parseInt(st.nextToken());
            int codeLen = Integer.parseInt(st.nextToken());
            int inputLen = Integer.parseInt(st.nextToken());
            char[] code = new char[codeLen];
            char[] inputs = new char[inputLen];

            String str = br.readLine().trim();
            for (int i = 0; i < codeLen; i++) {
                code[i] = str.charAt(i);
            }
            str = br.readLine().trim();
            for (int i = 0; i < inputLen; i++) {
                inputs[i] = str.charAt(i);
            }

            int[] storage = new int[memSize];
            int memPointer = 0;
            int codePointer = 0;
            int inputPointer = 0;

            int cnt = 0;

            while (true) {
//                System.out.println("mem pnt: " + memPointer);
//                System.out.println("storage: " + Arrays.toString(storage));
//                System.out.println("code pnt: " + codePointer);
//                System.out.println();
                if (codePointer >=  codeLen) break;
                if (cnt >= 50000000) break;
                cnt++;
                char command = code[codePointer];
                if (command == '+') {
                    storage[memPointer]++;
                    storage[memPointer] %= 256;
                    codePointer++;
                }
                else if (command == '-') {
                    storage[memPointer]--;
                    if (storage[memPointer] == -1) {
                        storage[memPointer] = 255;
                    }
                    codePointer++;
                }
                else if (command == '<') {
                    memPointer--;
                    if (memPointer == -1) {
                        memPointer = memSize - 1;
                    }
                    codePointer++;
                }
                else if (command == '>') {
                    memPointer++;
                    memPointer %= memSize;
                    codePointer++;
                }
                else if (command == ',') {
                    if (inputPointer >= inputLen) {
                        storage[memPointer] = 255;
                    }
                    else {
                        storage[memPointer] = inputs[inputPointer++];
                    }
                    codePointer++;
                }
                else if (command == '[') {
                    if (storage[memPointer] == 0) { // jump
                        int cntOpenBrackets = 1;
                        for (int i = codePointer + 1; i < codeLen; i++) {
                            if (code[i] == '[') {
                                cntOpenBrackets++;
                            }
                            else if (code[i] == ']') {
                                cntOpenBrackets--;
                            }
                            if (cntOpenBrackets == 0) {
                                codePointer = i + 1;
                                break;
                            }
                        }
                    }
                    else {
                        codePointer++;
                    }
                }
                else if (command == ']') {
                    if (storage[memPointer] != 0) { // jump
                        int cntClosedBrackets = 1;
                        for (int i = codePointer - 1; i >= 0; i--) {
                            if (code[i] == '[') {
                                cntClosedBrackets--;
                            }
                            else if (code[i] == ']') {
                                cntClosedBrackets++;
                            }
                            if (cntClosedBrackets == 0) {
                                codePointer = i + 1;
                                break;
                            }
                        }
                    }
                    else {
                        codePointer++;
                    }
                }
                else {
                    codePointer++;
                }
            }

            if (cnt >= 50000000) {

                int loopStartInd = 0;
                for (int i = codePointer; i >= 0; i--) { // find first open bracket
                    if (code[i] == '[') {
                        loopStartInd = i;
                        break;
                    }
                }

                int nOpenBrackets = 0;
                for (int i = loopStartInd - 1; i >= 0; i--) { // find outer loop start ind
                    if (code[i] == '[') {
                        nOpenBrackets++;
                        if (nOpenBrackets > 0) {
                            loopStartInd = i;
                        }
                    }
                    else if (code[i] == ']') {
                        nOpenBrackets--;
                    }
                }


                int loopEndInd = 0;
                for (int i = codePointer; i < codeLen; i++) { // find first close bracket
                    if (code[i] == ']') {
                        loopEndInd = i;
                        break;
                    }
                }
//                System.out.println(codePointer);
//                System.out.println(loopEndInd);
                int nClosedBrackets = 0;
                for (int i = loopEndInd + 1; i < codeLen; i++) { // find outer loop start ind
                    if (code[i] == '[') {
                        nClosedBrackets--;
                    }
                    else if (code[i] == ']') {
                        nClosedBrackets++;
                        if (nClosedBrackets > 0) {
                            loopEndInd = i;
                        }
                    }
                }
                System.out.println("Loops " + loopStartInd + " " + loopEndInd);
            }
            else {
                System.out.println("Terminates");
            }
        }
    }
}
