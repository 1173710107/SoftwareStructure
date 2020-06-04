package centralObject;

public class Nucleus {
  String name;

  @Override
  public String toString() {
    return "Nucleus :name=" + name;
  }

  /**
   * 构造器.
   * 
   * @param name the name of the nucleus.
   */
  public Nucleus(String name) {
    this.name = name;
  }

}
