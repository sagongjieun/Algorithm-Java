import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 알파벳

public class BOJ1987 {
	
	static int R, C;
	static int[][] alp;
	static boolean[] visited;
	
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		
		// 각 알파벳을 정수로 변환하여 배열에 저장
		alp = new int[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				alp[i][j] = s.charAt(j) - 'A';
			}
		}
		
		visited = new boolean[26]; // 알파벳 개수만큼 배열 크기를 초기화
		
		DFS(0, 0, 0);

		System.out.print(answer);
	}

	public static void DFS(int y, int x, int count) {
		// 알파벳이 이미 저장돼있다면
		if (visited[alp[y][x]]) {
			// 최대값 answer를 찾고 백트래킹
			answer = Math.max(answer, count);
			return;
		}
		
		// 알파벳 저장
		visited[alp[y][x]] = true;
		
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if (rangeCheck(ny, nx)) DFS(ny, nx, count + 1);
		}
		
		// 다른 경우의 수를 찾기 위해 알파벳 저장 취소
		visited[alp[y][x]] = false;
	}

	// 범위 체크 함수
	private static boolean rangeCheck(int y, int x) {
		if (y >= 0 && x >= 0 && y < R && x < C) return true;
		return false;
	}
}