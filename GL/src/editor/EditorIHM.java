package editor;

import game.Carottes;
import game.Rocher;
import game.SimpleCarottes;
import game.SimpleJardin;
import game.SimpleRocher;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

public class EditorIHM {

	SimpleJardin theJardin;
	ArrayList<Carottes> cList;
	ArrayList<Rocher> rList;

	private static final Logger LOGGER = Logger.getLogger(EditorIHM.class);

	public JFrame frame;
	//public JPanel panelG, panel;
	public JButton jardin, carotte, rocher, renard, quitter,fin;
	public boolean flagAnnuler = false;



	/////////////////////////////////////////////////	PRINCIPAL	////////////////////////////////////////////////

	//				Création de la fenetre principale

	public EditorIHM()
	{
		LOGGER.debug("Debut du IHM");

		frame = new JFrame("Edition");
		//frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(new Dimension(500,500));
		frame.setBounds(0, 0, 500, 500);
		frame.setLayout(null);
		frame.setLocation(700,200);

		jardin = new JButton("Jardin");
		carotte = new JButton("Carotte");
		rocher = new JButton ("Rocher");
		renard = new JButton ("Renard");
		quitter = new JButton("Retour");
		fin = new JButton ("Créer");

		frame.add(jardin);
		frame.add(carotte);
		frame.add(rocher);
		frame.add(renard);
		frame.add(quitter);
		frame.add(fin);

		int w = (frame.getWidth()/2)-50;
		int h = (frame.getHeight()/2);

		//Postionnement des boutons
		jardin.setBounds( w - 50, h-150, 200, 40);
		carotte.setBounds( w - 50, h -100, 200, 40);
		rocher.setBounds( w - 50, h -50, 200, 40);
		renard.setBounds( w - 50, h , 200, 40 );
		quitter.setBounds(w-50 , h + 100 , 200, 40);
		fin.setBounds(w-50,h+50,200,40);

		//Lien entre les boutons et les actions
		jardin.addActionListener(new EditJardin());
		carotte.addActionListener(new EditCarotte());
		rocher.addActionListener(new EditRocher());
		renard.addActionListener(new EditRenard());
		quitter.addActionListener(new EditQuitter(frame));
		fin.addActionListener(new EditFin(frame));
		/*GridLayout gridL = new GridLayout(4,1);
		gridL.setHgap(50);
		gridL.setVgap(30);

		panel = new JPanel();
		panel.setLayout(gridL);


		panel.add(jardin);
		panel.add(carotte);
		panel.add(rocher);
		panel.add(renard);

		panelG = new JPanel();
		panelG.add(panel, BorderLayout.SOUTH);

		frame.add(panelG);*/

		frame.setResizable(false);
		frame.setVisible(true);

		LOGGER.debug("Fin IHM");

	}


	//////////////////////////////////////////////////	GENERAL	///////////////////////////////////////////////////


	public static void main(String[] args){
		new EditorIHM();
	}

	//	Gestion du bouton quitter

	public class EditQuitter implements ActionListener{
		private JFrame frame;

		public EditQuitter(final JFrame frame){
			this.frame = frame;
		}
		public void actionPerformed(ActionEvent e){
			LOGGER.debug("Click de quitter");
			frame.setVisible(false);
		}
	}



	//		Gestion des boutons "annuler"

	public class EditCancel implements ActionListener{
		private JFrame frame;

