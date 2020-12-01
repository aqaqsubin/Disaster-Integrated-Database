package base;

public class Main {
	public static void main(String[] args) {

		APIController apiController = new APIController("FcstZone_List.xlsx");
		
		apiController.getFcstZoneCd();
	}


	
}