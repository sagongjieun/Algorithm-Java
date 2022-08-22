import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 암호 만들기

public class BOJ1759 {
	
	static int L, C;
	static char[] cArr, result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		
		cArr = new char[C];
		result = new char[L];
		stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			cArr[i] = stk.nextToken().charAt(0);
		}
		
		Arrays.sort(cArr); // 오름차순 정렬
		
		comb(0, 0); // 조합
	}

	public static void comb(int depth, int index) {
		// 재귀 종료조건
		if (depth == L) {
			// 조건에 부합하면 출력
			if (isAvailable(result)) System.out.println(result);
			return;
		}
		
		for (int i = index; i < C; i++) {
			result[depth] = cArr[i];
			comb(depth + 1, i + 1);
		}
	}

	// 모음 자음 체크 함수
	private static boolean isAvailable(char[] result) {
		int vowel = 0, consonant = 0; // 모음 자음
		
		// 결과의 각 문자에 대해 모음 자음 개수 세기
		for (char c : result) {
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') vowel++;
			else consonant++;
		}
		
		// 조건에 맞다면 true
		if (vowel >= 1 && consonant >= 2) return true;
		
		return false;
	}

}