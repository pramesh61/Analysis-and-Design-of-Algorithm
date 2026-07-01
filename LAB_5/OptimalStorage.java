import java.util.Arrays;
import java.util.Scanner;

public class OptimalStorage {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of files: ");
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Number of files must be positive.");
            scanner.close();
            return;
        }

        int[] files = new int[n];
        System.out.println("Enter file lengths: ");
        for (int i = 0; i < n; i++) {
            files[i] = scanner.nextInt();
        }

        Arrays.sort(files);

        int[] retrievalTimes = new int[n];
        int currentSum = 0;
        int totalRetrievalTime = 0;

        for (int i = 0; i < n; i++) {
            currentSum += files[i];
            retrievalTimes[i] = currentSum;
            totalRetrievalTime += currentSum;
        }

        double meanRetrievalTime = (double) totalRetrievalTime / n;

        System.out.print("\nOptimal Order: ");
        for (int i = 0; i < n; i++) {
            System.out.print(files[i] + (i < n - 1 ? " " : ""));
        }

        System.out.print("\nRetrieval Times: ");
        for (int i = 0; i < n; i++) {
            System.out.print(retrievalTimes[i] + (i < n - 1 ? " " : ""));
        }

        System.out.println("\nTotal Retrieval Time = " + totalRetrievalTime);
        System.out.println("Mean Retrieval Time = " + meanRetrievalTime);

        scanner.close();
    }
}
