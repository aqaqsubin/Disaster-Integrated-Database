package base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import occurrInfo.RedTideOccurInfoAPI;
import occurrSpot.RedTideOccurSpotAPI;

public class APIController {

	private List<String> dateList;
	private Calendar from_dttm, to_dttm;

	private String serviceKey = "";

	public APIController(String start_dttm, String end_dttm) {

		from_dttm = Calendar.getInstance();

		from_dttm.set(Calendar.YEAR, Integer.parseInt(start_dttm.substring(0, 4)));
		from_dttm.set(Calendar.MONTH, Integer.parseInt(start_dttm.substring(4, 6)) - 1);
		from_dttm.set(Calendar.DAY_OF_MONTH, Integer.parseInt(start_dttm.substring(6, 8)));

		to_dttm = Calendar.getInstance();

		to_dttm.set(Calendar.YEAR, Integer.parseInt(end_dttm.substring(0, 4)));
		to_dttm.set(Calendar.MONTH, Integer.parseInt(end_dttm.substring(4, 6)) - 1);
		to_dttm.set(Calendar.DAY_OF_MONTH, Integer.parseInt(end_dttm.substring(6, 8)));

		this.dateList = makeCalendar(from_dttm, to_dttm);

		System.out.println("make Calendar");

	}

	public int getRedTideOccurSpotAPI() {

		RedTideOccurSpotAPI spotAPI = null;
		int r = -1;
		for (int i = 0; i < dateList.size(); i++) {

			try {
				System.out.println("Date : " + dateList.get(i));
				spotAPI = new RedTideOccurSpotAPI("getOceanproblemRedTideOccurrenceSpot", serviceKey, 1, 10, (String) dateList.get(i));

				spotAPI.getResponse();
				spotAPI.parseResponse();

				r = spotAPI.insertDB();

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

	public int getRedTideOccurInfoAPI() {

		RedTideOccurInfoAPI fcstAPI = null;
		int r = -1;
		for (int i = 0; i < dateList.size(); i++) {

			try {
				System.out.println("Date : " + dateList.get(i));
				fcstAPI = new RedTideOccurInfoAPI("getOceanproblemRedTideOccurrenceInfo", serviceKey, 1, 10, (String) dateList.get(i));

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

	public List<String> makeCalendar(Calendar from_dttm, Calendar to_dttm) {

		List<String> calendar = new ArrayList<>();
		String fromYearMonth = String.format("%4d%02d%0d", from_dttm.get(Calendar.YEAR),
				from_dttm.get(Calendar.MONTH) + 1, from_dttm.get(Calendar.DAY_OF_MONTH));
		String toYearMonth = String.format("%4d%02d%02d", to_dttm.get(Calendar.YEAR), to_dttm.get(Calendar.MONTH) + 1,
				from_dttm.get(Calendar.DAY_OF_MONTH));

		while (!fromYearMonth.equals(toYearMonth)) {

			calendar.add(fromYearMonth);
			from_dttm.add(Calendar.MONTH, +1);
			fromYearMonth = String.format("%4d%02d%02d", from_dttm.get(Calendar.YEAR),
					from_dttm.get(Calendar.MONTH) + 1, from_dttm.get(Calendar.DAY_OF_MONTH));

		}
		calendar.add(fromYearMonth);

		return calendar;

	}

}
