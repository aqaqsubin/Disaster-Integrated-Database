package base;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class BaseAPI {

	private List<String> urlList;
	protected List<Document> docList;

	protected String response;


	// Constructor
	public BaseAPI(String operation, String key, String stnId, String fromTmFc, String toTmFc) throws IOException {

		StringBuilder urlBuilder = null;

		urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/WthrWrnInfoService/" + operation);
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + key);
		urlBuilder.append("&" + URLEncoder.encode("stnId", "UTF-8") + "=" + URLEncoder.encode(stnId, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("fromTmFc", "UTF-8") + "=" + URLEncoder.encode(fromTmFc, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("toTmFc", "UTF-8") + "=" + URLEncoder.encode(toTmFc, "UTF-8"));

		int iterNum = getIterNum(urlBuilder.toString());
		urlList = new ArrayList<>();

		for (int pgIdx = 1; pgIdx <= iterNum; pgIdx++) {
			urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/WthrWrnInfoService/" + operation);
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + key);
			urlBuilder.append("&" + URLEncoder.encode("stnId", "UTF-8") + "=" + URLEncoder.encode(stnId, "UTF-8"));
			urlBuilder
					.append("&" + URLEncoder.encode("fromTmFc", "UTF-8") + "=" + URLEncoder.encode(fromTmFc, "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("toTmFc", "UTF-8") + "=" + URLEncoder.encode(toTmFc, "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
					+ URLEncoder.encode(Integer.toString(pgIdx), "UTF-8"));

			urlList.add(urlBuilder.toString());
		}

	}

	public BaseAPI(String operation, String key, String stnId, String fromTmFc, String toTmFc, String areaCode,
			int warningType) throws IOException {

		StringBuilder urlBuilder = null;

		urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/WthrWrnInfoService/" + operation);
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + key);
		urlBuilder.append("&" + URLEncoder.encode("stnId", "UTF-8") + "=" + URLEncoder.encode(stnId, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("fromTmFc", "UTF-8") + "=" + URLEncoder.encode(fromTmFc, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("toTmFc", "UTF-8") + "=" + URLEncoder.encode(toTmFc, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("areaCode", "UTF-8") + "=" + URLEncoder.encode(areaCode, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("warningType", "UTF-8") + "="
				+ URLEncoder.encode(Integer.toString(warningType), "UTF-8"));

		int iterNum = getIterNum(urlBuilder.toString());
		urlList = new ArrayList<>();

		for (int pgIdx = 1; pgIdx <= iterNum; pgIdx++) {
			urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/WthrWrnInfoService/" + operation);
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + key);
			urlBuilder.append("&" + URLEncoder.encode("stnId", "UTF-8") + "=" + URLEncoder.encode(stnId, "UTF-8"));
			urlBuilder
					.append("&" + URLEncoder.encode("fromTmFc", "UTF-8") + "=" + URLEncoder.encode(fromTmFc, "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("toTmFc", "UTF-8") + "=" + URLEncoder.encode(toTmFc, "UTF-8"));
			urlBuilder
					.append("&" + URLEncoder.encode("areaCode", "UTF-8") + "=" + URLEncoder.encode(areaCode, "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("warningType", "UTF-8") + "="
					+ URLEncoder.encode(Integer.toString(warningType), "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
					+ URLEncoder.encode(Integer.toString(pgIdx), "UTF-8"));

			urlList.add(urlBuilder.toString());
		}

	}

	public BaseAPI(String operation, String key) throws IOException {

		StringBuilder urlBuilder = null;

		urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/WthrWrnInfoService/" + operation);
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + key);

		int iterNum = getIterNum(urlBuilder.toString());
		urlList = new ArrayList<>();

		for (int pgIdx = 1; pgIdx <= iterNum; pgIdx++) {
			urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/WthrWrnInfoService/" + operation);
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + key);
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
					+ URLEncoder.encode(Integer.toString(pgIdx), "UTF-8"));

			urlList.add(urlBuilder.toString());
		}

	}

	private Document getDocument(String url) {

		DocumentBuilderFactory dbFactoty = null;
		DocumentBuilder dBuilder = null;
		Document tmpDoc = null;

		try {

			dbFactoty = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactoty.newDocumentBuilder();
			tmpDoc = dBuilder.parse(url);

			tmpDoc.getDocumentElement().normalize();

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}

		return tmpDoc;
	}

	private int getIterNum(String url) {

		int iterNum = 0;

		Document tmpDoc = getDocument(url);
		NodeList body = tmpDoc.getElementsByTagName("body");

		String totalCount = getTagValue("totalCount", (Element) body.item(0));

		iterNum = totalCount == null ? 0 : Integer.parseInt(totalCount);
		iterNum = iterNum % 10 == 0 ? iterNum / 10 : (iterNum / 10) + 1;

		System.out.println(String.format("totalCount : %s, iterNum : %d", totalCount, iterNum));

		return iterNum;
	}

	public void getResponse() throws IOException {

		Document tmpDoc = null;
		docList = new ArrayList<>();

		for (int idx = 0; idx < urlList.size(); idx++) {

			tmpDoc = getDocument(urlList.get(idx));
			docList.add(tmpDoc);
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

	public String convertDate(String tm) throws java.text.ParseException {
		try {
			return String.format("%s-%s-%s %s:%s:00", tm.substring(0, 4), tm.substring(4, 6), tm.substring(6, 8),
					tm.substring(8, 10), tm.substring(10, 12));
		} catch (StringIndexOutOfBoundsException e) {
			return null;
		}
	}

}