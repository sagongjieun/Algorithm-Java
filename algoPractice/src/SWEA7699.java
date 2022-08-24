import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 수지의 수지 맞는 여행

public class SWEA7699 {

	static int R, C;
	static char[][] map;
	static boolean[] visited;
	static int answer;

	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

		// 테스트 케이스만큼 반복
		for (int tc = 1; tc <= T; tc++) {
			stk = new StringTokenizer(br.readLine());
			R = Integer.parseInt(stk.nextToken());
			C = Integer.parseInt(stk.nextToken());

			// RxC 크기의 2차원배열 map에 입력받은 문자를 하나씩 넣기
			map = new char[R][C];
			for (int i = 0; i < R; i++) {
				String s = br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = s.charAt(j);
				}
			}

			// 알파벳 개수만큼 방문체크 배열 생성
			visited = new boolean[26];
			answer = 0;
			visited[map[0][0] - 'A'] = true; // 맨처음 시작하는 (0, 0)은 방문 처리

			// (0, 0)은 방문처리 이미 했으므로 count 는 1로 시작
			DFS(0, 0, 1);

			System.out.println("#" + tc + " " + answer);
		}
	}

	private static void DFS(int y, int x, int count) {

		// 최대값 갱신
		if (count > answer) answer = count;

		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			// 해당 알파벳을 아직 방문하지 않았다면
			if (rangeCheck(ny, nx) && !visited[map[ny][nx] - 'A']) {
				// 방문처리하고 DFS 탐색
				visited[map[ny][nx] - 'A'] = true;
				DFS(ny, nx, count + 1);
				// 탐색 후에는 해당 알파벳 방문 처리 취소
				visited[map[ny][nx] - 'A'] = false;
			}
		}
	}

	// 범위 체크 함수
	private static boolean rangeCheck(int y, int x) {
		if (y >= 0 && x >= 0 && y < R && x < C) return true;
		return false;
	}
}