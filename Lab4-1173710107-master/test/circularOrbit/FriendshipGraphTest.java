package circularOrbit;
import centralObject.Person;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FriendshipGraphTest {

	@Test
	void test() {
		   FriendshipGraph<Person> graph = new FriendshipGraph<Person>();
		   Person rachel = new Person("Rachel");
		   Person ross = new Person("Ross");
		   Person ben = new Person("Ben");
		   Person kramer = new Person("Kramer");
		   Person kramer2 = new Person("Kramer");
		   graph.addVertex(rachel);
		   graph.addVertex(ross);
		   graph.addVertex(ben);
		   graph.addVertex(kramer);
		   graph.addVertex(kramer2);
		   graph.addEdge(rachel, ross,2);
		   graph.addEdge(ross, rachel,2);
		   graph.addEdge(ross, ben,2);
		   graph.addEdge(ben, ross,2);
		   
		   System.out.println(graph.toString());
		   assertEquals(1,graph.getDistance(rachel, ross)); 
		   //should print 1
		   assertEquals(2,graph.getDistance(rachel, ben)); 
		   //should print 2
		  assertEquals(0,graph.getDistance(rachel, rachel)); 
		   //should print 0
		   assertEquals(-1,graph.getDistance(rachel, kramer)); 
		   //should print -1
		   graph.addEdge(ross, ben);
	}

}
