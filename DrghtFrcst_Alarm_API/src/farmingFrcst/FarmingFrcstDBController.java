package farmingFrcst;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.JDBC;

public class FarmingFrcstDBController extends JDBC {

	private String tableName = "FarmingDrghtFrcst";

	public int getCountFcstInfoList(FarmingFrcstList frcstList) {
		int equalNum = 0;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;

		String deleteQuery = "SELECT count(*) FROM " + tableName + " WHERE anldt=DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s')";
		try {

			pstmt = con.prepareStatement(deleteQuery);
			pstmt.setString(1, convertDate(frcstList.getAnldt()));

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				equalNum = resultSet.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equalNum;

	}

	public int insertFcstInfoList(FarmingFrcstList frcstList) {
		int r = -1;
		PreparedStatement pstmt = null;
		String insertQuery = "INSERT INTO " + tableName
				+ " (anldt, farminCareZone, farminAlrtZone, farminSeriusZone, farminAttZone, farminMap,"
				+ " farmingCare1M, farminAlrt1M, farminSerius1M, farminAtt1M, farminMap1M,"
				+ " farminCare2M, farminAlrt2M, farminSerius2M, farminAtt2M, farminMap2M,"
				+ " farminCare3M, farminAlrt3M, farminSerius3M, farminAtt3M, farminMap3M)"
				+ "VALUES (DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), ?, ?, ?, ?, ?,"
				+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {

			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, convertDate(frcstList.getAnldt()));
			pstmt.setString(2, frcstList.getFarminCareZone());
			pstmt.setString(3, frcstList.getFarminAlrtZone());
			pstmt.setString(4, frcstList.getFarminSeriusZone());
			pstmt.setString(5, frcstList.getFarminAttZone());
			pstmt.setString(6, frcstList.getFarminMap());
			pstmt.setString(7, frcstList.getFarminCare1M());
			pstmt.setString(8, frcstList.getFarminAlrt1M());
			pstmt.setString(9, frcstList.getFarminSerius1M());
			pstmt.setString(10, frcstList.getFarminAtt1M());
			pstmt.setString(11, frcstList.getFarminMap1M());
			pstmt.setString(12, frcstList.getFarminCare2M());
			pstmt.setString(13, frcstList.getFarminAlrt2M());
			pstmt.setString(14, frcstList.getFarminSerius2M());
			pstmt.setString(15, frcstList.getFarminAtt2M());
			pstmt.setString(16, frcstList.getFarminMap2M());
			pstmt.setString(17, frcstList.getFarminCare3M());
			pstmt.setString(18, frcstList.getFarminAlrt3M());
			pstmt.setString(19, frcstList.getFarminSerius3M());
			pstmt.setString(20, frcstList.getFarminAtt3M());
			pstmt.setString(21, frcstList.getFarminMap3M());

			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
}
