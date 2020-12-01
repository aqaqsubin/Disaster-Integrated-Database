package base;

import java.io.IOException;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class BaseAPI {
	private String strURL;
	protected Document doc;
	
	protected String response;
	
	JDBC db;

	public BaseAPI(String operation, String key, int pageNo, int numOfRows, String rdate) throws IOException {
		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.nfrdi.re.kr/openapi/service/OceanProblemService/"+ operation);
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
				+ "="+key);
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
				+ URLEncoder.encode(Integer.toString(pageNo), "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
				+ URLEncoder.encode(Integer.toString(numOfRows), "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("rdate", "UTF-8") + "=" + URLEncoder.encode(rdate, "UTF-8"));

		strURL = urlBuilder.toString();
		System.out.println(strURL);

		db = new JDBC();
	}

	public void getResponse() throws IOException {
		
		DocumentBuilderFactory dbFactoty = null;
		DocumentBuilder dBuilder = null;
		doc = null;

		try {

			dbFactoty = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactoty.newDocumentBuilder();
			doc = dBuilder.parse(strURL);

			doc.getDocumentElement().normalize();

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	protected String getTagValue(String tag, Element eElement) {
		try {
			NodeList nlList = (NodeList) eElement.getElementsByTagName(tag).item(0).getChildNodes();
			Node nValue = (Node) nlList.item(0);

			return nValue.getNodeValue();
			
		} catch (NullPointerException e) {

			return null;
		}

	}

}
