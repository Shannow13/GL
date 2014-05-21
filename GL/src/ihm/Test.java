package ihm;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Test
{
	public static void main(String[] args)
	{
		JFrame frm = new JFrame( "ton prog " );
		frm.getContentPane().setLayout( new BorderLayout() );
		frm.getContentPane().add( new Graph(), BorderLayout.CENTER );
		int w = (int) Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = (int) Toolkit.getDefaultToolkit().getScreenSize().height;
		frm.setSize( w, h );
		frm.setVisible( true );
	}
}

class Graph extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Image bg = getToolkit().getImage("resources/bugs_bunny.jpg");

	JButton btnNew = new JButton("NOUVELLE PARTIE");
	JButton btnCont = new JButton("CONTINUER");
	JButton btnLoad = new JButton("CHARGER PARTIE");
	JButton btnHelp = new JButton("AIDE");
	JButton btnExit = new JButton("QUITTER");
	
	int width = (int) Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = (int) Toolkit.getDefaultToolkit().getScreenSize().height;

	public Graph()
	{
		setBounds(0, 0, width, height);

		setLayout(null);

		add( btnNew );
		add( btnCont );
		add( btnLoad );
		add( btnHelp );
		add( btnExit );
		
		int w = ( width / 2) - 50;
		int h = ( height / 2 ) - 150;

		btnNew.setBounds( w - 50, h - 100, 200, 40);
		btnCont.setBounds( w - 50, h, 200, 40 );
		btnLoad.setBounds( w - 50, h + 100, 200, 40);
		btnHelp.setBounds( w - 50, h + 200, 200, 40 );
		btnExit.setBounds( w - 50, h + 300, 200, 40 );

		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		
		setOpaque( false );
		setBackground( Color.blue );
	}

	public void paint(Graphics g)
	{
		g.drawImage( bg, 0, 0, width, height, this );
		super.paint( g );
	}
} 
