import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Connect {
	final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://localhost:3306/foodstreet";
	final String USER = "root";
	final String PASS = "";
	private Statement stmt;
	private PreparedStatement ps;
	Connection conn;
	
	public Connect(){
		try {
			Class.forName(JDBC_DRIVER);
			conn=DriverManager.getConnection(DB_URL, USER, PASS);
			stmt=conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ResultSet executeQuery(String query) {
		ResultSet res = null;
		try {
			res=stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public void executeUpdate(String query) {
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void preparedStatemnet (String name, String price, String stock,Blob blob) {
		try {
			ps = conn.prepareStatement("INSERT INTO food(foodname, foodprice, foodstock, foodimage) VALUES (?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, price);
			ps.setString(3,stock);
			ps.setBlob(4,blob);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	

}