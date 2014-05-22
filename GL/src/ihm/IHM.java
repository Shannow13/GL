package ihm;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class IHM
{
    static JFrame frm = new JFrame("Projet GL");
    
    public static void main(String[] args)
    {
        frm.getContentPane().setLayout( new BorderLayout() );
        frm.getContentPane().add( new Graph(), BorderLayout.CENTER );
        int w = (int) Toolkit.getDefaultToolkit().getScreenSize().width;
        int h = (int) Toolkit.getDefaultToolkit().getScreenSize().height;
        frm.setSize( w, h );
        frm.setVisible( true );
    }
    
    public static JFrame getFrm()
    {
        return frm;
    }

    public static void setFrm(JFrame frm)
    {
        IHM.frm = frm;
    }
}

class Graph extends JPanel
{
    private static final long serialVersionUID = 1L;

    private Image bg = getToolkit().getImage("resources/img/bugs_bunny.jpg");
    
    private boolean fullScreen = false;
    
    JButton btnNew = new JButton("NOUVELLE PARTIE");
    JButton btnCont = new JButton("CONTINUER");
    JButton btnLoad = new JButton("CHARGER NIVEAU");
    JButton btnHelp = new JButton("AIDE");
    JButton btnExit = new JButton("QUITTER");
    JButton btnFullScreen = new JButton("FULLSCREEN");
    JButton btnEditNiveau = new JButton("EDITION NIVEAU");
    
    int width = (int) Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = (int) Toolkit.getDefaultToolkit().getScreenSize().height;
    
