import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// DFS와 BFS

public class BOJ1260 {
	
	static int N, M, V;
	static ArrayList<ArrayList<Integer>> nums = new ArrayList<>();
	static boolean[] visited;
	static StringBuilder sb = null;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		V = Integer.parseInt(stk.nextToken());
		
		// 초기화
		for (int i = 0; i < N+1; i++) {
			nums.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(stk.nextToken());
			int to = Integer.parseInt(stk.nextToken());
			// 양방향 간선
			nums.get(from).add(to);
			nums.get(to).add(from);
		}
		
		// 각 정점들에 연결되는 정점들을 오름차순 정렬
		for (int i = 1; i <= N; i++) {
			Collections.sort(nums.get(i));
		}
		
		// DFS용 visited배열 초기화
		visited = new boolean[N+1];
		sb = new StringBuilder();
		DFS(V);
		System.out.println(sb);

		// BFS용 visited배열 초기화
		visited = new boolean[N+1];
		sb = new StringBuilder();
		BFS(V);
		System.out.println(sb);
	}
	
	public static void DFS(int v) {
		visited[v] = true; // 방문 체크
		sb.append(v + " ");
		ArrayList<Integer> linked = getNode(v); // 정점 v의 간선에 연결된 정점들 구하기
		
		// 정점 v와 연결된 정점의 수만큼 반복
		for (int i = 0; i < linked.size(); i++) {
			int num = linked.get(i);
			
			if (!visited[num]) DFS(num); // 방문하지 않은 수면 DFS 탐색
			else continue; // 방문했다면 다음 수 체크
		}
	}
	
	public static void BFS(int v) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(v); // 큐에 시작 정점 v 삽입
		visited[v] = true; // 방문 체크
		sb.append(v + " ");
		
		// 큐가 빌 때까지 반복
		while (!q.isEmpty()) {
			int p = q.poll();
			ArrayList<Integer> linked = getNode(p); // 큐에서 dequeue한 정점과 연결된 정점들 구하기
			
			// 정점 v와 연결된 정점의 수만큼 반복
			for (int i = 0; i < linked.size(); i++) {
				int num = linked.get(i);
				
				// 방문하지 않은 수면
				if (!visited[num]) {
					q.offer(num); // q에 삽입
					visited[num] = true; // 방문 체크
					sb.append(num + " ");
				}
				else continue; // 방문했다면 다음 수 체크
			}
		}
	}

	// 정점 v와 연결된 다른 정점들을 ArrayList 형태로 반환
	public static ArrayList<Integer> getNode(int v) {
		return nums.get(v);
	}
}
