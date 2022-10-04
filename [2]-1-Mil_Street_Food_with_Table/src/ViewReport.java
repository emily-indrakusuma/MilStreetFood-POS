import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;



public class ViewReport extends JInternalFrame implements MouseListener, ActionListener{
	private Operator o;
	private Connect c;
	
	private JPanel mainPanel;
	private JPanel northPanel;
	private JPanel centerPanel;
	private JLabel titleLbl;
	private JPanel salesPanel;
	private DefaultTableModel headerDtm;
	private JTable headerTable;
	private JScrollPane headerSp;
	private JPanel detSalesPanel;
	private DefaultTableModel detDtm;
	private JTable detTable;
	private JScrollPane detSp; 
	private JPanel southPanel;
	private JPanel totIncomePnl;
	private JLabel totIncome;
	private JLabel totIncomeAns;
	private JPanel totalTransPnl;
	private JLabel totTransLbl;
	private JLabel totTransAns;
	private JPanel confBtnPnl;
	private JButton confBtn;
	private JButton approvalBtn;
	
	private int totTrans=0;
	
	public ViewReport(Operator o) {
		this.o = o;
		
		c= new Connect();
		
		setTitle("Report");
		setClosable(true);
		setSize(650, 550);
		ImageIcon imgIcon = new ImageIcon(MainMenu.class.getResource("icon-Mil Street Food.png"));
		setFrameIcon(imgIcon);
		
		init();
	}


	private void init() {
		
		mainPanel = new JPanel();
		add(mainPanel, BorderLayout.CENTER);
		mainPanel.setBorder(new EmptyBorder(8, 8, 8, 8));
		mainPanel.setBackground(new Color(255, 248, 220));
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		northPanel = new JPanel();
		northPanel.setBackground(new Color(255, 248, 220));
		mainPanel.add(northPanel, BorderLayout.NORTH);
		
		titleLbl = new JLabel("View Sales Report");
		titleLbl.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		northPanel.add(titleLbl);
		
		centerPanel = new JPanel();
		centerPanel.setBackground(new Color(255, 248, 220));
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		initHeader();
		
		initDetail();
		
		southPanel = new JPanel();
		southPanel.setBackground(new Color(255, 248, 220));
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new GridLayout(3, 0, 0, 0));
		
		totIncomePnl = new JPanel(new BorderLayout());
		totIncomePnl.setBackground(new Color(255, 248, 220));
		southPanel.add(totIncomePnl);
		
		totIncome = new JLabel("Total Income today: ");
		totIncomePnl.add(totIncome, BorderLayout.WEST);
		
		totIncomeAns = new JLabel("Rp"+Integer.toString(totTrans));
		totIncomePnl.add(totIncomeAns);
		
		
		totalTransPnl = new JPanel(new BorderLayout());
		totalTransPnl.setBackground(new Color(255, 248, 220));
		southPanel.add(totalTransPnl);
		
		totTransLbl = new JLabel("Total Transactions: ");
		totalTransPnl.add(totTransLbl, BorderLayout.WEST);
		
		totTransAns = new JLabel(Integer.toString(headerTable.getRowCount()));
		totalTransPnl.add(totTransAns);
		
		
		
		confBtnPnl = new JPanel(new BorderLayout(20,20));
		confBtnPnl.setBackground(new Color(255, 248, 220));
		southPanel.add(confBtnPnl);
		
		confBtn = new JButton("Send to Supervisor");
		confBtnPnl.add(confBtn, BorderLayout.CENTER);
		confBtn.addActionListener(this);
		
		approvalBtn = new JButton("Supervisor Approval");
		confBtnPnl.add(approvalBtn, BorderLayout.EAST);
		approvalBtn.setEnabled(false);
		approvalBtn.addActionListener(this);

		
	}


	private void initDetail() {
		detSalesPanel = new JPanel(new BorderLayout());
		detSalesPanel.setBackground(new Color(255, 248, 220));
		detSalesPanel.setBorder(new TitledBorder(null, "Detail Transaction", TitledBorder.LEADING, TitledBorder.TOP, new Font("Calibri", Font.BOLD, 14)));
//		centerPanel.add(detSalesPanel, BorderLayout.SOUTH);
		
		Object [] colName2 = {"Food Name", "Quantity", "Total Item Price"};
		
		detDtm = new DefaultTableModel(colName2,0) {
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return Integer.class;
				case 2:
					return Integer.class;
				default:
					return String.class;
				}
			}
		};
		
		detTable = new JTable(detDtm);
		detTable.setBackground(new Color(255, 248, 220));
		detTable.getTableHeader().setResizingAllowed(false);
		TableRowSorter<TableModel>rowSorterDet = new TableRowSorter<TableModel>(detDtm);
		detTable.setRowSorter(rowSorterDet);
		detSp= new JScrollPane();
		detSp.setPreferredSize(new Dimension(525, 150));
		detSp.setViewportView(detTable);
