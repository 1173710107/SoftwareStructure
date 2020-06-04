package exception;
import javax.swing.JOptionPane;
public class NumOrder extends newfile1{//元素定义的数目，次序不同
	int num;
	public NumOrder(int num)
	{
		this.num = num;
		if(num==0)
		JOptionPane.showMessageDialog(null,"输入文本的元素的数目不符合要求，请重新输入一个文本！");
		else
		JOptionPane.showMessageDialog(null, "输入元素的顺序不符合要求，请重新输入一个文本！");
		
	}
	public int getnumOrder()
	{
		return num;
	}
}
