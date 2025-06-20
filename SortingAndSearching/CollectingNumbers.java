import java.util.Scanner;

public class CollectingNumbers {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int kN = scanner.nextInt();

    int[] nums = new int[kN];
    int[] numToInd = new int[kN + 1];
    for (int i = 0; i < kN; i++) {
      nums[i] = scanner.nextInt();
      numToInd[nums[i]] = i;
    }

    int passThroughs = 0;
    for (int i = 1; i < kN; i++) {
      if (numToInd[i] > numToInd[i + 1]) passThroughs += 1;
    }

    System.out.println(passThroughs + 1);
  }
}
