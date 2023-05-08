import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int SIZE = 5;
        int x = 0;
        int y = 0;
        int num = 0;
        int count = 0; // 0이 이어진 개수를 저장할 변수 추가

        int bingo [][] = new int[SIZE][SIZE];
        Scanner sc = new Scanner(System.in);

        // 배열의 모든 요소를 1부터 SIZE*SIZE까지의 숫자로 초기화
        for(int i = 0 ; i < SIZE ; i++) {
            for(int j = 0 ; j < SIZE ; j++) {
                bingo[i][j] = i*SIZE + j + 1;
            }
        }

        // 배열에 저장된 값을 뒤섞는다.(shuffle)
        for(int i = 0 ; i < SIZE ; i++) {
            for(int j = 0 ; j < SIZE ; j++) {
                x = (int)(Math.random() * SIZE);
                y = (int)(Math.random() * SIZE);

                // bingo[i][j]와 임의로 선택된 값(bingo[x][y])을 바꾼다.
                int tmp = bingo[i][j];
                bingo[i][j] = bingo[x][y];
                bingo[x][y] = tmp;
            }
        }

        do {
            count = 0; // 매번 0이 이어진 개수를 0으로 초기화
            for(int i = 0 ; i < SIZE ; i++) {
                for(int j = 0 ; j < SIZE ; j++) {
                    if(bingo[j][i] == 0) { // 세로 빙고 판단을 위해 i와 j 순서 변경
                        count++; // 0이 이어진 개수 증가
                    } else { // 0이 아니면
                        count = 0; // 0이 이어진 개수 초기화
                    }
                    System.out.printf("%2d ", bingo[j][i]); // 출력 순서 변경
                }
                System.out.println();
                if(count >= 5) { // 0이 5개 이상 이어지면
                    System.out.println("빙고!"); // 빙고 출력
                    sc.close();
                    return; // 프로그램 종료
                }
            }
            System.out.println();

            System.out.printf("1~%d의 숫자를 입력하세요. (종료:0) > ", SIZE*SIZE);
            String tmp =sc.nextLine(); // 화면에서 입력받은 내용을 tmp에 저장
            num = Integer.parseInt(tmp); // 입력받은 문자열(tmp)를 숫자로 변환

            // 입력받은 숫자와 같은 숫자가 저장된 요소를 찾아서 0을 저장
            outer:
            for(int i = 0 ; i < SIZE ; i++) {
                for(int j = 0 ; j < SIZE ; j++) {
                    if(bingo[i][j] == num) {
                        bingo[i][j] = 0;
                        break outer; // 2중 반복문을 벗어난다.
                    }
                }
            }
        } while (num != 0);

        sc.close();
    }
}
