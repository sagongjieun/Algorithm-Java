import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 알고스팟

public class BOJ1261 {

	static int M, N;
	static int[][] map;
	static boolean[][] visited;

	static int answer;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	static class Node implements Comparable<Node> {
		int y, x, count;

		public Node(int y, int x, int count) {
			this.y = y;
			this.x = x;
			this.count = count;
		}

		@Override
		public int compareTo(Node o) {
			// 벽을 부순 개수에 대해 오름차순 정렬
			return count - o.count;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		M = Integer.parseInt(stk.nextToken());
		N = Integer.parseInt(stk.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				int n = s.charAt(j) - '0';
				map[i][j] = n;
			}
		}

		visited = new boolean[N][M];

		BFS(0, 0);

		System.out.println(answer);
	}

	private static void BFS(int y, int x) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		visited[y][x] = true; // 시작점 방문처리
		pq.add(new Node(y, x, 0)); // 시작점 좌표와 부순 횟수 0 으로 초기화

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			// 도착지 도착하면 종료
			if (cur.y == N-1  && cur.x == M-1) {
				// 도착지에 도착했을 때의 벽을 부순 개수가 정답
				answer = cur.count;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];

				if (isInRange(ny, nx) && !visited[ny][nx]) {
					// 빈 방이라면
					if (map[ny][nx] == 0) {
						visited[ny][nx] = true;
						pq.offer(new Node(ny, nx, cur.count));
					}
					// 벽이라면
					else {
						visited[ny][nx] = true;
						pq.offer(new Node(ny, nx, cur.count + 1)); // 벽을 부순 카운트 + 1
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