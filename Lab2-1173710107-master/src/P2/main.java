package P2;

public class main {
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
