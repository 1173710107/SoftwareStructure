package P2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FriendshipGraphTest {



	@Test
	void testAddVertex() {
		FriendshipGraph graph = new FriendshipGraph();

		Person rachel = new Person("Rachel");
		 Person  ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		assertEquals(1,graph.addVertex(rachel));
		assertEquals(1,graph.addVertex(ross));
		assertEquals(1,graph.addVertex(ben));
		assertEquals(1,graph.addVertex(kramer));
		//fail("Not yet implemented");
	}

	@Test
	void testAddEdge() {
		FriendshipGraph graph = new FriendshipGraph();

		Person rachel = new Person("Rachel");
		 Person  ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		assertEquals(1,graph.addEdge(rachel, ross));
		assertEquals(1,graph.addEdge(ross, rachel));
		assertEquals(1,graph.addEdge(ross, ben));
		assertEquals(1,graph.addEdge(ben, ross));
		//fail("Not yet implemented");
	}

	@Test
	void testGetDistance() {
		FriendshipGraph graph = new FriendshipGraph();

		Person rachel = new Person("Rachel");
		 Person  ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		assertEquals(1,graph.getDistance(rachel, ross));
		assertEquals(2,graph.getDistance(rachel, ben));
		assertEquals(0,graph.getDistance(rachel, rachel));
		assertEquals(-1,graph.getDistance(rachel, kramer));
		//fail("Not yet implemented");
	}


}
