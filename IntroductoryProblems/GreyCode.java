import java.util.Scanner;

public class GreyCode {

    /**
        HOW TO CALCULATE GRAY CODE
        <p>
        Steps to generate the Gray code sequence for numbers 0 to n-1:
        <ol>
        <li>Convert each integer from 0 to n-1 to its binary representation.</li>
        <li>Shift the binary representation one bit to the right.</li>
        <li>XOR the original binary representation with the shifted version to obtain the Gray code.</li>
        </ol>
        <p>
        Example for 3-bit numbers:
        <pre>
        000 (0) -> 000 XOR 000 = 000
        001 (1) -> 001 XOR 000 = 001
        010 (2) -> 010 XOR 001 = 011
        011 (3) -> 011 XOR 001 = 010
        100 (4) -> 100 XOR 010 = 110
        101 (5) -> 101 XOR 010 = 111
        110 (6) -> 110 XOR 011 = 101
        111 (7) -> 111 XOR 011 = 100
        </pre>
    */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for (int i = 0; i < 1 << n; i++) {
            int g = i ^ (i >> 1);
            System.out.println(String.format("%" + n + "s", Integer.toBinaryString(g)).replace(' ', '0'));
        }

    }
}
