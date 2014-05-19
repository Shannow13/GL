package gl.dao.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import game.Lapin;
import gl.dao.CsvLapinDao;

import org.apache.log4j.*;
import org.junit.Before;
import org.junit.Test;
//import org.junit.Assert;

public abstract class AbstractCsvLapinDaoTest {
	
	
	private static final Logger LOGGER = Logger.getLogger(AbstractCsvLapinDaoTest.class);

    private final static String RESOURCES_PATH = "resources/";
    private final static String CHIENS_FILE_NAME = "lapin-2.csv";
    
    protected CsvLapinDao dao;
    
    @Before
    public void doBefore() {
        LOGGER.debug("doBefore Debut");

        final File file = new File(RESOURCES_PATH + CHIENS_FILE_NAME);
        dao.init(file);

        LOGGER.debug("doBefore Fin");
    }
    
    @Test
    public void testnbLapin(){
    	
    	LOGGER.debug("Début du Test !");
    	
    	final int nombreLapinAttendu = 10;
    	
    	final List<Lapin> Lapins = dao.findAllLapins();
    	
    	assertEquals(nombreLapinAttendu, Lapins.size());
    	
    	LOGGER.debug("Fin du Test !");
    	
    }
	
    //On teste le fait que l'objet a été créé correctement
    
    @Test
    public void testPremierLapin(){
    	LOGGER.debug("Début du Test de l'objet");
    	
    	final int position = 0;
    	final int posYAttendu = 7;
    	final char OrientAttendu = 'E';
    	
    	final List<Lapin> lapins = dao.findAllLapins();
    	final Lapin lapin = lapins.get(position);
    	
    	assertEquals(posYAttendu, lapin.getPositionY());
    	assertEquals(OrientAttendu, lapin.getOrientation());
    	
    	LOGGER.debug("Fin du Test");
    	
    	
    }
}

