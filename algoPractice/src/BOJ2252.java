import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 줄 세우기

public class BOJ2252 {
	
	static int N, M;
	static int[] degree;
	static ArrayList<Integer>[] graph;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken()); // 학생의 수
		M = Integer.parseInt(stk.nextToken()); // 키를 비교한 횟수
		
		degree = new int[N + 1]; // 진입차수 저장 배열
		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(stk.nextToken());
			int B = Integer.parseInt(stk.nextToken());
			
			graph[A].add(B); // 반드시 A의 뒤에 있어야 하는 B를 A의 리스트에 저장
			degree[B]++; // B의 진입차수는 +1
		}
		
		topologySort(); // 위상정렬
		
		System.out.print(sb);
	}

	private static void topologySort() {
		Queue<Integer> q = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) {
			// 진입차수가 0인 값 큐에 넣기
			if (degree[i] == 0) q.add(i);
		}
		
		while (!q.isEmpty()) {
			int curStudent = q.poll();
			sb.append(curStudent + " ");
			
			// 현재 학생의 뒤에 있어야 하는 학생의 수만큼 반복
			for (int i = 0; i < graph[curStudent].size(); i++) {
				int nextStudent = graph[curStudent].get(i); // 다음 학생의 번호 얻기
				degree[nextStudent]--; // 다음 학생의 진입차수는 -1
				
				if (degree[nextStudent] == 0) q.add(nextStudent); // 학생의 진입차수가 0이되면 q에 넣기
			}
		}
	}
}