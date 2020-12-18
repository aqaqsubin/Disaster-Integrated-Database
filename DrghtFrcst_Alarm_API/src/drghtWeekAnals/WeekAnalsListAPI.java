package drghtWeekAnals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;

public class WeekAnalsListAPI extends BaseAPI {

	private String resultCode, resultMsg;
	private ArrayList<WeekAnalsList> analsInfoArr;

	private WeekAnalsDBController dbController;
	private String sqlfilePath = "C:\\Users\\kisti_user\\Desktop\\DataON\\API\\Drght_Analysis\\tmpWeekAnals.sql";

	public WeekAnalsListAPI(String service, String operation, String key, String hjdCd, String stDt, String edDt)
			throws IOException {
		super(service, operation, key, hjdCd, stDt, edDt);
		// TODO Auto-generated constructor stub
		dbController = new WeekAnalsDBController();
		analsInfoArr = new ArrayList<>();
	}

	public void parseResponse() {
		Document doc = null;
		NodeList header = null;

		resultCode = null;
		resultMsg = null;

		NodeList nList = null;
		WeekAnalsList weekAnalsInfo;
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
					weekAnalsInfo = new WeekAnalsList();

					weekAnalsInfo.setHjdCd(getTagValue("hjdcd", eElement));
					weekAnalsInfo.setHjdName(getTagValue("hjdnm", eElement));
					weekAnalsInfo.setProsWeek(getTagValue("prsw", eElement));

					weekAnalsInfo.setPros1Week(getTagValue("prs1w", eElement));
					weekAnalsInfo.setPros2Week(getTagValue("prs2w", eElement));
					weekAnalsInfo.setPros3Week(getTagValue("prs3w", eElement));
					weekAnalsInfo.setPros4Week(getTagValue("prs4w", eElement));

					analsInfoArr.add(weekAnalsInfo);
				}
			}

		}

	}

	public int insertDB() {

		int result = -1;
		WeekAnalsList weekAnalsInfo;

		FileWriter fw = null;
		File file = null;
		String query = "";
		try {
			if (dbController.connect()) {
				System.out.println(String.format("analsInfoArr size : %d", analsInfoArr.size()));

				file = new File(sqlfilePath);
				fw = new FileWriter(file);
				for (int arrIdx = 0; arrIdx < analsInfoArr.size(); arrIdx++) {
					weekAnalsInfo = analsInfoArr.get(arrIdx);

					query = dbController.getInsertQuery(weekAnalsInfo);
					fw.write(query + "\n");
				}
				fw.flush();
				fw.close();

				dbController.dumpAnalsList(file.getPath());

			}

		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		} finally {
			if (dbController.connect())
				dbController.closeConnection();
		}
		return result;
	}

	public boolean isExist(WeekAnalsList weekAnalsInfo) {
		int count = dbController.getCountWeekAnalsList(weekAnalsInfo);
		return count != 0;
	}

}
