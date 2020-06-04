package P3;

public interface Board {
	/*
	 * 该方法是获取xy对应位置的棋子的信息
	 * @Param 获取棋子的位置的x y坐标
	 * @return 该位置的棋子
	 */
	public Piece1 getPiece(int x,int y);
	/*
	 * 该方法是将棋子放在棋盘的对应位置上
	 * @Param 棋子piece以及放置的位置x y
	 */
	public void setPiece(int x,int y,Piece1 piece);
	/*
	 * 该方法用于检查给定的xy位置坐标是否可用
	 * @Param 棋盘上x y坐标 
	 * @return 如果该位置是有意义的，那么返回true，否则返回false
	 */
	public boolean isavailable(int x,int y);
	/*
	 * 该方法检查该棋子是否在棋盘上
	 * @Param 需要检查的棋子piece
	 * @return 如果该棋子在棋盘上，返回true，否则返回false
	 */
	public boolean isinborad(Piece1 piece);
	/*
	 * 该方法用于获取该玩家棋盘上棋子的数量
	 * @Param 玩家player
	 * @return 该玩家棋盘上棋子的数量
	 */
	public int getplayernum(Player1 player);
}
