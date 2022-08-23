import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 최소 스패닝 트리 - 크루스칼 알고리즘

public class BOJ1197_kruskal {

	static int V, E;
	static ArrayList<Node> graph;
	static int[] parent;
	static int answer;

	static class Node {
		int from, to, weight;

		public Node(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		V = Integer.parseInt(stk.nextToken());
		E = Integer.parseInt(stk.nextToken());
		graph = new ArrayList<>();

		for (int i = 0; i < E; i++) {
			stk = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(stk.nextToken());
			int to = Integer.parseInt(stk.nextToken());
			int weight = Integer.parseInt(stk.nextToken());

			graph.add(new Node(from, to, weight));
		}

		// 가중치에대해 오름차순 정렬
		graph.sort((o1, o2) -> o1.weight - o2.weight);

		// 1 ~ N 의 수에 대해 부모를 자기 자신으로 세팅
		parent = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			parent[i] = i;
		}

		for (Node node : graph) {
			unionParent(node.from, node.to);
			answer += node.weight;
		}

		System.out.print(answer);
	}

	// 정점 v의 부모 구하기
	private static int getParent(int v) {
		if (parent[v] == v) return v;
		else return parent[v] = getParent(parent[v]);
	}

	// 정점 v1, v2 를 같은 집합으로 합치기
	private static void unionParent(int v1, int v2) {
		int p1 = getParent(v1);
		int p2 = getParent(v2);

		// 둘의 부모가 같지 않으면 합치기
		if (p1 != p2) parent[p2] = p1;
	}
}