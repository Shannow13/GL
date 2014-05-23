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
	
	public boolean isok;
	
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
		
		isok = isOk(jardin,c,r,f,l);
		
		if(!isok)
		{
			LOGGER.error("Quelques chose ne convient pas (élément en dehors du jardin, jardin à taille négative...)");
			return;
		}
		
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
	
	private boolean isOk(SimpleJardin jardin, ArrayList<Carottes> c, ArrayList<Rocher> r, ArrayList<Renard> f, ArrayList<Lapin> l)
	{
		if(jardin.getSizeX() <= 0 || jardin.getSizeY() <= 0)
		{
			LOGGER.error("Taille jardin négative.");
			return false;
		}
		
		if(jardin.getSizeX() > 9 || jardin.getSizeY() > 9)
		{
			LOGGER.error("Taille jardin trop grand.");
			return false;
		}
		
		for(Rocher rocher : r)
		{
			if(jardin.getSizeX() <= rocher.getPositionX() || rocher.getPositionX() < 0 || jardin.getSizeY() <= rocher.getPositionY() || rocher.getPositionY() < 0)
			{
				LOGGER.error("Rocher en dehors du jardin.");
				return false;
			}
		}
		
		for(Carottes carotte : c)
		{
			if(jardin.getSizeX() <= carotte.getPositionX() || carotte.getPositionX() < 0 || jardin.getSizeY() <= carotte.getPositionY() || carotte.getPositionY() < 0)
			{
				LOGGER.error("Carotte en dehors du jardin.");
				return false;
			}
		}
		
		for(Renard fox : f)
		{
			if(jardin.getSizeX() <= fox.getPositionX() || fox.getPositionX() < 0 || jardin.getSizeY() <= fox.getPositionY() || fox.getPositionY() < 0)
			{
				LOGGER.error("Renard en dehors du jardin.");
				return false;
			}
		}
		
		for(Lapin lapin : l)
		{
			if(jardin.getSizeX() <= lapin.getPositionX() || lapin.getPositionX() < 0 || jardin.getSizeY() <= lapin.getPositionY() || lapin.getPositionY() < 0)
			{
				LOGGER.error("Lapin en dehors du jardin.");
				return false;
			}
		}
		
		
		for(Rocher rocher : r)
		{
			for(Carottes carotte : c)
			{
				if(rocher.getPositionX() == carotte.getPositionX() && rocher.getPositionY() == carotte.getPositionY())
				{
					LOGGER.error("Une ou plusieurs carottes se superposent avec des rochers!");
					return false;
				}
			}
		}
		
		return true;
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
