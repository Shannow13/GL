package gl.dao;
import java.awt.*;

import javax.swing.*;

import org.apache.log4j.Logger;




public class Tableau extends JFrame {

	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	public static final Logger LOGGER = Logger.getLogger(Tableau.class);
	public JLabel grass = new JLabel(new ImageIcon("resources/grass.jpg"));
	
	public Tableau() {
		
		
		LOGGER.debug("config");
		
		int x = 6;
		int y = 6;
		
		char[][] jardin = new char[x][y]; //Tableau du jardin avec infos herbe, carottes et rochers
		
		
		for(int i = 0; i<x; i++) {
			for(int j = 0; j<y ; j++) {
				
				jardin[i][j] = 'h'; // herbe
				
			}
		}
		
		
		jardin[2][2] = 'c'; // carotte
		jardin[4][4] = 'r'; // rocher
		
		
		this.setTitle("Terrain de jeu");
		this.setSize(75*x,75*y);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		
		JPanel panelGeneral = new JPanel();
		GridLayout grille = new GridLayout(x,y);
		panelGeneral.setLayout(grille);

		JPanel[][] jp = new JPanel[x][y];
		
		
		//panel.setBackground(Color.GREEN);
		
		for(int i = 0; i<x; i++) {
			for(int j = 0; j<y; j++) {
				jp[i][j] = new JPanel();
				jp[i][j].setSize(90,80);
				
				if(jardin[i][j] == 'c') {
					JLabel carr = new JLabel(); //JLabel pour le fond des cases
					ImageIcon car_img = new ImageIcon("resources/carotte.jpg" );
					carr.setIcon(car_img);	
					(jp[i][j]).setLayout(new BorderLayout());
					(jp[i][j]).add(carr);
				}
				else if(jardin[i][j] == 'r'){
					JLabel rock = new JLabel(); //JLabel pour le fond des cases
					ImageIcon stone_img = new ImageIcon("resources/stone.png" );
					rock.setIcon(stone_img);	
					(jp[i][j]).setLayout(new BorderLayout());
					(jp[i][j]).add(rock);
				}
				else { 
					JLabel grass = new JLabel(); //JLabel pour le fond des cases
					ImageIcon img = new ImageIcon("resources/grass.jpg" );
					grass.setIcon(img);	
					(jp[i][j]).setLayout(new BorderLayout());
					(jp[i][j]).add(grass);
					
					
				}
				(jp[i][j]).setBorder(BorderFactory.createEtchedBorder());
				//setBorderColor(Color.BLACK);
				panelGeneral.add(jp[i][j]);
			}
 		}
		
		//pack();
		c.add(panelGeneral);
		
		this.setVisible(true);
		
		
		
		
		LOGGER.debug("closing");
		
		
	}
	
	
	
	
	public static void main(String[] args) {

			new Tableau();
		
		
	}

}
