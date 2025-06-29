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
   * DP memo maintains what is the maxPages for buying first i books, with j max cost 
   * 
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

    int[][] dp = new int[kBooks + 1][kMaxCost + 1];

    // bottom up dp
    for (int i = 1; i <= kBooks; i++) {
        int curPrice = bookArr[i - 1].price;
        int curPages = bookArr[i - 1].pages;

        for (int j = 1; j <= kMaxCost; j++) {
            dp[i][j] = dp[i-1][j]; // whats the max pages for i - 1 books, with our current maxCost? 
            if (curPrice <= j) {
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - curPrice] + curPages);
                // what's more optimal, excluding this book, or including this book? 
            }
        }
    }
    
    System.out.println(dp[kBooks][kMaxCost]);
  }
}
