import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 해킹

public class BOJ10282 {
	
	static int n, d, c;
	static ArrayList<Node>[] nodes;
	static int[] dis;
	
	static class Node implements Comparable<Node> {
		int computerNo, time;

		public Node(int computerNo, int time) {
			this.computerNo = computerNo;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			return this.time - o.time;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while (T > 0) {
			stk = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(stk.nextToken()); // 컴퓨터의 개수 (V)
			d = Integer.parseInt(stk.nextToken()); // 의존성 개수 (E)
			c = Integer.parseInt(stk.nextToken()); // 해킹당한 컴퓨터 번호 (S)
			
			nodes = new ArrayList[n + 1];
			for (int i = 1; i <= n; i++) {
				nodes[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < d; i++) {
				stk = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(stk.nextToken());
				int b = Integer.parseInt(stk.nextToken());
				int s = Integer.parseInt(stk.nextToken());
				
				// b -> a 감염
				nodes[b].add(new Node(a, s));
			}
			
			dis = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				dis[i] = Integer.MAX_VALUE;
			}
			
			int totalCount = 0;
			int totalTime = 0;
			
			Dijkstra(c);
			
			for (int i = 1; i <= n; i++) {
				if (dis[i] != Integer.MAX_VALUE) {
					totalCount += 1;
					totalTime = Math.max(totalTime, dis[i]);
				}
			}
			
			sb.append(totalCount + " " + totalTime + "\n");
			
			T--;
		}
		
		System.out.print(sb);
	}

	private static void Dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		dis[start] = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (cur.time > dis[cur.computerNo]) continue;
			
			for (int i = 0; i < nodes[cur.computerNo].size(); i++) {
				Node next = nodes[cur.computerNo].get(i);
				
				if (dis[next.computerNo] > cur.time + next.time) {
					dis[next.computerNo] = cur.time + next.time; // 더 작은값으로 갱신
					pq.add(new Node(next.computerNo, dis[next.computerNo]));
				}
			}
		}
	}
}