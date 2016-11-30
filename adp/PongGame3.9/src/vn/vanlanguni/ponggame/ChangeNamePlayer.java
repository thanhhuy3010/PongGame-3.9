
package vn.vanlanguni.ponggame;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ChangeNamePlayer extends JDialog{
	JTextField txtUsername1;
	
	JTextField txtUsername2;
	public String playerName1;
	public String playerName2;
	//Xem khai bao MyDialogResult o cuoi class nay
	public MyDialogResult dialogResult;
	
	public ChangeNamePlayer() {
		setPreferredSize(new Dimension(300, 200));
		setTitle("Second Window");
		getContentPane().setLayout(null);
		setModal(true);
		
		dialogResult = MyDialogResult.DEFAULT;
		txtUsername1 = new JTextField(10);
		txtUsername2 = new JTextField(10);
		getContentPane().add(txtUsername1);
		getContentPane().add(txtUsername2);
		txtUsername1.setBounds(90, 26, 100, 20);
		txtUsername2.setBounds(90, 66, 100, 20);
		
		JLabel lblUser_1 = new JLabel("Username 1");
		lblUser_1.setBounds(10, 29, 71, 14);
		getContentPane().add(lblUser_1);
		
		JLabel lblUser_2 = new JLabel("Username 2");
		lblUser_2.setBounds(10, 69, 71, 14);
		getContentPane().add(lblUser_2);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialogResult = MyDialogResult.YES;
				setVisible(false);
				playerName1 = getSetings().getUserName1();
				playerName2 = getSetings().getUserName2();
				
			}
		});
		btnSave.setBounds(44, 114, 89, 23);
		getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogResult = MyDialogResult.CANCEL;
				setVisible(false);
			}
		});
		btnCancel.setBounds(154, 114, 89, 23);
		getContentPane().add(btnCancel);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		pack();
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				int result = JOptionPane.showConfirmDialog(ChangeNamePlayer.this, "Exit?");
				if(result == JOptionPane.YES_OPTION){
					setVisible(false);
				}				
			}
		});
	}
	
	public Settings getSetings(){
		Settings st = new Settings();
		st.setUserName1(txtUsername1.getText());
		st.setUserName2(txtUsername2.getText());
		
		return st;
	}
}


