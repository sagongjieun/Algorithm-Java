import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 촌수계산

public class BOJ2644 {
	
	static int n, m;
	static int from, to;
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	static int answer = -1;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		n = Integer.parseInt(br.readLine()); // 사람의 수 = 정점의 수
		stk = new StringTokenizer(br.readLine());
		from = Integer.parseInt(stk.nextToken()); // 타겟 두명
		to = Integer.parseInt(stk.nextToken()); 
		m = Integer.parseInt(br.readLine()); // 관계의 수 = 간선의 수
		
		list = new ArrayList[n + 1];
		visited = new boolean[n + 1];
		
		for (int i = 0; i < n + 1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			stk = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			
			// 양방향
			list[p].add(c);
			list[c].add(p);
		}
		
		System.out.println(Arrays.toString(list));
		
		BFS();
		
		System.out.print(answer);
	}

	public static void BFS() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {from, 0}); // 출발점과 count 삽입
		visited[from] = true; // 방문 체크
		
		// 큐가 빌 때까지 반복
		while (!q.isEmpty()) {
			int[] cur = q.poll(); // dequeue
			
			// 종료 조건
			// 목적지인지 판단하고 종료
			if (cur[0] == to) {
				answer = cur[1]; // 지금까지 count한 수가 정답
				return;
			}
			
			// 아니라면 내것과 연결된 모든 노드들을 큐에 삽입
			for (Integer node : list[cur[0]]) {
				if (!visited[node]) {
					// 내것과 연결된 정점과 count + 1을 삽입
					q.offer(new int[] {node, cur[1] + 1});
					// 방문 체크
					visited[node] = true;
				}
			}
		}
	}
}
