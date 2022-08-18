import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 가운데를 말해요

public class BOJ1655 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2) -> o2 - o1); // 내림차순
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o1 - o2); // 오름차순
		
		// 입력받는 수들에 대해 바로바로 결과 출력
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			// minHeap, maxHeap 사이즈가 같으면 minHeap에 우선적으로 삽입
			if (minHeap.size() == maxHeap.size()) minHeap.offer(num);
			else maxHeap.offer(num);
			
			// 두 힙 모두에 값이 하나라도 있으면 체크
			if (!minHeap.isEmpty() && !maxHeap.isEmpty()) {
				// 두 힙의 head 값을 비교해서 minHeap의 값이 더 크면 head를 swap
				// (minHeap의 head에서 최소값을 출력할 것이기 때문에)
				if (minHeap.peek() > maxHeap.peek()) {
					int temp = minHeap.poll();
					minHeap.offer(maxHeap.poll());
					maxHeap.offer(temp);
				}
			}
			
			sb.append(minHeap.peek() + "\n");
		}
		
		System.out.print(sb);
	}
}