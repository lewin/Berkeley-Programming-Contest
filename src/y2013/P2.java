package y2013;
import java.util.*;
import java.io.*;

class P2 {
    private static Scanner in;
    private static int N;
    
    private static int [] dx = new int [] {-1, -1, 0, 1, 1, 1, 0, -1},
                          dy = new int [] {0, 1, 1, 1, 0, -1, -1, -1};
    private static int [] exp;
    private static int [] res;
    private static boolean [] vis;
    public static void main (String [] args) throws IOException {
        in = new Scanner (System.in);
        PrintWriter out = new PrintWriter (System.out, true);
        
        int test = 1;
        while (true) {
            N = in.nextInt();
            if (N == 0) break;
            eadj = new int [N * N * 5];
            eprev = new int [N * N * 5];
            elast = new int [N * N];
            Arrays.fill (elast, -1);
            eidx = 0;
            
            exp = new int [N * N + 1];
            Arrays.fill (exp, -1);
            
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++) {
                    int a = in.nextInt(), b = in.nextInt();
                    if (a != 0)
                        exp[a] = toIdx (i, j);
                    if (b == -1) continue;
                    int ni = i + dx [b], nj = j + dy [b];
                    while (ni >= 0 && ni < N && nj >= 0 && nj < N) {
                        addEdge (toIdx (i, j), toIdx (ni, nj));
                        ni += dx [b]; nj += dy [b];
                    }
                }
            exp[1] = 0;
            exp[N * N] = N * N - 1;
            res = new int [N * N + 1];
            vis = new boolean [N * N];
            Arrays.fill (res, -1);
            dfs (1, 0);
            
            int [] rev = new int [N * N];
            for (int i = 1; i <= N * N; i++)
                rev[res[i]] = i;
            
            out.printf ("Set %d:\n", test++);
            for (int i = 0; i < N; i++) {
                out.printf ("   %s", format ("" + rev[toIdx(i, 0)]));
                for (int j = 1; j < N; j++) {
                    out.printf (" %s", format ("" + rev[toIdx(i, j)]));
                }
                out.println();
            }
            out.println();
        }
        out.close();
        System.exit(0);
    }
    
    private static String format (String s) {
        if (s.length() == 2) return s;
        return " " + s;
    }
    
    private static boolean dfs (int i, int cp) {
        if (exp[i] != -1 && exp[i] != cp) return false;
        if (vis[cp]) return false;
        if (i == N * N) {
            res[i] = cp;
            return true;
        }
        vis[cp] = true;
        res[i] = cp;
        for (int e = elast [cp]; e != -1; e = eprev [e]) {
            if (dfs(i + 1, eadj[e]))
                return true;
        }
        res[i] = -1;
        vis[cp] = false;
        return false;
    }
    
    private static int [] eadj, eprev, elast;
    private static int eidx;
    
    private static void addEdge (int a, int b) {
        eadj [eidx] = b; eprev [eidx] = elast [a]; elast [a] = eidx++;
    }
    
    private static int toIdx (int i, int j) {
        return i * N + j;
    }
}
