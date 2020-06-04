package circularOrbit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AtomStructureTest {

  @Test
  void test() {
    AtomStructure test = new AtomStructure("txt/AtomicStructure_Medium.txt");
    ArrayList<Integer> a = test.numOnEachTrack();
    System.out.println(test);
    assertEquals(2, (int) a.get(1));
    assertEquals(8, (int) a.get(2));
    test.newTrack();
    test.clearTrack();
    assertEquals(6, test.trackNum);
    test.transitElectron(1, 2);
    test.transitElectron(2, 1);
    assertEquals(6, test.getTrackNumbers());
    //test.toString();
    //return ;
  }


}
