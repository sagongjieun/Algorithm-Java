import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1244 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int switchCount = Integer.parseInt(br.readLine());
		int[] switchStatus = new int[switchCount];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<switchCount; i++) {
			switchStatus[i] = Integer.parseInt(st.nextToken());
		}
		int studentCount = Integer.parseInt(br.readLine());
		
		for (int i=0; i<studentCount; i++) {
			st = new StringTokenizer(br.readLine());

			int gender = Integer.parseInt(st.nextToken());
			int targetSwitch = Integer.parseInt(st.nextToken());

			// 남학생
			if (gender == 1) {
				for (int j=0; j<switchCount; j++) {
					if ((j+1) % targetSwitch == 0) {
						switchStatus[j] = switchStatus[j] == 0 ? 1 : 0;
					}
				}
			}
			// 여학생
			else {
				switchStatus[targetSwitch - 1] =
						switchStatus[targetSwitch - 1] == 0 ? 1 : 0;
				int left = targetSwitch - 2;
				int right = targetSwitch;
				
				while (left >= 0 && right < switchCount) {
					if (switchStatus[left] == switchStatus[right]) {
						switchStatus[left] = switchStatus[left] == 0 ? 1 : 0;
						switchStatus[right] = switchStatus[right] == 0 ? 1 : 0;
					} else break;
					
					left--;
					right++;
				}
			}
		}
		
		for (int i=0; i<switchCount; i++) {
			System.out.print(switchStatus[i] + " ");
			if ((i+1) % 20 == 0) System.out.println();
		}

	}

}
