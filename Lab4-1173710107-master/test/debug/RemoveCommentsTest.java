package debug;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class RemoveCommentsTest {
	/**
	 * ע����һ����
	 * ע�Ͳ���һ����
	 * ע��ǰ���д���
	 */
	@Test
	public void test1() {
		RemoveComments remove = new RemoveComments();
		String source[] = {"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"};
		List<String> result = new ArrayList<>();
		result.add("int main()");
		result.add("{ ");
		result.add("  ");
		result.add("int a, b, c;");
		result.add("a = b + c;");
		result.add("}");
		assertEquals(result, remove.removeComments(source));
	}
	@Test
	public void test2() {
		RemoveComments remove = new RemoveComments();
		String source[] = {"a/*comment", "line", "more_comment*/b"};
		List<String> result = new ArrayList<>();
		result.add("ab");
		assertEquals(result, remove.removeComments(source));
	}

}
