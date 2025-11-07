// Member4_Main.java
// Member 4: Menu, data generation, timing, and comparison table

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Member4_Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[] data = new int[0];

        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    data = readNumbersFromInput();
                    System.out.println("Data stored.");
                    break;
                case "2":
                    data = generateRandomNumbers();
                    System.out.println("Random data generated: " + Arrays.toString(data));
                    break;
                case "3":
                    if (data.length == 0) {
                        System.out.println("No data available. Please enter or generate data first.");
                        break;
                    }
                    performAndPrint("Bubble Sort", data, (arr, stepsOut) -> Member1_Bubble.bubbleSort(arr, stepsOut));
                    break;
                case "4":
                    if (data.length == 0) {
                        System.out.println("No data available. Please enter or generate data first.");
                        break;
                    }
                    performAndPrint("Merge Sort", data, (arr, stepsOut) -> Member2_Merge.mergeSort(arr, stepsOut));
                    break;
                case "5":
                    if (data.length == 0) {
                        System.out.println("No data available. Please enter or generate data first.");
                        break;
                    }
                    performAndPrint("Quick Sort", data, (arr, stepsOut) -> Member3_Quick.quickSort(arr, stepsOut));
                    break;
                case "6":
                    if (data.length == 0) {
                        System.out.println("No data available. Please enter or generate data first.");
                        break;
                    }
                    compareAll(data);
                    break;
                case "7":
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please select from 1 to 7.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Data Sorter: Sorting Algorithm Comparison Tool ---");
        System.out.println("1. Enter numbers manually");
        System.out.println("2. Generate random numbers");
        System.out.println("3. Perform Bubble Sort");
        System.out.println("4. Perform Merge Sort");
        System.out.println("5. Perform Quick Sort");
        System.out.println("6. Compare all algorithms (show performance table)");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int[] readNumbersFromInput() {
        while (true) {
            System.out.print("Enter integers separated by spaces: ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("No input detected. Try again.");
                continue;
            }
            String[] parts = line.split("\\s+");
            int[] nums = new int[parts.length];
            try {
                for (int i = 0; i < parts.length; i++) {
                    nums[i] = Integer.parseInt(parts[i]);
                }
                return nums;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter integers only.");
            }
        }
    }

    private static int[] generateRandomNumbers() {
        while (true) {
            try {
                System.out.print("How many numbers to generate? (e.g., 10): ");
                int n = Integer.parseInt(scanner.nextLine().trim());
                if (n <= 0) {
                    System.out.println("Please enter a positive integer.");
                    continue;
                }
                System.out.print("Minimum value (e.g., 0): ");
                int low = Integer.parseInt(scanner.nextLine().trim());
                System.out.print("Maximum value (e.g., 100): ");
                int high = Integer.parseInt(scanner.nextLine().trim());
                if (low > high) {
                    System.out.println("Minimum cannot be greater than maximum.");
                    continue;
                }
                Random rnd = new Random();
                int[] arr = new int[n];
                for (int i = 0; i < n; i++) {
                    arr[i] = low + rnd.nextInt(high - low + 1);
                }
                return arr;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter integers only.");
            }
        }
    }

    // Functional interface to call sorting methods uniformly
    @FunctionalInterface
    interface SortFunc {
        int[] apply(int[] arr, long[] stepsOut);
    }

    private static void performAndPrint(String name, int[] data, SortFunc func) {
        long[] stepsOut = new long[1];
        int[] copy = data.clone();

        long start = System.nanoTime();
        int[] sorted = func.apply(copy, stepsOut);
        long end = System.nanoTime();

        double elapsedSec = (end - start) / 1_000_000_000.0;
        System.out.println("\n=== " + name + " Result ===");
        System.out.println("Sorted output: " + Arrays.toString(sorted));
        System.out.printf("Time: %.6f seconds | Steps: %d%n", elapsedSec, stepsOut[0]);
    }

    private static void compareAll(int[] data) {
        String[] names = {"Bubble Sort", "Merge Sort", "Quick Sort"};
        SortFunc[] funcs = {
            (arr, stepsOut) -> Member1_Bubble.bubbleSort(arr, stepsOut),
            (arr, stepsOut) -> Member2_Merge.mergeSort(arr, stepsOut),
            (arr, stepsOut) -> Member3_Quick.quickSort(arr, stepsOut)
        };

        long[] timesNs = new long[names.length];
        long[] steps = new long[names.length];
        int[][] outputs = new int[names.length][];

        for (int i = 0; i < names.length; i++) {
            long[] stepsOut = new long[1];
            int[] copy = data.clone();
            long start = System.nanoTime();
            int[] sorted = funcs[i].apply(copy, stepsOut);
            long end = System.nanoTime();
            timesNs[i] = end - start;
            steps[i] = stepsOut[0];
            outputs[i] = sorted;
        }

        System.out.println("\n=== Comparison Table ===");
        System.out.printf("%-15s | %10s | %10s%n", "Algorithm", "Time(s)", "Steps");
        System.out.println("-----------------------------------------------");
        for (int i = 0; i < names.length; i++) {
            double secs = timesNs[i] / 1_000_000_000.0;
            System.out.printf("%-15s | %10.6f | %10d%n", names[i], secs, steps[i]);
        }

        // Also print sorted outputs
        for (int i = 0; i < names.length; i++) {
            System.out.println();
            System.out.println("=== " + names[i] + " Result ===");
            System.out.println("Sorted output: " + Arrays.toString(outputs[i]));
            System.out.printf("Steps: %d%n", steps[i]);
        }
    }
}
