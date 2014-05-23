package editor;

import static org.apache.log4j.Logger.getLogger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import org.apache.log4j.Logger;

public class FileEditor {
	private static final Logger LOGGER = getLogger(FileEditor.class);
	 
	private final static String RESOURCES_PATH = "resources/";
	private final static String DAO_PATH = "resources/DAO/";

	File file;	//jardin
	File fFile;	// fox
	File lFile;	// lapin
	FileWriter wFile;	//jardin
	FileWriter fWFile;	// fox
	FileWriter lWFile;	// lapin
	
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
				fFile = new File(DAO_PATH + "renardEdite-" + num + ".csv");
				lFile = new File(DAO_PATH + "lapinEdite-"+num + ".csv");
				
			} catch (IOException e) {
				e.printStackTrace();
				LOGGER.debug("Le fichier � foir�!");
				LOGGER.error("Erreur de cr�ation du gestion");
			}
		
			try{
				wFile = new FileWriter(file,true);
				fWFile = new FileWriter(fFile,true);
				lWFile = new FileWriter(lFile,true);
				
			}catch(IOException er){
				er.printStackTrace();
				LOGGER.error("Les fichier d'�criture n'ont pas pu �tre ouvert");
				return;
			}
	}
	
}
