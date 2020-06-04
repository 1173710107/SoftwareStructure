package APIs;
import java.util.ArrayList;

import circularOrbit.CircularOrbit;
public class CircularOrbitAPIs<L,E> {
	 /**
     * 获取合法的距离
     * 
     * @param 一个具体的系统c
     * @param 开始物体e1
     * @param 终结点物体e2
     * @return 返回合法的距离，如果未获取到距离，返回-1
     * AF:逻辑距离在系统中是相隔几个轨道
     * RI：true
     * mutable
     */
	public static <L,E>int getLogicalDistance (CircularOrbit<L,E> c, E e1, E e2)
	{
		return c.getLogicalDistance(e1, e2);
	}
	 /**
     * 获取实际的距离
     * 
     * @param 一个具体的系统c
     * @param 开始点的物体e1
     * @param 终结点的物体e2
     * @return 返回距离，如果不符合要求，返回-1
     * AF:利用两点距离公式计算对应的物理距离
     * RI：true
     * mutable
     */
	public static <L,E>double getPhysicalDistance (CircularOrbit<L,E> c, E e1, E e2)
	{
		return c.getPhysicalDistance(e1, e2);
	}
	 /**
     * 获取两个系统的不同之处
     * 
     * @param 第一个系统c1 
     * @param 第二个系统c2
     * @return 包括每个轨道数目不同的一个list
     * AF:两个参数对于各个数据的不同
     * RI：true
     * mutable
     */
	ArrayList<Integer> getDifference (CircularOrbit<L,E> c1, CircularOrbit<L,E> c2)
	{
		ArrayList<Integer> num1 = c1.numOnEachTrack();
		ArrayList<Integer> num2 = c2.numOnEachTrack();
		ArrayList<Integer> ret = new ArrayList<Integer>();
		if(num1.size()!=num2.size())
		{
			System.out.println("大小不合法!");
			return ret;
		}
		for(int i=0;i<num1.size();i++)
		{
			ret.add(num1.get(i)-num2.get(i));
		}
		return ret;
	}
	 /**
     * 获取整个系统的熵值
     * @param 每个轨道上分布物体的数量
     * @return 整个的熵
     * AF:计算熵值
     * RI：true
     * mutable
     */
	public static double getObjectDistributionEntropy(ArrayList<Integer> nums) 
	{
		double H = .0;
		int sum = 0;
		for(Integer temp:nums)
		{
			sum+=temp;
		}
        for (Integer temp:nums){   
            double p = 1.0*temp/sum;   
            if(p>0)
            	H += -(p*Math.log(p)/Math.log(2));
        }   
        return H;
	}
	 /**
     * 获取整个系统的熵值
     * @param 一个系统a
     * @return 该系统的熵
     * AF:计算整个系统熵值
     * RI：true
     * mutable
     */
	public static <L,E>double getObjectDistributionEntropy(CircularOrbit<L,E> a) 
	{
		ArrayList<Integer> nums = a.numOnEachTrack();
        return getObjectDistributionEntropy(nums);
	}
	public static void main(String[] args)
	{
		ArrayList<Integer> test = new ArrayList<Integer>();
		test.add(0);
		test.add(0);
		test.add(3);
		System.out.println(getObjectDistributionEntropy(test));
	}
}

