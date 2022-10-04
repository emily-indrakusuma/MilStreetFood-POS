import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

public class SalesEntryInternalFrame extends JInternalFrame implements MouseListener, ActionListener, KeyListener {

	JPanel allPanel,  titlePanel, mainPanel, leftPanel, rightPanel;
	JLabel titleLbl;
	JPanel memberIDPnl, fieldID, memberTypePnl;
	JRadioButton memberBg, nonMemberBg;
	JLabel id, priceAnsLbl;
	DefaultTableModel transactionDtm, foodDtm;
	SpinnerNumberModel spinnerModel;
	JSpinner spinner;
	private int price=0;
	JPanel paymentBtnPnl;
	JButton paymentBtn;
	JPanel askBtnPnl;
	JTextField idTxt;
	JPanel datePnl, dateNamePnl;
	JDateChooser dateChooser;
	JLabel dateLbl;
	JPanel memberNamePnl;
	JLabel memberNameLbl;
	JLabel nameAnsLbl;
	JScrollPane jsp;
	JTable transactionTable, foodTbl;
	JButton callSupervisorBtn;

	private Operator o;

	private ArrayList<String> idArr;
	private ArrayList<String> selectedIdArr;
	//	 private ArrayList<Byte> photoArr;

	private Food f;
	Connect c;
	JDesktopPane desktopPane;

