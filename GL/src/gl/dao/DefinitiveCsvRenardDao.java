package gl.dao;

import game.Renard;
import game.SimpleRenard;
//import gl.dao.test.AbstractCsvRenardDaoTest;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVReader;

public class DefinitiveCsvRenardDao implements CsvRenardDao {

	private File file;
	private static final Logger LOGGER = Logger.getLogger(DefinitiveCsvRenardDao.class);
	private final static char SEPARATOR = ';';
	private List<Renard> renards;
	
	@Override
	public void init(File file) {
		LOGGER.debug("Initialisation du fichier");
		this.file=file;
		
		//Nouvelle lecture à chaque init. Cela permet ainsi de changer de fichier ou de le recharger.
		reloadRenards();
		
	}
	
    private void reloadRenards() {
    	 LOGGER.debug("reloadRenards");

         if (file == null) {
             throw new IllegalStateException("Le fichier est vide...");
         }

         try {
             final List<String[] > lignes = getLignesFromFile();

            
            

             renards = new ArrayList<Renard>(lignes.size());
             
             for (String[] ligne : lignes) {
                 final Renard renard= transformLigneToRenard(ligne);
                 renards.add(renard);

             
             }
         } catch (Exception e) {
             LOGGER.error("Une erreur s'est produite...", e);
         }
         
		
	}

	private List<String[] > getLignesFromFile(){
		LOGGER.debug("getLignesFromFile");

        if (file == null) {
            throw new IllegalStateException("Le fichier est vide...");
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
            csvReader.close();
        } catch (Exception e) {
            LOGGER.error("ça marche pô...", e);
        }
      
        return lignes;
    }

    private Renard transformLigneToRenard(final String [] values){
    	
    	final SimpleRenard renard = new SimpleRenard();
    	
    	
    	
    	renard.setPositionX(Integer.parseInt(values[1]));
    	renard.setPositionY(Integer.parseInt(values[2]));
    	renard.setOrientation(values[3].charAt(0));
    	renard.setTrajet(values[4]);
    	renard.setNom(values[5]);
    	
    	return renard;
    	
    }

	
	@Override
	public List<Renard> findAllRenards() {
		LOGGER.debug("Remplissage des Renards");
		
		
		
		return renards;
	}

	// Getters
	public File getFile() {
		return file;
	}		
	

}
