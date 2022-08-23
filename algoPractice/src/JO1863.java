import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 종교

public class JO1863 {

	static int n, m;
	static int[] parent;
	static ArrayList<Node> nodes;

	static class Node {
		int from, to;

		public Node(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		parent = new int[n + 1];
		// 각 정점의 부모는 자기자신으로 초기화
		for (int i = 1; i <= n; i++) parent[i] = i;

		nodes = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			stk = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(stk.nextToken());
			int to = Integer.parseInt(stk.nextToken());
			
			nodes.add(new Node(from, to));
		}
		
		for (Node node : nodes) {
			// 두 정점이 같은 부모가 아니라면
			if (!isSameParent(node.from, node.to)) {
				// 두 정점을 같은 집합으로 합치기
				unionParent(node.from, node.to);
			}
		}
		
		int answer = 0;
		// 자기 자신이 부모인 정점의 개수를 count 하여
		// count 개수가 부모들의 개수이므로 그것이 곧 종교의 개수
		for (int i = 1; i <= n; i++) {
			if (parent[i] == i) answer++;
		}
		
		System.out.print(answer);
	}

	private static void unionParent(int from, int to) {
		int fp = getParent(from);
		int tp = getParent(to);
		
		if (fp != tp) parent[tp] = fp;
	}

	private static boolean isSameParent(int from, int to) {
		int fp = getParent(from);
		int tp = getParent(to);
		
		if (fp == tp) return true;
		return false;
	}

	private static int getParent(int v) {
		if (parent[v] == v) return v;
		else return parent[v] = getParent(parent[v]);
	}
}