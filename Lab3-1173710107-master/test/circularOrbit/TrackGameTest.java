package circularOrbit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TrackGameTest {

	@Test
	void test() {
	   	 TrackGame test = new TrackGame();
         String info = test.readFile("txt/TrackGame.txt");
         System.out.println(info);
         test.Analysis(info);
         test.sortedArrange();
         System.out.println(test);
	}

}
