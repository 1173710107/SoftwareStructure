package applications;
import circularOrbit.*;
import exception.errorinfile;
import physicalObject.*;
import testLog4j.testLog4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import APIs.*;
import org.apache.log4j.Logger; 
import org.apache.log4j.BasicConfigurator; 
import org.apache.log4j.Level;
public class Main {
	
	 static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		Logger logger = Logger.getLogger(Main .class);
	    logger.setLevel(Level.INFO);
		int type;
		int choice;
		String path;
		
		System.out.println("��ѡ��Ӧ��?");
		System.out.println("1.TrackGame");
		System.out.println("2.AtomStructure");
		System.out.println("3.SocialNetworkCircle");
		type = in.nextInt();
		in.nextLine();
		
		if(type==1)
		{
			logger.info("ѡ��trackgameӦ��");
			
			TrackGame game = new TrackGame();
			System.out.println("���ڶ�ȡ TrackGame.txt �е���Ϣ");
			logger.info("��ȡtrackgame.txt����Ϣ");
			path = ("txt/TrackGame.txt");
			ArrayList<String> info = ConcreteCircularOrbit.readFile(path);
			game.setinfoall(info);
			logger.info("���ڶԶ����ı�����������ʽ������");
			game.Analysis();
			while(true)
			{
				logger.info("���Ӧ��һ��ѡ��˵�");
				System.out.println("��ѡ�����湦��?");
				System.out.println("1.���ӻ�");
				System.out.println("2.���ӹ��");
				System.out.println("3.ɾ�����");
				System.out.println("4.��ֵ");
				System.out.println("5.�������");
				System.out.println("6.���ճɼ�����");
				System.out.println("7.�ı���");
				System.out.println("8.�˳�");
				System.out.println("9.��ѯlogger");
			//	in.nextLine();
				choice = in.nextInt();
				in.nextLine();
				System.out.println("!!!");
				switch(choice)
				{
 					case 1:
						logger.info("Ӧ��һѡ����ӻ�����");
						System.out.println(game);
						CircularOrbitHelper.visualize(game.numOnEachTrack());
						break;
					case 2:
						logger.info("Ӧ��һ����һ���µĹ��");
						game.newTrack();
						break;
					case 3:
						logger.info("Ӧ��һɾ�������Ĺ��");
						game.clearTrack();
						break;
					case 4:
						logger.info("Ӧ��һ������ֵ");
						System.out.println(CircularOrbitAPIs.getObjectDistributionEntropy(game.numOnEachTrack()));
						break;
					case 5:
						logger.info("Ӧ��һ�Ե�ǰ��Ϣ�����������");
						game.emptyAll();
						game.shuffleArrange();
						System.out.println(game);
						break;
					case 6:
						logger.info("Ӧ��һ���ճɼ��Ե�ǰ��Ϣ��������");
						game.emptyAll();
						game.sortedArrange();
						System.out.println(game);
						break;
					case 7:
						
						System.out.println("��������Ҫ�ƶ����˶�Ա������");
						String name = in.nextLine();
						Athlete temp = new Athlete(name,0,"CHN",0,0.00);
						System.out.println("��������Ҫ�ƶ�����λ��?");
						int tar = in.nextInt();
						in.nextLine();
						game.transit(temp, tar);
						logger.info("Ӧ��һ���˶�Ա"+name+"�ƶ���"+tar+"�����");
						break;
					case 8:
						logger.info("�˳���ǰ��Ӧ��");
						break;
					case 9:
						searchlog();
						break;
					default:
						logger.error("Ӧ��һѡ�������������������");
						System.out.println("�밴��ʽ���룡��");
				}
				
				
			}	
		}
		else if(type==2)
		{
			logger.info("��ȡ AtomicStructure.txt�ļ�����Ϣ");
			System.out.println("���ڶ�ȡ AtomicStructure.txt �ļ�����Ϣ");
			path = ("txt/AtomicStructure.txt");
			logger.info("��Ӧ�ö������ı�����������ʽƥ��");
			AtomStructure atom = new AtomStructure(path);
			while(true)
			{
				logger.info("���Ӧ�ö��Ĺ��ܲ˵�");
				System.out.println("��ѡ������Ĺ���?");
				System.out.println("1.���ӻ�");
				System.out.println("2.���ӹ��");
				System.out.println("3.ɾ�����");
				System.out.println("4.������ֵ");
				System.out.println("5.�ı���");
				System.out.println("6.�˳�");
				System.out.println("7.��ѯlogger");
				
				choice = in.nextInt();
				in.nextLine();
				switch(choice)
				{
				case 1:
					logger.info("����Ӧ�ö��Ŀ��ӻ�");
					System.out.println(atom);
					CircularOrbitHelper.visualize(atom.numOnEachTrack());
					break;
				case 2:
					logger.info("Ӧ�ö�����һ�����");
					atom.newTrack();
					break;
				case 3:
					logger.info("Ӧ�ö�ɾ���������");
					atom.clearTrack();
					break;
				case 4:
					logger.info("Ӧ�ö�������ֵ");
					System.out.println(CircularOrbitAPIs.getObjectDistributionEntropy(atom.numOnEachTrack()));
					break;
				case 5:
					
					System.out.println("���ĸ������ʼ�ƶ�?");
					int source = in.nextInt();
					in.nextLine();
					System.out.println("�ƶ����Ǹ������?");
					int tar = in.nextInt();
					in.nextLine();
					atom.transitElectron(source, tar);
					logger.info("Ӧ�ö���"+source+"����ƶ���"+tar+"���");
					break;
				case 6:
					logger.info("�˳���ǰӦ��");
					break;
				case 7:
					searchlog();
					break;
				default:
					logger.error("Ӧ�ö��������д�������������");
					System.out.println("�밴��ʽ���룡��");
				}
			
				
			}	
		}
		else if (type == 3)
		{
			logger.info("����SocialNetworkCircle.txt �ļ�");
			System.out.println("���ڶ���  SocialNetworkCircle.txt �ļ�");
			path = ("txt/SocialNetworkCircle.txt");
			logger.info("��Ӧ�������ı�����������ʽƥ��");
			SocialNetworkCircle circle = new SocialNetworkCircle(path);
			while(true)
			{
				logger.info("���Ӧ�����˵�");
				System.out.println("��ѡ�����湦�ܣ�");
				System.out.println("1.���ӻ�");
				System.out.println("2.���ӹ��");
				System.out.println("3.ɾ�����");
				System.out.println("4.������ֵ");
				System.out.println("5.����ĳ���û����ĸ����");
				System.out.println("6.�鿴ĳ���û������ж�������");
				System.out.println("7.���ӹ�ϵ");
				System.out.println("8.ɾ����ϵ");
				System.out.println("9.ˢ��");
				System.out.println("10.���������û�֮��ľ���");
				System.out.println("11.�˳�");
				System.out.println("12.��ѯlogger");
				Scanner in3 = new Scanner(System.in);
				choice = in3.nextInt();
				in3.nextLine();
				switch(choice)
				{
					case 1:
						logger.info("��Ӧ�������п��ӻ�����");
						System.out.println(circle);
						CircularOrbitHelper.visualize(circle);
						break;
					case 2:
						logger.info("Ӧ��������¹��");
						circle.newTrack();
						break;
					case 3:
						logger.info("Ӧ��������������");
						circle.clearTrack();
						break;
					case 4:
						logger.info("����Ӧ��������ֵ");
						System.out.println(CircularOrbitAPIs.getObjectDistributionEntropy(circle.numOnEachTrack()));
						break;
					case 5:
						
						System.out.println("��������Ҫ�鿴���û������֣�");
						String source = in.nextLine();
						System.out.println(circle.onWhichTrack(source));
						logger.info("Ӧ������ѯ��"+source+"�û���"+circle.onWhichTrack(source)+"�����");
						break;
					case 6:
						System.out.println("��������Ҫ�鿴���û������֣�");
						String source1 = in.nextLine();
						System.out.println(circle.expansion(source1));
						logger.info("Ӧ������ѯ"+source1+"��"+circle.expansion(source1)+"����");
						break;
					case 7:
						System.out.println("��������Ҫ������ϵ����ʼ�û�");
						String source2 = in.nextLine();
						System.out.println("��������Ҫ������ϵ���յ��û�");
						String target = in.nextLine();
						System.out.println("Ȩ����?");
						Double val = in.nextDouble();
						in.nextLine();
						int ival = (int)(val*1000);
						circle.addEdge(source2, target, ival);
						logger.info("Ӧ���������û�"+source2+"��"+target+"�Ĺ�ϵ��ȨֵΪ"+val);
						break;
					case 8:
						System.out.println("��������Ҫɾ����ϵ����ʼ�û�");
						String source3 = in.nextLine();
						System.out.println("��������Ҫɾ����ϵ���յ��û�");
						String target1 = in.nextLine();
						circle.removeEdge(source3, target1);
						logger.info("Ӧ����ɾ���û�"+source3+"���û�"+target1+"�Ĺ�ϵ");
						break;
					case 9:
						circle.refresh();
						logger.info("Ӧ����ˢ��");
						break;
					case 10:
						System.out.println("��������Ҫ��ȡ�������ʼ�û�");
						String source4= in.nextLine();
						System.out.println("��������Ҫ��ȡ������յ��û�");
						String target2 = in.nextLine();
						System.out.println(circle.getLogicalDistance(source4, target2));
						logger.info("Ӧ������ȡ�û�"+source4+"���û�"+target2+"�ľ��룬������"+circle.getLogicalDistance(source4, target2));
						break;
					case 11:
						logger.info("Ӧ�����˳���ǰӦ��");
						break;
					case 12:
						searchlog();
						break;
					default:
						logger.error("Ӧ���������벻�Ϸ�����Ҫ��������");
						System.out.println("�밴��ʽ���룡��");
				}
				
			
			}	
		}in.close();
	}

	public static void searchlog()
	{
		System.out.println("1.��ʱ���ѯ");
		System.out.println("2.�����Ͳ�ѯ");
		System.out.println("3.�����ѯ");
		System.out.println("4.��������ѯ");
		System.out.println("5.���������Ͳ�ѯ");
		//Scanner in = new Scanner(System.in);
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("log4j.log"));//��ȡ�ļ�
			int choice = in.nextInt();
			in.nextLine();
			String line = null;
			int year =0;
			int month =0;
			int day =0;
			int hour = 0;
			int minite = 0;
			int miao =0;
			String time =null;
			String input = null;
			String input1 = null;
			String input2 = null;
			String input3 = null;
			if(choice==1)
			{
				System.out.println("�������ѯlog��ʱ��Σ���ʽ���� �� �� Сʱ ���� ��    eg:2019 05 15 19 02 04 2019 06 29 10 30 03");
				
			}
			else if(choice==2)
			{
				System.out.println("�������ѯlog������(INFO/ERROR)");
				 
			}
			else if(choice==3)
			{
				System.out.println("�������ѯlog����");
				 
			}
			else if(choice==4)
			{
				System.out.println("�������ѯlog�ķ���");
				
			}
			else if(choice==5)
			{
				System.out.println("�������ѯlog�Ĳ�������eg:ѡ��trackgameӦ��");
				
			}
			String input0 = in.nextLine();
			if(choice==1)
			{
				//System.out.println("�������ѯlog��ʱ��Σ���ʽ���� �� �� Сʱ ���� ��    eg:2019 05 15 19 02 04 2019 06 29 10 30 03");
				time = input0;
			}
			else if(choice==2)
			{
				//System.out.println("�������ѯlog������(INFO/ERROR)");
				 input = input0;
			}
			else if(choice==3)
			{
				//System.out.println("�������ѯlog����");
				 input1 = input0;
			}
			else if(choice==4)
			{
				//System.out.println("�������ѯlog�ķ���");
				 input2 = input0;
			}
			else if(choice==5)
			{
				//System.out.println("�������ѯlog�Ĳ�������eg:ѡ��trackgameӦ��");
				input3 = input0;
			}
			while((line = br.readLine())!=null)
			{
				//ʱ��
				String [] line1 = line.split("\\]");
				//System.out.println(line1[0]);
				String [] line2 = line1[0].split("\\[");
				String file = null;
				if(line2.length==1)
				{
					file = line2[0];
				}
				else if(line2.length==2)
				{
					file = line2[1];
				}
				else
				{
					System.out.println("@");
					throw new IOException();
				}
				String [] num = file.split(" ");
				if(num.length==2)
				{
					String [] file1 = num[0].split("-");
					if(file1.length==3)
					{
						String [] file2 = num[1].split(":");
						if(file2.length==3)
						{//file1������file2ʱ����
							try {
								String [] x= file2[2].split(",");
								
							    year = Integer.parseInt(file1[0]);
							    month = Integer.parseInt(file1[1]);
							    day = Integer.parseInt(file1[2]);
							    hour = Integer.parseInt(file2[0]);
							    minite = Integer.parseInt(file2[1]);
							    miao = Integer.parseInt(x[0]);
							} catch (NumberFormatException e) {
								System.out.println("!");
								IOException x = new IOException();
								x.initCause(e);
							    throw x;
							}
								
						}
						else
						{
							System.out.println("$");
							throw new IOException();
						}
					}
					else
					{
						System.out.println("5");
						throw new IOException();
					}
				}
				else
				{
					//System.out.println("p");
					throw new IOException();
				}
				// �������
				String [] l = line.split("-");
				String infomation = null;
				String classfun =null;
				if(l.length==5)
				{
					infomation = l[4];
					classfun = l[3];
				}
				//class&function
				//����
				String type = null;
				if(line.contains("INFO"))
				{
					type ="INFO";
				}
				else if(line.contains("ERROR"))
				{
					type ="ERROR";
				}
				
				else
				{
					System.out.println("j");
					System.out.println(line);
					throw new IOException();
				}
				switch(choice)
				{
				case 1:
					String [] time1 = time.split(" ");
					if(time1.length==12)
					{
						try {
							String [] y = time1[5].split(",");
							String [] z = time1[11].split(",");
							int byear = Integer.parseInt(time1[0]);
						    int bmonth = Integer.parseInt(time1[1]);
						    int bday = Integer.parseInt(time1[2]);
						    int bhour = Integer.parseInt(time1[3]);
						    int bminite = Integer.parseInt(time1[4]);
						    int bmiao = Integer.parseInt(y[0]);
						    int eyear = Integer.parseInt(time1[6]);
						    int emonth = Integer.parseInt(time1[7]);
						    int eday = Integer.parseInt(time1[8]);
						    int ehour = Integer.parseInt(time1[9]);
						    int eminite = Integer.parseInt(time1[10]);
						    int emiao = Integer.parseInt(z[0]);
						  //  int flag =0;
						  /*  if(byear<=year&&bmonth<=month&&bday<=day&&bhour<=hour&&bminite<=minite&&bmiao<=miao)
						    {
						    	if(year<=eyear&&month<=emonth&&day<=eday&&hour<=ehour&&minite<=eminite&&miao<=emiao)
						    	{
						    		System.out.println("ʱ��Ϊ"+year+" "+month+" "+day+" "+hour+" "+minite+" "+miao+
						    				"����"+type+"�෽��Ϊ"+classfun+"�������Ϊ"+infomation);
						    	}
						    	
						    }*/
						    String beginTime=new String(byear+"-"+bmonth+"-"+bday+" "+bhour+":"+bminite+":"+bmiao);
						    String endTime=new String(eyear+"-"+emonth+"-"+eday+" "+ehour+":"+eminite+":"+emiao); 
						    String nowtime = new String(year+"-"+month+"-"+day+" "+hour+":"+minite+":"+miao);
						    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						    try
						    {
						    	Date sd1=df.parse(beginTime);
						    	Date sd2=df.parse(endTime);
						    	Date sd3 = df.parse(nowtime);
						    	if(sd1.before(sd3)&&sd2.after(sd3))
						    	{
						    		System.out.println("ʱ��Ϊ"+year+" "+month+" "+day+" "+hour+" "+minite+" "+miao+
						    				" ����"+type+" �෽��Ϊ"+classfun+" �������Ϊ"+infomation);
						    	}
						    }
						    catch(ParseException e)
						    {
						    	errorinfile ex = new errorinfile();
						    	ex.errorinfile1();
						    }
						    
						    
						   
						} catch (NumberFormatException e) {
							System.out.println("m");
							IOException y  =new IOException();
							y.initCause(e);
						    throw y;
						}
							
						
					}
					else
					{
						System.out.println("δ���ո�ʽ����");
						throw new IOException();
					}
					break;
				case 2:
					if(line.contains(input))
					{
						System.out.println("ʱ��Ϊ"+year+" "+month+" "+day+" "+hour+" "+minite+" "+miao+
			    				" ����"+input+" �෽��Ϊ"+classfun+" �������Ϊ"+infomation);
					}
					
					break;
				case 3:
					if(line.contains(input1))
					{
						System.out.println("ʱ��Ϊ"+year+" "+month+" "+day+" "+hour+" "+minite+" "+miao+
			    				" ����"+type+" �෽��Ϊ"+classfun+" �������Ϊ"+infomation);
					}
					
					break;
				case 4:
					if(line.contains(input2))
					{
						System.out.println("ʱ��Ϊ"+year+" "+month+" "+day+" "+hour+" "+minite+" "+miao+
			    				" ����"+type+" �෽��Ϊ"+classfun+" �������Ϊ"+infomation);
					}
					
					break;
				case 5:
					if(line.contains(input3))
					{
						System.out.println("ʱ��Ϊ"+year+" "+month+" "+day+" "+hour+" "+minite+" "+miao+
			    				" ����"+type+" �෽��Ϊ"+classfun+" �������Ϊ"+infomation);
					}
					break;
					
				default:
					System.out.println("���벻�Ϸ�������������");
					break;
				}
			}
			
		}
		catch (IOException e) {
        errorinfile ex = new errorinfile();
        ex.errorinfile1();
    }
		
		
				
	}
}