		public EditCancel(final JFrame frame){
			this.frame = frame;
		}

		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			LOGGER.debug("Fermeture de " + frame.getTitle());
		}
	}


	///////////////////////////////////////////////////////	LES JARDINS	///////////////////////////////////////////////////////////


	//			Création de la fenetre de création du jardin
	// Gestion du bouton jardin

	public class EditJardin implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int w = (frame.getWidth()/2)-50;
			int h = (frame.getHeight()/2)-150;

			LOGGER.debug("Click de jardin");
			final JFrame frmJardin = new JFrame("Création Jardin");
			//frmJardin.setUndecorated(true);
			frmJardin.setSize(new Dimension(500,300));
			frmJardin.setLocation(600, 300);
			frmJardin.setLayout(null);
			frmJardin.getContentPane().setBackground(Color.CYAN);
			frmJardin.setResizable(false);

			JTextField colonnes = new JTextField("Nb de colonnes");
			JTextField lignes = new JTextField("Nb de lignes");
			JButton btnAnnuler = new JButton("Annuler");
			JButton btnOk = new JButton("OK");

			frmJardin.add(colonnes);
			frmJardin.add(lignes);
			frmJardin.add(btnAnnuler);
			frmJardin.add(btnOk);

			//Positionnement des boutons et champs
			colonnes.setBounds( w +75, h - 50, 150, 40);
			lignes.setBounds( w -125, h - 50, 150, 40);
			btnAnnuler.setBounds( w - 125, h + 50, 150, 40);
			btnOk.setBounds( w + 75, h + 50, 150, 40 );

			btnAnnuler.addActionListener(new EditCancel(frmJardin));		

			btnOk.addActionListener(new EditOkJardin(lignes, colonnes, frmJardin));

			frmJardin.setVisible(true);
		}
	}
	
	private class EditOkJardin implements ActionListener{
		
		JFrame frame;
		JTextField l,c;
		int ligne,colonne;

		public EditOkJardin (JTextField l, JTextField c, JFrame frame)
		{
			this.frame = frame;
			this.l = l;
			this.c = c;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			
			try{
				ligne = Integer.parseInt(l.getText());
				colonne = Integer.parseInt(c.getText());
				LOGGER.debug("Ce sont bien des int.");
			}catch(NumberFormatException er){
				LOGGER.error("Ce ne sont pas des INT!");
				return;
			}
			
			theJardin = new SimpleJardin();
			theJardin.setSizeX(ligne);
			theJardin.setSizeY(colonne);
			
			frame.setVisible(false);
		}
		
	}


	/////////////////////////////////////////////////////////	LES CAROTTES	///////////////////////////////////////////////////////


	//			Création de la fenetre de création des carottes
	// Gestion du bouton carotte

	public class EditCarotte implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int w = (frame.getWidth()/2)-200;
			int h = (frame.getHeight()/2)-200;

			LOGGER.debug("Click de carotte");
			final JFrame frmNbC = new JFrame("Nombre de Carottes");
			frmNbC.setSize(new Dimension(300,300));
			frmNbC.setLocation(700, 300);
			frmNbC.setLayout(null);
			frmNbC.getContentPane().setBackground(new Color(255, 102, 0));
			frmNbC.setResizable(false);

			JLabel text = new JLabel("Entrez le nombre de carottes:");
			JTextField nbCarottes = new JTextField("");
			JButton btnOk = new JButton("OK");
			JButton btnAnnuler = new JButton("Annuler");

			frmNbC.add(text);
			frmNbC.add(nbCarottes);
			frmNbC.add(btnOk);
			frmNbC.add(btnAnnuler);
			// positionnement des boutons
			text.setBounds( w + 15, h - 30, 200, 40);
			nbCarottes.setBounds( w + 15, h + 20, 160, 40);
			btnOk.setBounds( w + 15, h + 100, 160, 40);
			btnAnnuler.setBounds( w + 15, h + 150, 160, 40);

			btnAnnuler.addActionListener(new EditCancel(frmNbC));		

			btnOk.addActionListener(new EditOkCarotte(nbCarottes,frmNbC));

			frmNbC.setVisible(true);
		}
	} 



	// Gestion du bouton OK après selection du nombre de carottes

	public class EditOkCarotte implements ActionListener{

		private JTextField nbCarottes;
		JFrame frame;
		int nbC;

		public EditOkCarotte(JTextField nbCarottes,JFrame frame)
		{
			this.nbCarottes = nbCarottes;
			this.frame = frame;
		}

		public void actionPerformed(ActionEvent e) {
			LOGGER.debug("Click de OK pour les carottes");
			LOGGER.debug("Test : " + nbCarottes.getText());

			// On s'assure qu'on prend bien un int
			try{
				nbC = Integer.parseInt(nbCarottes.getText());
				frame.setVisible(false);
				creationCarotte(nbC);
				LOGGER.debug("C'est bien un int.");
			}catch(NumberFormatException er){
				LOGGER.debug("C'est pas un INT!!!!");
				return;
			}


		}



		// Gestion du pop up après avoir choisi le nombre de carotte

		private void creationCarotte(int nbC)
		{
			LOGGER.debug("Création des carottes");
			frame = new JFrame("Les Carottes!");
			frame.setSize(new Dimension(500,300));
			frame.setLocation(600, 300);
			frame.setLayout(null);
			frame.getContentPane().setBackground(new Color(255, 102, 0));
			frame.setResizable(false);

			int w = (frame.getWidth() /2);
			int h = frame.getHeight()/2;

			JLabel ligne = new JLabel("N° Ligne :");
			JLabel colonne = new JLabel("N° Colonne :");
			JLabel nombre = new JLabel("Nombre :");
			JTextField l = new JTextField();
			JTextField c = new JTextField();
			JTextField n = new JTextField();
			JButton cancel = new JButton("Annuler");
			JButton ok = new JButton("OK");

			frame.add(ligne);
			frame.add(colonne);
			frame.add(nombre);
			frame.add(l);
			frame.add(c);
			frame.add(n);
			frame.add(cancel);
			frame.add(ok);

			//text.setBounds( w-75 , h -100, 200, 40);

			ligne.setBounds(w-200, h-115, 100,40);
			l.setBounds(w-200,h-70,100,40);
			colonne.setBounds(w-50,h-115,100,40);
			c.setBounds(w-50,h-70,100,40);
			nombre.setBounds(w+100,h-115,100,40);
			n.setBounds(w+100,h-70,100,40);
			cancel.setBounds(w-150,h+50,100,40);
			ok.setBounds(w+50,h+50,100,40);

			frame.setVisible(true);

			cList = new ArrayList<Carottes>();

			cancel.addActionListener(new EditCancel(frame));
			ok.addActionListener(new NouvelleCarotte(nbC,l,c,n,frame));
		}
	}


	// Création d'une nouvelle carotte et ajour dans la liste des carottes à mettre dans le csv

	private class NouvelleCarotte implements ActionListener{

		private JFrame frame;
		private final int nbC;
		private JTextField l,c,n;
		int ligne, colonne, nombre;

		public NouvelleCarotte(int nbC, JTextField l, JTextField c,JTextField n, JFrame frame){
			this.nbC = nbC;
			this.l = l;
			this.c = c;
			this.n = n;
			this.frame = frame;
			LOGGER.debug("Click sur ok");
		}

		public void actionPerformed(ActionEvent e) {
			try{
				ligne = Integer.parseInt(l.getText());
				colonne = Integer.parseInt(c.getText());
				nombre = Integer.parseInt(n.getText());
				LOGGER.debug("Ce sont bien des int.");
			}catch(NumberFormatException er){
				LOGGER.debug("Ce ne sont pas des INT!");
				return;
			}

			SimpleCarottes carotte = new SimpleCarottes();
			carotte.setPositionX(ligne);
			carotte.setPositionY(colonne);
			carotte.setNombre(nombre);

			cList.add(carotte);

			if(cList.size() == nbC){
				frame.setVisible(false);
				LOGGER.debug("Les carottes sont finies! =>" + nbC);
			}

			l.setText("");
			c.setText("");
			n.setText("");
		}
	}


	//////////////////////////////////////////////////////	LES ROCHERS		//////////////////////////////////////////////////////


	//	Création de la fenetre de création des rochers
	// Gestion du bouton rocher

	class EditRocher implements ActionListener{
		public void actionPerformed(ActionEvent e){

			LOGGER.debug("Click de rocher");

			final JFrame frmNbR = new JFrame("Nombre de Rocher");
			frmNbR.setSize(new Dimension(500,300));
			frmNbR.setLocation(600, 300);
			frmNbR.setLayout(null);
			frmNbR.getContentPane().setBackground(new Color(255, 102, 255));
			frmNbR.setResizable(false);

			int w = (frmNbR.getWidth()/2);
			int h = (frmNbR.getHeight()/2);

			JLabel text = new JLabel("Entrez le nombre de rocher:");
			JTextField nbRocher = new JTextField("");
			JButton btnOk = new JButton("OK");
			JButton btnAnnuler = new JButton("Annuler");

			frmNbR.add(text);
			frmNbR.add(nbRocher);
			frmNbR.add(btnOk);
			frmNbR.add(btnAnnuler);

			//Positionnement des boutons et champs
			text.setBounds( w-75 , h -100, 200, 40);
			nbRocher.setBounds( w-75 , h - 50, 160, 40);
			btnOk.setBounds( w-75, h , 160, 40);
			btnAnnuler.setBounds( w-75 , h + 50, 160, 40);

			btnAnnuler.addActionListener(new EditCancel(frmNbR));			

			btnOk.addActionListener(new EditOkRocher(nbRocher,frmNbR));

			frmNbR.setVisible(true);

		}
	}



	// Gestion du choix du nombre de rocher 	
	private class EditOkRocher implements ActionListener{

		private JTextField nbRocher;
		JFrame frame;
		int nbR;

		public EditOkRocher(JTextField nbRocher,JFrame frame)
		{
			this.nbRocher = nbRocher;
			this.frame = frame;
		}

		public void actionPerformed(ActionEvent e) {
			LOGGER.debug("Click de OK pour les rochers");
			LOGGER.debug("Test : " + nbRocher.getText());

			try{
				nbR = Integer.parseInt(nbRocher.getText());
				frame.setVisible(false);
				LOGGER.debug("C'est bien un int.");
				creationRocher(nbR);
			}catch(NumberFormatException er){
				LOGGER.debug("C'est pas un INT!!!!");
				return;
			}
		}
	}


	// Gestion du pop up pour définir les rochers	
	private void creationRocher(int nbR)
	{
		LOGGER.debug("Création des Rochers");
		frame = new JFrame("Les Rochers!");
		frame.setSize(new Dimension(500,300));
		frame.setLocation(600, 300);
		frame.setLayout(null);
		frame.getContentPane().setBackground(new Color(255, 102, 255));
		frame.setResizable(false);

		int w = (frame.getWidth() /2);
		int h = frame.getHeight()/2;

		JLabel ligne = new JLabel("N° Ligne :");
		JLabel colonne = new JLabel("N° Colonne :");
		JTextField l = new JTextField();
		JTextField c = new JTextField();
		JButton cancel = new JButton("Annuler");
		JButton ok = new JButton("OK");

		frame.add(ligne);
		frame.add(colonne);
		frame.add(l);
		frame.add(c);
		frame.add(cancel);
		frame.add(ok);

		//text.setBounds( w-75 , h -100, 200, 40);

		ligne.setBounds(w-150, h-115, 100,40);
		l.setBounds(w-150,h-70,100,40);
		colonne.setBounds(w+50,h-115,100,40);
		c.setBounds(w+50,h-70,100,40);
		cancel.setBounds(w-150,h+50,100,40);
		ok.setBounds(w+50,h+50,100,40);

		frame.setVisible(true);

		rList = new ArrayList<Rocher>();

		cancel.addActionListener(new EditCancel(frame));
		ok.addActionListener(new NouveauRocher(nbR,l,c,frame));

	}


	// Création d'un nouveau rocher et ajout dans la liste des rochers à mettre dans le csv

	private class NouveauRocher implements ActionListener{

		private JFrame frame;
		private final int nbR;
		private JTextField l,c;
		int ligne, colonne;

		public NouveauRocher(int nbR, JTextField l, JTextField c, JFrame frame){
			this.nbR = nbR;
			this.l = l;
			this.c = c;
			this.frame = frame;
			LOGGER.debug("Click sur ok");
		}

		public void actionPerformed(ActionEvent e) {
			try{
				ligne = Integer.parseInt(l.getText());
				colonne = Integer.parseInt(c.getText());
				LOGGER.debug("Ce sont bien des int.");
			}catch(NumberFormatException er){
				LOGGER.debug("Ce ne sont pas des INT!");
				return;
			}

			SimpleRocher rocher = new SimpleRocher();
			rocher.setPositionX(ligne);
			rocher.setPositionY(colonne);

			rList.add(rocher);

			if(rList.size() == nbR){
				frame.setVisible(false);
				LOGGER.debug("Les rochers sont finies! =>" + nbR);
			}

			l.setText("");
			c.setText("");


		}
	}


	/////////////////////////////////////////////////	LES RENARDS	/////////////////////////////////////////////////////


	//			Gestion du bouton renard

	public class EditRenard implements ActionListener{
		public void actionPerformed(ActionEvent e){
			LOGGER.debug("Click de renard");
			System.exit(0);
		}
	}

	
	/////////////////////////////////////////////	FINALISATION	//////////////////////////////////////////////////////

	public class EditFin implements ActionListener{
		
		JFrame frame;
		JFrame popup;
		
		public EditFin(JFrame frame)
		{
			this.frame = frame;
		}
	
		public void actionPerformed(ActionEvent e) {
			if(theJardin == null || cList == null || rList == null)
			{
				LOGGER.debug("Pas tous fini!");
				new PopUp();
			}
				
			
		}
		
	}
	
	
	private class PopUp{
		JFrame frame;
		
		public PopUp(){
			frame = new JFrame("NOPE!");
			frame.setSize(new Dimension(500,300));
			frame.setLocation(700,300);
			
			JLabel text = new JLabel("Vous devez terminer TOUTES les étapes.");
			JButton ok = new JButton("J'y retourne...");
			
			frame.add(text);
			frame.add(ok);
			
			int w = 500/2;
			int h = 300/2;
			
			ok.setBounds(w-100,h-100,100,40);
			text.setBounds(w-100,h+50,100,40);
			
			frame.setVisible(true);
			
			 ok.addActionListener(new ActionListener()
		        {
				 public void actionPerformed(ActionEvent e)
		            {
		               frame.setVisible(false);
		               LOGGER.debug("On est reparti");
		            }
		        });
		}

	}
	
}	
