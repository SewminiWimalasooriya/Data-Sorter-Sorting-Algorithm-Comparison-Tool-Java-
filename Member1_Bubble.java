// Member1_Bubble.java
// Member 1: Bubble Sort with step counting

public class Member1_Bubble {

    /**
     * Performs bubble sort on a copy of the input array.
     * @param arr input array
     * @param stepsOut long[1] used to return step count (comparisons + swaps)
     * @return sorted array (new int[])
     */
    public static int[] bubbleSort(int[] arr, long[] stepsOut) {
        int[] a = arr.clone();
        int n = a.length;
        long steps = 0L;

        for (int i = 0; i < n; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                steps++; // comparison
                if (a[j] > a[j + 1]) {
                    // swap
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    steps++; // count swap as a step
                    swapped = true;
                }
            }
            if (!swapped) break;
        }

        stepsOut[0] = steps;
        return a;
    }

    // Quick local test if run directly (optional)
    public static void main(String[] args) {
        int[] arr = {5,2,9,1,5,6};
        long[] s = new long[1];
        int[] sorted = bubbleSort(arr, s);
        System.out.println("Bubble sorted: ");
        for (int x : sorted) System.out.print(x + " ");
        System.out.println("\nSteps: " + s[0]);
    }
}
