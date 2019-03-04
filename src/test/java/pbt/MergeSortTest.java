package pbt;
import java.util.Arrays;
import java.util.Random;

public class MergeSortTest {

  public static Random rand = new Random();

  /**
   * cs329.requires: array is non-null
   * cs329.ensures: a sorted permutation of array, rv, is returned
   *       rv.length == array.length 
   *    /\ \forall i, 0 <= i < rv.length IMPLIES (i > 0 IMPLIES rv[i-1] <= r[i])) (is sorted)
   *          /\ count(rv[i], rv) == count(rv[i], array) (is a permutation) 
   *          
   *  @param array
   *  @return a sorted version of array
   *
   **/
  public static int[] sort(int[] array) {
    return sort(array, 0, array.length - 1);
  }

  public static int[] sort(int[] array, int from, int to) {
    if (from >= to) {
      return new int[] { array[from] };
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
   * @param x a value
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

}