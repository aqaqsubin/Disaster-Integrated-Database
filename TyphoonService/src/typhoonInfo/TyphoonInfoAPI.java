package typhoonInfo;

import java.io.IOException;
import java.util.ArrayList;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;
import base.DBController;

public class TyphoonInfoAPI extends BaseAPI {

	private String tableName = "Typhoon_Info";
	private String resultCode, resultMsg;
	private ArrayList<TyphoonInfo> typhoonInfoArr;
	private DBController dbController;

	public TyphoonInfoAPI(String operation, String key, String startDate, String endDate)
			throws IOException {
		super(operation, key, startDate, endDate);
		typhoonInfoArr = new ArrayList<>();
		dbController = new DBController();
	}

	public ArrayList<TyphoonInfo> getTyphoonInfoArr() {
		return typhoonInfoArr;
	}

	public void setTyphoonInfoArr(ArrayList<TyphoonInfo> typhoonInfoArr) {
		this.typhoonInfoArr = typhoonInfoArr;
	}

	public void parseResponse() {
		Document doc = null;
		NodeList header = null;

		resultCode = null;
		resultMsg = null;

		NodeList nList = null;
		Node nNode;
		Element eElement;
		
		TyphoonInfo typInfo;

		for (int idx = 0; idx < docList.size(); idx++) {

			doc = docList.get(idx);

			header = doc.getElementsByTagName("header");

			resultCode = getTagValue("resultCode", (Element) header.item(0));
			resultMsg = getTagValue("resultMsg", (Element) header.item(0));
			System.out.println(String.format("resultCode : %s, resultMsg : %s", resultCode, resultMsg));

			nList = doc.getElementsByTagName("item");

			for (int ndIdx = 0; ndIdx < nList.getLength(); ndIdx++) {
				nNode = nList.item(ndIdx);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					eElement = (Element) nNode;
					typInfo = new TyphoonInfo();
					typInfo.setImgByte(image2ByteArray(getTagValue("img", eElement)));
					typInfo.setImgStr(getTagValue("img", eElement));
					typInfo.setTmFc(getTagValue("tmFc", eElement));
					
					typInfo.setTypSeq(getTagValue("typSeq", eElement));
					typInfo.setTmSeq(getTagValue("tmSeq", eElement));
					typInfo.setTypTm(getTagValue("typTm", eElement));
					
					typInfo.setTypLat(getTagValue("typLat", eElement));
					typInfo.setTypLon(getTagValue("typLon", eElement));
					
					typInfo.setTypLoc(getTagValue("typLoc", eElement));
					typInfo.setTypDir(getTagValue("typDir", eElement));

					typInfo.setTypSp(getTagValue("typSp", eElement));
					typInfo.setTypPs(getTagValue("typPs", eElement));
					typInfo.setTypWs(getTagValue("typWs", eElement));
					
					typInfo.setTypName(getTagValue("typName", eElement));
					typInfo.setTypEn(getTagValue("typEn", eElement));
					typInfo.setRem(getTagValue("rem", eElement));
					typInfo.setOther(getTagValue("other", eElement));


					typInfo.setTyp15(getTagValue("typ15", eElement));
					typInfo.setTyp15er(getTagValue("typ15er", eElement));
					typInfo.setTyp15ed(getTagValue("typ15ed", eElement));
					
					typInfo.setTyp25(getTagValue("typ25", eElement));
					typInfo.setTyp25er(getTagValue("typ25er", eElement));
					typInfo.setTyp25ed(getTagValue("typ25ed", eElement));
					
					typhoonInfoArr.add(typInfo);

				}
			}

		}
		
	}

	public int insertDB() {

		int result = -1;
		TyphoonInfo typInfo;

		try {
			if (dbController.connect()) {
				for(int arrIdx = 0;arrIdx < typhoonInfoArr.size();arrIdx++) {
					typInfo = typhoonInfoArr.get(arrIdx);
					if(!isExist(typInfo)){
						result = dbController.insertTyphoonInfo(tableName, typInfo);
						System.out.println("Insert Result : "+ result);
					}else
						System.out.println("Exist Same Data");
					
				}
				
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} finally {
			if(dbController.connect())
				dbController.closeConnection();
		}
		return result;
	}
	public int updateDBImg2Blob() {

		int result = -1;
		TyphoonInfo typInfo;

		try {
			if (dbController.connect()) {
				for(int arrIdx = 0;arrIdx < typhoonInfoArr.size();arrIdx++) {
					typInfo = typhoonInfoArr.get(arrIdx);
					if(isExist(typInfo)){
						result = dbController.updateTypInfoImgBlob(tableName, typInfo);
						System.out.println("Update Result : "+ result);
					}else
						System.out.println("Not Exist Data");
					
				}
				
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} finally {
			if(dbController.connect())
				dbController.closeConnection();
		}
		return result;
	}
	public int updateDBImg2Str() {

		int result = -1;
		TyphoonInfo typInfo;

		try {
			if (dbController.connect()) {
				for(int arrIdx = 0;arrIdx < typhoonInfoArr.size();arrIdx++) {
					typInfo = typhoonInfoArr.get(arrIdx);
					if(isExist(typInfo)){
						result = dbController.updateTypInfoImgStr(tableName, typInfo);
						System.out.println("Update Result : "+ result);
					}else
						System.out.println("Not Exist Data");
					
				}
				
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} finally {
			if(dbController.connect())
				dbController.closeConnection();
		}
		return result;
	}

	public boolean isExist(TyphoonInfo typInfo) {
		int count = dbController.getCountTyphoonInfo(tableName, typInfo);
		return count != 0;
	}

}