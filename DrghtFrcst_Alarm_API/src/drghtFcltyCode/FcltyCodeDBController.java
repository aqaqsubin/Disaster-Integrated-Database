package drghtFcltyCode;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import base.JDBC;

public class FcltyCodeDBController extends JDBC {

	private String tableName = "DrghtFcltyCode";
	
	public int getCountFcltyCodeList(FcltyCodeList fcltyCd) {
		int equalNum = 0;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;

		String deleteQuery = "SELECT count(*) FROM " + tableName + " WHERE fcltyCode = ?";
		try {

			pstmt = con.prepareStatement(deleteQuery);
			pstmt.setString(1, fcltyCd.getFcltyCode());

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				equalNum = resultSet.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equalNum;
	}
	
	public int insertFcltyCodeList(FcltyCodeList fcltyCd) {
		int r = -1;
		PreparedStatement pstmt = null;
		
		String insertQuery = "INSERT INTO " + tableName
				+ " (fcltyDivCode, fcltyCode, fcltyName) VALUES (?, ?, ?)";
		try {

			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, fcltyCd.getFcltyDivCode());
			pstmt.setString(2, fcltyCd.getFcltyCode());
			pstmt.setString(3, fcltyCd.getFcltyName());

			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public List<String> getFcltyCode(long fcltyDivCode){
		
		List<String> fcltyCdList = new ArrayList<>();

		ResultSet resultSet = null;
		PreparedStatement pstmt = null;

		String deleteQuery = "SELECT * FROM " + tableName + " WHERE fcltyDivCode = ?";
		try {

			pstmt = con.prepareStatement(deleteQuery);
			pstmt.setString(1, String.valueOf(fcltyDivCode));

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				fcltyCdList.add(resultSet.getString("fcltyCode"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fcltyCdList;
	}

}
