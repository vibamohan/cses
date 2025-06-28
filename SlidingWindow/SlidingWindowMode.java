// package SlidingWindow;

import java.util.Scanner;
import java.util.TreeMap;

class Pair<T1, T2> {
  T1 mode;
  T2 actual;

  Pair(T1 mode, T2 actual) {
    this.mode = mode;
    this.actual = actual;
  }
}

public class SlidingWindowMode {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int k = sc.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

    TreeMap<Integer, Integer> freq = new TreeMap<>();

    for (int i = 0; i < k; i++) {
      freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);
    }
    System.out.print(freq.size() + " ");

    Pair<Integer, Integer> modePair = new Pair<>(0, 0);

    for (int i = k; i < n; i++) {
      int out = arr[i - k];
      freq.put(out, freq.get(out) - 1);
      if (freq.get(out) == 0) freq.remove(out);

      int in = arr[i];
      freq.put(in, freq.getOrDefault(in, 0) + 1);

      System.out.print(freq.size() + ((i != n - 1) ? " " : ""));
    }

    System.out.println();
  }
}
