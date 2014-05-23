package gl.dao;
import game.Carottes;
import game.Jardin;
import game.Lapin;
import game.Renard;
import game.Rocher;
import game.SimpleLapin;
import game.SimpleRenard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import org.apache.log4j.Logger;





public class Tableau extends JFrame {


	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	public static final Logger LOGGER = Logger.getLogger(Tableau.class);
	
	public char[][] jardin;
	public JPanel[][] jp;
	public boolean[] isLapinAlive;
	public boolean over;
	public int tours;
	public char dir;
	private JPanel eastPanel;
	private JPanel boutonPanel;
	private JButton buttonA;
	private JButton buttonG;
	private JButton buttonX;
	private JButton buttonD;
	private JPanel textPanel;
	private JLabel nameLabel;
	private JLabel orientLabel;
	private JLabel orientPhoto;
	private ImageIcon orientImg;
	private boolean flagWin = false;
	
	public static Jardin jardins;
	
	public ArrayList<Carottes> carottes;
	public ArrayList<Rocher> rochers;
	public List<Renard> renards;
	public List<Lapin> lapins;
	
	/**
	 * 
	 * @param jardin_file
	 * @param renard_file
	 * @param lapin_file
	 * 
	 * constructeur du tableau ; appel a doDao, pour ouvrir les fichiers contenant les données du niveau
	 */
	public Tableau(final String jardin_file, final  String renard_file, final String lapin_file){
		doDao(jardin_file, renard_file, lapin_file);
	}
	
