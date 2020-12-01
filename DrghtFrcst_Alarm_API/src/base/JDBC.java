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

	public String convertDate(String tm) {
		
		try {
			return tm.length() == 6 
					? String.format("%s-%s-01 00:00:00", tm.substring(0, 4), tm.substring(4, 6)) 
					: tm.length() == 8
					? String.format("%s-%s-%s 00:00:00", tm.substring(0, 4), tm.substring(4, 6), tm.substring(6, 8))
					: tm.length() == 12
							? String.format("%s-%s-%s %s:%s:00", tm.substring(0, 4), tm.substring(4, 6),
									tm.substring(6, 8), tm.substring(8, 10), tm.substring(10, 12))
							: String.format("%s-%s-%s %s:%s:%s", tm.substring(0, 4), tm.substring(4, 6),
									tm.substring(6, 8), tm.substring(8, 10), tm.substring(10, 12),
									tm.substring(12, 14));

		} catch (StringIndexOutOfBoundsException e) {
			return null;
		}
	}

}
