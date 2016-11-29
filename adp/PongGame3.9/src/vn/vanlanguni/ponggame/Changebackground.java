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
public class Changebackground extends JDialog {
	private ButtonGroup btng;
	private JRadioButton btnbg1,btnbg2,btnbg3;
	private ImageIcon imgbg1,imgbg2,imgbg3;
	private String nameBg;
	ve panel = new ve();
	
	//Xem khai bao MyDialogResult o cuoi class nay
	public MyDialogResult dialogResult;
	
	public Changebackground() {
		setPreferredSize(new Dimension(300, 250));
		setTitle("Change changebackground Playing");
		getContentPane().setLayout(null);
		setModal(true);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(150,10,100,100);
		
		
		btng = new ButtonGroup();
		btnbg1 = new JRadioButton("San Bong Da");
		btnbg2 = new JRadioButton("San Bong Ro");
		
		btnbg3 = new JRadioButton("San Tennis");
		btng.add(btnbg1);btng.add(btnbg2);btng.add(btnbg3);
		dialogResult = MyDialogResult.DEFAULT;
		btnbg1.setBounds(10,10,100,25);
		btnbg2.setBounds(10,40,100,25);
		btnbg3.setBounds(10,70,100,25);
		btnbg1.setSelected(true);
		getContentPane().add(btnbg1);
		getContentPane().add(btnbg2);
		getContentPane().add(btnbg3);
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
				int result = JOptionPane.showConfirmDialog(Changebackground.this, "Exit?");
				if(result == JOptionPane.YES_OPTION){
					setVisible(false);
				}				
			}
		});
	}
	
	public Settings getSetings(){
		Settings st = new Settings();
		if ( btnbg1.isSelected()){
			nameBg = "changebackground/san bong da.jpg";
			st.setbg1(nameBg );
			
			
		}else if (btnbg2.isSelected()){
			nameBg = "changebackground/san bong ro.png";
			st.setbg2(nameBg );
			
			
		}else if (btnbg3.isSelected()){
			nameBg = "changebackground/san tennis.png";
			st.setbg3(nameBg );
			
			
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
			
			
			
			if ( btnbg1.isSelected()){
				
				nameBg = "changebackground/san bong da.jpg";
				repaint();
			}else if (btnbg2.isSelected()){
				nameBg = "changebackground/san bong ro.png";
				repaint();
			}else if (btnbg3.isSelected()){
				nameBg = "changebackground/san tennis.png";
				repaint();
			}
			imgbg1 = new ImageIcon(nameBg);
			g.drawImage(imgbg1.getImage(),0,0,100,100,null);
			
		}
		
	}

	
	
	
}


