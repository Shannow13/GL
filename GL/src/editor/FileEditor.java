package editor;

import static org.apache.log4j.Logger.getLogger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import org.apache.log4j.Logger;

public class FileEditor {
	private static final Logger LOGGER = getLogger(FileEditor.class);
	 
	private final static String RESOURCES_PATH = "resources/";

	File file;
	FileWriter wFile;
	
	private int num;
	
	public FileEditor()
	{
		LOGGER.debug("D�but cr�ation");
		num = 2;
		
		file = new File(RESOURCES_PATH + "jardinEdite" + num + ".csv");
		
			try {
				while(!file.createNewFile())
				{
					num++;
					file = new File(RESOURCES_PATH + "jardinEdite-" + num + ".csv");
				}
				LOGGER.debug("Fichier bien cr�er avec le num�ro : "+num);
			} catch (IOException e) {
				e.printStackTrace();
				LOGGER.debug("Le fichier � foir�!");
				LOGGER.error("Erreur de cr�ation du gestion");
			}
		
			try{
				wFile = new FileWriter(file,true);
			}catch(IOException er){
				er.printStackTrace();
			}
	}
	
}
