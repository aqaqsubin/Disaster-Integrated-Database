package wthrPwn;

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;

public class WthrPwnAPI extends BaseAPI {

	private String tableName = "WthrPwn";
	private String resultCode, resultMsg, stnName;
	private WthrPwnDBController dbController = null;
	private ArrayList<WthrPwn> pwnArr;

	public WthrPwnAPI(String operation, String key, String stnId, String fromTmFc, String toTmFc) throws IOException {
		super(operation, key, stnId, fromTmFc, toTmFc);
		// TODO Auto-generated constructor stub
		dbController = new WthrPwnDBController();
		pwnArr = new ArrayList<>();
	}

	public void parseResponse() {

		Document doc = null;
		NodeList header = null;

		resultCode = null;
		resultMsg = null;

		NodeList nList = null;
		Node nNode;
		Element eElement;

		WthrPwn pwn;

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
					pwn = new WthrPwn();

					pwn.setStnId(getTagValue("stnId", eElement));
					pwn.setTmSeq(getTagValue("tmSeq", eElement));
					pwn.setTmFc(getTagValue("tmFc", eElement));

					pwn.setRefNum(getTagValue("cnt", eElement));

					pwn.setPwnExpl(getTagValue("pwn", eElement));
					pwn.setOther(getTagValue("rem", eElement));

					pwnArr.add(pwn);
				}
			}

		}

	}

	public int insertDB() {

		int result = -1;
		WthrPwn pwn;

		try {
			if (dbController.connect()) {
				System.out.println(String.format("pwnArr size : %d", pwnArr.size()));
				for (int arrIdx = 0; arrIdx < pwnArr.size(); arrIdx++) {
					pwn = pwnArr.get(arrIdx);
					if (!isExist(pwn)) {
						result = dbController.insertWthrPwn(tableName, pwn, stnName);
						System.out.println("Insert Result : " + result);
					} else
						System.out.println("Exist Same Data");

				}

			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} finally {
			if (dbController.connect())
				dbController.closeConnection();
		}
		return result;
	}

	public boolean isExist(WthrPwn pwn) {
		int count = dbController.getCountWthrPwn(tableName, pwn);
		return count != 0;
	}

	public void setStnName(String stnName) {
		this.stnName = stnName;
	}
}
