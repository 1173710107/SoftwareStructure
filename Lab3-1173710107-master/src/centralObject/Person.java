package centralObject;


public class Person {
    private String Name;
	 /**
     * ������
     * 
     * @param name�˵�����
     */
	public Person(String name) {
		super();
		this.Name = name;
	}
	 /**
     * ��ȡ�˵�����
     * 
     * @return �����˵�����
     */
	public String getName() {
		return Name;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)      
			return true; 
		if (obj == null)    
			return false;
		if (getClass() != obj.getClass())                
			return false;
		Person other = (Person) obj;     
		if (!Name.equals(other.Name)) 
			return false;
		return true;
	}

}
