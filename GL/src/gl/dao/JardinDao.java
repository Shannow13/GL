package gl.dao;

import game.Carottes;
import game.Rocher;

import java.util.List;

public interface JardinDao {
	
	/**
	 * Renvoie le Jardin
	 */
	int findX();
	int findY();
	
	List<Rocher> findRochers();
	List<Carottes> findCarottes();
	
}
