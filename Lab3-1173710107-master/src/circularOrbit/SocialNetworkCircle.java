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
     * 获取该名字的账户信息
     * @param 需要的账户的名字name 
     * @return 返回该名字的账户
     * AF:获取轨道上的物体，也就是对应的账户
     * RI：true
     * mutable
     */
    Account getAccount(String name)
    {
    	for(Account temp:allFriends)
    	{
    		if (temp.getName().equals(name))
    			return temp;
    	}
    	System.out.println("未找到该账户！！！");
    	return null;
    }
	 /**
     * 在最外层增加轨道
     * AF:新建一个轨道，对应于多一层朋友圈
     * RI：true
     * immutable
     */
	public void newTrack()
	{
		int trackNumbers = tracks.size();
		this.addTrack("track"+trackNumbers, trackNumbers);
	}
	 /**
     * 删除最外层的轨道
     * AF:删除最外层的轨道，对应于朋友圈的最外层
     * RI：true
     * immutable
     */
	public void clearTrack()
	{
		int trackNumbers = tracks.size();
		this.removeTrack(trackNumbers-1);
	}
	 /**
     * 查找该名字的账户在哪个轨道
     * @param 账户的名字name
     * @return 返回该账户的所在的层数，未找到返回-1
     * AF:返回数字对应于所在该轨道号
     * RI：true
     * immutable
     */
	public int onWhichTrack(String name)
	{
		Account temp = getAccount(name);
		return relations.getDistance(this.centers.get(0), temp);
	}
	 /**
     * 查找该账户所在的轨道
     * 
     * @param 需要查找的账户temp
     * @return 找到返回该账户所在轨道数，否则返回-1
     * AF:返回数字对应于所在该轨道号
     * RI:true
     * mutable
     */
	public int onWhichTrack(Account temp)
	{
		return relations.getDistance(this.centers.get(0), temp);
	}
	 /**
     * 刷新
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
     * 找出某个用户的潜在的朋友个数
     * @param 该用户的名字name 
     * @return 朋友个数
     * AF:返回数值对应于该账号的拓展朋友个数
     * RI：return>=0
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
     * 增加两个用户的关系
     * 
     * @param 一个用户的名字a
     * @param 一个用户的名字b
     * @param val
     * AF：增加两个物体的边，对应于增加两个账号之间的关联
     * RI：val>0
     * immutable
     */
	public void addEdge(String a,String b,int val)
	{
		Account A = this.getAccount(a);
		Account B = this.getAccount(b);
		if(A==null || B == null)
		{
			System.out.println("添加关系失败！！");
			return;
		}
		this.relations.addEdge(A, B, val);
	}
	 /**
     * 删除两个账户的关系
     * 
     * @param 起始点的账户名字a 
     * @param 终结点的账户名字b
     * AF:对应于删除两个账户之间的联系
     * RI：true
     * immutable
     */
	public void removeEdge(String a, String b)
	{
		Account A = this.getAccount(a);
		Account B = this.getAccount(b);
		if(A==null || B == null)
		{
			System.out.println("删除关系失败");
			return;
		}
		this.relations.removeEdge(A, B);
	}
	 /**
     * 获取两个用户之间的距离
     * @param 起始点用户名字a 
     * @param 终结点的用户名字b 
     * @return 返回两个用户的额距离，如果不合法失败的额话，返回-1
     * AF:获取两个账号之间的逻辑距离，对应于两个物体之间的最短路径
     * RI：return>=-1
     * immutable
     */
	public int getLogicalDistance(String a,String b)
	{
		Account A = this.getAccount(a);
		Account B = this.getAccount(b);
		if(A==null || B == null)
		{
			System.out.println("名字不合法！！！");
			return -1;
		}
		return this.getLogicalDistance(A, B);
	}
	 /**
     * 建立一个社交网络
     * 
     * @param 文件的路径path
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
