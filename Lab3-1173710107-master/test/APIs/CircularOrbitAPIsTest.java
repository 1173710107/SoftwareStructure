package APIs;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class CircularOrbitAPIsTest {

	@Test
	void test() {
		ArrayList<Integer> test = new ArrayList<Integer>();
		test.add(0);
		test.add(0);
		test.add(3);
		assertEquals(0,CircularOrbitAPIs.getObjectDistributionEntropy(test));
	}

}
