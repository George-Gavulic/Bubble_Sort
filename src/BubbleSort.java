import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class BubbleSort {

    public static int[] createRandomArray(int arrayLength) {
        Random rand = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = rand.nextInt(101);
        }
        return array;
    }

    public static void writeArrayToFile(int[] array, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int value : array) {
                writer.write(value + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] readFileToArray(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.lines().mapToInt(Integer::parseInt).toArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new int[0];
        }
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the length of the array: ");
        int length = scanner.nextInt();

        int[] randomArray = createRandomArray(length);
        System.out.println("Generated Array: ");
        for (int value : randomArray) {
            System.out.print(value + " ");
        }
        System.out.println();

        System.out.print("Enter a filename (or press Enter to skip): ");
        scanner.nextLine();  // Clear the newline
        String filename = scanner.nextLine();
        
        if (!filename.isEmpty()) {
            writeArrayToFile(randomArray, filename);
            System.out.println("Array written to " + filename);
            int[] readArray = readFileToArray(filename);
            System.out.println("Array read from file: ");
            for (int value : readArray) {
                System.out.print(value + " ");
            }
            System.out.println();
            bubbleSort(readArray);
            System.out.println("Sorted Array: ");
            for (int value : readArray) {
                System.out.print(value + " ");
            }
            System.out.println();
        } else {
            System.out.println("Filename not provided. Skipping file operations.");
        }

        scanner.close();
    }
}
