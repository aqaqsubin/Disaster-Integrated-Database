package base;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
	protected Connection con = null;

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
	public int dumpAnalsList(String filePath) {
		int r = -1;
		try {
			Runtime runtime = Runtime.getRuntime();
			
			Process pc = runtime.exec("cmd.exe /c mysql -h 150.183.116.231 -uroot -pkisti1004 --default-character-set=utf8 DisasterInformation < "+filePath);
			BufferedReader bf = new BufferedReader(new InputStreamReader(pc.getInputStream()));
			
			String line="";
			while((line = bf.readLine()) != null) {
				System.out.println(line);
			}
			
			System.out.println("Insert!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return r;
	}
	

}