    public Graph()
    {  
        final JFrame frame = IHM.frm;
        
        setBounds(0, 0, width, height);

        setLayout(null);

        add( btnNew );
        add( btnCont );
        add( btnLoad );
        add( btnEditNiveau);
        add( btnHelp );
        add( btnExit );
        add( btnFullScreen );
                
        int w = ( width / 2) - 50;
        int h = ( height / 2 ) - 150;

        btnNew.setBounds( w - 50, h - 200, 200, 40);
        btnCont.setBounds( w - 50, h - 100, 200, 40 );
        btnLoad.setBounds( w - 50, h, 200, 40);
        btnEditNiveau.setBounds( w - 50, h + 100, 200, 40);
        btnHelp.setBounds( w - 50, h + 200, 200, 40 );
        btnExit.setBounds( w - 50, h + 300, 200, 40 );
        btnFullScreen.setBounds(w + 860, h + 580, 108, 15);
        
        setOpaque( false );
        
        btnNew.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Nouvelle Partie");
            }
        });
        
        btnCont.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Continuer");
            }
        });
        
        btnLoad.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                 final JFrame frmLoad = new JFrame("Chargement Niveau");
                 frmLoad.setResizable(false);
                 
                 Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                 int hauteur = (int)tailleEcran.getHeight();
                 int largeur = (int)tailleEcran.getWidth();
                 frmLoad.setLocation((int)(hauteur*0.62), (int)(largeur*0.1));
                 
                 int width = 600;
                 int height = 500;
                 frmLoad.setSize( width, height );
                 frmLoad.setVisible(true);
                 
                 JPanel panel = new JPanel();
                 JPanel back = new JPanel();
                 
                 JLabel choix = new JLabel("  Choisissez votre niveau :");
                 choix.setPreferredSize(new Dimension(160, 40));
                 JPanel top = new JPanel();
                 
                 ImageIcon img = new ImageIcon("resources/img/panneau.png");
                 JLabel imgHelp = new JLabel(img);
                 imgHelp.setPreferredSize(new Dimension(230,230));
                 
                 ImageIcon img2 = new ImageIcon("resources/img/lapin2.gif");
                 JLabel imgHelp2 = new JLabel(img2);
                 imgHelp2.setPreferredSize(new Dimension(230,230));
                 
                 JLabel fill1 = new JLabel();
                 JLabel fill2 = new JLabel();
                 JLabel fill3 = new JLabel();
                 JLabel fill4 = new JLabel();
                 JLabel fill5 = new JLabel();
                 JLabel fill6 = new JLabel();
                 JLabel fill7 = new JLabel();
             	 
                 JButton btnLoad1 = new JButton("1");
                 JButton btnLoad2 = new JButton("2");
                 JButton btnLoad3 = new JButton("3");
                 JButton btnLoad4 = new JButton("4");
                 JButton btnLoad5 = new JButton("5");
                 JButton btnLoad6 = new JButton("Niveaux perso...");
                 JButton btnBack = new JButton("Retour");
                 btnBack.setPreferredSize(new Dimension(160, 40));
                 
                 panel.setLayout(new GridLayout(13, 1));
                
                 panel.add(fill1);
                 panel.add(btnLoad1);
                 panel.add(fill2);
                 panel.add(btnLoad2);
                 panel.add(fill3);
                 panel.add(btnLoad3);
                 panel.add(fill4);
                 panel.add(btnLoad4);
                 panel.add(fill5);
                 panel.add(btnLoad5);
                 panel.add(fill6);
                 panel.add(btnLoad6);
                 panel.add(fill7);
                 back.add(btnBack);
                 top.add(choix);
                 
                 frmLoad.add(panel,BorderLayout.CENTER);
                 frmLoad.add(imgHelp, BorderLayout.EAST);
                 frmLoad.add(imgHelp2, BorderLayout.WEST);
                 frmLoad.add(back, BorderLayout.SOUTH);
                 frmLoad.add(top, BorderLayout.NORTH);
                 
                 btnBack.addActionListener(new ActionListener()
                 {
                         public void actionPerformed(ActionEvent e)
                         {
                             frmLoad.setVisible( false );
                         }
                 });
                
                 //panel.setBackground(Color.WHITE);
                 //back.setBackground(Color.WHITE);
                 
                 System.out.println("Charger Niveau");
            }
        });
        
        btnEditNiveau.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Edition Niveau");
            }
        });
        
        btnHelp.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                final JFrame frmHelp = new JFrame( "Aide" );
                frmHelp.setResizable(false);
                
                Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                int hauteur = (int)tailleEcran.getHeight();
                int largeur = (int)tailleEcran.getWidth();
                frmHelp.setLocation((int)(hauteur*0.712), (int)(largeur*0.15));
                
                JButton btnBack = new JButton("Retour");
                
                frmHelp.setLayout(new BorderLayout());
                
                ImageIcon img = new ImageIcon("resources/img/help2.png");
                JLabel imgHelp = new JLabel(img);
                imgHelp.setPreferredSize(new Dimension(200,500));
                
                frmHelp.getContentPane().setLayout( new BorderLayout() );    
                int width = 400;
                int height = 500;
                frmHelp.setSize( width, height );
                
                JLabel text1, text2;
                
                text1 = new JLabel ("COMMANDES");
                text2 = new JLabel ("<html>P : Pause<br>Q : Quitter<html>");
                
                JPanel title = new JPanel();
                JPanel help = new JPanel();
                JPanel back = new JPanel();
                
                title.add(text1);
                help.add(text2);
                
                frmHelp.add(imgHelp, BorderLayout.EAST);
                frmHelp.add(title, BorderLayout.NORTH);
                frmHelp.add(help, BorderLayout.WEST);
                
                title.setBackground(Color.WHITE);
                help.setBackground(Color.WHITE);
                frmHelp.getContentPane().setBackground(Color.WHITE);
                
                btnBack.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent e)
                        {
                            frmHelp.setVisible( false );
                        }
                });
                
                back.add(btnBack);
                back.setBackground(Color.WHITE);
                
                frmHelp.add(back, BorderLayout.SOUTH);
                
                frmHelp.setVisible( true );
            }
        });
        
        btnExit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                final JFrame frmQuit = new JFrame("Quitter");
                frmQuit.setResizable(false);
                
                Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                int hauteur = (int)tailleEcran.getHeight();
                int largeur = (int)tailleEcran.getWidth();
                frmQuit.setLocation((int)(hauteur*0.75), (int)(largeur*0.20));
                frame.setVisible(false);
 
                //JButton btnQuit = new JButton("Oui");
                //JButton btnBack = new JButton("Non");
                JButton btnQuit = new JButton(new ImageIcon("resources/img/vrai3.png"));
                btnQuit.setPreferredSize(new Dimension(40,40));
                JButton btnBack = new JButton(new ImageIcon("resources/img/faux3.png"));
                btnBack.setPreferredSize(new Dimension(40,40));
                
                frmQuit.setLayout(new BorderLayout());
                
                frmQuit.getContentPane().setLayout( new BorderLayout() );    
                int width = 300;
                int height = 220;
                frmQuit.setSize( width, height );
                
                JLabel text;
                
                text = new JLabel ("<html><br>Merci d'avoir joué !<br<br>A bientôt !<br><br>Etes-vous sûr de vouloir quitter ?<html>");
                
                JPanel title = new JPanel();
                
                title.add(text);
                
                frmQuit.add(title, BorderLayout.NORTH);
                title.setBackground(Color.WHITE);
                frmQuit.getContentPane().setBackground(Color.WHITE);
                
                btnQuit.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        System.exit(0);
                    }
                });
                
                btnBack.addActionListener(new ActionListener()
                {
                        public void actionPerformed(ActionEvent e)
                        {
                            frmQuit.setVisible( false );
                            frame.setVisible(true);
                        }
                });
                
                JPanel quit = new JPanel();
                
                quit.add(btnQuit);
                quit.add(btnBack);
                
                quit.setBackground(Color.WHITE);
                frmQuit.add(quit, BorderLayout.SOUTH);
                
                frmQuit.setVisible( true );
            }
        });
        
        btnFullScreen.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(fullScreen == false)
                {
                    frame.setVisible(false);
                    frame.removeNotify();
                    frame.setUndecorated(true);
                    frame.setVisible(true);
                    fullScreen = true;
                    btnFullScreen.setText("WINDOWED");
                }
                else
                {
                    frame.setVisible(false);
                    frame.removeNotify();
                    frame.setUndecorated(false);
                    frame.setVisible(true);
                    fullScreen = false;
                    btnFullScreen.setText("FULLSCREEN");
                }
            }
        });   
    }

    public void paint(Graphics g)
    {
        g.drawImage( bg, 0, 0, width, height, this );
        super.paint( g );
    }
} 