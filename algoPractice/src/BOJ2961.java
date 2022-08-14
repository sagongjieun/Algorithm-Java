import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2961 {
	
	static int N;
	static int[] sour, bitter;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		N = Integer.parseInt(br.readLine());
		sour = new int[N];
		bitter = new int[N];
		
		// 신맛은 신맛 배열에, 쓴맛은 쓴맛 배열에 넣기
		for (int i=0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			sour[i] = Integer.parseInt(stk.nextToken());
			bitter[i] = Integer.parseInt(stk.nextToken());
		}

		recur(1, 0, 0, 0);
		
		// 정답 출력
		System.out.print(answer);
	}

	// mul : 누적된 신 맛 값 (곱하기라서 초기값은 1)
	// sum : 누적된 쓴 맛 값
	// materialCount : 재료로 사용한 개수
	public static void recur(int mul, int sum, int index, int materialCount) {
		// 모든 재료 탐색이 끝나면
		if (index == N) {
			// 하나의 재료라도 들어있다면 현재 answer와 누적된 신맛 쓴맛의 차이 중 작은값으로 갱신
			if (materialCount != 0) answer = Math.min(answer,  Math.abs(mul - sum));
			return;
		}
		
		recur(mul, sum, index+1, materialCount); // index번째의 재료를 넣지 않으면
		recur(mul * sour[index], sum + bitter[index], index+1, materialCount+1); // 신맛은 곱하기, 쓴맛은 더하기
	}
}