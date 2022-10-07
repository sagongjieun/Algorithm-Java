import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 오! 나의 여신님

public class SWEA7793 {

	static int N, M;
	static char[][] map;
	static Queue<Node> sq;
	static Queue<Node> dq;

	static int answer;
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

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			stk = new StringTokenizer(br.readLine());

			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());

			map = new char[N][M];
			sq = new LinkedList<>();
			dq = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = s.charAt(j);

					if (map[i][j] == 'S') sq.add(new Node(i, j, 0)); // 수연이 초기위치 저장
					if (map[i][j] == '*') dq.add(new Node(i, j)); // 악마의 위치들 저장
				}
			}

			answer = Integer.MAX_VALUE;

			BFS();

			sb.append("#" + tc + " ");
			sb.append(answer == Integer.MAX_VALUE ? "GAME OVER" : answer);
			sb.append("\n");
		}

		System.out.print(sb);
	}

	private static void BFS() {

		while (!sq.isEmpty()) {

			// 악마 이동
			int dSize = dq.size();
			for (int d = 0; d < dSize; d++) {
				Node dcur = dq.poll();

				for (int i = 0; i < 4; i++) {
					int ny = dcur.y + dy[i];
					int nx = dcur.x + dx[i];

					// 악마가 이동할 수 있는 칸이라면 악마q에 저장하고 해당위치 악마위치로 변경
					if (isInRange(ny, nx) && (map[ny][nx] == '.' || map[ny][nx] == 'S')) {
						dq.add(new Node(ny, nx));
						map[ny][nx] = '*';
					}
				}
			}

			// 수연이 이동
			int sSize = sq.size();
			for (int s = 0; s < sSize; s++) {
				Node scur = sq.poll();

				for (int i = 0; i < 4; i++) {
					int ny = scur.y + dy[i];
					int nx = scur.x + dx[i];

					if (isInRange(ny, nx) && map[ny][nx] != 'S') {
						if (map[ny][nx] == 'D') {
							answer = Math.min(answer, scur.time + 1);
							break;
						}
						else if (map[ny][nx] == '.') {
							sq.add(new Node(ny, nx, scur.time + 1));
							map[ny][nx] = 'S';
						}
					}
				}
			}
		}
	}

	private static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < N && x < M) return true;
		return false;
	}
}
