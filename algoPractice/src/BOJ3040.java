import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3040 {
	
	static int[] dwarf = new int[9]; // 난쟁이
	static int[] temp = new int[2];
	static int sum = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<9; i++) {
			int num = Integer.parseInt(br.readLine());
			sum += num; // 난쟁이 모자 수들의 합 구하기
			dwarf[i] = num;
		}
		
		// 완전탐색으로 난쟁이 모자 수들을 두개씩 합하여
		// sum - 100 의 값이 되는 두 수 찾기
		for (int i=0; i<9; i++) {
			int target = dwarf[i]; // 기준이 되는 난쟁이 모자 수
			
			for (int j=i+1; j<9; j++) {
				if (target + dwarf[j] == sum - 100) {
					temp[0] = dwarf[i];
					temp[1] = dwarf[j];
				}
			}
		}
		
		// 일곱 난쟁이면 출력
		for (int i=0; i<9; i++) {
			if (dwarf[i] == temp[0] || dwarf[i] == temp[1]) continue;
			else sb.append(dwarf[i] + "\n");
		}
		
		System.out.print(sb);
	}
}