	/**
	 * 
	 * @param jardin_file
	 * @param renard_file
	 * @param lapin_file
	 * 
	 * ouvre les fichiers contenant les données et initialise les listes de lapins, renard, et le jardin
	 */
	private void doDao(final String jardin_file, final  String renard_file, final String lapin_file) {
		LOGGER.debug("On va créer le jardin");
		final CsvJardinDao dao = new DefinitiveCsvJardinDao();
		final CsvRenardDao daoR = new DefinitiveCsvRenardDao();
		final CsvLapinDao daoL = new DefinitiveCsvLapinDao();
		dao.init(new File(jardin_file));
		daoR.init(new File(renard_file));
		daoL.init(new File(lapin_file));
		final Jardin jardins = dao.findJardin();
		final List<Renard> renards = daoR.findAllRenards();
		final List<Lapin> lapins = daoL.findAllLapins();
		tableauCrea(jardins, renards, lapins);
	}

	
	/**
	 * 
	 * @param jardins
	 * @param renards
	 * @param lapins
	 *
	 *	création du Tableau de jeu et exécution de l'algorithme
	 */
	public void tableauCrea(Jardin jardins, List<Renard> renards, List<Lapin> lapins) {

		
		LOGGER.debug("config");
		
		orientImg = new ImageIcon("resources/img/fN.png");
		orientPhoto = new JLabel(orientImg);
	
		// récupération des données du jardin
		int x = jardins.getSizeX();
		int y = jardins.getSizeY();
		carottes = new ArrayList<Carottes>();
		rochers = new ArrayList<Rocher>();
		carottes = jardins.getCarottes();
		rochers = jardins.getRochers();
		
		over = false;

		jardin = new char[x][y]; //Tableau du jardin avec infos herbe, carottes et rochers


		for(int i = 0; i<x; i++) {
			for(int j = 0; j<y ; j++) {

				jardin[i][j] = 'h'; // herbe

			}
		}
		
		for(Carottes c : carottes){
		
			jardin[c.getPositionX()][c.getPositionY()] = 'c';
			
			
		}
		
		for(Rocher r : rochers){
			jardin[r.getPositionX()][r.getPositionY()] = 'r';
			
		}
		
		/*
		 * X : lignes
		 * Y : colonnes
		 * 
		 * */
		
		

		/*paramètrage du tableau*/
		this.setTitle("Terrain de jeu"); //Titre de la fenêtre
		this.setSize(100*x,120*y); //Taille de la fenêtre
		this.setResizable(false); //On interdit à l'utilisateur la possibilité de redimensionner la fenêtre
		this.setLocation(100, 10); //On set l'endroit où la fenêtre va apparaitre à l'écran
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //Fermeture du jeu si on ferme la fenêtre de jeu

		Container c = getContentPane(); //On créer notre container général pour la totalité de la fenêtre de jeu

		JPanel panelGeneral = new JPanel(); //On crée un Panel général pour y ajouter tous nos Panels d'images (Lapins, Herbe, Renards, Rochers)
		GridLayout grille = new GridLayout(x,y); //On créer un GridLayout pour positionner toutes nos entités comme on le souhaite
		panelGeneral.setLayout(grille);

		jp = new JPanel[x][y]; //On créer un JPanel


		for(int i = 0; i<x; i++) {
			for(int j = 0; j<y; j++) {
				jp[i][j] = new JPanel();
				//jp[i][j].setPreferredSize(new Dimension(500,130));

				if(jardin[i][j] == 'c') { //Si jardin[i][j] contient une ou plusieurs carottes
					ImageIcon car_img = new ImageIcon("resources/img/carrot2.png" ); //On crée l'image de carotte
					JLabel carr = new JLabel(car_img); //JLabel pour le fond des cases
					(jp[i][j]).setLayout(new BorderLayout());
					(jp[i][j]).add(carr); //On ajoute l'image de carotte au Panel
					(jp[i][j]).setBackground(Color.orange); //On rajoute un background de couleur orange
				}
				else if(jardin[i][j] == 'r'){ //Si jardin[i][j] contient un ou plusieurs rochers
					ImageIcon stone_img = new ImageIcon("resources/img/caillou2.png" ); //On crée les images de rochers
					JLabel rock = new JLabel(stone_img); //JLabel pour le fond des cases
					(jp[i][j]).setLayout(new BorderLayout());
					(jp[i][j]).add(rock); //On ajoute les images de rochers au Panel
					(jp[i][j]).setBackground(Color.lightGray); //On ajoute un background de couleur gris clair
				}
				else { 
					ImageIcon herbe_img = new ImageIcon("resources/img/grass4.png" ); //Sinon ce sont des cases d'herbe, on crée l'image d'herbe
					JLabel herbe = new JLabel(herbe_img); //JLabel pour le fond des cases
					(jp[i][j]).setLayout(new BorderLayout());
					(jp[i][j]).add(herbe); //On ajoute les images d'herbe au Panel
					(jp[i][j]).setBackground(new Color( 80, 151, 9)); //On ajoute un background de couleur vert
				}
				
				(jp[i][j]).setBorder(BorderFactory.createEtchedBorder()); //Affichage d'un quadrillage pour plus de lisibilité
				panelGeneral.add(jp[i][j]); //On ajoute la totalité de nos Panels au Panel général (terrain)
			}

 		}

		for(Renard r : renards) { //On parcourt la liste de renards
			ImageIcon fox_img = new ImageIcon("resources/img/fox2.png" ); //On crée l'image du renard à ajouter sur le terrain de jeu
			JLabel fox = new JLabel(fox_img); //JLabel pour le fond des cases
			(jp[r.getPositionX()][r.getPositionY()]).add(fox); //On ajoute le renard sur la case correspondante
			(jp[r.getPositionX()][r.getPositionY()]).setBackground(Color.red); //On rajouter un backgroudn de couleur rouge
		}

		for(Lapin l : lapins) { //On parcourt la liste de lapins
			ImageIcon lapin_img = new ImageIcon("resources/img/lap2.png" ); //On crée l'image du lapin à ajouter sur le terrain de jeu
			JLabel lapin = new JLabel(lapin_img); //JLabel pour le fond des cases
			(jp[l.getPositionX()][l.getPositionY()]).add(lapin); //On ajoute le lapin sur la case correspondante
			(jp[l.getPositionX()][l.getPositionY()]).setBackground(Color.white); //On rajoute un background de couleur blanche
		}

		//pack();
		c.add(panelGeneral, BorderLayout.CENTER); //On ajoute notre panelGeneral au Container pour créer la fenêtre de jeu
		
		/*boutons*/

		eastPanel = new JPanel(new GridLayout(2,1));
		boutonPanel = new JPanel(new GridLayout(2,2));
		
		//Bouton "Avancer"
		buttonA = new JButton();
		buttonA.setBackground(Color.WHITE); // Couleur de fond
		JPanel panA = new JPanel();
		panA.setBackground(Color.WHITE);
		panA.setLayout(new BorderLayout());
		JLabel titleA = new JLabel("     Avancer"); // Titre du bouton
		ImageIcon img_A = new ImageIcon("resources/img/fleche.png"); // Image liée au bouton
		JLabel imgA = new JLabel(img_A); 
		panA.add(titleA, BorderLayout.CENTER); // Ajout du titre en haut du bouton
		panA.add(imgA, BorderLayout.SOUTH);// Ajout de l'image en dessous du titre
		imgA.setPreferredSize(new Dimension(80, 80)); // Taille de l'image dans le JButton
		buttonA.add(panA); // AJout du bouton au Panel
		buttonA.addActionListener(new Avance()); // Action listener pour faire l'action si le bouton est clické
		
		//Bouton "Attendre"
		buttonX = new JButton();
		buttonX.setBackground(Color.WHITE);
		JPanel panX = new JPanel();
		panX.setBackground(Color.WHITE);
		panX.setLayout(new BorderLayout());
		JLabel titleX = new JLabel("     Attendre");
		ImageIcon img_X = new ImageIcon("resources/img/zzz2.png");
		JLabel imgX = new JLabel(img_X);
		panX.add(titleX, BorderLayout.CENTER);
		panX.add(imgX, BorderLayout.SOUTH);
		imgX.setPreferredSize(new Dimension(80, 80));
		buttonX.add(panX);
		buttonX.addActionListener(new stayHere()); // Action listener pour faire l'action si le bouton est clické
		
		//Bouton "Rotation Gauche"
		buttonG = new JButton();
		buttonG.setBackground(Color.WHITE);
		JPanel panG = new JPanel();
		panG.setBackground(Color.WHITE);
		panG.setLayout(new BorderLayout());
		JLabel titleG = new JLabel("     Rot. Gauche");
		ImageIcon img_G = new ImageIcon("resources/img/rotD.png");
		JLabel imgG = new JLabel(img_G);
		panG.add(titleG, BorderLayout.CENTER);
		panG.add(imgG, BorderLayout.SOUTH);
		imgG.setPreferredSize(new Dimension(80, 80));
		buttonG.add(panG);
		buttonG.addActionListener(new rotateGauche()); // Action listener pour faire l'action si le bouton est clické
		
		//Bouton "Rotation Droite"
		buttonD = new JButton();
		buttonD.setBackground(Color.WHITE);
		JPanel panD = new JPanel();
		panD.setBackground(Color.WHITE);
		panD.setLayout(new BorderLayout());
		JLabel titleD = new JLabel("     Rot. Droite");
		ImageIcon img_D = new ImageIcon("resources/img/rotG.png");
		JLabel imgD = new JLabel(img_D);
		panD.add(titleD, BorderLayout.CENTER);
		panD.add(imgD, BorderLayout.SOUTH);
		imgD.setPreferredSize(new Dimension(80, 80));
		buttonD.add(panD);
		buttonD.addActionListener(new rotateDroite()); // Action listener pour faire l'action si le bouton est clické
		
		boutonPanel.add(buttonA); //Ajout des boutons au Panel
		boutonPanel.add(buttonX);
		boutonPanel.add(buttonG);
		boutonPanel.add(buttonD);
		
		textPanel = new JPanel(new GridLayout(2,1));
		nameLabel = new JLabel("name", JLabel.CENTER); //Affichage du nom du lapin
		orientLabel = new JLabel("orient",JLabel.CENTER); //Affichage de l'orientation centré sur le Label
		textPanel.add(nameLabel, "North");
		//textPanel.add(orientLabel, "Center");
		textPanel.add(orientPhoto, "Center");
		eastPanel.add(boutonPanel,BorderLayout.NORTH);
		eastPanel.add(textPanel,BorderLayout.SOUTH);



		c.add(eastPanel, BorderLayout.EAST);
		
		
		
		
		LOGGER.debug("pop fenetre"); //Debug de console
		this.setVisible(true); // Autoriser l'affichage de la fenêtre


		//Parcours de la liste de lapins pour savoir s'ils sont vivants ou non
		isLapinAlive = new boolean[lapins.size()];
		for (int i = 0; i < isLapinAlive.length; i++) { //Pour chaque lapin de la liste
			isLapinAlive[i] = true; //S'ils sont vivants, on leur donne la valeur "true"
		}


		tours = 1;



		LOGGER.debug("starting game"); //Debug de console


		//Condition de jeu : tant qu'il reste des carottes et des lapins vivants
		while(resteCarottes(jardin) == true && resteLapinsVivants(isLapinAlive) == true) {

			LOGGER.debug("tour : "+tours);



			LOGGER.debug("player's turn");
			//phase du joueur : on va donner un ordre a tous les lapins vivants
			for(int i = 0; i<lapins.size(); i++) {
				if(!isLapinAlive[i]) {	// si le lapin est mort, on passe au suivant
					continue;
				}
				
				//on récupère les infos du lapin courant
				SimpleLapin l = (SimpleLapin) lapins.get(i);
				char o = l.getOrientation();
				int lx = l.getPositionX();
				int ly = l.getPositionY();

				LOGGER.debug("lapin en surbrillance");
				(jp[lx][ly]).setBackground(Color.cyan);

				LOGGER.debug("choix de l'action");
				/*System.out.println("Orientation actuelle : " + o);
				System.out.println("Choisir la direction (D,G,A)");
				System.out.println("Si A, tout droit");
				System.out.println("Sinon, on pivote de 90 degres vers le cote souhaite");
				 */
				dir = '0';
				boolean ok = false;
				do {
					//affichage des parmaètres sur la fenêtre
					nameLabel.setText(l.getNom());
					orientLabel.setText("orientation : "+ l.getOrientation());
					orientImg = new ImageIcon("resources/img/f"+ l.getOrientation() + ".png");
					orientPhoto.setIcon(orientImg);
										
					if(dir == 'A') { //Si la direction choisie est "Avancer" (A)
						switch(o) { //Alors plusieurs cas par rapport à l'orientation d'origine
						case 'N': //Dans le cas où le lapin avait pour direction initiale le NORD
							if(lx != 0) { //Si on ne se trouve pas sur le bord supérieur du plateau de jeu
								if(jardin[lx-1][ly] == 'r') { //Si il y a un rocher sur la case où le lapin essaie d'avancer
									System.out.println("Déplacement sur un rocher impossible, ressayer"); //Déplacement impossible
									dir = '0'; //Pas de direction
									break;
								} else { //S'il n'y a pas de rocher sur la case où le lapin veut aller
									(jp[lx][ly]).removeAll(); //On retire toutes les images sur la case où le lapin était
									(jp[lx][ly]).add(new JLabel(new ImageIcon("resources/img/grass4.png"))); //On remet de l'herbe sur la case où le lapin était
									(jp[lx][ly]).setBackground(new Color( 80, 151, 9)); //On rajoute en plus un backgroudn de couleur vert sur la case où le lapin était
									l.setPositionX(lx-1); //On donne la nouvelle position au lapin
									(jp[l.getPositionX()][l.getPositionY()]).removeAll(); //On récupère la nouvelle position du lapin
									(jp[l.getPositionX()][l.getPositionY()]).add(new JLabel(new ImageIcon("resources/img/lap2.png"))); //On peut donc remettre l'image du lapin sur la bonne case
									(jp[l.getPositionX()][l.getPositionY()]).setBackground(Color.white); //On rajoute un fond blanc pour dire que le lapin a joué son tour
									this.validate(); //Actualisation de la frame
									ok = true; //On passe le flag à "true"
									break;
								}
							}
							else { //Si on se trouve sur le bord supérieur du terrain de jeu
								dir = '0'; //Pas de direction choisie
								System.out.println("Déplacement impossible, reessayer"); //Le déplacement est impossible sinon on sort du terrain de jeu
								break;
							}

						case 'S': //Dans le cas où le lapin avait pour direction initiale le SUD
							if(lx != x-1) {
								if(jardin[lx+1][ly] == 'r') { // le lapin ne peut pas aller sur un rocher
									System.out.println("Déplacement sur un rocher impossible, ressayer");
									dir = '0';
									break;
								} else {
									// on déplace le lapin une case plus bas
									(jp[lx][ly]).removeAll();
									(jp[lx][ly]).add(new JLabel(new ImageIcon("resources/img/grass4.png")));
									(jp[lx][ly]).setBackground(new Color( 80, 151, 9));
									l.setPositionX(lx+1);
									(jp[l.getPositionX()][l.getPositionY()]).removeAll();
									(jp[l.getPositionX()][l.getPositionY()]).add(new JLabel(new ImageIcon("resources/img/lap2.png")));
									(jp[l.getPositionX()][l.getPositionY()]).setBackground(Color.white);
									this.validate();
									ok = true;
									break;
								}
							}
							else { //le lapin de ne pas se déplacer plus bas si il est déjà tout en bas
								dir = '0';
								System.out.println("Déplacement impossible, reessayer");
								break;
							}

						case 'E': //Dans le cas où le lapin avait pour direction initiale l'EST
							// même principe que pour le SUD
							if(ly != y-1) {
								if(jardin[lx][ly+1] == 'r') {
									System.out.println("Déplacement sur un rocher impossible, ressayer");
									dir = '0';
									break;
								}
								else {
									(jp[lx][ly]).removeAll();
									(jp[lx][ly]).add(new JLabel(new ImageIcon("resources/img/grass4.png")));
									(jp[lx][ly]).setBackground(new Color( 80, 151, 9));
									l.setPositionY(ly+1);
									(jp[l.getPositionX()][l.getPositionY()]).removeAll();
									(jp[l.getPositionX()][l.getPositionY()]).add(new JLabel(new ImageIcon("resources/img/lap2.png")));
									(jp[l.getPositionX()][l.getPositionY()]).setBackground(Color.white);
									this.validate();
									ok = true;
									break;
								}
							}
							else {
								System.out.println("Déplacement impossible, reessayer");
								break;
							}

						case 'W': //Dans le cas où le lapin avait pour direction initiale l'OUEST
							//même principe que les autres directions
							if(ly != 0) {
								if(jardin[lx][ly-1] == 'r') {
									System.out.println("Déplacement sur un rocher impossible, ressayer");
									dir = '0';
									break;
								} else {
									(jp[lx][ly]).removeAll();
									(jp[lx][ly]).add(new JLabel(new ImageIcon("resources/img/grass4.png")));
									(jp[lx][ly]).setBackground(new Color( 80, 151, 9));
									l.setPositionY(ly-1);
									(jp[l.getPositionX()][l.getPositionY()]).removeAll();
									(jp[l.getPositionX()][l.getPositionY()]).add(new JLabel(new ImageIcon("resources/img/lap2.png")));
									(jp[l.getPositionX()][l.getPositionY()]).setBackground(Color.white);
									this.validate();
									ok = true;
									break;
								}
							}
							else {
								dir = '0';
								System.out.println("Déplacement impossible, reessayer");
								break;
							}
						}
					}
					else if(dir == 'D') { //Si on choisit la rotation droite
						ok = true;
						switch(o){ //Plusieurs cas par rapport à l'orientation d'origine
							case('N'): //Si NORD
								l.setOrientation('E'); break; //Alors EST
							case('E'): //Si EST
								l.setOrientation('S'); break;//Alors SUD
							case('S'): //Si SUD
								l.setOrientation('W'); break; //Alors OUEST
							case('W'): //Si OUEST
								l.setOrientation('N'); break; //Alors NORD							
						}
						(jp[lx][ly]).setBackground(Color.white); //On remet le background en blanc car le lapin a joué son tour
						LOGGER.debug("Nouvelle orientation : " + o); //Debug de console pour l'orientation du lapin
					}
					else if(dir == 'G') { //Si on choisit la rotation gauche
						ok = true;
						switch(o){
							case('N'):
								l.setOrientation('W'); break;
							case('E'):
								l.setOrientation('N'); break;
							case('S'):
								l.setOrientation('E'); break;
							case('W'):
								l.setOrientation('S'); break;							
						}
						(jp[lx][ly]).setBackground(Color.white);
						LOGGER.debug("Nouvelle orientation : " + o);
					}
					else if(dir == 'X') { //Si on choisit de ne rien faire
						LOGGER.debug("stay here");
						(jp[l.getPositionX()][l.getPositionY()]).setBackground(Color.white); //On change juste la couleur de backgroudn de la case actuelle du lapin
						ok = true;
						break;
					}

				}while(!ok); //On attend la commande du joueur tant que le flag "ok" n'est pas à "true"

				//on mémorise les nouvelles coordonnées du lapin
				lx = l.getPositionX();
				ly = l.getPositionY();

				if(jardin[lx][ly] == 'c') {	// si le lapin est sur une carotte, il la mange
					LOGGER.debug("Carotte mangee");
					jardin[lx][ly] = 'h';
					(jp[lx][ly]).removeAll();
					(jp[lx][ly]).add(new JLabel(new ImageIcon("resources/img/lap2.png")));
					(jp[lx][ly]).setBackground(Color.white);
					this.validate();
					if(!resteCarottes(jardin)) { // si il n'y a plus de carottes, le joueur gagne
						over = true;
						flagWin = true;
					}
				}

				for(Renard r : renards) {
					// si le joueur fait sournoisement rentrer le lapin en contact
					// avec un renard, le napin meurt
					if(lx == r.getPositionX() && ly == r.getPositionY()) {
						LOGGER.debug("Lapin mort sur un renard");
						(jp[lx][ly]).setBackground(Color.red);
						isLapinAlive[i] = false;
						//si il n'y a plus de lapins vivants, le joueur a perdu
						if(!resteLapinsVivants(isLapinAlive)) {
							over = true;
						}
						break;
					}

				}

				if(over) {
					break;
				}

				LOGGER.debug("lapin suivant");
				
			}
			
			// si la partie est finie, on sort directement du jeu
			if(over) {
				break;
			}
			
			LOGGER.debug("fin du tour joueur");
			LOGGER.debug("debut du tout renards");
			// on parcourt tous les renards
			for(int i=0; i<renards.size(); i++) {
				SimpleRenard r = (SimpleRenard) renards.get(i);
				
				// on récupère le prochain mouvement du renard grâce a son pattern
				String trajet = r.getTrajet();
				LOGGER.debug("trajet : "+trajet);
				// on boucle sur le pattern si on arrive au bout
				char dir = trajet.charAt(tours%trajet.length());
				LOGGER.debug("dir : "+dir);
				
				//récupération des coordonnées actuelles du renard
				int rx = r.getPositionX();
				int ry = r.getPositionY();
				char o = r.getOrientation();

				// si le renard doit avancer
				if(dir == 'A') {
					switch(o) {
					case 'N': // au NORD 
						if(rx != 0) {
							if(jardin[rx-1][ry] == 'r') {	// le renard ne peut avancer dans un rocher, donc il ne fait rien
								LOGGER.warn("Déplacement du renard sur un rocher impossible");
								break;
							} else {
								//le renard avance d'une case plus haut (même principe que les lapins)
								(jp[rx][ry]).removeAll();
								(jp[rx][ry]).add(new JLabel(new ImageIcon("resources/img/grass4.png")));
								(jp[rx][ry]).setBackground(new Color( 80, 151, 9));
								r.setPositionX(rx-1);
								(jp[r.getPositionX()][r.getPositionY()]).removeAll();
								(jp[r.getPositionX()][r.getPositionY()]).add(new JLabel(new ImageIcon("resources/img/fox2.png")));
								(jp[r.getPositionX()][r.getPositionY()]).setBackground(Color.red);
								this.validate();
								break;
							}
						}
						else { // le renard ne peut pas avancer plus bas si il est déjà tout en bas
							LOGGER.warn("Déplacement du renard impossible");
							break;
						}

					case 'S': // au SUD, même principe
						if(rx != x-1) {
							if(jardin[rx+1][ry] == 'r') {
								LOGGER.warn("Déplacement du renard sur un rocher impossible");
								break;
							} else {
								(jp[rx][ry]).removeAll();
								(jp[rx][ry]).add(new JLabel(new ImageIcon("resources/img/grass4.png")));
								(jp[rx][ry]).setBackground(new Color( 80, 151, 9));
								r.setPositionX(rx+1);
								(jp[r.getPositionX()][r.getPositionY()]).removeAll();
								(jp[r.getPositionX()][r.getPositionY()]).add(new JLabel(new ImageIcon("resources/img/fox2.png")));
								(jp[r.getPositionX()][r.getPositionY()]).setBackground(Color.red);
								this.validate();
								break;
							}
						}
						else {
							LOGGER.warn("Déplacement du renard impossible");
							break;
						}

					case 'E': // à l'est même principe
						if(ry != y-1) {
							if(jardin[rx][ry+1] == 'r') {
								LOGGER.warn("Déplacement du renard sur un rocher impossible");
								break;
							}
							else {
								(jp[rx][ry]).removeAll();
								(jp[rx][ry]).add(new JLabel(new ImageIcon("resources/img/grass4.png")));
								(jp[rx][ry]).setBackground(new Color( 80, 151, 9));
								r.setPositionY(ry+1);
								(jp[r.getPositionX()][r.getPositionY()]).removeAll();
								(jp[r.getPositionX()][r.getPositionY()]).add(new JLabel(new ImageIcon("resources/img/fox2.png")));
								(jp[r.getPositionX()][r.getPositionY()]).setBackground(Color.red);
								this.validate();
								break;
							}
						}
						else {
							LOGGER.warn("Déplacement du renard impossible");
							break;
						}

					case 'W': // a l'OUEST même principe
						if(ry != 0) {
							if(jardin[rx][ry-1] == 'r') {
								LOGGER.warn("Déplacement du renard sur un rocher impossible");
								break;
							} else {
								(jp[rx][ry]).removeAll();
								(jp[rx][ry]).add(new JLabel(new ImageIcon("resources/img/grass4.png")));
								(jp[rx][ry]).setBackground(new Color( 80, 151, 9));
								r.setPositionY(ry-1);
								(jp[r.getPositionX()][r.getPositionY()]).removeAll();
								(jp[r.getPositionX()][r.getPositionY()]).add(new JLabel(new ImageIcon("resources/img/fox2.png")));
								(jp[r.getPositionX()][r.getPositionY()]).setBackground(Color.red);
								this.validate();
								break;
							}
						}
						else {
							LOGGER.warn("Déplacement du renard impossible");
							break;
						}
					}
				}
				else if(dir == 'D') { // le renard tourne sur lui même à 90° sur sa droite
					switch(o){
						case('N'):
							r.setOrientation('E'); break;
						case('E'):
							r.setOrientation('S'); break;
						case('S'):
							r.setOrientation('W'); break;
						case('W'):
							r.setOrientation('N'); break;							
					}
					(jp[rx][ry]).setBackground(Color.red);
					LOGGER.debug("Nouvelle orientation du renard : " + o);
				}
				else if(dir == 'G') { // le renard tourne sur lui même à 90° sur sa droite
					switch(o){
						case('N'):
							r.setOrientation('W'); break;
						case('E'):
							r.setOrientation('N'); break;
						case('S'):
							r.setOrientation('E'); break;
						case('W'):
							r.setOrientation('S'); break;							
					}
					(jp[rx][ry]).setBackground(Color.red);
					LOGGER.debug("Nouvelle orientation du renard : " + o);
				}
				else if(dir == 'X') { // le renard attend patiemment sa proie
					LOGGER.debug("renard statique");
				}
				else { //sinon, le pattern est éronné ; le renard ne fait rien
					LOGGER.error("Erreur du trajet du renard");
				}

				// obtention des nouvelles coordonnées du lapin
				rx = r.getPositionX();
				ry = r.getPositionY();
				
				// on regarde si le renard est arrivé sur un lapin
				for(int j=0; j< lapins.size(); j++) {
					Lapin l = lapins.get(j);
					// si le renard arrive sur un lapin, il le mange
					if(rx == l.getPositionX() && ry == l.getPositionY()) {
						LOGGER.debug("Renard a tué un lapin");
						(jp[rx][ry]).setBackground(Color.red);
						isLapinAlive[j] = false;
						if(!resteLapinsVivants(isLapinAlive)) { // si il n'y a plus de lapin, le joueur perd
							over = true;
							//flagWin = true;
						}
						break;
					}

				}

				if(over) {
					break;
				}

			}

			if(over) {
				break;
			}

			LOGGER.debug("fin de la phase renards");
			
			LOGGER.debug("mise à jour du tableau");
			// mise à jour du tableau; correction de bugs
			for(int i = 0; i<jardin.length;i++) {
				for(int j = 0; j>jardin[0].length;j++) {
					if(jardin[i][j] == 'c') {
						(jp[i][j]).removeAll();
						(jp[i][j]).add(new JLabel(new ImageIcon("resources/img/carrot2.png")));
						(jp[i][j]).setBackground(Color.orange);
					}
				}
			}
			
			
			
			
			
			LOGGER.debug("fin de ce tour");
			tours ++; // tour suivant
		}

		
		// arrivé à ce point, la partie est terminée
		LOGGER.debug("closing");

		if(flagWin == true) {
			
			new IsVictory();
			
		}
		else {
			
			new IsDefeat();
			
		}
		
		this.setVisible(false);
		dispose();
		//System.exit(0);
		
	}

