import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 빵집

public class BOJ3109 {

	static int R, C;
	static int[][] map;
	static boolean[][] visited;

	// 오른쪽 위, 오른쪽, 오른쪽 아래 3방위 탐색
	static int[] dy = {-1, 0, 1};
	static int[] dx = {1, 1, 1};
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());

		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				// 빈칸은 0, 건물은 1로 세팅
				if (s.charAt(j) == '.') map[i][j] = 0;
				else map[i][j] = 1;
			}
		}

		visited = new boolean[R][C];

		// 열의 수만큼 반복하여 DFS탐색
		for (int i = 0; i < R; i++) {
			DFS(i, 0);
		}

		System.out.print(answer);
	}


	public static boolean DFS(int y, int x) {
		// 종료조건 = 열의 끝에 오면 목적지 도착이라는 의미이므로 정답 +1
		if (x == C-1) {
			answer++;
			return true;
		}
		
		visited[y][x] = true; // 방문 체크
		
		// 3방위 탐색
		for (int i=0; i<3; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			// 범위를 벗어나지 않고, 방문하지 않은 빈칸이 있다면
			if (rangeCheck(ny, nx) && !visited[ny][nx] && map[ny][nx] == 0) {
				// 해당 좌표로 탐색하고 그 결과가 true이면 재귀 호출 return 하여 가지치기 방지
				if (DFS(ny, nx)) return true;
			}
		}

		return false;
	}
	
	// 범위 체크 함수
	public static boolean rangeCheck (int y, int x) {
		if (y >= 0 && x >= 0 && y < R && x < C) return true;
		return false;
	}
}
