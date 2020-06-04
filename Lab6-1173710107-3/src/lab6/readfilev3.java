package lab6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class readfilev3 {
  private static Map<Integer, monkey> monkeys = new ConcurrentHashMap<Integer, monkey>();
  private static Map<Integer, ladder> ladders = new ConcurrentHashMap<Integer, ladder>();

  public static void main(String[] args) {
    Logger logger = Logger.getLogger(readfilev3.class);
    logger.setLevel(Level.INFO);
    Scanner scanner = new Scanner(System.in);
    int ladderNumber = 0;
    int rungNumber = 0;
    String ladderNumberRegex = "n=([0-9]+)";
    String rungNumberRegex = "h=([0-9]+)";
    String monkeyRegex = "monkey=<([0-9]+),([0-9]+),([LR]->[LR]),([0-9]+)>";
    Pattern ladderNumberPattern = Pattern.compile(ladderNumberRegex);
    Pattern rungNumberPattern = Pattern.compile(rungNumberRegex);
    Pattern monkeyPattern = Pattern.compile(monkeyRegex);
    System.out.println("请输入123编号选择！");
    int input = 0;
    try {
      input = scanner.nextInt();
    } catch (Exception e) {
      System.exit(0);
    }
    scanner.close();
    List<String> lines = new ArrayList<String>();
    if (input == 1) {
      lines = readFile("txt/Competition_1.txt");
    } else if (input == 2) {
      lines = readFile("txt/Competition_2.txt");
    } else if (input == 3) {
      lines = readFile("txt/Competition_3.txt");
    } else {
      System.out.println("输入错误！！！");
      System.exit(0);
    }

    int lineNumber = 0;
    for (String line : lines) {
      lineNumber++;
      if (line.equals("")) {
        continue;
      }
      Matcher ladderNumberMatcher = ladderNumberPattern.matcher(line);
      Matcher rungNumberMatcher = rungNumberPattern.matcher(line);
      Matcher monkeyMatcher = monkeyPattern.matcher(line);
      if (ladderNumberMatcher.find()) {
        ladderNumber = Integer.parseInt(ladderNumberMatcher.group(1));
      } else if (rungNumberMatcher.find()) {
        rungNumber = Integer.parseInt(rungNumberMatcher.group(1));
      } else if (monkeyMatcher.find()) {
        int monkeyNumber = Integer.parseInt(monkeyMatcher.group(2));
        int direction = 0;
        if (monkeyMatcher.group(3).equals("L->R")) {
          direction = 1;
        } else {
          direction = -1;
        }
        int speed = Integer.parseInt(monkeyMatcher.group(4));
        int sleep = Integer.parseInt(monkeyMatcher.group(1));
        monkey monkey = new monkey(monkeyNumber, direction, speed, sleep);
        monkeys.put(monkeyNumber, monkey);
      } else {
        System.out.println("读入文件语法有错误，请重新输入！！");
        System.exit(0);

      }
    }

    for (int i = 1; i <= ladderNumber; ++i) {
      ladder ladder = new ladder(ladderNumber, rungNumber);
      ladders.put(ladderNumber, ladder);
    }

    for (Map.Entry<Integer, monkey> monkeyEntry : monkeys.entrySet()) {
      monkey tempMonkey = monkeyEntry.getValue();
      Thread monkeyThread = new Thread(tempMonkey);
      monkeyThread.start();
    }

    int sumNumber = monkeys.size();
    keepfair x = new keepfair(sumNumber);
    Thread timeThread = new Thread(x);
    timeThread.start();
  }

  private static List<String> readFile(String filePath) {

    ArrayList<String> l = new ArrayList<String>();
    File file = new File(filePath);
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(file));
      String tempString = null;
      while ((tempString = reader.readLine()) != null) {
        l.add(tempString);
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    }
    return l;
  }

  public static Map<Integer, monkey> getMonkeys() {
    return monkeys;
  }

  public static void setMonkeys(Map<Integer, monkey> monkeys) {
    readfilev3.monkeys = monkeys;
  }

  public static Map<Integer, ladder> getLadders() {
    return ladders;
  }

  public static void setLadders(Map<Integer, ladder> ladders) {
    readfilev3.ladders = ladders;
  }



}
