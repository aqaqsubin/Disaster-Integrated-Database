package base;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pwn.PwnCdAPI;
import pwn.PwnStatusAPI;
import wthrBrkNews.WthrBrkNewsAPI;
import wthrBrkNews.WthrBrkNewsListAPI;
import wthrInfo.WthrInfoAPI;
import wthrInfo.WthrInfoListAPI;
import wthrPwn.WthrPwnAPI;
import wthrPwn.WthrPwnListAPI;
import wthrWrn.WthrWrnListAPI;
import wthrWrn.WthrWrnMsgAPI;

public class WthrWrnInfoAPIController {
	private List<List<String>> zoneCdList;
	private List<List<String>> entireWrnZoneCdList;

	private Map<String, List<List<String>>> WrnZoneCdMap;
	private int WrnCode = 12;
	
	private String serviceKey = "";


	public WthrWrnInfoAPIController(String zoneFilePath, String wrnZoneFilePath) {
		ZoneCodeFileController fileController = new ZoneCodeFileController();

		this.zoneCdList = fileController.getZoneCdList(zoneFilePath);
		this.entireWrnZoneCdList = fileController.getZoneCdList(wrnZoneFilePath);
		this.WrnZoneCdMap = fileController.getSpecialZoneCdList(wrnZoneFilePath);

	}

