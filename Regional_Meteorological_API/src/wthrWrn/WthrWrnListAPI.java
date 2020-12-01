package wthrWrn;

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;

public class WthrWrnListAPI extends BaseAPI {

	private String tableName = "WthrWrnList";
	private String resultCode, resultMsg, stnName;
	private WthrWrnDBController dbController = null;
	private ArrayList<WthrWrnList> wrnListArr;

	public WthrWrnListAPI(String operation, String key, String stnId, String fromTmFc, String toTmFc)
			throws IOException {
		super(operation, key, stnId, fromTmFc, toTmFc);
		// TODO Auto-generated constructor stub
		dbController = new WthrWrnDBController();
		wrnListArr = new ArrayList<>();
	}

	public void parseResponse() {

		Document doc = null;
		NodeList header = null;

		resultCode = null;
		resultMsg = null;

		NodeList nList = null;
		Node nNode;
		Element eElement;

		WthrWrnList wrnList;

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
					wrnList = new WthrWrnList();

					wrnList.setTitle(getTagValue("title", eElement));
					wrnList.setStnId(getTagValue("stnId", eElement));
					wrnList.setTmSeq(getTagValue("tmSeq", eElement));
					wrnList.setTmFc(getTagValue("tmFc", eElement));

					wrnListArr.add(wrnList);

				}
			}

		}

	}

	public int insertDB() {

		int result = -1;
		WthrWrnList wrnList;

		try {
			if (dbController.connect()) {
				System.out.println(String.format("wrnListArr size : %d", wrnListArr.size()));
				for (int arrIdx = 0; arrIdx < wrnListArr.size(); arrIdx++) {
					wrnList = wrnListArr.get(arrIdx);
					if (!isExist(wrnList)) {
						result = dbController.insertWthrWrnList(tableName, wrnList, stnName);
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

	public boolean isExist(WthrWrnList wrnList) {
		int count = dbController.getCountWthrWrnList(tableName, wrnList);
		return count != 0;
	}

	public void setStnName(String stnName) {
		this.stnName = stnName;
	}

}
