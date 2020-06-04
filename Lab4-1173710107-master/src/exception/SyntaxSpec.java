package exception;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class SyntaxSpec extends newfile1{//元素定义语法规范不一致
	public SyntaxSpec()
	{
		JOptionPane pane = new JOptionPane("文本中有元素和定义语法规范不一致，请重新输入一个文本！", 0);
		JDialog dialog = pane.createDialog("");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
		dialog.dispose();
		//JOptionPane.showMessageDialog(null, "文本中有元素和定义语法规范不一致，请重新输入一个文本！");
	}
}
