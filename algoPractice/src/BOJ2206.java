import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 벽 부수고 이동하기

public class BOJ2206 {

	static int N, M;
	static int[][] map;
	static boolean[][][] visited;
	static Queue<Node> q = new LinkedList<>();

	static int answer = Integer.MAX_VALUE;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	static class Node {
		int y, x, count;
		boolean isBreak;

		public Node(int y, int x, int count, boolean isBreak) {
			this.y = y;
			this.x = x;
			this.count = count;
			this.isBreak = isBreak;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = s.charAt(j);
				map[i][j] = c - '0';
			}
		}

		visited = new boolean[N][M][2];

		BFS();

		System.out.print(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	private static void BFS() {

		// 시작부터 count 하므로 count를 1로 세팅
		q.add(new Node(0, 0, 1, false));

		while (!q.isEmpty()) {
			Node cur = q.poll();

			// 끝에 도착했다면 count 한 값으로 answer의 최소값 갱신
			if (cur.y == N-1 && cur.x == M-1) {
				answer = Math.min(answer, cur.count);
				return;
			}

			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];

				if (isInRange(ny, nx)) {
					// 벽이 아니라면
					if (map[ny][nx] == 0) {
						// 이전 좌표까지 벽을 부순적이 없고 아직 방문하지 않은 좌표라면
						if (!cur.isBreak && !visited[ny][nx][0]) {
							// ny, nx 좌표에 벽을 부시지 않고 도달한 방문 표시를 하고 q에 넣기
							visited[ny][nx][0] = true;
							q.add(new Node(ny, nx, cur.count + 1, false));
						}
						// 이전 좌표까지 벽을 부순적이 있고 아직 방문하지 않은 좌표라면
						else if (cur.isBreak && !visited[ny][nx][1]) {
							// ny, nx 좌표에 벽을 부수고 도달한 방문 표시를 하고 q에 넣기
							visited[ny][nx][1] = true;
							q.add(new Node(ny, nx, cur.count + 1, true));
						}
					}
					// 벽이면
					else if (map[ny][nx] == 1) {
						// 이전 좌표까지 벽을 부순적이 없어야 함
						if (!cur.isBreak) { 
							// ny, nx 좌표에 벽을 부수고 도달한 방문 표시를 하고 q에 넣기
							visited[ny][nx][1] = true;
							q.add(new Node(ny, nx, cur.count + 1, true));
						}
					}
				}
			}
		}
	}

	// 범위 체크 함수
	private static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < N && x < M) return true;
		return false;
	}
}