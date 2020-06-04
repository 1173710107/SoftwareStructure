package P3;

public interface Action {
/*
 * 该方法是将该玩家的start位置的棋子移动到end位置
 * @Param实现操作的玩家，移动棋子的开始位置和结束位置
 */
	public void movePiece(int num,int startx,int starty,int endx,int endy);
/*
 * 该方法是将该玩家的end位置的棋子拿掉,Num表示实现操作的玩家，num=0表示玩家一，num=2表示玩家二
 * @Param实现移除棋子操作的额玩家以及移除棋子的位置
 */
	public void removePiece(int num,int endx,int endy);
/*
 * 该方法是实现player玩家的吃子操作
 * @Param实现吃子操作的玩家以及棋子的起始位置和结束位置
 */
	public void eatPiece(int num,int startx,int starty,int endx,int endy);
}
