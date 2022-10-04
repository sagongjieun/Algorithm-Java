import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 스도쿠

public class BOJ2239 {

	static int[][] sudoku;
	static boolean isEnd;
	
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 입력받기
		sudoku = new int[9][9];
		for (int i = 0; i < 9; i++) {
			String s = br.readLine();
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = s.charAt(j) - '0';
			}
		}

		Backtracking(0);
		
		// 출력하기
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(sudoku[i][j]);
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}

	/**
	 * 총 81개의 스도쿠 칸 중에서 수가 놓이지 않은 곳에 1~9 사이의 수를 놓는 순열 구하기
	 * @param depth : 0~80번째까지의 칸을 의미
	 */
	private static void Backtracking(int depth) {		
		// 스도쿠의 모든 영역을 다 탐색하면 백트래킹
		if (depth == 81) {
			isEnd = true;
			return;
		}
		
		// 칸의 번호로 좌표 구하기
		int y = depth / 9;
		int x = depth % 9;
		
		if (sudoku[y][x] != 0) Backtracking(depth + 1); // 값이 이미 설정돼있으면 다음 칸 살피기
		else {
			for (int i = 1; i <= 9; i++) {
				// (y, x)에 i라는 수를 놓을 수 있다면
				if (isPossible(y, x, i)) {
					sudoku[y][x] = i; // 수 놓기
					Backtracking(depth + 1); // 다음 수 놓으러 가기
					
					if (isEnd) return; // 정답이 나왔으므로 재귀 탈출
					sudoku[y][x] = 0; // 되돌리기
				}
			}
		}
	}

	/**
	 * 행,열 체크하고 해당 좌표의 구역 체크
	 * @param y : 행
	 * @param x : 열
	 * @param num : 어떤 숫자를 놓을 지
	 * @return (y, x)좌표에 num이라는 수를 놓을 수 있으면 true, 아니면 false return
	 */
	private static boolean isPossible(int y, int x, int num) {
		
		for (int i = 0; i < 9; i++) {
			// 이미 x열에 num이 있거나 y행에 num이 있다면 false return
			if (sudoku[i][x] == num || sudoku[y][i] == num) return false;
		}
		
		// squareY, squareX
		// (y, x) 좌표가 포함된 3x3영역의 좌측상단 좌표 구하기
		int sy = y / 3 * 3;
		int sx = x - x % 3;
		
		for (int i = sy; i < sy + 3; i++) {
			for (int j = sx; j < sx + 3; j++) {
				// (y, x)가 포함된 3x3영역에 이미 num이 있다면 false return
				if (sudoku[i][j] == num) return false;
			}
		}
		
		return true;
	}
}

// 1. 모든 스도쿠칸 81개 중에서 수가 놓이지 않은 곳에 1~9까지 수 놓아보기
// 2. 근데 조건으로 같은 행, 열, 영역에 똑같은 수가 있으면 안됨
// 3. 위 조건을 만족하면 그 칸에 그 수를 놓고 다음 칸에 또 1번부터 반복