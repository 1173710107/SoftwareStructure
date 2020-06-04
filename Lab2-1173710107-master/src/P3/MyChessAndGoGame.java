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
		System.out.println("1.\t将尚未在棋盘上的一颗棋子放在棋盘上的指定位置");
		System.out.println("2.\t移动棋盘上的某个位置的棋子至新的位置");
		System.out.println("3.\t提子（移除对手棋子）");
		System.out.println("4.\t吃子（使用己方棋子吃掉对手棋子）");
		System.out.println("5.\t查询某个位置的占用情况");
		System.out.println("6.\t计算两个玩家分别在棋盘上的棋子总数");
		System.out.println("7.\t跳过");
		System.out.println("end.\t 结束操作");
	}
	
	public void gameMain() {
		String[] splitItems;
		GameType.add("chess");
		GameType.add("go");
		int num = -1;
			while(true) {
				System.out.println("请输入游戏类型(chess or go)");
				  Scanner scan = new Scanner(System.in);
				  String read = scan.nextLine().toLowerCase();
				if(GameType.contains(read)) {//判断是否包含
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
					System.out.println("输入游戏类型错误，请按照给定类型输入！");
				}
			}
			 Scanner scan = new Scanner(System.in);
			System.out.println("[用户A]\t请输入您的名称");
			player1name = scan.nextLine();
			System.out.println("[用户B]\t请输入您的名称");
			player2name = scan.nextLine();
			game.initgameplayer(player1name,player2name,num);
			players.add(game.getplayer(0));//添加玩家
			players.add(game.getplayer(1));
			System.out.println(String.format("%s，%s，游戏开始，请玩家按照顺序操作！", player1name,player2name));
			
			int count=0;
			while(true) {
				System.out.println(); 
				System.out.println(game.getplayername(count)+":");
				printMenu();//输出选择菜单
				 String answer = scan.nextLine();
				boolean exitFlag =false;
				switch(answer) {
				
					case "1":
					
//						将尚未在棋盘上的一颗棋子放在棋盘上的指定位置
						System.out.println("请"+game.getplayername(count)+"输入棋子名称和坐标(pieceName,edX,edY)按照上面格式：");
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
								if(game.isavailable(px, py))//判断该坐标是否可以用
								{
									game.addPiece(piece, count);//添加棋子
									game.setPiece(px, py, piece);//放到棋盘上
								}
								else
								{
									System.out.println("输入位置已经有棋子了，请重新输入！！");
									continue;
								}
								count = (count+1)%2;//将玩家变换，下面同
								System.out.println("添加棋子成功！！！");
					
							}
						else {
							System.out.println("输入不符合规则，请重新输入！！");
							continue;
						}
						break;
					case "2":
//						移动棋盘上的某个位置的棋子至新的位置
						System.out.println("输入(stX,stY,edX,edY)：");
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
							System.out.println("输入错误，请重新输入!!!");
							continue;
						}
						break;
					case "3":
//						提子
						System.out.println("输入(X,Y)：");
						String answer3=scan.nextLine();
						splitItems = answer3.split(",");
						if(splitItems.length==2) {
								int px=Integer.valueOf(splitItems[0]);
								int py=Integer.valueOf(splitItems[1]);
								game.removePiece(count, px,py);
								count = (count+1)%2;
								System.out.println("[SUCCESS]");
						} else {
							System.out.println("输入不符合规则，请重新输入");
							continue;
						}
						break;
					case "4":
//						吃子
						System.out.println("输入(stX,stY,edX,edY)：");
						String answer4=scan.nextLine();
						splitItems = answer4.split(",");
						if(splitItems.length==4) {//判断是不是两个坐标
								int stX=Integer.valueOf(splitItems[0]),
								stY=Integer.valueOf(splitItems[1]),
								edX=Integer.valueOf(splitItems[2]),
								edY=Integer.valueOf(splitItems[3]);
								game.eatPiece(count, stX, stY, edX, edY);
								count = (count+1)%2;
								System.out.println("吃子成功");
						} else {
							System.out.println("输入不符合规则，请重新输入");
							continue;
						}
						break;
					case "5":
						//查询某个位置的占用情况
						System.out.println("输入(edX,edY)：");
						String answer5=scan.nextLine();
						splitItems = answer5.split(",");
						if(splitItems.length==2) {
								int px=Integer.valueOf(splitItems[0]);
								int py=Integer.valueOf(splitItems[1]);
								if(game.getpstate(px, py)==0||game.getpstate(px, py)==2) {//该位置棋子被吃掉或者未放置棋子
									System.out.println("该位置没有棋子");
								} else {
									Piece1 piece = game.getPiece(px, py);
									System.out.println(String.format("该位置为 %s 的 %s 棋子",
											game.getpfrom(piece),game.getpname(px,py)));
								}
								
						} else {
							System.out.println("输入错误，请重新输入");
							continue;
						}
						break;
					case "6":
//						计算两个玩家分别在棋盘上的棋子总数
						System.out.println(String.format("玩家\t%s\t在棋盘上的棋子总数为%d", 
								game.getplayername(count),game.getnumPiece(count)));
						System.out.println(String.format("玩家\t%s\t在棋盘上的棋子总数为%d", 
								game.getplayername((count+1)%2),game.getnumPiece((count+1)%2)));
						break;
					case "7":
//						跳过
						System.out.println("当前玩家跳过操作");
						count=(count+1)%2;
						break;
					case "end":
//						结束游戏操作
						System.out.println("游戏结束！！！");
						exitFlag = true;
						break;
					default:
						System.out.println("输入错误，请重新输入");
						break;
				}
				if(exitFlag) break;
			}
//	输出下棋的历史记录
			scan.close();
			count = 0;
		for(count=0;count<2;count++) {
					System.out.println();
					System.out.println(String.format("%s的操作历史记录:",game.getplayername(count)));
					System.out.println(game.getplayerhistory(count));
					
		}
		}
	
	public static void main(String[] args) {
		new MyChessAndGoGame().gameMain();
	}
 
}