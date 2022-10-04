

import javax.swing.JFrame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class MainMenu extends JFrame implements ActionListener {

	private JMenuBar menuBar;
	private JMenu menu, transaction, member, profile;
	private JMenuItem entry, addMenu, viewReport, checkPoint, newMember, manageProfile,  logout;
	private Operator o;
	private JDesktopPane desktopPane;
	
	JFrame frmMilStreetFood;
	protected SalesEntryInternalFrame sif;
	protected Food f;
	public MainMenu(Operator o) {

		f=new Food();
		this.o=o;
		
		frmMilStreetFood = new JFrame();
		frmMilStreetFood.setTitle("Mil Street Food");
		frmMilStreetFood.setSize(1190,730);
		frmMilStreetFood.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMilStreetFood.setLocationRelativeTo(null);
		
		//meletakkan image icon di window
		Image imgIcon = new ImageIcon(LoginForm.class.getResource("logo-Mil Street Food.png")).getImage();
		frmMilStreetFood.setIconImage(imgIcon);
		
		
		desktopPane = new JDesktopPane();
		frmMilStreetFood.setContentPane(desktopPane);
		desktopPane.setBackground(new Color(255, 248, 220));
		
		init();

	}

	private void init() {
		menuBar = new JMenuBar();
		frmMilStreetFood.setJMenuBar(menuBar);

		menu = new JMenu("Main");
		menuBar.add(menu);
		

		entry = new JMenuItem("New Sales Entry");
		menu.add(entry);
		entry.addActionListener(this);
		
		addMenu = new JMenuItem ("Add New Menu");
		menu.add(addMenu);
		addMenu.addActionListener(this);

		transaction = new JMenu("Transaction");
		menuBar.add(transaction);

		viewReport = new JMenuItem("View Report");
		transaction.add(viewReport);
		viewReport.addActionListener(this);
		
		member = new JMenu("Member");
		menuBar.add(member);
		
		checkPoint = new JMenuItem("Check Point");
		member.add(checkPoint);
		checkPoint.addActionListener(this);
		
		newMember = new JMenuItem("New Member");
		member.add(newMember);
		newMember.addActionListener(this);
		
		profile = new JMenu("Profile");
		menuBar.add(profile);
		
		manageProfile = new JMenuItem("Manage Profile");
		profile.add(manageProfile);
		manageProfile.addActionListener(this);
		
		logout = new JMenuItem("Logout");
		profile.add(logout);
		logout.addActionListener(this);
		sif = new SalesEntryInternalFrame(o, desktopPane, f);
		desktopPane.add(sif).setVisible(true);
		

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==entry) {
			desktopPane.remove(sif);
			desktopPane.add(sif).setVisible(true);
		}
		
		if(arg0.getSource()==addMenu) {
			AddItem ai = new AddItem(f,sif);
			desktopPane.add(ai);
			ai.setVisible(true);
		}
		
		if(arg0.getSource()==checkPoint) {
			CheckPoint cp = new CheckPoint();
			desktopPane.add(cp);
			cp.setVisible(true);
		}
		
		if(arg0.getSource()==newMember) {
			Registration regist = new Registration();
			desktopPane.add(regist);
			regist.setVisible(true);
		}
		
		
		if(arg0.getSource()==manageProfile) {
			ManageProfile mp = new ManageProfile(o, this);
			desktopPane.add(mp);
			mp.setVisible(true);
		}
		
		if(arg0.getSource()==viewReport) {
			ViewReport vt = new ViewReport(o);
			desktopPane.add(vt);
			vt.setVisible(true);
		}
		
		if(arg0.getSource()==logout) {
			if(o.isCheckedSupervisor()) {
				JOptionPane.showMessageDialog(this, "See you on your next working day!", "Exit", JOptionPane.INFORMATION_MESSAGE);
				frmMilStreetFood.dispose();
				new LoginForm().setVisible(true);
			}
			else if(!o.isCheckedSupervisor()) {
				JOptionPane.showMessageDialog(this, "Please approve your transaction report today to supervisor!", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}

	}

	

}
