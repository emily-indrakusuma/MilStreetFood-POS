import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class ManageProfile extends JInternalFrame implements ActionListener{
	private JTextField nameTxt;
	private JTextField emailTxt;
	private JPasswordField pwTxt;
	private JPasswordField newPwTxt;
	private JPasswordField rePwTxt;
	private JPanel titlePnl;
	private JLabel manageProfilLbl;
	private JPanel formPanel;
	private JPanel changeExcpPwPnl;
	private JPanel usernamePnl;
	private JLabel usernameAnsLbl;
	private JLabel usernameLbl;
	private JPanel namePnl;
	private JLabel nameLbl;
	private JPanel emailPnl;
	private JLabel emailLbl;
	private JPanel btnPnl; 
	private JPanel chgProfBtnPnl; 
	private JButton changeProfileBtn;
	private JPanel operatorNamePnl;
	private JPanel inputPwPnl;
	private JLabel inputPwLbl;
	private JPanel inputNewPwPnl;
	private JLabel newPwLbl;
	private JPanel cnfPwPnl;
	private JLabel lblReinputNewPassword;
	private JPanel chgPwPnl;
	private JPanel checkPwPnl;
	private JPanel chgPwBtnPnl;
	private JButton chgPwBtn; 
	private JCheckBox showPwCb;
	
	private Operator o;
	private MainMenu mm;
	Connect c;
	public ManageProfile(Operator o, MainMenu mm) {
		setTitle("Manage Profile");
		setBackground(new Color(255, 248, 220));
		setClosable(true);
		setSize(600, 375);
		ImageIcon imgIcon = new ImageIcon(MainMenu.class.getResource("icon-Mil Street Food.png"));
		setFrameIcon(imgIcon);
		
		this.o = o;
		this.mm = mm;
		c  = new Connect();
		
		init();
		
	}
	private void init() {
		titlePnl = new JPanel();
		titlePnl.setBackground(new Color(255, 248, 220));
		add(titlePnl, BorderLayout.NORTH);
		
		manageProfilLbl = new JLabel("Manage Profile");
		
		manageProfilLbl.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		titlePnl.add(manageProfilLbl);
		
		formPanel = new JPanel();
		formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		formPanel.setBackground(new Color(255, 248, 220));
		add(formPanel, BorderLayout.CENTER);
		formPanel.setLayout(new GridLayout(0, 2, 10, 0));
		
		changeExcpPwPnl = new JPanel();
		changeExcpPwPnl.setBackground(new Color(255, 248, 220));
		changeExcpPwPnl.setBorder(new TitledBorder(null, "Change Profile Information", TitledBorder.LEADING, TitledBorder.TOP));
		formPanel.add(changeExcpPwPnl);
		changeExcpPwPnl.setLayout(new GridLayout(4, 0, 0, 20));
		
		usernamePnl = new JPanel();
		usernamePnl.setBackground(new Color(255, 248, 220));
		changeExcpPwPnl.add(usernamePnl);
		usernamePnl.setLayout(new GridLayout(0, 2, 0, 0));
		
		usernameLbl = new JLabel("Username: ");
		usernamePnl.add(usernameLbl);
		
		usernameAnsLbl = new JLabel(o.getOperatorUsername());
		usernamePnl.add(usernameAnsLbl);
		
		namePnl = new JPanel();
		namePnl.setBackground(new Color(255, 248, 220));
		changeExcpPwPnl.add(namePnl);
		namePnl.setLayout(new BorderLayout(10, 10));
		
		nameLbl = new JLabel("Name: ");
		namePnl.add(nameLbl, BorderLayout.WEST);
		
		nameTxt = new JTextField(o.getOperatorName());
		nameTxt.setBackground(new Color(255, 248, 220));
		namePnl.add(nameTxt);
		nameTxt.setColumns(10);
		
		emailPnl = new JPanel();
		emailPnl.setBackground(new Color(255, 248, 220));
		changeExcpPwPnl.add(emailPnl);
		emailPnl.setLayout(new BorderLayout(0, 0));
		
		emailLbl = new JLabel("Email:    ");
		emailPnl.add(emailLbl, BorderLayout.WEST);
		
		emailTxt = new JTextField(o.getOperatorEmail());
		emailTxt.setBackground(new Color(255, 248, 220));
		emailPnl.add(emailTxt);
		emailTxt.setColumns(10);
		
		btnPnl = new JPanel();
		btnPnl.setBackground(new Color(255, 248, 220));
		changeExcpPwPnl.add(btnPnl);
		
		chgProfBtnPnl = new JPanel();
		chgProfBtnPnl.setBackground(new Color(255, 248, 220));
		btnPnl.add(chgProfBtnPnl);
		
		changeProfileBtn = new JButton("Change Profile");
		changeProfileBtn.addActionListener(this);
		chgProfBtnPnl.add(changeProfileBtn);
		
		operatorNamePnl = new JPanel();
		operatorNamePnl.setBorder(new TitledBorder(null, "Change Password", TitledBorder.LEADING, TitledBorder.TOP));
		operatorNamePnl.setBackground(new Color(255, 248, 220));
		formPanel.add(operatorNamePnl);
		operatorNamePnl.setLayout(new GridLayout(4, 0, 0, 5));
		
		inputPwPnl = new JPanel();
		inputPwPnl.setBackground(new Color(255, 248, 220));
		operatorNamePnl.add(inputPwPnl);
		inputPwPnl.setLayout(new GridLayout(2, 0, 0, 0));
		
		inputPwLbl = new JLabel("Input current password:");
		inputPwPnl.add(inputPwLbl);
		
		pwTxt = new JPasswordField();
		pwTxt.setBackground(new Color(255, 248, 220));
		inputPwPnl.add(pwTxt);
		
		
		inputNewPwPnl = new JPanel();
		inputNewPwPnl.setBackground(new Color(255, 248, 220));
		operatorNamePnl.add(inputNewPwPnl);
		inputNewPwPnl.setLayout(new GridLayout(2, 1, 0, 0));
		
		newPwLbl = new JLabel("Input new password:");
		inputNewPwPnl.add(newPwLbl);
		
		newPwTxt = new JPasswordField();
		newPwTxt.setBackground(new Color(255, 248, 220));
		inputNewPwPnl.add(newPwTxt);
		
		cnfPwPnl = new JPanel();
		cnfPwPnl.setBackground(new Color(255, 248, 220));
		operatorNamePnl.add(cnfPwPnl);
		cnfPwPnl.setLayout(new GridLayout(2, 0, 0, 0));
		
		lblReinputNewPassword = new JLabel("Re-input new password: ");
		cnfPwPnl.add(lblReinputNewPassword);
		
		rePwTxt = new JPasswordField();
		rePwTxt.setBackground(new Color(255, 248, 220));
		cnfPwPnl.add(rePwTxt);
		
		chgPwPnl = new JPanel();
		operatorNamePnl.add(chgPwPnl);
		chgPwPnl.setLayout(new BorderLayout(0, 0));
		
		checkPwPnl = new JPanel();
		checkPwPnl.setBackground(new Color(255, 248, 220));
		chgPwPnl.add(checkPwPnl, BorderLayout.NORTH);
		checkPwPnl.setLayout(new BorderLayout(0, 0));
		
		showPwCb = new JCheckBox("Show Password");
		showPwCb.setHorizontalAlignment(SwingConstants.CENTER);
		showPwCb.setBackground(new Color(255, 248, 220));
		checkPwPnl.add(showPwCb);
		showPwCb.addActionListener(this);
		
		chgPwBtnPnl = new JPanel();
		chgPwBtnPnl.setBackground(new Color(255, 248, 220));
		chgPwPnl.add(chgPwBtnPnl);
		
		chgPwBtn = new JButton("Change Password");
		chgPwBtn.setVerticalAlignment(SwingConstants.TOP);
		chgPwBtnPnl.add(chgPwBtn);
		chgPwBtn.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==showPwCb) {
			if(showPwCb.isSelected()) {
				pwTxt.setEchoChar((char)0);
				newPwTxt.setEchoChar((char)0);
				rePwTxt.setEchoChar((char)0);
			}
			else {
				pwTxt.setEchoChar('•');
				newPwTxt.setEchoChar('•');
				rePwTxt.setEchoChar('•');
			}
		}
		if(e.getSource()==changeProfileBtn) {
			if(emailTxt.getText().isEmpty()||nameTxt.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please fill all field", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(!emailTxt.getText().endsWith(".com")) {
				JOptionPane.showMessageDialog(this, "Email must ends with \".com\"", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			String queryUpdateProfile = "UPDATE user set userMail = '%s', userFullName = '%s' WHERE id = %s";
			queryUpdateProfile = String.format(queryUpdateProfile, emailTxt.getText().toString(), nameTxt.getText().toString(), o.getId());
			
			c.executeUpdate(queryUpdateProfile);
			
			JOptionPane.showMessageDialog(this, "Update Success", "Success", JOptionPane.INFORMATION_MESSAGE);
			
		}
		
		if(e.getSource()==chgPwBtn) {
			String oldPw = new String(pwTxt.getPassword());
			String newPw = new String(newPwTxt.getPassword());
			String rePW = new String(rePwTxt.getPassword());
			
			if(oldPw.isEmpty()||newPw.isEmpty()||rePW.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please fill all field", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			if(!newPw.equals(rePW)) {
				JOptionPane.showMessageDialog(this, "Reconfirm Password Not Match", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			if(!oldPw.equals(o.getOperatorPassword())) {
				JOptionPane.showMessageDialog(this, "Current Password Not Match", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			if(oldPw.equals(newPw)) {
				JOptionPane.showMessageDialog(this, "New password can't be same with old password", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			if(newPw.length()<6) {
				JOptionPane.showMessageDialog(this, "Password must be more than or equals 6 character", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			String updatePw = "UPDATE user SET userPassword = '%s' WHERE id = %s";
			updatePw = String.format(updatePw, newPw, o.getId());
			c.executeUpdate(updatePw);
			
			o.setOperatorPassword(newPw);
			
			JOptionPane.showMessageDialog(this, "Password Changed", "Success", JOptionPane.INFORMATION_MESSAGE);
			mm.frmMilStreetFood.dispose();
			new LoginForm().setVisible(true);
			
		}
		
	}

}
