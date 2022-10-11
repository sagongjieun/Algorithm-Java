import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 회전 초밥

public class BOJ15961 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(stk.nextToken()); // 접시의 수
		int d = Integer.parseInt(stk.nextToken()); // 초밥의 가짓수
		int k = Integer.parseInt(stk.nextToken()); // 연속해서 먹는 접시의 수
		int c = Integer.parseInt(stk.nextToken()); // 쿠폰 번호
		
		int[] plates = new int[N+k-1];
		// 입력받기
		for (int i = 0; i < N; i++) {
			plates[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 0; i < k-1; i++) {
			plates[N++] = plates[i];
		}
		
		int[] sushi = new int[d+1];
		int type = 1; // 쿠폰 적용
		sushi[c]++;
		
		// 맨 처음 k개 스시의 종류 세기
		for (int i = 0; i < k; i++) {
			if (sushi[plates[i]] == 0) type++; // 해당 스시를 센 적이 없으면 새로나온 종류
			sushi[plates[i]]++; // 해당 스시 개수 +1
		}
		
		int deleted = 0;
		int added = k;
		int count = type;
		
		for (int i = added; i < plates.length; i++) {
			sushi[plates[deleted]]--; // 슬라이딩으로 삭제된 스시의 개수 -1
			if (sushi[plates[deleted]] == 0) count--; // 삭제된 스시의 개수가 0이라면 하나의 종류가 없어졌다는 의미이므로 종류 -1
			
			if (sushi[plates[i]] == 0) count++; // 추가된 스시의 개수가 0이라면 이 종류가 없었다는 의미이므로 종류 +1
			sushi[plates[i]]++; // 슬라이딩으로 추가된 스시의 개수 +1
			
			type = Math.max(type, count); // 더 큰 종류의 개수로 갱신
			deleted++; // 슬라이딩
		}
		
		System.out.print(type);
	}
}