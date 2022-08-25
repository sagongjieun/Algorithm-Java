import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 파티

public class BOJ1238 {

	static int N, M, X;
	static int[] distanceGo, distanceBack;
	static ArrayList<Node>[] nodesGo, nodesBack;

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
		X = Integer.parseInt(stk.nextToken()); // 도착지점

		distanceGo = new int[N + 1]; // 각 마을에서 X까지 가는 최단경로를 저장
		distanceBack = new int[N + 1]; // X에서 각 마을까지 가는 최단경로를 저장
		for (int j = 1; j <= N; j++) {
			distanceGo[j] = Integer.MAX_VALUE;
			distanceBack[j] = Integer.MAX_VALUE;
		}

		nodesGo = new ArrayList[N + 1]; // 각 마을에서 X까지 가는 간선을 저장할 공간
		nodesBack = new ArrayList[N + 1]; // X에서 각 마을까지 가는 간선을 저장할 공간
		for (int i = 1; i <= N; i++) {
			nodesGo[i] = new ArrayList<>();
			nodesBack[i] = new ArrayList<>();
		}

		// 각 간선에 대해
		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(stk.nextToken());
			int end = Integer.parseInt(stk.nextToken());
			int time = Integer.parseInt(stk.nextToken());

			nodesBack[start].add(new Node(end, time)); // X에서 각 마을까지 가는 간선을 저장
			nodesGo[end].add(new Node(start, time)); // 각 마을에서 X까지 가는 간선을 저장
		}

		Dijkstra(X, distanceGo, nodesGo); // 각 마을 -> X 의 최단경로 구하기
		Dijkstra(X, distanceBack, nodesBack); // X -> 각 마을의 최단경로 구하기

		int answer = 0;
		// 각 마을에 대해 X까지 가는 최단경로와 돌아오는 최단경로를 합하여
		// answer 의 값과 비교하며 최소값 갱신
		for (int i = 1; i <= N; i++) {
			answer = Math.max(answer, distanceGo[i] + distanceBack[i]);
		}

		System.out.print(answer);
	}

	private static void Dijkstra(int start, int[] distance, ArrayList<Node>[] nodes) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0)); // 시작점과 시간을 pq에 넣기
		distance[start] = 0; // 시작점의 시간은 0

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			// 현재까지의 최단시간보다 더 높은 시간으로 도착하면 넘어가기
			if (cur.time > distance[cur.v]) continue;

			int size = nodes[cur.v].size();
			// 현재 정점에 연결된 간선 탐색
			for (int i = 0; i < size; i++) {
				Node next = nodes[cur.v].get(i);

				// 현재까지 저장된 next 정점의 최단시간보다
				// cur 에서 next 로 가는 시간의 비용이 더 작다면
				if (distance[next.v] > next.time + cur.time) {
					// next 정점의 최단시간 갱신
					distance[next.v] = next.time + cur.time;
					pq.add(new Node(next.v, distance[next.v]));
				}
			}
		}
	}
}