import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class SlidingWindowMin {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    /** Standard sliding window pattern Keep expanding and minning till you exceed window */
    int n = sc.nextInt();
    int k = sc.nextInt();
    long x = sc.nextLong();
    long a = sc.nextLong();
    long b = sc.nextLong();
    long c = sc.nextLong();

    long[] window = new long[k];
    long windowMin = 0;
    long result = 0;
    int ptr = 0;
    Deque<Integer> deque = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
      int idx = i % k;
      window[idx] = x;

      while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
        deque.pollFirst();
      }

      while (!deque.isEmpty() && window[deque.peekLast() % k] >= x) {
        deque.pollLast();
      }

      deque.offerLast(i);

      if (i >= k - 1) {
        result ^= window[deque.peekFirst() % k];
      }

      ptr = (ptr + 1) % k;
      x = (a * x + b) % c;
    }

    System.out.println(result);
  }
}
