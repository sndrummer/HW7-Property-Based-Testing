package pbt;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        int[] left = sort(array, from, m);
        int[] right = sort(array, m + 1, to);

        int[] result = new int[left.length + right.length];
        int li = 0;
        int ri = 0;
        for (int i = 0; i < result.length; i++) {
            if (li < left.length && (ri >= right.length || left[li] < right[ri])) {
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
     * Generates a random array with values between MIN_ARRAY_VAL and MAX_ARRAY_VAL
     * and a random size from 0 to MAX_ARRAY_SIZE
     *
     * @return random array of ints with
     */
    private static int[] generateRandomArray() {
        int arraySize = getRandomInt(1, MAX_ARRAY_SIZE);
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


    /**
     * Test the post conditions hold:
     * a sorted permutation of array, rv, is returned
     * rv.length == array.length
     * /\ \forall i, 0 <= i < rv.length IMPLIES (i > 0 IMPLIES rv[i-1] <= r[i])) (is sorted)
     * /\ count(rv[i], rv) == count(rv[i], array) (is a permutation)
     *
     * @param array array of randomly generated ints
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("generateArrayArgs")
    void testPostCondition(int[] array) {
        int[] sortedArray = sort(array);

        logger.debug("Original array {}", array);
        logger.debug("Sorted array {}", sortedArray);

        assertEquals(array.length, sortedArray.length);

        int min = Integer.MIN_VALUE;
        for (int i = 0; i < sortedArray.length; i++) {
            assertTrue(min <= sortedArray[i]);
            min = sortedArray[i];
            assertEquals(count(sortedArray[i], array), count(sortedArray[i], sortedArray));
        }
    }

    /**
     * Tests that no exceptions are thrown when calling search given random input that satisfies the
     * pre-condition
     *
     * @param array a random int array that is generated at runtime to be tested with search
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("generateArrayArgs")
    void testRuntimeExceptions(int[] array) {
        assertDoesNotThrow(() -> {
            sort(array);
        });
    }


    /**
     * Tests the inverse property, in this case sort a random array and then reverse it and sort again
     * and make sure the results are equal.
     *
     * @param array a random int array that is generated at runtime to be tested with search
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("generateArrayArgs")
    void testInverseProperty(int[] array) {
        int[] sortedArr = sort(array);
        int[] reversedArr = reverse(sortedArr);
        int[] sortedAfterReverseArr = reverse(reversedArr);

        assertArrayEquals(sortedArr, sortedAfterReverseArr);
    }

    private int[] reverse(int[] array) {
        int[] reversed = new int[array.length];
        int j = array.length -1;
        for (int i = 0; i < reversed.length; i++, j--) {
            reversed[i] = array[j];
        }
        return reversed;
    }



    /**
     * Test pointwise equivalent by comparing the mergeSort with java utils Arrays.sort method
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("generateArrayArgs")
    void testPointWiseEquivalence(int[] array) {
        //Assert equivalence of Arrays.sort
        int[] sorted = sort(array);
        int[] copyOfArray = Arrays.copyOf(array, array.length);
        Arrays.sort(copyOfArray);

        assertArrayEquals(copyOfArray, sorted);
    }



}