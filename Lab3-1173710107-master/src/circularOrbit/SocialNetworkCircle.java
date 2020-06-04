package circularOrbit;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.*;
import track.Track;
import physicalObject.Account;
public class SocialNetworkCircle extends ConcreteCircularOrbit<Account,Account>{
    String info;
    ArrayList<Account> allFriends = new ArrayList<Account>();
	 /**
     * ��ȡ�����ֵ��˻���Ϣ
     * @param ��Ҫ���˻�������name 
     * @return ���ظ����ֵ��˻�
     * AF:��ȡ����ϵ����壬Ҳ���Ƕ�Ӧ���˻�
     * RI��true
     * mutable
     */
    Account getAccount(String name)
    {
    	for(Account temp:allFriends)
    	{
    		if (temp.getName().equals(name))
    			return temp;
    	}
    	System.out.println("δ�ҵ����˻�������");
    	return null;
    }
	 /**
     * ����������ӹ��
     * AF:�½�һ���������Ӧ�ڶ�һ������Ȧ
     * RI��true
     * immutable
     */
	public void newTrack()
	{
		int trackNumbers = tracks.size();
		this.addTrack("track"+trackNumbers, trackNumbers);
	}
	 /**
     * ɾ�������Ĺ��
     * AF:ɾ�������Ĺ������Ӧ������Ȧ�������
     * RI��true
     * immutable
     */
	public void clearTrack()
	{
		int trackNumbers = tracks.size();
		this.removeTrack(trackNumbers-1);
	}
	 /**
     * ���Ҹ����ֵ��˻����ĸ����
     * @param �˻�������name
     * @return ���ظ��˻������ڵĲ�����δ�ҵ�����-1
     * AF:�������ֶ�Ӧ�����ڸù����
     * RI��true
     * immutable
     */
	public int onWhichTrack(String name)
	{
		Account temp = getAccount(name);
		return relations.getDistance(this.centers.get(0), temp);
	}
	 /**
     * ���Ҹ��˻����ڵĹ��
     * 
     * @param ��Ҫ���ҵ��˻�temp
     * @return �ҵ����ظ��˻����ڹ���������򷵻�-1
     * AF:�������ֶ�Ӧ�����ڸù����
     * RI:true
     * mutable
     */
	public int onWhichTrack(Account temp)
	{
		return relations.getDistance(this.centers.get(0), temp);
	}
	 /**
     * ˢ��
     */
	public void refresh()
	{
		for(Track<Account> temp: tracks)
		{
			temp.emptyAll();
		}
		for(Account temp : allFriends)
		{
			int dis = this.relations.getDistance(this.centers.get(0), temp);
			if(dis>0)
			{
				this.autoAddTrack(dis);
				this.addObject(dis, temp);
			}
		}
	}
	 /**
     * �ҳ�ĳ���û���Ǳ�ڵ����Ѹ���
     * @param ���û�������name 
     * @return ���Ѹ���
     * AF:������ֵ��Ӧ�ڸ��˺ŵ���չ���Ѹ���
     * RI��return>=0
     * immutable
     */
	public int expansion(String name)
	{
		int res = 0;
		Account temp = getAccount(name);
		if(onWhichTrack(name)==1)
		{
			for(Map.Entry<Account, Integer> temp1:this.relations.graph.targets(temp).entrySet())
			{
				if(temp1.getValue()>=500&&temp1.getKey()!=this.centers.get(0)&&(!(tracks.get(1).contains(temp1.getKey()))))
					res++;
			}
		}
		else
		{
			System.out.println("Not on track 1!");
		}
		return res;
	}
	 /**
     * ���������û��Ĺ�ϵ
     * 
     * @param һ���û�������a
     * @param һ���û�������b
     * @param val
     * AF��������������ıߣ���Ӧ�����������˺�֮��Ĺ���
     * RI��val>0
     * immutable
     */
	public void addEdge(String a,String b,int val)
	{
		Account A = this.getAccount(a);
		Account B = this.getAccount(b);
		if(A==null || B == null)
		{
			System.out.println("��ӹ�ϵʧ�ܣ���");
			return;
		}
		this.relations.addEdge(A, B, val);
	}
	 /**
     * ɾ�������˻��Ĺ�ϵ
     * 
     * @param ��ʼ����˻�����a 
     * @param �ս����˻�����b
     * AF:��Ӧ��ɾ�������˻�֮�����ϵ
     * RI��true
     * immutable
     */
	public void removeEdge(String a, String b)
	{
		Account A = this.getAccount(a);
		Account B = this.getAccount(b);
		if(A==null || B == null)
		{
			System.out.println("ɾ����ϵʧ��");
			return;
		}
		this.relations.removeEdge(A, B);
	}
	 /**
     * ��ȡ�����û�֮��ľ���
     * @param ��ʼ���û�����a 
     * @param �ս����û�����b 
     * @return ���������û��Ķ���룬������Ϸ�ʧ�ܵĶ������-1
     * AF:��ȡ�����˺�֮����߼����룬��Ӧ����������֮������·��
     * RI��return>=-1
     * immutable
     */
	public int getLogicalDistance(String a,String b)
	{
		Account A = this.getAccount(a);
		Account B = this.getAccount(b);
		if(A==null || B == null)
		{
			System.out.println("���ֲ��Ϸ�������");
			return -1;
		}
		return this.getLogicalDistance(A, B);
	}
	 /**
     * ����һ���罻����
     * 
     * @param �ļ���·��path
     * 
     */
	public SocialNetworkCircle(String path) {
		this.info = this.readFile(path);
		this.analysis();
	}
		void analysis()
		{
			int i=0;
			Pattern p1 = Pattern.compile("CentralUser ::= <([a-zA-Z0-9]+) *, *(\\d+) *, *(M|F)>");
			Matcher m1 = p1.matcher(info);
			m1.find();
			Account cent = new Account(m1.group(1),Integer.valueOf(m1.group(2)),m1.group(3));
			this.addCenter(cent);
			this.allFriends.add(cent);
			this.relations.addVertex(cent);
			Pattern p2 = Pattern.compile("Friend ::= <([a-zA-Z0-9]+) *, *(\\d+) *, *(M|F)>");
			Matcher m2 = p2.matcher(info);
			while(m2.find())
			{
				Account a = new Account(m2.group(1),Integer.valueOf(m2.group(2)),m2.group(3));
				this.allFriends.add(a);
				this.relations.addVertex(a);
			}
			Pattern p3 = Pattern.compile("SocialTie ::= <([a-zA-Z0-9]+) *, *([a-zA-Z0-9]+) *, *((0|1).\\d{1,3})>");
			Matcher m3 = p3.matcher(info);
			while(m3.find())
			{
				Account A = getAccount(m3.group(1));
				Account B = getAccount(m3.group(2));
				int val = (int)(1000*Double.valueOf(m3.group(3)));
                this.relations.addEdge(A, B,val);
                this.relations.addEdge(B, A,val);
			}
			for(Account temp : allFriends)
			{
				int dis = this.relations.getDistance(cent, temp);
				System.out.println(dis+" "+i++);
				if(dis>0)
				{
					this.autoAddTrack(dis);
					this.addObject(dis, temp);
				}
			}
		}
		@Override
		public String toString() {
			StringBuilder a = new StringBuilder();
			a.append("Center:"+ this.centers+"\n");
			a.append(this.tracks.toString());
		return a.toString();
		}
		public static void main(String[] args) {
			SocialNetworkCircle test= new SocialNetworkCircle("txt/SocialNetworkCircle_Medium.txt");
			System.out.println(test);
		}
//
//	}

}
