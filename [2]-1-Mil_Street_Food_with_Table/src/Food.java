import java.awt.Image;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;

public class Food {
	
	private Connect c;
	private ArrayList<String> id;
	private ArrayList<String> name;
	private ArrayList<Integer>price;
	private ArrayList<Integer> stock;
	private ArrayList<byte[]> img;
	
	
	public Food() {
		
		c= new Connect();
		 
		
		id = new ArrayList<>();
		name = new ArrayList<>();
		price = new ArrayList<>();
		stock = new ArrayList<>();
		img = new ArrayList<>();
		initData();
		
	}


	private void initData() {
		ResultSet rs;
		String query = "SELECT * FROM FOOD";
		rs = c.executeQuery(query);
		try {
			while(rs.next()) {
				
				id.add(rs.getString(1));
				name.add(rs.getString(2));
				price.add(rs.getInt(3));
				stock.add(rs.getInt(4));
				
				byte[] image = null;
				image = rs.getBytes(5);
				img.add(image);


			}
		} catch (SQLException e) {
		}
		
	}


	public ArrayList<String> getId() {
		return id;
	}


	public void setId(ArrayList<String> id) {
		this.id = id;
	}


	public ArrayList<String> getName() {
		return name;
	}


	public void setName(ArrayList<String> name) {
		this.name = name;
	}


	public ArrayList<Integer> getPrice() {
		return price;
	}


	public void setPrice(ArrayList<Integer> price) {
		this.price = price;
	}


	public ArrayList<Integer> getStock() {
		return stock;
	}


	public void setStock(ArrayList<Integer> stock) {
		this.stock = stock;
	}


	public ArrayList<byte[]> getImg() {
		return img;
	}


	public void setImg(ArrayList<byte[]> img) {
		this.img = img;
	}


	public void resetData() {
		id.clear();
		name.clear();
		price.clear();
		stock.clear();
		img.clear();
		
		initData();
		
	}


	
	
	

}
