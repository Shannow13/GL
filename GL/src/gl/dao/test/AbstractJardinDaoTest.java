package gl.dao.test;
import static org.apache.log4j.Logger.getLogger;
import static org.junit.Assert.*;
import game.Jardin;
import gl.dao.DefinitiveCsvJardinDao;

import java.io.File;

import org.apache.log4j.*;
import org.junit.Before;
import org.junit.Test;
//import org.junit.Assert;


public abstract class AbstractJardinDaoTest {
	 private static final Logger LOGGER = getLogger(AbstractJardinDaoTest.class);
	 
	 private final static String RESOURCES_PATH = "resources/";
	 private final static String JARDIN_FILE_NAME = "jardin-1.csv";
	 
	 protected DefinitiveCsvJardinDao dao;
	 
	 @Before
	    public void doBefore() {
		 
		 //On initialise le fichier utilisé par les tests
	        LOGGER.debug("doBefore Debut");

	        final File file = new File(RESOURCES_PATH + JARDIN_FILE_NAME);
	        dao.init(file);

	        LOGGER.debug("doBefore Fin");
	    }
	 
	  
	 
	 @Test
	 public void tailleJardin(){
		 // On teste la taille du jardin pour vérifier la bonne construction du Jardin
		 LOGGER.debug("Test de la Taille du Jardin");
		 
		 final int sizeXAttendue = 6;
		 final int sizeYAttendue = 5;
		 final int lvlAttendu = 1;
		 final int debloqueAttendu = 1;
		 final Jardin jardins = dao.findJardin();
		 
		 if(jardins != null)
			 LOGGER.debug("Jardin alloué");
		 
		 assertEquals(sizeXAttendue, jardins.getSizeX());
		 assertEquals(sizeYAttendue, jardins.getSizeY());
		 assertEquals(lvlAttendu, jardins.getIndicelvl());
		 assertEquals(debloqueAttendu, jardins.getDebloque());
		 
		 LOGGER.debug("Fin du Test de la taille du Jardin");	 
			
		 
	 }
	 
	 @Test
	 public void testnbCarottes(){
		 //On teste le nombre de carottes de l'objet afin de vérifier sa construction 
		 int nbCarottesAttendues = 2;
		 
		 final Jardin jardins = dao.findJardin();
		 
		 assertEquals(nbCarottesAttendues, jardins.getCarottes().size());
		 
	 }
	 @Test
	 public void testplaceCarotte(){
		 //On teste l'emplacement des carottes et leur nombre afin de vérifier la bonne construction de l'objet.
		 int positionXattendue = 4;
		 int positionYattendue = 2;
		 int nbAttendu = 1;
		 
		 final Jardin jardins = dao.findJardin();
		 
		 assertEquals(positionXattendue, jardins.getCarottes().get(0).getPositionX());
		 assertEquals(positionYattendue, jardins.getCarottes().get(0).getPositionY());
		 assertEquals(nbAttendu, jardins.getCarottes().get(0).getNombre());
		 
	 }
	 
}
