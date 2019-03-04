package whitebox;

public class Coverage {

	private Coverage() {
	}

	public static int russianMultiplication(int a, int b) {
		int z = 0;
		while (a != 0) {
			if (a % 2 != 0) {
				z = z + b;
			}
			a = a / 2;
			b = b * 2;
		}
		return z;
	}

	public static int conditionDecision(int a, int x) {
		if ((3 > a) || (x != 0)) {
			return 1;
		} else {
			return 0;
		}
	}

	public static int conditionDecisionMore(int a, int x) {
		boolean b = x != 0;
		if ((3 > a) || b) {
			return 1;
		} else {
			return 0;
		}

	}

}
