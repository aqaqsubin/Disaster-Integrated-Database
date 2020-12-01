package occurrSpot;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.JDBC;

public class OccurSpotDBController extends JDBC {
	
	private String tableName = "RedTideOccurSpot";

	public int getCountRedTideOccurSpot(RedTideOccurSpot occurSpot) {
		int equalNum = 0;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;

		String deleteQuery = "SELECT count(*) FROM " + tableName
				+ " WHERE hjdCd = ? AND prosWeek = DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s')";
		try {

			pstmt = con.prepareStatement(deleteQuery);
			pstmt.setString(1, occurSpot.getHjdCd());
			pstmt.setString(2, convertDate(occurSpot.getProsWeek()));

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				equalNum = resultSet.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equalNum;
	}

	public int insertRedTideOccurSpot(RedTideOccurSpot occurSpot) {
		int r = -1;
		PreparedStatement pstmt = null;
		String insertQuery = "INSERT INTO " + tableName
				+ " (hjdCd, hjdName, prosWeek, pros1Week, pros2Week, pros3Week, pros4Week)"
				+ " VALUES (?, ? DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), ?, ?, ?, ?)";
		try {

			pstmt = con.prepareStatement(insertQuery);

			pstmt.setString(2, occurSpot.getHjdCd());
			pstmt.setString(3, occurSpot.getHjdName());
			pstmt.setString(1, convertDate(occurSpot.getProsWeek()));
			pstmt.setString(4, occurSpot.getPros1Week());
			pstmt.setString(5, occurSpot.getPros2Week());
			pstmt.setString(6, occurSpot.getPros3Week());
			pstmt.setString(7, occurSpot.getPros4Week());

			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
}
