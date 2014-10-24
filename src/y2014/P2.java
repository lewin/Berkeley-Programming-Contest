package y2014;
import java.io.PrintWriter;
import java.util.Scanner;


class P2 {
  public static void main (String[] args) {
    Scanner in = new Scanner (System.in);
    PrintWriter out = new PrintWriter (System.out, true);
    
    int test = 0;
    while (in.hasNext()) {
      String L = in.next(), U = in.next();
      
      out.printf("Case %d. %d\n", ++test, count(U) - count(L));
    }
    out.close();
    System.exit(0);
  }
  
  public static char[] c;
  public static long[][][][] dp;
  public static long count(String s) {
    if (s.equals("0")) return 0;
    c = s.toCharArray();
    for (int i = 0, j = c.length - 1; i < j; i++, j--) {
      char t = c[i]; c[i] = c[j]; c[j] = t;
    }
    dp = new long[c.length][c.length][2][2];
    for (int i = 0; i < c.length; i++) {
      for (int j = 0; j < c.length; j++)
        for (int k = 0; k < 2; k++)
          for (int l = 0; l < 2; l++)
            dp[i][j][k][l] = -1;
    }
    long res = 0;
    int pos2 = 0;
    for (int pos1 = 0; pos1 < c.length; pos1++) {
      for (int i = 1; i <= 9; i++) {
        int next_less = pos1 == c.length - 1 ? 0 : 1, next_hasless = 0;
        if (i > (c[pos1] - '0') && next_less == 0) continue;
        if (i < (c[pos1] - '0')) next_less = 1;
        if (i > (c[pos2] - '0')) next_hasless = 0;
        if (i < (c[pos2] - '0')) next_hasless = 1;
        res += dfs(pos1 - 1, pos2 + 1, next_less, next_hasless);
      }
    }
    return res + 1;
  }
  
  public static long dfs (int pos1, int pos2, int less, int hasless) {
    if (pos1 < pos2) {
      return less == 1 || (less == 0 && hasless == 1) ? 1 : 0;
    }
    if (dp[pos1][pos2][less][hasless] != -1)
      return dp[pos1][pos2][less][hasless];
    long res = 0;
    for (int i = 0; i <= 9; i++) {
      int next_less = less, next_hasless = hasless;
      if (i > (c[pos1] - '0') && less == 0) continue;
      if (i < (c[pos1] - '0')) next_less = 1;
      if (i > (c[pos2] - '0')) next_hasless = 0;
      if (i < (c[pos2] - '0')) next_hasless = 1;
      res += dfs(pos1 - 1, pos2 + 1, next_less, next_hasless);
    }
    return dp[pos1][pos2][less][hasless] = res;
  }
}
