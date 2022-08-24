import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 토마토 - visited 사용하지 않은 버전

public class BOJ7576_no_visited_version {

	static int M, N;
	static int[][] box;
	static Queue<int[]> tomatos = new LinkedList<>();

	static int answer = 0;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		M = Integer.parseInt(stk.nextToken()); // 열
		N = Integer.parseInt(stk.nextToken()); // 행

		box = new int[N][M];
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(stk.nextToken());
				// 익은 토마토의 좌표를 큐에 저장하기
				if (box[i][j] == 1) tomatos.add(new int[] {i, j});
			}
		}

		BFS();

		boolean isAllRiped = true;
		// BFS 탐색 후에 모든 토마토가 익었는지 확인하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 하나라도 익지 않은 것이 있다면 탐색 멈추기
				if (box[i][j] == 0) {
					isAllRiped = false;
					break;
				}
			}

			if (!isAllRiped) break;
		}

		System.out.print(isAllRiped ? answer : -1);
	}

	private static void BFS() {

		// 익을 수 있는 토마토가 있을 때까지 반복
		while(!tomatos.isEmpty()) {

			int size = tomatos.size();
			int count = 0; // 익을 수 있는 토마토의 개수 count
			for (int i = 0; i < size; i++) {
				int[] cur = tomatos.poll();
				int curY = cur[0];
				int curX = cur[1];

				for (int j = 0; j < 4; j++) {
					int ny = curY + dy[j];
					int nx = curX + dx[j];

					if (isInRange(ny, nx) && box[ny][nx] == 0) {
						// 다음 위치에 익지 않은 토마토가 있으면
						box[ny][nx] = 1; // 방문 체크하듯 다음에 탐색할 위치를 익은 토마토로 바꾸기
						tomatos.add(new int[] {ny, nx}); // 다음 탐색 좌표로 큐에 넣기
						count += 1; // 익을 수 있는 토마토 +1
					}
				}
			}

			// 이번 턴에서 익을 수 있는 토마토가 한개라도 있었으면 하루를 썼다는 의미
			if (count > 0) answer += 1;
		}
	}

	// 범위 체크 함수
	public static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < N && x < M) return true;
		return false;
	}
}