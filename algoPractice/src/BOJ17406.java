import java.util.Scanner;

public class BOJ17406 {
    
	static int n, m;
    static int[][] arrA, opernads;
    
    static boolean[] visited;
    static int[] result;
    static int answer = Integer.MAX_VALUE;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt(); // 배열의 row
        m = sc.nextInt(); // 배열의 col
        int k = sc.nextInt(); // 회전 연산의 개수
        
        // 원본 A배열에 대한 정보
        arrA = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                arrA[i][j] = sc.nextInt();
            }
        }
        
        // 회전 연산의 정보
        opernads = new int[k][3];
        for(int i = 0; i < k; i++) {
            opernads[i][0] = sc.nextInt(); // r
            opernads[i][1] = sc.nextInt(); // c
            opernads[i][2] = sc.nextInt(); // s
        }
        
        visited = new boolean[k];
        result = new int[k];
        
        perm(0, k);
        
        System.out.print(answer); // 정답 출력
    }    
    
    // 조합
    public static void perm(int depth, int k) {
        if(depth == k) {
            int[][] copyArr = new int[n][m];
            
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                	copyArr[i][j] = arrA[i][j];
                }
            }
            
            getCoords(copyArr);
            return;
        }
        
        for(int i = 0; i < k; i++) {
            if(!visited[i]) {
                visited[i] = true;
                result[depth] = i;
                
                perm(depth + 1, k);
                
                visited[i] = false;
            }
        }
    }
    
    // 회전 연산의 정보에 담긴 좌표를 얻어 회전 함수 호출
    public static void getCoords(int[][] copy) {
        for(int i = 0; i < result.length; i++) {
            int lx = opernads[result[i]][0] - opernads[result[i]][2] - 1;
            int ly = opernads[result[i]][1] - opernads[result[i]][2] - 1;
            int rx = opernads[result[i]][0] + opernads[result[i]][2] - 1;
            int ry = opernads[result[i]][1] + opernads[result[i]][2] - 1;
            
            rotate(lx, ly, rx, ry, copy);
        }
        
        getMinValue(copy);
    }
    
    // 회전할 배열의 행 별로 합을 구해 최소값을 answer에 저장
    public static void getMinValue(int[][] copy) {
        for(int i = 0; i < copy.length; i++) {
            int sum = 0;
            for(int j = 0; j < copy[i].length; j++) {
                sum += copy[i][j];
            }
            answer = Math.min(answer, sum);
        }
    }
    
    // 회전 함수
    public static void rotate(int lx, int ly, int rx, int ry, int[][] copy) {
        if(lx == rx && ly == ry) return;
        
        // 좌표값들을 temp에 저장
        int[] temp = new int[3];
        temp[0] = copy[lx][ry];
        temp[1] = copy[rx][ry];
        temp[2] = copy[rx][ly];
        
        // 우
        for(int i = ry; i > ly; i--) {
            copy[lx][i] = copy[lx][i - 1];
        }
        // 하
        for(int i = rx; i > lx; i--) {
            if(i == lx + 1) copy[i][ry] = temp[0];
            else copy[i][ry] = copy[i - 1][ry];
        }
        // 좌
        for(int i = ly; i < ry; i++) {
            if(i == ry - 1) copy[rx][i] = temp[1];
            else copy[rx][i] = copy[rx][i + 1];
        }
        // 상
        for(int i = lx; i < rx; i++) {
            if(i == rx - 1) copy[i][ly] = temp[2];
            else copy[i][ly] = copy[i + 1][ly];
        }
        
        rotate(lx + 1, ly + 1, rx - 1, ry - 1, copy);
    }
}
