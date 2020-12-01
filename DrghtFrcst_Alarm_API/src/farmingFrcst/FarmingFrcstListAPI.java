package farmingFrcst;

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;

public class FarmingFrcstListAPI extends BaseAPI {

	private String resultCode, resultMsg;
	private ArrayList<FarmingFrcstList> frcstInfoArr;

	private FarmingFrcstDBController dbController;

	public FarmingFrcstListAPI(String service, String operation, String key, String anlDt) throws IOException {
		super(service, operation, key, anlDt);
		// TODO Auto-generated constructor stub
		frcstInfoArr = new ArrayList<>();
		dbController = new FarmingFrcstDBController();

	}

	public void parseResponse() {
		Document doc = null;
		NodeList header = null;

		resultCode = null;
		resultMsg = null;

		NodeList nList = null;
		FarmingFrcstList frcstInfo;
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
					frcstInfo = new FarmingFrcstList();

					frcstInfo.setAnldt(getTagValue("anldt", eElement));
					frcstInfo.setFarminCareZone(getTagValue("farmngatent", eElement));
					frcstInfo.setFarminAlrtZone(getTagValue("farmngbndry", eElement));
					frcstInfo.setFarminSeriusZone(getTagValue("farmngserius", eElement));
					frcstInfo.setFarminAttZone(getTagValue("farmngintrst", eElement));
					frcstInfo.setFarminMap(getTagValue("farmngmap", eElement));

					frcstInfo.setFarminCare1M(getTagValue("farmngatent1m", eElement));
					frcstInfo.setFarminAlrt1M(getTagValue("farmngbndry1m", eElement));
					frcstInfo.setFarminSerius1M(getTagValue("farmngserius1m", eElement));
					frcstInfo.setFarminAtt1M(getTagValue("farmngintrst1m", eElement));
					frcstInfo.setFarminMap1M(getTagValue("farmngmap1m", eElement));

					frcstInfo.setFarminCare2M(getTagValue("farmngatent2m", eElement));
					frcstInfo.setFarminAlrt2M(getTagValue("farmngbndry2m", eElement));
					frcstInfo.setFarminSerius2M(getTagValue("farmngserius2m", eElement));
					frcstInfo.setFarminAtt2M(getTagValue("farmngintrst2m", eElement));
					frcstInfo.setFarminMap2M(getTagValue("farmngmap2m", eElement));

					frcstInfo.setFarminCare3M(getTagValue("farmngatent3m", eElement));
					frcstInfo.setFarminAlrt3M(getTagValue("farmngbndry3m", eElement));
					frcstInfo.setFarminSerius3M(getTagValue("farmngserius3m", eElement));
					frcstInfo.setFarminAtt3M(getTagValue("farmngintrst3m", eElement));
					frcstInfo.setFarminMap3M(getTagValue("farmngmap3m", eElement));

					frcstInfoArr.add(frcstInfo);
				}
			}

		}

	}

	public int insertDB() {

		int result = -1;
		FarmingFrcstList frcstInfo;

		try {
			if (dbController.connect()) {
				System.out.println(String.format("frcstInfoArr size : %d", frcstInfoArr.size()));
				for (int arrIdx = 0; arrIdx < frcstInfoArr.size(); arrIdx++) {
					frcstInfo = frcstInfoArr.get(arrIdx);
					if (!isEmptyValue(frcstInfo) && !isExist(frcstInfo)) {
						result = dbController.insertFcstInfoList(frcstInfo);
						System.out.println("Insert Result : " + result);
					} else if (!isEmptyValue(frcstInfo)) {
						System.out.println("Exist Same Data");
					} else {
						System.out.println("Empty Value");
					}

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

	public boolean isExist(FarmingFrcstList frcstInfo) {
		int count = dbController.getCountFcstInfoList(frcstInfo);
		return count != 0;
	}

	public boolean isEmptyValue(FarmingFrcstList frcstInfo) {
		return frcstInfo.getAnldt() == null;
	}

}
