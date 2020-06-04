package circularOrbit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import physicalObject.Account;

class SocialNetworkCircleTest {

  @Test
  void test() {
    SocialNetworkCircle test = new SocialNetworkCircle("txt/SocialNetworkCircle1.txt");
    System.out.println(test);
    assertEquals(1, test.onWhichTrack("TomWong"));
    assertEquals(2, test.onWhichTrack("FrankLee"));
    test.newTrack();
    test.clearTrack();
    Account x = test.getAccount("TomWong");
    assertEquals(1, test.onWhichTrack(x));
    test.refresh();
    int dis = test.getLogicalDistance("TomWong", "FrankLee");
    assertEquals(1, dis);
  }

}
