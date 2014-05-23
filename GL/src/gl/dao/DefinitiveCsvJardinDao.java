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

		//Nouvelle lecture � chaque init. Cela permet ainsi de changer de fichier ou de le recharger.
		reloadJardin();

	}



	private void reloadJardin() {

		LOGGER.debug("reloadJardin");


		if (file == null) {
			throw new IllegalStateException("Le fichier est vide...");

		}try{
			//On construit le Jardin

			lvljardin = new SimpleJardin();
			//Transformation du CSV en liste de String[] afin de cr�er un jardin correct
			final List<String[] > lignes = getLignesFromFile();

			LOGGER.debug("On a transform� le CSV en lignes");

			for(String[] ligne : lignes){
				if(ligne[0].charAt(0) == 'J'){
					LOGGER.debug("Jardin");
					//on saute la premi�re ligne qui contient la taille du jardin, elle sera trait�e plus loin
					continue;
				}
				if(ligne[0].charAt(0) == 'C'){
					LOGGER.debug("Carotte");
					//D�s qu'on voit une carotte on l'ajoute � la liste 
					addCarottes(ligne,carottes);
					LOGGER.debug("Carotte ajout�e !");
				}
				if(ligne[0].charAt(0) == 'R'){
					addRochers(ligne, rochers);
					//D�s qu'on voit un rocher on l'ajoute � la liste de rochers
					LOGGER.debug("Rocher ajout� !");
				}
			}

			//on transforme le tout en jardin
			transformLigneToJardin(lignes.get(0), lvljardin, carottes, rochers);

		}catch(Exception e){
			LOGGER.error("fuck");
		}
	}

	private ArrayList<Carottes> addCarottes(String[] ligne, ArrayList<Carottes> carotte){

		//On cr�e un nouvel objet carotte
		final SimpleCarottes carotteplus = new SimpleCarottes();
		LOGGER.debug("D�but de la cr�ation");
		//On initialise ses variables
		carotteplus.setPositionX(Integer.parseInt(ligne[1]));
		carotteplus.setPositionY(Integer.parseInt(ligne[2]));
		carotteplus.setNombre(Integer.parseInt(ligne[3]));

		LOGGER.debug("ajout � la liste");
		if(this.carottes == null)
		{
			//Si la liste pass�e en param�tre est nulle on la cr�e
			this.carottes = new ArrayList<Carottes>();
			LOGGER.debug("carottes NULL");
		}
		//On ajoute l'objet � la liste
		this.carottes.add(carotteplus);
		LOGGER.debug("A �t� ajout�");
		// on retourne la Liste
		return this.carottes;
	}

	private ArrayList<Rocher> addRochers(String[] ligne, ArrayList<Rocher> rochers){
		//M�me principe que pour la carotte
		final SimpleRocher rocherplus = new SimpleRocher();

		LOGGER.debug("Cr�ation de l'objet Rocher");
		rocherplus.setPositionX(Integer.parseInt(ligne[1]));
		rocherplus.setPositionY(Integer.parseInt(ligne[2]));

		LOGGER.debug("Ajout � la liste");
		if(this.rochers == null){

			this.rochers = new ArrayList<Rocher>();
			LOGGER.debug("rochers NULL");
		}
		this.rochers.add(rocherplus);
		LOGGER.debug("A �t� ajout�");

		return this.rochers;
	}



	private List<String[]> getLignesFromFile(){

		LOGGER.debug("getLignesFromFile");

		if (file == null) {
			throw new IllegalStateException("Le fichier est vide...");
		}

		final List<String[] > lignes = new ArrayList<String[] >();

		try {
			// on ouvre le fichier et on se sert de csvreader afin de le transformer en liste de String[]
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
			LOGGER.error("�a marche p�...", e);
		}

		return lignes;
	}


	private Jardin transformLigneToJardin(final String[] lignes, final SimpleJardin jardin, final ArrayList<Carottes> carottes, final ArrayList<Rocher> rochers){
		//On cr�e le Jardin

		jardin.setSizeX(Integer.parseInt(lignes[1]));
		jardin.setSizeY(Integer.parseInt(lignes[2]));
		jardin.setIndicelvl(Integer.parseInt(lignes[3]));
		jardin.setDebloque(Integer.parseInt(lignes[4]));
		jardin.setCarottes(carottes);
		jardin.setRochers(rochers);
		System.out.println(jardin.sizeX);
		System.out.println(jardin.sizeY);

		return jardin;
	}




	@Override
	public Jardin findJardin() {
		LOGGER.debug("D�but du remplissage du Jardin");



		return lvljardin;
	}
}