//		detSalesPanel.add(detSp);
		
	}


	private void initHeader() {
		salesPanel = new JPanel(new BorderLayout());
		salesPanel.setBackground(new Color(255, 248, 220));
		salesPanel.setBorder(new TitledBorder(null, "Today Transactions", TitledBorder.LEADING, TitledBorder.TOP, new Font("Calibri", Font.BOLD, 14)));
		centerPanel.add(salesPanel);
		
		Object [] colName = {"Order Id", "Date", "Grand Total"};
		
		headerDtm = new DefaultTableModel(colName,0) {
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return Date.class;
				case 2:
					return Integer.class;
				default:
					return String.class;
				}
			}
		};
		
		headerTable = new JTable(headerDtm);
		headerTable.setBackground(new Color(255, 248, 220));
		headerTable.getTableHeader().setResizingAllowed(false);
		TableRowSorter<TableModel>rowSorter = new TableRowSorter<TableModel>(headerDtm);
		headerTable.setRowSorter(rowSorter);
		JScrollPane reportSp = new JScrollPane();
		reportSp.setPreferredSize(new Dimension(525, 300));
		reportSp.setViewportView(headerTable);
		headerTable.addMouseListener(this);
		
		salesPanel.add(reportSp);
		getHeaderOrder();
		
	}


	private void getHeaderOrder() {
		ResultSet rs;
		java.sql.Date curDate =  new java.sql.Date(new Date().getTime());
		
	
		String query = "SELECT o.orderid, orderDate, SUM(foodprice * quantity) AS \"totTrans\" from headerorder o JOIN detailorder d ON d.orderId=o.orderId JOIN food f ON F.foodId= d.foodId\r\n" + 
				"WHERE userId = '%s' AND orderDate = '%s'\r\n" + 
				"GROUP by o.orderId";
		query = String.format(query,o.getId(), curDate);
		rs = c.executeQuery(query);
		try {
			while(rs.next()) {

				Vector<Object> data = new Vector<Object>();
				{
					for(int i=1;i<=rs.getMetaData().getColumnCount();i++) {
						data.add(rs.getObject(i));
					};
					headerDtm.addRow(data);
					totTrans+=rs.getInt("totTrans");
				}
			}
		} catch (SQLException e) {
		}

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==approvalBtn) {
			JPanel panelDialog = new JPanel(new GridLayout(2, 1));
			JLabel label = new JLabel("Input supervisor password to approve");
			panelDialog.add(label);
			JPasswordField passwordSupervisor = new JPasswordField();
			panelDialog.add(passwordSupervisor);
			String [] options = {"Approve","Cancel"};
			int opt= JOptionPane.showOptionDialog(this, panelDialog, "Approval Report", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			if(opt == JOptionPane.OK_OPTION) {
				String getPassword = new String(passwordSupervisor.getPassword());
				boolean x = passwordIsCorrect(getPassword);
				if(x) {
					//approved
					o.setCheckedSupervisor(true);
					approvalBtn.setEnabled(false);
				}
			}
		}
		
		if(e.getSource()==confBtn) {
			JOptionPane.showMessageDialog(this, "Sending to supervisor, waiting for supervisor come to your POS", "Sent", JOptionPane.INFORMATION_MESSAGE);
			approvalBtn.setEnabled(true);
			confBtn.setEnabled(false);
		}
		
	}


	private boolean passwordIsCorrect(String getPassword) {
		ResultSet rs ;
		String query = "SELECT userFullName, userpassword from user u JOIN supervisor s ON u.id = s.id WHERE userpassword = '%s'";
		query = String.format(query, getPassword);
		rs = c.executeQuery(query);
		try {
			if(rs.next()) {
				JOptionPane.showMessageDialog(this, "Approved by: "+rs.getString("userFullName"),"Approved",JOptionPane.INFORMATION_MESSAGE);
				return true;
			}
			
			else {
				JOptionPane.showMessageDialog(this, "password not match");
			}
		} catch (SQLException e) {
		}
		return false;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==headerTable) {
			detDtm.setRowCount(0);
			centerPanel.add(detSalesPanel, BorderLayout.SOUTH);
			detSalesPanel.add(detSp);
			invalidate();
			validate();
			repaint();
			int row = headerTable.getSelectedRow();
			getDetailData(headerTable.getValueAt(row, 0).toString());
			
			
		}
	}


	private void getDetailData(String orderId) {
		ResultSet rs; 
		
		String query = "SELECT f.foodName, quantity, SUM(f.foodPrice * quantity) AS 'totItemPrice' \r\n" + 
				"FROM detailorder d JOIN food f ON d.foodId = f.foodId\r\n" + 
				"WHERE orderId = '%s' \r\n" + 
				"GROUP BY f.foodId";
		
		query = String.format(query, orderId);
		
		rs = c.executeQuery(query);
		try {
			while(rs.next()) {
				Vector<Object> data = new Vector<Object>();
				{
					for(int i=1;i<=rs.getMetaData().getColumnCount();i++) {
						data.add(rs.getObject(i));
					};
					detDtm.addRow(data);
				}
			}
		} catch (SQLException e) {
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

}
