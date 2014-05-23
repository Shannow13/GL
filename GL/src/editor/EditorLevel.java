package editor;

import static org.apache.log4j.Logger.getLogger;
import game.Carottes;
import game.Rocher;
import game.SimpleJardin;

import java.io.FileWriter;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVWriter;



public class EditorLevel{
	
	private static final Logger LOGGER = getLogger(EditorLevel.class);
	
	private FileWriter wFile;
	private FileEditor fichier;
	
	private CSVWriter writer;
	
	
	public EditorLevel(SimpleJardin jardin, ArrayList<Carottes> c, ArrayList<Rocher> r )
	{
		fichier = new FileEditor();
		this.wFile = fichier.wFile;
		
		LOGGER.debug("Le fichier devrait etre créer avec le CSVWriter");
		
		writer = new CSVWriter(wFile,'\n');
		
		writeJardin(jardin);
		writeCarotte(c);
		writeRocher(r);
	}
	
	public String fileName()
	{
		return fichier.file.getName();
	}
	
	private void writeCarotte(ArrayList<Carottes> carotte){
		for(Carottes c : carotte)
		{
			String[] entries = ("C;"+c.getPositionX() +";"+c.getPositionY()+";"+c.getNombre()).split(";");
			writer.writeNext(entries);
		}
	}
	
	private void writeRocher(ArrayList<Rocher> rocher){
		for(Rocher r : rocher)
		{
			String[] entries = ("R;"+r.getPositionX() +";"+r.getPositionY()).split(";");
			writer.writeNext(entries);
		}
	}
	
	private void writeFox(){
		//TODO
	}
	
	private void writeJardin(SimpleJardin jardin){
		String[] entries = ("J;"+jardin.getSizeX()+";"+jardin.getSizeY()+";0;1").split(";");
		writer.writeNext(entries);
	}
}
