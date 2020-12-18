package base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import drghtFcltyCode.FcltyCodeDBController;
import drghtFcltyCode.FcltyCodeListAPI;
import drghtMonthAnals.MonthAnalsListAPI;
import drghtWeekAnals.WeekAnalsListAPI;
import farmingFrcst.FarmingFrcstListAPI;
import livinIndustryFrcst.LivinIndustryFrcstListAPI;
import wthrFrcst.WthrFrcstListAPI;

public class APIController {

	private List<String> monthCal;
	private Calendar from_dttm, to_dttm;

	private List<Long> fcltyCdList;
	
	private List<String> hjdCdList;

	public APIController(String start_dttm, String end_dttm) {

		from_dttm = Calendar.getInstance();

		from_dttm.set(Calendar.YEAR, Integer.parseInt(start_dttm.substring(0, 4)));
		from_dttm.set(Calendar.MONTH, Integer.parseInt(start_dttm.substring(4, 6)) - 1);
		from_dttm.set(Calendar.DAY_OF_MONTH, Integer.parseInt(start_dttm.substring(6, 8)));
		


		to_dttm = Calendar.getInstance();

		to_dttm.set(Calendar.YEAR, Integer.parseInt(end_dttm.substring(0, 4)));
		to_dttm.set(Calendar.MONTH, Integer.parseInt(end_dttm.substring(4, 6)) - 1);
		to_dttm.set(Calendar.DAY_OF_MONTH, Integer.parseInt(end_dttm.substring(6, 8)));

		this.monthCal = makeMonthCalendar(from_dttm.getInstance(), to_dttm.getInstance());
		
		
		System.out.println("make Calendar");
		
		FileController fileController = new FileController();
		fcltyCdList = fileController.getCdList(fcltyDivCdFilePath);
		
		hjdCdList = null;

		
	}

	public int getWthrFrcstInfoListAPI() {

		String urlName = "drghtFrcstAlarmWeather";

		WthrFrcstListAPI fcstAPI = null;
		int r = -1;
		for (int i = 0; i < monthCal.size(); i++) {

			try {
				System.out.println("Month : " + monthCal.get(i));
				fcstAPI = new WthrFrcstListAPI(urlName, "frcstInfoList", serviceKey, 
						(String) monthCal.get(i));

				fcstAPI.getResponse();
				fcstAPI.parseResponse();

				r = fcstAPI.insertDB();

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

	public int getLivinIndstryFrcstInfoListAPI() {

		String urlName = "drghtFrcstAlarmLniwtr";

		LivinIndustryFrcstListAPI fcstAPI = null;
		int r = -1;
		for (int i = 0; i < monthCal.size(); i++) {

			try {
				System.out.println("Month : " + monthCal.get(i));
				fcstAPI = new LivinIndustryFrcstListAPI(urlName, "frcstInfoList", serviceKey,
						(String) monthCal.get(i));

				fcstAPI.getResponse();
				fcstAPI.parseResponse();

				r = fcstAPI.insertDB();

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

	public int getFarminFrcstInfoListAPI() {

		String urlName = "drghtFrcstAlarmFarming";

		FarmingFrcstListAPI fcstAPI = null;
		int r = -1;
		for (int i = 0; i < monthCal.size(); i++) {

			try {
				System.out.println("Month : " + monthCal.get(i));
				fcstAPI = new FarmingFrcstListAPI(urlName, "frcstInfoList", serviceKey, 
						(String) monthCal.get(i));

				fcstAPI.getResponse();
				fcstAPI.parseResponse();

				r = fcstAPI.insertDB();

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

	public int getDrghtFcltyCodeAPI() {

		String urlName = "drghtFcltyCode";

		FcltyCodeListAPI fcltyCdListAPI = null;
		
		int r = -1;
		for (int i = 0; i < fcltyCdList.size(); i++) {

			try {
				fcltyCdListAPI = new FcltyCodeListAPI(urlName, "fcltyList", serviceKey, fcltyCdList.get(i));

				fcltyCdListAPI.getResponse();
				fcltyCdListAPI.parseResponse();

				r = fcltyCdListAPI.insertDB();

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
	private List<String> getFcltyCd(long fcltyDivCode){

		FcltyCodeDBController dbController = new FcltyCodeDBController();
		List<String> fcltyCd = dbController.getFcltyCode(fcltyDivCode);
		
		fcltyCd.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				Long o12Long = Long.parseLong(o1);
				Long o22Long = Long.parseLong(o2);
				
				return o12Long > o22Long ? 1 :
					o12Long == o22Long ? 0 : -1;
			}
			
		});
		return fcltyCd;
		
	}
	public int getDrghtWeekAnalsAPI() {

		String urlName = "drghtWeekAnalsAdmnszon";

		WeekAnalsListAPI weekAnalsAPI = null;
		
		int r = -1;
		
		if(hjdCdList == null ) 
			hjdCdList = getFcltyCd(1008);
		
		for (int i = 0; i < hjdCdList.size(); i++) {

			try {
				System.out.println(String.format("hjdCode : %s", hjdCdList.get(i)));
				System.out.println(String.format("from_dttm :  %s, to_dttm : %s", cal2Str(from_dttm), cal2Str(to_dttm)));
				weekAnalsAPI = new WeekAnalsListAPI(urlName, "analsInfoList", serviceKey, hjdCdList.get(i), cal2Str(from_dttm), cal2Str(to_dttm));

				weekAnalsAPI.getResponse();
				weekAnalsAPI.parseResponse();

				r = weekAnalsAPI.insertDB();

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
	public int getDrghtMonthAnalsAPI() {

		String urlName = "drghtMnbyAlansAdmnszon";

		MonthAnalsListAPI monthAnalsAPI = null;
		
		int r = -1;
		
		if(hjdCdList == null ) 
			hjdCdList = getFcltyCd(1008);
		
		String stDt = cal2Str(from_dttm).substring(0, 6);
		String edDt = cal2Str(to_dttm).substring(0, 6);
		
		for (int i = 0; i < hjdCdList.size(); i++) {

			try {
				System.out.println(String.format("hjdCode : %s", hjdCdList.get(i)));
				System.out.println(String.format("from_dttm :  %s, to_dttm : %s", stDt, edDt));
				monthAnalsAPI = new MonthAnalsListAPI(urlName, "analsInfoList", serviceKey, hjdCdList.get(i), stDt, edDt);

				monthAnalsAPI.getResponse();
				monthAnalsAPI.parseResponse();

				r = monthAnalsAPI.insertDB();

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
	public List<String> makeMonthCalendar(Calendar from_dttm, Calendar to_dttm) {

		List<String> calendar = new ArrayList<>();
		String fromYearMonth = String.format("%4d%02d", from_dttm.get(Calendar.YEAR),
				from_dttm.get(Calendar.MONTH) + 1);
		String toYearMonth = String.format("%4d%02d", to_dttm.get(Calendar.YEAR), to_dttm.get(Calendar.MONTH) + 1);

		while (!fromYearMonth.equals(toYearMonth)) {

			calendar.add(fromYearMonth);
			from_dttm.add(Calendar.MONTH, +1);
			fromYearMonth = String.format("%4d%02d", from_dttm.get(Calendar.YEAR), from_dttm.get(Calendar.MONTH) + 1);

		}
		calendar.add(fromYearMonth);

		return calendar;

	}
	public String cal2Str(Calendar dttm) {

		int year = dttm.get(Calendar.YEAR);
		int month = dttm.get(Calendar.MONTH) + 1;
		int day = dttm.get(Calendar.DAY_OF_MONTH);

		return String.format("%4d%02d%02d", year, month, day);
	}

}
