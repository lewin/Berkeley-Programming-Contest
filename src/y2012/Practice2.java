package y2012;
import java.io.*;
import java.util.*;
class Practice2 {
	public static void main (String [] args) throws IOException {
		BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
		PrintWriter out = new PrintWriter (System.out, true);
		
		String line = in.readLine();
		int t = 1;
		StringTokenizer st;
		while (line != null) {
			st = new StringTokenizer (line);
			ArrayList <Point> points = new ArrayList <Point> ();
			while (st.hasMoreTokens ()) {
				double x = Double.parseDouble (st.nextToken ());
				double y = Double.parseDouble (st.nextToken ());
				points.add (new Point (x, y));
			}
			double max = 0;
			for (int i = 0; i < points.size(); i++)
				for (int j = i + 1; j < points.size(); j++)
					if (points.get (i).dist (points.get (j)) > max)
						max = points.get (i).dist (points.get (j));
			out.printf ("Set #%d: Diameter is %.2f\n", t++, Math.sqrt (max));
			line = in.readLine();
		}
		out.close();
		System.exit(0);
	}
	
	static class Point {
		public double x, y;
		public Point (double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		public double dist (Point other) {
			return (x - other.x) * (x - other.x) + (y - other.y) * (y - other.y);
		}
	}
}
