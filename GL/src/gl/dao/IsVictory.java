package gl.dao;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IsVictory extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public IsVictory() {
		
		final JFrame frmWin = new JFrame("Victoire !");
		
		frmWin.setSize(new Dimension(500,500));
		frmWin.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frmWin.setLocation(700, 300);
		
		JPanel panWin = new JPanel();
		
		JButton btnMenu = new JButton("GG !");
		
		btnMenu.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	frmWin.setVisible(false);
            	frmWin.dispose();
            }
        });
		
		panWin.add(btnMenu);
		
		frmWin.add(panWin);
		
		JLabel imgWin = new JLabel(new ImageIcon("resources/img/victoire.png"));
		imgWin.setPreferredSize(new Dimension(100, 100));
		frmWin.add(imgWin, BorderLayout.CENTER);
		frmWin.add(panWin, BorderLayout.SOUTH);
		
		frmWin.validate();
		frmWin.setVisible(true);
		
	}
	
}