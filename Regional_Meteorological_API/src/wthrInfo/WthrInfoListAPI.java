package wthrInfo;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;

public class WthrInfoListAPI extends BaseAPI {
	
	private String tableName = "WthrInfoList";
	private String resultCode, resultMsg, stnName;
	private WthrInfoDBController dbController = null;
	private ArrayList<WthrInfoList> infoListArr;
	
	public WthrInfoListAPI(String operation, String key, String stnId, String fromTmFc, String toTmFc) throws IOException {
		super(operation, key, stnId, fromTmFc, toTmFc);
		// TODO Auto-generated constructor stub
		dbController = new WthrInfoDBController();
		infoListArr = new ArrayList<>();
	}
	
	public void parseResponse() {
		
		Document doc = null;
		NodeList header = null;

		resultCode = null;
		resultMsg = null;

		NodeList nList = null;
		WthrInfoList infoList;
		
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
					infoList = new WthrInfoList();

					infoList.setTitle(getTagValue("title", eElement));
					infoList.setStnId(getTagValue("stnId", eElement));
					infoList.setTmSeq(getTagValue("tmSeq", eElement));
					infoList.setTmFc(getTagValue("tmFc", eElement));

					infoListArr.add(infoList);
				}
			}

		}

	}
	
	public int insertDB() {

		int result = -1;
		WthrInfoList infoList;

		try {
			if (dbController.connect()) {
				System.out.println(String.format("infoListArr size : %d", infoListArr.size()));
				for (int arrIdx = 0; arrIdx < infoListArr.size(); arrIdx++) {
					infoList = infoListArr.get(arrIdx);
					if (!isExist(infoList)) {
						result = dbController.insertWthrInfoList(tableName, infoList, stnName);
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

	public boolean isExist(WthrInfoList infoList) {
		int count = dbController.getCountWthrInfoList(tableName, infoList);
		return count != 0;
	}
	
	public void setStnName(String stnName) {
		this.stnName = stnName;
	}
}
