package game;

public class SimpleCarottes implements Carottes {
	
	private int positionX;
	private int positionY;
	private int nombre;
	
	private static final long serialVersionUID = 1L;
	
	public SimpleCarottes(int positionX, int positionY, int nombre){
		this.positionX = positionX;
		this.positionY = positionY;
		this.nombre = nombre;
	}

	@Override
	public int getPositionX() {
		return this.positionX;
		
	}

	@Override
	public int getPositionY() {
		return this.positionY;
	}

	@Override
	public int getNombre() {
		return this.nombre;
	}
	

}
