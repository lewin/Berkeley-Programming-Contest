package y2014;
import java.io.PrintWriter;
import java.util.Scanner;


class P6 {
  public static void main (String[] args) {
    Scanner in = new Scanner (System.in);
    PrintWriter out = new PrintWriter (System.out, true);
    
    while (in.hasNext()) {
      long m = in.nextLong();
      out.printf("%d => %s\n", m, solve(m));
    }
    out.close();
    System.exit(0);
  }
  
  public static String solve(long m) {
    long count = 0, sum = 1;
    while (count + sum < m  ) {
      count += sum;
      sum++;
    }
    long rem = m - count;
    return (sum-rem+1)+"/"+rem; 
    
  }
}
