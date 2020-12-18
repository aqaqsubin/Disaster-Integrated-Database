package drghtMonthAnals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;

public class MonthAnalsListAPI extends BaseAPI {
	private String resultCode, resultMsg;
	private ArrayList<MonthAnalsList> analsInfoArr;

	private MonthAnalsDBController dbController;
	private String sqlfilePath = "C:\\Users\\kisti_user\\Desktop\\DataON\\API\\Drght_Analysis\\tmpMonthAnals.sql";

	public MonthAnalsListAPI(String service, String operation, String key, String hjdCd,
			String stDt, String edDt) throws IOException {
		super(service, operation, key, hjdCd, stDt, edDt);
		// TODO Auto-generated constructor stub
		dbController = new MonthAnalsDBController();
		analsInfoArr = new ArrayList<>();
	}

	public void parseResponse() {
		
		Document doc = null;
		NodeList header = null;

		resultCode = null;
		resultMsg = null;
		
		NodeList nList = null;
		MonthAnalsList monthAnalsInfo;
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
					monthAnalsInfo = new MonthAnalsList();

					monthAnalsInfo.setHjdCd(getTagValue("hjdcd", eElement));
					monthAnalsInfo.setHjdName(getTagValue("hjdnm", eElement));
					monthAnalsInfo.setProsMonth(getTagValue("prspctmt", eElement));

					monthAnalsInfo.setPros1Month(getTagValue("prs1mt", eElement));
					monthAnalsInfo.setPros2Month(getTagValue("prs2mt", eElement));
					monthAnalsInfo.setPros3Month(getTagValue("prs3mt", eElement));

					analsInfoArr.add(monthAnalsInfo);
				}
			}

		}

	}
	public int insertDB() {

		int result = -1;
		MonthAnalsList monthAnalsInfo;

		FileWriter fw = null;
		File file = null;
		String query = "";
		try {
			if (dbController.connect()) {
				System.out.println(String.format("analsInfoArr size : %d", analsInfoArr.size()));

				file = new File(sqlfilePath);
				fw = new FileWriter(file);
				for (int arrIdx = 0; arrIdx < analsInfoArr.size(); arrIdx++) {
					monthAnalsInfo = analsInfoArr.get(arrIdx);
					
					query = dbController.getInsertQuery(monthAnalsInfo);
					fw.write(query + "\n");
				}
				fw.flush();
				fw.close();
				
				dbController.dumpAnalsList(file.getPath());


			}

		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		} finally {
			if(dbController.connect())
				dbController.closeConnection();
		}
		return result;
	}

	public boolean isExist(MonthAnalsList monthAnalsInfo) {
		int count = dbController.getCountMonthAnalsList(monthAnalsInfo);
		return count != 0;
	}
}
