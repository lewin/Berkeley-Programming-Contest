package y2013;
import java.util.*;
import java.io.*;

class P5 {
    private static Scanner in;
    private static boolean[] seen;
    public static void main (String [] args) throws IOException {
        in = new Scanner (System.in);
        PrintWriter out = new PrintWriter (System.out, true);
        
        while (in.hasNext()) {
            String s = in.next();
            seen = new boolean [26];
            for (char c : s.toCharArray())
                seen[c - 'a'] = true;
            out.printf ("%s %s\n", s, min (0, "", ""));
        }
        out.close();
        System.exit(0);
    }
    
    private static char toChar (int i) {
        return (char)(i + 'a');
    }
    
    private static String min (int idx, String s1, String s2) {
        if (idx == 26) return "[" + s1 + "|" +  s2 + "]";
        if (!seen [idx])
            return min (idx + 1, s1, s2);
        String res = min (idx + 1, s1 + toChar(idx), s2);
        for (int i = idx + 2; i < 26; i++) {
            String t1 = toChar(idx) + "-" + toChar(i);
            String t2 = "";
            for (int j = idx; j <= i; j++)
                if (!seen[j])
                    t2 += toChar(j);
            String s = min (i + 1, s1 + t1, s2 + t2);
            if (s.length() <= res.length())
                res = s;
        }
        return res;
    }
}
