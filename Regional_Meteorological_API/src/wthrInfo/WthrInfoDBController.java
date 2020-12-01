package wthrInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.JDBC;

public class WthrInfoDBController  extends JDBC {
	
	
	public int getCountWthrInfoList(String tableName, WthrInfoList infoList) {

		int equalNum = 0;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		String selectQuery = "SELECT count(*) FROM "+tableName+" WHERE title=? AND tmFc=DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s') ";
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setString(1, infoList.getTitle());
			pstmt.setString(2, convertDate(infoList.getTmFc()));
			
			resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				equalNum = resultSet.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equalNum;
	}

	public int insertWthrInfoList(String tableName, WthrInfoList infoList, String stnName) {

		int r = -1;
		PreparedStatement pstmt = null;
		String insertQuery = "INSERT INTO "+tableName+" (title, stnId, stnName, tmSeq, tmFc)"
				+ "VALUES (?, ?, ?, ?, DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'))";
		try {
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, infoList.getTitle());
			pstmt.setString(2, infoList.getStnId());
			pstmt.setString(3, stnName);
			pstmt.setInt(4, infoList.getTmSeq());
			pstmt.setString(5, convertDate(infoList.getTmFc()));
			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;

	}
	public int getCountWthrInfo(String tableName, WthrInfo info) {

		int equalNum = 0;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		String selectQuery = "SELECT count(*) FROM "+tableName+" WHERE stnId=? AND tmFc=DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s')";
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setString(1, info.getStnId());
			pstmt.setString(2, convertDate(info.getTmFc()));
			
			resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				equalNum = resultSet.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equalNum;
	}
	
	
	public int insertWthrInfo(String tableName, WthrInfo info, String stnName) {
		
		int r = -1;
		PreparedStatement pstmt = null;
		
		String insertQuery = "INSERT INTO "+tableName+" (stnName, stnId, tmFc, tmSeq, wthrInfo)"
				+ "VALUES (?, ?, DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), ?, ?)";
		try {
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, stnName);
			pstmt.setString(2, info.getStnId());
			pstmt.setString(3, convertDate(info.getTmFc()));
			pstmt.setInt(4, info.getTmSeq());
			pstmt.setString(5, info.getWthrInfo());
			
			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
}
