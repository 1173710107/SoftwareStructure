package exception;

import javax.swing.JOptionPane;

public class InvalidTag extends newfile1 { // 元素定义的标签非法
  public InvalidTag() {
    JOptionPane.showMessageDialog(null, "输入文本的元素定义的标签非法，请重新输入一个文本！");
  }
}
