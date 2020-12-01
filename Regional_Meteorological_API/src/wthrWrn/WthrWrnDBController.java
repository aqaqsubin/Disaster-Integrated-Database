package wthrWrn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.JDBC;

public class WthrWrnDBController extends JDBC{


	public int getCountWthrWrnList(String tableName, WthrWrnList wrnList) {

		int equalNum = 0;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		
		String selectQuery = "SELECT count(*) FROM "+tableName+" WHERE title=? AND tmFc=DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s') ";
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setString(1, wrnList.getTitle());
			pstmt.setString(2, convertDate(wrnList.getTmFc()));
			
			resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				equalNum = resultSet.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equalNum;
	}

	public int insertWthrWrnList(String tableName, WthrWrnList wrnList, String stnName) {

		int r = -1;
		PreparedStatement pstmt = null;
		String insertQuery = "INSERT INTO "+tableName+" (title, stnId, stnName, tmSeq, tmFc)"
				+ "VALUES (?, ?, ?, ?, DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'))";
		try {
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, wrnList.getTitle());
			pstmt.setInt(2, wrnList.getStnId());
			pstmt.setString(3, stnName);
			pstmt.setInt(4, wrnList.getTmSeq());
			pstmt.setString(5, convertDate(wrnList.getTmFc()));
			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;

	}
	public int getCountWthrWrnMsg(String tableName, WthrWrnMsg wrnMsg) {

		int equalNum = 0;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		
		String selectQuery = "SELECT count(*) FROM "+tableName+" WHERE title=? AND tmFc=DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s') AND stnId=? ";
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setString(1, wrnMsg.getTitle());
			pstmt.setString(2, convertDate(wrnMsg.getTmFc()));
			pstmt.setString(3, wrnMsg.getStnId());
			
			resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				equalNum = resultSet.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equalNum;
	}
	public int insertWthrWrnMsg(String tableName, WthrWrnMsg wrnMsg, String stnName) {
		
		int r = -1;
		PreparedStatement pstmt = null;
		String insertQuery = "INSERT INTO "+tableName+" (stnName, stnId, tmFc, tmSeq, wrnCd, title, zoneReport, tmReport, expl, tmWrnReport, wrnReport ,preWrnReport, other)"
				+ "VALUES (?, ?, DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), ?, ?, ?, ?, ?, ?, DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, stnName);
			pstmt.setString(2, wrnMsg.getStnId());
			pstmt.setString(3, convertDate(wrnMsg.getTmFc()));
			pstmt.setInt(4, wrnMsg.getTmSeq());
			pstmt.setInt(5, wrnMsg.getWrnCd());
			pstmt.setString(6, wrnMsg.getTitle());
			pstmt.setString(7, wrnMsg.getZoneReport());
			pstmt.setString(8, wrnMsg.getTmReport());
			pstmt.setString(9, wrnMsg.getExpl());
			pstmt.setString(10, convertDate(wrnMsg.getTmWrnReport()));
			pstmt.setString(11, wrnMsg.getWrnReport());
			pstmt.setString(12, wrnMsg.getPreWrnReport());
			pstmt.setString(13, wrnMsg.getOther());
			
			System.out.println(pstmt.toString());
			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

}
