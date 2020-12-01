package occurrInfo;

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;

public class RedTideOccurInfoAPI extends BaseAPI {
	
	private String resultCode, resultMsg;
	private ArrayList<RedTideOccurInfo> occurInfoArr;

	private OccurInfoDBController dbController;


	public RedTideOccurInfoAPI(String operation, String key, int pageNo, int numOfRows, String rdate)
			throws IOException {
		super(operation, key, pageNo, numOfRows, rdate);
		// TODO Auto-generated constructor stub
	}
	
	public void parseResponse() {
		resultCode = null;
		resultMsg = null;
		
		NodeList header = doc.getElementsByTagName("header");
		
		resultCode = getTagValue("resultCode", (Element) header.item(0));
		resultMsg = getTagValue("resultMsg", (Element) header.item(0));
		System.out.println(String.format("resultCode : %s, resultMsg : %s", resultCode, resultMsg));
		
		NodeList nList = doc.getElementsByTagName("items");
		RedTideOccurInfo occurInfo;
		
		for(int idx = 0; idx < nList.getLength(); idx++){
			Node nNode = nList.item(idx);
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				
				Element eElement = (Element) nNode;
				occurInfo = new RedTideOccurInfo();

				occurInfo.setHjdCd(getTagValue("hjdCd", eElement));
				occurInfo.setHjdName(getTagValue("hjdnm", eElement));
				occurInfo.setProsWeek(getTagValue("prsw", eElement));
				
				occurInfo.setPros1Week(getTagValue("prs1w", eElement));
				occurInfo.setPros2Week(getTagValue("prs2w", eElement));
				occurInfo.setPros3Week(getTagValue("prs3w", eElement));
				occurInfo.setPros4Week(getTagValue("prs4w", eElement));

				occurInfoArr.add(occurInfo);
			}	
		}
		
	}


	public int insertDB() {

		int result = -1;
		RedTideOccurInfo occurInfo;

		try {
			if (dbController.connect()) {
				for (int arrIdx = 0; arrIdx < occurInfoArr.size(); arrIdx++) {
					occurInfo = occurInfoArr.get(arrIdx);
					if (!isExist(occurInfo)) {
						result = dbController.insertRedTideOccurInfo(occurInfo);
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

	public boolean isExist(RedTideOccurInfo occurInfo) {
		int count = dbController.getCountRedTideOccurInfo(occurInfo);
		return count != 0;
	}
}
