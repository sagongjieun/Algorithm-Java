import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 불!

public class BOJ4179 {

	static int R, C;
	static char[][] map;
	static Queue<Node> jq = new LinkedList<>();
	static Queue<Node> fq = new LinkedList<>();

	static int answer = Integer.MAX_VALUE;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	static class Node {
		int y, x, time;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}

		public Node(int y, int x, int time) {
			this.y = y;
			this.x = x;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);

				if (map[i][j] == 'J') jq.add(new Node(i, j, 0));
				else if (map[i][j] == 'F') fq.add(new Node(i, j));
			}
		}

		BFS();

		System.out.print(answer == Integer.MAX_VALUE ? "IMPOSSIBLE" : answer);
	}

	private static void BFS() {

		// 지훈이가 탈출할 때 까지 반복
		while (!jq.isEmpty()) {

			// 불의 확장
			int fSize = fq.size();
			for (int i = 0; i < fSize; i++) {
				Node cur = fq.poll();

				for (int j = 0; j < 4; j++) {
					int ny = cur.y + dy[j];
					int nx = cur.x + dx[j];

					if (isInRange(ny, nx) && (map[ny][nx] == '.' || map[ny][nx] == 'J')) {
						fq.add(new Node(ny, nx));
						map[ny][nx] = 'F';
					}
				}
			}

			// 지훈이의 이동
			int jSize = jq.size();
			for (int i = 0; i < jSize; i++) {
				Node cur = jq.poll();

				for (int j = 0; j < 4; j++) {
					int ny = cur.y + dy[j];
					int nx = cur.x + dx[j];

					// 다음 칸이 범위를 벗어난다면 탈출
					if (!isInRange(ny, nx)) {
						answer = Math.min(answer, cur.time + 1);
						break;
					}
					// 다음 칸이 빈칸일 때만 이동
					if (isInRange(ny, nx) && map[ny][nx] == '.') {
						jq.add(new Node(ny, nx, cur.time + 1));
						map[ny][nx] = 'J';
					}
				}
			}
		}
	}

	private static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < R && x < C) return true;
		return false;
	}
}
