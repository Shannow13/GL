package game;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;



public class BidonLapinDao implements LapinDao {

	
	private static final Logger LOGGER = Logger.getLogger(BidonLapinDao.class);

	
	
	@Override
	public List<Lapin> findAllLapins() {
		
		LOGGER.debug("find All Lapins : début");
		
		
		final ArrayList<Lapin> lapins = new ArrayList<Lapin>();
		
		final SimpleLapin bunny = new SimpleLapin(1,1, 'E', "AADADAGA", "Bunny");
		lapins.add(bunny);
		
		
		LOGGER.debug("find all Lapins : fin");
		
		return lapins;
	}

}
