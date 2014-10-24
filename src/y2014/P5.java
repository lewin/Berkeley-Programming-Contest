package y2014;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;


class P5 {
  
  static class Hand {
    public LinkedList<Character> queue;
    public Hand() {
      queue = new LinkedList<Character> ();
    }
    
    public int size() {
      return queue.size();
    }
    
    public void addHand(Hand h) {
      char c = h.getCard();
      while (c != ' ') {
        this.addCard(c);
        c = h.getCard();
      }
    }
    
    public void addCard(char val) {
      queue.addLast(val);
    }
    
    public char getCard() {
      if (queue.size() == 0) {
        return ' ';
      }
      return queue.removeFirst();
    }
  }
  
  public static void main (String[] args) {
    Scanner in = new Scanner (System.in);
    PrintWriter out = new PrintWriter(System.out, true);
    
    while (in.hasNext()) {
      Hand[] h = new Hand[2];
      h[0] = new Hand();
      h[1] = new Hand();
      for (int i = 0; i < 26; i++)
        h[0].addCard(in.next().charAt(1));
      for (int i = 0; i < 26; i++)
        h[1].addCard(in.next().charAt(1));
      

      int player = 0;
      int left = 1;
      boolean fin = false;
      outer : while (true) {
        Hand pile = new Hand();
        while (true) {
          inner : while (left-- > 0) {
            char c = h[player].getCard();
            pile.addCard(c);
            switch (c) {
              case ' ':
                break outer;
              case 'J':
                left = 1;
                fin = true;
                player = 1 ^ player;
                continue inner;
              case 'Q':
                left = 2;
                fin = true;
                player = 1 ^ player;
                continue inner;
              case 'K':
                left = 3;
                fin = true;
                player = 1 ^ player;
                continue inner;
              case 'A':
                left = 4;
                fin = true;
                player = 1 ^ player;
                continue inner;
              default:
                break;
            }
          }
          if (fin) {
            h[player ^ 1].addHand(pile);
            player = 1 ^ player;
            left = 1;
            fin = false;
            continue outer;
          } else {
            player = 1 ^ player;
            left = 1;
          }
        }
      }
      
      out.println (Math.max(h[0].size(), h[1].size()));
    }
    out.close();
    System.exit(0);
  }
}
