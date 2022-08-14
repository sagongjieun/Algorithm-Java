import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 치킨 배달

public class BOJ15686 {
	
	static int N, M;
	static int[][] map;
	static int answer = Integer.MAX_VALUE;
	
	static ArrayList<int[]> chickenList = new ArrayList<>();
	static ArrayList<int[]> houseList = new ArrayList<>();
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		map = new int[N][N];
		for (int i=0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				
				// 집들의 좌표 저장
				if (map[i][j] == 1) {
					houseList.add(new int[] {i, j});
				}
				// 치킨집들의 좌표 저장
				else if (map[i][j] == 2) {
					chickenList.add(new int[] {i, j});
				}
			}
		}

		// 치킨집들에 대한 visited 배열 생성
		visited = new boolean[chickenList.size()];
		
		getChickenDistance(0, 0);
		
		// 정답 출력
		System.out.print(answer);
	}
	
	// count : 폐업시킬 치킨집의 수
	public static void getChickenDistance(int index, int count) {
		// 종료 조건
		if (count == M) {
			int cityChickenDistance = 0; // 도시의 치킨거리
			
			// 모든 집들에 대해 모든 치킨집들과의 치킨 거리를 구하기
			for (int i=0; i<houseList.size(); i++) {
				int minChickenDistance = Integer.MAX_VALUE;
				
				for (int j=0; j<chickenList.size(); j++) {
					if (visited[j]) {
						// i번째 집에서 j번째 치킨집까지의 치킨 거리 구하기
						int distance = Math.abs(houseList.get(i)[0] - chickenList.get(j)[0]) + Math.abs(houseList.get(i)[1] - chickenList.get(j)[1]);
						// i번째 집에서 모든 치킨집까지의 치킨 거리중 최소값을 sum에 담기
						minChickenDistance = Math.min(minChickenDistance,  distance);
					}
				}
				
				// 도시의 치킨거리에 각 집에서 도출된 최소의 치킨 거리 더하기
				cityChickenDistance += minChickenDistance;
			}
			
			answer = Math.min(cityChickenDistance, answer);
			return;
		}
		
		for (int i=index; i<chickenList.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				getChickenDistance(i+1, count+1);
				visited[i] = false;
			}
		}
	}
}