import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 탈주범 검거

public class SWEA1953 {

	static int N, M, R, C, L;
	static int[][] map;
	static boolean[][] visited;

	static int answer;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	static class Node {
		int y, x, time;

		public Node(int y, int x, int time) {
			this.y = y;
			this.x = x;
			this.time = time;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			stk = new StringTokenizer(br.readLine());

			N = Integer.parseInt(stk.nextToken()); // 지도 r
			M = Integer.parseInt(stk.nextToken()); // 지도 c
			R = Integer.parseInt(stk.nextToken()); // 맨홀 y
			C = Integer.parseInt(stk.nextToken()); // 맨홀 x
			L = Integer.parseInt(stk.nextToken()); // 소요시간

			map = new int[N][M];
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}

			visited = new boolean[N][M];
			answer = 0;

			BFS(R, C);

			sb.append("#" + tc + " " + answer + "\n");
		}

		System.out.print(sb);
	}

	private static void BFS(int y, int x) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(y, x, 0));
		visited[y][x] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			if (cur.time  >= L) continue;

			answer++;

			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];

				if (isInRange(ny, nx) && !visited[ny][nx] && map[ny][nx] != 0 && isConnected(map[cur.y][cur.x], map[ny][nx], i)) {
					q.add(new Node(ny, nx, cur.time + 1));
					visited[ny][nx] = true;
				}
			}
		}
	}

	/**
	 * @param cur : 현재 좌표의 파이프 모양
	 * @param next : 다음 좌표의 파이프 모양
	 * @param d : 현재 좌표에서 상하좌우 중 어디인지 (0:상, 1:우, 2:하, 3:좌)
	 * @return : 현재 좌표의 파이프와 다음 좌표의 파이프가 연결돼있다면 true, 아니라면 false
	 */
	private static boolean isConnected(int cur, int next, int d) {
		if (cur == 1) {
			if (d == 0) {
				if (next == 1 || next == 2 || next == 5 || next == 6) return true;
			}
			else if (d == 1) {
				if (next == 1 || next == 3 || next == 6 || next == 7) return true;
			}
			else if (d == 2) {
				if (next == 1 || next == 2 || next == 4 || next == 7) return true;
			}
			else if (d == 3) {
				if (next == 1 || next == 3 || next == 4 || next == 5) return true;
			}
		}
		else if (cur == 2) {
			if (d == 0) {
				if (next == 1 || next == 2 || next == 5 || next == 6) return true;
			}
			else if (d == 2) {
				if (next == 1 || next == 2 || next == 4 || next == 7) return true;
			}
		}
		else if (cur == 3) {
			if (d == 1) {
				if (next == 1 || next == 3 || next == 6 || next == 7) return true;
			}
			else if (d == 3) {
				if (next == 1 || next == 3 || next == 4 || next == 5) return true;
			}
		}
		else if (cur == 4) {
			if (d == 0) {
				if (next == 1 || next == 2 || next == 5 || next == 6) return true;
			}
			else if (d == 1) {
				if (next == 1 || next == 3 || next == 6 || next == 7) return true;
			}
		}
		else if (cur == 5) {
			if (d == 1) {
				if (next == 1 || next == 3 || next == 6 || next == 7) return true;
			}
			else if (d == 2) {
				if (next == 1 || next == 2 || next == 4 || next == 7) return true;
			}
		}
		else if (cur == 6) {
			if (d == 2) {
				if (next == 1 || next == 2 || next == 4 || next == 7) return true;
			}
			else if (d == 3) {
				if (next == 1 || next == 3 || next == 4 || next == 5) return true;
			}
		}
		else if (cur == 7) {
			if (d == 3) {
				if (next == 1 || next == 3 || next == 4 || next == 5) return true;
			}
			else if (d == 0) {
				if (next == 1 || next == 2 || next == 5 || next == 6) return true;
			}
		}
		return false;
	}

	private static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < N && x < M) return true;
		return false;
	}
}