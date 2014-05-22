package editor;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

public class EditorIHM {
	
	private static final Logger LOGGER = Logger.getLogger(EditorIHM.class);
	
	public JFrame frame;
	//public JPanel panelG, panel;
	public JButton jardin, carotte, rocher, renard;
	public boolean flagAnnuler = false;
	
	public EditorIHM()
	{
		LOGGER.debug("Debut du IHM");
		
		frame = new JFrame("Edition");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(new Dimension(500,500));
		frame.setBounds(0, 0, 500, 500);
		frame.setLayout(null);
		frame.setLocation(600,300);
		
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
	    
	    jardin.addActionListener(new EditJardin());
	    carotte.addActionListener(new EditCarotte());
	    rocher.addActionListener(new EditRocher());
	    renard.addActionListener(new EditRenard());
		
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
	
	public class EditJardin implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int w = (frame.getWidth()/2)-50;
			int h = (frame.getHeight()/2)-150;
			
			LOGGER.debug("Click de jardin");
			final JFrame frmJardin = new JFrame("Création Jardin");
			//frmJardin.setUndecorated(true);
			//frmJardin.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
			
			colonnes.setBounds( w - 125, h - 50, 150, 40);
		    lignes.setBounds( w + 75, h - 50, 150, 40);
		    btnAnnuler.setBounds( w - 125, h + 50, 150, 40);
		    btnOk.setBounds( w + 75, h + 50, 150, 40 );
		    
		    btnAnnuler.addActionListener(new ActionListener()
            {
                    public void actionPerformed(ActionEvent e)
                    {
                        frmJardin.setVisible( false );
                    }
            });			
		    
		    btnOk.addActionListener(new ActionListener()
            {
                    public void actionPerformed(ActionEvent e)
                    {
                        frmJardin.setVisible( false );
                    }
            });	
		    
			frmJardin.setVisible(true);
		}
	}
	
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
			
			text.setBounds( w + 15, h - 30, 200, 40);
		    nbCarottes.setBounds( w + 15, h + 20, 160, 40);
			btnOk.setBounds( w + 15, h + 100, 160, 40);
			btnAnnuler.setBounds( w + 15, h + 150, 160, 40);
			
			btnAnnuler.addActionListener(new ActionListener()
            {
                    public void actionPerformed(ActionEvent e)
                    {
                        frmNbC.setVisible( false );
                    }
            });			
		    
		    btnOk.addActionListener(new ActionListener()
            {
                    public void actionPerformed(ActionEvent e)
                    {
                        frmNbC.setVisible( false );
                    }
            });	
		    
			frmNbC.setVisible(true);
		}
	} class EditRocher implements ActionListener{
		public void actionPerformed(ActionEvent e){
			LOGGER.debug("Click de rocher");
			System.exit(0);
		}
	}
	
	public class EditRenard implements ActionListener{
		public void actionPerformed(ActionEvent e){
			LOGGER.debug("Click de renard");
			System.exit(0);
		}
	}
}