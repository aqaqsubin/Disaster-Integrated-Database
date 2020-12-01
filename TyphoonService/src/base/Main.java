package base;
import java.util.Calendar;


public class Main {
    public static void main(String[] args){
    	
    	
    	Calendar now = Calendar.getInstance();
		
		Calendar pre = Calendar.getInstance();
		pre.add(Calendar.DAY_OF_MONTH, -3);
    	
    	APIController apiController = new APIController();

    	apiController.getEntireTypInfo(pre, now);
//    	apiController.updateTypInfo(pre, now);
    	
    	
    }


}