import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 색종이 붙이기

public class BOJ17136 {

	static int[][] map;
	static int[] usedPaper = {0, 5, 5, 5, 5, 5};
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;

		map = new int[10][10];
		for (int i = 0; i < 10; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}

		DFS(0, 0, 0);
		
		System.out.print(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	private static void DFS(int y, int x, int count) {
		// 좌표의 맨 끝에 도달하면 answer를 최소값으로 갱신하고 백트래킹
		// map에서 가질 수있는 가장 마지막 값이 (9, 9)번째 좌표의 값이고
		// 그 다음값은 (9, 10) 이기때문에 종료조건의 좌표를 이렇게 세팅
		if (y == 9 && x == 10) {
			answer = Math.min(answer, count);
			return;
		}
		// count가 현재까지 정답보다 더 커지는 순간 탐색할 필요 없어짐
		if (count >= answer) return;
		
		// 열이 범위를 벗어나면 다음 행 탐색
		if (x > 9) {
			DFS(y + 1, 0, count);
			return;
		}
		
		// 색종이 붙일 수 있는 좌표를 만나면
		if (map[y][x] == 1) {
			// 붙일 수 있는 색종이의 최대크기부터 하나씩 확인
			for (int size = 5; size >= 1; size--) {
				// 색종이를 다 사용하지 않았고, 해당 크기의 색종이를 붙일 수 있다면
				if (usedPaper[size] > 0 && isAttachable(y, x, size)) {
					attach(y, x, size, 0); // 색종이 붙이기
					usedPaper[size]--; // 색종이 사용하였으므로 개수 -1
					
					DFS(y, x+1, count+1); // 색종이 붙인상태로 나머지 좌표 다시 훑기
					
					attach(y, x, size, 1); // 색종이 떼기
					usedPaper[size]++; // 색종이 개수 원상복구
				}
			}
		} else DFS(y, x+1, count); // 1을 찾아 다음 열 탐색
	}

	/**
	 * 색종이 붙이는 함수
	 * @param y : 행 좌표
	 * @param x : 열 좌표
	 * @param size : 색종이 크기
	 * @param attachStatus : 붙일건지 뗄 건지의 상태 (0 = 붙임, 1 = 뗌)
	 * 색종이를 붙였으면 방문체크처럼 해당 부분은 0으로 만들어줘야 다른 구역에서 중복으로 붙일 일이 없어짐
	 */
	private static void attach(int y, int x, int size, int attachStatus) {
		for (int i = y; i < y + size; i++) {
			for (int j = x; j < x + size; j++) {
				map[i][j] = attachStatus;
			}
		}
	}

	// 색종이 붙일 수 있는지 확인
	private static boolean isAttachable(int y, int x, int size) {
		for (int i = y; i < y + size; i++) {
			for (int j = x; j < x + size; j++) {
				if (!isInRange(i, j)) return false; // 좌표가 범위를 벗어나면 false
				if (map[i][j] != 1) return false; // 좌표 중 하나라도 1이 아니면 false
			}
		}
		return true;
	}

	// 범위 체크 함수
	private static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < 10 && x < 10) return true;
		return false;
	}
}