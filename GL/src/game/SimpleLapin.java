package game;

public class SimpleLapin implements Lapin {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/*attributs*/
	int positionX;
	int positionY;
	char orientation;
	String trajet;
	String nom;
	
	
	
	
	/*constructeur*/
	public SimpleLapin(int positionX, int positionY, char orientation, String trajet, String nom) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.orientation = orientation;
		this.trajet = trajet;
		this.nom = nom;
		
	}
	
	
	
	
	@Override
	public String toString(){
		return("SimpleLapin = [posX = "+ positionX + " posY = " + positionY +" trajet = " + trajet + " orientation = " + orientation + " nom = " + nom);
	}
	
	
	
	
	
	
	
	/*getters - setters*/
	@Override
	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	@Override
	public int getPositionY() {
		return positionY;
	}
	
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	@Override
	public char getOrientation() {
		return orientation;
	}
	
	public void setOrientation(char orientation) {
		this.orientation = orientation;
	}

	@Override
	public String getTrajet() {
		return trajet;
	}
	
	public void setTrajet(String trajet) {
		this.trajet = trajet;
	}

	@Override
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
