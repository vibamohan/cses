import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DistinctNums {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Set<Integer> distinct = new HashSet<>();
        for (int i = 0; i <n; i++) {
            distinct.add(scanner.nextInt());
        }
        System.out.println(distinct.size());
    }
}