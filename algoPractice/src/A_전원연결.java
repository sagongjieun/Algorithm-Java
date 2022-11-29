import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A_전원연결 {
	
	static int N, coreSize, answer;
	static int[][] map;
	static Node[] core;
	static boolean[] selected;
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
			selected = new boolean[12];
			
			coreSize = 0; // 코어 개수
			answer = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
					
					// 가장자리 제외하고 코어 위치 저장
					if (i >= 1 && i < N-1 && j >= 1 && j < N-1) {
						if (map[i][j] == 1) core[coreSize++] = new Node(i, j);
					}
				}
			}
			
			// 최대한 많은 core를 써야 하므로 내림차순 반복문
			for (int i = coreSize; i >= 0; i--) {
				combination(0, 0, i);
				if (answer < Integer.MAX_VALUE) break; // 최소값이 갱신되면 종료
			}
			
			sb.append("#" + tc + " " + answer + "\n");
		}
		
		System.out.print(sb);
	}

	/**
	 * 조합으로 size개의 코어를 선택한느 메소드
	 * @param index : 1~12개의 코어들 중 몇 번 째 코어인지
	 * @param count : 현재 몇 개의 코어를 사용했는지
	 * @param size : 몇 개의 코어를 사용해야 하는지
	 */
	private static void combination(int index, int count, int size) {
		// 종료 조건
		if (count == size) {
			// 선택된 코어들로 전선 길이의 합 구하기
			DFS(0, 0);
			return;
		}
		
		for (int i = index; i < coreSize; i++) {
			selected[i] = true; // i번째 코어 선택
			combination(i + 1, count + 1, size);
			selected[i] = false; // i번째 코어 선택 취소
		}
	}

	private static void DFS(int index, int curSum) {
		// 12개의 코어를 모두 살펴봤다면 지금까지 합한 전선의 길이로 정답 최소값 갱신하기
		if (index == coreSize) {
			answer = Math.min(answer, curSum);
			return;
		}
		
		// 선택된 코어가 아니라면 index 늘려서 다음 코어 살펴보기
		if (!selected[index]) {
			DFS(index + 1, curSum);
			return;
		}
		
		// 현재까지의 전선 길이가 answer보다 커지면 가지치기
		if (curSum > answer) return;
		
		// 선택된 코어라면 4방위를 살펴서 전선을 어떻게 놓을 지 결정하기
		for (int i = 0; i < 4; i++) {
			int y = core[index].y;
			int x = core[index].x;
			
			int line = 0;
			boolean success = false;
			
			// 전선 놓기
			while (true) {
				y += dy[i];
				x += dx[i];
				
				// 범위의 끝까지 갔다면 이 방향으로 성공
				if (y < 0 || x < 0 || y >= N || x >= N) {
					success = true;
					break;
				}
				
				if (map[y][x] != 0) break; // 전선이나 코어를 만나면 이 방향으로는 실패
				map[y][x] = 2; // 임의의 값으로 전선 표시
				line++; // 전선 길이 +1
			}
			
			if (success) DFS(index + 1, curSum + line);
			
			// 원래 상태로 되돌리기
			while (true) {
				y -= dy[i];
				x -= dx[i];
				
				// 코어의 좌표로 돌아오면 멈추기
				if (y == core[index].y && x == core[index].x) break;
				
				map[y][x] = 0; // 전선이 놓였던 곳은 빈 셀로 되돌리기
			}
		}
	}
}