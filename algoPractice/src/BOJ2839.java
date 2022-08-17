import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 설탕 배달

public class BOJ2839 {

	static int N;
	static int answer = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// N이 양수일 동안 반복
		while (N > 0) {
			// N이 5로 나누어떨어지면 N이 0이 될때까지 5로 다 나누어진다는 의미이므로
			// answer에 몫을 더하고 break
			if (N % 5 == 0) {
				answer += N/5;
				break;
			}
			
			// 아니라면 계속해서 -3 진행
			N -= 3;
			answer++;
		}
		
		// 결과 N이 음수이면 5와 3으로 0을 만들 수 없다는 의미이므로 정답 = -1
		if (N < 0) answer = -1;
		System.out.print(answer);
	}
}
