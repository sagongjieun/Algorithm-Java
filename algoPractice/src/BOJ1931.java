import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

// 회의실 배정

public class BOJ1931 {

	static int answer = 1;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;

		ArrayList<int[]> meetings = new ArrayList<>();

		N = Integer.parseInt(br.readLine());
		for (int i=0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(stk.nextToken());
			int end = Integer.parseInt(stk.nextToken());
			meetings.add(new int[] {start, end});
		}

		// 회의 끝나는 시간으로 오름차순 정렬
		Collections.sort(meetings, new endComparator());
		
		// 이전 회의의 종료시간 초기화 (제일 첫 회의부터 시작)
		int endTime = meetings.get(0)[1];
		// 제일 첫 회의 제외이므로 i=1부터 시작
		for (int i=1; i<N; i++) {
			// 각 회의에 대해 시작시간이 이전 회의의 종료시간보다 같거나 크면
			if (meetings.get(i)[0] >= endTime) {
				// 이 회의는 가능한 것이므로 이 회의의 종료시간으로 갱신
				endTime = meetings.get(i)[1];
				// 회의 수 +1
				answer++;
			}
			else if (meetings.get(i)[0] < endTime) continue;
		}
		
		System.out.print(answer);
	}
}

class endComparator implements Comparator<int[]> {
	@Override
	public int compare(int[] o1, int[] o2) {
		// 끝나는 시간이 같다면 시작시간으로 오름차순
		if (o1[1] == o2[1]) return o1[0] - o2[0];
		return o1[1] - o2[1];
	}
}