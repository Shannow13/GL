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
	public JButton jardin, carotte, rocher, renard, quitter;
	public boolean flagAnnuler = false;
	
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
		
		frame.add(jardin);
		frame.add(carotte);
		frame.add(rocher);
		frame.add(renard);
		frame.add(quitter);
		
		int w = (frame.getWidth()/2)-50;
		int h = (frame.getHeight()/2);
		
		jardin.setBounds( w - 50, h-150, 200, 40);
	    carotte.setBounds( w - 50, h -100, 200, 40);
	    rocher.setBounds( w - 50, h -50, 200, 40);
	    renard.setBounds( w - 50, h , 200, 40 );
	    quitter.setBounds(w-50 , h + 50 , 200, 40);
	    
	    jardin.addActionListener(new EditJardin());
	    carotte.addActionListener(new EditCarotte());
	    rocher.addActionListener(new EditRocher());
	    renard.addActionListener(new EditRenard());
	    quitter.addActionListener(new EditQuitter(frame));
		
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
			frmJardin.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		    
		    btnAnnuler.addActionListener(new EditCancel(frmJardin));		
		    
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
			frmNbC.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
			
			btnAnnuler.addActionListener(new EditCancel(frmNbC));		
		    
		    btnOk.addActionListener(new EditOkCarotte(nbCarottes,frmNbC));
		    
			frmNbC.setVisible(true);
		}
	} 
	
	class EditRocher implements ActionListener{
		public void actionPerformed(ActionEvent e){
					
			LOGGER.debug("Click de rocher");
			
			final JFrame frmNbR = new JFrame("Nombre de Rocher");
			frmNbR.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
			
			text.setBounds( w-75 , h -100, 200, 40);
		    nbRocher.setBounds( w-75 , h - 50, 160, 40);
			btnOk.setBounds( w-75, h , 160, 40);
			btnAnnuler.setBounds( w-75 , h + 50, 160, 40);
			
			btnAnnuler.addActionListener(new EditCancel(frmNbR));			
		    
		    btnOk.addActionListener(new ActionListener()
            {
                    public void actionPerformed(ActionEvent e)
                    {
                        frmNbR.setVisible( false );
                    }
            });	
		    
			frmNbR.setVisible(true);
			
		}
	}
	
	
	
	
	public class EditRenard implements ActionListener{
		public void actionPerformed(ActionEvent e){
			LOGGER.debug("Click de renard");
			System.exit(0);
		}
	}
	
	
	
	
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
		
		private void creationCarotte(int nbC)
		{
			LOGGER.debug("Création des carottes");
				frame = new JFrame("Les Carottes!");
				frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
				
				cancel.addActionListener(new EditCancel(frame));
		}
	}
	
	
}
