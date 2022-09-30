import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 말이 되고픈 원숭이

public class BOJ1600 {

	static int K, W, H;
	static int[][] map;
	static boolean[][][] visited;
	static int answer = -1;

	static int[] hy = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] hx = {1, 2, 2, 1, -1, -2, -2, -1};

	static int[] my = {-1, 0, 1, 0};
	static int[] mx = {0, 1, 0, -1};

	static class Node {
		int y, x, moveCount, horseCount;

		public Node(int y, int x, int moveCount, int horseCount) {
			this.y = y;
			this.x = x;
			this.moveCount = moveCount;
			this.horseCount = horseCount;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;

		K = Integer.parseInt(br.readLine());
		stk = new StringTokenizer(br.readLine());
		W = Integer.parseInt(stk.nextToken());
		H = Integer.parseInt(stk.nextToken());

		map = new int[H][W];
		for (int i = 0; i < H; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}

		// 방문체크 시, 말로서 움직였는지도 체크해줘야함
		visited = new boolean[H][W][K+1];

		BFS(0, 0);

		System.out.print(answer);
	}

	private static void BFS(int y, int x) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(y, x, 0, 0));
		visited[y][x][0] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			// 도착지점에 도착하면 종료
			if (cur.y == H-1 && cur.x == W-1) {
				answer = cur.moveCount;
				break;
			}

			// 말로 이동하기
			// 현재 말 카운트가 K개가 안됐을 때만
			if (cur.horseCount < K) {
				for (int i = 0; i < 8; i++) {
					int ny = hy[i] + cur.y;
					int nx = hx[i] + cur.x;

					if (isInRange(ny, nx) && !visited[ny][nx][cur.horseCount + 1] && map[ny][nx] != 1) {
						// (ny, nx) 좌표의 말 카운트를 늘려주고 해당 상태를 방문체크
						visited[ny][nx][cur.horseCount + 1] = true;
						q.add(new Node(ny, nx, cur.moveCount + 1, cur.horseCount + 1));
					}
				}
			}

			// 원숭이로 이동하기
			for (int i = 0; i < 4; i++) {
				int ny = my[i] + cur.y;
				int nx = mx[i] + cur.x;

				if (isInRange(ny, nx) && !visited[ny][nx][cur.horseCount] && map[ny][nx] != 1) {
					visited[ny][nx][cur.horseCount] = true;
					q.add(new Node(ny, nx, cur.moveCount + 1, cur.horseCount));
				}
			}

		}
	}

	private static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < H && x < W) return true;
		return false;
	}
}