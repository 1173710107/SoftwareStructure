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
     * ����������������ڵڼ�������ϣ����ع���ļ���
     * @param ��Ҫ֪��������
     * @return �������ڵڼ������������ֵ��int
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
	 * ���������Ƶ�index��
	 *  @Param ����object��Ҫ�ƶ����Ĳ���index
	 *  @return �ƶ�ʧ�ܷ���null���ƶ��ɹ�����Ҫ�ƶ���object
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
     * ���ӹ���������Ǹ�����n��
     * 
     * @param n��Ҫ���ӵĹ��������
     */
	public void autoAddTrack(int n) {
		for(int i=tracks.size();i<=n;i++)
			addTrack("Track"+i,i);
	}
	 /**
     * ��չ��������
     */
	public void emptyAll()
	{
		for(Track<E> temp : tracks)
		   temp.emptyAll();
	}
	 /**
     * ��ȡ�ļ�������ת����string
     * 
     * @param �ļ��Ķ�ȡ·��
     * @return ���ļ��ж�ȡ���ַ���
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
     * ���ڷ������е�Ԫ��
     * 
     * @return ����Ԫ�ع��ɵ�һ��list
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
     * ��������Ԫ�ع��ɵĹ�ϵ
     * 
     * @return ���й�ϵ��һ��list
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
     * ����iterator
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
