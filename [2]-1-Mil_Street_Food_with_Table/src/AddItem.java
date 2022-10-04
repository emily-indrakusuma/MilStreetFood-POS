import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Blob;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Font;

public class AddItem extends JInternalFrame implements KeyListener{
	private JTextField textField;
	private JButton btn;
	Connect c;
	private JTextField fieldName;
	private JTextField priceField;
	private JTextField stockField;
	private Blob blob;
	private Food f;
	private SalesEntryInternalFrame sif;
	public AddItem(Food f, SalesEntryInternalFrame sif) {
		setClosable(true);
		setTitle("Add Menu");
		setSize(500, 300);
		setLocation(650, 0);
		ImageIcon imgIcon = new ImageIcon(MainMenu.class.getResource("icon-Mil Street Food.png"));
		setFrameIcon(imgIcon);
		this.f=f;
		this.sif = sif;
		c= new Connect();
		
		JPanel mainpanel = new JPanel(new BorderLayout());
		add(mainpanel);
		
		JPanel panel = new JPanel();
		mainpanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel titlePnl = new JPanel();
		titlePnl.setBackground(new Color(255, 248, 220));
		panel.add(titlePnl, BorderLayout.NORTH);
		
		JLabel newMenu = new JLabel("Add New Menu");
		newMenu.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		titlePnl.add(newMenu);
		
		JPanel formPnl = new JPanel();
		formPnl.setBackground(new Color(255, 248, 220));
		panel.add(formPnl, BorderLayout.CENTER);
		formPnl.setLayout(new GridLayout(4, 0, 20, 10));
		
		JPanel namePnl = new JPanel();
		namePnl.setBackground(new Color(255, 248, 220));
		formPnl.add(namePnl);
		namePnl.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel menuName = new JLabel("Menu Name: ");
		namePnl.add(menuName);
		
		fieldName = new JTextField();
		namePnl.add(fieldName);
		fieldName.setColumns(10);
		
		JPanel pricePnl = new JPanel();
		pricePnl.setBackground(new Color(255, 248, 220));
		formPnl.add(pricePnl);
		pricePnl.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel menuPrice = new JLabel("Menu Price: ");
		pricePnl.add(menuPrice);
		
		priceField = new JTextField();
		pricePnl.add(priceField);
		priceField.setColumns(10);
		priceField.addKeyListener(this);
		
		JPanel stockPnl = new JPanel();
		stockPnl.setBackground(new Color(255, 248, 220));
		formPnl.add(stockPnl);
		stockPnl.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel menuStock = new JLabel("Menu Stock: ");
		stockPnl.add(menuStock);
		
		stockField = new JTextField();
		stockPnl.add(stockField);
		stockField.setColumns(10);
		stockField.addKeyListener(this);
		
		JPanel imgPnl = new JPanel();
		imgPnl.setBackground(new Color(255, 248, 220));
		formPnl.add(imgPnl);
		imgPnl.setLayout(new BorderLayout(20, 0));
		
		JLabel menuImage = new JLabel("Menu Image");
		imgPnl.add(menuImage, BorderLayout.WEST);
		
		textField = new JTextField();
		imgPnl.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		textField.setEnabled(false);
		
		btn = new JButton("add image");
		imgPnl.add(btn, BorderLayout.EAST);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("Open File");
				jfc.setAcceptAllFileFilterUsed(false);
				jfc.setFileFilter(new FileNameExtensionFilter("Image (png, jpg)", "png", "jpg"));
				int res = jfc.showOpenDialog(null);
				if(res == JFileChooser.APPROVE_OPTION) {
					textField.setText(jfc.getSelectedFile().getName());
					File file = new File(jfc.getSelectedFile().toString());
					try {
						Image image = ImageIO.read(file);
						image = image.getScaledInstance(70, -1, Image.SCALE_SMOOTH);
						BufferedImage bi = convertToBufferedImage(image);
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						ImageIO.write(bi, "PNG", bos);
						byte[] data = bos.toByteArray();
						blob = new SerialBlob(data);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

			}


		}
	});

		
		JPanel btnPnl = new JPanel();
		btnPnl.setBackground(new Color(255, 248, 220));
		panel.add(btnPnl, BorderLayout.SOUTH);
		
		JButton addBtn = new JButton("Add Menu");
		btnPnl.add(addBtn);
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fieldName.getText().isEmpty()||priceField.getText().isEmpty()||stockField.getText().isEmpty()||textField.getText().isEmpty()){
					JOptionPane.showMessageDialog(mainpanel, "All field must be filled");
					return;
				}
				if(Integer.parseInt(priceField.getText())<1000) {
					JOptionPane.showMessageDialog(mainpanel, "Price must be more than 1000");
					return;
					
				}
				if(Integer.parseInt(stockField.getText())<0) {
					JOptionPane.showMessageDialog(mainpanel, "Stock must be more than 0");
					return;
					
				}
				c.preparedStatemnet(fieldName.getText(), priceField.getText(), stockField.getText(), blob);
				JOptionPane.showMessageDialog(mainpanel, "Added");
				f.resetData();
				sif.foodDtm.setRowCount(0);
				sif.initTblData();
				
			}
		});
	}
	
	public static BufferedImage convertToBufferedImage(Image image)
	{
	    BufferedImage newImage = new BufferedImage(
	    image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D draw = newImage.createGraphics();
	    draw.drawImage(image, 0, 0, null);
	    draw.dispose();
	    return newImage;
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
		
	}

}
