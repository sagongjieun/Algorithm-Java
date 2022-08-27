import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 괄호 추가하기

public class BOJ16637 {
	
	static int N;
	static ArrayList<Integer> nums = new ArrayList<>();
	static ArrayList<Character> operands = new ArrayList<>();

	static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		String s = br.readLine();
		for (int i = 0; i < N; i++) {
			char c = s.charAt(i);
			
			if (c == '+' || c == '-' || c == '*') operands.add(c);
			else nums.add(Character.getNumericValue(c));
		}
		
		DFS(0, nums.get(0));
		
		System.out.print(answer);
	}

	private static void DFS(int index, int sum) {
		// 모든 연산을 다 끝내면 정답의 최대값 갱신하고 백트래킹
		if (index >= operands.size()) {
			answer = Math.max(answer, sum);
			return;
		}
		
		// 괄호 없이 연산
		int noBracketResult = calc(sum, operands.get(index), nums.get(index+1));
		DFS(index + 1, noBracketResult);
		
		// 백트래킹하며 백트래킹하는 index번째 뒤의 수 두개를 괄호 묶어 연산
		if (index + 1 < operands.size()) {
			int bracketResult = calc(nums.get(index+1), operands.get(index+1), nums.get(index+2));
			int attachResult = calc(sum, operands.get(index), bracketResult);
			DFS(index + 2, attachResult);
		}
	}
	
	private static int calc(int num1, char op, int num2) {
		switch (op) {
		case '+':
			return num1 + num2;
		case '-':
			return num1 - num2;
		case '*':
			return num1 * num2;
		}
		return -1;
	}
}