import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 최소비용 구하기

public class BOJ1916 {

	static int N, M;
	static int[] distance;
	static ArrayList<Node>[] nodes;

	static int start, end;

	static class Node implements Comparable<Node> {
		int to, cost;

		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;

		N = Integer.parseInt(br.readLine()); // 도시의 수
		M = Integer.parseInt(br.readLine()); // 버스의 수

		// 각 정점(도시)의 최소비용 무한대로 초기화
		distance = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			distance[i] = Integer.MAX_VALUE;
		}

		// 인접리스트 초기화
		nodes = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			nodes[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(stk.nextToken());
			int to = Integer.parseInt(stk.nextToken());
			int cost = Integer.parseInt(stk.nextToken());

			nodes[from].add(new Node(to, cost));
		}

		stk = new StringTokenizer(br.readLine());
		start = Integer.parseInt(stk.nextToken());
		end = Integer.parseInt(stk.nextToken());

		dijkstra(start);
		
		System.out.print(distance[end]);
	}

	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		// 시작 정점의 비용 0 으로 초기화
		pq.add(new Node(start, 0));
		distance[start] = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			// 도착도시에 도착했다면 멈추기
			if (cur.to == end) break;
			
			// 현재까지의 최단경로보다 더 높은 비용으로 도착하면 넘어가기
			if (cur.cost > distance[cur.to]) continue;
			
			int size = nodes[cur.to].size();
			for (int i = 0; i < size; i++) {
				Node next = nodes[cur.to].get(i);
				
				if (distance[next.to] > next.cost + cur.cost) {
					distance[next.to] = next.cost + cur.cost;
					pq.add(new Node(next.to, distance[next.to]));
				}
			}
		}
	}

}