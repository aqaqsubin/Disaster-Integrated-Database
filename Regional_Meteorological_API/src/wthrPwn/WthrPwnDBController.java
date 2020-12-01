package wthrPwn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.JDBC;

public class WthrPwnDBController extends JDBC {

	public int getCountWthrPwnList(String tableName, WthrPwnList pwnList) {

		int equalNum = 0;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		String selectQuery = "SELECT count(*) FROM " + tableName
				+ " WHERE title=? AND tmFc=DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s') ";
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setString(1, pwnList.getTitle());
			pstmt.setString(2, convertDate(pwnList.getTmFc()));

			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				equalNum = resultSet.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equalNum;
	}

	public int insertWthrPwnList(String tableName, WthrPwnList pwnList, String stnName) {

		int r = -1;
		PreparedStatement pstmt = null;
		String insertQuery = "INSERT INTO " + tableName + " (title, stnId, stnName, tmSeq, tmFc)"
				+ "VALUES (?, ?, ?, ?, DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'))";
		try {
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, pwnList.getTitle());
			pstmt.setString(2, pwnList.getStnId());
			pstmt.setString(3, stnName);
			pstmt.setInt(4, pwnList.getTmSeq());
			pstmt.setString(5, convertDate(pwnList.getTmFc()));
			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;

	}

	public int getCountWthrPwn(String tableName, WthrPwn pwn) {

		int equalNum = 0;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		String selectQuery = "SELECT count(*) FROM " + tableName
				+ " WHERE stnId=? AND tmFc=DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s')";
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setString(1, pwn.getStnId());
			pstmt.setString(2, convertDate(pwn.getTmFc()));

			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				equalNum = resultSet.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equalNum;
	}

	public int insertWthrPwn(String tableName, WthrPwn pwn, String stnName) {

		int r = -1;
		PreparedStatement pstmt = null;
		String insertQuery = "INSERT INTO " + tableName + " (stnName, stnId, tmFc, tmSeq, refNum, pwnExpl, other)"
				+ "VALUES (?, ?, DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), ?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, stnName);
			pstmt.setString(2, pwn.getStnId());
			pstmt.setString(3, convertDate(pwn.getTmFc()));
			pstmt.setInt(4, pwn.getTmSeq());
			pstmt.setInt(5, pwn.getRefNum());
			pstmt.setString(6, pwn.getPwnExpl());
			pstmt.setString(7, pwn.getOther());

			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

}
