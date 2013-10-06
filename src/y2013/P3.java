package y2013;
import java.util.*;
import java.io.*;

class P3 {
    private static Scanner in;
    public static void main (String [] args) throws IOException {
        in = new Scanner (System.in);
        PrintWriter out = new PrintWriter (System.out, true);
        
        while (in.hasNext()) {
            int N = in.nextInt();
            int [] arr = new int [N];
            for (int i = 0; i < N; i++)
                arr[i] = in.nextInt();
            int K = in.nextInt();
            if (K < N) {
                out.printf ("Term %d of the sequence is %d\n", K, arr[K - 1]);
                continue;
            }
            
            int [][] diffTable = new int [N][K + 1];
            for (int i = 0; i < N; i++) {
                diffTable[0][i] = arr[i];
            }
            
            for (int i = 1; i < N; i++) {
                for (int j = 0; j < N - i; j++)
                    diffTable[i][j] = diffTable[i - 1][j + 1] - diffTable [i - 1][j];
            }
            Arrays.fill (diffTable[N - 1], diffTable[N - 1][0]);
            for (int i = N; i < K; i++) {
                for (int j = N - 2; j >= 0; j--) {
                    diffTable[j][i - j] = diffTable[j][i - j - 1] + diffTable[j + 1][i - j - 1];
                }
            }
            
            out.printf ("Term %d of the sequence is %d\n", K, diffTable[0][K - 1]);
        }
        out.close();
        System.exit(0);
    }
}
