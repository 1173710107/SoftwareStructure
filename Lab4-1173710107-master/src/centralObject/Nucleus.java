package centralObject;

public class Nucleus {
     String name;
     
	@Override
	public String toString() {
		return "Nucleus :name=" + name;
	}
	 /**
     * ������
     * 
     * @param the name of the nucleus.
     */
	public Nucleus(String name) {
		this.name = name;
	}

}
