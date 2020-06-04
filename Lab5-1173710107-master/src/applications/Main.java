package applications;

import APIs.CircularOrbitAPIs;
import APIs.CircularOrbitHelper;
import circularOrbit.AtomStructure;
import circularOrbit.ConcreteCircularOrbit;
import circularOrbit.SocialNetworkCircle;
import circularOrbit.TrackGame;
import exception.errorinfile;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import physicalObject.Athlete;


public class Main {

  static Scanner in = new Scanner(System.in);

  public static void main(String[] args) {
    Logger logger = Logger.getLogger(Main.class);
    logger.setLevel(Level.INFO);
    System.out.println("请选择应用?");
    System.out.println("1.TrackGame");
    System.out.println("2.AtomStructure");
    System.out.println("3.SocialNetworkCircle");
    int type;
    int choice;
    String path;
    type = in.nextInt();
    in.nextLine();

    if (type == 1) {
      logger.info("选择trackgame应用");
      System.out.println("正在读取 TrackGame.txt 中的信息");
      logger.info("读取trackgame.txt的信息");
      path = ("txt/TrackGame.txt");
      TrackGame game = new TrackGame();
      
      ArrayList<String> info = ConcreteCircularOrbit.readFile(path);
      game.setinfoall(info);
      logger.info("正在对读入文本进行正则表达式解析！");
      game.Analysis();
      while (true) {
        logger.info("输出应用一的选择菜单");
        System.out.println("请选择下面功能?");
        System.out.println("1.可视化");
        System.out.println("2.增加轨道");
        System.out.println("3.删除轨道");
        System.out.println("4.熵值");
        System.out.println("5.随机排序");
        System.out.println("6.按照成绩排序");
        System.out.println("7.改变轨道");
        System.out.println("8.退出");
        System.out.println("9.查询logger");
        System.out.println("10.将读入的信息写入到文本writetrackgame。txt中");
        // in.nextLine();
        choice = in.nextInt();
        in.nextLine();
        System.out.println("!!!");
        switch (choice) {
          case 1:
            logger.info("应用一选择可视化功能");
            System.out.println(game);
            CircularOrbitHelper.visualize(game.numOnEachTrack());
            break;
          case 2:
            logger.info("应用一增加一个新的轨道");
            game.newTrack();
            break;
          case 3:
            logger.info("应用一删除最外层的轨道");
            game.clearTrack();
            break;
          case 4:
            logger.info("应用一计算熵值");
            System.out
                .println(CircularOrbitAPIs.getObjectDistributionEntropy(game.numOnEachTrack()));
            break;
          case 5:
            logger.info("应用一对当前信息进行随机排序");
            game.emptyAll();
            game.shuffleArrange();
            System.out.println(game);
            break;
          case 6:
            logger.info("应用一按照成绩对当前信息进行排序");
            game.emptyAll();
            game.sortedArrange();
            System.out.println(game);
            break;
          case 7:

            System.out.println("请输入需要移动的运动员的名字");
            String name = in.nextLine();
            Athlete temp = new Athlete(name, 0, "CHN", 0, 0.00);
            System.out.println("请输入需要移动到的位置?");
            int tar = in.nextInt();
            in.nextLine();
            game.transit(temp, tar);
            logger.info("应用一将运动员" + name + "移动到" + tar + "轨道上");
            break;
          case 8:
            logger.info("退出当前的应用");
            break;
          case 9:
            searchlog();
            break;
          case 10:
            game.write();
            break;
          default:
            logger.error("应用一选择输入出错，请重新输入");
            System.out.println("请按格式输入！！");
        }


      }
    } else if (type == 2) {
      logger.info("读取 AtomicStructure.txt文件的信息");
      System.out.println("正在读取 AtomicStructure.txt 文件的信息");
      path = ("txt/AtomicStructure.txt");
      logger.info("对应用二读入文本进行正则表达式匹配");
      AtomStructure atom = new AtomStructure(path);
      while (true) {
        logger.info("输出应用二的功能菜单");
        System.out.println("请选择下面的功能?");
        System.out.println("1.可视化");
        System.out.println("2.增加轨道");
        System.out.println("3.删除轨道");
        System.out.println("4.计算熵值");
        System.out.println("5.改变轨道");
        System.out.println("6.退出");
        System.out.println("7.查询logger");
        System.out.println("8.输出读入的信息到writeatom.txt中");
        choice = in.nextInt();
        in.nextLine();
        switch (choice) {
          case 1:
            logger.info("进行应用二的可视化");
            System.out.println(atom);
            CircularOrbitHelper.visualize(atom.numOnEachTrack());
            break;
          case 2:
            logger.info("应用二增加一个轨道");
            atom.newTrack();
            break;
          case 3:
            logger.info("应用二删除最外层轨道");
            atom.clearTrack();
            break;
          case 4:
            logger.info("应用二计算熵值");
            System.out
                .println(CircularOrbitAPIs.getObjectDistributionEntropy(atom.numOnEachTrack()));
            break;
          case 5:

            System.out.println("从哪个轨道开始移动?");
            int source = in.nextInt();
            in.nextLine();
            System.out.println("移动到那个轨道？?");
            int tar = in.nextInt();
            in.nextLine();
            atom.transitElectron(source, tar);
            logger.info("应用二从" + source + "轨道移动到" + tar + "轨道");
            break;
          case 6:
            logger.info("退出当前应用");
            break;
          case 7:
            searchlog();
            break;
          case 8:
            atom.write();
            break;
          default:
            logger.error("应用二的输入有错误，请重新输入");
            System.out.println("请按格式输入！！");
        }


      }
    } else if (type == 3) {
      logger.info("读入SocialNetworkCircle.txt 文件");
      System.out.println("正在读入  SocialNetworkCircle.txt 文件");
      path = ("txt/SocialNetworkCircle.txt");
      logger.info("对应用三的文本进行正则表达式匹配");
      SocialNetworkCircle circle = new SocialNetworkCircle(path);
      while (true) {
        logger.info("输出应用三菜单");
        System.out.println("请选择下面功能：");
        System.out.println("1.可视化");
        System.out.println("2.增加轨道");
        System.out.println("3.删除轨道");
        System.out.println("4.计算熵值");
        System.out.println("5.查找某个用户在哪个轨道");
        System.out.println("6.查看某个用户具体有多少朋友");
        System.out.println("7.增加关系");
        System.out.println("8.删除关系");
        System.out.println("9.刷新");
        System.out.println("10.计算两个用户之间的距离");
        System.out.println("11.退出");
        System.out.println("12.查询logger");
        System.out.println("13.输出读入文件信息到writesocial.txt中");
        Scanner in3 = new Scanner(System.in);
        choice = in3.nextInt();
        in3.nextLine();
        switch (choice) {
          case 1:
            logger.info("对应用三进行可视化操作");
            System.out.println(circle);
            CircularOrbitHelper.visualize(circle);
            break;
          case 2:
            logger.info("应用三添加新轨道");
            circle.newTrack();
            break;
          case 3:
            logger.info("应用三清除最外层轨道");
            circle.clearTrack();
            break;
          case 4:
            logger.info("计算应用三的熵值");
            System.out
                .println(CircularOrbitAPIs.getObjectDistributionEntropy(circle.numOnEachTrack()));
            break;
          case 5:

            System.out.println("请输入需要查看的用户的名字：");
            String source = in.nextLine();
            System.out.println(circle.onWhichTrack(source));
            logger.info("应用三查询到" + source + "用户在" + circle.onWhichTrack(source) + "轨道上");
            break;
          case 6:
            System.out.println("请输入需要查看的用户的名字：");
            String source1 = in.nextLine();
            System.out.println(circle.expansion(source1));
            logger.info("应用三查询" + source1 + "有" + circle.expansion(source1) + "朋友");
            break;
          case 7:
            System.out.println("请输入需要建立关系的起始用户");
            String source2 = in.nextLine();
            System.out.println("请输入需要建立关系的终点用户");
            String target = in.nextLine();
            System.out.println("权重是?");
            Double val = in.nextDouble();
            in.nextLine();
            int ival = (int) (val * 1000);
            circle.addEdge(source2, target, ival);
            logger.info("应用三建立用户" + source2 + "和" + target + "的关系，权值为" + val);
            break;
          case 8:
            System.out.println("请输入需要删除关系的起始用户");
            String source3 = in.nextLine();
            System.out.println("请输入需要删除关系的终点用户");
            String target1 = in.nextLine();
            circle.removeEdge(source3, target1);
            logger.info("应用三删除用户" + source3 + "和用户" + target1 + "的关系");
            break;
          case 9:
            circle.refresh();
            logger.info("应用三刷新");
            break;
          case 10:
            System.out.println("请输入需要获取距离的起始用户");
            String source4 = in.nextLine();
            System.out.println("请输入需要获取距离的终点用户");
            String target2 = in.nextLine();
            System.out.println(circle.getLogicalDistance(source4, target2));
            logger.info("应用三获取用户" + source4 + "和用户" + target2 + "的距离，距离是"
                + circle.getLogicalDistance(source4, target2));
            break;
          case 11:
            logger.info("应用三退出当前应用");
            break;
          case 12:
            searchlog();
            break;
          case 13:
            circle.write();
            break;
          default:
            logger.error("应用三的输入不合法，需要重新输入");
            System.out.println("请按格式输入！！");
        }


      }
    }
    in.close();
  }

