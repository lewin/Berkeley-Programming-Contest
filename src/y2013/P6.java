package y2013;
import java.util.*;
import java.io.*;

class P6 {
    private static Scanner in;
    public static void main (String [] args) throws IOException {
        in = new Scanner (System.in);
        PrintWriter out = new PrintWriter (System.out, true);
        
        int MN = 1000;
        eadj = new int [2 * MN * MN];
        eprev = new int [2 * MN * MN];
        elast = new int [MN];
        while (in.hasNext()) {
            int N = in.nextInt();
            Arrays.fill (elast, -1);
            eidx = 0;
            idx = 0;
            fm = new HashMap <String, Integer> ();
            bm = new HashMap <Integer, String> ();
            
            String goal = in.next();
            getIdx(goal);
            mk = new boolean [MN];
            while (true) {
                String s1 = in.next(), s2 = in.next();
                if (s1.equals ("*") && s2.equals ("*")) break;
                int a = getIdx (s1), b = getIdx (s2);
                addEdge (a, b);
                if (b == 0 || a == 0) {
                    mk[a] = true;
                    mk[b] = true;
                }
            }
            
            Pair [] p = new Pair [idx];
            for (int i = 0; i < idx; i++) {
                if (!mk [i]) {
                    int count = 0;
                    for (int e = elast[i]; e != -1; e = eprev [e]) {
                        if (mk[eadj[e]])
                            count++;
                    }
                    p[i] = new Pair (bm.get(i), count);
                } else {
                    p[i] = new Pair ("", 0);
                }
            }
            Arrays.sort (p);
            
            boolean first = true;
            String [] arr = new String [N];
            for (int i = 0; i < N; i++)
                arr[i] = p[i].s;
            Arrays.sort (arr);
            for (int i = 0; i < N; i++) {
                if (!first) {
                    out.print (" ");
                }
                out.print (arr[i]);
                first = false;
            }
            out.println();
        }
        out.close();
        System.exit(0);
    }
    
    static class Pair implements Comparable <Pair> {
        public String s;
        public int count;
        
        public Pair (String s, int count) {
            this.count = count;
            this.s = s;
        }
        
        public int compareTo (Pair other) {
            return count == other.count ? s.compareTo (other.s) : other.count - count;
        }
    }
    
    private static boolean [] mk;
    
    private static int [] eadj, elast, eprev;
    private static int eidx;
    
    private static void addEdge (int a, int b) {
        eadj [eidx] = b; eprev [eidx] = elast [a]; elast [a] = eidx++;
        eadj [eidx] = a; eprev [eidx] = elast [b]; elast [b] = eidx++;
    }
    
    private static HashMap<String, Integer> fm;
    private static HashMap<Integer, String> bm;
    private static int idx;
    
    private static int getIdx(String s) {
        if (!fm.containsKey(s)) {
            fm.put (s, idx);
            bm.put(idx++, s);
        }
        return fm.get(s);
    }
}
