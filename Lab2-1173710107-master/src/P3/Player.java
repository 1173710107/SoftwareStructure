package P3;

import java.util.Set;

public interface Player {
	/*
	 * 该方法用于获取玩家的name,其中num是玩家标志num=0表示是玩家一，num=1表示是玩家二
	 * @Param 玩家标志变量num
	 * @return 玩家name,String类型
	 */
	public String getplayername(int num);
	/*
	 * 该方法用于获取该玩家的棋子的集合， 其中num是玩家标志num=0表示是玩家一，num=1表示是玩家二
	 * @Param 玩家标志变量num
	 * @return 该玩家的棋子的集合set
	 */
	public Set<Piece1> getplayerPieces(int num);
	/*
	 * 该方法用于获取该玩家的操作历史记录，其中num是玩家标志num=0表示是玩家一，num=1表示是玩家二
	 * @Param 玩家标志变量num
	 * @return 该玩家的操作历史StirngBuilder
	 */
	public StringBuilder getplayerhistory(int num);
	/*
	 * 该方法用于添加棋子到玩家中
	 * @Param 添加棋子piece以及num其中num是玩家标志num=0表示是玩家一，num=1表示是玩家二
	 * @return 如果添加成功返回true,否则返回false
	 */
	public boolean addPiece(Piece1 x,int num);
	/*
	 * 该方法用于判断该玩家是否有该棋子，其中num是玩家标志num=0表示是玩家一，num=1表示是玩家二
	 * @Param 棋子piece以及玩家标志变量num
	 * @return 如果该玩家包含该棋子，返回true，否则返回false
	 */
	public boolean iscontainPiece(int num,Piece1 x);
	/*
	 * 该方法用于设置玩家名字
	 * @Param 玩家名字name以及num,其中num是玩家标志num=0表示是玩家一，num=1表示是玩家二
	 */
	public void setplayername(String name,int num);
	/*
	 * 该方法主要用于记录玩家的操作
	 * @Param step表示玩家的操作步骤，以及num，其中num是玩家标志num=0表示是玩家一，num=1表示是玩家二
	 */
	public void addhistory(int num,String step);
	/*
	 * 该方法用于获取玩家，其中num是玩家标志num=0表示是玩家一，num=1表示是玩家二
	 * @Param 玩家标志num
	 */
	public Player1 getplayer(int num);
}
