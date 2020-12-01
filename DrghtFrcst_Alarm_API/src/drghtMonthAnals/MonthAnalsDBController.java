package drghtMonthAnals;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.JDBC;

public class MonthAnalsDBController extends JDBC {
private String tableName = "DrghtMonthAnalsList";
	
	public int getCountMonthAnalsList(MonthAnalsList monthAnalsInfo) {
		int equalNum = 0;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;

		String deleteQuery = "SELECT count(*) FROM " + tableName + " WHERE hjdCd = ? AND prosMonth = DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s')";
		try {

			pstmt = con.prepareStatement(deleteQuery);
			pstmt.setString(1, monthAnalsInfo.getHjdCd());
			pstmt.setString(2, convertDate(monthAnalsInfo.getProsMonth()));

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				equalNum = resultSet.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equalNum;
	}
	public String getInsertQuery(MonthAnalsList monthAnalsInfo) {
		int r = -1;
		PreparedStatement pstmt = null;
		String insertQuery = "INSERT INTO " + tableName
				+ " (hjdCd, hjdName, prosMonth, pros1Month, pros2Month, pros3Month)"
				+ " VALUES (?, ?, DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), ?, ?, ?)";
		try {

			pstmt = con.prepareStatement(insertQuery);
			
			pstmt.setString(1, monthAnalsInfo.getHjdCd());
			pstmt.setString(2, monthAnalsInfo.getHjdName());
			pstmt.setString(3, convertDate(monthAnalsInfo.getProsMonth()));
			pstmt.setString(4, monthAnalsInfo.getPros1Month());
			pstmt.setString(5, monthAnalsInfo.getPros2Month());
			pstmt.setString(6, monthAnalsInfo.getPros3Month());

			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pstmt.toString().split("ClientPreparedStatement: ")[1]+";";
	}
	public int dumpMonthAnalsList(String filePath) {
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
	public int insertMonthAnalsList(MonthAnalsList monthAnalsInfo) {
		int r = -1;
		PreparedStatement pstmt = null;
		String insertQuery = "INSERT INTO " + tableName
				+ " (hjdCd, hjdName, prosMonth, pros1Month, pros2Month, pros3Month)"
				+ " VALUES (?, ?, DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), ?, ?, ?)";
		try {

			pstmt = con.prepareStatement(insertQuery);
			
			pstmt.setString(1, monthAnalsInfo.getHjdCd());
			pstmt.setString(2, monthAnalsInfo.getHjdName());
			pstmt.setString(3, convertDate(monthAnalsInfo.getProsMonth()));
			pstmt.setString(4, monthAnalsInfo.getPros1Month());
			pstmt.setString(5, monthAnalsInfo.getPros2Month());
			pstmt.setString(6, monthAnalsInfo.getPros3Month());

			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

}
