package coverage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import whitebox.Coverage;

public class CoverageTests {

	@Test
	void testOneTimesZeroIsZero() {
		int result = Coverage.russianMultiplication(1, 0);
		assertEquals(result, 0);
	}
	
	@Test
	void testTwoTimesZeroIsZero() {
		int result = Coverage.russianMultiplication(2, 0);
		assertEquals(result, 0);
	}
	
	@Test
	void testComplexityCoverage() {
		// MC/DC coverage
		Coverage.russianMultiplication(2, 0);
		Coverage.russianMultiplication(1, 0);
		Coverage.russianMultiplication(0, 0);
	}
	
	@Test
	void testConditionCoverageConditionDecision() {
		Coverage.conditionDecision(2, 0);
		Coverage.conditionDecision(4, 1);
	}
	
	@Test
	void testDecisionCoverageConditionDecision() {
		Coverage.conditionDecision(1, 0);
		Coverage.conditionDecision(4, 0);
		Coverage.conditionDecision(1, 1);
		Coverage.conditionDecision(4, 1);
	}
	
	@Test
	void testDecisionCoverageConditionDecisionMore() {
		Coverage.conditionDecisionMore(1, 0);
		Coverage.conditionDecisionMore(4, 0);
		Coverage.conditionDecisionMore(4, 1);
	}
}
