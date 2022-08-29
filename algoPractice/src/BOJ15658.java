import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 연산자 끼워넣기 (2)

public class BOJ15658 {
	
	static int N;
	static int[] nums, operands;
	static int maxAnswer = Integer.MIN_VALUE;
	static int minAnswer = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		N = Integer.parseInt(br.readLine());
		
		nums = new int[N];
		stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(stk.nextToken());
		}
		
		operands = new int[4];
		stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operands[i] = Integer.parseInt(stk.nextToken());
		}
		
		setOperand(nums[0], 1);
		
		System.out.println(maxAnswer);
		System.out.print(minAnswer);
	}

	private static void setOperand(int sum, int index) {
		// 모든 연산을 다 했으면 정답 갱신하고 백트래킹
		if (index == N) {
			maxAnswer = Math.max(maxAnswer, sum);
			minAnswer = Math.min(minAnswer, sum);
			
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if (operands[i] > 0) {
				operands[i] -= 1;
				setOperand(calc(sum, i, nums[index]), index + 1);
				operands[i] += 1;
			}
		}
	}

	private static int calc(int num1, int op, int num2) {
		switch(op) {
		case 0:
			return num1 + num2;
		case 1:
			return num1 - num2;
		case 2:
			return num1 * num2;
		case 3:
			return num1 / num2;
		}
		return 0;
	}

}