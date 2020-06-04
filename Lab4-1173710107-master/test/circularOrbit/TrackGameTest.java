package circularOrbit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TrackGameTest {

	@Test
	void test() {
	   	 TrackGame test = new TrackGame();
	   	 test.initarraylist();
	   	 assertEquals(0,test.athletes.size());
         ArrayList info = test.readFile("txt/TrackGame1.txt");
         test.setinfoall(info);
         //System.out.println(info);
         test.Analysis();
         test.sortedArrange();
         int tracknum = test.Tracknum;
         test.newTrack();
         assertEquals(++tracknum,test.Tracknum);
         test.clearTrack();
         assertEquals(--tracknum,test.Tracknum);
         //System.out.println(test);
	}

}
