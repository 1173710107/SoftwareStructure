package P2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import P1.graph.ConcreteVerticesGraph;
import P1.graph.Graph;

public  class FriendshipGraph{
	public  ConcreteVerticesGraph<Person> graph;

	public FriendshipGraph() {
		graph = new ConcreteVerticesGraph<Person>();
	}

	
	public  int addVertex(Person name) {
		if (name==null)
			return -1;
		else
		{
			for(Person s:graph.vertices())
			{
				if(s.getName().equals(name.name))
				{
					System.out.println("该人名已经被添加！！！");
					System.exit(0);
				}
			}
			graph.add(name);
			return 1;
		}
	}

	public  int addEdge(Person person1, Person person2) {
		if (person1==null || person2==null)
			return -1;

		graph.set(person1, person2, 2);

		return 1;
	}

	public  int getDistance(Person person1, Person person2) {
		if (person1==null || person2==null)
			return -1;

		int distance = 0;

		if (person1.name.equalsIgnoreCase(person2.name))
			return distance;
		Queue<ArrayList<Person>> queue = new LinkedList<ArrayList<Person>>();
		ArrayList<Person> friends = new ArrayList<Person>();
		 Map<Person, Integer> x = new  HashMap<Person, Integer>();
		 x = graph.targets(person1);
		 for(Person in : x.keySet()) 
		 { 
			 friends.add(in);
			// System.out.println(in.name);
		 }
		queue.offer(friends);
		while (!queue.isEmpty()) {
			distance++;

			int size = queue.size();

			for (int i = 0; i < size; i++) {
				ArrayList<Person> node = queue.poll();
				for (int j = 0; j < node.size(); j++) {

					if (node.get(j).name.equalsIgnoreCase(person2.name))
						return distance;
					if (!node.get(j).visited
							&& (!graph.targets(node.get(j)).isEmpty()))
					{
						//queue.offer(node.get(j).friends);
						ArrayList<Person> friend3 = new ArrayList<Person>();
						for(Person s:graph.targets(node.get(j)).keySet())
						{
							friend3.add(s);
						}
						queue.offer(friend3);
					}
					node.get(j).visited = true;
				}
			}
		}
		return -1;
	}
	
}
