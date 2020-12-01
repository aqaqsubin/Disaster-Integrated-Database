package wthrFrcst;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.JDBC;

public class WthrFrcstDBController extends JDBC {

	private String tableName = "WthrDrghtFrcst";

	public int getCountFcstInfoList(WthrFrcstList infoList) {
		int equalNum = 0;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		
		String deleteQuery = "SELECT count(*) FROM " + tableName
				+ " WHERE anldt=DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s')";
		try {

			pstmt = con.prepareStatement(deleteQuery);
			System.out.println(infoList.getAnldt());
			pstmt.setString(1, convertDate(infoList.getAnldt()));

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				equalNum = resultSet.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equalNum;

	}

	public int insertFcstInfoList(WthrFrcstList infoList) {
		int r = -1;
		PreparedStatement pstmt = null;
		String insertQuery = "INSERT INTO " + tableName
				+ " (anldt, wthrCareZone, wthrAlrtZone, wthrSeriusZone, wthrAttZone, wthrMap,"
				+ " wthrCare1M, wthrAlrt1M, wthrSerius1M, wthrAtt1M, wthrMap1M,"
				+ " wthrCare2M, wthrAlrt2M, wthrSerius2M, wthrAtt2M, wthrMap2M,"
				+ " wthrCare3M, wthrAlrt3M, wthrSerius3M, wthrAtt3M, wthrMap3M)"
				+ "VALUES (DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), ?, ?, ?, ?, ?,"
				+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {

			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, convertDate(infoList.getAnldt()));
			pstmt.setString(2, infoList.getWthrCareZone());
			pstmt.setString(3, infoList.getWthrAlrtZone());
			pstmt.setString(4, infoList.getWthrSeriusZone());
			pstmt.setString(5, infoList.getWthrAttZone());
			pstmt.setString(6, infoList.getWthrMap());
			pstmt.setString(7, infoList.getWthrCare1M());
			pstmt.setString(8, infoList.getWthrAlrt1M());
			pstmt.setString(9, infoList.getWthrSerius1M());
			pstmt.setString(10, infoList.getWthrAtt1M());
			pstmt.setString(11, infoList.getWthrMap1M());
			pstmt.setString(12, infoList.getWthrCare2M());
			pstmt.setString(13, infoList.getWthrAlrt2M());
			pstmt.setString(14, infoList.getWthrSerius2M());
			pstmt.setString(15, infoList.getWthrAtt2M());
			pstmt.setString(16, infoList.getWthrMap2M());
			pstmt.setString(17, infoList.getWthrCare3M());
			pstmt.setString(18, infoList.getWthrAlrt3M());
			pstmt.setString(19, infoList.getWthrSerius3M());
			pstmt.setString(20, infoList.getWthrAtt3M());
			pstmt.setString(21, infoList.getWthrMap3M());

			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

}
