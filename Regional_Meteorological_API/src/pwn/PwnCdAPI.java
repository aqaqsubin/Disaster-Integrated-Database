package pwn;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;

public class PwnCdAPI extends BaseAPI  {

	private String tableName = "PwnCd";
	private String resultCode, resultMsg, stnName;
	private PwnDBController dbController = null;
	private ArrayList<PwnCd> cdList;
	
	public PwnCdAPI(String service, String key, String stnId, String fromTmFc, String toTmFc, String areaCode, int warningType) throws IOException {
		super(service, key, stnId, fromTmFc, toTmFc, areaCode, warningType);
		// TODO Auto-generated constructor stub
		dbController = new PwnDBController();
		cdList = new ArrayList<>();
	}

	public void parseResponse() {

		Document doc = null;
		NodeList header = null;

		resultCode = null;
		resultMsg = null;

		NodeList nList = null;
		PwnCd cd;
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
					
					cd = new PwnCd();

					cd.setStnId(getTagValue("stnId", eElement));
					cd.setTmSeq(getTagValue("tmSeq", eElement));
					cd.setAreaCd(getTagValue("areaCode", eElement));
					cd.setAreaName(getTagValue("areaName", eElement));
					
					cd.setWarnVar(getTagValue("warnVar", eElement));
					cd.setTmFc(getTagValue("tmFc", eElement));
					cd.setWarnStress(getTagValue("warnStress", eElement));
					
					cd.setWrnCd(getTagValue("command", eElement));
					cd.setTmStart(getTagValue("startTime", eElement));
					cd.setTmEnd(getTagValue("endTime", eElement));
					cd.setTmEntireEnd(getTagValue("allEndTime", eElement));
					
					cd.setCancelCd(getTagValue("cancel", eElement));

					cdList.add(cd);

				}
			}

		}

	}
	
	public int insertDB() {

		int result = -1;
		PwnCd cd;

		try {
			if (dbController.connect()) {
				System.out.println(String.format("cdList size : %d", cdList.size()));

				for (int arrIdx = 0; arrIdx < cdList.size(); arrIdx++) {
					cd = cdList.get(arrIdx);
					if (!isExist(cd)) {
						result = dbController.insertPwnCd(tableName, cd, stnName);
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

	public boolean isExist(PwnCd cd) {
		int count = dbController.getCountPwnCd(tableName, cd);
		return count != 0;
	}

	public void setStnName(String stnName) {
		this.stnName = stnName;
	}
}
