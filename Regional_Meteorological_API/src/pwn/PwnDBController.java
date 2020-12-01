package pwn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.JDBC;

public class PwnDBController extends JDBC {

	public int getCountPwnCd(String tableName, PwnCd cd) {

		int equalNum = 0;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		String selectQuery = "SELECT count(*) FROM " + tableName
				+ " WHERE stnId=? AND tmFc=DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s') AND areaCd =? AND warnVar =? ";
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setString(1, cd.getStnId());
			pstmt.setString(2, convertDate(cd.getTmFc()));
			pstmt.setString(3, cd.getAreaCd());
			pstmt.setInt(4, cd.getWarnVar());

			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				equalNum = resultSet.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equalNum;
	}

	public int insertPwnCd(String tableName, PwnCd cd, String stnName) {

		int r = -1;
		PreparedStatement pstmt = null;
		
		String tmStart = convertDate(cd.getTmStart());
		String tmEnd = convertDate(cd.getTmEnd());
		String tmEntireEnd = convertDate(cd.getTmEntireEnd());
		
		String tmStartQueryStatement = (tmStart == null) ? "?" : "DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s')";
		String tmEndQueryStatement = (tmEnd == null) ? "?" : "DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s')";
		String tmEntireEndQueryStatement = (tmEntireEnd == null) ? "?" : "DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s')";
		
		String insertQuery = "INSERT INTO " + tableName
				+ " (stnId, stnName, tmSeq, tmFc, areaCd, areaName, warnVar, warnStress, wrnCd, tmStart, tmEnd, tmEntireEnd, cancelCd)"
				+ "VALUES (?, ?, ?, DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), ?, ?, ?, ?, ?,"
				+ tmStartQueryStatement +","
				+ tmEndQueryStatement +","
				+ tmEntireEndQueryStatement +", ?)";
		
		try {
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, cd.getStnId());
			pstmt.setString(2, stnName);
			pstmt.setInt(3, cd.getTmSeq());
			pstmt.setString(4, convertDate(cd.getTmFc()));
			pstmt.setString(5, cd.getAreaCd());
			pstmt.setString(6, cd.getAreaName());
			pstmt.setInt(7, cd.getWarnVar());
			pstmt.setInt(8, cd.getWarnStress());
			pstmt.setInt(9, cd.getWrnCd());
			pstmt.setString(10, tmStart);
			pstmt.setString(11, tmEnd);
			pstmt.setString(12, tmEntireEnd);
			pstmt.setInt(13, cd.getCancelCd());

			System.out.println(pstmt.toString());
			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;

	}

	public int getCountPwnStatus(String tableName, PwnStatus stat) {

		int equalNum = 0;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		String selectQuery = "SELECT count(*) FROM " + tableName
				+ " WHERE titleExpl=? AND tmFc=DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s')";
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setString(1, stat.getTitleExpl());
			pstmt.setString(2, convertDate(stat.getTmFc()));

			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				equalNum = resultSet.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equalNum;
	}

	public int insertPwnStatus(String tableName, PwnStatus stat) {

		int r = -1;
		PreparedStatement pstmt = null;

		String insertQuery = "INSERT INTO " + tableName + " ( tmFc, tmEffect, tmSeq, titleExpl, effectExpl, preEffectExpl, other)"
				+ "VALUES ( DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), ?, ?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, convertDate(stat.getTmFc()));
			pstmt.setString(2, convertDate(stat.getTmEffect()));
			pstmt.setInt(3, stat.getTmSeq());
			pstmt.setString(4, stat.getTitleExpl());
			pstmt.setString(5, stat.getEffectExpl());
			pstmt.setString(6, stat.getPreEffectExpl());
			pstmt.setString(7, stat.getOther());

			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
}
