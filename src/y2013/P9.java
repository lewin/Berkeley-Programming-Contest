package y2013;
import java.util.*;
import java.io.*;

class P9 {
    private static Scanner in;
    private static HashMap<Integer, Double> res;
    private static ArrayList<Integer> order;
    public static void main (String [] args) throws IOException {
        in = new Scanner (System.in);
        PrintWriter out = new PrintWriter (System.out, true);
        
        int tree = 1;
        while (in.hasNext()) {
            res = new HashMap<Integer, Double> ();
            order = new ArrayList<Integer> ();
            
            parseTree(0);
            
            out.printf ("Tree %d:\n", tree++);
            for (int i = 0; i < order.size(); i++) {
                out.printf ("   %d %.4f\n", order.get(i), res.get(order.get(i)));
            }
        }
        out.close();
        System.exit(0);
    }
    
    private static double[] parseTree(double start) {
        int N = in.nextInt();
        int label = in.nextInt();
        order.add(label);
        if (N == 0) {
            res.put (label, start);
            return new double [] {start, start};
        }
        double fp = 0, lp = 0;
        double ns = start;
        for (int i = 0; i < N; i++) {
            double [] r = parseTree(ns);
            ns = r[1] + 1;
            if (i == 0) fp = r[0];
            if (i == N - 1) lp = r[0];
        }
        double cp = (fp + lp) / 2.;
        res.put (label, cp);
        return new double [] {cp, ns - 1};
    }
}
