import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 거스름돈

public class BOJ5585 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int rest = 1000 - Integer.parseInt(br.readLine());
		int[] type = {500, 100, 50, 10, 5, 1};
		int answer = 0;
		
		for (int i=0; i<type.length; i++) {
			answer += rest / type[i];
			rest = rest % type[i];
		}
		
		System.out.print(answer);
	}
}