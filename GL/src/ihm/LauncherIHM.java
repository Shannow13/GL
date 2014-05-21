package ihm;

import javax.swing.JFrame;

//import org.apache.log4j.Logger;

import ihm.Menu;

public class LauncherIHM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//private static final Logger LOGGER = Logger.getLogger(LauncherIHM.class);

		/**
		 * @param args
		 */
		//LOGGER.debug("TP des chiens : DEBUT");

		// final ChienJFrame1 f = new ChienJFrame1();
		// final ChienJFrame2 f = new ChienJFrame2();
		// final JFrame f = new ChienJFrame3();
		// final JFrame f = new ChienJFrame4();
		// final JFrame f = new ChienJFrame5();
		// final JFrame f = new ChienJFrame6();
		// final JFrame f = new ChienJFrame7();
		
		final JFrame f = new Menu();
		f.setVisible(true);

		//LOGGER.debug("TP des chiens : FIN");

	}


}