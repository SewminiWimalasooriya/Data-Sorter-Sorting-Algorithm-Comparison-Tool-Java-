// Member2_Merge.java
// Member 2: Merge Sort with step counting

public class Member2_Merge {

    /**
     * Public API: merge sort that returns sorted array and sets stepsOut[0]
     */
    public static int[] mergeSort(int[] arr, long[] stepsOut) {
        long[] steps = new long[1];
        int[] sorted = mergeSortRecursive(arr.clone(), steps);
        stepsOut[0] = steps[0];
        return sorted;
    }

    // recursive merge sort that accumulates steps in steps[0]
    private static int[] mergeSortRecursive(int[] a, long[] steps) {
        if (a.length <= 1) return a;

        int mid = a.length / 2;
        int[] left = new int[mid];
        int[] right = new int[a.length - mid];
        System.arraycopy(a, 0, left, 0, mid);
        System.arraycopy(a, mid, right, 0, right.length);

        int[] sLeft = mergeSortRecursive(left, steps);
        int[] sRight = mergeSortRecursive(right, steps);

        return merge(sLeft, sRight, steps);
    }

    private static int[] merge(int[] left, int[] right, long[] steps) {
        int i = 0, j = 0, k = 0;
        int[] merged = new int[left.length + right.length];

        while (i < left.length && j < right.length) {
            steps[0]++; // comparison
            if (left[i] <= right[j]) {
                merged[k++] = left[i++];
                steps[0]++; // append counted as step
            } else {
                merged[k++] = right[j++];
                steps[0]++; // append counted as step
            }
        }

        while (i < left.length) {
            merged[k++] = left[i++];
            steps[0]++; // append remaining
        }
        while (j < right.length) {
            merged[k++] = right[j++];
            steps[0]++; // append remaining
        }
        return merged;
    }

    // Local test
    public static void main(String[] args) {
        int[] arr = {5,2,9,1,5,6};
        long[] s = new long[1];
        int[] sorted = mergeSort(arr, s);
        System.out.println("Merge sorted: ");
        for (int x : sorted) System.out.print(x + " ");
        System.out.println("\nSteps: " + s[0]);
    }
}
