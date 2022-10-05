import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 숫자 만들기

public class SWEA4008 {

	static int N;
	static int[] op;
	static int[] nums;
	static int[] comb;

	static int max, min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			op = new int[4]; // + - * / 입력받는 연산자
			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				op[i] = Integer.parseInt(stk.nextToken());
			}

			nums = new int[N]; // 입력받는 숫자
			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(stk.nextToken());
			}

			comb = new int[N-1]; // 조합된 연산자들을 넣을 배열

			// 최대 최소값 초기화
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;

			setOperands(0);

			int answer = max - min;
			sb.append("#" + tc + " " + answer + "\n");
		}

		System.out.print(sb);
	}

	private static void setOperands(int depth) {
		// N-1개의 연산자들을 다 배치하면
		if (depth == N-1) {
			getResultValue(comb);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (op[i] != 0) {
				comb[depth] = i+1; // 연산자 배치, comb의 초기화된 0과 헷갈리지 않기 위해 각 연산 flag에 +1
				op[i]--; // 연산자 사용
				setOperands(depth + 1);
				comb[depth] = 0; // 연산자 배치 취소
				op[i]++;
			}
		}
	}

	private static void getResultValue(int[] comb) {
		int result = 0;

		for (int i = 1; i < N; i++) {
			int num = nums[i];
			int op = comb[i-1];

			if (i == 1) result += nums[i-1];

			switch (op) {
			case 1:
				result += num;
				break;
			case 2:
				result -= num;
				break;
			case 3:
				result *= num;
				break;
			case 4:
				result /= num;
				break;
			}
		}

		max = Math.max(max, result);
		min = Math.min(min, result);
	}
}