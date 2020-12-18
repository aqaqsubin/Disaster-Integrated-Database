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
				+ " WHERE title = ? AND srCode = ?";
		try {

			pstmt = con.prepareStatement(deleteQuery);
			pstmt.setString(1, occurInfo.getTitle());
			pstmt.setString(2, occurInfo.getSrCode());

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				equalNum = resultSet.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equalNum;
	}
	public String getInsertQuery(RedTideOccurInfo occurInfo) {
		PreparedStatement pstmt = null;
		String insertQuery = "INSERT INTO " + tableName
				+ " (title, srCode, afterView, etc, state, regDate, report)"
				+ " VALUES (?, ?, ?, ?, ?, DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), ?)";

		try {
			pstmt = con.prepareStatement(insertQuery);

			pstmt.setString(1, occurInfo.getTitle());
			pstmt.setString(2, occurInfo.getSrCode());
			pstmt.setString(3, occurInfo.getAfterView());
			pstmt.setString(4, occurInfo.getEtc());
			pstmt.setString(5, occurInfo.getState());
			pstmt.setString(6, convertDate(occurInfo.getRegDate()));
			pstmt.setString(7, occurInfo.getReport());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pstmt.toString().split("ClientPreparedStatement: ")[1]+";";
	}
	
	public int insertRedTideOccurInfo(RedTideOccurInfo occurInfo) {
		int r = -1;
		PreparedStatement pstmt = null;
		String insertQuery = "INSERT INTO " + tableName
				+ " (title, srCode, afterView, etc, state, regDate, report)"
				+ " VALUES (?, ?, ?, ?, ?, DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), ?)";
		try {

			pstmt = con.prepareStatement(insertQuery);

			pstmt.setString(1, occurInfo.getTitle());
			pstmt.setString(2, occurInfo.getSrCode());
			pstmt.setString(3, occurInfo.getAfterView());
			pstmt.setString(4, occurInfo.getEtc());
			pstmt.setString(5, occurInfo.getState());
			pstmt.setString(6, convertDate(occurInfo.getRegDate()));
			pstmt.setString(7, occurInfo.getReport());

			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
}
