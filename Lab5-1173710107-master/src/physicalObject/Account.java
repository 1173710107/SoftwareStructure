package physicalObject;

public class Account {
  final String name;
  int age;
  final String sex;

  /**
   * 获取该用户的name.
   * 
   * @return 该用户的name.
   */
  public String getName() {
    return this.name;
  }

  /**
   * 构造器.
   * @param name 人的名称
   * @param age 人的年龄
   * @param sex 人的性别
   */
  public Account(String name, int age, String sex) {
    this.name = name;
    this.age = age;
    this.sex = sex;
  }

  @Override // 输出account的信息
  public String toString() {
    return "Account :name=" + name + ", age=" + age + ", sex=" + sex;
  }

}
