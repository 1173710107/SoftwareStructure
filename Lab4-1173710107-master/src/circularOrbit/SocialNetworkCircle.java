package circularOrbit;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.*;

import exception.InvalidTag;
import exception.NumOrder;
import exception.SyntaxSpec;
import exception.errorordertag;
import exception.sameele;
import exception.sametag;
import exception.wrongformat;
import exception.wrongrely;
import track.Track;
import physicalObject.Account;
import org.apache.log4j.Logger; 
import org.apache.log4j.BasicConfigurator; 
import org.apache.log4j.Level;
public class SocialNetworkCircle extends ConcreteCircularOrbit<Account,Account>{
    ArrayList<String> infoall = new ArrayList<String>();
    ArrayList<Account> allFriends = new ArrayList<Account>();
    Logger logger = Logger.getLogger(SocialNetworkCircle.class);
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
		this.infoall = this.readFile(path);
		this.setinfoall(infoall);
		this.Analysis();
	}
	public final void setinfoall(ArrayList<String> info)
	{
		this.infoall = info;
	}
	public final void Analysis()
		{
		logger.setLevel(Level.INFO);
			try
			{
				Account cent = new Account("init",1,"M");
				boolean visited = false;
				int centnum = 0;
				for(String info:infoall)
				{
					visited = false;
					int i=0;
					Pattern p1 = Pattern.compile("CentralUser ::= <([a-zA-Z0-9]+) *, *(\\d+) *, *(M|F)>");
					Matcher m1 = p1.matcher(info);
					if(m1.find())
					{
						 cent = new Account(m1.group(1),Integer.valueOf(m1.group(2)),m1.group(3));
						this.addCenter(cent);
						this.allFriends.add(cent);
						this.relations.addVertex(cent);
						visited = true;
						centnum++;
					}
					if(centnum==2)
					{
						logger.error("Ӧ�������ĵ��ǩ���������Σ����������ı�");
						throw new sametag();
					}
					Pattern p2 = Pattern.compile("Friend ::= <([a-zA-Z0-9]+) *, *(\\d+) *, *(M|F)>");
					Matcher m2 = p2.matcher(info);
					if(m2.find())
					{
						Account a = new Account(m2.group(1),Integer.valueOf(m2.group(2)),m2.group(3));
						if(allFriends.contains(a))
						{
							throw new sameele();
						}
						this.allFriends.add(a);
						this.relations.addVertex(a);
						visited = true;
					}
					Pattern p3 = Pattern.compile("SocialTie ::= <([a-zA-Z0-9]+) *, *([a-zA-Z0-9]+) *, *((0|1).\\d{1,3})>");
					Matcher m3 = p3.matcher(info);
					if(m3.find())
					{
						Account A = getAccount(m3.group(1));
						Account B = getAccount(m3.group(2));
						int val = (int)(1000*Double.valueOf(m3.group(3)));
						if(!allFriends.contains(A)||!allFriends.contains(B))
						{
							logger.error("Ӧ�����й�ϵû�ж�Ӧ��������������friends��ǩ������socialtie֮�����������ı�");
							throw new errorordertag();
						
						}
		                this.relations.addEdge(A, B,val);
						this.relations.addEdge(B, A,val);
		                visited = true;
					}
					if(!visited)
					{
						if(info.contains(" ::= <"))
						{
							System.out.println(info);
							String [] line = info.split(" ::= <");
							if(line.length==2)
							{
								if(line[0].equals("CentralUser"))
								{
									String [] line1 = line[1].split(",");
									if(line1.length==3)
									{
										int j=0;
										for( j=0;j<line1[0].length();j++)
										{
											if(!Character.isLetter(line1[0].charAt(j)))
											{
												logger.error("Ӧ�������ĵ��һ��������ʽ���ԣ����������ı�");
												throw new SyntaxSpec();
											}
										}
										for(j=0;j<line1[1].length();j++)
										{
											if(!Character.isDigit(line1[1].charAt(j)))
											{
												logger.error("Ӧ�������ĵ�ڶ���������ʽ���ԣ����������ı�");
												throw new SyntaxSpec();
											}
										}
										if(line1[2].contains(">"))
										{
											String [] line2 = line1[2].split(">");
											if(line2.length==1)
											{
												if(line2[0].equals("M"))
												{
													
												}
												else if(line2[0].equals("F"))
												{
													
												}
												else
												{
													logger.error("Ӧ�������ĵ������������ʽ���ԣ����������ı�");
													throw new SyntaxSpec();
												}
											}
											else
											{
												logger.error("Ӧ�������ĵ��ʽ���ԣ����������ı�");
												throw new wrongformat();
											}
										}
										else
										{
											logger.error("Ӧ�������ĵ��ʽ���ԣ����������ı�");
											throw new wrongformat();
										}
										
										
									}
									else
									{
										logger.error("Ӧ�������ĵ��ʽ���ԣ����������ı�");
										throw new wrongformat();
									}
								}
								else if(line[0].equals("Friend"))
								{
									
									int j=0;
									String [] line1 = line[1].split(",");
									if(line1.length==3)
									{
										for(j=0;j<line1[1].length();j++)
										{
											if(!Character.isDigit(line1[1].charAt(j)))
											{
												logger.error("Ӧ����friends�������Ǵ����֣����������ı�");
												throw new SyntaxSpec();
											}
										}
										if(line1[2].contains(">"))
										{
											String [] line2 = line1[2].split(">");
											if(line2.length==1)
											{
												if(line2[0].equals("M"))
												{
													
												}
												else if(line2[0].equals("F"))
												{
													
												}
												else
												{
													logger.error("Ӧ����friends��������M/F�����������ı�");
													throw new SyntaxSpec();
												}
											}
											else
											{
												logger.error("Ӧ����friends��ʽ���ԣ����������ı�");
												throw new wrongformat();
											}
										}
										else
										{
											logger.error("Ӧ����friends��ʽ���ԣ����������ı�");
											throw new wrongformat();
										}
									}
									else
									{
										logger.error("Ӧ����friends��ʽ���ԣ����������ı�");
										throw new wrongformat();
									}
									
								}
								else if(line[0].equals("SocialTie"))
								{
									String [] line1 = line[1].split(",");
									if(line1.length==3)
									{
										String [] line2 = line1[2].split(">");
										if(line2.length==1)
										{
											String [] line3 = line2[0].split("\\.");
											if(line3.length==2)
											{
												String newinfo = null;
												if(line3[1].length()==3)
												{
												//û�ҵ�����
												}
												else
												{
													for( i=0;i<line3[1].length();i++)
													{
														if(!Character.isDigit(line3[1].charAt(i)))
														{
															logger.error("Ӧ����socialtie�������Ǵ����֣����������ı�");
															throw new SyntaxSpec();
														}
													}
													if(line3[1].length()<3)
													{
														if(line3[1].length()==1)
														{
															 newinfo = "SocialTie ::= <"+line1[0]+","+line1[1]+","+line3[0]+"."+line3[1]+"00"+">";
														}
														else if(line3[1].length()==2)
														{
															 newinfo = "SocialTie ::= <"+line1[0]+","+line1[1]+","+line3[0]+"."+line3[1]+"0"+">";
														}
														else
														{
															 newinfo = "SocialTie ::= <"+line1[0]+","+line1[1]+","+line3[0]+"."+"000"+">";
														}
														
													}
													else{
														String d = Character.toString(line3[1].charAt(0))+Character.toString(line3[1].charAt(1))+Character.toString(line3[1].charAt(2));
														newinfo = "SocialTie ::= <"+line1[0]+","+line1[1]+","+line3[0]+"."+d+"000"+">";
													}
													Pattern p5 = Pattern.compile("SocialTie ::= <([a-zA-Z0-9]+) *, *([a-zA-Z0-9]+) *, *((0|1).\\d{1,3})>");
													Matcher m5 = p5.matcher(newinfo);
													if(m5.find())
													{
														Account A = getAccount(m5.group(1));
														Account B = getAccount(m5.group(2));
														int val = (int)(1000*Double.valueOf(m5.group(3)));
														if(!allFriends.contains(A)||!allFriends.contains(B))
														{
															logger.error("Ӧ����socialtie������ϵ����ȷ������friends��ǩ������socialtie֮�����������ı�");
															throw new errorordertag();
														
														}
										                this.relations.addEdge(A, B,val);
														this.relations.addEdge(B, A,val);
										                visited = true;
										                logger.error("Ӧ����socialtie�Բ�����ϵȨ�ؽ�������д�������˹���С����С����λ��ֱ��0���룬������λ����ȥ��������");
													}
													else
													{
														logger.error("Ӧ����socialtie�������Ǵ����֣����������ı�");
														throw new SyntaxSpec();
													}
												}
											}
											else
											{
												logger.error("Ӧ���������ı��ĸ�ʽ���ԣ����������ı�");
												throw new SyntaxSpec();
											}
										}
										else
										{
											logger.error("Ӧ���������ı��ĸ�ʽ���ԣ����������ı�");
											throw new wrongformat();
										}
					
									}
									else
									{
										logger.error("Ӧ���������ı��ĸ�ʽ���ԣ����������ı�");
										throw new wrongformat();
									}
								}
								else
								{
									logger.error("Ӧ�����ı��зǷ���ǩ�Ĵ��ڣ����������ı�");
									throw new InvalidTag();
								}
							}
							else
							{
								logger.error("Ӧ���������ı��ĸ�ʽ���ԣ����������ı�");
								throw new wrongformat();
							}
						}
						else 
						{
							System.out.println(info);
							logger.error("Ӧ���������ı��ĸ�ʽ���ԣ����������ı�");
							throw new wrongformat();
						}
						
					}
					
				}
				for(Account temp : allFriends)
				{
					int dis = this.relations.getDistance(cent, temp);
					if(dis>0)
					{
						this.autoAddTrack(dis);
						this.addObject(dis, temp);
					}
				}
				
				}
			catch(sameele e)
			{
				e.newfile2(this, 3);
			}
			catch(NumOrder e)
		   {
				  e.newfile2(this,3);
		   }
		   catch(sametag e)
		   {
			  e.newfile2(this,3);
		   }
		   catch(InvalidTag e)
		   {
			   e.newfile2(this,3);
		   }
		   catch(SyntaxSpec e)
		   {
			   e.newfile2(this,3);
		   }
		   catch(wrongformat e)
		   {
			   e.newfile2(this,3);
		   }
			catch(wrongrely e)
			{
				e.newfile2(this, 3);
			}
			catch(errorordertag e)
			{
				e.newfile2(this, 3);
			}
			
		}
	/**
	 * ���ÿ�������ڵĹ���Ƿ���ں����ĵ�ľ���
	 */
	public void checkRep()
	{
		try
		{
			for(Account x:allFriends)
			{
				int dis1 = this.onWhichTrack(x);
				for(Account y:centers)
				{
					int dis2 = this.getLogicalDistance(y, x);
					if(dis1!=dis2)
					{
						throw new wrongrely();
					}
				}
				
			}
		}
		catch(wrongrely e)
		{
			e.wrongrely1();
		}
		
	}
		@Override
		public String toString() {
			StringBuilder a = new StringBuilder();
			a.append("Center:"+ this.centers+"\n");
			String string = this.tracks.toString();
			a.append(string);
		return a.toString();
		}
		public static void main(String[] args) {
			SocialNetworkCircle test= new SocialNetworkCircle("txt/SocialNetworkCircle_Medium.txt");
			System.out.println(test);
		}
//
//	}

}
