package wthrBrkNews;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;

public class WthrBrkNewsAPI extends BaseAPI  {

	private String tableName = "WthrBrkNews";
	private String resultCode, resultMsg, stnName;
	private WthrBrkNewsDBController dbController = null;
	private ArrayList<WthrBrkNews> brkNewsArr;
	
	public WthrBrkNewsAPI(String operation, String key, String stnId, String fromTmFc, String toTmFc) throws IOException {
		super(operation, key, stnId, fromTmFc, toTmFc);
		// TODO Auto-generated constructor stub
		dbController = new WthrBrkNewsDBController();
		brkNewsArr = new ArrayList<>();
	}

	public void parseResponse() {

		Document doc = null;
		NodeList header = null;

		resultCode = null;
		resultMsg = null;

		NodeList nList = null;
		WthrBrkNews brkNews;
		
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
					
					brkNews = new WthrBrkNews();

					brkNews.setStnId(getTagValue("stnId", eElement));
					brkNews.setTmSeq(getTagValue("tmSeq", eElement));
					brkNews.setTmFc(getTagValue("tmFc", eElement));
					brkNews.setRefNum(getTagValue("cnt", eElement));
					
					brkNews.setNewsExpl(getTagValue("ann", eElement));
					brkNews.setTitleExpl(brkNews.getNewsExpl().split("\n")[0]);

					brkNewsArr.add(brkNews);
					
				}
			}

		}

	}
	
	public int insertDB() {

		int result = -1;
		WthrBrkNews brkNews;

		try {
			if (dbController.connect()) {
				System.out.println(String.format("brkNewsArr size : %d", brkNewsArr.size()));
				for (int arrIdx = 0; arrIdx < brkNewsArr.size(); arrIdx++) {
					brkNews = brkNewsArr.get(arrIdx);
					if (!isExist(brkNews)) {
						result = dbController.insertWthrBrkNews(tableName, brkNews, stnName);
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

	public boolean isExist(WthrBrkNews brkNews) {
		int count = dbController.getCountWthrBrkNews(tableName, brkNews);
		return count != 0;
	}
	
	public void setStnName(String stnName) {
		this.stnName = stnName;
	}
}
