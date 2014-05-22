package game;

import java.util.List;

public class SimpleJardin implements Jardin {

	// attributs
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int sizeX;
	public int sizeY;
	public int indicelvl;
	public int debloque;
	private List<Rocher> rochers;
	private List<Carottes> carottes;
	
	
	
	
	public SimpleJardin() {
	}
	
	public SimpleJardin(int sizeX, int sizeY, List<Rocher> rochers, List<Carottes> carottes) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.rochers = rochers;
		this.carottes = carottes;
	}
	
	
	
	//getters - setters
	@Override
	public int getSizeX() {
		return this.sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}
	
	@Override
	public int getSizeY() {
		return this.sizeY;
	}
	
	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	@Override
	public int getIndicelvl(){
		return this.indicelvl;
	}
	
	public void setIndicelvl(int indicelvl){
		this.indicelvl = indicelvl;
	}
	
	@Override
	public int getDebloque(){
		return this.debloque;
	}
	public void setDebloque(int debloque){
		
		this.debloque = debloque;
	}
	@Override
	public List<Rocher> getRochers() {
		return this.rochers;
	}

	public void setRochers(List<Rocher> rochers) {
		this.rochers = rochers;
	}
	
	@Override
	public List<Carottes> getCarottes() {
		return this.carottes;
	}

	public void setCarottes(List<Carottes> carottes) {
		this.carottes = carottes;
	}
}
