package wthrBrkNews;

public class WthrBrkNews {
	private String stnId, tmSeq, tmFc, refNum, newsExpl, titleExpl;
	private int lenExpl;
	
	public WthrBrkNews() {
		setStnId(null);
		setTmSeq(null);
		setTmFc(null);
		setRefNum(null);
		setNewsExpl(null);
	}

	public String getStnId() {
		return stnId;
	}

	public void setStnId(String stnId) {
		this.stnId = stnId;
	}

	public int getTmSeq() {
		return tmSeq.equals("null") ? java.sql.Types.NULL : Integer.parseInt(tmSeq);
	}

	public void setTmSeq(String tmSeq) {
		this.tmSeq = tmSeq;
	}

	public String getTmFc() {
		return tmFc;
	}

	public void setTmFc(String tmFc) {
		this.tmFc = tmFc;
	}

	public int getRefNum() {
		return refNum.equals("null") ? java.sql.Types.NULL : Integer.parseInt(refNum);
	}

	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}

	public String getNewsExpl() {
		return newsExpl;
	}

	public void setNewsExpl(String newsExpl) {
		this.newsExpl = newsExpl;
		this.lenExpl = newsExpl == null ? 0 : newsExpl.length();
	}

	public String getTitleExpl() {
		return titleExpl;
	}

	public void setTitleExpl(String titleExpl) {
		this.titleExpl = titleExpl;
	}
	public int getLenExpl() {
		return lenExpl;
	}

}
