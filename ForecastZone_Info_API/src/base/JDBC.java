package base;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
	protected Connection con = null;

	private String url = "";
	private String id = "";
	private String pw = "";

	public JDBC() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // mysql
			con = DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean connect() {

		try {
			if (con == null)
				con = DriverManager.getConnection(url, id, pw);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con != null;
	}

	public Connection getConnection() {
		return con;
	}

	public void closeConnection() {

		try {
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
