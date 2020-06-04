package debug;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FindMedianSortedArraysTest {
  /*
   * A.length>B.length A.length<B.length A.length=B.length (A.length+B.length)%2=0
   * (A.length+B.length)%2=1
   */
  @Test
  public void test1() {
    FindMedianSortedArrays med = new FindMedianSortedArrays();
    int [] num1 = {1, 3};
    int [] num2 = {2};
    assertEquals(2.0, med.findMedianSortedArrays(num1, num2), 1e-1);
  }

  @Test
  public void test2() {
    FindMedianSortedArrays med = new FindMedianSortedArrays();
    int [] num3 = {1, 2};
    int [] num4 = {3, 4};
    assertEquals(2.5, med.findMedianSortedArrays(num3, num4), 1e-1);
  }

  @Test
  public void test3() {
    FindMedianSortedArrays med = new FindMedianSortedArrays();
    int [] num5 = {1, 1, 1};
    int [] num6 = {5, 6, 7};
    assertEquals(3.0, med.findMedianSortedArrays(num5, num6), 1e-1);
  }

  @Test
  public void test4() {
    FindMedianSortedArrays med = new FindMedianSortedArrays();
    int [] num7 = {1, 1};
    int [] num8 = {1, 2, 3};
    assertEquals(1.0, med.findMedianSortedArrays(num7, num8), 1e-1);
  }

}
