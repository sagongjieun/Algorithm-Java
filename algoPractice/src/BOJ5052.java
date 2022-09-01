import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 전화번호 목록

public class BOJ5052 {
	
	static int N;
	static String[] numList;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			
			// 입력받는 번호를 문자열로 저장
			numList = new String[N];
			for (int i = 0; i < N; i++) {
				numList[i] = br.readLine();
			}
			
			Arrays.sort(numList); // 오름차순 정렬
			
			if (isConsistent(N, numList)) System.out.println("YES");
			else System.out.println("NO");
		}
	}

	private static boolean isConsistent(int N, String[] list) {
		// 목록2의 앞시작이 목록1과 동일하면 겹치므로 false return
		for (int i = 0; i < N - 1; i++) {
			if (list[i + 1].startsWith(list[i])) {
				return false;
			}
		}
		return true;
	}
}