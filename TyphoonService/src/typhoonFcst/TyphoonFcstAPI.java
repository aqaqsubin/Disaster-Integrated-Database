package typhoonFcst;

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import base.BaseAPI;
import base.DBController;

public class TyphoonFcstAPI extends BaseAPI{

	private String tableName = "Typhoon_Fcst";
	private String resultCode, resultMsg;
	private ArrayList<TyphoonFcst> typFcstList;
	private DBController dbController;
	
	public TyphoonFcstAPI(String operation, String key, String tmFc, int typSeq) throws IOException {
		super(operation, key, tmFc, typSeq);
		// TODO Auto-generated constructor stub
		typFcstList = new ArrayList<>();
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

		TyphoonFcst typFcst;
		
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
					typFcst = new TyphoonFcst();
					
					typFcst.setTmAnalysis(getTagValue("tm", eElement));
					typFcst.setTmFc(getTagValue("tmFc", eElement));
					
					typFcst.setTypSeq(getTagValue("seq", eElement));

					
					typFcst.setTypLat(getTagValue("lat", eElement));
					typFcst.setTypLon(getTagValue("lon", eElement));
					
					typFcst.setTypLoc(getTagValue("fcLocko", eElement));
					typFcst.setTypDir(getTagValue("dir", eElement));

					typFcst.setTypSp(getTagValue("sp", eElement));
					typFcst.setTypPs(getTagValue("ps", eElement));
					typFcst.setTypWs(getTagValue("ws", eElement));

					typFcst.setTyp70PrRad(getTagValue("radPr", eElement));
					
					typFcst.setTyp15(getTagValue("rad15", eElement));
					typFcst.setTyp15er(getTagValue("er15", eElement));
					typFcst.setTyp15ed(getTagValue("ed15", eElement));
					
					typFcst.setTyp25(getTagValue("rad25", eElement));
					typFcst.setTyp25er(getTagValue("er25", eElement));
					typFcst.setTyp25ed(getTagValue("ed25", eElement));

					typFcstList.add(typFcst);
				}
			}

		}
		
	}
	public int insertDB() {

		int result = -1;
		TyphoonFcst typFcst;

		try {
			if (dbController.connect()) {
				for(int arrIdx = 0;arrIdx < typFcstList.size();arrIdx++) {
					typFcst = typFcstList.get(arrIdx);
					if(!isExist(typFcst)){
						result = dbController.insertTyphoonFcst(tableName, typFcst);
						System.out.println("Insert Result : "+ result);
					}else
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

	public boolean isExist(TyphoonFcst typFcst) {

		int count = dbController.getCountTyphoonFcst(tableName, typFcst);
		return count != 0;
	}
}
