package applications;
import circularOrbit.*;
import physicalObject.*;
import java.util.Scanner;

import APIs.*;

public class Main {
	public static void main(String[] args) {
		int type;
		int choice;
		String path;
		Scanner in = new Scanner(System.in);
		System.out.println("请选择应用?");
		System.out.println("1.TrackGame");
		System.out.println("2.AtomStructure");
		System.out.println("3.SocialNetworkCircle");
		type = in.nextInt();
		in.nextLine();
		if(type==1)
		{
			TrackGame game = new TrackGame();
			System.out.println("正在读取 TrackGame.txt 中的信息");
			path = ("txt/TrackGame.txt");
			String info = ConcreteCircularOrbit.readFile(path);
			game.Analysis(info);
			while(true)
			{
				System.out.println("请选择下面功能?");
				System.out.println("1.可视化");
				System.out.println("2.增加轨道");
				System.out.println("3.删除轨道");
				System.out.println("4.熵值");
				System.out.println("5.随机排序");
				System.out.println("6.按照成绩排序");
				System.out.println("7.改变轨道");
				System.out.println("8.退出");
				choice = in.nextInt();
				in.nextLine();
				switch(choice)
				{
					case 1:
						System.out.println(game);
						CircularOrbitHelper.visualize(game.numOnEachTrack());
						break;
					case 2:
						game.newTrack();
						break;
					case 3:
						game.clearTrack();
						break;
					case 4:
						System.out.println(CircularOrbitAPIs.getObjectDistributionEntropy(game.numOnEachTrack()));
						break;
					case 5:
						game.emptyAll();
						game.shuffleArrange();
						System.out.println(game);
						break;
					case 6:
						game.emptyAll();
						game.sortedArrange();
						System.out.println(game);
						break;
					case 7:
						System.out.println("请输入需要移动的运动员的名字");
						String name = in.nextLine();
						Athlete temp = new Athlete(name,0,"CHN",0,0.00);
						System.out.println("请输入需要移动到的位置?");
						int tar = in.nextInt();
						in.nextLine();
						game.transit(temp, tar);
						break;
					case 8:
						break;
					default:
						System.out.println("请按格式输入！！");
				}
				
			}	
		}
		else if(type==2)
		{
			System.out.println("正在读取 AtomicStructure.txt 文件的信息");
			path = ("txt/AtomicStructure.txt");
			AtomStructure atom = new AtomStructure(path);
			while(true)
			{
				System.out.println("请选择下面的功能?");
				System.out.println("1.可视化");
				System.out.println("2.增加轨道");
				System.out.println("3.删除轨道");
				System.out.println("4.计算熵值");
				System.out.println("5.改变轨道");
				System.out.println("6.退出");
				choice = in.nextInt();
				in.nextLine();
				switch(choice)
				{
				case 1:
					System.out.println(atom);
					CircularOrbitHelper.visualize(atom.numOnEachTrack());
					break;
				case 2:
					atom.newTrack();
					break;
				case 3:
					atom.clearTrack();
					break;
				case 4:
					System.out.println(CircularOrbitAPIs.getObjectDistributionEntropy(atom.numOnEachTrack()));
					break;
				case 5:
					System.out.println("从哪个轨道开始移动?");
					int source = in.nextInt();
					in.nextLine();
					System.out.println("移动到那个轨道？?");
					int tar = in.nextInt();
					in.nextLine();
					atom.transitElectron(source, tar);
					break;
				case 6:
					break;
				default:
					System.out.println("请按格式输入！！");
				}
				
			}	
		}
		else if (type == 3)
		{
			System.out.println("正在读入  SocialNetworkCircle.txt 文件");
			path = ("txt/SocialNetworkCircle.txt");
			SocialNetworkCircle circle = new SocialNetworkCircle(path);
			while(true)
			{
				System.out.println("请选择下面功能：");
				System.out.println("1.可视化");
				System.out.println("2.增加轨道");
				System.out.println("3.删除轨道");
				System.out.println("4.计算熵值");
				System.out.println("5.查找某个用户在哪个轨道");
				System.out.println("6.查看某个用户具体有多少朋友");
				System.out.println("7.增加关系");
				System.out.println("8.删除关系");
				System.out.println("9.刷新");
				System.out.println("10.计算两个用户之间的距离");
				System.out.println("11.退出");
				choice = in.nextInt();
				in.nextLine();
				switch(choice)
				{
					case 1:
						System.out.println(circle);
						CircularOrbitHelper.visualize(circle);
						break;
					case 2:
						circle.newTrack();
						break;
					case 3:
						circle.clearTrack();
						break;
					case 4:
						System.out.println(CircularOrbitAPIs.getObjectDistributionEntropy(circle.numOnEachTrack()));
						break;
					case 5:
						System.out.println("请输入需要查看的用户的名字：");
						String source = in.nextLine();
						System.out.println(circle.onWhichTrack(source));
						break;
					case 6:
						System.out.println("请输入需要查看的用户的名字：");
						String source1 = in.nextLine();
						System.out.println(circle.expansion(source1));
						break;
					case 7:
						System.out.println("请输入需要建立关系的起始用户");
						String source2 = in.nextLine();
						System.out.println("请输入需要建立关系的终点用户");
						String target = in.nextLine();
						System.out.println("权重是?");
						Double val = in.nextDouble();
						in.nextLine();
						int ival = (int)(val*1000);
						circle.addEdge(source2, target, ival);
						break;
					case 8:
						System.out.println("请输入需要删除关系的起始用户");
						String source3 = in.nextLine();
						System.out.println("请输入需要删除关系的终点用户");
						String target1 = in.nextLine();
						circle.removeEdge(source3, target1);
						break;
					case 9:
						circle.refresh();
						break;
					case 10:
						System.out.println("请输入需要获取距离的起始用户");
						String source4= in.nextLine();
						System.out.println("请输入需要获取距离的终点用户");
						String target2 = in.nextLine();
						System.out.println(circle.getLogicalDistance(source4, target2));
						break;
					case 11:
						break;
					default:
						System.out.println("请按格式输入！！");
				}
			
			}	
		}
	}

}
