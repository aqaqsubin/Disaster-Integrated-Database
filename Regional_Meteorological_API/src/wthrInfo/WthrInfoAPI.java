package wthrInfo;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;

public class WthrInfoAPI extends BaseAPI {

	private String tableName = "WthrInfo";
	private String resultCode, resultMsg, stnName;
	private WthrInfoDBController dbController = null;
	private ArrayList<WthrInfo> infoArr;
	
	public WthrInfoAPI(String operation, String key, String stnId, String fromTmFc, String toTmFc) throws IOException {
		super(operation, key, stnId, fromTmFc, toTmFc);
		// TODO Auto-generated constructor stub
		dbController = new WthrInfoDBController();
		infoArr = new ArrayList<>();
	}

	public void parseResponse() {
		Document doc = null;
		NodeList header = null;

		resultCode = null;
		resultMsg = null;

		NodeList nList = null;
		WthrInfo info;
		
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
					info = new WthrInfo();

					info.setStnId(getTagValue("stnId", eElement));
					info.setTmSeq(getTagValue("tmSeq", eElement));
					info.setTmFc(getTagValue("tmFc", eElement));
					info.setWthrInfo(getTagValue("t1", eElement));

					infoArr.add(info);
				}
			}

		}

	}
	public int insertDB() {

		int result = -1;
		WthrInfo info;

		try {
			if (dbController.connect()) {
				System.out.println(String.format("infoArr size : %d", infoArr.size()));
				for (int arrIdx = 0; arrIdx < infoArr.size(); arrIdx++) {
					info = infoArr.get(arrIdx);
					if (!isExist(info)) {
						result = dbController.insertWthrInfo(tableName, info, stnName);
						System.out.println("Insert Result : " + result);
					} else
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

	public boolean isExist(WthrInfo info) {
		int count = dbController.getCountWthrInfo(tableName, info);
		return count != 0;
	}
	
	public void setStnName(String stnName) {
		this.stnName = stnName;
	}
}
