import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BFS 스페셜 저지

public class BOJ16940 {
	
	static int N;
	static ArrayList<Integer>[] nodes;
	static int[] inputVisitOrder; 
	static int[] visitOrder; 
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		N = Integer.parseInt(br.readLine()); // 정점의 수
		nodes = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			nodes[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			stk = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			
			// 양방향 간선
			nodes[a].add(b);
			nodes[b].add(a);
		}
		
		inputVisitOrder = new int[N];
		stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			inputVisitOrder[i] = Integer.parseInt(stk.nextToken());
		}
		
		visitOrder = new int[N];
		visited = new boolean[N+1];
		
		int answer = specialJudge(1);
		
		System.out.print(answer);
	}

	private static int specialJudge(int start) {
		int index = 0;
		Queue<Integer> q = new LinkedList<>();
		visited[start] = true;
		visitOrder[index++] = start;
		q.add(start);
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int i = 0; i < nodes[cur].size(); i++) {
				int next = nodes[cur].get(i);
				
				if (!visited[next]) {
					q.add(next);
					visited[next] = true;
					visitOrder[index++] = next;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			if (visitOrder[i] != inputVisitOrder[i]) return 0;
		}
		
		return 1;
	}
}