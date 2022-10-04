
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class Registration extends JInternalFrame implements KeyListener, ActionListener{

	private JDesktopPane desktopPane;
	private JPanel allPnl;
	private JLabel registrationTitle;
	private JPanel registrationPnl;
	private JPanel memberNamePnl;
	private JPanel memberNameLblPnl;
	private JLabel memberNameLbl;
	private JPanel memberNameTxtPnl;
	private JPanel memberPhoneNoPnl;
	private JPanel memberPhNoLblPnl;
	private JLabel memberPhNoLbl;
	private JPanel memberBDatePnl;
	private JPanel bDateLblPnl;
	private JPanel phoneNoLbl;
	private JLabel bDateLbl;
	private JPanel birthDateChooserPnl;
	private JPanel southPnl;
	private JPanel agreementPnl;
	private JPanel btnPnl;
	private JButton registerBtn;
	
	
	private JTextField memberNameTxt;
	private JTextField phNoTxt;
	JDateChooser birthDateChooser;
	JCheckBox agreementCb ;
	
	Connect c;
	
	public Registration() {
		setClosable(true);
		setTitle("New Member Registration");
		setSize(500, 300);
		setLocation(650, 0);
		ImageIcon imgIcon = new ImageIcon(MainMenu.class.getResource("icon-Mil Street Food.png"));
		setFrameIcon(imgIcon);
		
		c= new Connect();
		
		init();
	}

	private void init() {
		allPnl = new JPanel();
		allPnl.setBackground(new Color(255, 248, 220));
		allPnl.setBorder(new EmptyBorder(20, 20, 20, 20));
		add(allPnl, BorderLayout.CENTER);
		allPnl.setLayout(new BorderLayout(5, 5));
		
		registrationTitle = new JLabel("New Member Registration",JLabel.CENTER);
		registrationTitle.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		allPnl.add(registrationTitle, BorderLayout.NORTH);
		
		registrationPnl = new JPanel();
		registrationPnl.setBackground(new Color(255, 248, 220));
		allPnl.add(registrationPnl, BorderLayout.CENTER);
		registrationPnl.setLayout(new GridLayout(0, 1, 0, 10));
		
		memberNamePnl = new JPanel();
		memberNamePnl.setBackground(new Color(255, 248, 220));
		registrationPnl.add(memberNamePnl);
		memberNamePnl.setLayout(new BorderLayout(0, 0));
		
		memberNameLblPnl = new JPanel();
		memberNameLblPnl.setBackground(new Color(255, 248, 220));
		memberNamePnl.add(memberNameLblPnl, BorderLayout.WEST);
		
		memberNameLbl = new JLabel("Member Name");
		memberNameLbl.setFont(new Font("Calibri", Font.BOLD, 16));
		memberNameLblPnl.add(memberNameLbl);
		
		memberNameTxtPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
		memberNameTxtPnl.setBackground(new Color(255, 248, 220));
		memberNamePnl.add(memberNameTxtPnl);
		
		memberNameTxt = new JTextField();
		memberNameTxt.setBackground(new Color(255, 248, 220));
		memberNameTxtPnl.add(memberNameTxt);
		memberNameTxt.setColumns(25);
		
		memberPhoneNoPnl = new JPanel();
		memberPhoneNoPnl.setBackground(new Color(255, 248, 220));
		registrationPnl.add(memberPhoneNoPnl);
		memberPhoneNoPnl.setLayout(new BorderLayout());
		
		memberPhNoLblPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
		memberPhNoLblPnl.setBackground(new Color(255, 248, 220));
		memberPhoneNoPnl.add(memberPhNoLblPnl, BorderLayout.WEST);
		
		memberPhNoLbl = new JLabel("Member Phone Number");
		memberPhNoLbl.setFont(new Font("Calibri", Font.BOLD, 16));
		memberPhNoLblPnl.add(memberPhNoLbl);
		
		phoneNoLbl = new JPanel(new FlowLayout(FlowLayout.CENTER));
		phoneNoLbl.setBackground(new Color(255, 248, 220));
		memberPhoneNoPnl.add(phoneNoLbl, BorderLayout.CENTER);
		
		phNoTxt = new JTextField();
		phNoTxt.setBackground(new Color(255, 248, 220));
		phoneNoLbl.add(phNoTxt);
		phNoTxt.setColumns(15);
		phNoTxt.addKeyListener(this);
		
		memberBDatePnl = new JPanel();
		memberBDatePnl.setBackground(new Color(255, 248, 220));
		registrationPnl.add(memberBDatePnl);
		memberBDatePnl.setLayout(new BorderLayout(5, 5));
		
		bDateLblPnl = new JPanel();
		bDateLblPnl.setBackground(new Color(255, 248, 220));
		memberBDatePnl.add(bDateLblPnl, BorderLayout.WEST);
		bDateLblPnl.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		bDateLbl = new JLabel("Member Birth Date");
		bDateLbl.setFont(new Font("Calibri", Font.BOLD, 16));
		bDateLblPnl.add(bDateLbl);
		
		birthDateChooserPnl = new JPanel();
		birthDateChooserPnl.setBackground(new Color(255, 248, 220));
		memberBDatePnl.add(birthDateChooserPnl, BorderLayout.CENTER);
		birthDateChooserPnl.setLayout(new GridLayout(0, 2, 0, 0));
		
		birthDateChooser = new JDateChooser();
		birthDateChooser.getCalendarButton().setBackground(new Color(255, 248, 220));
		birthDateChooser.setBackground(new Color(255, 248, 220));
		birthDateChooserPnl.add(birthDateChooser);
		
		southPnl = new JPanel();
		allPnl.add(southPnl, BorderLayout.SOUTH);
		southPnl.setLayout(new GridLayout(2, 0, 0, 0));
		
		agreementPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
		agreementPnl.setBackground(new Color(255, 248, 220));
		southPnl.add(agreementPnl);
		
		agreementCb = new JCheckBox("Customer Agree Terms and Conditions");
		agreementCb.setBackground(new Color(255, 248, 220));
		agreementPnl.add(agreementCb);
		agreementCb.addActionListener(this);
		
		btnPnl = new JPanel();
		btnPnl.setBackground(new Color(255, 248, 220));
		southPnl.add(btnPnl);
		
		registerBtn = new JButton("Register New Member");
		btnPnl.add(registerBtn);
		registerBtn.setEnabled(false);
		registerBtn.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ResultSet rs = null;
		boolean idIsUsed=false;
		String memberId = null;
		
		if(arg0.getSource()==agreementCb) {
			if(agreementCb.isSelected())
				registerBtn.setEnabled(true);
			else registerBtn.setEnabled(false);
		}
		
		if(arg0.getSource()==registerBtn) {
			if(memberNameTxt.getText().isEmpty()||phNoTxt.getText().isEmpty()||birthDateChooser.getDate()==null) {
				JOptionPane.showMessageDialog(this, "All field must be filled","Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(phNoTxt.getText().length()<10||phNoTxt.getText().length()>15) {
				JOptionPane.showMessageDialog(this, "Phone Number must be between 10 - 15 numbers","Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(!phNoTxt.getText().startsWith("08")) {
				JOptionPane.showMessageDialog(this, "Phone Number must starts with \"08\"","Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			String memberName = memberNameTxt.getText();
			String phNo = phNoTxt.getText();
			java.sql.Date sqldate = new java.sql.Date(birthDateChooser.getDate().getTime());
			
			//generate MemberID
			String checkId = "SELECT memberId from member WHERE memberId = '%s' ";
			do {
				idIsUsed=false;
				memberId = "MB";
				for (int i = 0; i < 3; i++) {
					memberId += Integer.toString((int) (Math.random() * 10));
				} 
				checkId = String.format(checkId, memberId);
				rs = c.executeQuery(checkId);
				try {
					while(rs.next()) {
						idIsUsed=true;
					}
				} catch (SQLException e) {
				}
			} while (idIsUsed);
			
			//updateDB
			String queryInsert = "INSERT INTO member(memberId, memberName, memberPhoneNumber, memberBirthdate) VALUES ('%s','%s','%s','%s')";
			queryInsert= String.format(queryInsert, memberId, memberName, phNo, sqldate);
			c.executeUpdate(queryInsert);
			
			JOptionPane.showMessageDialog(this, "Member Registered \nMemberId: "+memberId,"Success", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		if(arg0.getSource()==phNoTxt) {
			String x = Character.toString(arg0.getKeyChar());
			if("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()_-+={}\\|;:\"\'>.<,?/~`".contains(x)) {
				JOptionPane.showMessageDialog(this, "Must be numeric");
				getToolkit().beep();
				arg0.consume();
				return;
			}
			if(phNoTxt.getText().length()+1>15) {
				getToolkit().beep();
				arg0.consume();
				return;
			}
		}
		
		
	}

}
