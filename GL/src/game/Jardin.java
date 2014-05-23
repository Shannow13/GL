package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface Jardin extends Serializable {

	
	
	int getSizeX();
	int getSizeY();
	int getIndicelvl();
	int getDebloque();
	
	ArrayList<Rocher> getRochers();
	ArrayList<Carottes> getCarottes();
	

	
	
	
}
