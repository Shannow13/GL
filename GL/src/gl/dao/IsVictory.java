package gl.dao;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IsVictory extends JFrame {

	
	private static final long serialVersionUID = 1L;
	/*private boolean flagWin;
	
	private static final long serialVersionUID = 1L;
	
	public static JFrame getFrm()
    {
        return this.flagWin;
    }

    public static void setFrm(boolean flagWin)
    {
        this.flagWin = flagWin;
    }
	*/
	public IsVictory() {
JFrame frmWin = new JFrame("Victoire !");
		
		frmWin.setSize(new Dimension(500,500));
		frmWin.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panWin = new JPanel();
		
		JButton btnNext = new JButton("Continuer");
		JButton btnReload = new JButton("Rejouer");
		
		panWin.add(btnNext);
		panWin.add(btnReload);
		
		frmWin.add(panWin);
		
		JLabel imgWin = new JLabel(new ImageIcon("resources/img/bugs_bunny.jpg"));
		imgWin.setPreferredSize(new Dimension(100, 100));
		frmWin.add(imgWin, BorderLayout.CENTER);
		frmWin.add(panWin, BorderLayout.SOUTH);
		
		frmWin.validate();
		frmWin.setVisible(true);
		//this.flagWin = flagWin;
		
	}
}
