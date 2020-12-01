package fcstZone;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;

public class FcstZoneCdAPI extends BaseAPI {

	private String resultCode, resultMsg;
	private ArrayList<FcstZoneCd> cdList;
	private String tableName = "FcstZone";
	private FcstZoneDBController dbController;

	public FcstZoneCdAPI(String operation, String regId) throws IOException {
		super(operation, regId);
		// TODO Auto-generated constructor stub
		cdList = new ArrayList<>();
		dbController = new FcstZoneDBController();
	}

	public void parseResponse() {
		
		Document doc = null;
		NodeList header = null;

		resultCode = null;
		resultMsg = null;
		
		NodeList nList = null;
		FcstZoneCd cd;
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
					cd = new FcstZoneCd();
					
					cd.setRegId(getTagValue("regId", eElement));
					cd.setTmEd(getTagValue("tmEd", eElement));
					cd.setTmSt(getTagValue("tmSt", eElement));
					cd.setRegName(getTagValue("regName", eElement));
					cd.setRegEn(getTagValue("regEn", eElement));
					cd.setRegSp(getTagValue("regSp", eElement));
					cd.setLat(getTagValue("lat", eElement));
					cd.setLon(getTagValue("lon", eElement));
					cd.setHt(getTagValue("ht", eElement));
					cd.setRegUp(getTagValue("regUp", eElement));
					cd.setStnWn(getTagValue("stnWn", eElement));
					cd.setStnF3(getTagValue("stnF3", eElement));
					cd.setStnFd(getTagValue("stnFw", eElement));
					cd.setStnFw(getTagValue("seq", eElement));

					cdList.add(cd);
				}
			}

		}

	}
	public int insertDB() {

		int result = -1;
		FcstZoneCd cd;

		try {
			if (dbController.connect()) {
				System.out.println(String.format("cdList size : %d", cdList.size()));
				for (int arrIdx = 0; arrIdx < cdList.size(); arrIdx++) {
					cd = cdList.get(arrIdx);
					if (!isExist(cd)) {
						result = dbController.insertFcstZone(tableName, cd);
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

	public boolean isExist(FcstZoneCd cd) {
		int count = dbController.getCountFcstZone(tableName, cd);
		return count != 0;
	}

	


}