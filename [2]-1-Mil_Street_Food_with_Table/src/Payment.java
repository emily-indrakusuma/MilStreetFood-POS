import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Payment extends JInternalFrame implements ItemListener,KeyListener, ActionListener {
	
	private String memberName;
	private String memberId;
	
	private java.sql.Date date;
	
	private int tot;
	
	private Operator o;
	
	private JPanel headerPnl;
	private JPanel IdentityPanel;
	private JPanel memberDescPnl;
	private JLabel memberNonLbl;
	private JLabel nameMemberLbl;
	private JPanel paymentPnl;
	private JPanel methodPnl;
	private JPanel dateTransPnl;
	private JLabel dateTransLbl;
	private JLabel dateAnsLbl;
	private JPanel inputPnl;
	private JLabel paymentMethodLbl;
	private JComboBox<Object> comboBox;
	private JPanel inputSomethPnl;
	private JPanel totChangePnl;
	private JLabel inputLbl;
	private JTextField inputField;
	private JLabel totChangeLbl;
	private JLabel changeLbl;
	private JPanel summaryPnl;
	private JScrollPane summarySp;
	private JTable summaryTbl;
	private JPanel totalConfirmPnl;
	private JPanel totalTransactionPnl;
	private JLabel totTransLbl;
	private JLabel totTransAns;
	private JPanel confirmBtnPnl;
	private JButton confirmBtn;
	TableModel tm;
	
	
	
	Connect c ;
	
	SalesEntryInternalFrame sif;
	
	public Payment(Operator o, String memberId, String memberName, TableModel tm, java.sql.Date date, SalesEntryInternalFrame sif ) {
		this.memberId=memberId;
		this.memberName = memberName;
		this.tm=tm;
		this.date=date;
		this.o=o;
		this.sif=sif;
		
		c= new Connect();
		
		setTitle("Payment");
		
		init();
		ImageIcon imgIcon = new ImageIcon(MainMenu.class.getResource("icon-Mil Street Food.png"));
		setFrameIcon(imgIcon);

		setBackground(new Color(255, 248, 220));
		setResizable(true);
		setClosable(true);
		setSize(new Dimension(515, 500));
		setLocation(50, 50);
		
	}

	private void init() {
		JPanel paymentTitlePnl = new JPanel();
		paymentTitlePnl.setBackground(new Color(255, 248, 220));
		add(paymentTitlePnl, BorderLayout.NORTH);
		
		JLabel label = new JLabel("Payment");
		label.setFont(new Font("Calibri", Font.BOLD, 18));
		paymentTitlePnl.add(label);
		
		headerPnl = new JPanel();
		headerPnl.setBackground(new Color(255, 248, 220));
		add(headerPnl, BorderLayout.CENTER);
		headerPnl.setLayout(new BorderLayout(0, 0));
		
		IdentityPanel = new JPanel();
		headerPnl.add(IdentityPanel, BorderLayout.NORTH);
		IdentityPanel.setLayout(new GridLayout(3, 1, 0, 0));
		
		memberDescPnl = new JPanel();
		memberDescPnl.setBackground(new Color(255, 248, 220));
		IdentityPanel.add(memberDescPnl);
		memberDescPnl.setLayout(new BorderLayout(20, 10));
		
		memberNonLbl = new JLabel("");
		memberNonLbl.setFont(new Font("Calibri", Font.PLAIN, 15));
		memberDescPnl.add(memberNonLbl, BorderLayout.WEST);
		
		nameMemberLbl = new JLabel(memberName);
		memberDescPnl.add(nameMemberLbl, BorderLayout.CENTER);
		
		dateTransPnl = new JPanel();
		dateTransPnl.setBackground(new Color(255, 248, 220));
		IdentityPanel.add(dateTransPnl);
		dateTransPnl.setLayout(new BorderLayout(10, 0));
		
		dateTransLbl = new JLabel("Transaction Date: ");
		dateTransPnl.add(dateTransLbl, BorderLayout.WEST);
		
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
		String dateFormat = sdf.format(date);
		dateAnsLbl = new JLabel(dateFormat);
		dateTransPnl.add(dateAnsLbl, BorderLayout.CENTER);
		
		paymentPnl = new JPanel();
		paymentPnl.setBackground(new Color(255, 248, 220));
		IdentityPanel.add(paymentPnl);
		paymentPnl.setLayout(new GridLayout(2, 0, 0, 0));
		
		methodPnl = new JPanel();
		methodPnl.setBackground(new Color(255, 248, 220));
		paymentPnl.add(methodPnl);
		methodPnl.setLayout(new BorderLayout(0, 0));
		
		paymentMethodLbl = new JLabel("Payment Method: ");
		methodPnl.add(paymentMethodLbl, BorderLayout.WEST);
		
		String [] option= new String[] {"--select one--", "Ewallet", "Debit Card", "Cash"};
		
		comboBox = new JComboBox<Object>(option);
		comboBox.setBackground(new Color(255, 248, 220));
		comboBox.addItemListener(this);
		methodPnl.add(comboBox);
		
		inputPnl = new JPanel();
		inputPnl.setBackground(new Color(255, 248, 220));
		paymentPnl.add(inputPnl);
		inputPnl.setLayout(new GridLayout(0, 2, 0, 0));
		
		inputSomethPnl = new JPanel();
		inputSomethPnl.setBackground(new Color(255, 248, 220));
		inputPnl.add(inputSomethPnl);
		inputSomethPnl.setLayout(new BorderLayout(0, 0));
		
		inputLbl = new JLabel("");
		inputSomethPnl.add(inputLbl, BorderLayout.WEST);
		
		inputField = new JTextField();
		inputField.setEnabled(false);
		inputField.addKeyListener(this);
		inputSomethPnl.add(inputField, BorderLayout.CENTER);
		inputField.setColumns(10);
		
		totChangePnl = new JPanel();
		totChangePnl.setBackground(new Color(255, 248, 220));
		inputPnl.add(totChangePnl);
		totChangePnl.setLayout(new BorderLayout(0, 0));
		
		totChangeLbl = new JLabel("Total Change: ");
		totChangePnl.add(totChangeLbl, BorderLayout.WEST);
		
		changeLbl = new JLabel("Rp-");
		totChangePnl.add(changeLbl, BorderLayout.CENTER);
		
		summaryPnl = new JPanel();
		summaryPnl.setBorder(new TitledBorder(null, "Summary of Order", TitledBorder.LEADING, TitledBorder.TOP));
		summaryPnl.setBackground(new Color(255, 248, 220));
		headerPnl.add(summaryPnl, BorderLayout.CENTER);
		
		
		summaryPnl.setLayout(new GridLayout(0, 1, 0, 0));
		
		summaryTbl = new JTable(tm);
		
		
		summarySp = new JScrollPane(summaryTbl);
		summaryPnl.add(summarySp);
		
//		addDataTbl(); 
		
		totalConfirmPnl = new JPanel();
		headerPnl.add(totalConfirmPnl, BorderLayout.SOUTH);
		totalConfirmPnl.setLayout(new GridLayout(0, 2, 0, 0));
		
		totalTransactionPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
		totalTransactionPnl.setBackground(new Color(255, 248, 220));
		totalConfirmPnl.add(totalTransactionPnl);
		
		totTransLbl = new JLabel("Total Transaction: ");
		totalTransactionPnl.add(totTransLbl);
		
		tot= countTotal();
		totTransAns = new JLabel(Integer.toString(tot));
		totalTransactionPnl.add(totTransAns);
		
		confirmBtnPnl = new JPanel();
		confirmBtnPnl.setBackground(new Color(255, 248, 220));
		totalConfirmPnl.add(confirmBtnPnl);
		
		confirmBtn = new JButton("   Confirm   ");
		confirmBtnPnl.add(confirmBtn);
		confirmBtn.setEnabled(false);
		confirmBtn.addActionListener(this);
		
		
		checkMember();
		
		
		
	}

	private int countTotal() {
		int y=0;
		for(int i=0;i<summaryTbl.getRowCount();i++) {
			int x = Integer.parseInt(summaryTbl.getValueAt(i, 3).toString());
			y+=x;
		}
		return y;
	}

	private void checkMember() {
		if(memberName.isEmpty()) {
			memberNonLbl.setText("Non-Member");
		}
		else memberNonLbl.setText("Member");
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==comboBox) {
			int x=comboBox.getSelectedIndex();
			inputField.setText("");
			confirmBtn.setEnabled(false);
			switch (x) {
			case 1:
				inputField.setEnabled(true);
				changeLbl.setText("Rp-");
				inputLbl.setText("Input Ph. Number: ");
				return;
				
			case 2:
				inputField.setEnabled(true);
				changeLbl.setText("Rp-");
				inputLbl.setText("Input Card Number: ");
				return;
				
			case 3: 
				inputField.setEnabled(true);
				changeLbl.setText("Rp-");
				inputLbl.setText("Input nominal: ");
				return;

			default:
				changeLbl.setText("Rp-");
				inputField.setEnabled(false);
				break;
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==confirmBtn) {
			
			updateDatabase();
		}
		
	}

	private void updateDatabase() {
		ResultSet rs=null;
		String orderId = null;
		boolean idIsUsed=false;;
		
		//checkId
		String checkId = "SELECT orderId from headerorder WHERE orderId = '%s' ";
		
		do {
			idIsUsed=false;
			orderId = "OR";
			for(int i =0; i<3;i++) {
				orderId += Integer.toString((int)(Math.random()*10));
//				System.out.println(orderId);
			};
			checkId = String.format(checkId, orderId);
			rs = c.executeQuery(checkId);
			try {
				while(rs.next()) {
					idIsUsed=true;
				}
			} catch (SQLException e) {
			}
		} while (idIsUsed);
		
		String queryHeader = null;
		if(memberId.isEmpty()) {
			queryHeader = "INSERT INTO headerorder (orderId, userId, orderDate) VALUES ('%s', '%s', '%s') ";
			
			queryHeader= String.format(queryHeader, orderId, o.getId(), date) ;
			
			JOptionPane.showMessageDialog(this, "Order Placed!", "Order Placed", JOptionPane.INFORMATION_MESSAGE);
			
		}
		else {
			queryHeader = "INSERT INTO headerorder (orderId, userId, memberId, orderDate) VALUES ('%s', %s, '%s', '%s') ";
		
			queryHeader= String.format(queryHeader, orderId, o.getId(), memberId, date);
			
			//update member point
			int plusPoint = (tot/10000);
			String updatePoint = "UPDATE member SET memberPoint=memberPoint+%s  WHERE memberid='%s'";
			updatePoint= String.format(updatePoint, plusPoint, memberId);
			
			c.executeUpdate(updatePoint);
			int memberCurrPoint = 0;
			String getMemberPoint = "SELECT memberPoint FROM member where memberId = '%s'";
			getMemberPoint = String.format(getMemberPoint, memberId);
			rs = c.executeQuery(getMemberPoint);
			try {
				rs.next();
				memberCurrPoint= rs.getInt("memberPoint");
			} catch (Exception e) {
				
			}
			
			
			
			JOptionPane.showMessageDialog(this, "Order Placed! Now, "+memberName+" has "+memberCurrPoint+" points", "Order Placed", JOptionPane.INFORMATION_MESSAGE);
			
			
		}
		
		c.executeUpdate(queryHeader);
		
		
		
		
		//update Qty
		for(int i = 0; i< summaryTbl.getRowCount();i++) {
			String queryDetail = "INSERT INTO detailorder (orderId, foodId, quantity) VALUES ('%s',%s,%s)";
			String queryUpdateStock = "UPDATE Food SET foodstock = foodstock-%s WHERE foodId = '%s'";
			String foodId =null;
			 String qty = null;
			
			
			//catch Id
			 foodId=sif.getSelectedIdArr().get(i);
			 
			//catch qty
			qty = summaryTbl.getValueAt(i, 2).toString();
			
			//format detail
			queryDetail= String.format(queryDetail, orderId, foodId, qty);
			
			//format update stok
			queryUpdateStock=String.format(queryUpdateStock, qty, foodId);
			
			//execute
			c.executeUpdate(queryDetail);
			c.executeUpdate(queryUpdateStock);
			
			
		}
		
		sif.transactionDtm.setRowCount(0);
		sif.priceAnsLbl.setText("");
		if(sif.nonMemberBg.isSelected()) {
			sif.memberBg.setSelected(true);
			sif.setMember();
		}
		else {
			sif.idTxt.setText("");
			sif.resetNamePnl();
		}
			
		sif.jsp.getVerticalScrollBar().setValue(0);
		sif.refresh();
		sif.deleteSelectedIdData();
		
		this.dispose();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getSource()==inputField&&comboBox.getSelectedIndex()==3) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				int priceInput = Integer.parseInt(inputField.getText());
				if(tot>priceInput) {
					JOptionPane.showMessageDialog(this, "Cash must be more than total");
				}
				else {
					confirmBtn.setEnabled(true);
					changeLbl.setText("Rp"+(priceInput-tot));
				}
			} else {
				confirmBtn.setEnabled(false);
				changeLbl.setText("Rp");
			}
			
		}
		
		if(e.getSource()==inputField && e.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
			if(comboBox.getSelectedIndex()==1&&inputField.getText().length()-1<10) {
//				System.out.println("check");
				confirmBtn.setEnabled(false);
				return;
			}
			if(comboBox.getSelectedIndex()==2&&inputField.getText().length()-1!=16) {
				confirmBtn.setEnabled(false);
				return;
			}
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		String x = Character.toString(e.getKeyChar());
		
		if(e.getKeyChar()==8) {
			return;
		}
		
		if("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()_-+={}\\|;:\"\'>.<,?/~`".contains(x)) {
			JOptionPane.showMessageDialog(this, "Must be numeric");
			getToolkit().beep();
			e.consume();
			return;
		}
		if(comboBox.getSelectedIndex()==1&&(inputField.getText().length()+1>=10&&inputField.getText().length()<=15)) {
			confirmBtn.setEnabled(true);
			return;
		}
		
		if(comboBox.getSelectedIndex()==2&&inputField.getText().length()+1==16) {
			confirmBtn.setEnabled(true);
			return;
		}

		
	}

//	private void addDataTbl() {
//		summaryDtm.addRow(rowData);
//		
//	}

}
