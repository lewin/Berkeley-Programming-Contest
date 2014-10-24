package y2014;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


class P42 {
  public static long gcd(long a, long b) {
    return b == 0 ? a : gcd(b, a % b);
  }
  
  public static boolean[] forb;
  public static int best;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    PrintWriter out = new PrintWriter(System.out, true);

    int test = 0;
    int bd = 1000000;
    while (in.hasNext()) {
      N = in.nextInt();
      D = in.nextInt();

      long oN = N, oD = D;
      int k = in.nextInt();
      forb = new boolean[bd + 1];
      for (int i = 0; i < k; i++) {
        int a = in.nextInt();
        if (a > bd)
          continue;
        forb[a] = true;
      }

      ArrayList<Integer> res = null;
      best = INF;
      for (fden = D; fden <= 10000000; fden += D) {
        mp = new HashMap<Pair, ArrayList<Integer>> ();
        for (int i = 1; i <= 5000; i++) {
          if (forb[i] || fden % i != 0) continue;
          depth = 1;
          ArrayList<Integer> res2 = dfs(i, 0);
          if (res == null || (res2 != null && res2.size() < res.size())) {
            res = res2;
            if (res != null) best = res.size();
          }
        }
      }

      out.printf("Case %d: %d/%d=", ++test, oN, oD);
      boolean first = true;
      for (int i : res) {
        if (!first)
          out.print("+");
        out.print("1/" + i);
        first = false;
      }
      out.println();
    }
    out.close();
    System.exit(0);
  }

  public static int INF = 1 << 29;

  static class Pair {
    public int cur;
    public long N;
    public Pair (int cur, long N) {
      this.cur = cur;
      this.N = N;
    }
    
    @Override
    public int hashCode() {
      int prime = 298329847;
      long hc = N;
      hc = prime * hc + cur;
      return new Long(hc).hashCode();
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Pair))
        return false;
      Pair t = (Pair)other;
      return t.cur == cur && t.N == N;
    }
  }
  
  public static HashMap<Pair, ArrayList<Integer>> mp;
  public static long fden;
  public static int depth = 0;
  public static long N, D;
  private static ArrayList<Integer> dfs(int cur, long curnum) {
    if (N * fden == D * curnum) {
      return new ArrayList<Integer> ();
    }
    if (cur <= 1)
      return null;
    if (depth >= best) return null;
    
    long nextnum = curnum + (fden / cur);
    if (nextnum * D > fden * N) return null;
    
    Pair key = new Pair(cur, nextnum);
    if (mp.containsKey(key))
      return mp.get(key);
    
    ArrayList<Integer> res = null;
    for (int next = 1; next < cur; next++) {
      if (forb[next]) continue;
      depth++;
      ArrayList<Integer> res2 = dfs(next, nextnum);
      depth--;
      if (res2 != null)
        res2.add(cur);
      if (res == null || (res2 != null && res2.size() < res.size())) {
        res = res2;
      }
    }
    mp.put(key, res);
    return res;
  }
}
