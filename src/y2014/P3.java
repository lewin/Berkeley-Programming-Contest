package y2014;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class P3 {
  public static final double  EPS = 1e-9;
  public static final int     INF = Integer.MAX_VALUE >> 2;
  
  // returns dot product of |a-b|*|b-c|
  public static double dot (Point a, Point b, Point c) {
      Point ab = new Point (b.x - a.x, b.y - a.y);
      Point bc = new Point (c.x - b.x, c.y - b.y);
      return ab.x * bc.x + ab.y * bc.y;
  }
  
  // return cross product of |b-a|x|c-a|
  public static double cross (Point a, Point b, Point c) {
      Point ab = new Point (b.x - a.x, b.y - a.y);
      Point ac = new Point (c.x - a.x, c.y - a.y);
      return ab.x * ac.y - ab.y * ac.x;
  }
  
  // returns if a,b,c are colinear
  public static boolean colinear (Point a, Point b, Point c) {
      return (a.x - b.x) * (a.y - c.y) == (a.x - c.x) * (a.y - b.y);
  }
  
  // returns distance between points a, and b
  public static double distance (Point a, Point b) {
      return Math.hypot(a.x - b.x, a.y - b.y);
  }
  
  // returns true iff p lies in box with opposite corners b1, b2
  public static boolean point_in_box (Point p, Point b1, Point b2) {
      return ((p.x >= Math.min (b1.x, b2.x)) && (p.x <= Math.max (b1.x, b2.x))
              && (p.y >= Math.min (b1.y, b2.y)) && (p.y <= Math.max (b1.y, b2.y)));
  }
  static class Line {
    public double   a, b, c;
    
    // ax + by = c
    
    /**
     * Standard representation
     */
    public Line (double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    /**
     * Unique line between two points
     */
    public Line (Point p1, Point p2) { // two points
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
    public Line (Point p, double m) {
        a = -m;
        b = 1;
        c = -((a * p.x) + (b * p.y));
    }
    
    // returns true if line l is parallel to this
    public boolean parallel (Line l) {
        return (Math.abs (a - l.a) <= EPS && Math.abs (b - l.b) <= EPS);
    }
    
    // tests if two lines describe the same object
    public boolean same_line (Line l) {
        return (parallel (l) && Math.abs (c - l.c) <= EPS);
    }
    
    // finds intersection point between lines
    // if no intersection found, returns null;
    // if same line, returns INF, INF point
    public Point intersection_point (Line l) {
        Point p = new Point (INF, INF);
        if (same_line (l))
            return p;
        if (parallel (l))
            return null;
        
        p.x = (l.b * c - b * l.c) / (l.a * b - a * l.b);
        if (Math.abs (b) > EPS)
            p.y = -(a * p.x + c) / b;
        else
            p.y = -(l.a * p.x + l.c) / l.b;
        return p;
    }
    
    // Finds the point closest to p lying on this line
    public Point closest_Point (Point p) {
        Point p_c = new Point (INF, INF);
        if (Math.abs (b) <= EPS) {
            p_c.x = -c;
            p_c.y = p.y;
            return p_c;
        }
        if (Math.abs (a) <= EPS) {
            p_c.x = p.x;
            p_c.y = -c;
            return p_c;
        }
        Line perp = new Line (p, 1 / a);
        return intersection_point (perp);
    }
  }
  
  static class Segment {
    public Point    a, b;
    public double   length;
    
    public Segment (Point a, Point b) {
        this.a = a;
        this.b = b;
        length = distance (a, b);
    }
    
    /**
     * Returns shortest point distance
     * @param c point we are considering
     * @return shortest length from c to this segment
     */
    public double ptDist (Point c) {
        if (dot (a, b, c) > 0)
            return distance (b, c);
        if (dot (b, a, c) > 0)
            return distance (a, c);
        if (length == 0)
            return distance (a, c);
        return Math.abs (cross (a, b, c) / length);
    }
    
    public Point closestPoint(Point p) {
      Point x = new Line(a, b).closest_Point(p);
      if (point_in_box(x, a, b)) {
        return x;
      }
      if (distance(a, p) < distance(b, p))
        return a;
      return b;
    }
  }
  
  static class Point {
    public double x, y;
    
    public Point (double x, double y) {
        this.x = x;
        this.y = y;
    }
  }

  public static void main (String[] args) {
    Scanner in = new Scanner (System.in);
    PrintWriter out = new PrintWriter (System.out, true);
    
    
    int N = in.nextInt(), B = in.nextInt();
    Point me = new Point(in.nextInt(), in.nextInt());
    Segment[] route = new Segment[N];
    double[] sp = new double[N];
    Point prev = new Point(in.nextInt(), in.nextInt());
    for (int i = 0; i < N; i++) {
      sp[i] = in.nextInt();
      Point next = new Point(in.nextInt(), in.nextInt());
      route[i] = new Segment (prev, next);
      prev = next;
    }
    
    int myseg = 0;
    Point act = route[0].closestPoint(me);
    for (int i = 1; i < N; i++) {
      Point d = route[i].closestPoint(me);
      if (distance(d, me) < distance(act, me)) {
        act = d;
        myseg = i;
      }
    }
    
    ArrayList<Integer> dists = new ArrayList<Integer> ();
    for (int t = 0; t < B; t++) {
      Point b = new Point(in.nextInt(), in.nextInt());
      int whseg = 0;
      Point best = route[0].closestPoint(b);
      for (int i = 1; i < N; i++) {
        Point d = route[i].closestPoint(b);
        if (distance(d, b) < distance(best, b)) {
          best = d;
          whseg = i;
        }
      }
      
      if (whseg > myseg) continue;
      
      double dist = 0;
      while (whseg < myseg) {
        dist += distance(best, route[whseg].b) / sp[whseg];
        best = route[whseg].b;
        whseg++;
      }
      if (distance(best, route[myseg].a) > distance(act, route[myseg].a))
          continue;
      dist += distance(best, act) / sp[whseg];
      
      dists.add((int)(dist + 0.5));
    }
    Collections.sort(dists);
    for (int i : dists)
      out.println(i);
    out.close();
    System.exit(0);
    
  }
}
