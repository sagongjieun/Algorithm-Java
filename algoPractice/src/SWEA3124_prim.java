import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 최소 스패닝 트리 - 프림

public class SWEA3124_prim {

	static int V, E;
	static boolean[] visited;
	static ArrayList<Node>[] nodes;
	static PriorityQueue<Node> pq = new PriorityQueue<>();

	static long answer;

	static class Node implements Comparable<Node> {
		int v, weight;

		public Node(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			stk = new StringTokenizer(br.readLine());
			V = Integer.parseInt(stk.nextToken()); // 정점 개수
			E = Integer.parseInt(stk.nextToken()); // 간선 개수

			visited = new boolean[V + 1];

			// 인접리스트 생성
			nodes = new ArrayList[V + 1];
			for (int i = 1; i <= V; i++) {
				nodes[i] = new ArrayList<>();
			}

			for (int i = 0; i < E; i++) {
				stk = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(stk.nextToken());
				int to = Integer.parseInt(stk.nextToken());
				int weight = Integer.parseInt(stk.nextToken());

				// 인접리스트 초기화
				nodes[from].add(new Node(to, weight));
				nodes[to].add(new Node(from, weight));
			}

			answer = 0;
			Prim(1);

			System.out.println("#" + tc + " " + answer);
		}
	}

	private static void Prim(int start) {
		int count = 0;
		pq.add(new Node(start, 0)); // pq에 시작 정점과 그 가중치 0을 넣기

		while (!pq.isEmpty()) {
			// 종료 조건 = 모든 정점을 다 봤으면 멈추기
			if (count == V) break;

			Node cur = pq.poll();

			// 현재 정점을 들른 적 없으면
			if (!visited[cur.v]) {
				visited[cur.v] = true; // 방문 표시
				answer += cur.weight;

				for (int i = 0; i < nodes[cur.v].size(); i++) {
					Node next = nodes[cur.v].get(i);

					if (!visited[next.v]) pq.add(next);
				}

				count++;
			}
		}
	}
}