package game;

import java.io.File;
import java.util.List;

public class DefinitiveCsvJardinDao implements CsvJardinDao {
	
	private List<String> getLignesFromFile() throws Exception {
        throw new UnsupportedOperationException("Cette methode n'est pas encore programmee.");
    }

    private Jardin transformLigneToJardin(final String ligne) throws Exception {
        throw new UnsupportedOperationException("Cette methode n'est pas encore programmee.");
    }

	@Override
	public int findX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int findY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Rocher> findRochers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Carottes> findCarottes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(File file) {
		// TODO Auto-generated method stub
		
	}
	
	
}
