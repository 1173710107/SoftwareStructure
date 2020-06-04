package circularOrbit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

class ConcreteCircularOrbitTest {

	@Test
	void test() {
		ConcreteCircularOrbit<String,String> test= new ConcreteCircularOrbit<String,String>();
		test.addCenter("central");
		test.addCenter("star");
		test.addTrack("track0", 0.5);
		test.addTrack("track1", 1);
		test.addTrack("track2", 2);
		test.addTrack("track3",3);
		test.addObject(0, "something on 0",0);
		test.addObject(0,"something on 90",90);
		test.addObject(1, "another thing on 90",90);
		test.addObject(2,"something on 1",1);
		test.addRelation("track1", "track2");
		test.addRelation("track2","track3");
		test.addRelation("track3","track4");
		test.addRelation("track2","track4");
		assertEquals(2,test.getLogicalDistance("track1", "track4"));
		assertEquals(1.118,test.getPhysicalDistance("something on 0", "another thing on 90"),0.1);
		System.out.println(test.numOnEachTrack());
	   System.out.println(test);
	   System.out.println(test.allElements());
	   Iterator<String> ite = test.iterator();
	   test.transit("track2", 1);
	   test.addObject(2, "num");
	   test.move("track3", 1);
		assertEquals("something on 0",ite.next());
	}

}
