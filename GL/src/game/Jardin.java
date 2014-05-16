package game;

import java.io.Serializable;
import java.util.List;

public interface Jardin extends Serializable {

	
	
	int getSizeX();
	int getSizeY();
	
	List<Rocher> getRochers();
	List<Carottes> getCarottes();
	
	
	
}
