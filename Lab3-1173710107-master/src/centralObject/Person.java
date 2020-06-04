package centralObject;


public class Person {
    private String Name;
	 /**
     * 构造器
     * 
     * @param name人的名字
     */
	public Person(String name) {
		super();
		this.Name = name;
	}
	 /**
     * 获取人的名字
     * 
     * @return 返回人的名字
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
