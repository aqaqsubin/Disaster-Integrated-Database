package typhoonInfoList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;
import base.DBController;

public class TyphoonInfoListAPI extends BaseAPI {

	private String tableName = "Typhoon_Info_List";
	private String resultCode, resultMsg;
	private ArrayList<TyphoonInfoList> typInfoListArr;
	
	private DBController dbController = null;

	

	public TyphoonInfoListAPI(String operation, String key, String tmFc) throws IOException {
		super(operation, key, tmFc);
		// TODO Auto-generated constructor stub
		typInfoListArr = new ArrayList<>();
		dbController = new DBController();
	}

	public void parseResponse() {
		Document doc = null;
		NodeList header = null;

		resultCode = null;
		resultMsg = null;

		NodeList nList = null;
		Node nNode;
		Element eElement;

		TyphoonInfoList typInfoList;

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
					typInfoList = new TyphoonInfoList();

					typInfoList.setTmFc(getTagValue("announceTime", eElement));
					typInfoList.setTypSeq(getTagValue("typhoonSeq", eElement));
					typInfoList.setTmSeq(getTagValue("announceSeq", eElement));
					typInfoList.setTitle(getTagValue("title", eElement));

					typInfoListArr.add(typInfoList);
				}
			}

		}
	}

	public int insertDB() {

		int result = -1;
		TyphoonInfoList typInfoList;

		try {
			if (dbController.connect()) {
				for (int arrIdx = 0; arrIdx < typInfoListArr.size(); arrIdx++) {
					typInfoList = typInfoListArr.get(arrIdx);
					if (!isExist(typInfoList)) {
						result = dbController.insertTyphoonInfoList(tableName, typInfoList);
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

	public List<Integer> getTypSeqList() {
		List<Integer> typSeqList = new ArrayList<>();
		
		if (dbController.connect()) {
			typSeqList = dbController.getTypSeqList(tableName);
		}

		return typSeqList;
	}

	public boolean isExist(TyphoonInfoList typInfoList) {
		int count = dbController.getCountTyphoonInfoList(tableName, typInfoList);
		return count != 0;
	}

}
