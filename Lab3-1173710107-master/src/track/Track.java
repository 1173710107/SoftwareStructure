package track;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.google.common.collect.ImmutableMap;

public class Track<E>
{
	private Map<Double,E> objects = new ImmutableMap.Builder<Double,E>().build();
	private double defpos = -1;
	private double size = 0;
	private String name;
	 /**
     * 删除轨道上的所有物体
     * AF:轨道物体清空
     * RI:true
     * immutable
     */
	public void emptyAll()
	{
		objects.clear();
	}
	 /**
     * 构造器
     * @param name轨道的名字
     * @param size轨道的大小
     * AF:新建track
     * RI:size>0
     * immutable
     */
	public Track (String name,double size) {
		this.size = size;
		this.name = name;
	}
	 /**
     * 判断是否包含该物体
     * @param 判断该轨道是否包含的物体obj
     * @return 如果包含返回true，不包含返回false
     * AF:轨道是否包含，就是immutablemap是否包含
     * RI:true
     * immutable
     */
	public boolean contains(E obj)
	{
		return objects.values().contains(obj);
	}
	 /**
     * 判断该轨道有多少物体
     * @return 返回该轨道上物体的数量
     * AF:轨道物体数量，对应于immutablemap中的元素个数
     * RI:true
     * immutable
     */
	public int howMany()
	{
		return objects.size();
	}
	 /**
     * 判断该物体在轨道的那个角度
     * @param 被判断的额物体obj
     * @return 返回该轨道的角度，其他情况返回361
     * AF:对应于寻找该物体在set中的key
     * RI:true
     * immutable
     */
	public double angleOf(E obj)
	{
        Set<Double> keys = objects.keySet();
        for(Double a:keys)
        {
        	if(objects.get(a).equals(obj))
        	{
        	   return a;
        	}
        }
        return 361.0;
	}
	 /**
     * 重新改变该轨道的大小
     * 
     * @param 该轨道的size
     * AF:重置size
     * RI:size>0
     * immutable
     */
	public void resize(double size)
	{
		this.size = size;
	}
	 /**
     * 对该轨道进行重新命名
     * @param 重新命名的名字name
     * AF:重新赋值name
     * RI:true
     * immutable
     */
	public void rename(String name)
	{
		this.name = name;
	}
	 /**
     * 轨道增加物体，但是不给坐标
     * @param 增加的物体obj
     * AF:objects添加
     * RI:true
     * immutable
     */
	public void add(E obj)
	{
		objects.put(defpos--,obj);
	}
	 /**
     * 在轨道上增加物体和位置
     * @param 增加的物体obj
     * @param 物体的坐标位置pos
     * AF:添加到objects中
     * RI:pos>0 pos<361
     * immutable
     */
	public void add(E obj,double pos)
	{
        Set<Double> keys = objects.keySet();
        if(keys.contains(pos))
        {
        	System.out.println("Already exists");
        	return;
        }
		objects.put(pos,obj);
	}
	 /**
     * 删除轨道上的物体
     * @param 要删除的物体obj
     * @return 如果删除成功，返回删除的物体，删除失败返回null
     * AF:在objects中删除掉
     * RI:true
     * immutable
     */
	public E remove(E obj)
	{
		Double todispose = -1.0;
		boolean flag = false;
		E ret;
        Set<Double> keys = objects.keySet();
        for(Double a:keys)
        {
        	if(objects.get(a).equals(obj))
        	{
        	todispose = a;
        	flag = true;
        	break;
        	}
        }
        if(flag==true)
        {
        	ret = objects.get(todispose);
        	objects.remove(todispose);
        	return ret;
        }
        return null;
	}
	 /**
     * 获取该轨道的size
     * @return 返回该轨道的size
     * AF:返回size
     * RI:true
     * immutable
     */
	public double getsize()
	{
		return this.size;
	}
	 /**
     * 移动该轨道上的物体到另一个位置
     * @param 被移动的物体obj
     * @param 移动到的位置pos
     * @return 成功的话，返回true，否则返回false
     * AF:删除重新put到objects
     * RI:pos>0 pos<361
     * immutable
     */
	public boolean changePos(E obj,Double pos)
	{
		Double todispose = -1.0;
		boolean flag = false;
        Set<Double> keys = objects.keySet();
        if(keys.contains(pos))
        {
        	System.out.println("该轨道该位置上面已经存在物体！！！");
        	return false;
        }
        for(Double a:keys)
        {
          	if(objects.get(a).equals(obj))
          	{
        	todispose = a;
        	flag = true;
        	break;
          	}
        }
        if(flag==true)
        {
        	objects.remove(todispose);
        	objects.put(pos, obj);
        }
        return flag;
	}
	 /**
     * 获取一个包括该轨道上的所有元素的list
     * @return 该轨道上的所有元素的list
     * AF:返回list
     * RI:true
     * immutable
     */
	public ArrayList<E> allElements()
	{
		return new ArrayList<E>(this.objects.values());
	}
	 /**
     * 获取该轨道的name
     * @return 返回该轨道的name
     * AF:返回name
     * RI:true
     * immutable
     */
	public String getname()
	{
		return this.name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Track other =  (Track)obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		if(size!=0)
		{
		StringBuilder a = new StringBuilder();
		a.append("-------------------------------------------------\n");
		a.append("NAME: "+name+" SIZE: "+size+":\n");
		Set<Double> keys = objects.keySet();
	        for(Double temp:keys)
	        {
	        	if(temp<0)
	        	{
	        		a.append(objects.get(temp)+"\n");
	        	}
	        	else
	        	{
	        		a.append(objects.get(temp)+" on "+temp+"\n");
	        	}
	      }
		return a.toString();
		}
		else
			return "";
	}
	 /**
     * 获取该轨道的所有信息
     * @return 一个包含轨道所有信息的string
     */
	public String singleLinetoString() {
		if(size!=0)
		{
		StringBuilder a = new StringBuilder();
		a.append("\n");
		a.append(name);
		Set<Double> keys = objects.keySet();
	        for(Double temp:keys)
	        {
	        	if(temp<0)
	        	{
	        		a.append(objects.get(temp)+" ");
	        	}
	        	else
	        	{
	        		a.append(objects.get(temp)+" on "+temp+" ");
	        	}
	      }
		return a.toString();
		}
		else
			return "";
	}
    public static void main(String[] args)
    {
    	Track<String> a = new Track<String>("default",10);
    	a.add("thing1");
    	a.add("thing2");
    	a.add("thing20",20.22);
    	a.add("thing5",5);
    	a.add("thing14",14);
    	a.add("thing100",100);
    	a.add("thing18",18);
    	System.out.println(a.contains("thing1"));
    	System.out.println(a.contains("thing11"));
    	System.out.println(a);
    	a.remove("thing20");
    	System.out.println(a);
    	a.changePos("thing18", 14.0);
    	a.changePos("thing14", 15.0);
    	System.out.println(a);
    	a.add("thing12",15.0);
    	a.add("thing3");
    	a.add("thing55");
    	a.add("thing55", 25.0);
    	
    }
}
