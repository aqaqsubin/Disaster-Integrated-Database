package base;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String strtDate = "20201101";
		String endDate = "20201230";
		
		APIController apiController = new APIController(strtDate,endDate);
		
		apiController.getWthrFrcstInfoListAPI();
		apiController.getLivinIndstryFrcstInfoListAPI();
		apiController.getFarminFrcstInfoListAPI();
		
		apiController.getDrghtFcltyCodeAPI();
		apiController.getDrghtWeekAnalsAPI();
		apiController.getDrghtMonthAnalsAPI();
		
	}

}
