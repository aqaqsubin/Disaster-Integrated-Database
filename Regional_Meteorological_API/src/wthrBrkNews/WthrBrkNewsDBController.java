package wthrBrkNews;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.JDBC;

public class WthrBrkNewsDBController extends JDBC {
	
	public int getCountWthrBrkNewsList(String tableName, WthrBrkNewsList newsList) {

		int equalNum = 0;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		String selectQuery = "SELECT count(*) FROM "+tableName+" WHERE title=? AND tmFc=DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s') ";
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setString(1, newsList.getTitle());
			pstmt.setString(2, convertDate(newsList.getTmFc()));
			
			resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				equalNum = resultSet.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equalNum;
	}

	public int insertWthrBrkNewsList(String tableName, WthrBrkNewsList newsList, String stnName) {

		int r = -1;
		PreparedStatement pstmt = null;
		String insertQuery = "INSERT INTO "+tableName+" (title, stnId, stnName, tmSeq, tmFc)"
				+ "VALUES (?, ?, ?, ?, DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'))";
		try {
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, newsList.getTitle());
			pstmt.setString(2, newsList.getStnId());
			pstmt.setString(3, stnName);
			pstmt.setInt(4, newsList.getTmSeq());
			pstmt.setString(5, convertDate(newsList.getTmFc()));
			
			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;

	}
	public int getCountWthrBrkNews(String tableName, WthrBrkNews news) {

		int equalNum = 0;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		String selectQuery = "SELECT count(*) FROM "+tableName+" WHERE titleExpl = ? AND stnId=? AND tmFc=DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s') AND tmSeq=? AND lenExpl=?";
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setString(1, news.getTitleExpl());
			pstmt.setString(2, news.getStnId());
			pstmt.setString(3, convertDate(news.getTmFc()));
			pstmt.setInt(4, news.getTmSeq());
			pstmt.setInt(5, news.getLenExpl());
			
			
			resultSet = pstmt.executeQuery();
			while(resultSet.next()) {
				equalNum = resultSet.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equalNum;
	}
	
	
	public int insertWthrBrkNews(String tableName, WthrBrkNews news, String stnName) {
		
		int r = -1;
		PreparedStatement pstmt = null;
		
		String insertQuery = "INSERT INTO "+tableName+" (titleExpl, stnName, stnId, tmFc, tmSeq, refNum, newsExpl, lenExpl)"
				+ "VALUES (?, ?, ?, DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), ?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1,  news.getTitleExpl());
			pstmt.setString(2, stnName);
			pstmt.setString(3, news.getStnId());
			pstmt.setString(4, convertDate(news.getTmFc()));
			pstmt.setInt(5, news.getTmSeq());
			pstmt.setInt(6, news.getRefNum());
			pstmt.setString(7, news.getNewsExpl());
			pstmt.setInt(8, news.getLenExpl());
			
			System.out.println(pstmt.toString());
			r = pstmt.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
}
