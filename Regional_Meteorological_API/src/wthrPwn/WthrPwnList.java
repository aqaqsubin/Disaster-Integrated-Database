package wthrPwn;

public class WthrPwnList {
	private String title, stnId, tmSeq, tmFc;

	public WthrPwnList() {
		setTitle(null);
		setStnId(null);
		setTmSeq(null);
		setTmFc(null);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
}
