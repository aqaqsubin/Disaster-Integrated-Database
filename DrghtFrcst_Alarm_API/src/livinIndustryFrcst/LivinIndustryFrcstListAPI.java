package livinIndustryFrcst;

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;

public class LivinIndustryFrcstListAPI extends BaseAPI {

	private String resultCode, resultMsg;
	private ArrayList<LivinIndustryFrcstList> frcstInfoArr;

	private LivinIndustryFrcstDBController dbController;
	
	public LivinIndustryFrcstListAPI(String service, String operation, String key, String anlDt) throws IOException {
		super(service, operation, key, anlDt);
		// TODO Auto-generated constructor stub
		frcstInfoArr = new ArrayList<>();
		dbController = new LivinIndustryFrcstDBController();

	}

	public void parseResponse() {
		Document doc = null;
		NodeList header = null;

		resultCode = null;
		resultMsg = null;

		NodeList nList = null;
		LivinIndustryFrcstList frcstInfo;
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
					frcstInfo = new LivinIndustryFrcstList();

					frcstInfo.setAnldt(getTagValue("anldt", eElement));
					frcstInfo.setLivinCareZone(getTagValue("lniwtratent", eElement));
					frcstInfo.setLivinAlrtZone(getTagValue("lniwtrbndry", eElement));
					frcstInfo.setLivinSeriusZone(getTagValue("lniwtrserius", eElement));
					frcstInfo.setLivinAttZone(getTagValue("lniwtrintrst", eElement));
					frcstInfo.setLivinMap(getTagValue("lniwtrmap", eElement));

					frcstInfo.setLivinCare1M(getTagValue("lniwtratent1m", eElement));
					frcstInfo.setLivinAlrt1M(getTagValue("lniwtrbndry1m", eElement));
					frcstInfo.setLivinSerius1M(getTagValue("lniwtrserius1m", eElement));
					frcstInfo.setLivinAtt1M(getTagValue("lniwtrintrst1m", eElement));
					frcstInfo.setLivinMap1M(getTagValue("lniwtrmap1m", eElement));

					frcstInfo.setLivinCare2M(getTagValue("lniwtratent2m", eElement));
					frcstInfo.setLivinAlrt2M(getTagValue("lniwtrbndry2m", eElement));
					frcstInfo.setLivinSerius2M(getTagValue("lniwtrserius2m", eElement));
					frcstInfo.setLivinAtt2M(getTagValue("lniwtrintrst2m", eElement));
					frcstInfo.setLivinMap2M(getTagValue("lniwtrmap2m", eElement));

					frcstInfo.setLivinCare3M(getTagValue("lniwtratent3m", eElement));
					frcstInfo.setLivinAlrt3M(getTagValue("lniwtrbndry3m", eElement));
					frcstInfo.setLivinSerius3M(getTagValue("lniwtrserius3m", eElement));
					frcstInfo.setLivinAtt3M(getTagValue("lniwtrintrst3m", eElement));
					frcstInfo.setLivinMap3M(getTagValue("lniwtrmap3m", eElement));

					frcstInfoArr.add(frcstInfo);
				}
			}

		}
		
	}


	public int insertDB() {

		int result = -1;
		LivinIndustryFrcstList frcstInfo;

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

	public boolean isExist(LivinIndustryFrcstList frcstInfo) {
		int count = dbController.getCountFcstInfoList(frcstInfo);
		return count != 0;
	}
	public boolean isEmptyValue(LivinIndustryFrcstList frcstInfo) {
		return frcstInfo.getAnldt() == null;
	}


}
