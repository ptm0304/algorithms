package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_3025_tk {
    static int R, C, N;
    static char[][] map;

    static public class P { // 현재 맵을 제어하는 클래스
        int[] col = new int[30000];
        int r;

        public void init() { // 이번에 날아올 돌 초기화
            map[r - 1][col[r - 1]] = 'O';
        }

        public void start() { // 규칙에 따라 돌을 밑으로 보내는 메소드
            while(true) {
                int s = col[r-1];

                if( r > 1 && map[r-1][s] != '.' ) { --r; continue; }

                if( r == R ) break;
                if( map[r][s] == 'X' ) break; // 장애물을 만나면 멈춤

                if( map[r][s] == '.' ) { // 빈칸이라면 내려간다
                    col[r++] = s;
                } else { // 굳은 화산탄일 때
                    if(s > 0 && map[r][s-1] == '.' && map[r-1][s-1] == '.' ) {
                        col[r++] = s - 1;  // 왼쪽으로 이동
                    } else if( s+1 < C && map[r][s+1] == '.' && map[r-1][s+1] == '.' ) {
                        col[r++] = s + 1; // 오른쪽으로 이동
                    } else {
                        break;
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[30000][32]; // 배열 선언
        P[] p_arr = new P[30]; // 제어 클래스 초기화
        for(int i = 0; i < 30; i++) {
            p_arr[i] = new P();
        }
        for(int i = 0; i < R; i++) // 배열 입력
            map[i] = bf.readLine().toCharArray();
        for(int i = 0; i < C; i++) {  // 제어클래스 배열을 초기화 한다
            p_arr[i].col[0] = i;
            p_arr[i].r = 1;
            p_arr[i].start();
        }

        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int stone = Integer.parseInt(st.nextToken()) - 1; // 날리는 돌 컬럼 인덱스
            p_arr[stone].init();
            for(int j = 0; j < C; j++) p_arr[j].start(); // 각 컬럼마다 모두 처리해준다.
        }

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}