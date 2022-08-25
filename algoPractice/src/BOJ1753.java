import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 최단경로

public class BOJ1753 {

	static int V, E, K;
	static ArrayList<Node>[] nodes;
	static int[] distance; // 최단거리를 저장할 배열

	static StringBuilder sb = new StringBuilder();

	static class Node implements Comparable<Node> {
		int v, cost;

		public Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		V = Integer.parseInt(stk.nextToken()); // 정점의 개수
		E = Integer.parseInt(stk.nextToken()); // 간선의 개수
		K = Integer.parseInt(br.readLine()); // 시작 정점

		// 거리배열 무한대로 초기화
		distance = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			distance[i] = Integer.MAX_VALUE;
		}

		// 인접리스트 초기화
		nodes = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			nodes[i] = new ArrayList<>();
		}

		// 방향 간선을 인접리스트에 입력
		for (int i = 0; i < E; i++) {
			stk = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(stk.nextToken());
			int v = Integer.parseInt(stk.nextToken());
			int w = Integer.parseInt(stk.nextToken());

			nodes[u].add(new Node(v, w));
		}

		dijkstra(K);

		// 정답 출력
		for (int i = 1; i <= V; i++) {
			if (distance[i] == Integer.MAX_VALUE) sb.append("INF\n");
			else sb.append(distance[i] + "\n");
		}

		System.out.print(sb);
	}

	private static void dijkstra(int start) {
		// distance 배열 중 최소값을 가지는 노드를 구하기 위해서 우선순위 큐 사용
		PriorityQueue<Node> pq = new PriorityQueue<>();
		distance[start] = 0; // 출발지 비용은 0
		pq.add(new Node(start, 0)); // 출발지 V와 출발지 비용 pq에 넣기
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			// 더 큰 비용으로 도착한 경우는 패스
			if (cur.cost > distance[cur.v]) continue;
			
			// 현재 위치에 연결된 간선 탐색
			int size = nodes[cur.v].size();
			for (int i = 0; i < size; i++) {
				Node next = nodes[cur.v].get(i);
				
				// 현재까지 저장된 next 정점의 최단거리보다
				// cur 에서 next 로 가는 간선의 비용이 더 작다면
				if (distance[next.v] > cur.cost + next.cost) {
					// next 정점의 최단거리 갱신
					distance[next.v] = cur.cost + next.cost;
					pq.add(new Node(next.v, distance[next.v]));
				}
			}
		}
	}
}