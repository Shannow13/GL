package game;

import java.io.Serializable;

public interface Renard extends Serializable {

			
		/**
		 * 
		 * Fonction recupérant la position de depart en X d'un renard
		 * 
		 * @return
		 */
		int getPositionX();
		
		
		/**
		 * 
		 * Fonction recuperant la position de depart en Y d'un renard
		 * 
		 * @return
		 */
		int getPositionY();
		
		
		/**
		 * 
		 * Fonction recuperant l'orientation de depart d'un renard
		 * 
		 * @return
		 */
		char getOrientation();
		
		
		/**
		 * 
		 * Fonction recuperant le trajet complet d'un renard
		 * 
		 *  @return
		 */
		String getTrajet();
		
		/**
		 * 
		 * Fonction recuperant le nom d'un renard
		 * 
		 * @return
		 */
		String getNom();

	
}
