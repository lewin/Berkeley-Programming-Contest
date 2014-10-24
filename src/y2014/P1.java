package y2014;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


class P1 {
  public static char[] s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ".toCharArray();
  
  public static char shift (char let, int m) {
    if (let == '.') return let;
    int pos = let == ' ' ? 26 : let - 'A';
    return s[(pos + m) % s.length];
  }
  public static void main (String[] args) throws IOException {
    BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
    PrintWriter out = new PrintWriter (System.out, true);
    String line = in.readLine();
    while (line != null) {
      String s = line;
      String p = in.readLine();
      for (int i = 0; i < 26; i++) {
        String t = "";
        for (char c : s.toCharArray())
          t += shift(c, i);
        if (t.contains(p)) {
          out.println(t);
          break;
        }
      }
      
      line = in.readLine();
    }
    out.close();
    System.exit(0);
  }
}
