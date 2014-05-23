package editor;

import game.Carottes;
//import game.Renard;
import game.Rocher;
import game.SimpleCarottes;
import game.SimpleJardin;
//import game.SimpleRenard;
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
   // ArrayList<Renard> fList;

    private static final Logger LOGGER = Logger.getLogger(EditorIHM.class);

    public JFrame frame;
    //public JPanel panelG, panel;
    public JButton jardin, carotte, rocher, renard, quitter,fin;
    public boolean IsOk;



    /////////////////////////////////////////////////    PRINCIPAL    ////////////////////////////////////////////////

    //                Création de la fenetre principale

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
       // renard = new JButton ("Renard");
        quitter = new JButton("Retour");
        fin = new JButton ("Créer");

        frame.add(jardin);
        frame.add(carotte);
        frame.add(rocher);
        //frame.add(renard);
        frame.add(quitter);
        frame.add(fin);

        int w = (frame.getWidth()/2)-50;
        int h = (frame.getHeight()/2);

        //Postionnement des boutons
        jardin.setBounds( w - 50, h-200, 200, 40);
        carotte.setBounds( w - 50, h -150, 200, 40);
        rocher.setBounds( w - 50, h -100, 200, 40);
       // renard.setBounds( w - 50, h -50 , 200, 40 );
        quitter.setBounds(w-50 , h + 100 , 200, 40);
        fin.setBounds(w-50,h,200,40);

        //Lien entre les boutons et les actions
        jardin.addActionListener(new EditJardin());
        carotte.addActionListener(new EditCarotte());
        rocher.addActionListener(new EditRocher());
       // renard.addActionListener(new EditRenard());
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
        
        IsOk = false;

    }


    //////////////////////////////////////////////////    GENERAL    ///////////////////////////////////////////////////

    public static void main(String[] args){
        new EditorIHM();
    }

    //    Gestion du bouton quitter

    public class EditQuitter implements ActionListener{
        private JFrame frame;

        public EditQuitter(final JFrame frame){
            this.frame = frame;
        }
        public void actionPerformed(ActionEvent e){
            LOGGER.debug("Click de quitter" + frame.getTitle());
            frame.setVisible(false);
        }
    }


    //        Gestion des boutons "annuler"

    public class EditCancel implements ActionListener{
        private JFrame frame;
        ArrayList<?> list;

        public EditCancel(final JFrame frame, ArrayList<?> list){
            this.frame = frame;
            this.list = list;
        }

        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            list = null;
            LOGGER.debug("Cancel de " + frame.getTitle());
        }
    }


    ///////////////////////////////////////////////////////    LES JARDINS    ///////////////////////////////////////////////////////////


    //            Création de la fenetre de création du jardin
    // Gestion du bouton jardin

    public class EditJardin implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int w = (frame.getWidth()/2)-50;
            int h = (frame.getHeight()/2)-150;

            LOGGER.debug("Click de jardin");
            final JFrame frmJardin = new JFrame("Création Jardin");
            frmJardin.setSize(new Dimension(500,240));
            frmJardin.setLocation(700, 300);
            frmJardin.setLayout(null);
            frmJardin.getContentPane().setBackground(Color.CYAN);
            frmJardin.setResizable(false);

            JTextField colonnes = new JTextField("");
            JLabel colonnes_text = new JLabel("Nombre de colonnes");
            JTextField lignes = new JTextField("");
            JLabel lignes_text = new JLabel("Nombre de lignes");
            JButton btnAnnuler = new JButton("Annuler");
            JButton btnOk = new JButton("OK");

            frmJardin.add(colonnes);
            frmJardin.add(colonnes_text);
            frmJardin.add(lignes);
            frmJardin.add(lignes_text);
            frmJardin.add(btnAnnuler);
            frmJardin.add(btnOk);

            //Positionnement des boutons et champs
            colonnes.setBounds( w + 75, h - 50, 150, 40);
            colonnes_text.setBounds( w + 90, h - 90, 150, 40);
            lignes.setBounds( w - 125, h - 50, 150, 40);
            lignes_text.setBounds( w - 102, h - 90, 150, 40);
            btnAnnuler.setBounds( w - 45, h + 50, 80, 40);
            btnOk.setBounds( w + 65, h + 50, 80, 40 );

            btnAnnuler.addActionListener(new EditQuitter(frmJardin));        

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


    /////////////////////////////////////////////////////////    LES CAROTTES    ///////////////////////////////////////////////////////


    //            Création de la fenetre de création des carottes
    // Gestion du bouton carotte

    public class EditCarotte implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int w = (frame.getWidth()/2)-200;
            int h = (frame.getHeight()/2)-200;

            LOGGER.debug("Click de carotte");
            final JFrame frmNbC = new JFrame("Création Carottes");
            frmNbC.setSize(new Dimension(300,230));
            frmNbC.setLocation(805, 300);
            frmNbC.setLayout(null);
            frmNbC.getContentPane().setBackground(new Color(255, 102, 0));
            frmNbC.setResizable(false);

            JLabel text = new JLabel("Nombre de carottes");
            JTextField nbCarottes = new JTextField("");
            JButton btnOk = new JButton("OK");
            JButton btnAnnuler = new JButton("Annuler");

            frmNbC.add(text);
            frmNbC.add(nbCarottes);
            frmNbC.add(btnOk);
            frmNbC.add(btnAnnuler);
            // positionnement des boutons
            text.setBounds( w + 38, h - 40, 200, 40);
            nbCarottes.setBounds( w + 15, h, 160, 40);
            btnOk.setBounds( w + 115, h + 90, 80, 40);
            btnAnnuler.setBounds( w, h + 90, 80, 40);

            btnAnnuler.addActionListener(new EditCancel(frmNbC,cList));        

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
                LOGGER.debug("C'est bien un int.");
            }catch(NumberFormatException er){
                LOGGER.debug("C'est pas un INT!!!!");
                return;
            }

            if(nbC > 0)
            	creationCarotte(nbC);

        }



        // Gestion du pop up après avoir choisi le nombre de carotte

        private void creationCarotte(int nbC)
        {
            LOGGER.debug("Création des carottes");
            frame = new JFrame("Les Carottes!");
            frame.setSize(new Dimension(500,300));
            frame.setLocation(700, 300);
            frame.setLayout(null);
            frame.getContentPane().setBackground(new Color(255, 102, 0));
            frame.setResizable(false);

            int w = (frame.getWidth() /2);
            int h = frame.getHeight()/2;

            JLabel ligne = new JLabel("N° Ligne");
            JLabel colonne = new JLabel("N° Colonne");
            JLabel nombre = new JLabel("Nombre");
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

            cancel.addActionListener(new EditCancel(frame,cList));
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


    //////////////////////////////////////////////////////    LES ROCHERS        //////////////////////////////////////////////////////


    //    Création de la fenetre de création des rochers
    // Gestion du bouton rocher

    class EditRocher implements ActionListener{
        public void actionPerformed(ActionEvent e){

            LOGGER.debug("Click de rocher");

            final JFrame frmNbR = new JFrame("Création Rochers");
            frmNbR.setSize(new Dimension(300,230));
            frmNbR.setLocation(805, 300);
            frmNbR.setLayout(null);
            frmNbR.getContentPane().setBackground(new Color(255, 102, 255));
            frmNbR.setResizable(false);

            int w = (frmNbR.getWidth()/2);
            int h = (frmNbR.getHeight()/2);

            JLabel text = new JLabel("Nombre de rochers");
            JTextField nbRocher = new JTextField("");
            JButton btnOk = new JButton("OK");
            JButton btnAnnuler = new JButton("Annuler");

            frmNbR.add(text);
            frmNbR.add(nbRocher);
            frmNbR.add(btnOk);
            frmNbR.add(btnAnnuler);

            //Positionnement des boutons et champs
            text.setBounds( w - 58 , h - 105, 200, 40);
            nbRocher.setBounds( w - 81 , h - 65, 160, 40);
            btnOk.setBounds( w + 19, h + 25 , 80, 40);
            btnAnnuler.setBounds( w - 96 , h + 25, 80, 40);

            btnAnnuler.addActionListener(new EditCancel(frmNbR,rList));            

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

        cancel.addActionListener(new EditCancel(frame,rList));
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


    /////////////////////////////////////////////////    LES RENARDS    /////////////////////////////////////////////////////


/*    
    //            Gestion du bouton renard

    public class EditRenard implements ActionListener{
        public void actionPerformed(ActionEvent e){
            LOGGER.debug("Click de renard");

                final JFrame frmNbF = new JFrame("Création des renards!");
                frmNbF.setSize(new Dimension(300,230));
                frmNbF.setLocation(805, 300);
                frmNbF.setLayout(null);
                frmNbF.getContentPane().setBackground(Color.YELLOW);
                frmNbF.setResizable(false);

                int w = (frmNbF.getWidth()/2);
                int h = (frmNbF.getHeight()/2);

                JLabel text = new JLabel("Nombre de renards");
                JTextField nbF = new JTextField("");
                JButton btnOk = new JButton("OK");
                JButton btnAnnuler = new JButton("Annuler");

                frmNbF.add(text);
                frmNbF.add(nbF);
                frmNbF.add(btnOk);
                frmNbF.add(btnAnnuler);

                //Positionnement des boutons et champs
                text.setBounds( w - 58 , h - 105, 200, 40);
                nbF.setBounds( w - 81 , h - 65, 160, 40);
                btnOk.setBounds( w + 19, h + 25 , 80, 40);
                btnAnnuler.setBounds( w - 96 , h + 25, 80, 40);

                btnAnnuler.addActionListener(new EditCancel(frmNbF, fList));            

                btnOk.addActionListener(new EditOkRenard(nbF,frmNbF));

                frmNbF.setVisible(true);
        }
    }

    
 // Gestion du choix du nombre de rocher     
    private class EditOkRenard implements ActionListener{

        private JTextField nbF;
        JFrame frame;
        int nbFox;

        public EditOkRenard(JTextField nbF,JFrame frame)
        {
            this.nbF = nbF;
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            LOGGER.debug("Click de OK pour les renards");
            LOGGER.debug("Test : " + nbF.getText());

            try{
                nbFox = Integer.parseInt(nbF.getText());
                frame.setVisible(false);
                LOGGER.debug("C'est bien un int.");
                creationRenard(nbFox);
            }catch(NumberFormatException er){
                LOGGER.debug("C'est pas un INT!!!!");
                return;
            }
        }
    }


    // Gestion du pop up pour définir les renards    
    private void creationRenard(int nbF)
    {
        LOGGER.debug("Création des Renards");
        frame = new JFrame("Les Renards!");
        frame.setSize(new Dimension(500,300));
        frame.setLocation(600, 300);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setResizable(false);

        int w = (frame.getWidth() /2);
        int h = frame.getHeight()/2;

        JLabel ligne = new JLabel("N° Ligne :");
        JLabel colonne = new JLabel("N° Colonne :");
        JLabel orientation = new JLabel("Orientation* :");
        JLabel path = new JLabel ("Pattern** :");
        JLabel orientationExpl = new JLabel("*N=haut, S=bas, E=droite, W=gauche");
        JLabel pathExpl = new JLabel("**A=avant, D=droite, G=gauche, X=stop");
        JTextField l = new JTextField();
        JTextField c = new JTextField();
        JTextField o = new JTextField();
        JTextField p = new JTextField();
        JButton cancel = new JButton("Annuler");
        JButton ok = new JButton("OK");

        frame.add(ligne);
        frame.add(colonne);
        frame.add(orientation);
        frame.add(path);
        frame.add(l);
        frame.add(c);
        frame.add(o);
        frame.add(p);
        frame.add(cancel);
        frame.add(ok);
        frame.add(orientationExpl);
        frame.add(pathExpl);

        //text.setBounds( w-75 , h -100, 200, 40);

        ligne.setBounds(w-230, h-115, 100,40);
        l.setBounds(w-230,h-70,100,40);
        colonne.setBounds(w-110,h-115,100,40);
        c.setBounds(w-110,h-70,100,40);
        orientation.setBounds(w+10, h-115, 100, 40);
        o.setBounds(w+10,h-70, 100, 40);
        path.setBounds(w+130,h-115,100,40);
        p.setBounds(w+130,h-70,100,40);
        cancel.setBounds(w-150,h+50,100,40);
        ok.setBounds(w+50,h+50,100,40);
        orientationExpl.setBounds(w-240,h,220,40);
        pathExpl.setBounds(w+5,h,220,40);

        frame.setVisible(true);

        fList = new ArrayList<Renard>();

        cancel.addActionListener(new EditCancel(frame,fList));
        ok.addActionListener(new NouveauRenard(nbF,l,c,o,p,frame));

    }


    // Création d'un nouveau renard et ajout dans la liste des rochers à mettre dans le csv

    private class NouveauRenard implements ActionListener{

        private JFrame frame;
        private int nbF;
        private JTextField l,c,o,p;
        int ligne, colonne;
        char orient;
        String path;

        public NouveauRenard(int nbF,  JTextField l, JTextField c, JTextField o, JTextField p, JFrame frame){
            this.nbF = nbF;
            this.l = l;
            this.c = c;
            this.o = o;
            this.p = p;
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

            
            orient = o.getText().toUpperCase().charAt(0);
            LOGGER.debug("Orientation : "+orient);
            
           if(orient != 'N' && orient != 'S' && orient != 'W' && orient != 'E')
            {
            	LOGGER.error("L'orientation n'est pas bonne!");
            	return;
            }
            
            path = new String(p.getText());
            path.toUpperCase();
            
            for(int i = 0; i < path.length(); i++)
            {
            	if(path.charAt(i) != 'A' && path.charAt(i) != 'D' && path.charAt(i) != 'G' && path.charAt(i) != 'X')
            	{
            		LOGGER.error("Le path n'est pas bon!");
            		return;
            	}
            }
            
            SimpleRenard renard = new SimpleRenard();
            renard.setPositionX(ligne);
            renard.setPositionY(colonne);
            renard.setOrientation(orient);
            renard.setTrajet(path);

            fList.add(renard);

            if(fList.size() == nbF){
                frame.setVisible(false);
                LOGGER.debug("Les renards sont finies! =>" + nbF);
            }

            l.setText("");
            c.setText("");
            o.setText("");
            p.setText("");


        }
    }
    
    */
    
    /////////////////////////////////////////////    FINALISATION    //////////////////////////////////////////////////////

    
    // Gestion de la finalisation
    
    public class EditFin implements ActionListener{
        
        JFrame frame;
        JFrame popup;
        
        public EditFin(JFrame frame)
        {
            this.frame = frame;
        }
    
        public void actionPerformed(ActionEvent e) {
            if(theJardin == null || cList == null || rList == null /*|| fList == null*/)
            {
                LOGGER.debug("Pas tous fini!");
                new PopUp();
            }
            else
            {
                LOGGER.debug("Toutes les étapes sont OK");
                new WriteCSV(frame);
            }
            
        }
        
    }
    
    
    // Gestion de la demande de finalisation sans toutes les étapes
    private class PopUp{
        JFrame frame;
        
        public PopUp(){
            frame = new JFrame("Erreur");
            frame.setSize(new Dimension(400,300));
            frame.setLocation(750,300);
            frame.setLayout(null);
            
            JLabel text = new JLabel("Vous devez terminer TOUTES les étapes.");
            JButton ok = new JButton("J'y retourne...");
            
            frame.add(text);
            frame.add(ok);
            
            
            
            ok.setBounds(90,150,200,40);
            text.setBounds(75,50,300,40);
            
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
    
    
    // Gestion de la finalisation avec toutes les étapes accomplies
    
    public class WriteCSV{
        JFrame frame;
        
        public WriteCSV(JFrame frame){
            this.frame = frame;
            new EditorLevel(theJardin, cList, rList);
        }
        
    }
}    