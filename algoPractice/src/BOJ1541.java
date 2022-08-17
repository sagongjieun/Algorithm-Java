import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 잃어버린 괄호

public class BOJ1541 {
	
	static int answer = 0;
	static ArrayList<Integer> sub = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// "-" 를 기준으로 나눠서 입력받기
		StringTokenizer stk = new StringTokenizer(br.readLine(), "-");
		
		ArrayList<String> operands = new ArrayList<>();
		// "-"를 기준으로 나뉜 식들을 배열에 나눠 담기
		while (stk.hasMoreTokens()) {
			operands.add(stk.nextToken());
		}
		
		for (int i=0; i<operands.size(); i++) {
			int sum = 0;
			String op = operands.get(i);
			// 식에 "+" 연산자가 있다면 "+"를 기준으로 한번 더 나누기
			stk = new StringTokenizer(op, "+");
			
			// 식에서 숫자들만 뽑아서 nums 배열에 삽입
			ArrayList<Integer> nums = new ArrayList<>();
			while (stk.hasMoreTokens()) {
				nums.add(Integer.parseInt(stk.nextToken()));
			}
			
			// 숫자들을 모두 더하기
			for (int j=0; j<nums.size(); j++) {
				sum += nums.get(j);
			}
			
			// 모든 식들에서 "+" 연산만 수행한 숫자들 삽입
			sub.add(sum);
		}
		
		// sub의 모든 값들을 순차적으로 빼주면 정답
		answer = sub.get(0);
		for (int i=1; i<sub.size(); i++) {
			answer = answer - sub.get(i);
		}
		
		System.out.print(answer);
	}
}
