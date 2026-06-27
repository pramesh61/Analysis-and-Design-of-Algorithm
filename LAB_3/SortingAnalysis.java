
import java.util.Random;

public class SortingAnalysis {

    static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1, n2 = r - m;
        int[] L = new int[n1], R = new int[n2];
        for (int i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[m + 1 + j];
        }
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            arr[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];
        }
        while (i < n1) {
            arr[k++] = L[i++];
        }
        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        int tmp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = tmp;
        return i + 1;
    }

    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int tmp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = tmp;
        }
    }

    // ─── Helper: generate random array ───────────────────────────
    static int[] randomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] sizes = {100, 500, 1000, 2000, 3000};

        System.out.println("Performance Analysis of Sorting Algorithms");
        System.out.println("===========================================");
        System.out.printf("%-10s %-18s %-18s %-18s%n",
                "Size", "Merge Sort (ms)", "Quick Sort (ms)", "Selection Sort (ms)");
        System.out.println("-".repeat(66));

        for (int size : sizes) {
            int[] base = randomArray(size);

            int[] a1 = base.clone();
            long t1 = System.nanoTime();
            mergeSort(a1, 0, a1.length - 1);
            long mergeTime = (System.nanoTime() - t1) / 1_000_000;

            int[] a2 = base.clone();
            long t2 = System.nanoTime();
            quickSort(a2, 0, a2.length - 1);
            long quickTime = (System.nanoTime() - t2) / 1_000_000;

            int[] a3 = base.clone();
            long t3 = System.nanoTime();
            selectionSort(a3);
            long selTime = (System.nanoTime() - t3) / 1_000_000;

            System.out.printf("%-10d %-18d %-18d %-18d%n",
                    size, mergeTime, quickTime, selTime);
        }

        System.out.println("\nTime Complexity Summary:");
        System.out.println("  Merge Sort    : O(n log n) — always");
        System.out.println("  Quick Sort    : O(n log n) avg, O(n^2) worst");
        System.out.println("  Selection Sort: O(n^2)     — always");
    }
}
