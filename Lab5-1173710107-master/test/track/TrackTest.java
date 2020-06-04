package track;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TrackTest {

  @Test
  void testmethods() {
    Track<String> a = new Track<String>("trackA", 12);
    a.add("obj1");
    a.add("obj2");
    assertTrue(a.contains("obj1"));
    assertFalse(a.contains("obj3"));
    a.remove("obj1");
    assertFalse(a.contains("obj1"));
  }

  @Test
  void testfields() {
    Track<String> a = new Track<String>("trackA", 10);
    Track<String> b = new Track<String>("trackA", 10);
    assertTrue(a.equals(b));
    b.rename("trackB");
    assertFalse(a.equals(b));
  }

  @Test
  void testmain() {
    Track<String> a = new Track<String>("default", 10);
    a.add("thing1");
    a.add("thing2");
    a.add("thing20", 20.22);
    a.add("thing5", 5);
    a.add("thing14", 14);
    a.add("thing100", 100);
    a.add("thing18", 18);
    // System.out.println(a.contains("thing1"));
    // System.out.println(a.contains("thing11"));
    // System.out.println(a);
    assertEquals(true, a.contains("thing1"));
    assertEquals(false, a.contains("thing11"));
    a.remove("thing20");
    // System.out.println(a);
    a.changePos("thing18", 14.0);
    a.changePos("thing14", 15.0);
    // System.out.println(a);
    a.add("thing12", 15.0);
    a.add("thing3");
    a.add("thing55");
    a.add("thing55", 25.0);
  }
}
