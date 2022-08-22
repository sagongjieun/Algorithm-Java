import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 구슬 탈출 2

public class BOJ13460 {

	static int N, M;
	static char[][] board;
	static int holeY, holeX;
	static Bead red, blue;

	static boolean[][][][] visited;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	static class Bead {
		int ry, rx; // 빨간구슬 좌표
		int by, bx; // 파란구슬 좌표
		int count;

		public Bead(int ry, int rx, int by, int bx, int count) {
			this.ry = ry;
			this.rx = rx;
			this.by = by;
			this.bx = bx;
			this.count = count;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		board = new char[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = s.charAt(j);

				if (c == 'O') {
					holeY = i;
					holeX = j;
				}
				if (c == 'R') red = new Bead(i, j, 0, 0, 0);
				if (c == 'B') blue = new Bead(0, 0, i, j, 0);

				board[i][j] = c;
			}
		}

		visited = new boolean[N][M][N][M];

		System.out.print(BFS());
	}

	private static int BFS() {
		Queue<Bead> q = new LinkedList<>();
		q.add(new Bead(red.ry, red.rx, blue.by, blue.bx, 1)); // 초기 구슬의 위치와 수행횟수 1 삽입
		visited[red.ry][red.rx][blue.ry][blue.rx] = true; // 초기 구슬의 위치 방문 체크

		while (!q.isEmpty()) {
			Bead bead = q.poll();

			int curRY = bead.ry;
			int curRX = bead.rx;
			int curBY = bead.by;
			int curBX = bead.bx;
			int curCount = bead.count;

			if (curCount > 10) return -1;

			for (int i = 0; i < 4; i++) {
				int newRY = curRY;
				int newRX = curRX;
				int newBY = curBY;
				int newBX = curBX;

				boolean isRedGoal = false;
				boolean isBlueGoal = false;

				// 빨간구슬이 벽을 만날때까지 같은방향으로 이동
				while (board[newRY + dy[i]][newRX + dx[i]] != '#') {
					newRY += dy[i];
					newRX += dx[i];

					// 이동하다가 구멍을 만나면 빨간구슬이 구멍만남 상태 true로 하고 break
					if (newRY == holeY && newRX == holeX) {
						isRedGoal = true;
						break;
					}
				}

				// 파란구슬이 벽을 만날때까지 같은방향으로 이동
				while (board[newBY + dy[i]][newBX + dx[i]] != '#') {
					newBY += dy[i];
					newBX += dx[i];

					// 이동하다가 구멍을 만나면 파란구슬이 구멍만남 상태 true로 하고 break
					if (newBY == holeY && newBX == holeX) {
						isBlueGoal = true;
						break;
					}
				}

				// 파란구슬이 구멍에 빠지면 실패 -> 큐에 있는 다음좌표 확인하러
				if (isBlueGoal) continue;

				// 빨간 구슬만 구멍에 빠지면 횟수 return
				if (isRedGoal && !isBlueGoal) return curCount;

				// 둘다 구멍에 안빠졌는데 이동할위치가 동일하다면
				if (newRY == newBY && newRX == newBX) {
					// 위쪽으로 기울일 때
					if (i == 0) {
						// 더 큰 y값을 가지는 구슬이 뒤로 감
						if (curRY > curBY) newRY -= dy[i];
						else newBY -= dy[i];
					}
					// 오른쪽으로 기울일 때
					else if (i == 1) {
						// 더 작은 x값을 가지는 구슬이 뒤로 감
						if (curRX < curBX) newRX -= dx[i];
						else newBX -= dx[i];
					}
					// 아래쪽으로 기울일 때
					else if (i == 2) {
						// 더 작은 y값을 가지는 구슬이 뒤로 감
						if (curRY < curBY) newRY -= dy[i];
						else newBY -= dy[i];
					}
					// 왼쪽으로 기울일 때
					else {
						// 더 큰 x값을 가지는 구슬이 뒤로 감
						if (curRX > curBX) newRX -= dx[i];
						else newBX -= dx[i];
					}
				}

				// 구슬이 이동할 위치가 처음 방문하는 경우에만 이동 (큐에 삽입)
				if (!visited[newRY][newRX][newBY][newBX]) {
					visited[newRY][newRX][newBY][newBX] = true;
					q.add(new Bead(newRY, newRX, newBY, newBX, curCount + 1));
				}
			}
		}

		return -1;
	}
}