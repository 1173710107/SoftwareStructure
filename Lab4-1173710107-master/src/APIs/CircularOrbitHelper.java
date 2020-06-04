package APIs;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import circularOrbit.CircularOrbit;
import circularOrbit.FriendshipGraph;
import circularOrbit.Pair;
import circularOrbit.SocialNetworkCircle;

public class CircularOrbitHelper {
	 /**
     * 将系统可视化
     * 
     * @param 一个把每个track的数目的list nums 
     */
	public static void visualize(ArrayList<Integer> nums) 
	{
		int numOfTracks = nums.size();
		int k = 300/numOfTracks;
		double sitha=0;
		Picture pic = new Picture(900,800);
		for(int i=1;i<numOfTracks;i++)
		{
			int r = 10+(i+1)*k;
			pic.add(new Circle(450,400,r));
			sitha = 2*Math.PI/nums.get(i);
			for(int j=0;j<nums.get(i);j++)
			{
				pic.add(new Circle((int)(450+r*Math.cos(sitha*j)),(int)(400+r*Math.sin(sitha*j)),5));
			}
		}
		Circle c1 = new Circle(450,400,10);
		pic.add(c1);
		pic.draw();	
	}
	 /**
     * 将一个系统可视化
     * 
     * @param 一个系统orbit
     */
	public static <L,E>void visualize(CircularOrbit<L,E> orbit)
	{
		class position
		{
			int x;
			int y;
			position(int x,int y)
			{
				this.x=x;
				this.y=y;
			}
			@Override
			public String toString() {
				return "position [x=" + x + ", y=" + y + "]";
			}
		}
		Map<E,position> pos= new HashMap<E,position>();
		ArrayList<position> track1 = new ArrayList<position>();
		ArrayList<Integer> nums = orbit.numOnEachTrack();
		Iterator<E> ite = orbit.iterator();
		for(int i=1;i<= nums.get(0);i++)
		{
			ite.next();//忽略掉所有在track0的物体
		}
		int numOfTracks = nums.size();
		int k = 300/numOfTracks;
		double sitha=0;
		Picture pic = new Picture(900,800);
		for(int i=1;i<numOfTracks;i++)
		{
			int r = 10+(i+1)*k;
			pic.add(new Circle(450,400,r));//在图上添加轨道
			sitha = 2*Math.PI/nums.get(i);
			for(int j=0;j<nums.get(i);j++)//针对每一个物体
			{
				pic.add(new Circle((int)(450+r*Math.cos(sitha*j)),(int)(400+r*Math.sin(sitha*j)),5));
				if(i==1)
				{
					track1.add(new position((int)(450+r*Math.cos(sitha*j)),(int)(400+r*Math.sin(sitha*j))));
				}
				pos.put(ite.next(),new position((int)(450+r*Math.cos(sitha*j)),(int)(400+r*Math.sin(sitha*j))));
			}
		}
		ArrayList<Pair<E,E>> rel = orbit.getPair();
		for(Pair<E,E> temp:rel)
		{
			position pos1 = pos.get(temp.get1());
		    position pos2 = pos.get(temp.get2());
		    if(pos1!=null&&pos2!=null)
		    {
			Line a = new Line(pos1.x,pos1.y,pos2.x,pos2.y);
			pic.add(a);
		    }
		}
		for(position temp:track1)
		{
			Line a = new Line(450,400,temp.x,temp.y);
			pic.add(a);
		}
		Circle c1 = new Circle(450,400,10);
		pic.add(c1);
		pic.draw();	
		System.out.println(pos);
	}
	/*public static void main(String[] args)
	{
		ArrayList<Integer> a = new ArrayList<Integer>(); 
		a.add(9);
		a.add(7);
		a.add(11);
		a.add(12);
		a.add(18);
		visualize(new SocialNetworkCircle("txt/SocialNetworkCircle_Medium.txt"));
	}*/
}
