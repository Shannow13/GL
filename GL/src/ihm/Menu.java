package ihm;

import java.awt.*;
import javax.swing.*;

public class Menu extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Menu() {
		
		super();
		
		setPreferredSize(new Dimension(600, 400));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.BLACK);
		
		
		add(new JLabel(new ImageIcon("resources/testimg.jpg")));	
		pack();
		
		final JPanel main = new JPanel();
		//main.setLayout(new BorderLayout());
		main.setLayout(new GridLayout(3,3));
		//main.add(img, CENTER);
		
		final JPanel boutons = new JPanel();
		boutons.setLayout(new BoxLayout(boutons, BoxLayout.Y_AXIS));
		
		JButton b1 = new JButton("Continuer");
		JButton b2 = new JButton("Nouvelle Partie");
		JButton b3 = new JButton("Charger");
		JButton b4 = new JButton("Quitter");
		boutons.add(b1);
		boutons.add(b2);
		boutons.add(b3);
		boutons.add(b4);
		
		b1.setAlignmentX(Component.CENTER_ALIGNMENT);
		b2.setAlignmentX(Component.CENTER_ALIGNMENT);
		b3.setAlignmentX(Component.CENTER_ALIGNMENT);
		b4.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		main.add(boutons, ("3,3"));
		
		getContentPane().add(main);
		
		pack();
				
	}
}