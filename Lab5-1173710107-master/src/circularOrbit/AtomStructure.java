package circularOrbit;

import centralObject.Nucleus;

import exception.InvalidTag;

import exception.NumOrder;

import exception.SyntaxSpec;

import exception.lacktag;

import exception.sameele;

import exception.sametag;

import exception.wrongformat;

import exception.wrongrely;

import java.util.ArrayList;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import org.apache.log4j.Level;

import org.apache.log4j.Logger;
import physicalObject.Electron;

import track.Track;


public class AtomStructure extends ConcreteCircularOrbit<Nucleus, Electron> {
  ArrayList<String> infoall = new ArrayList<String>();
  String elementName;
  int trackNum = 0;

  /**
   * 增加一个新轨道 AF:新建一个轨道，对应于一个电子的轨道 RI：true immutable.
   */
  public void setinfoall(ArrayList<String> info) {
    this.infoall = info;
  }

  public void newTrack() {
    trackNum++;
    this.addTrack("track" + trackNum, trackNum);
  }

  /**
   * 删除掉最外层的轨道 AF:删除最外层的一个轨道，对应原子上的轨道 RI：true immutable.
   */
  public void clearTrack() {
    this.removeTrack(trackNum);
    trackNum--;
  }

  /**
   * 获取现在轨道的数量.
   * 
   * @return 轨道数量int类型 AF：获取原子轨道的个数 RI：true immutable
   */
  public int getTrackNumbers() {
    return this.trackNum;
  }

  /**
   * 移动电子.
   * 
   * @param source 移动电子的source 
   * @param target 移动电子的target AF:两个数代表两个原子轨道 RI：0<source<tracknum 0<target<tracknum mutable
   */
  public void transitElectron(int source, int target) {
    if (source > trackNum || target > trackNum || source <= 0 
        || target <= 0 || source == target) {// 判断参数是否符合规则
    
      System.out.println("输入不合法!");
      return;
    } else {
      Track<Electron> a = this.tracks.get(source);
      Track<Electron> b = this.tracks.get(target);
      a.remove(new Electron(source));
      b.add(new Electron(target));
    }
    return;
  }

