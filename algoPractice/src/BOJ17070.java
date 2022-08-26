import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 파이프 옮기기 1

public class BOJ17070 {

	static int N;
	static int[][] map;
	static int answer = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;

		N = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}

		// 시작 좌표 (1, 2)
		DFS(1, 2, 0);

		System.out.print(answer);
	}

	// flag = 0 가로 1 세로 2 대각선
	private static void DFS(int y, int x, int flag) {

		// 도착점에 도착하면 재귀 종료
		if (y == N && x == N) {
			answer++;
			return;
		}

		// 가로
		if (flag == 0) {
			// 오른쪽
			if (isInRange(y, x+1) && map[y][x+1] == 0) {
				DFS(y, x+1, 0);
			}
			// 대각선 아래 (3가지 영역에 대해 다 확인)
			if (isInRange(y, x+1) && isInRange(y+1, x) && isInRange(y+1, x+1) 
					&& map[y][x+1] == 0 && map[y+1][x] == 0 && map[y+1][x+1] == 0) {
				DFS(y+1, x+1, 2);
			}
		}

		// 세로
		else if (flag == 1) {
			// 아래
			if (isInRange(y+1, x) && map[y+1][x] == 0) {
				DFS(y+1, x, 1);
			}
			// 대각선 아래
			if (isInRange(y, x+1) && isInRange(y+1, x) && isInRange(y+1, x+1) 
					&& map[y][x+1] == 0 && map[y+1][x] == 0 && map[y+1][x+1] == 0) {
				DFS(y+1, x+1, 2);
			}
		}

		// 대각선
		else if (flag == 2) {
			// 가로
			if (isInRange(y, x+1) && map[y][x+1] == 0) {
				DFS(y, x+1, 0);
			}
			// 세로
			if (isInRange(y+1, x) && map[y+1][x] == 0) {
				DFS(y+1, x, 1);
			}
			// 대각선 아래
			if (isInRange(y, x+1) && isInRange(y+1, x) && isInRange(y+1, x+1) 
					&& map[y][x+1] == 0 && map[y+1][x] == 0 && map[y+1][x+1] == 0) {
				DFS(y+1, x+1, 2);
			}
		}
	}

	// 범위 체크 함수
	private static boolean isInRange(int y, int x) {
		if (y >= 1 && x >= 1 && y <= N && x <= N) return true;
		return false;
	}
}