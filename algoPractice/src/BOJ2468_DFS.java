import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DFS

public class BOJ2468_DFS {
	
	static int N;
	static int[][] area;
	static int answer = 1;
	static int maxHeight = 0;
	static boolean[][] visited;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		N = Integer.parseInt(br.readLine());
		area = new int[N][N];
		
		for (int i=0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				int height = Integer.parseInt(stk.nextToken());
				maxHeight = Math.max(maxHeight, height); // area에서 최대 높이 구하기
				area[i][j] = height;
			}
		}

		// maxHeight-1 : 최대높이에서 시작하면 다 잠긴 상태이니 최대높이-1에서 시작
		for (int height=maxHeight-1; height>0; height--) {
			int safeArea = 0; // 기준 높이에서의 안전영역 개수
			visited = new boolean[N][N]; // 기준 높이별로 완전 탐색이기 때문에 방문 체크 여기서 선언
			
			// 그래프 내의 모든 경우의 수 구하기
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					// 안전영역 기준에 맞고 아직 방문하지 않은 곳이라면 해당 구역을 기준으로 탐색
					if (area[i][j] > height && !visited[i][j]) {
						safeArea += DFS(i, j, height);
					}
				}
			}
			
			System.out.println(answer);
			System.out.println(safeArea);
			
			// 기준 높이별로 구한 안전영역 개수가 최대값이라면 answer에 갱신
			answer = Math.max(answer, safeArea);
		}
		
//		System.out.print(answer);
	}
	
	public static int DFS(int y, int x, int h) {
		visited[y][x] = true; // 탐색하는 곳은 방문 체크
		
		// 상하좌우 탐색
		for (int i=0; i<4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			// 탐색하는 곳 사방면으로
			// 범위를 벗어나지 않고 안전영역 기준에 맞고 아직 방문하지 않은 곳이 있다면
			// 그 곳을 기준으로 다시 탐색시작
			if (rangeCheck(ny, nx) && area[ny][nx] > h && !visited[ny][nx]) {
				DFS(ny, nx, h);
			}
		}
		
		// 구역 개수 1 리턴
		return 1;
	}
	
	// 배열 범위를 벗어나는지 범위 체크
	public static boolean rangeCheck(int y, int x) {
		if (y >= 0 && x >= 0 && y < N && x < N) return true;
		return false;
	}
}
