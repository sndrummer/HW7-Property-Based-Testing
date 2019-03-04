package misc;

public class Misc {

	private Misc() {
		// Do nothing
	}

	private static final Object[] EMPTY_ARRAY = {};
	
	public static Object[] reverse(Object[] a) {
		if (a == null) {
			return EMPTY_ARRAY.clone();
		}
		
		Object[] b = new Object[a.length];
		for (int i = 0; i < a.length; i++) {
			b[i] = a[a.length - 1 - i];
		}
		
		return b;
	}

	public static void vectorAdd(Float[] a, Float[] b) {
		for (int i = 0; i < a.length; i++) {
			a[i] += b[i];
		}
	}

	/**
	 * Adds an element to an array if it is not already in the array.
	 * 
	 * @cs329.requires x and arr are non-null and there is an element in arr that is null
	 * @cs329.ensures when f returns to the caller there is an element in arr that is equal to x
	 * 
	 * @param x element to add
	 * @param arr array to which x is added
	 */
	public static void f(Object x, Object[] arr) {
		int i;
		boolean exists = false;

		for (i = 0; i < arr.length; i++) { // A1
			if (x.equals(arr[i])) { // B1
				exists = true;
				break;
			} // B2
		} // A2
		if (!exists) // C1
		{
			for (i = 0;; i++) { // D1
				if (arr[i] == null) { // E1
					arr[i] = x;
					break;
				} // E2
			} // D2
		} // C2
	}

	public static void dnfsort(int[] arr) {
		int a = 0;
		int b = 0;
		int c = arr.length;

		while (b < c) {
			if (arr[b] == 1) {
				arr[b] = arr[a];
				arr[a] = 1;
				a = a + 1;
				b = b + 1;
			} else {
				if (arr[b] == 2) {
					b = b + 1;
				} else // arr[b] should be 3 here
				{
					c = c - 1;
					arr[b] = arr[c];
					arr[c] = 3;
				}
			}
		}
	}

	public static int factorial(int n) {
		int r;
		int i;

		if (n < 1) {
			throw new IllegalArgumentException("ERROR: n = " + n + " must be greater than 0");
		} // 1

		r = 1; // 2
		i = 2; // 3
		while (i <= n) {
			r = r * i; // 5
			i = i + 1; // 6
		} // 4
		return r; // 7
	}

	public static int fib(int n) {
		/* Declare an array to store Fibonacci numbers. */
		int f[] = new int[n + 2]; // 1 extra to handle case, n = 0
		int i;

		/* 0th and 1st number of the series are 0 and 1 */
		f[0] = 0;
		f[1] = 1;

		for (i = 2; i <= n; i++) {
			/*
			 * Add the previous 2 numbers in the series and store it
			 */
			f[i] = f[i - 1] + f[i - 2];
		}

		return f[n];
	}

}
