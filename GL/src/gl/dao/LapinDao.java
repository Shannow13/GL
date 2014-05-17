package gl.dao;

import game.Lapin;

import java.util.List;

public interface LapinDao {

	/**
	 * Fonction revoyant la liste complete des lapins
	 * 
	 * RG 1 : Renvoie la liste complete des lapins.
	 * 
	 * RG2 : Renvoie une liste vide s'il n'y a aucun lapin (n'est pas suppose arriver)
	 * 
	 * @return
	 */
	List<Lapin> findAllLapins();
}
