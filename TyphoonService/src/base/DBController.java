package base;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;


import typhoonFcst.TyphoonFcst;
import typhoonInfo.TyphoonInfo;
import typhoonInfoList.TyphoonInfoList;

public class DBController extends JDBC {

	public int getCountTyphoonInfo(String tableName, TyphoonInfo typInfo) {

		int equalNum = 0;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		
		String tmFc = convertDate(typInfo.getTmFc());
		String selectQuery = "SELECT count(*) FROM " + tableName
				+ " WHERE tmFc=DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s') AND typSeq =? AND tmSeq =? ";
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setString(1, tmFc);
			pstmt.setInt(2, typInfo.getTypSeq());
			pstmt.setInt(3, typInfo.getTmSeq());

			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				equalNum = resultSet.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equalNum;
	}

	public int insertTyphoonInfo(String tableName, TyphoonInfo typInfo) {

		int r = -1;
		PreparedStatement pstmt = null;
		String tmFc = convertDate(typInfo.getTmFc());
		String typTm = convertDate(typInfo.getTypTm());

		String tmFcQueryStatement = (tmFc == null) ? "?" : "DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s')";
		String typTmQueryStatement = (typTm == null) ? "?" : "DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s')";

		String insertQuery = "INSERT INTO " + tableName
				+ " (imgStr, img, tmFc, typSeq, tmSeq, typTm, typLat, typLon, typLoc, typDir, typSp, typPs, typWs, typ15, typ15ed, typ15er, typ25, typ25ed, typ25er, typName, typEn, rem, other)"
				+ "VALUES ( ?, ?," + tmFcQueryStatement + ", ?, ?, " + typTmQueryStatement
				+ ", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			
			SerialBlob img = new SerialBlob(typInfo.getImgByte());
			
			pstmt = con.prepareStatement(insertQuery);
			
			pstmt.setString(1, typInfo.getImgStr());
			pstmt.setBlob(2, img);
			pstmt.setString(3, tmFc);
			pstmt.setInt(4, typInfo.getTypSeq());
			pstmt.setInt(5, typInfo.getTmSeq());
			pstmt.setString(6, typTm);
			pstmt.setDouble(7, typInfo.getTypLat());
			pstmt.setDouble(8, typInfo.getTypLon());
			pstmt.setString(9, typInfo.getTypLoc());
			pstmt.setString(10, typInfo.getTypDir());
			pstmt.setDouble(11, typInfo.getTypSp());
			pstmt.setDouble(12, typInfo.getTypPs());
			pstmt.setDouble(13, typInfo.getTypWs());
			pstmt.setDouble(14, typInfo.getTyp15());
			pstmt.setString(15, typInfo.getTyp15ed());
			pstmt.setDouble(16, typInfo.getTyp15er());
			pstmt.setDouble(17, typInfo.getTyp25());
			pstmt.setString(18, typInfo.getTyp25ed());
			pstmt.setDouble(19, typInfo.getTyp25er());
			pstmt.setString(20, typInfo.getTypName());
			pstmt.setString(21, typInfo.getTypEn());
			pstmt.setString(22, typInfo.getRem());
			pstmt.setString(23, typInfo.getOther());
			
			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;

	}
	
