import java.util.ArrayList;
import java.util.Scanner;

enum Directions {
  UP(-1, 0),
  DOWN(1, 0),
  LEFT(-1, 0),
  RIGHT(1, 0);

  int x;
  int y;

  Directions(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

public class NumberSpiral {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
  }
}
