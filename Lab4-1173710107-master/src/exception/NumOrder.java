package exception;
import javax.swing.JOptionPane;
public class NumOrder extends newfile1{//Ԫ�ض������Ŀ������ͬ
	int num;
	public NumOrder(int num)
	{
		this.num = num;
		if(num==0)
		JOptionPane.showMessageDialog(null,"�����ı���Ԫ�ص���Ŀ������Ҫ������������һ���ı���");
		else
		JOptionPane.showMessageDialog(null, "����Ԫ�ص�˳�򲻷���Ҫ������������һ���ı���");
		
	}
	public int getnumOrder()
	{
		return num;
	}
}
