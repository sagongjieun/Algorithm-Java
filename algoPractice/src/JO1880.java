import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 암호풀기

public class JO1880 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		char[] alp = br.readLine().toCharArray();
		char[] input = br.readLine().toCharArray();
		
		// 암호화된 문장의 각 문자에 대해
		for (int i = 0; i < input.length; i++) {
			
			// 공백이면 공백 그대로 출력
			if (input[i] == ' ') sb.append(input[i]);
			// 대문자라면 (소문자 a = 97)
			else if (input[i] < 97) {
				// 해당 문자를 소문자로 바꿔서
				// 복호화 키의 index로 해당 암호를 찾고 다시 대문자로 바꿔서 출력
				input[i] += 32;
				sb.append((char) (alp[input[i] - 97] - 32));
			}
			// 소문자라면 바로 복호화 키의 index로 해당 암호를 찾고 바로 출력
			else sb.append(alp[input[i] - 97]);
		}
		
		System.out.print(sb);
	}

}