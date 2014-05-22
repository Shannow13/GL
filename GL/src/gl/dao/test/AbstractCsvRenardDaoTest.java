package gl.dao.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import game.Renard;
import gl.dao.CsvRenardDao;

import org.apache.log4j.*;
import org.junit.Before;
import org.junit.Test;
//import org.junit.Assert;

public abstract class AbstractCsvRenardDaoTest {
	
	
	private static final Logger LOGGER = Logger.getLogger(AbstractCsvRenardDaoTest.class);

    private final static String RESOURCES_PATH = "resources/";
    private final static String CHIENS_FILE_NAME = "renard-2.csv";
    
    protected CsvRenardDao dao;
    
    @Before
    public void doBefore() {
        LOGGER.debug("doBefore Debut");

        final File file = new File(RESOURCES_PATH + CHIENS_FILE_NAME);
        dao.init(file);

        LOGGER.debug("doBefore Fin");
    }
    
    @Test
    public void testnbRenard(){
    	
    	LOGGER.debug("Début du Test !");
    	
    	final int nombreRenardAttendu = 10;
    	
    	final List<Renard> Renards = dao.findAllRenards();
    	
    	assertEquals(nombreRenardAttendu, Renards.size());
    	
    	LOGGER.debug("Fin du Test !");
    	
    }
	
    //On teste le fait que l'objet a été créé correctement
    
    @Test
    public void testPremierRenard(){
    	LOGGER.debug("Début du Test de l'objet");
    	
    	final int position = 0;
    	final int posYAttendu = 7;
    	final char OrientAttendu = 'E';
    	
    	final List<Renard> renards = dao.findAllRenards();
    	final Renard renard = renards.get(position);
    	
    	assertEquals(posYAttendu, renard.getPositionY());
    	assertEquals(OrientAttendu, renard.getOrientation());
    	
    	LOGGER.debug("Fin du Test");
    	
    	
    }
}

