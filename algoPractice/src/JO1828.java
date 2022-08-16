import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

// 냉장고

public class JO1828 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = null;
		
		int N = Integer.parseInt(br.readLine());
		int answer = N; // 냉장고 개수 초기화
		ArrayList<int[]> temps = new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			stk = new StringTokenizer(br.readLine());
			int low = Integer.parseInt(stk.nextToken());
			int high = Integer.parseInt(stk.nextToken());
			
			temps.add(new int[] {low,  high});
		}
		
		// 각 화학물질의 최고온도에 대해 오름차순으로 정렬
		Collections.sort(temps, new highComparator());
		
		// 이전 화학물질의 최고온도를 초기화
		int prevHigh = temps.get(0)[1];
		// 각 화학물질들에 대해
		for (int i=1; i<N; i++) {
			// 이전 최고온도가 현재 최저온도보다 높으면 같은 냉장고 사용 불가
			if (prevHigh >= temps.get(i)[0]) answer--;
			// 아니라면 이전 최고온도를 현재 최고온도로 갱신
			else prevHigh = temps.get(i)[1];
		}
		
		System.out.print(answer);
	}
}

class highComparator implements Comparator<int[]> {

	@Override
	public int compare(int[] o1, int[] o2) {
		// 두 화학물질의 최고온도가 같으면 최저온도에 대해 오름차순 정렬
		if (o1[1] - o2[1] == 0) return o1[0] - o2[0];
		// 최고온도에 대해 오름차순 정렬
		return o1[1] - o2[1];
	}
	
}