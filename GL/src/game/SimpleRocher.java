package game;

public class SimpleRocher implements Rocher {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int positionX;
	private int positionY;
	
	
	public SimpleRocher(){
		
		
	}
	
	public SimpleRocher(int positionX, int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	
	
	
	@Override
	public int getPositionX() {
		return this.positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	@Override
	public int getPositionY() {
		return this.positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

}
