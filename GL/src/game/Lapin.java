package game;

import java.io.Serializable;

public interface Lapin extends Serializable {
	
	/**
	 * 
	 * Fonction recupérant la position de depart en X d'un lapin
	 * 
	 * @return
	 */
	int getPositionX();
	
	
	/**
	 * 
	 * Fonction recuperant la position de depart en Y d'un lapin
	 * 
	 * @return
	 */
	int getPositionY();
	
	
	/**
	 * 
	 * Fonction recuperant l'orientation de depart d'un lapin
	 * 
	 * @return
	 */
	char getOrientation();
	
	
	/**
	 * 
	 * Fonction recuperant le trajet complet d'un lapin
	 * 
	 *  @return
	 */
	String getTrajet();
	
	/**
	 * 
	 * Fonction recuperant le nom d'un lapin
	 * 
	 * @return
	 */
	String getNom();
}
