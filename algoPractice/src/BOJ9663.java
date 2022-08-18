import java.util.Scanner;

// N-Queen

public class BOJ9663 {
	
	static int N;
	static int answer;
	static int[] chess;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		answer = 0;
		chess = new int[N+1]; // 인덱스 맞추기위해 N+1
		
		setQueen(1); // 인덱스 1부터 퀸 놓기 시작
		System.out.print(answer);
	}
	
	public static void setQueen(int col) {
		
		// 범위를 벗어나면 == 모든 퀸을 다 놓았다면 정답 +1
		if (col > N) {
			answer++;
			return;
		}
		
		for (int row = 1; row <= N; row++) {
			chess[col] = row; // 해당 열에 row행 넣기
			
			// 유망성 체크를 통과하면 다음 열 탐색
			if (isAvailable(col)) setQueen(col + 1);
		}
		
	}
	
	public static boolean isAvailable(int col) {

		// 현재 행의 직전까지 체크
		for (int i = 1; i <= col - 1; i++) {
			// 현재 열의 행과 이전의 열의 행이 일치하거나 (같은 행에 존재할 경우)
			// 열의 차이와 행의 차이가 같을 경우 (대각선에 높여있는 경우) 불가
			if (chess[i] == chess[col] || 
					col - i == (Math.abs(chess[col] - chess[i]))) return false;
		}
		
		return true;
	}
}