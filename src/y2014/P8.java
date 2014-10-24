package y2014;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;


class P8 {
  public static HashMap<String, Integer> mp;
  public static HashMap<Integer, String> rmp;
  public static int idx;
  
  public static int getIdx(String s) {
    if (!mp.containsKey(s)) {
      mp.put(s, idx);
      rmp.put(idx, s);
      idx++;
    }
    return mp.get(s);
  }
  
  public static String getStr(int id) {
    return rmp.get(id);
  }
  
  static class Graph {
    public HashMap<Integer, ArrayList<Integer>> graph;
    public Graph() {
      graph = new HashMap<Integer, ArrayList<Integer>> ();
    }
    
    public void addEdge(Integer a, Integer b) {
      if (!graph.containsKey(a)) {
        graph.put(a, new ArrayList<Integer>());
      }
      graph.get(a).add(b);
    }
  }
  
  public static Graph g, rg;
  public static int[] order;
  public static int counter;
  public static boolean[] vis;
  public static void dfs(int node) {
    vis[node] = true;
    if (g.graph.containsKey(node)) {
      for (int k : g.graph.get(node)) {
        if (!vis[k])
          dfs(k);
      }
    }
    order[counter++] = node;
  }
  
  public static boolean[] vis2;
  public static ArrayList<Integer> collect;
  public static void dfs2(int node) {
    vis2[node] = true;
    collect.add(node);
    if (rg.graph.containsKey(node)) {
      for (int k : rg.graph.get(node)) {
        if (!vis2[k])
          dfs2(k);
      }
    }
  }
  
  public static void main (String[] args) {
    Scanner in = new Scanner (System.in);
    PrintWriter out = new PrintWriter (System.out, true);
    
    g = new Graph();
    rg = new Graph();
    idx = 0;
    mp = new HashMap<String, Integer> ();
    rmp = new HashMap<Integer, String> ();
    while(in.hasNext()) {
      int to = getIdx(in.next());
      in.next();
      String k = in.next();
      while (!k.equals(";")) {
        int from = getIdx(k);
        g.addEdge(from, to);
        rg.addEdge(to, from);
        k = in.next();
      }
    }
    int N = idx;
    order = new int[N];
    vis = new boolean[N];
    counter = 0;
    for (int i = 0; i < N; i++) {
      if (!vis[i]) {
        dfs(i);
      }
    }
    
    vis2 = new boolean[N];
    for (int i = N - 1; i >= 0; i--) {
      int node = order[i];
      if (!vis2[node]) {
        collect = new ArrayList<Integer> ();
        dfs2(node);
        ArrayList<String> res = new ArrayList<String> ();
        for (int k : collect)
          res.add(getStr(k));
        Collections.sort(res);
        out.print(res.get(0));
        for (int k = 1; k < res.size(); k++) {
          out.print(" " + res.get(k));
        }
        out.println();
      }
    }
    out.close();
    System.exit(0);
    
  }
}
