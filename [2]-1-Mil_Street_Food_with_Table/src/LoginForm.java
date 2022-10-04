import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


public class LoginForm extends JFrame implements ActionListener, KeyListener, ItemListener{

	private Connect c;
	
	private JLabel usernameLbl;
	private JLabel passLbl;
	private JLabel showPass;

	private JTextField usernameTxt;
	private JPasswordField passTxt;
	
	private JButton loginBtn;
	
	private JCheckBox showPassCheckBox;

	private JPanel formPanel, userNamePanel, passwordPanel, checkBoxPanel, buttonPanel, userNameLblPanel, passwordLblPanel, userNameFieldPanel, passwordFieldPanel;
	public LoginForm() {
		setLayout(new BorderLayout());
		c=new Connect();
		
		init();

		setTitle("Mil Street Food-Operator");
		setSize(new Dimension(420,225));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		Image imgIcon = new ImageIcon(MainMenu.class.getResource("logo-Mil Street Food.png")).getImage();
		setIconImage(imgIcon);
		
	}
	private void init() {
		formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(4, 1, 0, 0));
		formPanel.setBorder(new TitledBorder(null, "Login", TitledBorder.LEFT, TitledBorder.TOP,new Font("Arial", Font.BOLD, 15)));
		add(formPanel, BorderLayout.CENTER);
		
		
		userNamePanel = new JPanel(new BorderLayout());
		formPanel.add(userNamePanel);
		
		userNameLblPanel = new JPanel();
		userNamePanel.add(userNameLblPanel, BorderLayout.WEST);
		
		usernameLbl = new JLabel("User Name:");
		usernameLbl.setFont(new Font("Arial", Font.BOLD, 15));
		userNameLblPanel.add(usernameLbl);
		
		userNameFieldPanel = new JPanel();
		userNameFieldPanel.setBorder(new EmptyBorder(0, 10, 0, 0));
		userNamePanel.add(userNameFieldPanel, BorderLayout.CENTER);
		
		usernameTxt = new JTextField("");
		usernameTxt.setColumns(20);
		usernameTxt.setFont(new Font("Arial", Font.PLAIN, 15));
		usernameTxt.addKeyListener(this);
		userNameFieldPanel.add(usernameTxt);
		
		passwordPanel = new JPanel(new BorderLayout());
		formPanel.add(passwordPanel);
		
		passwordLblPanel = new JPanel();
		passwordPanel.add(passwordLblPanel,BorderLayout.WEST);
		
		passLbl = new JLabel("Password:  ");
		passLbl.setFont(new Font("Arial", Font.BOLD, 15));
		passwordLblPanel.add(passLbl, BorderLayout.CENTER);
		
		passwordFieldPanel = new JPanel();
		passwordFieldPanel.setBorder(new EmptyBorder(0, 10, 0, 0));
		passwordPanel.add(passwordFieldPanel);
		
		passTxt = new JPasswordField();
		passTxt.setColumns(20);
		passTxt.setFont(new Font("Arial", Font.PLAIN, 15));
		passTxt.addKeyListener(this);
		passwordFieldPanel.add(passTxt);
		
		checkBoxPanel = new JPanel();
		formPanel.add(checkBoxPanel);
		
		
		buttonPanel = new JPanel();

		loginBtn = new JButton("Login");
		loginBtn.setFont(new Font("Arial", Font.BOLD, 15));
		buttonPanel.add(loginBtn);
		loginBtn.addActionListener(this);

		formPanel.add(buttonPanel);
		
		showPassCheckBox = new JCheckBox("Show Password");
		showPassCheckBox.setFont(new Font("Arial", Font.BOLD, 15));
		checkBoxPanel.add(showPassCheckBox);
//		showPassCheckBox.addActionListener(this);
		showPassCheckBox.addItemListener(this);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {

	}
	@Override
	public void keyTyped(KeyEvent e) {
		String str = Character.toString(e.getKeyChar());
		String check = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890-=!@#$%^&*()_+{}[]:\";'<,>.?/|\\";
			
		if (e.getSource()==usernameTxt) {
				
			if(!check.contains(str)) {
				return;
			}
			
			if(usernameTxt.getText().length()+1>20) {
				JOptionPane.showMessageDialog(this, "Username must less than or equals to 20");
				getToolkit().beep();
				e.consume();
				
			}
		}
		
		if(e.getSource()==passTxt) {
			if(!check.contains(str)) {
				return;
			}
			
			if(new String(passTxt.getPassword()).length()+1>20) {
				JOptionPane.showMessageDialog(this, "password must less than or equals to 20");
				getToolkit().beep();
				e.consume();
			}
			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getSource()==loginBtn) {
			ResultSet rs;
			
			String userTemp = usernameTxt.getText();
			String pwTemp = new String(passTxt.getPassword());
			if(userTemp.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Username field must be filled!");
				return;
			}
			if(pwTemp.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Password field must be filled!");
				return;
			}
			
				String query = "SELECT * FROM user U JOIN OPERATOR O ON O.id=U.id WHERE username = '%s'";
				query = String.format(query, userTemp);
				rs = c.executeQuery(query);
				try {
					if(!rs.next()) {
						JOptionPane.showMessageDialog(this, "username as an operator not found");
						return;
					}
					
					else {
						if(!pwTemp.equals(rs.getString("userPassword"))) {
							JOptionPane.showMessageDialog(this, "password not match!");
							return;
						}
						else {
							JOptionPane.showMessageDialog(this, "Welcome to work, "+rs.getString("userFullName"));
							Operator o = new Operator(rs.getInt("id"), rs.getString("userFullName"), rs.getString("username"), rs.getString("userPassword"), rs.getString("userMail"));
							
							dispose();
							
							new MainMenu(o).frmMilStreetFood.setVisible(true);
							
						
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==showPassCheckBox) {
			if(showPassCheckBox.isSelected()) 
				passTxt.setEchoChar((char)0);
			else {
				String temp = new String(passTxt.getPassword());
				passTxt.setEchoChar('•');
			}
		}
	}

}
