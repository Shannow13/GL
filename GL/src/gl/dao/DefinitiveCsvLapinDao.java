package gl.dao;

import game.Lapin;
import game.SimpleLapin;
import gl.dao.test.AbstractCsvLapinDaoTest;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVReader;

public class DefinitiveCsvLapinDao implements CsvLapinDao {

	private File file;
	private static final Logger LOGGER = Logger.getLogger(DefinitiveCsvLapinDao.class);
	private final static char SEPARATOR = ';';
	private List<Lapin> lapins;
	
	@Override
	public void init(File file) {
		LOGGER.debug("Initialisation du fichier");
		this.file=file;
		
		//Nouvelle lecture à chaque init. Cela permet ainsi de changer de fichier ou de le recharger.
		reloadLapins();
		
	}
	
    private void reloadLapins() {
    	 LOGGER.debug("reloadLapins");

         if (file == null) {
             throw new IllegalStateException("Le fichier est nul...");
         }

         try {
             final List<String[] > lignes = getLignesFromFile();

            
            

             lapins = new ArrayList<Lapin>(lignes.size());
             
             for (String[] ligne : lignes) {
                 final Lapin lapin= transformLigneToLapin(ligne);
                 lapins.add(lapin);

             
             }
         } catch (Exception e) {
             LOGGER.error("Une erreur s'est produite...", e);
         }
         
		
	}

	private List<String[] > getLignesFromFile(){
		LOGGER.debug("getLignesFromFile");

        if (file == null) {
            throw new IllegalStateException("Le fichier est nul...");
        }

        final List<String[] > lignes = new ArrayList<String[] >();
        
        try {
            final FileReader fr = new FileReader(file);
            final CSVReader csvReader = new CSVReader(fr, SEPARATOR);
            
            String[] nextLine = null;
            while ((nextLine = csvReader.readNext()) != null) {
                int size = nextLine.length;

                // ligne vide
                if (size == 0) {
                    continue;
                }
                String debut = nextLine[0].trim();
                if (debut.isEmpty() && size == 1) {
                    continue;
                }

                // ligne de commentaire
                if (debut.startsWith("#")) {
                    continue;
                }
                lignes.add(nextLine);
            }

        } catch (Exception e) {
            LOGGER.error("aie aie aie", e);
        }

        return lignes;
    }

    private Lapin transformLigneToLapin(final String [] values) throws Exception {
    	
    	final SimpleLapin lapin = new SimpleLapin();
    	
    	
    	
    	lapin.setPositionX((values[1]));
    	lapin.setPositionY(values[2]);
    	lapin.setOrientation(values[3]);
    	lapin.setTrajet(values[4]);
    	lapin.setNom(values[5]);
    	
    }

	
	@Override
	public List<Lapin> findAllLapins() {
		LOGGER.debug("Remplissage des Lapins");
		
		
		
		return lapins;
	}

	// Getters
	public File getFile() {
		return file;
	}		
	

}
