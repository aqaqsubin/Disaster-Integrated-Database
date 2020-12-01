package livinIndustryFrcst;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.JDBC;

public class LivinIndustryFrcstDBController extends JDBC {
	
	private String tableName = "LivinIndustryDrghtFrcst";

	public int getCountFcstInfoList(LivinIndustryFrcstList frcstList) {
		int equalNum = 0;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		
		String deleteQuery = "SELECT count(*) FROM " + tableName
				+ " WHERE anldt = DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s')";
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

	public int insertFcstInfoList(LivinIndustryFrcstList frcstList) {
		int r = -1;
		PreparedStatement pstmt = null;
		String insertQuery = "INSERT INTO " + tableName
				+ " (anldt, livinCareZone, livinAlrtZone, livinSeriusZone, livinAttZone, livinMap,"
				+ " livinCare1M, livinAlrt1M, livinSerius1M, livinAtt1M, livinMap1M,"
				+ " livinCare2M, livinAlrt2M, livinSerius2M, livinAtt2M, livinMap2M,"
				+ " livinCare3M, livinAlrt3M, livinSerius3M, livinAtt3M, livinMap3M)"
				+ "VALUES (DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), ?, ?, ?, ?, ?,"
				+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {

			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, convertDate(frcstList.getAnldt()));
			pstmt.setString(2, frcstList.getLivinCareZone());
			pstmt.setString(3, frcstList.getLivinAlrtZone());
			pstmt.setString(4, frcstList.getLivinSeriusZone());
			pstmt.setString(5, frcstList.getLivinAttZone());
			pstmt.setString(6, frcstList.getLivinMap());
			pstmt.setString(7, frcstList.getLivinCare1M());
			pstmt.setString(8, frcstList.getLivinAlrt1M());
			pstmt.setString(9, frcstList.getLivinSerius1M());
			pstmt.setString(10, frcstList.getLivinAtt1M());
			pstmt.setString(11, frcstList.getLivinMap1M());
			pstmt.setString(12, frcstList.getLivinCare2M());
			pstmt.setString(13, frcstList.getLivinAlrt2M());
			pstmt.setString(14, frcstList.getLivinSerius2M());
			pstmt.setString(15, frcstList.getLivinAtt2M());
			pstmt.setString(16, frcstList.getLivinMap2M());
			pstmt.setString(17, frcstList.getLivinCare3M());
			pstmt.setString(18, frcstList.getLivinAlrt3M());
			pstmt.setString(19, frcstList.getLivinSerius3M());
			pstmt.setString(20, frcstList.getLivinAtt3M());
			pstmt.setString(21, frcstList.getLivinMap3M());

			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

}
