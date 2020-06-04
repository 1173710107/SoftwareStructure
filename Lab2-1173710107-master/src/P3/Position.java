package P3;

public interface Position {
	/*
	 * 该方法用于获取坐标的x值
	 * @Param 位置坐标position
	 * @return x坐标
	 */
	public int x(Position1 position);
	/*
	 * 该方法用于获取坐标的y值
	 * @Param 位置坐标position
	 * @return y坐标
	 */
	public int y(Position1 position);
	/*
	 * 该方法用于判断两个坐标是否相等
	 * @Param 两个坐标x,y
	 * @return 两个坐标相同返回true,不同返回false
	 */
	public boolean equals(Position1 x,Position1 y);
	
}
