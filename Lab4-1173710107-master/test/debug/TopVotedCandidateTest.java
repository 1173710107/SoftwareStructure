package debug;

import static org.junit.Assert.*;

import org.junit.Test;

public class TopVotedCandidateTest {
	/**
	 * t的取值
	 * TopVotedCandidate不变，最高得票数变化
	 * TopVotedCandidate不变，最高得票数不变
	 * TopVotedCandidate变化，最高得票数不变
	 * TopVotedCandidate变化，最高得票数变化
	 * 超过最大t
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
