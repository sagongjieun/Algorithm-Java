import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 최소 스패닝 트리

public class BOJ1197 {
	
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
		
		graph.sort((o1, o2) -> o1.weight - o2.weight);
		
		parent = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			parent[i] = i;
		}
		
		for (Node node : graph) {
			if (!compareParent(node.from, node.to)) {
				unionParent(node.from, node.to);
				answer += node.weight;
			}
		}
		
		System.out.print(answer);
	}
	
	private static int getParent(int v) {
		if (parent[v] == v) return v;
		else return parent[v] = getParent(parent[v]);
	}

	private static boolean compareParent(int v1, int v2) {
		int parentOfV1 = getParent(v1);
		int parentOfV2 = getParent(v2);
		
		if (parentOfV1 == parentOfV2) return true;
		else return false;
	}

	private static void unionParent(int v1, int v2) {
		int parentOfV1 = getParent(v1);
		int parentOfV2 = getParent(v2);
		
		if (parentOfV1 != parentOfV2) parent[parentOfV2] = parentOfV1;
	}

}