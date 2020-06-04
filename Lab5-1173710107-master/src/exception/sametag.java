package exception;

import javax.swing.JOptionPane;

public class sametag extends newfile1 {// 相同的元素标签出现，例如两个game
  public sametag() {

    JOptionPane.showMessageDialog(null, "文本中有不应该出现的相同的元素标签，请重新输入一个文本！");
  }
}
