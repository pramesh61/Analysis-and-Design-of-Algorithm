
import java.util.Random;
import java.util.Scanner;

public class MonteCarlo {

    static boolean monteCarloSearch(int[] arr, int target, int k) {
        Random rand = new Random();
        boolean is_found = false;

        for (int i = 0; i < k; i++) {
            int index = rand.nextInt(arr.length);
            System.out.println("Trial" + i);

            if (arr[index] == target) {
                System.out.println("Sucessfully found");
                is_found = true;
                break;
            } else {
                System.out.println("Not Found");
            }
        }

        return is_found;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int[] numbers = {1, 2, 3, 4, 5, 6, 7};

            System.out.println("Enter a number to search from array");
            int target = sc.nextInt();

            System.out.println("Enter number of trial");
            int trial = sc.nextInt();

            boolean tag = monteCarloSearch(numbers, target, trial);

            if (tag) {
                System.out.println("So number is found");
            } else {
                System.out.println("So number is not found after " + trial + " trial");
            }
        }
    }
}
