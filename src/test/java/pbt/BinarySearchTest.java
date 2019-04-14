package pbt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Uses parametric tests to test the different properties of the Binary Search method
 */
public class BinarySearchTest {
    private static Logger logger = LoggerFactory.getLogger(BinarySearchTest.class);

    private static Random rand = new Random();

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
     * cs329.requires:
     * array is non null.
     * array is sorted by increasing value:
     * \forall i, 0 < i < array.length - 1 implies array[i] < array[i+1]
     * cs329.ensures:
     * true if an only if \exists i, 0 <= i < array.length /\ value == array[i]
     * <p>
     * Please see changes made to search method in HW-7-Report.md
     **/
    public static boolean search(int[] array, int value) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int index = (right + left) / 2;
            if (array[index] == value)
                return true;
            if (array[index] < value) {
                left = index + 1;
            } else
                right = index - 1;
        }
        return false;
    }

    /**
     * Generates a random sorted array with values between MIN_ARRAY_VAL and MAX_ARRAY_VAL
     * and a random size from 0 to MAX_ARRAY_SIZE
     *
     * @return random array of ints with
     */
    private static int[] generateRandomSortedArray() {
        int arraySize = getRandomInt(0, MAX_ARRAY_SIZE);
        int[] arr = new int[arraySize];

        List<Integer> arrayValues = new ArrayList<>();

        for (int i = 0; i < arraySize; i++) {
            int arrayVal = getRandomInt(MIN_ARRAY_VAL, MAX_ARRAY_VAL);
            if (!arrayValues.contains(arrayVal)) {
                arr[i] = arrayVal;
                arrayValues.add(arrayVal);
            } else --i;

        }

        Arrays.sort(arr);

        return arr;
    }

    /**
     * Get a random int value for generating the arguments to be tested for the binary search tests
     *
     * @return random int value
     */
    private static int getRandomVal() {
        return getRandomInt(MIN_TEST_VAL, MAX_TEST_VAL);
    }

    /**
     * Generates the random arguments to be tested for the binary search tests
     *
     * @return arguments for binary search
     */
    private static Stream<Arguments> generateArrayArgs() {
        ArrayList<Arguments> args = new ArrayList<>();

        for (int i = 0; i < AMT_TESTS; i++) {
            args.add(Arguments.of(generateRandomSortedArray(), getRandomVal()));
        }

        return args.stream();
    }

    /**
     * Test the post condition that binary search method only returns true if an only if
     * "\exists i, 0 <= i < array.length /\ value == array[i]"
     *
     * @param array randomly generated sorted array
     * @param value randomly generated value within a range
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("generateArrayArgs")
    void testPostCondition(int[] array, int value) {
        boolean exists = false;
        for (int i = 0; i < array.length; i++) {
            if (value == array[i]) {
                exists = true;
            }
        }
        assertEquals(exists, search(array, value));

        List<Integer> intList = new ArrayList<Integer>(array.length);
        for (int i : array) {
            intList.add(i);
        }

        boolean postConditionHolds = intList.contains(value);
        assertEquals(postConditionHolds, search(array, value));
    }


    /**
     * Slower equivalent of the binary search, is just a normal O(n) search
     * Used to compare
     *
     * @param array array of ints
     * @param value int value
     * @return boolean
     */
    private boolean slowSearch(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value)
                return true;
        }
        return false;
    }


    /**
     * Test runtime exceptions
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("generateArrayArgs")
    void testPointWiseEquivalence(int[] array, int value) {
        //Assert equivalence with slowSearch
        assertEquals(slowSearch(array, value), search(array, value));
    }

    /**
     * Tests that no exceptions are thrown when calling search given random input that satisfies the
     * pre-condition
     *
     * @param array a random int array that is generated at runtime to be tested with search
     * @param value an int value that is generated at runtime to be tested with search
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("generateArrayArgs")
    void testRuntimeExceptions(int[] array, int value) {
        assertDoesNotThrow(() -> {
            search(array, value);
        });
    }


    /**
     * Tests the inverse property, so if an array contains that value then removing it and running
     * the test again should be the opposite of the the original result, while if an array does
     * not contain a value then adding the value should produce the opposite result.
     *
     * @param array a random int array that is generated at runtime to be tested with search
     * @param value an int value that is generated at runtime to be tested with search
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("generateArrayArgs")
    void testInverseProperty(int[] array, int value) {
        boolean found = search(array, value);

        boolean actual;
        boolean oppositeOfActual;

        actual = search(array, value);

        if (found) {
            //delete value
            int[] newArr = new int[array.length - 1];
            for (int i = 0; i < newArr.length; i++) {
                if (array[i] == value) {
                    newArr[i] = array[i + 1];
                }
                else newArr[i] = array[i];
            }
            logger.debug("Deleted {}.. Array: {} vs newArray: {}", value, array, newArr);
            oppositeOfActual = search(newArr, value);
        } else {
            //add value
            int[] newArr = Arrays.copyOf(array, array.length + 1);
            newArr[array.length] = value;
            Arrays.sort(newArr);
            logger.debug("Added {}.. Array: {} vs newArray: {}", value, array, newArr);
            oppositeOfActual = search(newArr, value);
        }

        assertNotEquals(oppositeOfActual, actual);
    }


    //TODO delete example

    public static void streamExample() {
        // create a list of integers
        List<Integer> number = Arrays.asList(2, 3, 4, 5);

        // demonstration of map method
        List<Integer> square = number.stream().map(x -> x * x).
                collect(Collectors.toList());

        System.out.println(square);

        // create a list of String
        List<String> names =
                Arrays.asList("Reflection", "Collection", "Stream");

        // demonstration of filter method
        List<String> result = names.stream().filter(s -> s.startsWith("S")).
                collect(Collectors.toList());
        System.out.println(result);

        // demonstration of sorted method
        List<String> show =
                names.stream().sorted().collect(Collectors.toList());
        System.out.println(show);

        // create a list of integers
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5, 2);

        // collect method returns a set
        Set<Integer> squareSet =
                numbers.stream().map(x -> x * x).collect(Collectors.toSet());
        System.out.println(squareSet);

        // demonstration of forEach method
        number.stream().map(x -> x * x).forEach(y -> System.out.println(y));

        // demonstration of reduce method
        int even =
                number.stream().filter(x -> x % 2 == 0).reduce(0, (ans, i) -> ans + i);

        System.out.println(even);
    }


}

