import java.util.Arrays;
import java.util.Scanner;

public class Apartments {
   /**
    * @param request The size we want
    * @param size The actual size
    * @param maxAllowedDeviation The maximum deviation 
    * @return If the size and the request's difference falls within the deviation
    */
   static boolean withinThreshold(int request, int size, int maxAllowedDeviation) {
        return Math.abs(request - size) <= maxAllowedDeviation;
   }

   /**
    * This uses the 2 pointer technique, taking in the 2 arrays through stdin
    * Sorting the 2 arrays, if the currently viewing size lies within the request, give this apt and move on
    * If the request is too small for the current size, go forward one request
    * If the request is too big for the current size, go forward one size
    * Output number of requests satisfied
    */
   public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int kApplicants = scanner.nextInt();
        int kApartments = scanner.nextInt();
        int kMaxDeviation = scanner.nextInt();

        int[] aptSizes = new int[kApartments];
        int[] requests = new int[kApplicants];

        for (int i = 0; i < kApplicants; i++) requests[i] = scanner.nextInt();
        for (int i = 0; i < kApartments; i++) aptSizes[i] = scanner.nextInt();

        Arrays.sort(aptSizes);
        Arrays.sort(requests);

        int sizePtr = 0;
        int requestPtr = 0;
        int granted = 0;

        while (sizePtr < kApartments && requestPtr < kApplicants) {
            if (withinThreshold(requests[requestPtr], aptSizes[sizePtr], kMaxDeviation)) {
                sizePtr++;
                requestPtr++;
                granted++;
            } else if (requests[requestPtr] < aptSizes[sizePtr]) {
                requestPtr++;
            } else {
                sizePtr++;
            }
        }

        System.out.println(granted);
   } 
}
