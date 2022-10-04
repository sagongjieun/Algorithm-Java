import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 프로세서 연결하기

public class SWEA1767 {

	static int N;
	static int[][] map;
	static Node[] cores;
	static boolean[] selected;
	
	static int coreCount;
	static int minLine;
	
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
			cores = new Node[12];
			selected = new boolean[12];
			
			coreCount = 0;
			minLine = Integer.MAX_VALUE;

			// 입력 받기
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			
			// 가장자리를 제외한 코어의 개수 세기
			for (int i = 1; i < N-1; i++) {
				for (int j = 1; j < N-1; j++) {
					if (map[i][j] == 1) {
						cores[coreCount] = new Node(i, j);
						coreCount++;
					}
				}
			}

			for (int i = coreCount; i >= 0; i--) {
				combination(0, 0, i);
				if (minLine < Integer.MAX_VALUE) break; // 최소값이 갱신되면 그때의 minLine이 정답
			}            

			sb.append("#" + tc + " " + minLine + "\n");
		}

		System.out.print(sb);
	}

	/**
	 * 
	 * @param depth : 몇 개를 뽑았는지
	 * @param start : 몇 번 인덱스부터 뽑을 지
	 * @param size : 몇 개의 코어로 탐색할 지
	 */
	private static void combination(int index, int count, int size) {
		if (count == size) {
			DFS(0, 0, size);
			return;
		}
		
		for (int i = index; i < size; i++) {
			selected[i] = true;
			combination(index + 1, count + 1, size);
			selected[i] = false;
		}
	}

	private static void DFS(int index, int count, int size) {
		if (index == size) {
			minLine = Math.min(minLine, count);
			return;
		}
		
		if (!selected[index]) {
			DFS(index + 1, count, size);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			Node cur = cores[index];
			int y = cur.y;
			int x = cur.x;
			
			boolean isSuccess = false;
			int lineCount = 0;
			
			while (true) {
				y += dy[i];
				x += dx[i];
				
				// 범위를 넘어가면 성공
				if (y < 0 || x < 0 || y >= N || x >= N) {
					isSuccess = true;
					break;
				}
				
				if (map[y][x] != 0) break; // 전선이나 코어를 만나면 실패
				map[y][x] = 2; // 전선이 설치됨을 표시
				lineCount++; // 전선 길이 +1
			}
			
			if (isSuccess) DFS(index + 1, count + lineCount, size);
			
			// 원상태로 되돌리기
			while (true) {
				y -= dy[i];
				x -= dx[i];
				
				if (y == cur.y && x == cur.x) break;
				map[y][x] = 0;
			}
		}
	}
}