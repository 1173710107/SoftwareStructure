package debug;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import java.util.List;

import org.junit.Test;

public class RemoveCommentsTest {
  /**
   * 注释在一行内 注释不在一行内 注释前后有代码.
   */
  @Test
  public void test1() {
    String [] source = {"/*Test program */", "int main()", "{ ", "  // variable declaration ",
        "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */",
        "a = b + c;", "}"};
    List<String> result = new ArrayList<>();
    result.add("int main()");
    result.add("{ ");
    result.add("  ");
    result.add("int a, b, c;");
    result.add("a = b + c;");
    result.add("}");
    RemoveComments remove = new RemoveComments();
    assertEquals(result, remove.removeComments(source));
  }

  @Test
  public void test2() {
    RemoveComments remove = new RemoveComments();
    String [] source = {"a/*comment", "line", "more_comment*/b"};
    List<String> result = new ArrayList<>();
    result.add("ab");
    assertEquals(result, remove.removeComments(source));
  }

}
