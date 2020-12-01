package occurrInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.JDBC;

public class OccurInfoDBController extends JDBC {
	
	private String tableName = "RedTideOccurInfo";

	public int getCountRedTideOccurInfo(RedTideOccurInfo occurInfo) {
		int equalNum = 0;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;

		String deleteQuery = "SELECT count(*) FROM " + tableName
				+ " WHERE hjdCd = ? AND prosWeek = DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s')";
		try {

			pstmt = con.prepareStatement(deleteQuery);
			pstmt.setString(1, occurInfo.getHjdCd());
			pstmt.setString(2, convertDate(occurInfo.getProsWeek()));

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				equalNum = resultSet.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equalNum;
	}

	public int insertRedTideOccurInfo(RedTideOccurInfo occurInfo) {
		int r = -1;
		PreparedStatement pstmt = null;
		String insertQuery = "INSERT INTO " + tableName
				+ " (hjdCd, hjdName, prosWeek, pros1Week, pros2Week, pros3Week, pros4Week)"
				+ " VALUES (?, ? DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), ?, ?, ?, ?)";
		try {

			pstmt = con.prepareStatement(insertQuery);

			pstmt.setString(2, occurInfo.getHjdCd());
			pstmt.setString(3, occurInfo.getHjdName());
			pstmt.setString(1, convertDate(occurInfo.getProsWeek()));
			pstmt.setString(4, occurInfo.getPros1Week());
			pstmt.setString(5, occurInfo.getPros2Week());
			pstmt.setString(6, occurInfo.getPros3Week());
			pstmt.setString(7, occurInfo.getPros4Week());

			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
}
