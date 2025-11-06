// Member3_Quick.java
// Member 3: Quick Sort with step counting
// In-place quicksort implementation with steps tracked via long[] wrapper

public class Member3_Quick {

    /**
     * Public API: quickSort (returns sorted array and sets stepsOut[0])
     */
    public static int[] quickSort(int[] arr, long[] stepsOut) {
        int[] a = arr.clone();
        long[] steps = new long[1]; // accumulator
        quickSortInPlace(a, 0, a.length - 1, steps);
        stepsOut[0] = steps[0];
        return a;
    }

    private static void quickSortInPlace(int[] a, int low, int high, long[] steps) {
        if (low < high) {
            int p = partition(a, low, high, steps);
            quickSortInPlace(a, low, p - 1, steps);
            quickSortInPlace(a, p + 1, high, steps);
        }
    }

    private static int partition(int[] a, int low, int high, long[] steps) {
        // choose pivot as middle element
        int mid = low + (high - low) / 2;
        int pivot = a[mid];

        // move pivot to end for simplicity
        swap(a, mid, high, steps);

        int store = low;
        for (int i = low; i < high; i++) {
            steps[0]++; // comparison with pivot
            if (a[i] < pivot) {
                swap(a, i, store, steps);
                store++;
            }
        }
        swap(a, store, high, steps); // move pivot to its final place
        return store;
    }

    private static void swap(int[] a, int i, int j, long[] steps) {
        if (i == j) return;
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        steps[0]++; // count swap as step
    }

    // Local test
    public static void main(String[] args) {
        int[] arr = {5,2,9,1,5,6};
        long[] s = new long[1];
        int[] sorted = quickSort(arr, s);
        System.out.println("Quick sorted: ");
        for (int x : sorted) System.out.print(x + " ");
        System.out.println("\nSteps: " + s[0]);
    }
}