	public int updateTypInfoImgStr(String tableName, TyphoonInfo typInfo) {

		int r = -1;
		PreparedStatement pstmt = null;
		String tmFc = convertDate(typInfo.getTmFc());

		String insertQuery = "UPDATE " + tableName
				+ " SET img = ?"
				+ " WHERE tmFc=DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s') AND typSeq =? AND tmSeq =?";

		try {
			
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, typInfo.getImgStr());
			pstmt.setString(2, tmFc);
			pstmt.setInt(3, typInfo.getTypSeq());
			pstmt.setInt(4, typInfo.getTmSeq());
			
			System.out.println(pstmt.toString());

			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;

	}
	public int updateTypInfoImgBlob(String tableName, TyphoonInfo typInfo) {

		int r = -1;
		PreparedStatement pstmt = null;
		String tmFc = convertDate(typInfo.getTmFc());

		String insertQuery = "UPDATE " + tableName
				+ " SET img = ?"
				+ " WHERE tmFc=DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s') AND typSeq =? AND tmSeq =?";

		try {
			SerialBlob img = new SerialBlob(typInfo.getImgByte());
			
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setBlob(1, img);
			pstmt.setString(2, tmFc);
			pstmt.setInt(3, typInfo.getTypSeq());
			pstmt.setInt(4, typInfo.getTmSeq());
			
			System.out.println(pstmt.toString());

			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;

	}

	public int getCountTyphoonInfoList(String tableName, TyphoonInfoList typInfoList) {

		int equalNum = 0;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		
		String selectQuery = "SELECT count(*) FROM " + tableName + " WHERE title = ? AND typSeq =?";
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setString(1, typInfoList.getTitle());
			pstmt.setInt(2, typInfoList.getTypSeq());

			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				equalNum = resultSet.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equalNum;
	}

	public int insertTyphoonInfoList(String tableName, TyphoonInfoList typInfoList) {

		int r = -1;
		PreparedStatement pstmt = null;

		String insertQuery = "INSERT INTO " + tableName + " (tmFc, typSeq, tmSeq, title)"
				+ " VALUES (DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), ?, ?, ?)";

		try {
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, convertDate(typInfoList.getTmFc()));
			pstmt.setInt(2, typInfoList.getTypSeq());
			pstmt.setInt(3, typInfoList.getTmSeq());
			pstmt.setString(4, typInfoList.getTitle());

			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;

	}

	public int getCountTyphoonFcst(String tableName, TyphoonFcst fcst) {

		int equalNum = 0;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		String selectQuery = "SELECT count(*) FROM " + tableName
				+ " WHERE tmFc = DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s') AND typSeq =? AND tmAnalysis = DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s')";
		try {
			pstmt = con.prepareStatement(selectQuery);
			pstmt.setString(1, convertDate(fcst.getTmFc()));
			pstmt.setInt(2, fcst.getTypSeq());
			pstmt.setString(3, convertDate(fcst.getTmAnalysis()));

			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				equalNum = resultSet.getInt("count(*)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equalNum;
	}

	public int insertTyphoonFcst(String tableName,TyphoonFcst typFcst) {

		int r = -1;
		PreparedStatement pstmt = null;

		String insertQuery = "INSERT INTO " + tableName
				+ " (tmFc, typSeq, tmAnalysis, typLat, typLon, typLoc, typDir, typSp, typPs, typWs, typ70PrRad, typ15, typ15ed, typ15er, typ25, typ25ed, typ25er)"
				+ " VALUES (DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), ?, DATE_FORMAT(?, '%Y-%m-%d %H:%i:%s'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, convertDate(typFcst.getTmFc()));
			pstmt.setInt(2, typFcst.getTypSeq());
			pstmt.setString(3, convertDate(typFcst.getTmAnalysis()));
			pstmt.setDouble(4, typFcst.getTypLat());
			pstmt.setDouble(5, typFcst.getTypLon());
			pstmt.setString(6, typFcst.getTypLoc());
			pstmt.setString(7, typFcst.getTypDir());
			pstmt.setDouble(8, typFcst.getTypSp());
			pstmt.setDouble(9, typFcst.getTypPs());
			pstmt.setDouble(10, typFcst.getTypWs());
			pstmt.setDouble(11, typFcst.getTyp70PrRad());
			pstmt.setDouble(12, typFcst.getTyp15());
			pstmt.setString(13, typFcst.getTyp15ed());
			pstmt.setDouble(14, typFcst.getTyp15er());
			pstmt.setDouble(15, typFcst.getTyp25());
			pstmt.setString(16, typFcst.getTyp25ed());
			pstmt.setDouble(17, typFcst.getTyp25er());

			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;

	}
	public String convertDate(String tm) {
		try {
			return String.format("%s-%s-%s %s:%s:00", tm.substring(0, 4), tm.substring(4, 6), tm.substring(6, 8),
					tm.substring(8, 10), tm.substring(10, 12));
		}catch(StringIndexOutOfBoundsException e) {
			return null;
		}
	}

	public List<Integer> getTypSeqList(String tableName) {
		
		List<Integer> typSeqList = new ArrayList<>();
		
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		String selectQuery = "SELECT distinct(typSeq) FROM " + tableName;
		try {
			pstmt = con.prepareStatement(selectQuery);

			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				typSeqList.add(resultSet.getInt("typSeq"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return typSeqList;
	}

	
}