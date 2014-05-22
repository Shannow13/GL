package editor;

import static org.apache.log4j.Logger.getLogger;
import java.io.File;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVReader;


public class EditorLevel {
	
	private static final Logger LOGGER = getLogger(EditorLevel.class);
	
	private File file;
	
	public void init(File file)
	{
		LOGGER.debug("Création fichier");
		this.file = file;
	}
	
	public String fileName()
	{
		return file.getName();
	}
	
	private void writeCarotte(){
		//TODO
	}
	
	private void writeRocher(){
		//TODO
	}
	
	private void writeFox(){
		//TODO
	}
	
	private void writeJardin(){
		//TODO
	}
}
