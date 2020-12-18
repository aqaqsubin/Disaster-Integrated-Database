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

		String deleteQuery = "SELECT count(*) FROM " + tableName + " WHERE srcode = ?";

		if (occurSpot.getSrCode().equals("null"))
			return -1;

		try {

			pstmt = con.prepareStatement(deleteQuery);
			pstmt.setString(1, occurSpot.getSrCode());

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				equalNum = resultSet.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equalNum;
	}

	public String getInsertQuery(RedTideOccurSpot occurSpot) {
		PreparedStatement pstmt = null;
		String insertQuery = "INSERT INTO " + tableName
				+ " (srCode, causeOrganism, orgDensityMax, orgDensityMin, tempMax, tempMin, seaArea ,regDate)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'))";

		try {
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, occurSpot.getSrCode());
			pstmt.setString(2, occurSpot.getCauseOrganism());
			pstmt.setDouble(3, occurSpot.getOrgDensityMax());
			pstmt.setDouble(4, occurSpot.getOrgDensityMin());
			pstmt.setDouble(5, occurSpot.getTempMax());
			pstmt.setDouble(6, occurSpot.getTempMin());
			pstmt.setString(7, occurSpot.getSeaArea());
			pstmt.setString(8, convertDate(occurSpot.getRegDate()));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pstmt.toString().split("ClientPreparedStatement: ")[1] + ";";
	}

	public int insertRedTideOccurSpot(RedTideOccurSpot occurSpot) {
		int r = -1;
		PreparedStatement pstmt = null;
		String insertQuery = "INSERT INTO " + tableName
				+ " (srCode, causeOrganism, orgDensityMax, orgDensityMin, tempMax, tempMin, seaArea ,regDate)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'))";

		try {

			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, occurSpot.getSrCode());
			pstmt.setString(2, occurSpot.getCauseOrganism());
			pstmt.setDouble(3, occurSpot.getOrgDensityMax());
			pstmt.setDouble(4, occurSpot.getOrgDensityMin());
			pstmt.setDouble(5, occurSpot.getTempMax());
			pstmt.setDouble(6, occurSpot.getTempMin());
			pstmt.setString(7, occurSpot.getSeaArea());
			pstmt.setString(8, convertDate(occurSpot.getRegDate()));

			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
}
