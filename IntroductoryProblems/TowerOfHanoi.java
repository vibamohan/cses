import java.util.Scanner;

public class TowerOfHanoi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
                  scanner.close();
        System.out.println((long) Math.pow(2, n) - 1);
        hanoi(n, 1, 3, 2);
    }

    public static void hanoi(int n, int src, int dst, int aux) {
        if (n == 1) {
            System.out.println(src + " " + dst);
        } else {
            hanoi(n-1, src, aux, dst);
            System.out.println(src + " " + dst);
            hanoi(n-1, aux, dst, src);

            // Inorder traversal, moving src->aux, then aux->dst switching out aux nodes 
            // The base strategy is to move n-1 rings to aux, then switch the largest ring to the dest & repeat
        }
    }
}