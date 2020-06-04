package APIs;
import java.util.ArrayList;

import circularOrbit.CircularOrbit;
public class CircularOrbitAPIs<L,E> {
	 /**
     * ��ȡ�Ϸ��ľ���
     * 
     * @param һ�������ϵͳc
     * @param ��ʼ����e1
     * @param �ս������e2
     * @return ���غϷ��ľ��룬���δ��ȡ�����룬����-1
     * AF:�߼�������ϵͳ��������������
     * RI��true
     * mutable
     */
	public static <L,E>int getLogicalDistance (CircularOrbit<L,E> c, E e1, E e2)
	{
		return c.getLogicalDistance(e1, e2);
	}
	 /**
     * ��ȡʵ�ʵľ���
     * 
     * @param һ�������ϵͳc
     * @param ��ʼ�������e1
     * @param �ս�������e2
     * @return ���ؾ��룬���������Ҫ�󣬷���-1
     * AF:����������빫ʽ�����Ӧ���������
     * RI��true
     * mutable
     */
	public static <L,E>double getPhysicalDistance (CircularOrbit<L,E> c, E e1, E e2)
	{
		return c.getPhysicalDistance(e1, e2);
	}
	 /**
     * ��ȡ����ϵͳ�Ĳ�֮ͬ��
     * 
     * @param ��һ��ϵͳc1 
     * @param �ڶ���ϵͳc2
     * @return ����ÿ�������Ŀ��ͬ��һ��list
     * AF:�����������ڸ������ݵĲ�ͬ
     * RI��true
     * mutable
     */
	ArrayList<Integer> getDifference (CircularOrbit<L,E> c1, CircularOrbit<L,E> c2)
	{
		ArrayList<Integer> num1 = c1.numOnEachTrack();
		ArrayList<Integer> num2 = c2.numOnEachTrack();
		ArrayList<Integer> ret = new ArrayList<Integer>();
		if(num1.size()!=num2.size())
		{
			System.out.println("��С���Ϸ�!");
			return ret;
		}
		for(int i=0;i<num1.size();i++)
		{
			ret.add(num1.get(i)-num2.get(i));
		}
		return ret;
	}
	 /**
     * ��ȡ����ϵͳ����ֵ
     * @param ÿ������Ϸֲ����������
     * @return ��������
     * AF:������ֵ
     * RI��true
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
     * ��ȡ����ϵͳ����ֵ
     * @param һ��ϵͳa
     * @return ��ϵͳ����
     * AF:��������ϵͳ��ֵ
     * RI��true
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

