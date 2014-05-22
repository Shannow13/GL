package editor;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

public class EditorIHM {
	
	private static final Logger LOGGER = Logger.getLogger(EditorIHM.class);
	
	public JFrame frame;
	//public JPanel panelG, panel;
	public JButton jardin, carotte, rocher, renard;
	
	public EditorIHM()
	{
		LOGGER.debug("Debut du IHM");
		
		frame = new JFrame("Edition");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(new Dimension(500,500));
		frame.setBounds(0, 0, 500, 500);
		frame.setLayout(null);
		
		jardin = new JButton("Jardin");
		carotte = new JButton("Carotte");
		rocher = new JButton ("Rocher");
		renard = new JButton ("Renard");
		
		frame.add(jardin);
		frame.add(carotte);
		frame.add(rocher);
		frame.add(renard);
		
		int w = (frame.getWidth()/2)-50;
		int h = (frame.getHeight()/2);
		
		jardin.setBounds( w - 50, h-100, 200, 40);
	    carotte.setBounds( w - 50, h -50, 200, 40);
	    rocher.setBounds( w - 50, h , 200, 40);
	    renard.setBounds( w - 50, h +50, 200, 40 );
		
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
		
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args){
		new EditorIHM();
	}
	
}

