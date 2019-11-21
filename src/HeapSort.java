public class HeapSort {

    public long heapStudentSort(double[] studentMarks) {
        long starTime = System.nanoTime();
        int n = studentMarks.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapSort(studentMarks, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            double temp = studentMarks[0];
            studentMarks[0] = studentMarks[i];
            studentMarks[i] = temp;
            heapSort(studentMarks, i, 0);
        }
        return System.nanoTime() - starTime;
    }

    private void heapSort(double arr[], int n, int i) {
        int smallest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] < arr[smallest]) {
            smallest = l;
        }

        if (r < n && arr[r] < arr[smallest]) {
            smallest = r;
        }

        if (smallest != i) {
            double swap = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = swap;

            heapSort(arr, n, smallest);
        }
    }
}
