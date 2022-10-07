import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 맥주 마시면서 걸어가기 - BFS

public class BOJ9205_BFS {

	static int N, sy, sx, ty, tx;
	static ArrayList<Node> stores;
	static boolean[] visited;
	static boolean arrived;

	static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			stores = new ArrayList<>();
			visited = new boolean[N];

			for (int i = 0; i < N+2; i++) {
				stk = new StringTokenizer(br.readLine());

				// 상근이 집
				if (i == 0) {
					sy = Integer.parseInt(stk.nextToken());
					sx = Integer.parseInt(stk.nextToken());
					continue;
				}
				// 페스티벌
				if (i == N+1) {
					ty = Integer.parseInt(stk.nextToken());
					tx = Integer.parseInt(stk.nextToken());
					continue;
				}
				// 편의점
				stores.add(new Node(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken())));
			}

			arrived = false;

			BFS();

			System.out.println(arrived ? "happy" : "sad");
		}
	}

	private static void BFS() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(sy, sx));

		while (!q.isEmpty()) {
			Node cur = q.poll();

			// 현재 장소에서 도착지까지 거리가 1000이하면 도착할 수 있으므로 종료
			if (getDistance(cur.y, cur.x, ty, tx) <= 1000) {
				arrived = true;
				break;
			}

			int size = stores.size();
			for (int i = 0; i < size; i++) {
				// i번째 편의점을 아직 방문하지 않았다면
				if (!visited[i]) {
					Node store = stores.get(i);
					int distance = getDistance(cur.y, cur.x, store.y, store.x);

					if (distance <= 1000) {
						visited[i] = true;
						q.add(new Node(store.y, store.x));
					}
				}
			}
		}
	}

	// 맨해튼 거리
	private static int getDistance(int y1, int x1, int y2, int x2) {
		return Math.abs(y1 - y2) + Math.abs(x1 - x2);
	}
}
