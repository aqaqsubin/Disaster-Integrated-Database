package occurrSpot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;

public class RedTideOccurSpotAPI extends BaseAPI {
	
	private String resultCode, resultMsg;
	private ArrayList<RedTideOccurSpot> occurSpotArr;

	private OccurSpotDBController dbController;
	
	private String sqlfilePath = "C:\\Users\\kisti_user\\Desktop\\DataON\\API\\Drght_Analysis\\tmpRedTideOccursSpot.sql";



	public RedTideOccurSpotAPI(String operation, String key, int pageNo, int numOfRows, String rdate)
			throws IOException {
		super(operation, key, pageNo, numOfRows, rdate);
		// TODO Auto-generated constructor stub
		dbController = new OccurSpotDBController();
		occurSpotArr = new ArrayList<>();
	}

	public void parseResponse() {
		
		Document doc = null;
		NodeList header = null;

		resultCode = null;
		resultMsg = null;

		NodeList nList = null;
		RedTideOccurSpot occurSpot;
		
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
					
					occurSpot = new RedTideOccurSpot();

					occurSpot.setSrCode(getTagValue("srcode", eElement));
					occurSpot.setCauseOrganism(getTagValue("dname", eElement));
					occurSpot.setOrgDensityMax(getTagValue("edensity", eElement));
					occurSpot.setOrgDensityMin(getTagValue("sdensity", eElement));
					
					occurSpot.setTempMax(getTagValue("ewt", eElement));
					occurSpot.setTempMin(getTagValue("swt", eElement));
					occurSpot.setSeaArea(getTagValue("oarea", eElement));
					occurSpot.setRegDate(getTagValue("regdate", eElement));

					occurSpotArr.add(occurSpot);
					
				}
			}

		}
		
	}

	public int insertDB() {

		int result = -1;
		RedTideOccurSpot occurSpot;

		FileWriter fw = null;
		File file = null;
		String query = "";
		try {
			if (dbController.connect()) {
				System.out.println(String.format("occurSpotArr size : %d", occurSpotArr.size()));

				file = new File(sqlfilePath);
				fw = new FileWriter(file);
				for (int arrIdx = 0; arrIdx < occurSpotArr.size(); arrIdx++) {
					occurSpot = occurSpotArr.get(arrIdx);
					
					query = dbController.getInsertQuery(occurSpot);
					fw.write(query + "\n");
				}
				fw.flush();
				fw.close();
				
				dbController.dumpRedTideOccur(file.getPath());

			}

		} catch (NumberFormatException | IOException e) {
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