  /**
   * 分析从文件中读取的信息 RI：true AF:将文件中的信息保存到对应的数据类型中 immutable.
   */
  public final void Analysis() {
    Logger logger = Logger.getLogger(AtomStructure.class);
    logger.setLevel(Level.INFO);
    boolean visited = false;
    int elenum = 0;
    int tracknum = 0;
    try {
      for (String info : infoall) {
        Pattern p2 = Pattern.compile("NumberOfTracks ::= (\\d+)");
        Matcher m2 = p2.matcher(info);
        if (m2.find()) {

          this.trackNum = Integer.valueOf(m2.group(1));
          this.autoAddTrack(trackNum);
          visited = true;
          tracknum++;
        }
      }
      if (tracknum == 0) {
        throw new lacktag();
      } else if (tracknum > 1) {
        throw new sametag();
      }
      for (String info : infoall) {
        visited = false;
        Pattern p1 = Pattern.compile("ElementName ::= ([a-zA-Z]{1,2})");
        Matcher m1 = p1.matcher(info);
        Pattern p2 = Pattern.compile("NumberOfTracks ::= (\\d+)");
        Matcher m2 = p2.matcher(info);
        if (m1.find()) {



          this.elementName = m1.group(1);
          visited = true;
          elenum++;
        } else if (m2.find()) {
          visited = true;
        } else {
          Pattern p3 = Pattern.compile("(\\d+)/(\\d+)");
          Matcher m3 = p3.matcher(info);
          Pattern p4 = Pattern.compile("NumberOfElectron ::=");
          Matcher m4 = p4.matcher(info);
          if (!m4.find()) {
            logger.error("应用三缺少轨道数目的标签，重新输入文本");
            throw new lacktag();
          }
          while (m3.find()) {
            int layer = Integer.valueOf(m3.group(1));
            if (layer > trackNum || layer < 1) {
              logger.error("应用2电子的轨道数超过了轨道数，重新输入文本");
              throw new wrongrely();
            }
            int num = Integer.valueOf(m3.group(2));

            for (int i = 1; i <= num; i++) {
              this.addObject(layer, new Electron(layer));
            }
            visited = true;
          }
        }
        if (!visited) {
          if (info.indexOf(" ::= ") != -1) {
            String[] line = info.split(" ::= ");
            if (line.length == 2) {
              if (line[0].equals("ElementName")) {
                if (line[1].length() == 2) {
                  char a = line[1].charAt(0);
                  char b = line[1].charAt(1);
                  if (Character.isLowerCase(a)) {
                    String newinfo = null;
                    if (Character.isLowerCase(b)) {
                      String x = line[1].toUpperCase();
                      newinfo = "ElementName ::= " + x.charAt(0) + b;
                    } else if (Character.isUpperCase(b)) {
                      String x = line[1].toUpperCase();
                      String y = line[1].toUpperCase();
                      newinfo = "ElementName ::= " + x.charAt(0) + y.charAt(1);
                    } else {
                      logger.error("应用2元素的名字不符合要求，重新输入文本");
                      throw new SyntaxSpec();
                    }
                    Pattern p5 = Pattern.compile("ElementName ::= ([a-zA-Z]{1,2})");
                    Matcher m5 = p5.matcher(newinfo);
                    if (m5.find()) {
                      if (Character.isUpperCase(a)) {
                        String newinfo1 = null;
                        if (Character.isLowerCase(b)) {
                          // 正确
                          this.elementName = m5.group(1);
                          visited = true;
                          elenum++;
                          System.out.println(elementName);
                        } else if (Character.isUpperCase(b)) {
                          String y = line[1].toLowerCase();
                          newinfo1 = "ElementName ::= " + a + y.charAt(1);
                        } else {
                          throw new SyntaxSpec();
                        }
                        if (Character.isUpperCase(a)) {
                          String newinfo11 = null;
                          if (Character.isLowerCase(b)) {
                            // 正确
                          } else if (Character.isUpperCase(b)) {
                            String y = line[1].toLowerCase();
                            newinfo11 = "ElementName ::= " + a + y.charAt(1);
                          } else {
                            throw new SyntaxSpec();
                          }
                          Pattern p511 = Pattern.compile("ElementName ::= ([a-zA-Z]{1,2})");
                          Matcher m511 = p5.matcher(newinfo11);
                          if (m511.find()) {
                            this.elementName = m511.group(1);
                            visited = true;
                            elenum++;
                            logger.info("元素名称大小写不正确，修改成功");
                            System.out.println("元素名称大小写不正确，修改成功");
                          }
                        }
                      }

                    }
                  } else if (Character.isUpperCase(a)) {
                    String newinfo = null;
                    if (Character.isLowerCase(b)) {
                      // 正确
                    } else if (Character.isUpperCase(b)) {
                      String y = line[1].toLowerCase();
                      newinfo = "ElementName ::= " + a + y.charAt(1);
                    } else {
                      throw new SyntaxSpec();
                    }
                    Pattern p5 = Pattern.compile("ElementName ::= ([a-zA-Z]{1,2})");
                    Matcher m5 = p5.matcher(newinfo);
                    if (m5.find()) {
                      this.elementName = m5.group(1);
                      visited = true;
                      elenum++;
                    }
                  } else {
                    logger.error("应用2元素的名字不符合要求，重新输入文本");
                    throw new SyntaxSpec();
                  }
                } else if (line[1].length() == 1) {
                  char c = line[1].charAt(0);
                  if (Character.isUpperCase(c)) {
                    // 好像莫得错误
                  } else if (Character.isLowerCase(c)) {
                    String x = line[1].toUpperCase();
                    String newinfo = "ElementName ::= " + x;
                    Pattern p5 = Pattern.compile("ElementName ::= ([a-zA-Z]{1,2})");
                    Matcher m5 = p5.matcher(newinfo);
                    if (m5.find()) {
                      this.elementName = m5.group(1);
                      visited = true;
                      elenum++;
                    }
                  } else {
                    logger.error("应用2元素的名字不符合要求，重新输入文本");
                    throw new SyntaxSpec();
                  }
                } else {
                  logger.error("应用2元素的名字不符合要求，重新输入文本");
                  throw new SyntaxSpec();
                }

              } else if (line[0].equals("NumberOfElectron")) {

                for (int i = 0; i < line[1].length(); i++) {
                  System.out.println(line[1].charAt(i));
                  if (!Character.isDigit(line[1].charAt(i))) {
                    logger.error("应用2轨道数不是纯数字，重新输入文本");
                    throw new SyntaxSpec();

                  }
                }
              } else {
                logger.error("应用2文本有非法标签，重新输入文本");
                throw new lacktag();

              }
            } else {
              logger.error("应用2文本的格式不正确，重新输入文本");
              throw new wrongformat();
            }
          } else {
            logger.error("应用2文本的格式不正确，重新输入文本");
            throw new wrongformat();
          }

        }

      }
      if (elenum == 2 || tracknum == 2) {
        logger.error("应用2文本有不应该出现多次的标签出现，重新输入文本");
        throw new sametag();
      }

    } catch (sameele e) {
      e.newfile2(this, 2);
    } catch (NumOrder e) {
      e.newfile2(this, 2);
    } catch (sametag e) {
      e.newfile2(this, 2);
    } catch (InvalidTag e) {
      e.newfile2(this, 2);
    } catch (SyntaxSpec e) {
      e.newfile2(this, 2);
    } catch (wrongformat e) {
      e.newfile2(this, 2);
    } catch (wrongrely e) {
      e.newfile2(this, 2);
    } catch (lacktag e) {
      e.newfile2(this, 2);
    }
  }

  /**
   * 构建track.
   */
  public AtomStructure(String path) {
    this.infoall = this.readFile(path);
    Analysis();
  }

  public void checkRep() {
    try {
      if (trackNum < 0) {
        throw new SyntaxSpec();
      }
    } catch (SyntaxSpec e) {
      e.newfile2(this, 2);
    }

  }

  @Override
  public String toString() {
    StringBuilder a = new StringBuilder();
    a.append("Element Name:" + this.elementName);
    a.append("\nTrack Numbers:" + this.trackNum);
    a.append("\n");
    for (Track<Electron> temp : this.tracks) {
      a.append(temp.singleLinetoString());
    }
    return a.toString();
  }

  public static void main(String[] args) {
    AtomStructure test = new AtomStructure("txt/AtomicStructure_Medium.txt");
    System.out.println(test);

  }

}
