import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 맥주 마시면서 걸어가기 - 플로이드 와샬

public class BOJ9205_floyd_warshall {

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
			int N = Integer.parseInt(br.readLine()); // 편의점 개수
			ArrayList<Node> list = new ArrayList<>(); // 장소들의 좌표를 넣을 리스트
			boolean[][] isConnected = new boolean[N+2][N+2]; // 장소들이 연결돼있는지 확인할 배열

			for (int i = 0; i < N+2; i++) {
				stk = new StringTokenizer(br.readLine());
				list.add(new Node(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken())));
			}

			// i에서 j까지의 거리가 1000 이하면 연결가능
			for (int i = 0; i < N+2; i++) {
				Node cur = list.get(i);

				for (int j = 0; j < N+2; j++) {
					Node next = list.get(j);

					int distance = getDistance(cur.y, cur.x, next.y, next.x);
					if (distance <= 1000) isConnected[i][j] = true;
				}
			}

			for (int k = 0; k < N+2; k++) {
				for (int i = 0; i < N+2; i++) {
					for (int j = 0; j < N+2; j++) {
						if (!isConnected[i][j] && isConnected[i][k] && isConnected[k][j]) isConnected[i][j] = true;
					}
				}
			}

			System.out.println(isConnected[0][N+1] ? "happy" : "sad");
		}
	}

	// 맨해튼 거리
	private static int getDistance(int y1, int x1, int y2, int x2) {
		return Math.abs(y1 - y2) + Math.abs(x1 - x2);
	}
}