  public static void searchlog() {
    System.out.println("1.按时间查询");
    System.out.println("2.按类型查询");
    System.out.println("3.按类查询");
    System.out.println("4.按方法查询");
    System.out.println("5.按操作类型查询");
    // Scanner in = new Scanner(System.in);
    try {
      BufferedReader br = new BufferedReader(new FileReader("log4j.log"));// 读取文件
      int choice = in.nextInt();
      in.nextLine();
      String line = null;
      int year = 0;
      int month = 0;
      int day = 0;
      int hour = 0;
      int minite = 0;
      int miao = 0;
      String time = null;
      String input = null;
      String input1 = null;
      String input2 = null;
      String input3 = null;
      if (choice == 1) {
        System.out
            .println("请输入查询log的时间段，格式：年 月 日 小时 分钟 秒    eg:2019 05 15 19 02 04 2019 06 29 10 30 03");

      } else if (choice == 2) {
        System.out.println("请输入查询log的类型(INFO/ERROR)");

      } else if (choice == 3) {
        System.out.println("请输入查询log的类");

      } else if (choice == 4) {
        System.out.println("请输入查询log的方法");

      } else if (choice == 5) {
        System.out.println("请输入查询log的操作类型eg:选择trackgame应用");

      }
      String input0 = in.nextLine();
      if (choice == 1) {
        // System.out.println("请输入查询log的时间段，格式：年 月 日 小时 分钟 秒 eg:2019 05 15 19 02 04 2019 06
        // 29 10 30 03");
        time = input0;
      } else if (choice == 2) {
        // System.out.println("请输入查询log的类型(INFO/ERROR)");
        input = input0;
      } else if (choice == 3) {
        // System.out.println("请输入查询log的类");
        input1 = input0;
      } else if (choice == 4) {
        // System.out.println("请输入查询log的方法");
        input2 = input0;
      } else if (choice == 5) {
        // System.out.println("请输入查询log的操作类型eg:选择trackgame应用");
        input3 = input0;
      }
      while ((line = br.readLine()) != null) {
        // 时间
        String[] line1 = line.split("\\]");
        // System.out.println(line1[0]);
        String[] line2 = line1[0].split("\\[");
        String file = null;
        if (line2.length == 1) {
          file = line2[0];
        } else if (line2.length == 2) {
          file = line2[1];
        } else {
          System.out.println("@");
          throw new IOException();
        }
        String[] num = file.split(" ");
        if (num.length == 2) {
          String[] file1 = num[0].split("-");
          if (file1.length == 3) {
            String[] file2 = num[1].split(":");
            if (file2.length == 3) { 
              try {
                year = Integer.parseInt(file1[0]);
                month = Integer.parseInt(file1[1]);
                day = Integer.parseInt(file1[2]);
                hour = Integer.parseInt(file2[0]);
                minite = Integer.parseInt(file2[1]);
                String[] x = file2[2].split(",");
                miao = Integer.parseInt(x[0]);
              } catch (NumberFormatException e) {
                System.out.println("!");
                IOException x = new IOException();
                x.initCause(e);
                throw x;
              }

            } else {
              System.out.println("$");
              throw new IOException();
            }
          } else {
            System.out.println("5");
            throw new IOException();
          }
        } else {
          // System.out.println("p");
          throw new IOException();
        }
        // 操作结果
        String[] l = line.split("-");
        String infomation = null;
        String classfun = null;
        if (l.length == 5) {
          infomation = l[4];
          classfun = l[3];
        }
        // class&function
        // 类型
        String type = null;
        if (line.contains("INFO")) {
          type = "INFO";
        } else if (line.contains("ERROR")) {
          type = "ERROR";
        } else {
          System.out.println("j");
          System.out.println(line);
          throw new IOException();
        }
        switch (choice) {
          case 1:
            String[] time1 = time.split(" ");
            if (time1.length == 12) {
              try {
                String[] y = time1[5].split(",");
                String[] z = time1[11].split(",");
                int byear = Integer.parseInt(time1[0]);
                int bmonth = Integer.parseInt(time1[1]);
                int bday = Integer.parseInt(time1[2]);
                int bhour = Integer.parseInt(time1[3]);
                int bminite = Integer.parseInt(time1[4]);
                int bmiao = Integer.parseInt(y[0]);
                int eyear = Integer.parseInt(time1[6]);
                int emonth = Integer.parseInt(time1[7]);
                int eday = Integer.parseInt(time1[8]);
                int ehour = Integer.parseInt(time1[9]);
                int eminite = Integer.parseInt(time1[10]);
                int emiao = Integer.parseInt(z[0]);
                // int flag =0;
                /*
                 * if(byear<=year&&bmonth<=month&&bday<=day&&bhour<=hour&&bminite<=
                 * minite&&bmiao<=miao) {
                 * if(year<=eyear&&month<=emonth&&day<=eday&&hour<=ehour&&minite<=
                 * eminite&&miao<=emiao) {
                 * System.out.println("时间为"+year+" "+month+" "+day+" "+hour+" " +minite+" "+miao+
                 * "类型"+type+"类方法为"+classfun+"具体操作为"+infomation); }
                 * 
                 * }
                 */
                String beginTime = new String(
                    byear + "-" + bmonth + "-" + bday + " " + bhour + ":" + bminite + ":" + bmiao);
                String endTime = new String(
                    eyear + "-" + emonth + "-" + eday + " " + ehour + ":" + eminite + ":" + emiao);
                String nowtime = new String(
                    year + "-" + month + "-" + day + " " + hour + ":" + minite + ":" + miao);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                  Date sd1 = df.parse(beginTime);
                  Date sd2 = df.parse(endTime);
                  Date sd3 = df.parse(nowtime);
                  if (sd1.before(sd3) && sd2.after(sd3)) {
                    System.out.println(
                        "时间为" + year + " " + month + " " + day + " " + hour + " " + minite + " "
                            + miao + " 类型" + type + " 类方法为" + classfun + " 具体操作为" + infomation);
                  }
                } catch (ParseException e) {
                  errorinfile ex = new errorinfile();
                  ex.errorinfile1();
                }



              } catch (NumberFormatException e) {
                System.out.println("m");
                IOException y = new IOException();
                y.initCause(e);
                throw y;
              }


            } else {
              System.out.println("未按照格式输入");
              throw new IOException();
            }
            break;
          case 2:
            if (line.contains(input)) {
              System.out.println("时间为" + year + " " + month + " " + day + " " + hour + " " + minite
                  + " " + miao + " 类型" + input + " 类方法为" + classfun + " 具体操作为" + infomation);
            }

            break;
          case 3:
            if (line.contains(input1)) {
              System.out.println("时间为" + year + " " + month + " " + day + " " + hour + " " + minite
                  + " " + miao + " 类型" + type + " 类方法为" + classfun + " 具体操作为" + infomation);
            }

            break;
          case 4:
            if (line.contains(input2)) {
              System.out.println("时间为" + year + " " + month + " " + day + " " + hour + " " + minite
                  + " " + miao + " 类型" + type + " 类方法为" + classfun + " 具体操作为" + infomation);
            }

            break;
          case 5:
            if (line.contains(input3)) {
              System.out.println("时间为" + year + " " + month + " " + day + " " + hour + " " + minite
                  + " " + miao + " 类型" + type + " 类方法为" + classfun + " 具体操作为" + infomation);
            }
            break;

          default:
            System.out.println("输入不合法，返回主函数");
            break;
        }
      }

    } catch (IOException e) {
      errorinfile ex = new errorinfile();
      ex.errorinfile1();
    }



  }
}
