package pwn;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;

public class PwnStatusAPI extends BaseAPI  {

	private String tableName = "PwnStatus";
	private String resultCode, resultMsg;
	private PwnDBController dbController = null;
	private ArrayList<PwnStatus> statArr;
	
	public PwnStatusAPI(String service, String key) throws IOException {
		super(service, key);
		// TODO Auto-generated constructor stub
		dbController = new PwnDBController();
		statArr = new ArrayList<>();
	}
	
	public void parseResponse() {

		Document doc = null;
		NodeList header = null;

		resultCode = null;
		resultMsg = null;

		NodeList nList = null;
		PwnStatus stat;
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
					
					
					stat = new PwnStatus();

					stat.setTmSeq(getTagValue("tmSeq", eElement));
					stat.setTmFc(getTagValue("tmFc", eElement));
					stat.setTmEffect(getTagValue("tmEf", eElement));
					stat.setEffectExpl(getTagValue("t6", eElement));
					
					stat.setPreEffectExpl(getTagValue("t7", eElement));
					stat.setOther(getTagValue("other", eElement));
					stat.setTitleExpl(stat.getEffectExpl().split("\n")[0]);

					statArr.add(stat);

				}
			}

		}

	}
	
	public int insertDB() {

		int result = -1;
		PwnStatus stat;

		try {
			if (dbController.connect()) {
				System.out.println(String.format("statList size : %d", statArr.size()));
				for (int arrIdx = 0; arrIdx < statArr.size(); arrIdx++) {
					stat = statArr.get(arrIdx);
					if (!isExist(stat)) {
						result = dbController.insertPwnStatus(tableName, stat);
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

	public boolean isExist(PwnStatus stat) {
		int count = dbController.getCountPwnStatus(tableName, stat);
		return count != 0;
	}

}
