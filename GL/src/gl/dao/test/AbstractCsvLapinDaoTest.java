package gl.dao.test;

import java.io.File;
import java.util.List;

import game.Lapin;
import gl.dao.CsvLapinDao;

import org.apache.log4j.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public abstract class AbstractCsvLapinDaoTest {
	
	
	private static final Logger LOGGER = Logger.getLogger(AbstractCsvLapinDaoTest.class);

    private final static String RESOURCES_PATH = "resources/";
    private final static String CHIENS_FILE_NAME = "lapin-1.csv";
    
    protected CsvLapinDao dao;
    
    @Before
    public void doBefore() {
        LOGGER.debug("doBefore Debut");

        final File file = new File(RESOURCES_PATH + CHIENS_FILE_NAME);
        dao.init(file);

        LOGGER.debug("doBefore Fin");
    }
    
    @Test
    public void testLapin(){
    	
    	LOGGER.debug("Début du Test !");
    	
    	final int nombreLapinAttendu = 1;
    	
    	final List<Lapin> Lapins = dao.findAllLapins();
    	
    	Assert.assertEquals(nombreLapinAttendu, Lapins.size());
    	
    	LOGGER.debug("Fin du Test !");
    	
    }
	

}

