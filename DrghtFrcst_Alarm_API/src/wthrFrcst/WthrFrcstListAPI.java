package wthrFrcst;

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;

public class WthrFrcstListAPI extends BaseAPI {

	private String resultCode, resultMsg;
	private ArrayList<WthrFrcstList> frcstInfoArr;

	private WthrFrcstDBController dbController;
	
	

	public WthrFrcstListAPI(String service, String operation, String key, String anlDt) throws IOException {
		super(service, operation, key, anlDt);
		// TODO Auto-generated constructor stub
		frcstInfoArr = new ArrayList<>();
		dbController = new WthrFrcstDBController();

	}

	public void parseResponse() {
		Document doc = null;
		NodeList header = null;

		resultCode = null;
		resultMsg = null;
		
		NodeList nList = null;
		WthrFrcstList frcstInfo;
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
					frcstInfo = new WthrFrcstList();

					frcstInfo.setAnldt(getTagValue("anldt", eElement));
					frcstInfo.setWthrCareZone(getTagValue("wetheratent", eElement));
					frcstInfo.setWthrAlrtZone(getTagValue("wetherbndry", eElement));
					frcstInfo.setWthrSeriusZone(getTagValue("wetherserius", eElement));
					frcstInfo.setWthrAttZone(getTagValue("wetherintrst", eElement));
					frcstInfo.setWthrMap(getTagValue("wethermap", eElement));

					frcstInfo.setWthrCare1M(getTagValue("wetheratent1m", eElement));
					frcstInfo.setWthrAlrt1M(getTagValue("wetherbndry1m", eElement));
					frcstInfo.setWthrSerius1M(getTagValue("wetherserius1m", eElement));
					frcstInfo.setWthrAtt1M(getTagValue("wetherintrst1m", eElement));
					frcstInfo.setWthrMap1M(getTagValue("wethermap1m", eElement));

					frcstInfo.setWthrCare2M(getTagValue("wetheratent2m", eElement));
					frcstInfo.setWthrAlrt2M(getTagValue("wetherbndry2m", eElement));
					frcstInfo.setWthrSerius2M(getTagValue("wetherserius2m", eElement));
					frcstInfo.setWthrAtt2M(getTagValue("wetherintrst2m", eElement));
					frcstInfo.setWthrMap2M(getTagValue("wethermap2m", eElement));

					frcstInfo.setWthrCare3M(getTagValue("wetheratent3m", eElement));
					frcstInfo.setWthrAlrt3M(getTagValue("wetherbndry3m", eElement));
					frcstInfo.setWthrSerius3M(getTagValue("wetherserius3m", eElement));
					frcstInfo.setWthrAtt3M(getTagValue("wetherintrst3m", eElement));
					frcstInfo.setWthrMap3M(getTagValue("wethermap3m", eElement));

					frcstInfoArr.add(frcstInfo);
				}
			}

		}
		
	}


	public int insertDB() {

		int result = -1;
		WthrFrcstList frcstInfo;

		try {
			if (dbController.connect()) {
				System.out.println(String.format("frcstInfoArr size : %d", frcstInfoArr.size()));
				for (int arrIdx = 0; arrIdx < frcstInfoArr.size(); arrIdx++) {
					frcstInfo = frcstInfoArr.get(arrIdx);
					if (!isEmptyValue(frcstInfo) && !isExist(frcstInfo)) {
						result = dbController.insertFcstInfoList(frcstInfo);
						System.out.println("Insert Result : " + result);
					} else if(!isEmptyValue(frcstInfo)) {
						System.out.println("Exist Same Data");
					} else {
						System.out.println("Empty Value");
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

	public boolean isExist(WthrFrcstList frcstInfo) {
		int count = dbController.getCountFcstInfoList(frcstInfo);
		return count != 0;
	}
	public boolean isEmptyValue(WthrFrcstList frcstInfo) {
		return frcstInfo.getAnldt() == null;
	}

}
