package occurrSpot;

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;

public class RedTideOccurSpotAPI extends BaseAPI {
	
	private String resultCode, resultMsg;
	private ArrayList<RedTideOccurSpot> occurSpotArr;

	private OccurSpotDBController dbController;


	public RedTideOccurSpotAPI(String operation, String key, int pageNo, int numOfRows, String rdate)
			throws IOException {
		super(operation, key, pageNo, numOfRows, rdate);
		// TODO Auto-generated constructor stub
		dbController = new OccurSpotDBController();
		occurSpotArr = new ArrayList<>();
	}

	public void parseResponse() {
		resultCode = null;
		resultMsg = null;
		
		NodeList header = doc.getElementsByTagName("header");
		
		resultCode = getTagValue("resultCode", (Element) header.item(0));
		resultMsg = getTagValue("resultMsg", (Element) header.item(0));
		System.out.println(String.format("resultCode : %s, resultMsg : %s", resultCode, resultMsg));
		
		NodeList nList = doc.getElementsByTagName("items");
		RedTideOccurSpot occurSpot;
		
		for(int idx = 0; idx < nList.getLength(); idx++){
			Node nNode = nList.item(idx);
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				
				Element eElement = (Element) nNode;
				occurSpot = new RedTideOccurSpot();

				occurSpot.setHjdCd(getTagValue("hjdCd", eElement));
				occurSpot.setHjdName(getTagValue("hjdnm", eElement));
				occurSpot.setProsWeek(getTagValue("prsw", eElement));
				
				occurSpot.setPros1Week(getTagValue("prs1w", eElement));
				occurSpot.setPros2Week(getTagValue("prs2w", eElement));
				occurSpot.setPros3Week(getTagValue("prs3w", eElement));
				occurSpot.setPros4Week(getTagValue("prs4w", eElement));

				occurSpotArr.add(occurSpot);
			}	
		}
		
	}


	public int insertDB() {

		int result = -1;
		RedTideOccurSpot occurSpot;

		try {
			if (dbController.connect()) {
				for (int arrIdx = 0; arrIdx < occurSpotArr.size(); arrIdx++) {
					occurSpot = occurSpotArr.get(arrIdx);
					if (!isExist(occurSpot)) {
						result = dbController.insertRedTideOccurSpot(occurSpot);
						System.out.println("Insert Result : " + result);
					} else {
						System.out.println("Exist Same Data");
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

	public boolean isExist(RedTideOccurSpot occurSpot) {
		int count = dbController.getCountRedTideOccurSpot(occurSpot);
		return count != 0;
	}
}
