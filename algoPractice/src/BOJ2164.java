import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2164 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Queue<Integer> queue = new LinkedList<>();
		
		for (int i=1; i<=N; i++) queue.offer(i); // queue에 1 ~ N번 삽입
		
		// queue에 원소 1개가 남을때까지 반복
		while (queue.size() > 1) {
			queue.poll(); // 첫번째 원소 제거
			queue.offer(queue.poll()); // 첫번째 원소를 하나 더 제거하고 그 원소를 삽입
		}
		
		System.out.println(queue.peek());
	}
}