package gl.dao;

import game.Renard;

import java.util.List;

public interface RenardDao {

	/**
	 * Fonction revoyant la liste complete des renards
	 * 
	 * RG 1 : Renvoie la liste complete des renards.
	 * 
	 * RG2 : Renvoie une liste vide s'il n'y a aucun renard (n'est pas suppose arriver)
	 * 
	 * @return
	 */
	List<Renard> findAllRenards();
}
