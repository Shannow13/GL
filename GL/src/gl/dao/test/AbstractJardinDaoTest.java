package gl.dao.test;
import static org.apache.log4j.Logger.getLogger;
import static org.junit.Assert.*;
import game.Jardin;
import gl.dao.CsvJardinDao;

import java.io.File;

import org.apache.log4j.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


public abstract class AbstractJardinDaoTest {
	 private static final Logger LOGGER = getLogger(AbstractJardinDaoTest.class);
	 
	 private final static String RESOURCES_PATH = "resources/";
	 private final static String JARDIN_FILE_NAME = "jardin-1.csv";
	 
	 protected CsvJardinDao dao;
	 
	 @Before
	    public void doBefore() {
	        LOGGER.debug("doBefore Debut");

	        final File file = new File(RESOURCES_PATH + JARDIN_FILE_NAME);
	        dao.init(file);

	        LOGGER.debug("doBefore Fin");
	    }
	 
	 //TODO Finir le Test 
	 
	 @Test
	 public void tailleJardin(){
		 
		 LOGGER.debug("Test de la Taille du Jardin");
		 
		 final int sizeXAttendue = 6;
		 final int sizeYAttendue = 5;
		 
		 final Jardin jardins = dao.findJardin();
		 
		 if(jardins != null)
			 LOGGER.debug("Jardin allou�");
		 
		 assertEquals(sizeXAttendue, jardins.getSizeX());
		 assertEquals(sizeYAttendue, jardins.getSizeY());
		 
		 LOGGER.debug("Fin du Test de la taille du Jardin");	 
			
		 
	 }
	 
	/* @Test
	 public void testCarottes(){

		 final int position = 0;
		 final int positionXAttendue = 4;
		 final int positionYAttendue = 2;
		 
		 final List<Carotte> carottes = dao.completeLapins();
		 
		 assertEquals(positionXAttendue, )
		 */
}
