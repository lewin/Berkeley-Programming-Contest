package y2013;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

class P8 {
    public static final double EPS = 1e-9;
    public static final int INF = 1 << 29;
    private static Scanner in;
    public static void main (String [] args) {
        in = new Scanner (System.in);
        PrintWriter out = new PrintWriter (System.out, true);
        
        while (in.hasNext()) {
            double w = in.nextDouble();
            double ang1 = in.nextDouble() / 180. * Math.PI;
            double ang2 = in.nextDouble() / 180. * Math.PI;
            Point a = new Point (0, 0);
            Point b = new Point (0, w);
            
            Point p1 = new Line (a, Math.tan(ang1)).intersection_point(
                    new Line (b, Math.tan (ang2)));
            
            double ang3 = in.nextDouble() / 180. * Math.PI;
            double ang4 = in.nextDouble() / 180. * Math.PI;
            
            Point p2 = new Line (a, Math.tan(ang3)).intersection_point(
                    new Line (b, Math.tan (ang4)));
            
            if (p1.x < p2.x + EPS) {
                out.println ("Not approaching");
                continue;
            }
            double time = p1.x / (p1.x - p2.x);
            double x = new Line (p1, p2).intersection_point(new Line (a, b)).y;
            
            out.printf ("Intersects at x=%.2f, t=%.2f\n", x, time);
        }
        out.close();
        System.exit(0);
    }
    
    static class Line {
        public double a, b, c;

        public Line(Point p1, Point p2) {
            if (p1.x == p2.x) {
                a = 1;
                b = 0;
                c = -p1.x;
            } else {
                b = 1;
                a = -(p1.y - p2.y) / (p1.x - p2.x);
                c = -(a * p1.x) - (b * p1.y);
            }
        }

        /**
         * Point-slope formula
         */
        public Line(Point p, double m) {
            a = -m;
            b = 1;
            c = -((a * p.x) + (b * p.y));
        }

        // returns true if line l is parallel to this
        public boolean parallel(Line l) {
            return (Math.abs(a - l.a) <= EPS && Math.abs(b - l.b) <= EPS);
        }

        // tests if two lines describe the same object
        public boolean same_line(Line l) {
            return (parallel(l) && Math.abs(c - l.c) <= EPS);
        }

        // finds intersection point between lines
        // if no intersection found, returns null;
        // if same line, returns INF, INF point
        public Point intersection_point(Line l) {
            Point p = new Point(INF, INF);
            if (same_line(l))
                return p;
            if (parallel(l))
                return null;

            p.x = (l.b * c - b * l.c) / (l.a * b - a * l.b);
            if (Math.abs(b) > EPS)
                p.y = -(a * p.x + c) / b;
            else
                p.y = -(l.a * p.x + l.c) / l.b;
            return p;
        }
    }
    static class Point implements Comparable<Point> {
        public double x, y, angle;
        public int idx;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
            angle = 0;
        }

        public Point(double x, double y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }

        public int compareTo(Point other) {
            return x == other.x ? (int) Math.signum(y - other.y)
                    : (int) Math.signum(x - other.x);
        }
    }
}
