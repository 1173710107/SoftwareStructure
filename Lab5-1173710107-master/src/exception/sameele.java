package exception;

import javax.swing.JOptionPane;

public class sameele extends newfile1 { // 有完全一样的元素
  public sameele() {
    JOptionPane.showMessageDialog(null, "文本中有完全一样的元素，请重新输入一个文本！");
  }
}
