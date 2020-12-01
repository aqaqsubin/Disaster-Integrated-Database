package wthrWrn;

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;

public class WthrWrnMsgAPI extends BaseAPI {

	private String tableName = "WthrWrnMsg";
	private String resultCode, resultMsg, stnName;

	private WthrWrnDBController dbController = null;
	private ArrayList<WthrWrnMsg> wrnMsgArr;

	public WthrWrnMsgAPI(String operation, String key, String stnId, String fromTmFc, String toTmFc)
			throws IOException {
		super(operation, key, stnId, fromTmFc, toTmFc);
		// TODO Auto-generated constructor stub
		dbController = new WthrWrnDBController();
		wrnMsgArr = new ArrayList<>();
	}

	public void parseResponse() {

		Document doc = null;
		NodeList header = null;

		resultCode = null;
		resultMsg = null;

		NodeList nList = null;
		Node nNode;
		Element eElement;

		WthrWrnMsg wrnMsg;

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
					wrnMsg = new WthrWrnMsg();

					wrnMsg.setStnId(getTagValue("stnId", eElement));
					wrnMsg.setTmFc(getTagValue("tmFc", eElement));
					wrnMsg.setTmSeq(getTagValue("tmSeq", eElement));
					wrnMsg.setWrnCd(getTagValue("warFc", eElement));

					wrnMsg.setTitle(getTagValue("t1", eElement));
					wrnMsg.setZoneReport(getTagValue("t2", eElement));
					wrnMsg.setTmReport(getTagValue("t3", eElement));
					wrnMsg.setExpl(getTagValue("t4", eElement));
					System.out.println("Expl : "+wrnMsg.getExpl());

					wrnMsg.setTmWrnReport(getTagValue("t5", eElement));
					wrnMsg.setWrnReport(getTagValue("t6", eElement));
					wrnMsg.setPreWrnReport(getTagValue("t7", eElement));
					wrnMsg.setOther(getTagValue("other", eElement));

					wrnMsgArr.add(wrnMsg);
				}
			}

		}

	}

	public int insertDB() {

		int result = -1;
		WthrWrnMsg wrnMsg;

		try {
			if (dbController.connect()) {
				System.out.println(String.format("wrnMsgArr size : %d", wrnMsgArr.size()));

				for (int arrIdx = 0; arrIdx < wrnMsgArr.size(); arrIdx++) {
					
					wrnMsg = wrnMsgArr.get(arrIdx);
					if (!isExist(wrnMsg)) {
						result = dbController.insertWthrWrnMsg(tableName, wrnMsg, stnName);
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

	public boolean isExist(WthrWrnMsg wrnMsg) {
		int count = dbController.getCountWthrWrnMsg(tableName, wrnMsg);
		return count != 0;
	}

	public void setStnName(String stnName) {
		this.stnName = stnName;
	}

}
