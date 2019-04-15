package hashtable;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test suite for the hash table.
 */
public class LLHashTableTest {

    private static Logger logger = LoggerFactory.getLogger(LLHashTableTest.class);
    private static Random rand = new Random();
    private static final int AMT_TESTS = 10;
    private static final int MIN_TEST_VAL = -10;
    private static final int MAX_TEST_VAL = 10;

    private LLHashTable hashTable1;
    private LLHashTable hashTable2;

    private static final int HASH_BUCKET_MAX = 25;


    /**
     * Returns a random integer given a min int and max int
     *
     * @param min int
     * @param max int
     * @return random int
     */
    public static int getRandomInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    /**
     * Generates a random array with values between MIN_ARRAY_VAL and MAX_ARRAY_VAL
     * and a random size from 0 to MAX_ARRAY_SIZE
     *
     * @return random array of ints with
     */
    public static int[] generateRandomArray(int minArraySize, int maxArraySize, int minArrayVal,
                                            int maxArrayVal) {
        int arraySize = getRandomInt(minArraySize, maxArraySize);
        int[] arr = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            arr[i] = getRandomInt(minArrayVal, maxArrayVal);
        }

        return arr;
    }


    @BeforeEach
    public void setUp() throws Exception {
        logger.info("@BeforeEach: setUp()");
        hashTable1 = new LLHashTable(HASH_BUCKET_MAX);
        hashTable2 = new LLHashTable(HASH_BUCKET_MAX);
    }

    @AfterEach
    public void tearDown() throws Exception {
        logger.info("@AfterEach: tearDown()");
        hashTable1 = null;
        hashTable2 = null;
    }


    /**
     * Test Stateful equivalence when changing the order of the key value pairs that are 'put'
     * in the hashTable. put(Ka,Va), put(Kb,Vb) <==> put(Kb,Vb), put(Ka, Va) iff Ka != Kb
     *
     * @param prefixKeys   array of randomly generated ints
     * @param prefixValues array of randomly generated ints
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("generateArrayArgs")
    void testStatefulEquivalencePutOrder(int[] prefixKeys, int[] prefixValues, int[] suffix) {

        assertEquals(prefixKeys.length, prefixValues.length);

        //prefix
        applyPrefix(prefixKeys, prefixValues);

        //key value pairs
        int keyA = 55555;
        int valA = 11111;

        int keyB = 77777;
        int valB = 22222;

        //Test put with different orders
        hashTable1.put(keyA, valA);
        hashTable1.put(keyB, valB);

        hashTable2.put(keyB, valB);
        hashTable2.put(keyA, valA);

        //suffix
        applySuffix(prefixKeys, prefixValues, suffix);

        //Assert objects are the same
        assertEquals(hashTable1, hashTable2);

        //Assert that the hashtables are equivalent
        assertEquals(hashTable1.toString(), hashTable2.toString());
    }


    /**
     * Test Stateful equivalence when changing the order of the key value pairs that are 'put'
     * in the hashTable. put(Ka,Va), put(Kb,Vb) <==> put(Kb,Vb), put(Ka, Va) iff Ka != Kb
     *
     * @param prefixKeys   array of randomly generated ints
     * @param prefixValues array of randomly generated ints
     */
    @ParameterizedTest(name = "run #{index} with [{arguments}]")
    @MethodSource("generateArrayArgs")
    void testStatefulEquivalencePutThenRemoveOrder(int[] prefixKeys, int[] prefixValues, int[] suffix) {

        assertEquals(prefixKeys.length, prefixValues.length);

        //prefix
        applyPrefix(prefixKeys, prefixValues);

        //key value pairs
        int keyA = 55555;
        int valA = 11111;

        //Test put with different orders
        int randomIndexToRemove = getRandomInt(0, prefixKeys.length - 1);

        //Test put with different orders
        int keyToRemove = prefixKeys[randomIndexToRemove];

        hashTable1.remove(keyToRemove);
        hashTable1.put(keyA, valA);


        hashTable2.put(keyA, valA);
        hashTable2.remove(keyToRemove);

        //suffix
        applySuffix(prefixKeys, prefixValues, suffix);

        //Assert objects are the same
        assertEquals(hashTable1, hashTable2);

        //Assert that the hashtables are equivalent
        assertEquals(hashTable1.toString(), hashTable2.toString());
    }



    private void applyPrefix(int[] prefixKeys, int[] prefixValues) {
        for (int i = 0; i < prefixKeys.length; i++) {
            hashTable1.put(prefixKeys[i], prefixValues[i]);
            hashTable2.put(prefixKeys[i], prefixValues[i]);
        }
    }

    private void applySuffix(int[] prefixKeys, int[] prefixValues, int[] suffix) {
        for (int i = 0; i < prefixKeys.length; i++) {
            if (suffix[i] % 2 == 0) {
                hashTable1.remove(prefixKeys[i]);
                hashTable2.remove(prefixKeys[i]);
            }
        }
    }

    /**
     * Generates the random arguments to be tested for the binary search tests
     *
     * @return arguments for binary search
     */
    private static Stream<Arguments> generateArrayArgs() {
        ArrayList<Arguments> args = new ArrayList<>();


        //List<Integer> prefixKeys = new ArrayList<Integer>();
        //List<Integer> prefixValues = new ArrayList<Integer>();

        for (int i = 0; i < AMT_TESTS; i++) {
            int randomSize = getRandomInt(1, HASH_BUCKET_MAX);
            int[] prefixKeys = new int[randomSize];
            int[] prefixValues = new int[randomSize];

            int[] suffix = new int[randomSize];
            for (int j = 0; j < randomSize; j++) {
                prefixKeys[j] = getRandomInt(MIN_TEST_VAL, MAX_TEST_VAL);
                prefixValues[j] = getRandomInt(MIN_TEST_VAL, MAX_TEST_VAL);

                suffix[j] = getRandomInt(MIN_TEST_VAL, MAX_TEST_VAL);

            }
            args.add(Arguments.of(prefixKeys, prefixValues, suffix));
        }


        return args.stream();
    }


}
