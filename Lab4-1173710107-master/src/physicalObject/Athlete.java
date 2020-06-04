package physicalObject;

public class Athlete implements Comparable<Athlete>{
    final String name;
    int number;
    final String nation;
    int age;
    double grade;
	
	@Override
	public String toString() {
		return "Athlete :name=" + name + ", number=" + number + ", nation=" + nation + ", age=" + age + ", grade="
				+ grade ;
	}
	
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
		{
			return true;
		}
		else if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
			return false;
		Athlete x = (Athlete) obj;
		if (name == null) {
			if (x.name != null)
			{
				return false;
			}
		}
		else if (!name.equals(x.name))
		{
			return false;
		}
		return true;
	}

	 /**
     * ������
     * 
     * @param name �˶�Ա������
     * @param number �˶�Ա�ĺ���
     * @param nation �˶�Ա�Ĺ���
     * @param age �˶�Ա������
     * @param grade �˶�Ա�ĳɼ�
     */
	public Athlete(String name, int number, String nation, int age, double grade) {
		super();
		this.name = name;
		this.number = number;
		this.nation = nation;
		this.age = age;
		this.grade = grade;
	}
    public String getname()
    {
    	return name;
    }

	@Override
	public int compareTo(Athlete o) {
		if(this.grade>o.grade)
		{
			return 1;
		}
		else if(this.grade==o.grade)
		{
			return 0;
		}
		else
		{
			return -1;
		}
	}

}
