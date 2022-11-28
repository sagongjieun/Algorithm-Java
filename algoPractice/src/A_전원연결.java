import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A_전원연결 {
	
	static int N, coreSize, min;
	static int[][] map;
	static Node[] core;
	static boolean[] visited;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
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
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			core = new Node[12];
			visited = new boolean[12];
			
			coreSize = 0; // 코어 개수
			min = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			
			// 가장자리 제외하고 코어 위치 저장
			for (int i = 1; i < N-1; i++) {
				for (int j = 1; j < N-1; j++) {
					if (map[i][j] == 1) core[coreSize++] = new Node(i, j);
				}
			}
			
			for (int i = coreSize; i >= 0; i--) {
				combination(0, 0, i);
				if (min < Integer.MAX_VALUE) break; // 최소값이 갱신되면 종료
			}
			
			sb.append("#" + tc + " " + min + "\n");
		}
		
		System.out.print(sb);
	}

	private static void combination(int index, int count, int size) {
		// 종료 조건
		if (count == size) {
			DFS(0, 0);
			return;
		}
		
		for (int i = index; i < coreSize; i++) {
			visited[i] = true;
			combination(index + 1, count + 1, size);
			visited[i] = false;
		}
	}

	private static void DFS(int index, int count) {
		if (index == coreSize) {
			min = Math.min(min, count);
			return;
		}
		
		// 부분 집합에 포함되는 코어들만 다음 단계로 진행
		if (!visited[index]) {
			DFS(index + 1, count);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int y = core[index].y;
			int x = core[index].x;
			int line = 0;
			boolean success = false;
			
			while (true) {
				y += dy[i];
				x += dx[i];
				
				// 범위의 끝까지 갔다면 성공
				if (y < 0 || x < 0 || y >= N || x >= N) {
					success = true;
					break;
				}
				
				if (map[y][x] != 0) break; // 전선이나 코어를 만나면 실패
				map[y][x] = 2; // 임의의 값으로 전선 표시
				line++; // 전선 길이 +1
			}
			
			if (success) DFS(index + 1, count + line);
			
			// 원래 상태로 되돌리기
			while (true) {
				y -= dy[i];
				x -= dx[i];
				
				if (y == core[index].y && x == core[index].x) break;
				
				map[y][x] = 0; // 빈 셀로 표시
			}
		}
	}
}