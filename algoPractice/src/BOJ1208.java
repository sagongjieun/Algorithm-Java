import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 부분수열의 합 2

public class BOJ1208 {

	static int N, S;
	static int[] nums;
	static ArrayList<Integer> leftList = new ArrayList<>();
	static ArrayList<Integer> rightList = new ArrayList<>();

	static long answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		N = Integer.parseInt(stk.nextToken());
		S = Integer.parseInt(stk.nextToken());

		nums = new int[N];
		stk = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) nums[i] = Integer.parseInt(stk.nextToken());

		// nums를 반으로 나누어 각각의 부분수열의 합을 구함
		subSet(0, N/2, 0, leftList);
		subSet(N/2, N, 0, rightList);

		// 각 리스트의 부분수열의 합들을 오름차순으로 정렬
		Collections.sort(leftList);
		Collections.sort(rightList);

		twoPointer();

		// 구하고자 하는 합이 0이라면 list에 공집합이 포함돼있으므로 정답 -1 
		if (S == 0) answer--;

		System.out.print(answer);
	}

	public static void twoPointer() {
		// 포인터가 될 인덱스 세팅
		int left = 0;
		int right = rightList.size()-1;

		// 포인터 인덱스들이 범위를 벗어나지 않는 동안 반복
		while (left < leftList.size() && right >= 0) {
			// leftList에선 왼쪽부터, rightList에선 오른쪽에서부터 더한 값을 S와 비교하기
			int sum = leftList.get(left) + rightList.get(right);

			// S와 같다면
			if (sum == S) {
				// sum == S가 되는 left, right 값 구하기
				int leftValue = leftList.get(left);
				int rightValue = rightList.get(right);
				long lc = 0, rc = 0;

				// 현재의 left인덱스를 포함하여 또다른 leftValue가 있는지 찾기 (중복값 찾기)
				while (left < leftList.size() && leftList.get(left) == leftValue) {
					left++;
					lc++;
				}

				// 현재의 right인덱스를 포함하여 또다른 rightValue가 있는지 찾기 (중복값 찾기)
				while (right >= 0 && rightList.get(right) == rightValue) {
					right--;
					rc++;
				}

				// lc, rc의 개수만큼 곱하여 answer에 삽입
				// 경우의 수를 구하는 것이기 때문에 곱하기
				answer += lc * rc;
			}
			// S보다 작으면 left 포인터를 1 증가
			else if (sum < S) left++;
			// S보다 크면 right 포인터를 1 감소
			else right--;
		}
	}

	public static void subSet(int start, int end, int sum, ArrayList<Integer> list) {

		// 종료조건
		if (start == end) {
			list.add(sum);
			return;
		}

		subSet(start+1, end, sum, list); // 현재 수를 포함하지않는 경우
		subSet(start+1, end, sum + nums[start], list); // 현재 수를 포함하는 경우
	}
}