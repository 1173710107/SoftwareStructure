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
		System.out.println("��ѡ��Ӧ��?");
		System.out.println("1.TrackGame");
		System.out.println("2.AtomStructure");
		System.out.println("3.SocialNetworkCircle");
		type = in.nextInt();
		in.nextLine();
		if(type==1)
		{
			TrackGame game = new TrackGame();
			System.out.println("���ڶ�ȡ TrackGame.txt �е���Ϣ");
			path = ("txt/TrackGame.txt");
			String info = ConcreteCircularOrbit.readFile(path);
			game.Analysis(info);
			while(true)
			{
				System.out.println("��ѡ�����湦��?");
				System.out.println("1.���ӻ�");
				System.out.println("2.���ӹ��");
				System.out.println("3.ɾ�����");
				System.out.println("4.��ֵ");
				System.out.println("5.�������");
				System.out.println("6.���ճɼ�����");
				System.out.println("7.�ı���");
				System.out.println("8.�˳�");
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
						System.out.println("��������Ҫ�ƶ����˶�Ա������");
						String name = in.nextLine();
						Athlete temp = new Athlete(name,0,"CHN",0,0.00);
						System.out.println("��������Ҫ�ƶ�����λ��?");
						int tar = in.nextInt();
						in.nextLine();
						game.transit(temp, tar);
						break;
					case 8:
						break;
					default:
						System.out.println("�밴��ʽ���룡��");
				}
				
			}	
		}
		else if(type==2)
		{
			System.out.println("���ڶ�ȡ AtomicStructure.txt �ļ�����Ϣ");
			path = ("txt/AtomicStructure.txt");
			AtomStructure atom = new AtomStructure(path);
			while(true)
			{
				System.out.println("��ѡ������Ĺ���?");
				System.out.println("1.���ӻ�");
				System.out.println("2.���ӹ��");
				System.out.println("3.ɾ�����");
				System.out.println("4.������ֵ");
				System.out.println("5.�ı���");
				System.out.println("6.�˳�");
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
					System.out.println("���ĸ������ʼ�ƶ�?");
					int source = in.nextInt();
					in.nextLine();
					System.out.println("�ƶ����Ǹ������?");
					int tar = in.nextInt();
					in.nextLine();
					atom.transitElectron(source, tar);
					break;
				case 6:
					break;
				default:
					System.out.println("�밴��ʽ���룡��");
				}
				
			}	
		}
		else if (type == 3)
		{
			System.out.println("���ڶ���  SocialNetworkCircle.txt �ļ�");
			path = ("txt/SocialNetworkCircle.txt");
			SocialNetworkCircle circle = new SocialNetworkCircle(path);
			while(true)
			{
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
						System.out.println("��������Ҫ�鿴���û������֣�");
						String source = in.nextLine();
						System.out.println(circle.onWhichTrack(source));
						break;
					case 6:
						System.out.println("��������Ҫ�鿴���û������֣�");
						String source1 = in.nextLine();
						System.out.println(circle.expansion(source1));
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
						break;
					case 8:
						System.out.println("��������Ҫɾ����ϵ����ʼ�û�");
						String source3 = in.nextLine();
						System.out.println("��������Ҫɾ����ϵ���յ��û�");
						String target1 = in.nextLine();
						circle.removeEdge(source3, target1);
						break;
					case 9:
						circle.refresh();
						break;
					case 10:
						System.out.println("��������Ҫ��ȡ�������ʼ�û�");
						String source4= in.nextLine();
						System.out.println("��������Ҫ��ȡ������յ��û�");
						String target2 = in.nextLine();
						System.out.println(circle.getLogicalDistance(source4, target2));
						break;
					case 11:
						break;
					default:
						System.out.println("�밴��ʽ���룡��");
				}
			
			}	
		}
	}

}
