import java.util.Random;
import java.util.Scanner;

public class LasVegas {

    static int lasVegasSearch(int[] arr, int target) {
        Random rand = new Random();
        int attempts = 0;

        while (true) {
            int index = rand.nextInt(arr.length);
            attempts++;
            System.out.println("Trial " + attempts +
                ": checked index " + index +
                " → value = " + arr[index]);

            if (arr[index] == target) {
                System.out.println("Successfully found " + target +
                    " at index " + index +
                    " after " + attempts + " trial(s).");
                return index;
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int[] numbers = {1, 2, 3, 4, 5, 6, 7};

            System.out.print("Enter a number to search from array: ");
            int target = sc.nextInt();

            int resultIndex = lasVegasSearch(numbers, target);
            System.out.println("Result: index = " + resultIndex +
                ", value = " + numbers[resultIndex]);
        }
    }
}