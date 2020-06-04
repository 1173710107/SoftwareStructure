package track;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TrackTest {

	@Test
	void testmethods() {
		Track<String> a = new Track<String>("trackA",12);
		a.add("obj1");
		a.add("obj2");
		assertTrue(a.contains("obj1"));
		assertFalse(a.contains("obj3"));
		a.remove("obj1");
		assertFalse(a.contains("obj1"));
	}
    void testfields() {
    	Track<String> a = new Track<String>("trackA",10);
    	Track<String> b = new Track<String>("trackA",10);
    	assertTrue(a.equals(b));
    	b.rename("trackB");
    	assertFalse(a.equals(b));
    }
}
