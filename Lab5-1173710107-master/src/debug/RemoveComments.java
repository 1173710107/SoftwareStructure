package debug;

import java.util.ArrayList;

import java.util.List;

class RemoveComments {
  public List<String> removeComments(String[] source) {
    boolean inBlock = false;
    StringBuilder newline = new StringBuilder();
    List<String> ans = new ArrayList<>();
    for (String line : source) {
      int i = 0;
      char[] chars = line.toCharArray();
      if (!inBlock) {
        newline = new StringBuilder();
      }

      while (i < line.length()) {
        if (i + 1 < line.length() && chars[i] == '/' && chars[i + 1] == '/') {
          break;
        }
        if (!inBlock && i + 1 < line.length() && chars[i] == '/' && chars[i + 1] == '*') {
          inBlock = true;
        } else if (inBlock && i + 1 < line.length() && chars[i] == '*' && chars[i + 1] == '/') {
          inBlock = false;
          i++;
        } else if (!inBlock) {
          newline.append(chars[i]);
        }
        i++;
      }
      if (!inBlock && newline.length() > 0) {
        ans.add(new String(newline));
      }
    }
    return ans;
  }
}
