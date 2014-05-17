package gl.dao;

import java.io.File;

public interface CsvLapinDao extends LapinDao {

	/**
	 * Initialisation du DAO.
	 * 
	 * @param file
	 */
	public void init(File file);
	
	
}
