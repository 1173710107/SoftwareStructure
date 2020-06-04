package circularOrbit;

import exception.InvalidTag;

import exception.NumOrder;

import exception.SyntaxSpec;

import exception.errorordertag;

import exception.sameele;

import exception.sametag;

import exception.wrongformat;

import exception.wrongrely;

import java.util.ArrayList;

import java.util.Map;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import org.apache.log4j.Level;

import org.apache.log4j.Logger;

import physicalObject.Account;

import track.Track;


public class SocialNetworkCircle extends ConcreteCircularOrbit<Account, Account> {
  ArrayList<String> infoall = new ArrayList<String>();
  ArrayList<Account> allFriends = new ArrayList<Account>();
  Logger logger = Logger.getLogger(SocialNetworkCircle.class);

  /**
   * 获取该名字的账户信息.
   * 
   * @param name 需要的账户的名字name
   * @return 返回该名字的账户 AF:获取轨道上的物体，也就是对应的账户 RI：true mutable
   */
  Account getAccount(String name) {
    for (Account temp : allFriends) {
      if (temp.getName().equals(name)) {
        return temp;
      }

    }
    System.out.println("未找到该账户！！！");
    return null;
  }

  /**
   * 在最外层增加轨道 AF:新建一个轨道，对应于多一层朋友圈 RI：true immutable.
   */
  public void newTrack() {
    int trackNumbers = tracks.size();
    this.addTrack("track" + trackNumbers, trackNumbers);
  }

  /**
   * 删除最外层的轨道 AF:删除最外层的轨道，对应于朋友圈的最外层 RI：true immutable.
   */
  public void clearTrack() {
    int trackNumbers = tracks.size();
    this.removeTrack(trackNumbers - 1);
  }

  /**
   * 查找该名字的账户在哪个轨道.
   * 
   * @param name 账户的名字name
   * @return 返回该账户的所在的层数，未找到返回-1 AF:返回数字对应于所在该轨道号 RI：true immutable
   */
  public int onWhichTrack(String name) {
    Account temp = getAccount(name);
    return relations.getDistance(this.centers.get(0), temp);
  }

  /**
   * 查找该账户所在的轨道.
   * 
   * @param temp 需要查找的账户temp
   * @return 找到返回该账户所在轨道数，否则返回-1 AF:返回数字对应于所在该轨道号 RI:true mutable
   */
  public int onWhichTrack(Account temp) {
    return relations.getDistance(this.centers.get(0), temp);
  }

  /**
   * 刷新.
   */
  public void refresh() {
    for (Track<Account> temp : tracks) {
      temp.emptyAll();
    }
    for (Account temp : allFriends) {
      int dis = this.relations.getDistance(this.centers.get(0), temp);
      if (dis > 0) {
        this.autoAddTrack(dis);
        this.addObject(dis, temp);
      }
    }
  }

  /**
   * 找出某个用户的潜在的朋友个数.
   * 
   * @param name 该用户的名字name
   * @return 朋友个数 AF:返回数值对应于该账号的拓展朋友个数 RI：return>=0 immutable
   */
  public int expansion(String name) {
    int res = 0;
    Account temp = getAccount(name);
    if (onWhichTrack(name) == 1) {
      for (Map.Entry<Account, Integer> temp1 : this.relations.graph.targets(temp).entrySet()) {
        if (temp1.getValue() >= 500 && temp1.getKey() != this.centers.get(0)
            && (!(tracks.get(1).contains(temp1.getKey())))) {
          res++;
        }

      }
    } else {
      System.out.println("Not on track 1!");
    }
    return res;
  }

  /**
   * 增加两个用户的关系.
   * 
   * @param a 一个用户的名字a
   * @param b 一个用户的名字b
   * @param val AF：增加两个物体的边，对应于增加两个账号之间的关联 RI：val>0 immutable
   */
  public void addEdge(String a, String b, int val) {
    Account A = this.getAccount(a);
    Account B = this.getAccount(b);
    if (A == null || B == null) {
      System.out.println("添加关系失败！！");
      return;
    }
    this.relations.addEdge(A, B, val);
  }

