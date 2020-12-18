package drghtWeekAnals;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.JDBC;

public class WeekAnalsDBController extends JDBC {

	private String tableName = "DrghtWeekAnalsList";

	public int getCountWeekAnalsList(WeekAnalsList weekAnalsInfo) {
		int equalNum = 0;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;

		String deleteQuery = "SELECT count(*) FROM " + tableName
				+ " WHERE hjdCd = ? AND prosWeek = DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s')";
		try {

			pstmt = con.prepareStatement(deleteQuery);
			pstmt.setString(1, weekAnalsInfo.getHjdCd());
			pstmt.setString(2, convertDate(weekAnalsInfo.getProsWeek()));

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				equalNum = resultSet.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equalNum;
	}

	public String getInsertQuery(WeekAnalsList weekAnalsInfo) {
		PreparedStatement pstmt = null;
		String insertQuery = "INSERT INTO " + tableName
				+ " (hjdCd, hjdName, prosWeek, pros1Week, pros2Week, pros3Week, pros4Week)"
				+ " VALUES (?, ?, DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), ?, ?, ?, ?)";

		try {
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, weekAnalsInfo.getHjdCd());
			pstmt.setString(2, weekAnalsInfo.getHjdName());
			pstmt.setString(3, convertDate(weekAnalsInfo.getProsWeek()));
			pstmt.setString(4, weekAnalsInfo.getPros1Week());
			pstmt.setString(5, weekAnalsInfo.getPros2Week());
			pstmt.setString(6, weekAnalsInfo.getPros3Week());
			pstmt.setString(7, weekAnalsInfo.getPros4Week());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pstmt.toString().split("ClientPreparedStatement: ")[1]+";";
	}

	

	public int insertWeekAnalsList(WeekAnalsList weekAnalsInfo) {
		int r = -1;
		PreparedStatement pstmt = null;
		String insertQuery = "INSERT INTO " + tableName
				+ " (hjdCd, hjdName, prosWeek, pros1Week, pros2Week, pros3Week, pros4Week)"
				+ " VALUES (?, ?, DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), ?, ?, ?, ?)";
		try {

			pstmt = con.prepareStatement(insertQuery);

			pstmt.setString(1, weekAnalsInfo.getHjdCd());
			pstmt.setString(2, weekAnalsInfo.getHjdName());
			pstmt.setString(3, convertDate(weekAnalsInfo.getProsWeek()));
			pstmt.setString(4, weekAnalsInfo.getPros1Week());
			pstmt.setString(5, weekAnalsInfo.getPros2Week());
			pstmt.setString(6, weekAnalsInfo.getPros3Week());
			pstmt.setString(7, weekAnalsInfo.getPros4Week());

			System.out.println(pstmt.toString());
			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	

}
