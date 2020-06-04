package exception;

import javax.swing.JOptionPane;

public class wrongformat extends newfile1 { // 输入的格式符号不对
  public wrongformat() {
    JOptionPane.showMessageDialog(null, "文本中有元素的格式符号不对，请重新输入一个文本！");
  }
}