  /**
   * 删除两个账户的关系.
   * 
   * @param a 起始点的账户名字a 
   * @param b 终结点的账户名字b AF:对应于删除两个账户之间的联系 RI：true immutable
   */
  public void removeEdge(String a, String b) {
    Account A = this.getAccount(a);
    Account B = this.getAccount(b);
    if (A == null || B == null) {
      System.out.println("删除关系失败");
      return;
    }
    this.relations.removeEdge(A, B);
  }

  /**
   * 获取两个用户之间的距离.
   * 
   * @param a 起始点用户名字a
   * @param b 终结点的用户名字b
   * @return 返回两个用户的额距离，如果不合法失败的额话，返回-1 AF:获取两个账号之间的逻辑距离，对应于两个物体之间的最短路径 RI：return>=-1 immutable
   */
  public int getLogicalDistance(String a, String b) {
    Account A = this.getAccount(a);
    Account B = this.getAccount(b);
    if (A == null || B == null) {
      System.out.println("名字不合法！！！");
      return -1;
    }
    return this.getLogicalDistance(A, B);
  }

  /**
   * 建立一个社交网络.
   * 
   * @param path 文件的路径path
   * 
   */
  public SocialNetworkCircle(String path) {
    this.infoall = this.readFile(path);
    this.setinfoall(infoall);
    this.Analysis();
  }

  public final void setinfoall(ArrayList<String> info) {
    this.infoall = info;
  }

