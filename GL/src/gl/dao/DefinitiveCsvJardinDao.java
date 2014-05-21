package gl.dao;

import game.Carottes;
import game.Jardin;
import game.Rocher;
import game.SimpleCarottes;
import game.SimpleJardin;
import game.SimpleRocher;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVReader;

public class DefinitiveCsvJardinDao implements CsvJardinDao {
	
	private File file;
	private static final Logger LOGGER = Logger.getLogger(DefinitiveCsvJardinDao.class);
	private final static char SEPARATOR = ';';
	private SimpleJardin lvljardin;
	private ArrayList<Carottes> carottes;
	private ArrayList<Rocher> rochers;
	
	
	@Override
	public void init(File file) {
		LOGGER.debug("Initialisation du fichier");
		this.file=file;
		
		//Nouvelle lecture à chaque init. Cela permet ainsi de changer de fichier ou de le recharger.
		reloadJardin();
		
	}
	
	
	
	private void reloadJardin() {
	
		LOGGER.debug("reloadJardin");
	

        if (file == null) {
            throw new IllegalStateException("Le fichier est vide...");
       
        }try{
        	
        	lvljardin = new SimpleJardin();
		final List<String[] > lignes = getLignesFromFile();
		
			LOGGER.debug("On a transformé le CSV en lignes");
		
			for(String[] ligne : lignes){
				if(ligne[0].charAt(0) == 'J'){
					LOGGER.debug("Jardin");
					continue;
				}
				if(ligne[0].charAt(0) == 'C'){
					LOGGER.debug("Carotte");
					addCarottes(ligne,carottes);
					LOGGER.debug("Carotte ajoutée !");
				}
				if(ligne[0].charAt(0) == 'R'){
					addRochers(ligne, rochers);
					LOGGER.debug("Rocher ajouté !");
				}
			}
		
		
			transformLigneToJardin(lignes.get(0), lvljardin, carottes, rochers);
		
        }catch(Exception e){
        	LOGGER.error("fuck");
        }
	}

	private ArrayList<Carottes> addCarottes(String[] ligne, ArrayList<Carottes> carotte){
		
		final SimpleCarottes carotteplus = new SimpleCarottes();
		LOGGER.debug("Début de la création");
		
		carotteplus.setPositionX(Integer.parseInt(ligne[1]));
		carotteplus.setPositionY(Integer.parseInt(ligne[2]));
		carotteplus.setNombre(Integer.parseInt(ligne[3]));
		
		LOGGER.debug("ajout à la liste");
		if(carottes == null)
		{
			carottes = new ArrayList<Carottes>();
			LOGGER.debug("carottes NULL");
		}
		
			carottes.add(carotteplus);
			LOGGER.debug("A été ajouté");
			
	return carotte;
	}

private ArrayList<Rocher> addRochers(String[] ligne, ArrayList<Rocher> rochers){
	
	final SimpleRocher rocherplus = new SimpleRocher();
	
	LOGGER.debug("Création de l'objet Rocher");
	rocherplus.setPositionX(Integer.parseInt(ligne[1]));
	rocherplus.setPositionY(Integer.parseInt(ligne[2]));
	
	LOGGER.debug("Ajout à la liste");
	if(rochers == null){
		
		rochers = new ArrayList<Rocher>();
		LOGGER.debug("rochers NULL");
	}
	rochers.add(rocherplus);
	LOGGER.debug("A été ajouté");
	
	return rochers;
}



	private List<String[]> getLignesFromFile(){
	
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


    private Jardin transformLigneToJardin(final String[] lignes, final SimpleJardin jardin, final ArrayList<Carottes> carottes, final ArrayList<Rocher> rochers){
    	
    		
    		jardin.setSizeX(Integer.parseInt(lignes[1]));
    		jardin.setSizeY(Integer.parseInt(lignes[2]));
    		jardin.setCarottes(carottes);
    		jardin.setRochers(rochers);
    		System.out.println(jardin.sizeX);
    		System.out.println(jardin.sizeY);
    		
		return jardin;
    }
    	
  


	@Override
	public Jardin findJardin() {
		LOGGER.debug("Début du remplissage du Jardin");
		
		//lvljardin = transformLigneToJardin
		
		return lvljardin;
	}
}
	