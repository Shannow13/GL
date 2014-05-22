package gl.dao;

import java.io.File;

public interface CsvRenardDao extends RenardDao {

	/**
	 * Initialisation du DAO.
	 * 
	 * @param file
	 */
	public void init(File file);
	
	
}
