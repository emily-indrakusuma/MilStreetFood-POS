import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CheckPoint extends JInternalFrame implements ActionListener, MouseListener {
	
	private JPanel titlePanel;
	private JLabel titleLbl;
	private JPanel formPnl;
	private JPanel searchPanel;
	private JLabel memberSearchLbl;
	private JTextField inputTxt;
	private JButton searchBtn;
	private DefaultTableModel memberDtm;
	private JTable memberTable;
	private JScrollPane memberSp;
	private JPanel pointPanel;
	private JLabel memberIdLbl ;
	private JLabel memberIdAns;
	private JLabel memberNameLbl;
	private JLabel memberNameAns;
	private JLabel currentPointLbl;
	private JLabel currentPointAns;
	
	private ArrayList<Integer>pointMember;
	
	private Connect c;
	
	public CheckPoint() {
		setBackground(new Color(255, 248, 220));
		
		c = new Connect();
		
		init();
		
		setTitle("Check Member Point");
		setClosable(true);
		setSize(450, 300);
		setLocation(700,0);
		ImageIcon imgIcon = new ImageIcon(MainMenu.class.getResource("icon-Mil Street Food.png"));
		setFrameIcon(imgIcon);

		
	}
	

	
	void init() {
		
		pointMember = new ArrayList<>();
		titlePanel = new JPanel();
		titlePanel.setBackground(new Color(255, 248, 220));
		add(titlePanel, BorderLayout.NORTH);

		titleLbl = new JLabel("Check Point Member");
		titleLbl.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		titlePanel.add(titleLbl);

		formPnl = new JPanel();
		formPnl.setBackground(new Color(255, 248, 220));
		formPnl.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(formPnl, BorderLayout.CENTER);
		formPnl.setLayout(new BorderLayout(0, 3));

		searchPanel = new JPanel();
		searchPanel.setBackground(new Color(255, 248, 220));
		formPnl.add(searchPanel, BorderLayout.NORTH);
		searchPanel.setLayout(new BorderLayout(10, 0));

		memberSearchLbl = new JLabel("Input Member Name: ");
		searchPanel.add(memberSearchLbl, BorderLayout.WEST);

		inputTxt = new JTextField();
		searchPanel.add(inputTxt, BorderLayout.CENTER);
		inputTxt.setColumns(10);

		searchBtn = new JButton("Search");
		searchPanel.add(searchBtn, BorderLayout.EAST);
		searchBtn.addActionListener(this);

		Object [] colName = {"Id","Name", "Phone Number", "Birth Date"};

		memberDtm = new DefaultTableModel(colName,0) ;

		memberTable = new JTable(memberDtm);
		memberTable.setBackground(new Color(255, 248, 220));
		memberTable.getTableHeader().setResizingAllowed(false);
		
		
		TableColumnModel memberTcm = memberTable.getColumnModel();
		memberTcm.getColumn(0).setPreferredWidth(20);
		memberTcm.getColumn(1).setPreferredWidth(30);
		memberTcm.getColumn(2).setPreferredWidth(80);
		memberTcm.getColumn(2).setPreferredWidth(60);
		
		
		TableRowSorter<TableModel>rowSorter = new TableRowSorter<TableModel>(memberDtm);
		memberTable.setRowSorter(rowSorter);
		memberSp = new JScrollPane();
		memberSp.setPreferredSize(new Dimension(450, 200));
		memberSp.setViewportView(memberTable);
		formPnl.add(memberSp, BorderLayout.CENTER);
		
		pointPanel = new JPanel();
		pointPanel.setBackground(new Color(255, 248, 220));
		pointPanel.setBorder(new EmptyBorder(8, 8, 8, 8));
		add(pointPanel, BorderLayout.SOUTH);
		pointPanel.setLayout(new GridLayout(3, 2, 5, 0));
		
		memberIdLbl = new JLabel("Member Id: ");
		pointPanel.add(memberIdLbl);
		
		memberIdAns = new JLabel("-");
		pointPanel.add(memberIdAns);
		
		memberNameLbl = new JLabel("Member Name: ");
		pointPanel.add(memberNameLbl);
		
		memberNameAns = new JLabel("-");
		pointPanel.add(memberNameAns);
		
		currentPointLbl = new JLabel("Current Point:");
		pointPanel.add(currentPointLbl);
		
		currentPointAns = new JLabel("-");
		pointPanel.add(currentPointAns);
		memberTable.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==memberTable){
			int row = memberTable.getSelectedRow();
			
			//udpateLabel
			memberIdAns.setText(memberTable.getValueAt(row, 0).toString());
			memberNameAns.setText(memberTable.getValueAt(row, 1).toString());
			currentPointAns.setText(Integer.toString(pointMember.get(row)));
			
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==searchBtn) {
			ResultSet rs = null ;
			pointMember.clear();
			memberDtm.setRowCount(0);
			String query = "SELECT memberId, memberName, memberPhoneNumber, memberBirthDate, memberPoint FROM member where memberName LIKE '%"+inputTxt.getText()+"%'";
//			query = String.format(query, inputTxt.getText().toLowerCase());
			rs = c.executeQuery(query);
			try {
				while(rs.next()) {
					Vector<Object> data = new Vector<Object>();

					{
						for(int i=1;i<=rs.getMetaData().getColumnCount();i++) {
							data.add(rs.getObject(i));
						};
						memberDtm.addRow(data);
						pointMember.add(rs.getInt("memberPoint"));
					}
				}
			} catch (SQLException e1) {
			}
		}
		
	}

}
