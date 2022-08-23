import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 최소 스패닝 트리 - 프림 알고리즘

public class BOJ1197_prim {

	static int V, E;
	static boolean[] visited;
	static int answer = 0;
	static ArrayList<Node>[] nodes;
	static PriorityQueue<Node> pq = new PriorityQueue<>();

	static class Node implements Comparable<Node> {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		// 가중치를 오름차순 정렬하여 더 낮은 가중치의 정점부터 들르게 하기
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		V = Integer.parseInt(stk.nextToken());
		E = Integer.parseInt(stk.nextToken());

		visited = new boolean[V + 1];

		nodes = new ArrayList[V + 1];
		for (int i = 0; i <= V; i++) {
			nodes[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			stk = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(stk.nextToken());
			int to = Integer.parseInt(stk.nextToken());
			int weight = Integer.parseInt(stk.nextToken());

			nodes[from].add(new Node(to, weight));
			nodes[to].add(new Node(from, weight));
		}

		Prim(1);

		System.out.print(answer);
	}

	private static void Prim(int start) {
		int count = 0;
		pq.add(new Node(start, 0));
		
		while (!pq.isEmpty()) {
			// 모든 정점을 다 봤으면 멈추기
			if (count == V) break;

			Node cur = pq.poll();

			// 해당 정점을 들른 적 있으면 넘어가기
			if (visited[cur.v]) continue;

			visited[cur.v] = true; // 방문체크

			answer += cur.w; // 현재의 가중치 더하기

			// 현재 정점과 연결된 정점들 중 방문하지 않은 정점은 pq에 넣기
			for (Node next : nodes[cur.v]) {
				if (!visited[next.v]) pq.add(next);
			}

			count++;
		}
	}
}