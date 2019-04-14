package pbt;

import org.junit.jupiter.params.provider.Arguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class MergeSortTest {

    private static Logger logger = LoggerFactory.getLogger(BinarySearchTest.class);

    public static Random rand = new Random();

    private static final int MAX_ARRAY_SIZE = 15;
    private static final int MIN_ARRAY_VAL = -10;
    private static final int MAX_ARRAY_VAL = 10;

    private static final int MIN_TEST_VAL = -10;
    private static final int MAX_TEST_VAL = 10;
    private static final int AMT_TESTS = 10;


    private static int getRandomInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;

    }

    /**
     * cs329.requires: array is non-null
     * cs329.ensures: a sorted permutation of array, rv, is returned
     * rv.length == array.length
     * /\ \forall i, 0 <= i < rv.length IMPLIES (i > 0 IMPLIES rv[i-1] <= r[i])) (is sorted)
     * /\ count(rv[i], rv) == count(rv[i], array) (is a permutation)
     *
     * @param array
     * @return a sorted version of array
     **/
    public static int[] sort(int[] array) {
        return sort(array, 0, array.length - 1);
    }

    public static int[] sort(int[] array, int from, int to) {
        if (from >= to) {
            return new int[]{array[from]};
        }

        int m = (from + to) / 2;
        int[] left = sort(array, from, m - 1);
        int[] right = sort(array, m + 1, to);

        int[] result = new int[left.length + right.length];
        int li = 0;
        int ri = 0;
        for (int i = 0; i < result.length; i++) {
            if (li < left.length
                    && (ri >= right.length || left[li] < right[ri])) {
                result[i] = left[li];
                li++;
            } else {
                result[i] = right[ri];
                ri++;
            }
        }

        return result;
    }

    /**
     * The number of time x is in arr
     *
     * @param x   a value
     * @param arr an array
     * @return count of x in arr
     */
    static int count(int x, int[] arr) {
        int c = 0;
        for (int i : arr) {
            if (x == i) {
                ++c;
            }
        }
        return c;
    }


    /**
     * Generates a random sorted array with values between MIN_ARRAY_VAL and MAX_ARRAY_VAL
     * and a random size from 0 to MAX_ARRAY_SIZE
     *
     * @return random array of ints with
     */
    private static int[] generateRandomArray() {
        int arraySize = getRandomInt(0, MAX_ARRAY_SIZE);
        int[] arr = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            arr[i] = getRandomInt(MIN_ARRAY_VAL, MAX_ARRAY_VAL);
        }

        return arr;
    }

    /**
     * Generates the random arguments to be tested for the binary search tests
     *
     * @return arguments for binary search
     */
    private static Stream<Arguments> generateArrayArgs() {
        ArrayList<Arguments> args = new ArrayList<>();

        for (int i = 0; i < AMT_TESTS; i++) {
            args.add(Arguments.of(generateRandomArray()));
        }

        return args.stream();
    }



}