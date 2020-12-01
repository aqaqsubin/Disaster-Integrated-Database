package base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import typhoonFcst.TyphoonFcstAPI;
import typhoonInfo.TyphoonInfo;
import typhoonInfo.TyphoonInfoAPI;
import typhoonInfoList.TyphoonInfoListAPI;

public class APIController {

	private List<Integer> typSeqList;
	private String serviceKey = "";
	
	public int getEntireTypInfo(Calendar from_dttm, Calendar to_dttm) {
		
		int r = -1;
		
		TyphoonInfoAPI typInfo = null;
		TyphoonInfoListAPI typInfoList = null;
		TyphoonFcstAPI typFcst = null;
		
		ArrayList<TyphoonInfo> typInfoArr = null;
		
		try {
			typInfo = new TyphoonInfoAPI("getTyphoonInfo", serviceKey, cal2Str(from_dttm), cal2Str(to_dttm));

			typInfo.getResponse();
			typInfo.parseResponse();
			
			r = typInfo.insertDB();
			
			typInfoArr = typInfo.getTyphoonInfoArr();
			for(int i=0;i<typInfoArr.size();i++) {
				typInfoList = new TyphoonInfoListAPI("getTyphoonInfoList", serviceKey, typInfoArr.get(i).getTmFc());
				typInfoList.getResponse();
				typInfoList.parseResponse();
				
				r = typInfoList.insertDB();
				
				typFcst = new TyphoonFcstAPI("getTyphoonFcst", serviceKey, typInfoArr.get(i).getTmFc(), typInfoArr.get(i).getTypSeq());
				typFcst.getResponse();
				typFcst.parseResponse();

				r = typFcst.insertDB();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} 
		return r;
	}

	public int getTypInfoAPI(Calendar from_dttm, Calendar to_dttm) {

		TyphoonInfoAPI typInfo = null;
		int r = -1;

		try {
			typInfo = new TyphoonInfoAPI("getTyphoonInfo", serviceKey, cal2Str(from_dttm), cal2Str(to_dttm));

			typInfo.getResponse();
			typInfo.parseResponse();

			r = typInfo.insertDB();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} 
		return r;
	}

	public int getTypInfoListAPI(Calendar dttm) {

		TyphoonInfoListAPI typInfoList = null;
		int r = -1;

		try {
			typInfoList = new TyphoonInfoListAPI("getTyphoonInfoList", serviceKey, cal2Str(dttm));

			typInfoList.getResponse();
			typInfoList.parseResponse();

			r = typInfoList.insertDB();
			typSeqList = typInfoList.getTypSeqList();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} 
		return r;
	}

	public int getTypFcstAPI(Calendar from_dttm, Calendar to_dttm) {

		List<String> calList = makeCalendarList(from_dttm, to_dttm);
		TyphoonFcstAPI typFcst = null;

		int r = -1;

		try {
			for (int i = 0; i < calList.size(); i++) {
				for (int j = 0; j < typSeqList.size(); j++) {
					System.out.println(String.format("Date : %s, TypSeq : %d", calList.get(i), typSeqList.get(j)));
					typFcst = new TyphoonFcstAPI("getTyphoonFcst", serviceKey, calList.get(i), typSeqList.get(j));

					typFcst.getResponse();
					typFcst.parseResponse();

					r = typFcst.insertDB();

				}
			}
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

	public Calendar getCalendar(String tmFc) {
		Calendar dttm = Calendar.getInstance();

		dttm.set(Calendar.YEAR, Integer.parseInt(tmFc.substring(0, 4)));
		dttm.set(Calendar.MONTH, Integer.parseInt(tmFc.substring(4, 6)) - 1);
		dttm.set(Calendar.DAY_OF_MONTH, Integer.parseInt(tmFc.substring(6, 8)));

		return dttm;
	}

	public List<String> makeCalendarList(Calendar from_dttm, Calendar to_dttm) {

		List<String> cal = new ArrayList<>();

		String fromYearMonth = String.format("%4d%02d%02d", from_dttm.get(Calendar.YEAR),
				from_dttm.get(Calendar.MONTH) + 1, from_dttm.get(Calendar.DAY_OF_MONTH));
		String toYearMonth = String.format("%4d%02d%02d", to_dttm.get(Calendar.YEAR), to_dttm.get(Calendar.MONTH) + 1,
				to_dttm.get(Calendar.DAY_OF_MONTH));

		while (!fromYearMonth.equals(toYearMonth)) {

			cal.add(fromYearMonth);
			from_dttm.add(Calendar.DAY_OF_MONTH, +1);
			fromYearMonth = String.format("%4d%02d%02d", from_dttm.get(Calendar.YEAR),
					from_dttm.get(Calendar.MONTH) + 1, from_dttm.get(Calendar.DAY_OF_MONTH));

		}
		cal.add(fromYearMonth);

		return cal;

	}

	public int updateTypInfo(Calendar from_dttm, Calendar to_dttm) {
		// TODO Auto-generated method stub
		TyphoonInfoAPI typInfo = null;
		int r = -1;

		try {
			typInfo = new TyphoonInfoAPI("getTyphoonInfo", serviceKey, cal2Str(from_dttm), cal2Str(to_dttm));

			typInfo.getResponse();
			typInfo.parseResponse();

//			r = typInfo.updateDBImg2Blob();
			r = typInfo.updateDBImg2Str();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} 
		return r;
	}
}
