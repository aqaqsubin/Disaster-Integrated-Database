package wthrBrkNews;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;

public class WthrBrkNewsListAPI extends BaseAPI{
	
	private String tableName = "WthrBrkNewsList";
	private String resultCode, resultMsg, stnName;
	private WthrBrkNewsDBController dbController = null;
	private ArrayList<WthrBrkNewsList> brkNewsListArr;
	
	public WthrBrkNewsListAPI(String operation, String key, String stnId, String fromTmFc, String toTmFc) throws IOException {
		super(operation, key, stnId, fromTmFc, toTmFc);
		// TODO Auto-generated constructor stub
		dbController = new WthrBrkNewsDBController();
		brkNewsListArr = new ArrayList<>();
	}
	
	
	public void parseResponse() {
		
		Document doc = null;
		NodeList header = null;

		resultCode = null;
		resultMsg = null;

		NodeList nList = null;
		WthrBrkNewsList brkNewsList;
		
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
					brkNewsList = new WthrBrkNewsList();

					brkNewsList.setTitle(getTagValue("title", eElement));
					brkNewsList.setStnId(getTagValue("stnId", eElement));
					brkNewsList.setTmSeq((getTagValue("tmSeq", eElement)));
					brkNewsList.setTmFc((getTagValue("tmFc", eElement)));

					brkNewsListArr.add(brkNewsList);
				}
			}

		}

	}
	
	public int insertDB() {

		int result = -1;
		WthrBrkNewsList brkNewsList;

		try {
			if (dbController.connect()) {
				System.out.println(String.format("brkNewsListArr size : %d", brkNewsListArr.size()));
				for (int arrIdx = 0; arrIdx < brkNewsListArr.size(); arrIdx++) {
					brkNewsList = brkNewsListArr.get(arrIdx);
					if (!isExist(brkNewsList)) {
						result = dbController.insertWthrBrkNewsList(tableName, brkNewsList, stnName);
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

	public boolean isExist(WthrBrkNewsList brkNewsList) {
		int count = dbController.getCountWthrBrkNewsList(tableName, brkNewsList);
		return count != 0;
	}
	
	public void setStnName(String stnName) {
		this.stnName = stnName;
	}
}
