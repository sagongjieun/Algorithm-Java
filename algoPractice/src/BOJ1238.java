import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 파티

public class BOJ1238 {
	
	static int N, M, X;
	static int[] distance;
	static ArrayList<Node>[] nodes;
	static int answer = Integer.MIN_VALUE;
	
	static class Node implements Comparable<Node> {
		int v, time;

		public Node(int v, int time) {
			this.v = v;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			return this.time - o.time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken()); // 마을의 수 (정점)
		M = Integer.parseInt(stk.nextToken()); // 도로의 수 (간선)
		X = Integer.parseInt(stk.nextToken()); // 도착지점 X
		
		nodes = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			nodes[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(stk.nextToken());
			int end = Integer.parseInt(stk.nextToken());
			int time = Integer.parseInt(stk.nextToken());
			
			nodes[start].add(new Node(end, time));
		}
		
		for (int i = 1; i <= N; i++) {
			
			distance = new int[N + 1];
			for (int j = 1; j <= N; j++) {
				distance[j] = Integer.MAX_VALUE;
			}
			
			int result = Dijkstra(i) * 2;
			answer = Math.max(answer, result);
		}
		
		System.out.print(answer);
	}

	private static int Dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		distance[start] = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			// 도착지점 X에 도착하면 해당 최단시간 반환하고 종료
			if (cur.v == X) return distance[cur.v];
			
			// 정점 v의 현재까지의 최단시간보다 더 큰 최단시간을 갖는다면 패스
			if (cur.time > distance[cur.v]) continue;
			
			int size = nodes[cur.v].size();
			for (int i = 0; i < size; i++) {
				Node next = nodes[cur.v].get(i);
				
				if (distance[next.v] > next.time + cur.time) {
					distance[next.v] = next.time + cur.time;
					pq.add(new Node(next.v, distance[next.v]));
				}
			}
		}
		
		return 0;
	}

}