package P3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class FriendshipGraph {
	public  HashSet<Person> nodes;

	public FriendshipGraph() {
		nodes = new HashSet<Person>();
	}
	
	public  int addVertex(Person name) {
		if (name == null)
			return -1;
		else
		{
			for(Person s:nodes)
			{
				if(s.getName().equals(name.name))
				{
					System.out.println("该人名已经被添加！！！");
					System.exit(0);
				}
			}
			nodes.add(name);
			return 1;
		}
	}

	public  int addEdge(Person person1, Person person2) {
		if (person1 == null || person2 == null)
			return -1;

		person1.friends.add(person2);

		return 1;
	}

	public  int getDistance(Person person1, Person person2) {
		if (person1 == null || person2 == null)
			return -1;

		int distance = 0;

		if (person1.name.equalsIgnoreCase(person2.name))
			return distance;

		Queue<ArrayList<Person>> queue = new LinkedList<ArrayList<Person>>();

		queue.offer(person1.friends);

		while (!queue.isEmpty()) {
			distance++;

			int size = queue.size();

			for (int i = 0; i < size; i++) {
				ArrayList<Person> node = queue.poll();
				for (int j = 0; j < node.size(); j++) {

					if (node.get(j).name.equalsIgnoreCase(person2.name))
						return distance;

					if (!node.get(j).visited
							&& (!node.get(j).friends.isEmpty()))
						queue.offer(node.get(j).friends);

					node.get(j).visited = true;
				}
			}

		}

		return -1;
	}
	public static void main(String args[])
	{
		 FriendshipGraph graph = new FriendshipGraph();

			Person rachel = new Person("Rachel");
			 Person  ross = new Person("Ross");
			 Person x = new Person("Rachel");
			Person ben = new Person("Ben");
			Person kramer = new Person("Kramer");
			//graph.addVertex(x);
			if(graph.addVertex(rachel) == -1)
				System.out.println("添加人时出错!！");
			if(graph.addVertex(ross) == -1)
				System.out.println("添加人时出错!！");
			if(graph.addVertex(ben) == -1)
				System.out.println("添加人时出错!！");
			if(graph.addVertex(kramer) == -1)
				System.out.println("添加人时出错!！");
			
			if(graph.addEdge(rachel, ross) == -1)
				System.out.println("添加关系时出错！！");
			if(graph.addEdge(ross, rachel) == -1)
				System.out.println("添加关系时出错！！");
			if(graph.addEdge(ross, ben) == -1)
				System.out.println("添加关系时出错！！");
			if(graph.addEdge(ben, ross) == -1)
				System.out.println("添加关系时出错！！");
			
			System.out.println(graph.getDistance(rachel, ross)); 
			System.out.println(graph.getDistance(rachel, ben)); 
			System.out.println(graph.getDistance(rachel, rachel)); 
			System.out.println(graph.getDistance(rachel, kramer)); 
	}
}
