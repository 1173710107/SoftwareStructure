package debug;

import static org.junit.Assert.*;

import org.junit.Test;

public class TopVotedCandidateTest {
	/**
	 * t��ȡֵ
	 * TopVotedCandidate���䣬��ߵ�Ʊ���仯
	 * TopVotedCandidate���䣬��ߵ�Ʊ������
	 * TopVotedCandidate�仯����ߵ�Ʊ������
	 * TopVotedCandidate�仯����ߵ�Ʊ���仯
	 * �������t
	 */
	@Test
	public void test() {
		int person[] = {0, 1, 1, 0, 0, 1, 0};
		int times[] = {0, 5, 10, 15, 20, 25, 30};
		TopVotedCandidate top = new TopVotedCandidate(person, times);
		assertEquals(0, top.q(3));
		assertEquals(1, top.q(12));
		assertEquals(1, top.q(25));
		assertEquals(0, top.q(15));
		assertEquals(0, top.q(24));
		assertEquals(1, top.q(8));
		assertEquals(0, top.q(30));
		assertEquals(0, top.q(31));
		assertEquals(1, top.q(10));
	}

}
