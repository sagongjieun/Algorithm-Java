import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// N번째 큰 수 - Priority Queue 사용

public class BOJ2075_priorityqueue {
	
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int i=0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				pq.offer(Integer.parseInt(stk.nextToken()));
				
				if (pq.size() > N) pq.poll();
			}
		}
		
		System.out.println(pq.poll());
	}
}