import java.util.Arrays;
import java.util.Random;

public class Assignment1 {

    // -----------------------------------------------------------------------------------

    /**
     * ---- ToDo: recursion methods to implement ---------
     *
     */

    /**
     * Assignment 1 Question 6 returns the largest difference between the first and last element of a
     * contiguous ascending array within the given array
     *
     * @param array the array to search in
     * @return the sum
     */

    public int maxAscendingArraySum(int[] array) {
        return maxAscendingArraySum(array, 0, 2147483647, -1);
    }

    public int maxAscendingArraySum(int[] array, int index, int first, int max) {
        if (index >= array.length) {
            return max;
        }
        if (index != array.length - 1 && array[index] <= array[index + 1 ]) {
            if (first > array[index]) {
                first = array[index];
            }
            return maxAscendingArraySum(array, index + 1, first, max);
        }
        if (array[index] - first > max) {
            return maxAscendingArraySum(array, index + 1, 2147483647, array[index] - first);
        }
        return maxAscendingArraySum(array, index + 1, 2147483647, max);
    }
    // -----------------------------------------------------------------------------------

    /**
     * ---- Implemented methods used for benchmarking ---------
     *
     */

    /**
     * Swap two elements in an array
     *
     * @param array the array in question
     * @param index1 one of the elements
     * @param index2 the other element
     */
    private static void swap(int[] array, int index1, int index2) {
        int x = array[index1];
        array[index1] = array[index2];
        array[index2] = x;
    }

    /**
     *  Assignment 1 Question 8 - Benchmarking
     *  Bubble sort, sometimes faster version
     *
     * @param array the array to sort
     */
    public static void bubbleSort(int[] array) {
        boolean swapped = true;
        for (int i = 0; i < array.length && swapped != false; i++) {
            swapped = false;
            for (int j = 1; j < (array.length - i); j++) {
                if (array[j - 1] > array[j]) {
                    swap(array, j, j - 1);
                    swapped = true;
                }
            }
        }
    }

    /**
     *  Assignment 1 Question 8 - Benchmarking
     *  Insertion sort
     *
     * @param array The array to sort
     */

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {
                swap(array, j, j - 1);
            }
        }
    }

// -----------------------------------------------------------------------------------

    /**
     * ---- ToDo: Sorting algorithms for you to implement ---------
     *
     */

    /**
     * Methods to call Quicksort (do not change these)
     *
     * @param array the array to sort
     */
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1, false);
    }

    public static void quickSortMedian(int[] array) {
        quickSort(array, 0, array.length - 1, true);
    }

    /**
     * Assignment 1 Question 7 - Quicksort
     *
     * @param array the array to sort
     */

    private static void quickSort(int[] array, int begin, int end, boolean useMedian) {
        int i = begin;
        int j = end;
        if (array.length==0 || array == null) {
            return;
        }
        int pivot = array[begin];
        if (useMedian) { 
            pivot = array[begin + (end - begin) / 2];
        }

        while (i <= j) { 
            while (array[i] < pivot) { 
                i++;
            }
            while (array[j] > pivot) { 
                j--; r
            }
            if (i <= j) { 
                swap(array, i, j); 
                i++;
                j--;
            }
        }

        if (begin < j) { 
            quickSort(array, begin, j, useMedian);
        }
        if (i < end) { 
            quickSort(array, i, end, useMedian);
        }
    }





    /**
     * Assignment 1 Question 7 - Mergesort
     *
     * @param array the array to sort
     */
    public static void mergeSort(int[] array) {
        int[] tmpArray = new int[array.length];
        mergeSort(array, tmpArray, 0, array.length-1);
    }

    public static void mergeSort(int[] a, int[] tmpArray, int left, int right){
        if( left < right){
            int center = (left+right) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center+1, right);
            merge(a, tmpArray, left, center+1,right);
        }
    }

    public static void merge( int[] a, int[] tmpArray, int leftPos, int rightPos, int rightEnd){
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        while ( leftPos <= leftEnd && rightPos <= rightEnd ){
            if( a[leftPos] <= a[rightPos])
                tmpArray[tmpPos++] = a[leftPos++];
            else
                tmpArray[ tmpPos++ ] = a[ rightPos ++ ];
        }

        while( leftPos <= leftEnd)
            tmpArray[ tmpPos ++ ] = a[ leftPos ++ ];

        while( rightPos <= rightEnd)
            tmpArray[ tmpPos++ ] = a[ rightPos++ ];

        for(int i = 0; i < numElements; i++, rightEnd--)
            a[ rightEnd ] = tmpArray[ rightEnd ];
    }

    public static void printArr(int [] array){
        for (int i = 0; i < array.length; i++){
            System.out.print(array[i]);
            System.out.print(" ");
        }
    }
    // -----------------------------------------------------------------------------------

    /**
     * ---- Main ---------
     *
     */

    public static void main(String[] args) {
        int[] myTestArray = new int[] {1, 2, 3, 2, 4, 10, 11, 20, 2, 4};
        Assignment1 myTest = new Assignment1();

    }

}
