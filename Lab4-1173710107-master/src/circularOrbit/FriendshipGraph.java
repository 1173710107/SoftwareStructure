package circularOrbit;

import centralObject.Person;
import java.util.ArrayList;
import java.util.Map;
public class FriendshipGraph<T> {
	ConcreteVerticesGraph<T> graph= new ConcreteVerticesGraph<T>();
	ArrayList<T> visited = new ArrayList<>();
	int count=4;
	int dis=0;
	 /**
     * 增加边
     * 
     * @param a边的开头
     * @param b边的结尾
     * @return 正常添加，返回1
     */
	int addEdge(T a,T b)
	{
		graph.set(a, b, 1);
		return 1;
	}
	 /**
     * 增加一个物体
     * 
     * @param 要增加的物体
     * @return 正常添加返回1，添加失败返回-1
     */
	int addVertex(T a) {
		for(T temp:graph.vertices())
		{
			if(temp.equals(a))
			{
				  System.out.println("该名字已经存在!");
				  return -1;
			}
			graph.add(a);
		}
		return 1;
	}
	 /**
     * 增加边
     * 
     * @param a 边的开头
     * @param b 边的结尾
     * @param val边的权重
     */
   void addEdge(T a,T b,int val)
   {
	   graph.set(a, b, val);
   }
	 /**
    * 删除掉边
    * 
    * @param a 边的开头
    * @param b 边的结尾
    */
   void removeEdge(T a,T b)
   {
	   graph.set(a, b, 0);
   }
   boolean flag = false;
	 /**
    * 获取两点之间的长度
    * 
    * @param a 起始点
    * @param b 终结点
    * @return 获取两点之间的距离，如果失败的话，返回-1
    */
    int getDistance(T a,T b)
    {
    	count=3;
    	flag = false;
    	dis=0;
    	DFS(a,b);
    	if(flag == false)
    		count = -1;
    	return count;
    }
	 /**
     * 深度优先搜索算法
     * 
     * @param a 搜索起点
     * @param b 搜索终点
     */
	void DFS(T a,T b)
	{
		if(dis>count)
			return;
		if(count == 1)
			return;
		if(a.equals(b))
		{
			if(dis<=count)
			{
				count = dis;
				flag = true;
			}
			return;
		}
		for(Map.Entry<T, Integer> temp : graph.targets(a).entrySet())
		{
			if(visited.contains(temp.getKey())==false)
			{
				visited.add(temp.getKey());
				dis++;
				DFS(temp.getKey(),b);
				dis--;
				visited.remove(temp.getKey());
			}
		}
	}
	@Override
	public String toString()
	{
		return graph.toString();
	}
  /* public static void main(String args[]) {
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
	   System.out.println(graph.getDistance(rachel, ross)); 
	   System.out.println(graph.getDistance(rachel, ben)); 
	  System.out.println(graph.getDistance(rachel, rachel)); 
	   System.out.println(graph.getDistance(rachel, kramer)); 

   }*/
}
