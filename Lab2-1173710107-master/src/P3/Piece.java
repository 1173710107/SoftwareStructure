package P3;

public interface Piece {
	/*
	 * 该方法主要用于获取当前棋子的name
	 * @Param 需要获取name的棋子piece的xy
	 * @return 返回该棋子的name 类型为String
	 */
	public String getpname(int px,int py);
	/*
	 * 该方法主要用于获取该棋子所属玩家的名字
	 * @Param参数为该棋子
	 * @return返回值为玩家名字
	 */
	public String getpfrom(Piece1 piece);
	/*
	 * 
	 * 该方法主要是设置棋子的所属玩家
	 * @Param参数from为玩家的名字，piece为设置的棋子
	 */
	public void setpfrom(String from,Piece1 piece);
	/*
	 * 该方法是获取棋子在棋盘上的x坐标
	 * @Param 棋子piece
	 * @return 棋盘上的坐标x
	 */
	public int getpx(Piece1 piece);
	/*
	 * 该方法是获取棋子在棋盘上的y坐标
	 * @Param 棋子piece
	 * @return 棋盘上的坐标y
	 */
	public int getpy(Piece1 piece);
	/*
	 * 该方法用于获取棋子的状态，0表示未放置，1表示被放置在棋盘上，2表示被吃掉弃用
	 * @Param 	棋子piece
	 * @return 该棋子的当前状态int类型
	 */
	public int getpstate(int px,int py);
	/*
	 * 该方法用于设置棋子的name
	 * @Param 棋子piece以及name
	 */
	public void setpname(String pname,Piece1 piece);
	/*
	 * 该方法用于设置棋子在棋盘上的x坐标
	 * @Param 棋子在棋盘上x位置px,棋子piece
	 */
	public void setpx(int px,Piece1 piece);
	/*
	 * 该方法用于设置棋子在棋盘上的y坐标
	 * @Param 棋子在棋盘上x位置py,棋子piece
	 */
	public void setpy(int py,Piece1 piece);
	/*
	 * 该方法用于删除棋盘上的棋子，num表示该棋子是哪个玩家的，num=0表示是玩家一的棋子num=1表示是玩家二的棋子
	 * @Param 需要删除的棋子piece以及玩家标志变量num
	 */
	public void remove(Piece1 x,int num);
}
