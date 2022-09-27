import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 소문난 칠공주 - 다시 풀기

public class BOJ1941_2 {
	
	static char[][] map = new char[5][5];
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 5; i++) {
			String s = br.readLine();
			for (int j = 0; j < 5; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		getPrincess(0, 0, 0, 0);
		
		System.out.print(answer);
	}

	/**
	 * 
	 * @param y : 좌표
	 * @param x : 좌표
	 * @param lee : 이다솜파 학생 수
	 * @param im : 임도연파 학생 수
	 */
	private static void getPrincess(int y, int x, int lee, int im) {
		
		if (lee + im == 7) {
			System.out.println(lee + " " + im);
			if (lee >= 4) {
				System.out.println("여기");
				answer++;
			}
			return;
		}
		
		if (map[y][x] == 'S') {
			if (isInRange(y-1, x)) getPrincess(y-1, x, lee+1, im);
			if (isInRange(y, x+1)) getPrincess(y, x+1, lee+1, im);
			if (isInRange(y+1, x)) getPrincess(y+1, x, lee+1, im);
			if (isInRange(y, x-1)) getPrincess(y, x-1, lee+1, im);
		}
		
		if (map[y][x] == 'Y') {
			if (isInRange(y-1, x)) getPrincess(y-1, x, lee, im+1);
			if (isInRange(y, x+1)) getPrincess(y, x+1, lee, im+1);
			if (isInRange(y+1, x)) getPrincess(y+1, x, lee, im+1);
			if (isInRange(y, x-1)) getPrincess(y, x-1, lee, im+1);
		}
	}

	private static boolean isInRange(int y, int x) {
		if (y >= 0 && x >= 0 && y < 5 && x < 5) return true;
		return false;
	}

}