import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// Contact

public class SWEA1238 {
	
	static int size, start;
	static int[][] graph;
	static int[] visited;
	static int answer, maxDepth;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		// 테스트 케이스 10개만큼 반복
		for (int tc = 1; tc <= 10; tc++) {
			stk = new StringTokenizer(br.readLine());
			size = Integer.parseInt(stk.nextToken()); // 입력받는 데이터의 길이
			start = Integer.parseInt(stk.nextToken()); // 시작점
			graph = new int[101][101]; // 최대 100개의 정점을 가짐
			visited = new int[101]; // 최대 100개의 정점의 방문처리 배열
			answer = 0;
			maxDepth = 0;
			
			stk = new StringTokenizer(br.readLine());
			// 한줄 입력에 from to from to 형식이므로 입력받는 데이터 길이의 /2로 하여
			// from to를 얻고 graph에서 해당 좌표에 간선이 있음을 표시
			for (int i = 0; i < size / 2; i++) {
				int from = Integer.parseInt(stk.nextToken());
				int to = Integer.parseInt(stk.nextToken());
				graph[from][to] = 1;
			}
			
			// 시작 정점으로부터 가까이 연결된 것 중심으로 탐색해야 하므로 BFS탐색
			BFS(start);
			
			System.out.printf("#%d %d\n", tc, answer);
		}
	}

	public static void BFS(int start) {
		Queue<Integer> q = new LinkedList<>();
		
		q.offer(start); // 시작 정점 q에 삽입
		visited[start] = 1; // 시작 정점 방문 처리
		
		// 큐가 빌 때까지 반복
		while (!q.isEmpty()) {
			int from = q.poll();
			
			for (int to = 1; to <= 100; to++) {
				// from에서 to로 가는 간선이 존재하고
				// 아직 to번째 정점을 방문하지 않았다면
				if (graph[from][to] == 1 && visited[to] == 0) {
					// to 정점의 방문깊이는 from 깊이의 + 1
					visited[to] = visited[from] + 1;
					q.offer(to);
				}
			}
			
			// q가 빌 때까지 최대깊이 계속해서 갱신
			maxDepth = visited[from];
		}
		
		// 가능한 모든 수 중에서
		for (int i = 1; i <= 100; i++) {
			// 방문 깊이가 최대깊이인 정점들에 대해 최대값 찾기
			if (maxDepth == visited[i]) {
				answer = Math.max(answer, i);
			}
		}
	}

}
