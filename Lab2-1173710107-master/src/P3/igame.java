package P3;

import java.util.HashSet;
import java.util.Set;

public class igame implements P3game {
	Set<Piece1> pieces = new HashSet<>();
	Game1 game = new Game1();
	@Override
	public Player1 getplayer(int num)//��ȡ��Ӧplayer
	{
		return game.getplayer(num);
	}

	@Override
	public void movePiece(int num, int startx,int starty,int endx,int endy) {//�ƶ�����
		Piece1 x1 = game.getPiece(startx, starty);
		//System.out.println(x1);
		if(x1.getfrom().equals(game.getPlayerName(num)))
		{
			Piece1 x = game.getPiece(startx, starty);
			x.setPx(endx);
			x.setPy(endy);
			Piece1 board[][] = game.getBoardPiece();
			Piece1 l= new Piece1();
			board[startx][starty] = l;
			board[endx][endy] = x;
			String step = game.getPlayerName(num)+" move "+game.getPname(endx, endy)+" from "+startx+" "+starty+" to "+endx+" "+endy; 
			game.addhistory(num, step);
		}
		else
		{
			System.out.println("��ʼ�㲻�Ǹ���ҵ����ӣ��ƶ�����ʧ�ܣ���");
		}
		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePiece(int num, int endx,int endy) {//ȥ������
		// TODO Auto-generated method stub
		Piece1 x = game.getPiece(endx, endy);
		if(x.getfrom().equals(game.getPlayerName(num)))
		{
			game.deletepiece(num,endx, endy);
			if(num==0)
			{
				String step = game.getPlayerName(num)+" has remove "+game.getPname(endx, endy)+" at "+endx+" "+endy;
				Piece1 piece = getPiece(endx,endy);
				Set<Piece1> pieces =game.playerPiece(0);
				pieces.remove(piece);
				Piece1[][] piece2  =game.getBoardPiece();
				Piece1 empty = new Piece1();
				piece2[endx][endy] = empty;
				game.addhistory(num, step);

			}
			else if(num==1)
			{
				String step = game.getPlayerName(num)+" has remove "+game.getPname(endx, endy)+" at "+endx+" "+endy;
				Piece1 piece = getPiece(endx,endy);
				Set<Piece1> pieces =game.playerPiece(1);
				pieces.remove(piece);
				Piece1[][] piece2  =game.getBoardPiece();
				Piece1 empty = new Piece1();
				piece2[endx][endy] = empty;
				game.addhistory(num, step);
			}
			else
			{
				System.out.println("�Ҳ�����������Ϣ����");
				System.exit(0);
			}

		}
		else
		{
			System.out.println("������㲻�Ǹ���ҵ����ӣ�ȥ������ʧ�ܣ���");
		}
		
		
	}

	@Override
	public void eatPiece(int num, int startx,int starty,int endx,int endy) {//����
		// TODO Auto-generated method stub
		Piece1 x = game.getPiece(startx, starty);
		if(x.getfrom().equals(game.getPlayerName(num)))
		{
			removePiece((num+1)%2,endx,endy);
			movePiece(num,startx,starty,endx,endy);
		}
		else
		{
			System.out.println("��ʼ�㲻�Ǹ���ҵ����ӣ�����ʧ�ܣ���");
		}
		

	}

	@Override
	public Piece1 getPiece(int x, int y) {//��ȡ���̸�λ������
		// TODO Auto-generated method stub
		Piece1 board[][] = game.getBoardPiece();
		return board[x][y]; 
	}

	@Override
	public void setPiece(int x, int y, Piece1 piece) {//�������ӵ�������
		Piece1 board[][] = game.getBoardPiece();
		piece.setPx(x);
		piece.setPy(y);
		board[x][y] = piece;
		
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isavailable(int x, int y) {//�жϸ������Ƿ����
		if(game.getboardtype()==0)//����
		{
			if(x>=0&&x<8&&y>=0&&y<8)
			{
				Piece1 broad[][] = game.getBoardPiece();
				if(broad==null||broad.length==0||broad[x][y]==null)
				{
					return true;
				}
				if(broad[x][y].getPiecestate()==0)
				{
					return true;
				}
				return false;
			}
			else
			{
				return false;
			}
		}
		else
		{
			if(x>=0&&x<19&&y>=0&&y<19)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isinborad(Piece1 piece) {//�жϸ������Ƿ���������
		// TODO Auto-generated method stub
		if(piece.getPiecestate()==0||piece.getPiecestate()==2)
		{
			return false;
		}
		else 
		{
			return true;
		}
	}

	@Override
	public int getplayernum(Player1 player) {//��ȡ����ҵ���������
		return player.playerpiece().size();
		// TODO Auto-generated method stub
	}

	@Override
	public void initgameplayer(String name1,String name2,int num) {//��ʼ����Ϸ
		// TODO Auto-generated method stub
		game.setplayername(name1, 0);
		game.setplayername(name2, 1);
		if(num==0)//chess
		{
			game.setgametype("chess");
		}
		else if(num==1)
		{
			game.setgametype("go");
		}
		else
		{
			System.out.println("��ȡ������Ϣʧ�ܣ�����");
			System.exit(0);
		}
		

	}

	@Override
	public Player1 getPositionplayer(Position1 x) {//��ȡ���̸�λ�õ����ӵ����
		// TODO Auto-generated method stub
		Piece1 piece = getPiece(x(x),y(x));
		String name = piece.getPname();
		if(game.getPlayerName(0).equals(name))
		{
			return game.getplayer(0);
		}
		else
		{
			return game.getplayer(1);
		}
	}
	

	@Override
	public Piece1 getPositionPiece(Position1 x) {//��ȡ��λ�õ�����
		// TODO Auto-generated method stub
		return  getPiece(x(x),y(x));
	
	}

	@Override
	public int getnumPiece(int num) {//��ȡ�����������
		// TODO Auto-generated method stub
		return game.getpiecenum(num);
	}

	@Override
	public String getplayername(int num) {//��ȡ�������
		// TODO Auto-generated method stub
		return game.getPlayerName(num);
	}

	@Override
	public Set<Piece1> getplayerPieces(int num) {//��ȡ��ҵ����ӵļ���
		// TODO Auto-generated method stub
		
		return game.getplayerpieces(num);
	}

	@Override
	public StringBuilder getplayerhistory(int num) //��ø���ҵ���ʷ����
	{
		// TODO Auto-generated method stub
		return game.getstringbuilder(num);
	}

	@Override
	public boolean addPiece(Piece1 x,int num) {//��д
			Piece1 board[][] = game.getBoardPiece();
			x.setPiecestate(1);;
			board[x.getPx()][x.getPy()] = x;
		//	System.out.println("^^"+x.getPname()+game.getPlayerName(0)+game.getPlayerName(1));
			if(game.getPlayerName(0).equals(x.getfrom()))
			{
				System.out.println("@@@");
				return game.addplayerpiece(0, x);
			}
			else if(game.getPlayerName(1).equals(x.getfrom()))
			{
				System.out.println("!!!");
				return game.addplayerpiece(1, x);
			}
			else
			{
				return false;
			}
			
	
		// TODO Auto-generated method stub
	}

	@Override
	public boolean iscontainPiece(int num,Piece1 x) {//�жϸ�����Ƿ��и�����
		// TODO Auto-generated method stub
		return game.iscontainPiece(num, x);
	}

	@Override
	public void addhistory(int num,String step) {//������ʷ��¼
		// TODO Auto-generated method stub
		game.addhistory(num, step);

	}
	@Override
	public void setpfrom(String from,Piece1 piece)//�������������ĸ����
	{
		game.setpfrom(from,piece);
	}
	@Override
	public String getpname(int px,int py) {//��ȡ��λ�õ����ӵ�
		// TODO Auto-generated method stub
		if(game.getPiece(px, py)==null)
		{
			System.out.println("��λ��û�����ӣ�����");
			return null;
		}
		return game.getPname(px,py);
	}

	@Override
	public int getpx(Piece1 piece) {//��ȡ�����ӵĺ�����
		return game.getpx(piece);
		// TODO Auto-generated method stub
	}

	@Override
	public int getpy(Piece1 piece) {//��ȡ�����ӵ�������
		// TODO Auto-generated method stub
		return game.getpy(piece);
	}

	@Override
	public int getpstate(int px,int py) {//��ȡ�����ӵķ���״̬
		// TODO Auto-generated method stub
		return game.getPiecestate(px,py);
	}
	@Override
	public void setplayername(String name,int num)//������ҵ�name
	{
		game.setplayername(name,num);
	}
	@Override
	public void setpname(String pname,Piece1 piece) {//��ȡ�����ӵ�name
		game.setPname(pname,piece);
		// TODO Auto-generated method stub

	}

	@Override
	public void setpx(int px,Piece1 piece) {//�������ӵ�x
		// TODO Auto-generated method stub
		game.setPx(px,piece);
	}

	@Override
	public void setpy(int py,Piece1 piece) {//�������ӵ�y
		// TODO Auto-generated method stub
		piece.setPy(py);
	}

	@Override
	public void remove(Piece1 x,int num) {//�Ƴ�����
		if(x.getfrom().equals(game.getPlayerName(num)))
		game.remove(x, num);
		else
		{
			System.out.println("�����Ӳ��Ǹ���ҵģ�����ɾ��ʧ�ܣ���");
		}
		// TODO Auto-generated method stub

	}

	@Override
	public int x(Position1 position) {//��ȡposition��x
		// TODO Auto-generated method stub
		return game.getpositionx(position);
	}

	@Override
	public int y(Position1 position) {//��ȡposition��y
		// TODO Auto-generated method stub
		return game.getpositiony(position);
	}
	@Override
	public boolean equals(Position1 x, Position1 y) {//�ж�����position�Ƿ���ͬ
		// TODO Auto-generated method stub
		if(x(x)==x(y)&&y(x)==y(y))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	@Override
	public String getpfrom(Piece1 piece) {//��ȡ���������ĸ����
		// TODO Auto-generated method stub
		return null;
	}

}
class Position1
{
	private int x=-1;
	private int y=-1;
	public int getpositionx() {
		return x;
	}
	public int getpositiony()
	{
		return y;
	}
}
class Piece1
{
	private int piecestate = 0;
	private String pname = new String();
	private String from=new String();
	public int getPiecestate() {
		return piecestate;
	}
	public void setPiecestate(int piecestate) {
		this.piecestate = piecestate;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPx() {
		return px;
	}
	public void setPx(int px) {
		this.px = px;
	}
	public int getPy() {
		return py;
	}
	public void setPy(int py) {
		this.py = py;
	}
	public String getfrom()
	{ 
		return from;
	}
	public void setfrom(String from)
	{
		this.from = from;
	}
	private int px =-1;
	private int py = -1;
}
class Player1
{
	private String playerName;
	private StringBuilder gamehistory = new StringBuilder();
	private Set<Piece1> playerPieces = new HashSet<>();
	public void deletepiece(int endx,int endy)
	{
		for(Piece1 m:playerPieces)
		{
			if(m.getPx()==endx&&m.getPy()==endy)
			{
				playerPieces.remove(m);
			}
		}
	}
	public String name()
	{
		return playerName;
	}
	public Set<Piece1> playerpiece()
	{
		return playerPieces;
	}
	public StringBuilder getplayerStringBuilder()
	{
		return gamehistory;
	}
	public boolean iscontain(Piece1 x)
	{
		return playerPieces.contains(x);
	}
	public void addhistory(String step)
	{
		gamehistory.append(step);
	}
	public void removepiece(Piece1 piece)
	{
		playerPieces.remove(piece);
	}
	public void setplayername(String name)
	{
		this.playerName = name;
	}
	public int getnum()
	{
		return playerPieces.size();
	}
	public void addpiece(Piece1 piece)
	{
		playerPieces.add(piece);
	}
}
class Board1
{
	private int boardtype = -1;
	private int boardsize = -1;
	private Piece1 boardPiece[][] = new Piece1[20][20];
	public Piece1[][] boardPiece()
	{
		return boardPiece;
	}
	public int getboardtype()
	{
		return boardtype;
	}
	public int getboardsize()
	{
		return boardsize;
	}
	public void setboardsizeandtype(String type)
	{
		if(type.toLowerCase().equals("chess"))
		{
			this.boardtype = 0;//chess
			this.boardsize = 8;
		}
		else
		{
			this.boardtype = 1;//go
			this.boardsize = 19;
		}
	}

}
/*class Action1
{
	private Board1 gameboard = new Board1();
	private Player1 player1= new Player1();
	private Player1 player2 = new Player1();
	
}*/
class Game1
{
	private String gametype = new String();
	private Board1 gameboard = new Board1();
//	private Action1 gameaction = new Action1();
	private Player1 player1 = new Player1();
	private Player1 player2 = new Player1();
	public void setgametype(String type)
	{
		this.gametype = type;
		gameboard.setboardsizeandtype(type);
	}
	public Piece1[][] getBoardPiece()
	{
		return gameboard.boardPiece();
	}
	public Player1 getplayer(int x)
	{
		if(x==0)
		{
			return player1;
		}
		else
		{
			return player2;
		}
	}
	public String getPlayerName(int x)
	{
		if(x==0)
		{
			return player1.name();
		}
		else if(x==1)
		{
			return player2.name();
		}
		else
		{
			System.out.println("δ�ҵ������Ϣ������");
			System.exit(0);
			return null;
		}
	}
	public Set<Piece1> playerPiece(int num)
	{
		if(num==0)
		{
			return player1.playerpiece();
		}
		else if(num==1)
		{
			return player2.playerpiece();
		}
		else
		{
			System.out.println("δ�ҵ���ǰ�����Ϣ����");
			System.exit(0);
			return null;
		}
	}
	public int getboardtype()
	{
		return gameboard.getboardtype();
	}
	public Set<Piece1> getplayerpieces(int num)
	{
		if(num==0)
		{
			return player1.playerpiece();
		}
		else
		{
			return player2.playerpiece();
		}
	}
	public StringBuilder getstringbuilder(int num)
	{
		if(num==0)
		{
			return player1.getplayerStringBuilder();
		}
		else
		{
			return player2.getplayerStringBuilder();
		}
	}
	public boolean addplayerpiece(int num,Piece1 piece)
	{
		if(num==0)
		{
			player1.addpiece(piece);
			String history = player1.name()+" add "+piece.getPname()+" at "+piece.getPx()+" "+piece.getPy();
			player1.addhistory(history);
			return true;
		}
		else if(num==1)
		{
			player2.addpiece(piece);
			String history = player2.name()+" add "+piece.getPname()+" at "+piece.getPx()+" "+piece.getPy();
			player2.addhistory(history);
			return true;
		}
		else
		{
			System.out.println("δ�ҵ���������Ϣ����");
			System.exit(0);
			return false;
		}
		
	}
	public boolean iscontainPiece(int num,Piece1 x)
	{
		if(num==0)
		{
			return player1.iscontain(x);
		}
		else if(num==1)
		{
			return player2.iscontain(x);
		}
		else 
		{
			System.out.println("δ���ҵ���ǰ�����Ϣ!!");
			System.exit(0);
			return false;
		}
	}
	public void addhistory(int num,String step)
	{
		if(num==0)
		{
			player1.addhistory(step);
		}
		else if(num==1)
		{
			player2.addhistory(step);
		}
	}
	public Piece1 getPiece(int x,int y)
	{
		Piece1 board[][] = gameboard.boardPiece();
		return board[x][y];
	}
	public void remove(Piece1 piece,int num)
	{
		Piece1 board[][]  = gameboard.boardPiece();
		Piece1 piece2 = new Piece1();
		board[piece.getPx()][piece.getPy()] = piece2;
		if(num==0)
		{
			player1.removepiece(piece);;
		}
		else if(num==1)
		{
			player2.removepiece(piece);
		}
		else
		{
			System.out.println("�Ҳ�����ǰ�����Ϣ����");
			System.exit(0);
		}
	}
	public String getPname(int px,int py)
	{
		Piece1 gameborad1[][] = gameboard.boardPiece();
		
		return gameborad1[px][py].getPname();
	}
	public int getpx(Piece1 piece)
	{
		return piece.getPx();
	}
	public int getpy(Piece1 piece)
	{
		return piece.getPy();
	}
	public int getPiecestate(int px,int py)
	{
		Piece1 board[][] = gameboard.boardPiece();
		
		return board[px][py].getPiecestate();
	}
	public void setPname(String Pname,Piece1 piece)
	{
		piece.setPname(Pname);
	}
	public void setPx(int px,Piece1 piece)
	{
		piece.setPx(px);
	}
	public void setPy(int py,Piece1 piece)
	{
		piece.setPy(py);
	}
	public int getpositionx(Position1 position)
	{
		return position.getpositionx();
	}
	public int getpositiony(Position1 position)
	{
		return  position.getpositiony();
	}
	public void setplayername(String name,int num)
	{
		if(num==0)
		{
			player1.setplayername(name);
		}
		else if(num==1)
		{
			player2.setplayername(name);
		}
		else
		{
			System.out.println("δ�ҵ���ǰ�����Ϣ����");
			System.exit(0);
		}
	}
	public int  getpiecenum(int num)
	{
		if(num==0)
		{
			return player1.getnum();
		}
		else if(num==1)
		{
			return player2.getnum();
		}
		else
		{
			System.out.println("δ�ҵ���������Ϣ����");
			return 0;
		}
	}
	public void setpfrom(String from,Piece1 piece)
	{
		piece.setfrom(from);
	}
	public void deletepiece(int num,int endx,int endy)
	{
		if(num==0)
		{
			player1.deletepiece(endx, endy);
		}
		else if(num==1)
		{
			player2.deletepiece( endx, endy);
		}
	}
	
	
}