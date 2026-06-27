import java.util.Arrays;
import java.util.Random;

public class GreedyAlgorithmsLab {

    static class Item {
        int id;
        double weight;
        double profit;
        double ratio;

        public Item(int id, double weight, double profit) {
            this.id = id;
            this.weight = weight;
            this.profit = profit;
            this.ratio = profit / weight;
        }
    }

    public static double getFractionalKnapsack(Item[] items, double capacity) {

        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double totalProfit = 0.0;
        double currentWeight = 0.0;

        for (Item item : items) {
            if (currentWeight + item.weight <= capacity) {
                currentWeight += item.weight;
                totalProfit += item.profit;
            } else {
                double remainingWeight = capacity - currentWeight;
                totalProfit += item.profit * (remainingWeight / item.weight);
                break;
            }
        }

        return totalProfit;
    }

    static class Job {
        int id;
        int deadline;
        int profit;

        public Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    public static int getJobSequencing(Job[] jobs) {

        Arrays.sort(jobs, (a, b) -> Integer.compare(b.profit, a.profit));

        int maxDeadline = 0;
        for (Job job : jobs) {
            if (job.deadline > maxDeadline) {
                maxDeadline = job.deadline;
            }
        }

        int[] slots = new int[maxDeadline + 1];
        Arrays.fill(slots, -1);

        int totalProfit = 0;

        for (Job job : jobs) {
            for (int j = Math.min(maxDeadline, job.deadline); j > 0; j--) {
                if (slots[j] == -1) {
                    slots[j] = job.id;
                    totalProfit += job.profit;
                    break;
                }
            }
        }

        return totalProfit;
    }

    public static void main(String[] args) {

        int[] inputSizes = {10, 100, 500, 1000, 5000};
        Random rand = new Random(42);

        System.out.println("-");
        System.out.printf("%-12s | %-30s | %-25s\n",
                "Input Size",
                "Fractional Knapsack Time (ms)",
                "Job Sequencing Time (ms)");
        System.out.println("-");

        for (int n : inputSizes) {

            Item[] items = new Item[n];
            double totalWeight = 0;

            for (int i = 0; i < n; i++) {
                double weight = 1 + rand.nextDouble() * 50;
                double profit = 10 + rand.nextDouble() * 500;
                items[i] = new Item(i + 1, weight, profit);
                totalWeight += weight;
            }

            double capacity = totalWeight * 0.5;

            Job[] jobs = new Job[n];
            for (int i = 0; i < n; i++) {
                int deadline = 1 + rand.nextInt(n / 2 + 1);
                int profit = 10 + rand.nextInt(1000);
                jobs[i] = new Job(i + 1, deadline, profit);
            }

            long startKnapsack = System.nanoTime();
            getFractionalKnapsack(items, capacity);
            long endKnapsack = System.nanoTime();

            double knapsackTimeMs =
                    (endKnapsack - startKnapsack) / 1_000_000.0;

            long startJob = System.nanoTime();
            getJobSequencing(jobs);
            long endJob = System.nanoTime();

            double jobTimeMs =
                    (endJob - startJob) / 1_000_000.0;

            System.out.printf("%-12d | %-30.4f | %-25.4f\n",
                    n, knapsackTimeMs, jobTimeMs);
        }

        System.out.println("-");
    }
}