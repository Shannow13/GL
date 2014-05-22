package gl.dao;

import java.util.ArrayList;
import java.util.List;
import game.Renard;
import game.SimpleRenard;

import org.apache.log4j.Logger;



public class BidonRenardDao implements RenardDao {

	
	private static final Logger LOGGER = Logger.getLogger(BidonRenardDao.class);

	
	
	@Override
	public List<Renard> findAllRenards() {
		
		LOGGER.debug("find All Renards : début");
		
		
		final ArrayList<Renard> renards = new ArrayList<Renard>();
		
		final SimpleRenard fox = new SimpleRenard(1,1, 'E', "AADADAGA", "Fox");
		renards.add(fox);
		
		
		LOGGER.debug("find all Renards : fin");
		
		return renards;
	}

}
