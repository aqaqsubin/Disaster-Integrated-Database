package base;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		APIController apiController = new APIController("20170101","20201126");
		apiController.getWthrFrcstInfoListAPI();
		apiController.getLivinIndstryFrcstInfoListAPI();
		apiController.getFarminFrcstInfoListAPI();
		
		apiController.getDrghtFcltyCodeAPI();
		apiController.getDrghtWeekAnalsAPI();
		apiController.getDrghtMonthAnalsAPI();
		
	}

}
