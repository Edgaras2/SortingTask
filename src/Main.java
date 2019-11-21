import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Application which reads from file, determines which sorting method to use by user input, sorts given file,
 * saves to new file if user wants to.
 *
 * @author Edgaras Bajorinas
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Console console = System.console();
        String readLine = "Please enter file path: ";
        String userPath;

        while (!readLine.equalsIgnoreCase("N")) {
            userPath = console.readLine("Please enter file path: ");
            choicesOfSortingAlgorithms(console, userPath);
            readLine = console.readLine("Would you like to try again? Y/N ");
        }
    }

    /**
     * Retrieves user input and prints results to console
     *
     * @param console it is used to get responses from cmd
     * @param path is used to locate file
     * @throws IOException
     *
     * @author Edgaras Bajorinas
     */
    protected static void choicesOfSortingAlgorithms(Console console, String path) throws IOException {
        double[] marks = readFromFile(path);
        if (marks == null) return;

        String readLine = console.readLine("Choose sorting algorithm: \n" +
                "1 Bubble sort\n" +
                "2 Heap sort\n" +
                "3 Merge sort ");
        String result = getChosenSortingMethod(marks, readLine);
        System.out.println(result);
        saveToFile(console, path, marks, result);
    }


    /**
     * Sorts given file data by user input
     *
     * @param marks data from given file, which will be sorted using different method depending on user input
     * @param readLine user input
     * @return which sorting method was chosen
     *
     * @author Edgaras Bajorinas
     */
    private static String getChosenSortingMethod(double[] marks, String readLine) {
        String result = null;
        if (readLine.equalsIgnoreCase("1")) {
            BubbleSort bubbleSort = new BubbleSort();
            long bubbleSortTime = bubbleSort.bubbleStudentSort(marks);
            result = "Bubble sort took time in nano seconds " + bubbleSortTime;
        } else if (readLine.equalsIgnoreCase("2")) {
            HeapSort heapSort = new HeapSort();
            long heapSortTime = heapSort.heapStudentSort(marks);
            result = "Heap sort took time in nano seconds " + heapSortTime;
        } else if (readLine.equalsIgnoreCase("3")) {
            MergeSort ms = new MergeSort();
            long mergeSortTime = ms.sort(marks, 0, marks.length - 1);
            result = "Heap sort took time in nano seconds " + mergeSortTime;
        }
        return result;
    }

    /**
     * Saves result to file if user wants to
     *
     * @param console it is used to get responses from cmd
     * @param path is used to locate file
     * @param marks sorted data from given file
     * @param result results after sorting
     *
     * @author Edgaras Bajorinas
     */
    private static void saveToFile(Console console, String path, double[] marks, String result) throws IOException {
        String readLine;
        readLine = console.readLine("\nWould you like to save file? Y/N");

        if (readLine.equalsIgnoreCase("y")) {
            path = path.substring(0, path.length() - 4) + "Out.txt";
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(path), StandardCharsets.UTF_8))) {
                writer.write("Number of records: " + marks.length + "\n" + result);
            }
            System.out.println("File create successfully it's path is " + path);
        }
    }

    /**
     * Reads data from file
     *
     * @param path is used to locate file
     * @return reads data from file, constructs to new array and takes just student marks
     *
     * @author Edgaras Bajorinas
     */
    private static double[] readFromFile(String path) {
        File file = new File(path);
        List<String> studentList = new ArrayList<>();

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                studentList.add(st);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Invalid path");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] arr = studentList.toArray(new String[0]);
        double[] marks = new double[arr.length];

        for (int i = 0; i < arr.length; i++) {
            marks[i] = Double.valueOf(arr[i].substring(arr[i].indexOf(",") + 1, arr[i].length()));
        }
        return marks;
    }
}