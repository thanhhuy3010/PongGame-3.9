package vn.vanlanguni.ponggame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ChangeButtonPlayer extends JDialog {
	private ButtonGroup btng;
	private JRadioButton btnpd1,btnpd2,btnpd3;
	private ImageIcon imgpd1,imgbg2,imgbg3;
	private String namePd1,namePd2;
	ve panel = new ve();
	
	//Xem khai bao MyDialogResult o cuoi class nay
	public MyDialogResult dialogResult;
	
	public ChangeButtonPlayer() {
		setPreferredSize(new Dimension(300, 250));
		setTitle("Change Paddles");
		getContentPane().setLayout(null);
		setModal(true);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(150,10,100,100);
		
		
		btng = new ButtonGroup();
		btnpd1 = new JRadioButton("Bong Den");
		btnpd2 = new JRadioButton("Kien");
		
		btnpd3 = new JRadioButton("vot Tennis");
		btng.add(btnpd1);btng.add(btnpd2);btng.add(btnpd3);
		dialogResult = MyDialogResult.DEFAULT;
		btnpd1.setBounds(10,10,100,25);
		btnpd2.setBounds(10,40,100,25);
		btnpd3.setBounds(10,70,100,25);
		btnpd1.setSelected(true);
		getContentPane().add(btnpd1);
		getContentPane().add(btnpd2);
		getContentPane().add(btnpd3);
		getContentPane().add(panel);
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dialogResult = MyDialogResult.YES;
				setVisible(false);
			}
		});
		btnSave.setBounds(44, 114+50, 89, 23);
		getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogResult = MyDialogResult.CANCEL;
				setVisible(false);
			}
		});
		btnCancel.setBounds(154, 114+50, 89, 23);
		getContentPane().add(btnCancel);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				int result = JOptionPane.showConfirmDialog(ChangeButtonPlayer.this, "Exit?");
				if(result == JOptionPane.YES_OPTION){
					setVisible(false);
				}				
			}
		});
	}
	
	public Settings getSetings(){
		Settings st = new Settings();
		if ( btnpd1.isSelected()){
			namePd1 = "button/button2.png";
			
			st.setpd1(namePd1 );
			
			
			
		}else if (btnpd2.isSelected()){
			namePd1 = "button/button1.png";
		
			st.setpd2(namePd1 );
			
			
		}else if (btnpd3.isSelected()){
			namePd1 = "button/button3.png";
	
			st.setpd3(namePd1 );
			
			
		}
		return st;
		
	}

	
	

	public class ve extends JPanel{

		/* (non-Javadoc)
		 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
		 */
		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);
			
			
			
			if ( btnpd1.isSelected()){
				
				namePd1 = "button/button2.png";
				repaint();
			}else if (btnpd2.isSelected()){
				namePd1 = "button/button1.png";
				repaint();
			}else if (btnpd3.isSelected()){
				namePd1 = "button/button3.png";
				repaint();
			}
			imgpd1 = new ImageIcon(namePd1);
			g.drawImage(imgpd1.getImage(),0,0,100,100,null);
			
		}
		
	}

	
	
	
}


