import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2630 {
	
	static int N;
	static int[][] confetti;
	static int white = 0, blue = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		N = Integer.parseInt(br.readLine());
		confetti = new int[N][N];
		
		// 입력받기
		for (int i=0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				confetti[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		recur(0, 0, N);
		
		System.out.println(white);
		System.out.println(blue);
	}
	
	public static void recur(int y, int x, int n) {
		// 이번 턴의 결과를 저장할 변수
		int result = 0;
		
		// 이번 턴의 nxn 정사각형들의 수의 합 구하기
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				result += confetti[x+j][y+i];
			}
		}
		
		// nxn 의 모든 합이 0이면 흰색으로만 구성된 것이니 white에 +1
		if (result == 0) white += 1;
		// nxn 의 모든 합이 n*n이면 파란색으로만 구성된 것이니 blue에 +1
		else if (result == n*n) blue += 1;
		// 아니라면 nxn이 한가지 색으로만 구성되게 n/2로 범위를 나누어 재귀 호출
		else {
			n = n/2;
			
			recur(y, x, n);
			recur(y+n, x, n);
			recur(y, x+n, n);
			recur(y+n, x+n, n);
		}
	}
}