  public final void Analysis() {
    logger.setLevel(Level.INFO);
    try {
      Account cent = new Account("init", 1, "M");
      boolean visited = false;
      int centnum = 0;
      for (String info : infoall) {
        visited = false;
        int i = 0;
        Pattern p1 = Pattern.compile("CentralUser ::= <([a-zA-Z0-9]+) *, *(\\d+) *, *(M|F)>");
        Matcher m1 = p1.matcher(info);
        if (m1.find()) {
          cent = new Account(m1.group(1), Integer.valueOf(m1.group(2)), m1.group(3));
          this.addCenter(cent);
          this.allFriends.add(cent);
          this.relations.addVertex(cent);
          visited = true;
          centnum++;
        }
        if (centnum == 2) {
          logger.error("应用三中心点标签出现了两次，重新输入文本");
          throw new sametag();
        }
        Pattern p2 = Pattern.compile("Friend ::= <([a-zA-Z0-9]+) *, *(\\d+) *, *(M|F)>");
        Matcher m2 = p2.matcher(info);
        if (m2.find()) {
          Account a = new Account(m2.group(1), Integer.valueOf(m2.group(2)), m2.group(3));
          if (allFriends.contains(a)) {
            throw new sameele();
          }
          this.allFriends.add(a);
          this.relations.addVertex(a);
          visited = true;
        }
        Pattern p3 = Pattern
            .compile("SocialTie ::= <([a-zA-Z0-9]+) *, *([a-zA-Z0-9]+) *, *((0|1).\\d{1,3})>");
        Matcher m3 = p3.matcher(info);
        if (m3.find()) {
          Account A = getAccount(m3.group(1));
          Account B = getAccount(m3.group(2));
          int val = (int) (1000 * Double.valueOf(m3.group(3)));
          if (!allFriends.contains(A) || !allFriends.contains(B)) {
            logger.error("应用三有关系没有对应该人名，或者有friends标签放在了socialtie之后，重新输入文本");
            throw new errorordertag();

          }
          this.relations.addEdge(A, B, val);
          this.relations.addEdge(B, A, val);
          visited = true;
        }
        if (!visited) {
          if (info.contains(" ::= <")) {
            System.out.println(info);
            String[] line = info.split(" ::= <");
            if (line.length == 2) {
              if (line[0].equals("CentralUser")) {
                String[] line1 = line[1].split(",");
                if (line1.length == 3) {
                  int j = 0;
                  for (j = 0; j < line1[0].length(); j++) {
                    if (!Character.isLetter(line1[0].charAt(j))) {
                      logger.error("应用三中心点第一个参数格式不对，重新输入文本");
                      throw new SyntaxSpec();
                    }
                  }
                  for (j = 0; j < line1[1].length(); j++) {
                    if (!Character.isDigit(line1[1].charAt(j))) {
                      logger.error("应用三中心点第二个参数格式不对，重新输入文本");
                      throw new SyntaxSpec();
                    }
                  }
                  if (line1[2].contains(">")) {
                    String[] line2 = line1[2].split(">");
                    if (line2.length == 1) {
                      if (line2[0].equals("M")) {
                        logger.info("true");
                      } else if (line2[0].equals("F")) {
                        logger.info("true");
                      } else {
                        logger.error("应用三中心点第三个参数格式不对，重新输入文本");
                        throw new SyntaxSpec();
                      }
                    } else {
                      logger.error("应用三中心点格式不对，重新输入文本");
                      throw new wrongformat();
                    }
                  } else {
                    logger.error("应用三中心点格式不对，重新输入文本");
                    throw new wrongformat();
                  }


                } else {
                  logger.error("应用三中心点格式不对，重新输入文本");
                  throw new wrongformat();
                }
              } else if (line[0].equals("Friend")) {

                int j = 0;
                String[] line1 = line[1].split(",");
                if (line1.length == 3) {
                  for (j = 0; j < line1[1].length(); j++) {
                    if (!Character.isDigit(line1[1].charAt(j))) {
                      logger.error("应用三friends参数不是纯数字，重新输入文本");
                      throw new SyntaxSpec();
                    }
                  }
                  if (line1[2].contains(">")) {
                    String[] line2 = line1[2].split(">");
                    if (line2.length == 1) {
                      if (line2[0].equals("M")) {
                        logger.info("true");
                      } else if (line2[0].equals("F")) {
                        logger.info("true");
                      } else {
                        logger.error("应用三friends参数不是M/F，重新输入文本");
                        throw new SyntaxSpec();
                      }
                    } else {
                      logger.error("应用三friends格式不对，重新输入文本");
                      throw new wrongformat();
                    }
                  } else {
                    logger.error("应用三friends格式不对，重新输入文本");
                    throw new wrongformat();
                  }
                } else {
                  logger.error("应用三friends格式不对，重新输入文本");
                  throw new wrongformat();
                }

              } else if (line[0].equals("SocialTie")) {
                String[] line1 = line[1].split(",");
                if (line1.length == 3) {
                  String[] line2 = line1[2].split(">");
                  if (line2.length == 1) {
                    String[] line3 = line2[0].split("\\.");
                    if (line3.length == 2) {
                      String newinfo = null;
                      if (line3[1].length() == 3) {
                        // 没找到问题
                      } else {
                        for (i = 0; i < line3[1].length(); i++) {
                          if (!Character.isDigit(line3[1].charAt(i))) {
                            logger.error("应用三socialtie参数不是纯数字，重新输入文本");
                            throw new SyntaxSpec();
                          }
                        }
                        if (line3[1].length() < 3) {
                          if (line3[1].length() == 1) {
                            newinfo = "SocialTie ::= <" + line1[0] + "," + line1[1] + "," + line3[0]
                                + "." + line3[1] + "00" + ">";
                          } else if (line3[1].length() == 2) {
                            newinfo = "SocialTie ::= <" + line1[0] + "," + line1[1] + "," + line3[0]
                                + "." + line3[1] + "0" + ">";
                          } else {
                            newinfo = "SocialTie ::= <" + line1[0] + "," + line1[1] + "," + line3[0]
                                + "." + "000" + ">";
                          }

                        } else {
                          String d = Character.toString(line3[1].charAt(0))
                              + Character.toString(line3[1].charAt(1))
                              + Character.toString(line3[1].charAt(2));
                          newinfo = "SocialTie ::= <" + line1[0] + "," + line1[1] + "," + line3[0]
                              + "." + d + "000" + ">";
                        }
                        Pattern p5 = Pattern.compile(
                     "SocialTie ::= <([a-zA-Z0-9]+) *, *([a-zA-Z0-9]+) *, *((0|1).\\d{1,3})>"
                            );
                        Matcher m5 = p5.matcher(newinfo);
                        if (m5.find()) {
                          Account A = getAccount(m5.group(1));
                          Account B = getAccount(m5.group(2));
                          int val = (int) (1000 * Double.valueOf(m5.group(3)));
                          if (!allFriends.contains(A) || !allFriends.contains(B)) {
                            logger.error("应用三socialtie依赖关系不正确，或者friends标签放在了socialtie之后，重新输入文本");
                            throw new errorordertag();

                          }
                          this.relations.addEdge(A, B, val);
                          this.relations.addEdge(B, A, val);
                          visited = true;
                          logger.error("应用三socialtie对参数关系权重进行了重写，符合了规则，小数点小于三位的直接0补齐，大于三位的舍去后面数字");
                        } else {
                          logger.error("应用三socialtie参数不是纯数字，重新输入文本");
                          throw new SyntaxSpec();
                        }
                      }
                    } else {
                      logger.error("应用三输入文本的格式不对，重新输入文本");
                      throw new SyntaxSpec();
                    }
                  } else {
                    logger.error("应用三输入文本的格式不对，重新输入文本");
                    throw new wrongformat();
                  }

                } else {
                  logger.error("应用三输入文本的格式不对，重新输入文本");
                  throw new wrongformat();
                }
              } else {
                logger.error("应用三文本有非法标签的存在，重新输入文本");
                throw new InvalidTag();
              }
            } else {
              logger.error("应用三输入文本的格式不对，重新输入文本");
              throw new wrongformat();
            }
          } else {
            System.out.println(info);
            logger.error("应用三输入文本的格式不对，重新输入文本");
            throw new wrongformat();
          }

        }

      }
      for (Account temp : allFriends) {
        int dis = this.relations.getDistance(cent, temp);
        if (dis > 0) {
          this.autoAddTrack(dis);
          this.addObject(dis, temp);
        }
      }

    } catch (sameele e) {
      e.newfile2(this, 3);
    } catch (NumOrder e) {
      e.newfile2(this, 3);
    } catch (sametag e) {
      e.newfile2(this, 3);
    } catch (InvalidTag e) {
      e.newfile2(this, 3);
    } catch (SyntaxSpec e) {
      e.newfile2(this, 3);
    } catch (wrongformat e) {
      e.newfile2(this, 3);
    } catch (wrongrely e) {
      e.newfile2(this, 3);
    } catch (errorordertag e) {
      e.newfile2(this, 3);
    }

  }

  /**
   * 检查每个人所在的轨道是否等于和中心点的距离.
   */
  public void checkRep() {
    try {
      for (Account x : allFriends) {
        int dis1 = this.onWhichTrack(x);
        for (Account y : centers) {
          int dis2 = this.getLogicalDistance(y, x);
          if (dis1 != dis2) {
            throw new wrongrely();
          }
        }

      }
    } catch (wrongrely e) {
      e.wrongrely1();
    }

  }

  @Override
  public String toString() {
    StringBuilder a = new StringBuilder();
    a.append("Center:" + this.centers + "\n");
    String string = this.tracks.toString();
    a.append(string);
    return a.toString();
  }

  public static void main(String[] args) {
    SocialNetworkCircle test = new SocialNetworkCircle("txt/SocialNetworkCircle_Medium.txt");
    System.out.println(test);
  }
  //
  // }

}
