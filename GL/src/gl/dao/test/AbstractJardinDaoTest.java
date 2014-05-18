package gl.dao.test;
import gl.dao.CsvJardinDao;

import java.io.File;

import org.apache.log4j.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


public abstract class AbstractJardinDaoTest {
	 private static final Logger LOGGER = Logger.getLogger(AbstractJardinDaoTest.class);
	 
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
		 
		 final int position = 0;
		 final int sizeXAttendue = 6;
		 final int sizeYAttedue = 5;
	
		
		 
	 }
	 
	 

}