	public int getWthrWrnListAPI(Calendar from_dttm, Calendar to_dttm) {

		WthrWrnListAPI wrnList = null;
		int r = -1;
		for (int i = 0; i < zoneCdList.size(); i++) {

			try {
				System.out.println("구역 명 : " + zoneCdList.get(i).get(1));
				wrnList = new WthrWrnListAPI("getWthrWrnList", serviceKey, zoneCdList.get(i).get(0), cal2Str(from_dttm),
						cal2Str(to_dttm));
				wrnList.setStnName(zoneCdList.get(i).get(1));

				wrnList.getResponse();
				wrnList.parseResponse();

				r = wrnList.insertDB();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		return r;
	}

	public int getWthrWrnMsg(Calendar from_dttm, Calendar to_dttm) {

		WthrWrnMsgAPI wrnMsg = null;

		int r = -1;
		for (int i = 0; i < zoneCdList.size(); i++) {

			try {
				System.out.println("구역 명 : " + zoneCdList.get(i).get(1));
				wrnMsg = new WthrWrnMsgAPI("getWthrWrnMsg", serviceKey, zoneCdList.get(i).get(0), cal2Str(from_dttm),
						cal2Str(to_dttm));
				wrnMsg.setStnName(zoneCdList.get(i).get(1));

				wrnMsg.getResponse();
				wrnMsg.parseResponse();

				r = wrnMsg.insertDB();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} 
		}
		return r;
	}

	public int getWthrPwnListAPI(Calendar from_dttm, Calendar to_dttm) {

		WthrPwnListAPI pwnList = null;
		int r = -1;
		for (int i = 0; i < zoneCdList.size(); i++) {

			try {
				System.out.println("구역 명 : " + zoneCdList.get(i).get(1));
				pwnList = new WthrPwnListAPI("getWthrPwnList", serviceKey, zoneCdList.get(i).get(0), cal2Str(from_dttm),
						cal2Str(to_dttm));
				pwnList.setStnName(zoneCdList.get(i).get(1));

				pwnList.getResponse();
				pwnList.parseResponse();

				r = pwnList.insertDB();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} 
		}
		return r;
	}

	public int getWthrPwnAPI(Calendar from_dttm, Calendar to_dttm) {

		WthrPwnAPI pwn = null;
		int r = -1;
		for (int i = 0; i < zoneCdList.size(); i++) {

			try {
				System.out.println("구역 명 : " + zoneCdList.get(i).get(1));
				pwn = new WthrPwnAPI("getWthrPwn", serviceKey, zoneCdList.get(i).get(0), cal2Str(from_dttm),
						cal2Str(to_dttm));
				pwn.setStnName(zoneCdList.get(i).get(1));

				pwn.getResponse();
				pwn.parseResponse();

				r = pwn.insertDB();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} 
		}
		return r;
	}

	public int getWthrInfoListAPI(Calendar from_dttm, Calendar to_dttm) {

		WthrInfoListAPI wthrInfoList = null;
		int r = -1;
		for (int i = 0; i < zoneCdList.size(); i++) {

			try {
				System.out.println("구역 명 : " + zoneCdList.get(i).get(1));
				wthrInfoList = new WthrInfoListAPI("getWthrInfoList", serviceKey, zoneCdList.get(i).get(0),
						cal2Str(from_dttm), cal2Str(to_dttm));
				wthrInfoList.setStnName(zoneCdList.get(i).get(1));

				wthrInfoList.getResponse();
				wthrInfoList.parseResponse();

				r = wthrInfoList.insertDB();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			} 
		}
		return r;
	}

	public int getWthrInfoAPI(Calendar from_dttm, Calendar to_dttm) {

		WthrInfoAPI wthrInfo = null;
		int r = -1;
		for (int i = 0; i < zoneCdList.size(); i++) {

			try {
				System.out.println("구역 명 : " + zoneCdList.get(i).get(1));
				wthrInfo = new WthrInfoAPI("getWthrInfo", serviceKey, zoneCdList.get(i).get(0), cal2Str(from_dttm),
						cal2Str(to_dttm));
				wthrInfo.setStnName(zoneCdList.get(i).get(1));

				wthrInfo.getResponse();
				wthrInfo.parseResponse();

				r = wthrInfo.insertDB();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		return r;
	}

	public int getWthrBrkNewsListAPI(Calendar from_dttm, Calendar to_dttm) {

		WthrBrkNewsListAPI brkNewsList = null;
		int r = -1;
		for (int i = 0; i < zoneCdList.size(); i++) {

			try {
				System.out.println("구역 명 : " + zoneCdList.get(i).get(1));
				brkNewsList = new WthrBrkNewsListAPI("getWthrBrkNewsList", serviceKey, zoneCdList.get(i).get(0),
						cal2Str(from_dttm), cal2Str(to_dttm));

				brkNewsList.setStnName(zoneCdList.get(i).get(1));

				brkNewsList.getResponse();
				brkNewsList.parseResponse();

				r = brkNewsList.insertDB();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		return r;
	}

	public int getWthrBrkNewsAPI(Calendar from_dttm, Calendar to_dttm) {

		WthrBrkNewsAPI brkNews = null;
		int r = -1;
		for (int i = 0; i < zoneCdList.size(); i++) {

			try {
				System.out.println("구역 명 : " + zoneCdList.get(i).get(1));
				brkNews = new WthrBrkNewsAPI("getWthrBrkNews", serviceKey, zoneCdList.get(i).get(0), cal2Str(from_dttm),
						cal2Str(to_dttm));
				brkNews.setStnName(zoneCdList.get(i).get(1));

				brkNews.getResponse();
				brkNews.parseResponse();

				r = brkNews.insertDB();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		return r;
	}

	public Map<Integer, List<List<String>>> mappingStnIdWrnArea() {

		Map<Integer, List<List<String>>> stnId2AreaName = new HashMap<>();
		String areaName;

		for (int i = 1; i < zoneCdList.size(); i++) {

			Set<String> key = WrnZoneCdMap.keySet();
			for (Iterator<String> it = key.iterator(); it.hasNext();) {
				areaName = (String) it.next();

				if (areaName.equals(zoneCdList.get(i).get(2))) {
					stnId2AreaName.put(Integer.parseInt(zoneCdList.get(i).get(0)), WrnZoneCdMap.get(areaName));
					System.out.println(areaName + " + " + zoneCdList.get(i).get(0));
				}
			}
		}
		return stnId2AreaName;
	}

	public int getPwnCdAPI(Calendar from_dttm, Calendar to_dttm) throws InterruptedException {

		PwnCdAPI pwnCd = null;
		int r = -1;

		Map<Integer, List<List<String>>> stnId2Area = mappingStnIdWrnArea();
		List<List<String>> areaList;

		for (int cdIdx = 0; cdIdx < zoneCdList.size(); cdIdx++) {

			int zondCd = Integer.parseInt(zoneCdList.get(cdIdx).get(0));

			areaList = stnId2Area.get(zondCd) == null ? entireWrnZoneCdList : stnId2Area.get(zondCd);

			for (int wrnIdx = 0; wrnIdx < areaList.size(); wrnIdx++) {
				for (int warnNum = 1; warnNum <= WrnCode; warnNum++) {

					System.out.println("warnArea : " + areaList.get(wrnIdx).get(2) + " stnID : " + zondCd + " "
							+ zoneCdList.get(cdIdx).get(1) + " warnType :" + warnNum);
					try {

						pwnCd = new PwnCdAPI("getPwnCd", serviceKey, zoneCdList.get(cdIdx).get(0), cal2Str(from_dttm),
								cal2Str(to_dttm), areaList.get(wrnIdx).get(1), warnNum);
						pwnCd.setStnName(zoneCdList.get(cdIdx).get(1));

						pwnCd.getResponse();
						pwnCd.parseResponse();

						pwnCd.insertDB();

					} catch (IOException e) {
						e.printStackTrace();
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
				}
				Thread.sleep(500);
			}
		}
		return r;

	}

	public int getTmpPwnCdAPI(Calendar from_dttm, Calendar to_dttm) throws InterruptedException {

		PwnCdAPI pwnCd = null;
		int r = -1;

		int zondCd = Integer.parseInt(zoneCdList.get(0).get(0));

		for (int wrnIdx = 0; wrnIdx < entireWrnZoneCdList.size(); wrnIdx++) {
			for (int warnNum = 1; warnNum <= WrnCode; warnNum++) {

				System.out.println("warnArea : " + entireWrnZoneCdList.get(wrnIdx).get(2) + " stnID : " + zondCd + " "
						+ zoneCdList.get(0).get(1) + " warnType :" + warnNum);
				try {

					pwnCd = new PwnCdAPI("getPwnCd", serviceKey, zoneCdList.get(0).get(0), cal2Str(from_dttm),
							cal2Str(to_dttm), entireWrnZoneCdList.get(wrnIdx).get(1), warnNum);
					pwnCd.setStnName(zoneCdList.get(0).get(1));

					pwnCd.getResponse();
					pwnCd.parseResponse();

					r = pwnCd.insertDB();

				} catch (IOException e) {
					e.printStackTrace();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (NullPointerException e) {
					e.printStackTrace();
				} 
			}
			Thread.sleep(500);
		}
		return r;

	}

	public int getPwnStatusAPI() {

		PwnStatusAPI pwnStat = null;
		int r = -1;
		try {
			pwnStat = new PwnStatusAPI("getPwnStatus", serviceKey);

			pwnStat.getResponse();
			pwnStat.parseResponse();

			r = pwnStat.insertDB();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} 

		return r;
	}

	public String cal2Str(Calendar dttm) {

		int year = dttm.get(Calendar.YEAR);
		int month = dttm.get(Calendar.MONTH) + 1;
		int day = dttm.get(Calendar.DAY_OF_MONTH);

		return String.format("%4d%02d%02d", year, month, day);
	}
}
