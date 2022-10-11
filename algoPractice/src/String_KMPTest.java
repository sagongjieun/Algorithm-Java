import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// KMP 알고리즘(Knuth–Morris–Pratt Algorithm) 
// O(N+M)

public class String_KMPTest {
	
	// text 에서 pattern이 몇 번 등장하는 지 찾기

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] text = br.readLine().toCharArray();
		char[] pattern = br.readLine().toCharArray();
		
		int tLength = text.length;
		int pLength = pattern.length;
		
		int[] pi = new int[pLength]; // 부분 일치 테이블
		// i : 접미사 포인터
		// j : 접두사 포인터
		for (int i = 1, j = 0; i < pLength; i++) {
			while (j > 0 && pattern[i] != pattern[j]) {
				j = pi[j-1];
			}
			
			if (pattern[i] == pattern[j]) pi[i] = ++j;
			else pi[i] = 0;
		}
		
		int count = 0;
		ArrayList<Integer> list = new ArrayList<>();
		// i : 텍스트 포인터
		// j : 패턴 포인터
		for (int i = 0, j = 0; i < tLength; i++) {
			while (j > 0 && text[i] != pattern[j]) {
				j = pi[j-1];
			}
			
			// 두 글자가 일치하면
			if (text[i] == pattern[j]) {
				// j가 패턴의 마지막 인덱스라면 카운트 증가
				if (j == pLength-1) {
					count++;
					list.add(i-j);
					j = pi[j];
				} else j++;
			}
		}
		
		System.out.println(count);
		if (count > 0) System.out.println(list);
	}
}