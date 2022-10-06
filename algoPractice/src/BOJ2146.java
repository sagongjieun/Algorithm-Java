import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 다리 만들기

public class BOJ2146 {
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Node> edges;
	
	static int answer = Integer.MAX_VALUE;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	static class Node {
		int y, x, bridgeCount;

		public Node(int y, int x, int bridgeCount) {
			this.y = y;
			this.x = x;
			this.bridgeCount = bridgeCount;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		visited = new boolean[N][N];
		edges = new ArrayList<>();
		
		int flag = 1; // 섬을 구분짓기위한 flag
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					numberingIslands(i, j, flag); // 섬에 번호 붙이기
					flag++; // 다음 섬은 다른 번호로 지정해 구분짓기
				}
			}
		}
		
		// 모든 가장자리에 대해 다리를 놔보기
		for (Node edge : edges) {
			setBridge(edge.y, edge.x, map[edge.y][edge.x]);
		}
		
		System.out.print(answer);
	}

	private static void numberingIslands(int y, int x, int flag) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(y, x, 0));
		visited[y][x] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			map[cur.y][cur.x] = flag; // 섬에게 flag란 번호를 매김
			
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if (isInRange(ny, nx) && !visited[ny][nx]) {
					// cur 기준 상하좌우중에 섬이아닌 것이 있다면 cur이 가장자리임
					if (map[ny][nx] == 0) edges.add(cur); // 가장자리 리스트에 해당좌표 추가
					// 가장자리가 아니라면 q에 넣어서 flag 번호를 설정하기
					else if (map[ny][nx] == 1) {
						visited[ny][nx] = true;
						q.add(new Node(ny, nx, 0));
					}
				}
			}
		}
	}

	private static void setBridge(int y, int x, int num) {
		visited = new boolean[N][N]; // 방문 체크 배열 초기화
		
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(y, x, 0));
		visited[y][x] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			// answer보다 작아야하므로 answer보다 count가 커지면 패쓰
			if (cur.bridgeCount >= answer) continue;
			
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if (isInRange(ny, nx) && !visited[ny][nx]) {
					// 다른 섬의 가장자리를 만난다면
					if (map[ny][nx] != num && map[ny][nx] != 0) {
						// 지금까지 카운트 해왔던 걸로 최소값 갱신
						answer = Math.min(answer, cur.bridgeCount);
					}
					// 아직 다른 섬의 가장자리를 만나지 않았으니 다리 놓기
					if (map[ny][nx] == 0) {
						visited[ny][nx] = true;
						q.add(new Node(ny, nx, cur.bridgeCount + 1));
					}
				}
			}
		}
	}

	private static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < N && x < N) return true;
		return false;
	}
}