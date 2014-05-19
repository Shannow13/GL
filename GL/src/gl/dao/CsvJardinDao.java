package gl.dao;

import java.io.File;

/**
 * 
 * DAO pour les Jardins en CSV.
 *
 */
public interface CsvJardinDao extends JardinDao {
	/**
	 * Initialisation du DAO.
	 * 
	 * @param file
	 */
	public void init(File file);
	
}