	/**
	 * 
	 * @param jardin
	 * @return true si il reste des carottes à manger dans le jardin ; false sinon
	 */
	public boolean resteCarottes(char jardin[][]) {

		for(int i=0; i<jardin.length; i++) {
			for(int j=0; j<jardin[0].length; j++) {
				if(jardin[i][j] == 'c') {
					return true;
				}
			}
		}
		LOGGER.debug("Toutes les carottes ont été mangées");
		return false;
	}

	/**
	 * 
	 * @param isLapinAlive
	 * @return true si il reste des lapins vivants ; false sinon
	 */
	public boolean resteLapinsVivants(boolean isLapinAlive[]) {

		for(int i=0; i<isLapinAlive.length; i++) {
			if(isLapinAlive[i] == true) {
				return true;
			}
		}
		LOGGER.debug("Tous les lapins sont morts");
		return false;
	}

	

	/*
	public static void main(String[] args) {
		
			new Tableau("resources/jardin-1.csv","resources/DAO/renard-1.csv","resources/DAO/lapin-1.csv");
			
	}*/
	
	
	/*
	 * ActionListener pour les différents boutons
	 */
	class Avance implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			dir = 'A';
		}
		
	}

	class rotateGauche implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			dir = 'G';
		}
	}
	
	class rotateDroite implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			dir = 'D';
		}
	}
	
	class stayHere implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			dir = 'X';
		}
	}
	
	
}



