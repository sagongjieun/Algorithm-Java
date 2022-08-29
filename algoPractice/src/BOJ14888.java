import java.util.Scanner;

// 연산자 끼워넣기

public class BOJ14888 {
	
	static int N;
	static int[] nums;
	static int[] operands;
	static boolean[] used;
	
	static int maxAnswer = Integer.MIN_VALUE;
	static int minAnswer = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = sc.nextInt();
		}
		
		operands = new int[4];
		for (int i = 0; i < 4; i++) {
			operands[i] = sc.nextInt();
		}
		
		used = new boolean[N-1];
		
		setOperand(nums[0], 1);
		
		System.out.println(maxAnswer);
		System.out.print(minAnswer);
	}

	private static void setOperand(int sum, int index) {
		if (index == N) {
			maxAnswer = Math.max(maxAnswer, sum);
			minAnswer = Math.min(minAnswer, sum);
			
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			// 해당 연산자가 하나라도 남아있으면
			if (operands[i] > 0) {
				operands[i] -= 1; // 연산자 사용
				setOperand(calc(sum, i, nums[index]), index + 1); // 연산 후 재귀호출
				operands[i] += 1; // 연산자 사용 해제
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
			if (num1 < 0 || num2 < 0) {
				num1 = Math.abs(num1);
				num2 = Math.abs(num2);
				int result = num1 / num2;
				return result * -1;
			}
			else return num1 / num2;
		}
		
		return 0;
	}
}