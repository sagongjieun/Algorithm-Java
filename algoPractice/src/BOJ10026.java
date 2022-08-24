import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 적록색약

public class BOJ10026 {

	static int N;
	static char[][] gridNormal, gridSpecial;
	static boolean[][] visitedNormal, visitedSpecial;
	static int countNormal, countSpecial;

	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		gridNormal = new char[N][N];
		gridSpecial = new char[N][N];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				char c = s.charAt(j);

				// 적록색약 사람들은 입력받을 때 R, G를 합치기
				if (c == 'G') gridSpecial[i][j] = 'R';
				else gridSpecial[i][j] = c;

				// 일반 사람들은 기존대로 입력받기
				gridNormal[i][j] = c;
			}
		}

		visitedNormal = new boolean[N][N];
		visitedSpecial = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 일반사람들에 대한 구역 구하기
				if (!visitedNormal[i][j]) {
					visitedNormal[i][j] = true;
					DFS(i, j, gridNormal, visitedNormal);
					countNormal += 1;
				}
				// 적록색약사람들에 대한 구역 구하기
				if (!visitedSpecial[i][j]) {
					visitedSpecial[i][j] = true;
					DFS(i, j, gridSpecial, visitedSpecial);
					countSpecial += 1;
				}
			}
		}

		System.out.print(countNormal + " " + countSpecial);
	}

	private static void DFS(int y, int x, char[][] grid, boolean[][] visited) {

		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			// 다음 좌표가 범위안에 있고, 방문하지 않았으며, grid의 문자가 같으면
			if (isInRange(ny, nx) && !visited[ny][nx] && grid[ny][nx] == grid[y][x]) {
				visited[ny][nx] = true;
				DFS(ny, nx, grid, visited);
			}
		}
	}

	// 방문 체크 함수
	private static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < N && x < N) return true;
		return false;
	}
}