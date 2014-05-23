package editor;

import static org.apache.log4j.Logger.getLogger;
import game.Carottes;
import game.Lapin;
import game.Renard;
import game.Rocher;
import game.SimpleJardin;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVWriter;


public class EditorLevel{
	
	private static final Logger LOGGER = getLogger(EditorLevel.class);
	
	private FileWriter wFile;	// jardin
	private FileWriter fFile;	// fox
	private FileWriter lFile;	// lapin
	
	private FileEditor fichier;
	
	private CSVWriter writer;	// jardin
	private CSVWriter fWriter;	// fox
	private CSVWriter lWriter;	// lapin
	
	
	public EditorLevel(SimpleJardin jardin, ArrayList<Carottes> c, ArrayList<Rocher> r, ArrayList<Renard> f, ArrayList<Lapin> l)
	{
		fichier = new FileEditor();
		this.wFile = fichier.wFile;	// jardin
		this.fFile = fichier.fWFile;	// fox
		this.lFile = fichier.lWFile;	// lapin
		
		LOGGER.debug("Le fichier devrait etre créer avec le CSVWriter");
		
		writer = new CSVWriter(wFile,';',CSVWriter.NO_QUOTE_CHARACTER);	// jardin
		fWriter = new CSVWriter(fFile,';',CSVWriter.NO_QUOTE_CHARACTER);	// fox
		lWriter = new CSVWriter(lFile,';',CSVWriter.NO_QUOTE_CHARACTER);	// lapin
		
		writeJardin(jardin);
		writeCarotte(c);
		writeRocher(r);
		writeFox(f);
		writeLapin(l);

		
		try{
			writer.close();
			fWriter.close();
			lWriter.close();
		}catch(IOException e){
			LOGGER.error("Erreur de fermeture du fichier!");
			return;
		}
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
	
	private void writeFox(ArrayList<Renard> renard){
		int i =0;
		for( Renard f : renard)
		{
			String[] entries = ("F;"+ f.getPositionX()+ ";"+ f.getPositionY()+";"+f.getOrientation()+";"+f.getTrajet()+";Renard"+i++).split(";");
			fWriter.writeNext(entries);
		}
		
	}
	
	private void writeLapin(ArrayList<Lapin> lapin){
		int i =0;
		for( Lapin l : lapin)
		{
			String[] entries = ("L;"+ l.getPositionX()+ ";"+ l.getPositionY()+";"+l.getOrientation()+";Lapin"+i++).split(";");
			lWriter.writeNext(entries);
		}
		
	}
	
	private void writeJardin(SimpleJardin jardin){
		String[] entries = ("J;"+jardin.getSizeX()+";"+jardin.getSizeY()+";0;1").split(";");
		writer.writeNext(entries);
		
		
	}
}
