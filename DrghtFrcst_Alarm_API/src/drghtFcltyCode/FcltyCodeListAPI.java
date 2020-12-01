package drghtFcltyCode;

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;

public class FcltyCodeListAPI extends BaseAPI{
	
	private String resultCode, resultMsg;
	private ArrayList<FcltyCodeList> fcltyCdArr;
	

	private FcltyCodeDBController dbController;

	public FcltyCodeListAPI(String service, String operation, String key, long fcltyDivCode)
			throws IOException {
		super(service, operation, key, fcltyDivCode);
		// TODO Auto-generated constructor stub
		dbController = new FcltyCodeDBController();
		fcltyCdArr = new ArrayList<>();
	}
	

	

	public void parseResponse() {
		
		Document doc = null;
		NodeList header = null;

		resultCode = null;
		resultMsg = null;
		
		NodeList nList = null;
		FcltyCodeList fcltyCd;
		Node nNode;
		Element eElement;
		
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
					fcltyCd = new FcltyCodeList();

					fcltyCd.setFcltyName(getTagValue("cdnm", eElement));
					fcltyCd.setFcltyCode(getTagValue("cd", eElement));
					fcltyCd.setFcltyDivCode(getTagValue("dv", eElement));
					
					System.out.println(String.format("Name : %s, Code : %s", getTagValue("cdnm", eElement), getTagValue("cd", eElement)));

					fcltyCdArr.add(fcltyCd);
				}
			}

		}
		
	}


	public int insertDB() {

		int result = -1;
		FcltyCodeList fcltyCd;

		try {
			if (dbController.connect()) {
				System.out.println(String.format("fcltyCdArr size : %d", fcltyCdArr.size()));
				for (int arrIdx = 0; arrIdx < fcltyCdArr.size(); arrIdx++) {
					fcltyCd = fcltyCdArr.get(arrIdx);
					if (!isExist(fcltyCd)) {
						result = dbController.insertFcltyCodeList(fcltyCd);
						System.out.println("Insert Result : " + result);
					} else {
						System.out.println("Exist Same Data");
					} 

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

	public boolean isExist(FcltyCodeList fcltyCd) {
		int count = dbController.getCountFcltyCodeList(fcltyCd);
		return count != 0;
	}
}
