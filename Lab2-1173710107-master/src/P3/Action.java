package P3;

public interface Action {
/*
 * �÷����ǽ�����ҵ�startλ�õ������ƶ���endλ��
 * @Paramʵ�ֲ�������ң��ƶ����ӵĿ�ʼλ�úͽ���λ��
 */
	public void movePiece(int num,int startx,int starty,int endx,int endy);
/*
 * �÷����ǽ�����ҵ�endλ�õ������õ�,Num��ʾʵ�ֲ�������ң�num=0��ʾ���һ��num=2��ʾ��Ҷ�
 * @Paramʵ���Ƴ����Ӳ����Ķ�����Լ��Ƴ����ӵ�λ��
 */
	public void removePiece(int num,int endx,int endy);
/*
 * �÷�����ʵ��player��ҵĳ��Ӳ���
 * @Paramʵ�ֳ��Ӳ���������Լ����ӵ���ʼλ�úͽ���λ��
 */
	public void eatPiece(int num,int startx,int starty,int endx,int endy);
}
