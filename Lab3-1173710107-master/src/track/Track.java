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
     * ɾ������ϵ���������
     * AF:����������
     * RI:true
     * immutable
     */
	public void emptyAll()
	{
		objects.clear();
	}
	 /**
     * ������
     * @param name���������
     * @param size����Ĵ�С
     * AF:�½�track
     * RI:size>0
     * immutable
     */
	public Track (String name,double size) {
		this.size = size;
		this.name = name;
	}
	 /**
     * �ж��Ƿ����������
     * @param �жϸù���Ƿ����������obj
     * @return �����������true������������false
     * AF:����Ƿ����������immutablemap�Ƿ����
     * RI:true
     * immutable
     */
	public boolean contains(E obj)
	{
		return objects.values().contains(obj);
	}
	 /**
     * �жϸù���ж�������
     * @return ���ظù�������������
     * AF:���������������Ӧ��immutablemap�е�Ԫ�ظ���
     * RI:true
     * immutable
     */
	public int howMany()
	{
		return objects.size();
	}
	 /**
     * �жϸ������ڹ�����Ǹ��Ƕ�
     * @param ���жϵĶ�����obj
     * @return ���ظù���ĽǶȣ������������361
     * AF:��Ӧ��Ѱ�Ҹ�������set�е�key
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
     * ���¸ı�ù���Ĵ�С
     * 
     * @param �ù����size
     * AF:����size
     * RI:size>0
     * immutable
     */
	public void resize(double size)
	{
		this.size = size;
	}
	 /**
     * �Ըù��������������
     * @param ��������������name
     * AF:���¸�ֵname
     * RI:true
     * immutable
     */
	public void rename(String name)
	{
		this.name = name;
	}
	 /**
     * ����������壬���ǲ�������
     * @param ���ӵ�����obj
     * AF:objects���
     * RI:true
     * immutable
     */
	public void add(E obj)
	{
		objects.put(defpos--,obj);
	}
	 /**
     * �ڹ�������������λ��
     * @param ���ӵ�����obj
     * @param ���������λ��pos
     * AF:��ӵ�objects��
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
     * ɾ������ϵ�����
     * @param Ҫɾ��������obj
     * @return ���ɾ���ɹ�������ɾ�������壬ɾ��ʧ�ܷ���null
     * AF:��objects��ɾ����
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
     * ��ȡ�ù����size
     * @return ���ظù����size
     * AF:����size
     * RI:true
     * immutable
     */
	public double getsize()
	{
		return this.size;
	}
	 /**
     * �ƶ��ù���ϵ����嵽��һ��λ��
     * @param ���ƶ�������obj
     * @param �ƶ�����λ��pos
     * @return �ɹ��Ļ�������true�����򷵻�false
     * AF:ɾ������put��objects
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
        	System.out.println("�ù����λ�������Ѿ��������壡����");
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
     * ��ȡһ�������ù���ϵ�����Ԫ�ص�list
     * @return �ù���ϵ�����Ԫ�ص�list
     * AF:����list
     * RI:true
     * immutable
     */
	public ArrayList<E> allElements()
	{
		return new ArrayList<E>(this.objects.values());
	}
	 /**
     * ��ȡ�ù����name
     * @return ���ظù����name
     * AF:����name
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
     * ��ȡ�ù����������Ϣ
     * @return һ���������������Ϣ��string
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
