package wthrPwn;

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;

public class WthrPwnListAPI extends BaseAPI {

	private String tableName = "WthrPwnList";
	private String resultCode, resultMsg, stnName;
	private WthrPwnDBController dbController = null;
	private ArrayList<WthrPwnList> pwnListArr;

	public WthrPwnListAPI(String operation, String key, String stnId, String fromTmFc, String toTmFc)
			throws IOException {
		super(operation, key, stnId, fromTmFc, toTmFc);
		// TODO Auto-generated constructor stub
		dbController = new WthrPwnDBController();
		pwnListArr = new ArrayList<>();
	}

	public void parseResponse() {

		Document doc = null;
		NodeList header = null;

		resultCode = null;
		resultMsg = null;

		NodeList nList = null;
		Node nNode;
		Element eElement;

		WthrPwnList pwnList;

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
					pwnList = new WthrPwnList();

					pwnList.setTitle(getTagValue("title", eElement));
					pwnList.setStnId(getTagValue("stnId", eElement));
					pwnList.setTmSeq(getTagValue("tmSeq", eElement));

					pwnList.setTmFc(getTagValue("tmFc", eElement));

					pwnListArr.add(pwnList);
				}
			}

		}
	}

	public int insertDB() {

		int result = -1;
		WthrPwnList pwnList;

		try {
			if (dbController.connect()) {
				System.out.println(String.format("pwnListArr size : %d", pwnListArr.size()));
				for (int arrIdx = 0; arrIdx < pwnListArr.size(); arrIdx++) {
					pwnList = pwnListArr.get(arrIdx);
					if (!isExist(pwnList)) {
						result = dbController.insertWthrPwnList(tableName, pwnList, stnName);
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

	public boolean isExist(WthrPwnList pwnList) {
		int count = dbController.getCountWthrPwnList(tableName, pwnList);
		return count != 0;
	}

	public void setStnName(String stnName) {
		this.stnName = stnName;
	}
}
