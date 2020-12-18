package occurrInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;

public class RedTideOccurInfoAPI extends BaseAPI {

	private String resultCode, resultMsg;
	private ArrayList<RedTideOccurInfo> occurInfoArr;

	private OccurInfoDBController dbController;

	private String sqlfilePath = "C:\\Users\\kisti_user\\Desktop\\DataON\\API\\Drght_Analysis\\tmpRedTideOccursInfo.sql";

	public RedTideOccurInfoAPI(String operation, String key, int pageNo, int numOfRows, String rdate)
			throws IOException {
		super(operation, key, pageNo, numOfRows, rdate);
		// TODO Auto-generated constructor stub

		dbController = new OccurInfoDBController();
		occurInfoArr = new ArrayList<>();
	}

	public void parseResponse() {

		Document doc = null;
		NodeList header = null;

		resultCode = null;
		resultMsg = null;

		NodeList nList = null;
		RedTideOccurInfo occurInfo;

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

					occurInfo = new RedTideOccurInfo();

					occurInfo.setTitle(getTagValue("title", eElement));
					occurInfo.setSrCode(getTagValue("srcode", eElement));
					occurInfo.setAfterView(getTagValue("aview", eElement));
					occurInfo.setEtc(getTagValue("etc", eElement));

					occurInfo.setState(getTagValue("pstate", eElement));
					occurInfo.setRegDate(getTagValue("rdate", eElement));
					occurInfo.setReport(getTagValue("sreport", eElement));

					occurInfoArr.add(occurInfo);

				}
			}

		}

	}

	public int insertDB() {

		int result = -1;
		RedTideOccurInfo occurInfo;

		FileWriter fw = null;
		File file = null;
		String query = "";
		try {
			if (dbController.connect()) {
				System.out.println(String.format("occurInfoArr size : %d", occurInfoArr.size()));

				file = new File(sqlfilePath);
				fw = new FileWriter(file);
				for (int arrIdx = 0; arrIdx < occurInfoArr.size(); arrIdx++) {
					occurInfo = occurInfoArr.get(arrIdx);

					query = dbController.getInsertQuery(occurInfo);
					fw.write(query + "\n");
				}
				fw.flush();
				fw.close();

				dbController.dumpRedTideOccur(file.getPath());

			}

		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		} finally {
			if (dbController.connect())
				dbController.closeConnection();
		}
		return result;
	}

	public boolean isExist(RedTideOccurInfo occurInfo) {
		int count = dbController.getCountRedTideOccurInfo(occurInfo);
		return count != 0;
	}
}
