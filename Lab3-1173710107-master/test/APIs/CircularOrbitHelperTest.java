package APIs;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import circularOrbit.SocialNetworkCircle;

class CircularOrbitHelperTest {

	@Test
	void test() {
		ArrayList<Integer> a = new ArrayList<Integer>(); 
		a.add(9);
		a.add(7);
		a.add(11);
		a.add(12);
		a.add(18);
		CircularOrbitHelper.visualize(a);
		CircularOrbitHelper.visualize(new SocialNetworkCircle("txt/SocialNetworkCircle.txt"));
	}

}
