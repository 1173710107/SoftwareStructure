package physicalObject;

public class Electron {
	private int layer = 0;
	 /**
     * ������
     * 
     * @param layer�������ڵĲ���
     */
	public Electron(int layer) {
		this.layer = layer;
	}
	 /**
     * ���е��ӵ�ԾǨ
     * @param n ԾǨ����n��
     */
     public void transit(int n)
     {
    	 this.layer = n;
     }
    
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + layer;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		Electron x = (Electron) obj;
		if (layer != x.layer)
		{
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "the layer :"+layer;
	}

}
