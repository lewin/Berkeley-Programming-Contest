package y2013;
import java.util.*;
import java.io.*;

class P4 {
    private static Scanner in;
    public static void main (String [] args) throws IOException {
        in = new Scanner (System.in);
        PrintWriter out = new PrintWriter (System.out, true);
        
        while (in.hasNext()) {
            int N = in.nextInt(), M = in.nextInt();
            int tN = N;
            while (jwq(tN) >= jwq(M)) {
                int s = jwq (tN) - jwq (M);
                tN ^= M << s;
            }
            out.printf ("As polynomials, %d mod %d = %d\n", N, M, tN);
        }
        out.close();
        System.exit(0);
    }
    
    private static int jwq(int v) {
        int c = 0;
        while (v > 0) {
            v >>= 1;
            c++;
        }
        return c;
    }
}
