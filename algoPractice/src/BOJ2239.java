import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 스도쿠

public class BOJ2239 {

	static int[][] sudoku;
	static boolean[][] visited;
	static boolean flag;
	
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		sudoku = new int[9][9];
		for (int i = 0; i < 9; i++) {
			String s = br.readLine();
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = s.charAt(j) - '0';
			}
		}

		visited = new boolean[9][9];
		flag = false;

		Backtracking(0);
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(sudoku[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}

	private static void Backtracking(int depth) {
		// 스도쿠의 모든 영역을 다 탐색하면 백트래킹
		if (depth == 81) {
			flag = true;
			return;
		}
		
		int y = depth / 9;
		int x = depth % 9;
		
		if (sudoku[y][x] != 0) Backtracking(depth + 1); // 값이 이미 설정돼있으면 다음 칸 살피기
		else {
			for (int i = 1; i <= 9; i++) {
				if (isPossible(y, x, i)) {
					sudoku[y][x] = i; // 수 놓기
					Backtracking(depth + 1); // 다음 수 놓으러 가기
					
					if (flag) return;
					sudoku[y][x] = 0; // 되돌리기
				}
			}
		}
	}

	private static boolean isPossible(int y, int x, int num) {
		for (int i = 0; i < 9; i++) {
			// 이미 y나 x의 수를 가지는 행이나 열에 num 수를 가지고 있으면 false return
			if (sudoku[i][x] == num || sudoku[y][i] == num) return false;
		}
		
		// squareY, squareX
		int sy = y / 3 * 3;
		int sx = x - x % 3;
		
		for (int i = sy; i < sy + 3; i++) {
			for (int j = sx; j < sx + 3; j++) {
				// (y, x)가 포함된 영역에 이미 num이라는 수를 가지고 있으면 false return
				if (sudoku[i][j] == num) return false;
			}
		}
		
		return true;
	}
}