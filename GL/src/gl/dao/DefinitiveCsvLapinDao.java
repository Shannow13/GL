package gl.dao;

import game.Lapin;

import java.io.File;
import java.util.List;

public class DefinitiveCsvLapinDao implements CsvLapinDao {

	private File file;
	
	@Override
	public void init(File file) {
		this.file=file;
		
	}

	
	@Override
	public List<Lapin> findAllLapins() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Cette methode n'est pas encore programmee.");

	}

	// Getters
	public File getFile() {
		return file;
	}		
	

}
