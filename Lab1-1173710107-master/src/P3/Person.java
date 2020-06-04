package P3;
import java.util.ArrayList;
public class Person {
	String name;
	boolean visited;
	ArrayList<Person> friends;

	public Person(String name) {
		this.name = name;
		this.visited = false;
		this.friends = new ArrayList<Person>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public ArrayList<Person> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<Person> friends) {
		this.friends = friends;
	}

}
