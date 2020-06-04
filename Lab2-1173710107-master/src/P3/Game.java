package P3;

public interface Game {
	/*
	 * 该方法主要用于初始化游戏，具体包括棋盘，玩家
	 * @Param 参数为两个player的名字以及游戏类型num ，当num=0时，表示是象棋游戏，当num=1时，表示是围棋游戏
	 */
	public void initgameplayer(String name1,String name2,int num);
	/*
	 * 该方法用于获取该位置对应的玩家
	 * @Param 获取棋盘位置position
	 * @return 该位置的玩家
	 */
	public Player1 getPositionplayer(Position1 x);
	/*
	 * 该方法用于获取该位置的棋子
	 * @Param 获取棋子的位置position
	 * @return 该位置的棋子
	 */
	public Piece1 getPositionPiece(Position1 x);
	/*
	 * 该方法用于获取该玩家棋盘上棋子的数量
	 * @Param 玩家player
	 * @return 该玩家棋盘上棋子的数量
	 */
	public int getnumPiece(int num);
}