	public SalesEntryInternalFrame(Operator o, JDesktopPane desktopPane, Food f) {
		this.o = o;
		this.desktopPane=desktopPane;
		c = new Connect();
		this.f=f;
		idArr = new ArrayList<>();
		selectedIdArr = new ArrayList<>();
		//		photoArr = new ArrayList<>();

		spinnerModel = new SpinnerNumberModel(1,1,1,1);
		spinner = new JSpinner(spinnerModel);

		setTitle("Sales Entry Record");
		setClosable(false);
		setResizable(false);
		setSize(1000,500);
		ImageIcon imgIcon = new ImageIcon(MainMenu.class.getResource("icon-Mil Street Food.png"));
		setFrameIcon(imgIcon);

		allPanel = new JPanel();
		add(allPanel, BorderLayout.CENTER);
		allPanel.setLayout(new BorderLayout(0, 0));

		titlePanel = new JPanel();
		titlePanel.setBackground(new Color(255, 248, 220));
		allPanel.add(titlePanel, BorderLayout.NORTH);

		titleLbl = new JLabel("Mil Street Food POS");
		titleLbl.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 24));
		titleLbl.setIcon(new ImageIcon(MainMenu.class.getResource("logo-Mil Street Food.png")));
		titlePanel.add(titleLbl);

		mainPanel = new JPanel();
		allPanel.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new GridLayout(0, 2, 0, 0));

		leftPanel = new JPanel();
		leftPanel.setBackground(new Color(255, 248, 220));
		leftPanel.setBorder(new TitledBorder(null, "Current Order", TitledBorder.LEADING, TitledBorder.TOP, new Font("Calibri", Font.BOLD, 18), null));
		mainPanel.add(leftPanel);
		leftPanel.setLayout(new GridLayout(3, 2, 5, 5));

		rightPanel = new JPanel(new BorderLayout());
		rightPanel.setBackground(new Color(255, 248, 220));
		rightPanel.setBorder(null);
		rightPanel.setBorder(new TitledBorder(null, "Food Menu", TitledBorder.LEADING, TitledBorder.TOP, new Font("Calibri", Font.BOLD, 18), null));



		initLeft();
		initRight();

		mainPanel.add(rightPanel);

	}



	private void initLeft() {
		JPanel identityPanel = new JPanel();
		identityPanel.setBackground(new Color(255, 248, 220));
		leftPanel.add(identityPanel, BorderLayout.NORTH);
		identityPanel.setLayout(new GridLayout(3, 0, 0, 0));

		memberTypePnl = new JPanel();
		memberTypePnl.setBackground(new Color(255, 248, 220));
		identityPanel.add(memberTypePnl);
		memberTypePnl.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel rdbMemberTypePnl = new JPanel();
		rdbMemberTypePnl.setBackground(new Color(255, 248, 220));
		memberTypePnl.add(rdbMemberTypePnl);
		rdbMemberTypePnl.setLayout(new BorderLayout(0, 0));

		JLabel memberTypeLbl = new JLabel("Type: ");
		memberTypeLbl.setHorizontalAlignment(SwingConstants.LEFT);
		rdbMemberTypePnl.add(memberTypeLbl, BorderLayout.WEST);

		JPanel radioBtnPnl = new JPanel();
		radioBtnPnl.setBackground(new Color(255, 248, 220));
		rdbMemberTypePnl.add(radioBtnPnl, BorderLayout.CENTER);
		radioBtnPnl.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		memberBg = new JRadioButton("Member");
		memberBg.setBackground(new Color(255, 248, 220));
		memberBg.setHorizontalAlignment(SwingConstants.LEFT);
		memberBg.setSelected(true);
		memberBg.addActionListener(this);
		radioBtnPnl.add(memberBg);

		nonMemberBg = new JRadioButton("Non-Member");
		nonMemberBg.setBackground(new Color(255, 248, 220));
		radioBtnPnl.add(nonMemberBg);
		nonMemberBg.addActionListener(this);

		ButtonGroup memberType = new ButtonGroup();
		memberType = new ButtonGroup();
		memberType.add(memberBg);
		memberType.add(nonMemberBg);

		setMember();

		dateNamePnl = new JPanel();
		dateNamePnl.setBackground(new Color(255, 248, 220));
		identityPanel.add(dateNamePnl);
		dateNamePnl.setLayout(new GridLayout(0, 2, 0, 0));

		datePnl = new JPanel();
		datePnl.setBackground(new Color(255, 248, 220));
		dateNamePnl.add(datePnl);
		datePnl.setLayout(new BorderLayout(0, 0));

		dateChooser = new JDateChooser();
		datePnl.add(dateChooser, BorderLayout.CENTER);

		dateLbl = new JLabel("Date:");
		datePnl.add(dateLbl, BorderLayout.WEST);
		Date date = new Date();
		dateChooser.setDate(date);

		memberNamePnl = new JPanel();
		memberNamePnl.setBackground(new Color(255, 248, 220));
		dateNamePnl.add(memberNamePnl);
		memberNamePnl.setLayout(new BorderLayout(10, 0));

		memberNameLbl = new JLabel("");
		memberNamePnl.add(memberNameLbl, BorderLayout.WEST);

		nameAnsLbl = new JLabel("");
		memberNamePnl.add(nameAnsLbl, BorderLayout.CENTER);

		memberNamePnl = new JPanel();
		memberNamePnl.setBackground(new Color(255, 248, 220));
		dateNamePnl.add(memberNamePnl);
		memberNamePnl.setLayout(new BorderLayout(10, 0));

		JPanel totalPricePnl = new JPanel();
		totalPricePnl.setBackground(new Color(255, 248, 220));
		identityPanel.add(totalPricePnl);
		totalPricePnl.setLayout(new BorderLayout(0, 0));

		JPanel totPriceLblPnl = new JPanel(new FlowLayout());
		totPriceLblPnl.setBackground(new Color(255, 248, 220));
		totalPricePnl.add(totPriceLblPnl, BorderLayout.WEST);

		JLabel totPriceLbl = new JLabel("Total Price: ");
		totPriceLbl.setHorizontalAlignment(SwingConstants.CENTER);
		totPriceLbl.setFont(new Font("Calibri", Font.BOLD, 16));
		totPriceLblPnl.add(totPriceLbl);

		priceAnsLbl = new JLabel("Rp"+price);
		priceAnsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		priceAnsLbl.setFont(new Font("Calibri", Font.BOLD, 16));
		totPriceLblPnl.add(priceAnsLbl);

		paymentBtnPnl = new JPanel();
		paymentBtnPnl.setBackground(new Color(255, 248, 220));
		totalPricePnl.add(paymentBtnPnl, BorderLayout.EAST);

		paymentBtn = new JButton("Payment");
		paymentBtnPnl.add(paymentBtn);
		paymentBtn.addActionListener(this);

		Object [] colName = {"Menu", "Price", "Qty", "Total Price"};

		transactionDtm = new DefaultTableModel(colName,0) {
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return Integer.class;
				case 2:
					return Integer.class;
				case 3:
					return Integer.class;
				default:
					return String.class;
				}
			}
		};

		transactionTable = new JTable(transactionDtm);
		transactionTable.setBackground(new Color(255, 248, 220));
		transactionTable.setEnabled(false);
		//agar tidak bisa diresize
		transactionTable.getTableHeader().setResizingAllowed(false);
		TableRowSorter<TableModel>rowSorter = new TableRowSorter<TableModel>(transactionDtm);
		transactionTable.setRowSorter(rowSorter);
		transactionTable.addMouseListener(this);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(transactionTable);
		leftPanel.add(scrollPane, BorderLayout.CENTER);

		askBtnPnl = new JPanel();
		askBtnPnl.setBackground(new Color(255, 248, 220));
		leftPanel.add(askBtnPnl, BorderLayout.SOUTH);

		callSupervisorBtn = new JButton("Call Supervisor");
		askBtnPnl.add(callSupervisorBtn);
		callSupervisorBtn.addActionListener(this);


	}


	private void initRight() {

		Object [] tableName = {
				"Name","Price","Stock","Image"
		};

		foodDtm = new DefaultTableModel(tableName,0) {
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return Integer.class;
				case 2:
					return Integer.class;
				case 3:
					return ImageIcon.class;
				default:
					return String.class;
				}
			}
		};
		
		
		foodTbl = new JTable(foodDtm);
		foodTbl.setBackground(new Color(255, 248, 220));
		foodTbl.setRowHeight(100);
		foodTbl.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,14));
		foodTbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
		foodTbl.addMouseListener(this);
		
		TableColumnModel tcm = foodTbl.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(200);
		tcm.getColumn(1).setPreferredWidth(25);
		tcm.getColumn(2).setPreferredWidth(25);
		tcm.getColumn(3).setPreferredWidth(40);
		
		initTblData();

		jsp = new JScrollPane();
		jsp.setBackground(new Color(255, 248, 220));
		jsp.setViewportView(foodTbl);

		rightPanel.add(jsp, BorderLayout.CENTER);
	}

	public void initTblData() {
		idArr.clear();
		for(int i = 0; i<f.getId().size();i++) {
			Vector<Object>obj = new Vector<>();
			if(f.getStock().get(i)==0) {
			}
			else {
				idArr.add(f.getId().get(i));
				obj.add(f.getName().get(i));
				obj.add(f.getPrice().get(i));
				obj.add(f.getStock().get(i));
				Image img = Toolkit.getDefaultToolkit().createImage(f.getImg().get(i));
				img =  img.getScaledInstance(80, foodTbl.getRowHeight(i), java.awt.Image.SCALE_SMOOTH);
				ImageIcon icon = new ImageIcon(img);
				
				obj.add(icon);
				foodDtm.addRow(obj);
			}
			
		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getSource()==transactionTable) {
			int row = transactionTable.getSelectedRow();
			if(transactionTable.isEnabled())
				updatDeleteSupervisor(row);
			transactionTable.clearSelection();
			transactionTable.setEnabled(false);
		}

		if(arg0.getSource()==foodTbl) {
			int row=foodTbl.getSelectedRow();
			int idxFood = Integer.parseInt(idArr.get(row))-1;
			int currStock = f.getStock().get(idxFood);
			int opt = dialogBox(currStock);
			int transRow = getTransRow(row);
			int newQty = Integer.parseInt(spinner.getValue().toString());
			if(opt==JOptionPane.OK_OPTION) {
				if(transRow==-1) {
					Vector<Object> selectData = new Vector<>();
					selectData.add(foodTbl.getValueAt(row, 0));
					selectData.add(foodTbl.getValueAt(row, 1));
					selectData.add(newQty);
					selectData.add(Integer.parseInt(foodTbl.getValueAt(row, 1).toString())*Integer.parseInt(spinner.getValue().toString()));
					transactionDtm.addRow(selectData);
					selectedIdArr.add(idArr.get(row));
				}
				else {
					int currQty = Integer.parseInt(transactionTable.getValueAt(transRow, 2).toString());
					int currPrice = (currQty+newQty)* Integer.parseInt(transactionTable.getValueAt(transRow, 1).toString());
					transactionTable.setValueAt(currQty+newQty, transRow, 2);
					transactionTable.setValueAt(currPrice,transRow,3);
				}
				int newStock = currStock-newQty; 
				f.getStock().set(idxFood, newStock);
				foodDtm.setRowCount(0);
				initTblData();
			}

		}
		resetSpinner();
		calculatePrice();
	}


	private int getTransRow(int row) {
		for(int i=0;i<selectedIdArr.size();i++) {
			if(selectedIdArr.get(i).equals(idArr.get(row))) {
				return i;
			}
		}
		return -1;
	}



	private void updatDeleteSupervisor(int row) {
		int idxFood = (Integer.parseInt(selectedIdArr.get(row))-1);
		String [] options = {"Update","Delete","Cancel"};
		int stock=0;
		int currQty = Integer.parseInt(transactionTable.getValueAt(row, 2).toString());
		int opt=JOptionPane.showOptionDialog(this, "Update/Delete "+f.getName().get(idxFood),"Confirmation",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,options, options[0]);
		if(opt==JOptionPane.YES_OPTION) {
			//Update
			spinner.setValue(currQty);
			stock=(f.getStock().get(idxFood)+currQty);
			int x =dialogBox(stock);
			if(x==JOptionPane.YES_OPTION) {
				int newStock = Integer.parseInt(spinner.getValue().toString());
				int addStock = currQty-newStock;
				f.getStock().set(idxFood, (f.getStock().get(idxFood)+addStock));
				transactionTable.setValueAt(newStock, row, 2);
				transactionTable.setValueAt(newStock*f.getPrice().get(idxFood),row, 3);
			}

			resetSpinner();
		}
		
		if(opt==JOptionPane.NO_OPTION) {
			//delete
			int ans=JOptionPane.showConfirmDialog(this, "Delete "+f.getName().get(idxFood));
			if(ans==JOptionPane.YES_OPTION) {
				f.getStock().set(idxFood, f.getStock().get(idxFood)+currQty);
				transactionDtm.removeRow(row);
				selectedIdArr.remove(row);
			}
		}
		foodDtm.setRowCount(0);
		initTblData();
		calculatePrice();
	}


	private void calculatePrice() {
		int a=0;
		for(int i=0;i<transactionTable.getRowCount();i++) {
			a+=Integer.parseInt((transactionTable.getValueAt(i, 3).toString()));
		}
		priceAnsLbl.setText(Integer.toString(a));
	}

	private void resetSpinner() {
		spinner.setValue(1);
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==paymentBtn) {
			if(transactionTable.getRowCount()==0) {
				JOptionPane.showMessageDialog(this, "Please add item(s) first");
				return;
			}

			if(memberBg.isSelected()&&nameAnsLbl.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please input member ID or choose non-member option");
				return;
			}

			else {
				java.sql.Date date = new java.sql.Date(dateChooser.getDate().getTime());
				TableModel obj = transactionTable.getModel();
				Payment payment = new Payment(o, idTxt.getText(), nameAnsLbl.getText().toString(),obj, date, this);
				desktopPane.add(payment).setVisible(true);

			}
		}

		if(arg0.getSource()==nonMemberBg) {
			if(nonMemberBg.isSelected())
				memberTypePnl.remove(memberIDPnl);
			resetNamePnl();
			refresh();
		}
		else if(arg0.getSource()==memberBg) {
			if(memberBg.isSelected()) {
				setMember();
			}

			refresh();
		}


		if(arg0.getSource()==callSupervisorBtn) {
			JPanel dialogPanel = new JPanel(new GridLayout(2, 1));
			JLabel title= new JLabel("Supervisor Password: ");
			dialogPanel.add(title);
			JPasswordField passwordSupervisor = new JPasswordField();
			dialogPanel.add(passwordSupervisor);
			int opt= JOptionPane.showOptionDialog(this, dialogPanel, "Supervisor Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
			if (opt==JOptionPane.OK_OPTION) {
				String pwInput = new String(passwordSupervisor.getPassword());
				ResultSet rs;
				String query = "SELECT userFullName, userPassword FROM user u JOIN supervisor s ON u.id = s.id where userPassword = '%s'";
				query = String.format(query, pwInput);
				rs = c.executeQuery(query);
				try {
					if (!rs.next())
					{
						JOptionPane.showMessageDialog(this, "Can't Find Password");
						return;
					}
					else {
						String name= rs.getString("userFullName");
						transactionTable.setEnabled(true);
						JOptionPane.showMessageDialog(null, "Hi, "+name);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}


	}


	public void setMember() {

		memberIDPnl = new JPanel();
		memberIDPnl.setBackground(new Color(255, 248, 220));
		memberTypePnl.add(memberIDPnl);
		memberIDPnl.setLayout(new BorderLayout(0, 0));

		id = new JLabel("memberID:");
		id.setBackground(new Color(255, 248, 220));
		memberIDPnl.add(id, BorderLayout.WEST);

		fieldID = new JPanel();
		fieldID.setBackground(new Color(255, 248, 220));
		memberIDPnl.add(fieldID, BorderLayout.CENTER);

		idTxt = new JTextField(15);
		idTxt.setHorizontalAlignment(SwingConstants.CENTER);
		fieldID.add(idTxt);
		idTxt.addKeyListener(this);

	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getSource()==idTxt) {
			if(arg0.getKeyCode()==KeyEvent.VK_ENTER) {
				ResultSet rs = null ;
				String query = "SELECT memberId, memberName FROM Member WHERE memberID = '%s'";
				query = String.format(query, idTxt.getText().toUpperCase());
				rs= c.executeQuery(query);
				resetNamePnl();
				try {
					if(!rs.next()) {
						JOptionPane.showMessageDialog(this, "No member found, please input valid member or use Non-Member type");
						idTxt.setText("");
						return;
					}
					else {
						memberNameLbl.setText("Member Name:");

						nameAnsLbl.setText(rs.getString("memberName"));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void resetNamePnl() {
		memberNameLbl.setText("");
		nameAnsLbl.setText("");

	}

	private int dialogBox (int max) {
		spinnerModel.setMaximum(max);
		return JOptionPane.showOptionDialog(this, spinner, "Input Quantity", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, 1);
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	void refresh() {
		invalidate();
		validate();
		repaint();

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}



	public ArrayList<String> getSelectedIdArr() {
		return selectedIdArr;
	}



	public void setSelectedIdArr(ArrayList<String> selectedIdArr) {
		this.selectedIdArr = selectedIdArr;
	}



	public void deleteSelectedIdData() {
		selectedIdArr.clear();
	}
	
	


}
