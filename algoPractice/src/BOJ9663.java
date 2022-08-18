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
		chess = new int[N+1];
		
		setQueen(1);
		System.out.print(answer);
	}
	
	// 하나의 퀸만 가능한 모든 곳에 놓아보기
	public static void setQueen(int row) {
		
		// 유망성 체크
		// 직전까지의 상황이 유망하지 않으면 현재 퀸 놓을 필요가 없으니 백트래킹
		if (!isAvailable(row-1)) return;
		
		// 모든 퀸을 다 놓았다면
		// 모든 퀸의 배치에 성공했다는 의미
		if (row > N) {
			answer++;
			return;
		}
		
		// i = 퀸 번호
		for (int i = 1; i <= N; i++) {
			chess[row] = i; // 해당 행에 i번째 퀸 놓기
			setQueen(row+1); // 다음 행 탐색
		}
		
	}
	
	public static boolean isAvailable(int row) {
		// 현재 행의 직전까지 체크하기
		for (int i = 1; i <= row-1; i++) {
			// 현재 행과 겹치는 행이 있거나
			// 현재 행과의 행 차이가 열 차이와 같으면 false return
			if (chess[i] == chess[row] || 
					row - i == (Math.abs(chess[row] - chess[i]))) return false;
		}
		
		return true;
	}
}