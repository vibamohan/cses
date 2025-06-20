import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class Towers {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    final int kN = scanner.nextInt();

    List<Integer> cubes = new ArrayList<>();
    for (int i = 0; i < kN; i++) cubes.add(scanner.nextInt());

    int minTowers = 0;
    TreeMap<Integer, Integer> topK = new TreeMap<>();
    for (int i = 0; i < kN; i++) {
      Entry<Integer, Integer> nextEntry = topK.higherEntry(cubes.get(i));
      if (nextEntry == null) minTowers++;
      else {
        if (nextEntry.getValue() == 1) {
          topK.remove(nextEntry.getKey());
        } else {
          topK.put(nextEntry.getKey(), nextEntry.getValue() - 1);
        }
      }
      topK.put(cubes.get(i), topK.getOrDefault(cubes.get(i), 0) + 1);
    }

    System.out.println(minTowers);
  }
}
