package physicalObject;
public class Account {
    final String name;
    int age;
    final String sex;
	 /**
     * ��ȡ���û���name
     * 
     * @return ���û���name.
     */
    public String getName()
    {
    	return this.name;
    }

	 /**
     * ������
     * 
     * @param name �˵�����
     * @param age �˵�����
     * @param sex �˵��Ա�
     */
	public Account(String name, int age, String sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
	@Override//���account����Ϣ
	public String toString() {
		return "Account :name=" + name + ", age=" + age + ", sex=" + sex ;
	}

}
