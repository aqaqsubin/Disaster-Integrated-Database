package drghtFcltyCode;

public class FcltyCodeList {
	
	private String fcltyDivCode, fcltyName, fcltyCode;
	
	public FcltyCodeList() {
		setFcltyName(null);
		setFcltyCode(null);
	}

	public String getFcltyName() {
		return fcltyName;
	}

	public void setFcltyName(String fcltyName) {
		this.fcltyName = fcltyName;
	}

	public String getFcltyCode() {
		return fcltyCode;
	}

	public void setFcltyCode(String fcltyCode) {
		this.fcltyCode = fcltyCode;
	}
	public void setFcltyDivCode(String fcltyDivCode) {
		this.fcltyDivCode = fcltyDivCode;
	}

	public String getFcltyDivCode() {
		return fcltyDivCode;
	}

}
