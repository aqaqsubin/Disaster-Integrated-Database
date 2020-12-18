package occurrInfo;

public class RedTideOccurInfo {
	private String title, srCode, afterView, etc, state, report, regDate;

	public RedTideOccurInfo() {
		setTitle(null);
		setSrCode(null);
		setAfterView(null);
		setEtc(null);
		setState(null);
		setReport(null);
		setRegDate(null);
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getSrCode() {
		return srCode;
	}

	public void setSrCode(String srCode) {
		this.srCode = srCode;
	}

	public String getAfterView() {
		return afterView;
	}

	public void setAfterView(String afterView) {
		this.afterView = afterView;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

}
