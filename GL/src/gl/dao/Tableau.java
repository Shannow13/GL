package gl.dao;
import game.Carottes;
import game.Jardin;
import game.Lapin;
import game.Renard;
import game.Rocher;
import game.SimpleJardin;
import game.SimpleLapin;
import game.SimpleRenard;
import gl.dao.*;

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

	public List<Renard> renards;
	public List<Lapin> lapins;
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

	public static Jardin jardins;
	
	private final static String RESOURCES_PATH = "resources/";
	private final static String JARDIN_FILE_NAME = "jardin-1.csv";
	final static File file = new File(RESOURCES_PATH + JARDIN_FILE_NAME);


	public Tableau(Jardin jardins) {

		
		LOGGER.debug("config");
		
		int x = jardins.getSizeX();
		int y = jardins.getSizeY();
		ArrayList<Carottes> carottes = new ArrayList<Carottes>();
		ArrayList<Rocher> rochers = new ArrayList<Rocher>();
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
		
		//jardin[2][2] = 'c'; // carotte
		//jardin[4][4] = 'r'; // rocher
		/*jardin[2][4] = 'l'; // lapin
		jardin[5][5] = 'l';
		jardin[0][0] = 'l';
		jardin[1][1] = 'f'; // renard ; fox*/


		
		/*
		 * X : lignes
		 * Y : colonnes
		 * 
		 * */
		
		

		/*ajout de renards pour la liste*/
		LOGGER.debug("init renards");
		renards = new ArrayList<Renard>();
		renards.add(new SimpleRenard(1,1,'S',"AGAGAGAGADADADAD","Fox1"));

		LOGGER.debug("init lapins");
		lapins = new ArrayList<Lapin>();
		//lapins.add(new SimpleLapin(2,4,'E',"Bugs"));
		//lapins.add(new SimpleLapin(5,5,'N',"Bunny"));
		lapins.add(new SimpleLapin(0,0,'E',"Wabbit"));


		this.setTitle("Terrain de jeu");
		this.setSize(75*x,75*y);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container c = getContentPane();

		JPanel panelGeneral = new JPanel();
		GridLayout grille = new GridLayout(x,y);
		panelGeneral.setLayout(grille);

		jp = new JPanel[x][y];


		//panel.setBackground(Color.GREEN);

		for(int i = 0; i<x; i++) {
			for(int j = 0; j<y; j++) {
				jp[i][j] = new JPanel();
				jp[i][j].setSize(90,80);

				if(jardin[i][j] == 'c') {
					/*JLabel carr = new JLabel(); //JLabel pour le fond des cases
					ImageIcon car_img = new ImageIcon("resources/carotte.jpg" );
					carr.setIcon(car_img);	
					(jp[i][j]).setLayout(new BorderLayout());
					(jp[i][j]).add(carr);*/
					(jp[i][j]).setBackground(Color.orange);
				}
				else if(jardin[i][j] == 'r'){
					/*JLabel rock = new JLabel(); //JLabel pour le fond des cases
					ImageIcon stone_img = new ImageIcon("resources/stone.png" );
					rock.setIcon(stone_img);	
					(jp[i][j]).setLayout(new BorderLayout());
					(jp[i][j]).add(rock);*/
					(jp[i][j]).setBackground(Color.darkGray);
				}
				/*else if(jardin[i][j] == 'f') {
					(jp[i][j]).setBackground(Color.red);
				}
				else if(jardin[i][j] == 'l') {
					(jp[i][j]).setBackground(Color.white);
				}*/
				else { 
					/*JLabel grass = new JLabel(); //JLabel pour le fond des cases
					ImageIcon img = new ImageIcon("resources/grass.jpg" );
					grass.setIcon(img);	
					(jp[i][j]).setLayout(new BorderLayout());
					(jp[i][j]).add(grass);*/
					(jp[i][j]).setBackground(Color.green);


				}
				(jp[i][j]).setBorder(BorderFactory.createEtchedBorder());
				//setBorderColor(Color.BLACK);
				panelGeneral.add(jp[i][j]);
			}

 		}

		for(Renard r : renards) {
			(jp[r.getPositionX()][r.getPositionY()]).setBackground(Color.red);
		}

		for(Lapin l : lapins) {
			(jp[l.getPositionX()][l.getPositionY()]).setBackground(Color.white);
		}



		//pack();
		c.add(panelGeneral, BorderLayout.CENTER);
		
		/*boutons*/

		eastPanel = new JPanel(new GridLayout(2,1));
		boutonPanel = new JPanel(new GridLayout(2,2));
		buttonA = new JButton("A");
		buttonA.addActionListener(new Avance());
		buttonX = new JButton("X");
		buttonX.addActionListener(new stayHere());
		buttonG = new JButton("G");
		buttonG.addActionListener(new rotateGauche());
		buttonD = new JButton("D");
		buttonD.addActionListener(new rotateDroite());
		boutonPanel.add(buttonA);
		boutonPanel.add(buttonX);
		boutonPanel.add(buttonG);
		boutonPanel.add(buttonD);
		textPanel = new JPanel(new GridLayout(2,1));
		nameLabel = new JLabel("name", JLabel.CENTER);
		orientLabel = new JLabel("orient",JLabel.CENTER);
		textPanel.add(nameLabel, "Center");
		textPanel.add(orientLabel, "Center");
		eastPanel.add(boutonPanel,BorderLayout.NORTH);
		eastPanel.add(textPanel,BorderLayout.SOUTH);



		c.add(eastPanel, BorderLayout.EAST);
		
		
		
		
		LOGGER.debug("pop fenetre");
		this.setVisible(true);



		isLapinAlive = new boolean[lapins.size()];
		for (int i = 0; i < isLapinAlive.length; i++) {
			isLapinAlive[i] = true;
		}


		tours = 1;



		LOGGER.debug("starting game");



		while(resteCarottes(jardin) == true && resteLapinsVivants(isLapinAlive) == true) {

			LOGGER.debug("tour : "+tours);



			LOGGER.debug("player's turn");
			for(int i = 0; i<lapins.size(); i++) {
				if(!isLapinAlive[i]) {
					continue;
				}

				SimpleLapin l = (SimpleLapin) lapins.get(i);
				char o = l.getOrientation();
				int lx = l.getPositionX();
				int ly = l.getPositionY();

				LOGGER.debug("lapin en surbrillance");
				(jp[lx][ly]).setBackground(Color.cyan);

				LOGGER.debug("choix de l'action");
				System.out.println("Orientation actuelle : " + o);
				System.out.println("Choisir la direction (D,G,A)");
				System.out.println("Si A, tout droit");
				System.out.println("Sinon, on pivote de 90 degres vers le cote souhaite");

				dir = '0';
				boolean ok = false;
				do {


					// TODO remplacer le scanner par des actionlistener sur le coté du tableau
					/*String s = scan.next();
					s.toUpperCase();
					System.out.print(s);
					char dir = s.charAt(0);
					System.out.println(" "+ dir);*/
					
					nameLabel.setText(l.getNom());
					orientLabel.setText("orientation : "+ l.getOrientation());

					if(dir == 'A') {
						switch(o) {
						case 'N': 
							if(lx != 0) {
								if(jardin[lx-1][ly] == 'r') {
									System.out.println("Déplacement sur un rocher impossible, ressayer");
									dir = '0';
									break;
								} else {
									(jp[lx][ly]).setBackground(Color.green);
									l.setPositionX(lx-1);
									(jp[l.getPositionX()][l.getPositionY()]).setBackground(Color.white);
									ok = true;
									break;
								}
							}
							else {
								dir = '0';
								System.out.println("Déplacement impossible, reessayer");
								break;
							}

						case 'S':
							if(lx != x-1) {
								if(jardin[lx+1][ly] == 'r') {
									System.out.println("Déplacement sur un rocher impossible, ressayer");
									dir = '0';
									break;
								} else {
									(jp[lx][ly]).setBackground(Color.green);
									l.setPositionX(lx+1);
									(jp[l.getPositionX()][l.getPositionY()]).setBackground(Color.white);
									ok = true;
									break;
								}
							}
							else {
								dir = '0';
								System.out.println("Déplacement impossible, reessayer");
								break;
							}

						case 'E':
							if(ly != y-1) {
								if(jardin[lx][ly+1] == 'r') {
									System.out.println("Déplacement sur un rocher impossible, ressayer");
									dir = '0';
									break;
								}
								else {
									(jp[lx][ly]).setBackground(Color.green);
									l.setPositionY(ly+1);
									(jp[l.getPositionX()][l.getPositionY()]).setBackground(Color.white);
									ok = true;
									break;
								}
							}
							else {
								System.out.println("Déplacement impossible, reessayer");
								break;
							}

						case 'W':
							if(ly != 0) {
								if(jardin[lx][ly-1] == 'r') {
									System.out.println("Déplacement sur un rocher impossible, ressayer");
									dir = '0';
									break;
								} else {
									(jp[lx][ly]).setBackground(Color.green);
									l.setPositionY(ly-1);
									(jp[l.getPositionX()][l.getPositionY()]).setBackground(Color.white);
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
					else if(dir == 'D') {
						ok = true;
						switch(o){
							case('N'):
								l.setOrientation('E'); break;
							case('E'):
								l.setOrientation('S'); break;
							case('S'):
								l.setOrientation('W'); break;
							case('W'):
								l.setOrientation('N'); break;							
						}
						(jp[lx][ly]).setBackground(Color.white);
						LOGGER.debug("Nouvelle orientation : " + o);
					}
					else if(dir == 'G') {
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
					else if(dir == 'X') {
						LOGGER.debug("stay here");
						ok = true;
						break;
					}

				}while(!ok);

				lx = l.getPositionX();
				ly = l.getPositionY();

				if(jardin[lx][ly] == 'c') {
					LOGGER.debug("Carotte mangee");
					jardin[lx][ly] = 'h';
					if(!resteCarottes(jardin)) {
						over = true;
					}
				}

				for(Renard r : renards) {
					if(lx == r.getPositionX() && ly == r.getPositionY()) {
						LOGGER.debug("Lapin mort sur un renard");
						(jp[lx][ly]).setBackground(Color.red);
						isLapinAlive[i] = false;
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

			if(over) {
				break;
			}

			LOGGER.debug("fin du tour joueur");
			LOGGER.debug("debut du tout renards");

			for(int i=0; i<renards.size(); i++) {
				SimpleRenard r = (SimpleRenard) renards.get(i);

				String trajet = r.getTrajet();
				LOGGER.debug("trajet : "+trajet);
				char dir = trajet.charAt(tours%trajet.length());
				LOGGER.debug("dir : "+dir);

				int rx = r.getPositionX();
				int ry = r.getPositionY();
				char o = r.getOrientation();


				if(dir == 'A') {
					switch(o) {
					case 'N': 
						if(rx != 0) {
							if(jardin[rx-1][ry] == 'r') {
								LOGGER.warn("Déplacement du renard sur un rocher impossible");
								break;
							} else {
								(jp[rx][ry]).setBackground(Color.green);
								r.setPositionX(rx-1);
								(jp[r.getPositionX()][r.getPositionY()]).setBackground(Color.red);
								break;
							}
						}
						else {
							LOGGER.warn("Déplacement du renard impossible");
							break;
						}

					case 'S':
						if(rx != x-1) {
							if(jardin[rx+1][ry] == 'r') {
								LOGGER.warn("Déplacement du renrad sur un rocher impossible");
								break;
							} else {
								(jp[rx][ry]).setBackground(Color.green);
								r.setPositionX(rx+1);
								(jp[r.getPositionX()][r.getPositionY()]).setBackground(Color.red);
								break;
							}
						}
						else {
							LOGGER.warn("Déplacement du renard impossible");
							break;
						}

					case 'E':
						if(ry != y-1) {
							if(jardin[rx][ry+1] == 'r') {
								LOGGER.warn("Déplacement du renard sur un rocher impossible");
								break;
							}
							else {
								(jp[rx][ry]).setBackground(Color.green);
								r.setPositionY(ry+1);
								(jp[r.getPositionX()][r.getPositionY()]).setBackground(Color.red);
								break;
							}
						}
						else {
							LOGGER.warn("Déplacement du renard impossible");
							break;
						}

					case 'W':
						if(ry != 0) {
							if(jardin[rx][ry-1] == 'r') {
								LOGGER.warn("Déplacement du renard sur un rocher impossible");
								break;
							} else {
								(jp[rx][ry]).setBackground(Color.green);
								r.setPositionY(ry-1);
								(jp[r.getPositionX()][r.getPositionY()]).setBackground(Color.red);
								break;
							}
						}
						else {
							LOGGER.warn("Déplacement du renard impossible");
							break;
						}
					}
				}
				else if(dir == 'D') {
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
				else if(dir == 'G') {
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
				else {
					LOGGER.error("Erreur du trajet du renard");
				}

				
				rx = r.getPositionX();
				ry = r.getPositionY();
				
				for(int j=0; j< lapins.size(); j++) {
					Lapin l = lapins.get(j);
					if(rx == l.getPositionX() && ry == l.getPositionY()) {
						LOGGER.debug("Renard a buté un lapin");
						(jp[rx][ry]).setBackground(Color.red);
						isLapinAlive[i] = false;
						if(!resteLapinsVivants(isLapinAlive)) {
							over = true;
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
			LOGGER.debug("fin de ce tour");
			tours ++;
		}


		LOGGER.debug("closing");

		this.setVisible(false);
		dispose();
		System.exit(0);
	}


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


	public boolean resteLapinsVivants(boolean isLapinAlive[]) {

		for(int i=0; i<isLapinAlive.length; i++) {
			if(isLapinAlive[i] == true) {
				return true;
			}
		}
		LOGGER.debug("Tous les lapins sont morts");
		return false;
	}

	
	public void setDirection(char dir) {
		this.dir = dir;
	}
	

	public static void main(String[] args) {
		
			doDao();
			
	}
	
	
	
	
	private static void doDao() {
		LOGGER.debug("On va créer le jardin");
		final CsvJardinDao dao = new DefinitiveCsvJardinDao();
		dao.init(file);
		doWork(dao);
	}


	private static void doWork(final JardinDao dao) {
		final Jardin jardins = dao.findJardin();
		new Tableau(jardins);

		
	}

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



