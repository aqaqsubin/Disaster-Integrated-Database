package fcstZone;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.JDBC;

public class FcstZoneDBController extends JDBC {
	
	public int getCountFcstZone(String tableName, FcstZoneCd cd) {

		int equalNum = 0;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		String deleteQuery = "SELECT count(*) FROM " + tableName
				+ " WHERE regId =? AND tmEd =DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s') AND tmSt=DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s')";
		try {

			pstmt = con.prepareStatement(deleteQuery);
			pstmt.setString(1, cd.getRegId());
			pstmt.setString(2, convertDate(cd.getTmEd()));
			pstmt.setString(3, convertDate(cd.getTmSt()));

			resultSet = pstmt.executeQuery();
			
			while (resultSet.next()) {
				equalNum = resultSet.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equalNum;
	}

	public int insertFcstZone(String tableName, FcstZoneCd cd) {

		int r = -1;
		PreparedStatement pstmt = null;
		String insertQuery = "INSERT INTO " + tableName
				+ " (regId, tmEd, tmSt, regName, regEn, regSp, lat, lon, ht, regUp, stnWn, stnF3, stnFd, stnFw, seq)"
				+ "VALUES (?, DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {

			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, cd.getRegId());
			pstmt.setString(2, convertDate(cd.getTmEd()));
			pstmt.setString(3, convertDate(cd.getTmSt()));
			pstmt.setString(4, cd.getRegName());
			pstmt.setString(5, cd.getRegEn());
			pstmt.setString(6, cd.getRegSp());
			pstmt.setDouble(7, cd.getLat());
			pstmt.setDouble(8, cd.getLon());
			pstmt.setDouble(9, cd.getHt());
			pstmt.setString(10, cd.getRegUp());
			pstmt.setString(11, cd.getStnWn());
			pstmt.setString(12, cd.getStnF3());
			pstmt.setString(13, cd.getStnFd());
			pstmt.setString(14, cd.getStnFw());
			pstmt.setInt(15, cd.getSeq());

			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;

	}
	public String convertDate(String tm) {

		return String.format("%s-%s-%s %s:%s:00", tm.substring(0, 4), tm.substring(4, 6), tm.substring(6, 8),
				tm.substring(8, 10), tm.substring(10, 12));
	}


}
