package physicalObject;

public class Electron {
	private int layer = 0;
	 /**
     * 构造器
     * 
     * @param layer电子所在的层数
     */
	public Electron(int layer) {
		this.layer = layer;
	}
	 /**
     * 进行电子的跃迁
     * @param n 跃迁到第n层
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
