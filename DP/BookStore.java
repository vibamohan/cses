import java.util.Objects;
import java.util.Scanner;

class Book {
  public int price;
  public int pages;

  Book(int price, int pages) {
    this.price = price;
    this.pages = pages;
  }

  /** For debugging */
  public String toString() {
    return "Book [" + " price = " + price + ", pages = " + pages + "]";
  }

  public boolean equals(Object other) {
    return (other instanceof Book)
        && ((Book) (other)).price == this.price
        && ((Book) (other)).pages == this.pages;
  }

  public int hashCode() {
    return Objects.hash(price, pages);
  }
}

public class BookStore {
  /**
   * DP memo maintains what is the maxPages for buying first i books, with j max cost Both the 1d
   * and 2d dp maintain the same info but 1d optimizes it because we only use the previous row to
   * calculate the currnt row We go in reverse to avoid overwriting the previous row: refer this
   * book:
   * https://www.hello-algo.com/en/chapter_dynamic_programming/knapsack_problem/#3-method-three-dynamic-programming
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    final int kBooks = scanner.nextInt();
    final int kMaxCost = scanner.nextInt();

    Book[] bookArr = new Book[kBooks];

    for (int i = 0; i < kBooks; i++) {
      bookArr[i] = new Book(scanner.nextInt(), 0);
    }

    for (int i = 0; i < kBooks; i++) {
      bookArr[i].pages = scanner.nextInt();
    }

    // This uses 2D dp but it fails the final test case, so
    // switched to 1D dp to save space and time

    // int[][] dp = new int[kBooks + 1][kMaxCost + 1];

    // // bottom up dp
    // for (int i = 1; i <= kBooks; i++) {
    //     int curPrice = bookArr[i - 1].price;
    //     int curPages = bookArr[i - 1].pages;

    //     for (int j = 1; j <= kMaxCost; j++) {
    //         dp[i][j] = dp[i-1][j]; // whats the max pages for i - 1 books, with our current
    // maxCost?
    //         if (curPrice <= j) {
    //             dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - curPrice] + curPages);
    //             // what's more optimal, excluding this book, or including this book?
    //         }
    //     }
    // }

    int[] dp = new int[kMaxCost + 1];
    // bottom up dp, but using 1D array to save space/time
    // dp[j] = max pages for buying first i books, with j max cost

    for (int i = 0; i < kBooks; i++) {
      int curPrice = bookArr[i].price;
      int curPages = bookArr[i].pages;
      for (int j = kMaxCost; j >= curPrice; j--) {
        dp[j] = Math.max(dp[j], dp[j - curPrice] + curPages);
      }
    }

    System.out.println(dp[kMaxCost]);
  }
}
