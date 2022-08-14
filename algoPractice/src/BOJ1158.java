import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1158 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		Queue<Integer> josephus = new LinkedList<>();
		
		// 1~N 까지의 수를 Queue에 삽입
		for (int i=1; i<=N; i++) {
			josephus.offer(i);
		}

		StringBuilder sb = new StringBuilder();
		
		sb.append("<");
		// Queue에 마지막 요소 하나가 남을 때까지 반복
		while (josephus.size() != 1) {
			// 현재의 0번째 인덱스부터 M-1번째 인덱스까지 dequeue해서 enqueue 시키기
			for (int i=0; i<M-1; i++) {
				josephus.offer(josephus.poll());
			}
			// Queue의 첫번째 요소가 출력할 요소에 해당됨
			sb.append(josephus.poll() + ", ");
		}
		sb.append(josephus.poll() + ">");
		
		System.out.println(sb);
	}
}
