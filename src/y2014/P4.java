package y2014;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


class P4 {
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
      int N = in.nextInt(), D = in.nextInt();
      mp = new HashMap<Triple, ArrayList<Integer>>();

      int oN = N, oD = D;
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
      int count = 0;
      for (int i = 1; i <= 5000; i++) {
        if (forb[i]) continue;
        if (gcd(D, i) == 1) continue;
        depth = 1;
        ArrayList<Integer> res2 = dfs(i, N, D);
        if (res == null || (res2 != null && res2.size() < res.size())) {
          res = res2;
          if (res != null) best = res.size();
        }
        if (res != null) count++;
        if (count > 150) break;
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
  
  static class Triple {
    public int cur;
    public long N, D;
    public Triple (int cur, long N, long D) {
      this.cur = cur;
      this.N = N;
      this.D = D;
    }
    
    @Override
    public int hashCode() {
      int prime = 298329847;
      long hc = D;
      hc = prime * hc + N;
      hc = prime * hc + cur;
      return new Long(hc).hashCode();
    }
    
    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Triple))
        return false;
      Triple t = (Triple)other;
      return t.cur == cur && t.N == N && t.D == D;
    }
  }
  
  public static HashMap<Triple, ArrayList<Integer>> mp;

  public static int depth = 0;
  private static ArrayList<Integer> dfs(int cur, long N, long D) {
    if (N == 0)
      return new ArrayList<Integer>();
    if (depth >= best) return null;
    Triple key = new Triple(cur, N, D);
    if (mp.containsKey(key))
      return mp.get(key);
    if (D > 10000000)
      return null;
    if (cur <= 1)
      return null;
    ArrayList<Integer> res = null;
    if (cur * N >= D) {
      for (int next = 1; next < cur; next++) {
        if (forb[next]) continue;
        depth++;
        ArrayList<Integer> res2 = dfs(next, N * cur - D, D * cur);
        depth--;
        if (res2 != null)
          res2.add(cur);
        if (res == null || (res2 != null && res2.size() < res.size())) {
          res = res2;
        }
      }
    }
    mp.put(key, res);
    return res;
  }
}
