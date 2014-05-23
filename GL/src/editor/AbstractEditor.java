package editor;

import static org.apache.log4j.Logger.getLogger;

import java.io.File;
import java.io.IOException;


import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractEditor {
	private static final Logger LOGGER = getLogger(AbstractEditor.class);
	 
	private final static String RESOURCES_PATH = "resources/";
	private EditorLevel edit = new EditorLevel();
	
	private int num;
	
	@Before
	private void doBefore()
	{
		LOGGER.debug("D�but cr�ation");
		num = 2;
		
		File file = new File(RESOURCES_PATH + "jardinEdite" + num + ".csv");
		
			try {
				while(!file.createNewFile())
				{
					num++;
					file = new File(RESOURCES_PATH + "jardinEdite-" + num + ".csv");
					edit.init(file);
				}
				LOGGER.debug("Fichier bien cr�er avec le num�ro : "+num);
			} catch (IOException e) {
				e.printStackTrace();
				LOGGER.debug("Le fichier � foir�!");
				LOGGER.error("Erreur de cr�ation du gestion");
			}
			
			
	}
	
	@Test
	public void deuxiemeFichier(){
		final String nomAttendu = "jardinEdite-2.csv";
		
		if(edit.fileName() != nomAttendu)
			LOGGER.debug("Pas le bon nom!!");
		
		
	}
	
}
