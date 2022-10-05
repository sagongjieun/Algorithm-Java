import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 치즈

public class BOJ2636 {

	static int N, M;
	static int[][] map;
	static boolean[][] visited;

	static int cheese, time, count;

	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());

				if (map[i][j] == 1) cheese++; // 치즈 개수 세기
			}
		}

		// 치즈 개수가 남아있을 때까지 반복
		while (cheese > 0) {
			time++; // 1시간지남
			count = cheese; // 현재 치즈 개수 저장해놓기
			meltingCheese();
		}

		System.out.println(time); // 치즈가 모두 녹아 없어지는데 걸리는 시간
		System.out.print(count); // 모두 녹기 전에 남아있는 치즈 개수
	}

	private static void meltingCheese() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0)); // (0,0)부터 시작
		
		visited = new boolean[N][M];
		visited[0][0] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			// cur을 기준으로 상하좌우 탐색하며
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];

				if (isInRange(ny, nx) && !visited[ny][nx]) {
					// 치즈라면 (치즈의 테두리라면)
					if (map[ny][nx] == 1) {
						cheese--; // 치즈 개수 줄이고
						map[ny][nx] = 0; // 녹은 상태로 만들기
					}
					// 치즈가 없는 곳이라면 q에 넣어서 그 좌표를 기준으로 다시 치즈 탐색
					else q.add(new Node(ny, nx));
					
					visited[ny][nx] = true;
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