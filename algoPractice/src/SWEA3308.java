import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 최장 증가 부분 수열 (Hard) : O(NlogN)

public class SWEA3308 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());

			int[] nums = new int[N];
			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(stk.nextToken());
			}

			int[] C = new int[N]; // C[i] : i길이를 만족하는 자리에 오는 수의 최소값
			
			int size = 0;
			for (int i = 0; i < N; i++) {
				int pos = Arrays.binarySearch(C, 0, size, nums[i]); // C배열에서 데이터가 들어있는 0인덱스부터 size-1인덱스까지 nums[i] 수를 넣을 수 있는 인덱스 찾기
				
				if (pos >= 0) continue; // 인덱스가 양수라는 건 이미 수가 있다는 의미이므로 패쓰
				
				int insertPos = Math.abs(pos) - 1; // 삽입위치 구하기
				
				C[insertPos] = nums[i]; // 삽입위치에 nums[i] 넣기
				if (size == insertPos) size++; // 마지막에 삽입됐으면 사이즈 늘이기
			}
			
			System.out.println("#" + tc + " " + size);
		}
	}
}