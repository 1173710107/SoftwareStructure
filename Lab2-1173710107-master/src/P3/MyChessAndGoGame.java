package P3;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
 
public class MyChessAndGoGame {

	igame game  =new igame();
	public Set<String> GameType=new HashSet<String>();
	public String player1name = new String(),player2name = new String();
	public ArrayList<Player1> players = new ArrayList<Player1>();
	
	public void printMenu() {
		System.out.println("1.\t����δ�������ϵ�һ�����ӷ��������ϵ�ָ��λ��");
		System.out.println("2.\t�ƶ������ϵ�ĳ��λ�õ��������µ�λ��");
		System.out.println("3.\t���ӣ��Ƴ��������ӣ�");
		System.out.println("4.\t���ӣ�ʹ�ü������ӳԵ��������ӣ�");
		System.out.println("5.\t��ѯĳ��λ�õ�ռ�����");
		System.out.println("6.\t����������ҷֱ��������ϵ���������");
		System.out.println("7.\t����");
		System.out.println("end.\t ��������");
	}
	
	public void gameMain() {
		String[] splitItems;
		GameType.add("chess");
		GameType.add("go");
		int num = -1;
			while(true) {
				System.out.println("��������Ϸ����(chess or go)");
				  Scanner scan = new Scanner(System.in);
				  String read = scan.nextLine().toLowerCase();
				if(GameType.contains(read)) {//�ж��Ƿ����
					if(read.toLowerCase().equals("chess"))
					{
						num = 0;
					}
					else
					{
						num = 1;
					}
					break;
				} else {
					System.out.println("������Ϸ���ʹ����밴�ո����������룡");
				}
			}
			 Scanner scan = new Scanner(System.in);
			System.out.println("[�û�A]\t��������������");
			player1name = scan.nextLine();
			System.out.println("[�û�B]\t��������������");
			player2name = scan.nextLine();
			game.initgameplayer(player1name,player2name,num);
			players.add(game.getplayer(0));//������
			players.add(game.getplayer(1));
			System.out.println(String.format("%s��%s����Ϸ��ʼ������Ұ���˳�������", player1name,player2name));
			
			int count=0;
			while(true) {
				System.out.println(); 
				System.out.println(game.getplayername(count)+":");
				printMenu();//���ѡ��˵�
				 String answer = scan.nextLine();
				boolean exitFlag =false;
				switch(answer) {
				
					case "1":
					
//						����δ�������ϵ�һ�����ӷ��������ϵ�ָ��λ��
						System.out.println("��"+game.getplayername(count)+"�����������ƺ�����(pieceName,edX,edY)���������ʽ��");
						String answer1=scan.nextLine();
						splitItems = answer1.split(",");
						if(splitItems.length==3) {
								int px=Integer.valueOf(splitItems[1]);
								int py=Integer.valueOf(splitItems[2]);
								String pName=splitItems[0];
								Piece1 piece = new Piece1();
								game.setpname(pName, piece);
								game.setpx(px, piece);
								game.setpy(py, piece);
								game.setpfrom(game.getplayername(count), piece);
								if(game.isavailable(px, py))//�жϸ������Ƿ������
								{
									game.addPiece(piece, count);//�������
									game.setPiece(px, py, piece);//�ŵ�������
								}
								else
								{
									System.out.println("����λ���Ѿ��������ˣ����������룡��");
									continue;
								}
								count = (count+1)%2;//����ұ任������ͬ
								System.out.println("������ӳɹ�������");
					
							}
						else {
							System.out.println("���벻���Ϲ������������룡��");
							continue;
						}
						break;
					case "2":
//						�ƶ������ϵ�ĳ��λ�õ��������µ�λ��
						System.out.println("����(stX,stY,edX,edY)��");
						String answer2=scan.nextLine();
						splitItems = answer2.split(",");
						if(splitItems.length==4) {
								int stX=Integer.valueOf(splitItems[0]),
								stY=Integer.valueOf(splitItems[1]),
								edX=Integer.valueOf(splitItems[2]),
								edY=Integer.valueOf(splitItems[3]);
								game.movePiece(count,stX ,stY,edX, edY);
								count = (count+1)%2;	
						} else {
							System.out.println("�����������������!!!");
							continue;
						}
						break;
					case "3":
//						����
						System.out.println("����(X,Y)��");
						String answer3=scan.nextLine();
						splitItems = answer3.split(",");
						if(splitItems.length==2) {
								int px=Integer.valueOf(splitItems[0]);
								int py=Integer.valueOf(splitItems[1]);
								game.removePiece(count, px,py);
								count = (count+1)%2;
								System.out.println("[SUCCESS]");
						} else {
							System.out.println("���벻���Ϲ�������������");
							continue;
						}
						break;
					case "4":
//						����
						System.out.println("����(stX,stY,edX,edY)��");
						String answer4=scan.nextLine();
						splitItems = answer4.split(",");
						if(splitItems.length==4) {//�ж��ǲ�����������
								int stX=Integer.valueOf(splitItems[0]),
								stY=Integer.valueOf(splitItems[1]),
								edX=Integer.valueOf(splitItems[2]),
								edY=Integer.valueOf(splitItems[3]);
								game.eatPiece(count, stX, stY, edX, edY);
								count = (count+1)%2;
								System.out.println("���ӳɹ�");
						} else {
							System.out.println("���벻���Ϲ�������������");
							continue;
						}
						break;
					case "5":
						//��ѯĳ��λ�õ�ռ�����
						System.out.println("����(edX,edY)��");
						String answer5=scan.nextLine();
						splitItems = answer5.split(",");
						if(splitItems.length==2) {
								int px=Integer.valueOf(splitItems[0]);
								int py=Integer.valueOf(splitItems[1]);
								if(game.getpstate(px, py)==0||game.getpstate(px, py)==2) {//��λ�����ӱ��Ե�����δ��������
									System.out.println("��λ��û������");
								} else {
									Piece1 piece = game.getPiece(px, py);
									System.out.println(String.format("��λ��Ϊ %s �� %s ����",
											game.getpfrom(piece),game.getpname(px,py)));
								}
								
						} else {
							System.out.println("�����������������");
							continue;
						}
						break;
					case "6":
//						����������ҷֱ��������ϵ���������
						System.out.println(String.format("���\t%s\t�������ϵ���������Ϊ%d", 
								game.getplayername(count),game.getnumPiece(count)));
						System.out.println(String.format("���\t%s\t�������ϵ���������Ϊ%d", 
								game.getplayername((count+1)%2),game.getnumPiece((count+1)%2)));
						break;
					case "7":
//						����
						System.out.println("��ǰ�����������");
						count=(count+1)%2;
						break;
					case "end":
//						������Ϸ����
						System.out.println("��Ϸ����������");
						exitFlag = true;
						break;
					default:
						System.out.println("�����������������");
						break;
				}
				if(exitFlag) break;
			}
//	����������ʷ��¼
			scan.close();
			count = 0;
		for(count=0;count<2;count++) {
					System.out.println();
					System.out.println(String.format("%s�Ĳ�����ʷ��¼:",game.getplayername(count)));
					System.out.println(game.getplayerhistory(count));
					
		}
		}
	
	public static void main(String[] args) {
		new MyChessAndGoGame().gameMain();
	}
 
}