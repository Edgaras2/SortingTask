public class BubbleSort {

    public long bubbleStudentSort(double[] studentMarks) {
        long starTime = System.nanoTime();
        int n = studentMarks.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (studentMarks[j] < studentMarks[j + 1]) {
                    double tempMark = studentMarks[j];
                    studentMarks[j] = studentMarks[j + 1];
                    studentMarks[j + 1] = tempMark;
                }
            }
        }
        return System.nanoTime() - starTime;
    }
}
