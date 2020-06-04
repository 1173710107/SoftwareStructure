package circularOrbit;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import track.Track;
public class ConcreteCircularOrbit<L,E> implements CircularOrbit<L,E>{
    List<L> centers = new ArrayList<L>();
    public FriendshipGraph<E> relations= new FriendshipGraph<E>();
    List<Track<E>> tracks = new ArrayList<Track<E>>();
	@Override
	public String toString() {
		return "centers=" + centers + "\nrelations=" + relations + "\n" + tracks;
	}
    @Override
	public boolean addTrack(String name,double size) {
	    Track<E> a = new Track<E>(name,size);
	    return tracks.add(a);
	}
	@Override
	public void removeTrack(int index) {
		tracks.remove(index);
	}

	@Override
	public boolean addCenter(L center) {
		centers.add(center);
		return false;
	}
	
	@Override
	public boolean addObject(int index, E Obj) {
		try {
		Track<E> dispose = (Track<E>)tracks.get(index);
		dispose.add(Obj);
		}catch(java.lang.IndexOutOfBoundsException e)
		{
			System.out.println("the track number doesn't exist!");
		}
		return false;
	}
	@Override
	public boolean addObject(int index, E Obj,double pos) {
		try {
		Track<E> dispose = (Track<E>)tracks.get(index);
		dispose.add(Obj,pos);
	}catch(java.lang.IndexOutOfBoundsException e)
	{
		System.out.println("the track number doesn't exist!");
	}
		return false;
	}
    @Override
	public int getLogicalDistance(E source,E target)
	{
		return relations.getDistance(source, target);
	}
	
	@Override
	public double getPhysicalDistance(E source,E target)
	{
		int track1 = whichTrack(source);
		if(track1<0)
		{
			return -1;
		}
		int track2 = whichTrack(target);
		if(track2<0)
		{
			return -1;
		}
		double r1 = tracks.get(track1).getsize();
		double a1 = tracks.get(track1).angleOf(source);
		double sitha1 = Math.toRadians(a1);
		double r2 = tracks.get(track2).getsize();
		double a2 = tracks.get(track2).angleOf(target);
		double sitha2 = Math.toRadians(a2);
		double x1 = r1*Math.cos(sitha1);
		double y1 = r1*Math.sin(sitha1);
		double x2 = r2*Math.cos(sitha2);
		double y2 = r2*Math.sin(sitha2);
		return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	}
/*
 * 
 * 
 */
	@Override
	public boolean addRelation(E source, E target) {
		relations.addEdge(source,target);
		return false;
	}
	@Override
	public E transit(E object, int index) {
		for (Track<E> temp:tracks)
		{
			if(temp.contains(object))
			{
				E prev = temp.remove(object);
				tracks.get(index).add(prev);
				return prev;
			}
		}
		return null;
	}
	@Override
	public boolean move(E object, double x) {
		for (Track<E> temp:tracks)
		{
			if(temp.contains(object))
			{
				temp.changePos(object, x);
				return true;
			}
		}
		return false;
	}
	 /**
     * 
     * 计算给出的物体是在第几个轨道上，返回轨道的级数
     * @param 想要知道的物体
     * @return 返回是在第几个轨道，返回值是int
     */
	int whichTrack(E object)
	{
		for(Track<E> temp:tracks)
		{
			if(temp.contains(object))
				return tracks.indexOf(temp);
		}
		return -1;
	}
	
	public ArrayList<Integer> numOnEachTrack()
	{
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for(Track<E> temp :tracks)
		{
			ret.add(temp.howMany());
		}
		return ret;
	}
	/**
	 * 将该物体移到index层
	 *  @Param 物体object，要移动到的层数index
	 *  @return 移动失败返回null，移动成功返回要移动的object
	 */
	public E transit2(E object, int index) {
		for (Track<E> temp:tracks)
		{
			if(temp.contains(object))
			{
				E prev = temp.remove(object);
				tracks.get(index).add(object);
				return prev;
			}
		}
		return null;
	}
	
	 /**
     * 增加轨道（数量是给定的n）
     * 
     * @param n是要增加的轨道的数量
     */
	public void autoAddTrack(int n) {
		for(int i=tracks.size();i<=n;i++)
			addTrack("Track"+i,i);
	}
	 /**
     * 清空轨道的所有
     */
	public void emptyAll()
	{
		for(Track<E> temp : tracks)
		   temp.emptyAll();
	}
	 /**
     * 读取文件把内容转换成string
     * 
     * @param 文件的读取路径
     * @return 从文件中读取的字符串
     */
	public static String readFile(String filename) {
		 StringBuilder a = new StringBuilder();
		 File file = new File(filename);
		 BufferedReader reader=null;
	     try {  
	         reader = new BufferedReader(new FileReader(file));  
	         String tempString = null;   
	         while ((tempString = reader.readLine()) != null) {  
	             a.append(tempString+'\n'); 
	         }  
	         reader.close();  
	     } catch (IOException e) {  
	         e.printStackTrace();  
	     } finally {  
	         if (reader != null) {  
	             try {  
	                 reader.close();  
	             } catch (IOException e1) {  
	             }  
	         }  
	     }  
		return a.toString();
	}
	

	 /**
     * 用于返回所有的元素
     * 
     * @return 所有元素构成的一个list
     */
	ArrayList<E> allElements()
	{
		ArrayList<E> res = new ArrayList<E>();
		for(Track<E> temp:this.tracks)
		{
			 res.addAll(temp.allElements());
		}
		return res;
	}
	 /**
     * 返回所有元素构成的关系
     * 
     * @return 所有关系的一个list
     */
   public ArrayList<Pair<E,E>> getPair()
   {
	   return this.relations.graph.getPair();
   }

	class iterator implements Iterator<E>
	{
        ArrayList<E> allElement = allElements();
        int index = 0;
		@Override
		public boolean hasNext() {
			return index<allElement.size();
		}

		@Override
		public E next() {
			return allElement.get(index++);
		}
		
	}
	 /**
     * 返回iterator
     */
	public Iterator<E> iterator() {
		return new iterator();
	}
	public static void main(String[] args)
	{
		ConcreteCircularOrbit<String,String> test= new ConcreteCircularOrbit<String,String>();
		test.addCenter("central");
		test.addCenter("star");
		test.addTrack("track0", 0.5);
		test.addTrack("track1", 1);
		test.addTrack("track2", 2);
		test.addObject(0, "something on 0",0);
		test.addObject(0,"something on 90",90);
		test.addObject(1, "another thing on 90",90);
		test.addObject(2,"something on 1",1);
		test.addRelation("track1", "track2");
		test.addRelation("track2","track3");
		test.addRelation("track3","track4");
		test.addRelation("track2","track4");
		System.out.println(test.getLogicalDistance("track1", "track4"));
		System.out.println(test.getPhysicalDistance("something on 0", "another thing on 90"));
		System.out.println(test.numOnEachTrack());
	   System.out.println(test);
	   System.out.println(test.allElements());
	   Iterator<String> ite = test.iterator();
	   while(ite.hasNext())
	   {
		   System.out.println(ite.next());
	   }
	}
}
