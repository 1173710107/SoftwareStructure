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
     * ���ӱ�
     * 
     * @param a�ߵĿ�ͷ
     * @param b�ߵĽ�β
     * @return ������ӣ�����1
     */
	int addEdge(T a,T b)
	{
		graph.set(a, b, 1);
		return 1;
	}
	 /**
     * ����һ������
     * 
     * @param Ҫ���ӵ�����
     * @return ������ӷ���1�����ʧ�ܷ���-1
     */
	int addVertex(T a) {
		for(T temp:graph.vertices())
		{
			if(temp.equals(a))
			{
				  System.out.println("�������Ѿ�����!");
				  return -1;
			}
			graph.add(a);
		}
		return 1;
	}
	 /**
     * ���ӱ�
     * 
     * @param a �ߵĿ�ͷ
     * @param b �ߵĽ�β
     * @param val�ߵ�Ȩ��
     */
   void addEdge(T a,T b,int val)
   {
	   graph.set(a, b, val);
   }
	 /**
    * ɾ������
    * 
    * @param a �ߵĿ�ͷ
    * @param b �ߵĽ�β
    */
   void removeEdge(T a,T b)
   {
	   graph.set(a, b, 0);
   }
   boolean flag = false;
	 /**
    * ��ȡ����֮��ĳ���
    * 
    * @param a ��ʼ��
    * @param b �ս��
    * @return ��ȡ����֮��ľ��룬���ʧ�ܵĻ�������-1
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
     * ������������㷨
     * 
     * @param a �������
     * @param b �����յ�
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
