package circularOrbit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SocialNetworkCircleTest {

	@Test
	void test() {
		SocialNetworkCircle test= new SocialNetworkCircle("txt/SocialNetworkCircle.txt");
		System.out.println(test);
		assertEquals(1,test.onWhichTrack("TomWong"));
		assertEquals(2,test.onWhichTrack("FrankLee"));
	}

}
