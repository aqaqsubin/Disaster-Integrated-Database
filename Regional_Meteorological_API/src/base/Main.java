package base;

import java.util.Calendar;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Calendar now = Calendar.getInstance();
		
		Calendar pre = Calendar.getInstance();
		pre.add(Calendar.DAY_OF_MONTH, -6);

		WthrWrnInfoAPIController apiController = new WthrWrnInfoAPIController("ZoneCode.xlsx",
				"SpecialZoneCode.xlsx");
		
		apiController.getWthrWrnListAPI(pre, now);
		apiController.getWthrWrnMsg(pre, now);
		
		apiController.getWthrPwnListAPI(pre, now);
		apiController.getWthrPwnAPI(pre, now);
		
		apiController.getWthrInfoListAPI(pre, now);
		apiController.getWthrInfoAPI(pre, now);

		apiController.getWthrBrkNewsListAPI(pre, now);
		apiController.getWthrBrkNewsAPI(pre, now);

		apiController.getTmpPwnCdAPI(pre, now);
		apiController.getPwnStatusAPI();
	}
	
	
}