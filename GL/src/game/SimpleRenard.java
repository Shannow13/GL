package game;

public class SimpleRenard implements Renard {

	
private static final long serialVersionUID = 1L;
	
	
	
	/*attributs*/
	private int positionX;
	private int positionY;
	private char orientation;
	private String trajet;
	private String nom;
	
	
	
	
	/*constructeur*/
	public SimpleRenard(){
		
	}
	
	public SimpleRenard(int positionX, int positionY, char orientation, String trajet, String nom) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.orientation = orientation;
		this.trajet = trajet;
		this.nom = nom;
		
	}
	
	
	
	
	@Override
	public String toString(){
		return("SimpleLapin = [posX = "+ positionX + " posY = " + positionY + " orientation = " + orientation + " nom = " + nom);
	}
	
	
	
	
	
	
	
	/*getters - setters*/
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

	@Override
	public char getOrientation() {
		return this.orientation;
	}
	
	public void setOrientation(char orientation) {
		this.orientation = orientation;
	}
	
	@Override
	public String getTrajet() {
		return this.trajet;
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
