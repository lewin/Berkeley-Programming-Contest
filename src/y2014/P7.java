package y2014;
import java.io.PrintWriter;
import java.util.Scanner;


class P7 {

  public static int[] inv = new int[] {0, 1, 3, 2, 4};

  private static void rref(int[][] M) {
    int row = M.length;
    if (row == 0)
      return;

    int col = M[0].length;

    int lead = 0;
    for (int r = 0; r < row; r++) {
      if (lead >= col)
        return;

      int k = r;
      while (M[k][lead] == 0) {
        k++;
        if (k == row) {
          k = r;
          lead++;
          if (lead == col)
            return;
        }
      }
      int[] temp = M[r];
      M[r] = M[k];
      M[k] = temp;

      int lv = M[r][lead];
      for (int j = 0; j < col; j++)
        M[r][j] = (M[r][j] * inv[lv]) % 5;

      for (int i = 0; i < row; i++) {
        if (i != r) {
          lv = M[i][lead];
          for (int j = 0; j < col; j++)
            M[i][j] = (M[i][j] + (5 - (lv * M[r][j]) % 5)) % 5;
        }
      }
      lead++;
    }
  }

  public static int[] dx = new int[] {1, 1, 1, 0, 0, 0, 4, 4, 4};
  public static int[] dy = new int[] {4, 0, 1, 4, 0, 1, 4, 0, 1};
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    PrintWriter out = new PrintWriter(System.out, true);

    int test = 0;
    while (in.hasNext()) {
      int[][] grid = new int[5][5];
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++)
          grid[i][j] = in.nextInt();
      }
      int[][] mat = new int[25][26];
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          for (int k = 0; k < dx.length; k++) {
            int nx = (i + dx[k]) % 5, ny = (j + dy[k]) % 5;
            mat[i * 5 + j][nx * 5 + ny] = 1;
          }
          mat[i * 5 + j][25] = (5 - grid[i][j]) % 5;
        }
      }
      
      rref(mat);
      
      out.println("Case " + (++test) + ":");
      for (int i = 0; i < 5; i++) {
        out.print(mat[i * 5][25]);
        for (int j = 1; j < 5; j++)
          out.print(" " + mat[i * 5 + j][25]);
        out.println();
      }
    }
    out.close();
    System.exit(0);
  }
}
