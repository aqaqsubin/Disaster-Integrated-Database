package wthrInfo;

public class WthrInfo {
	private String stnId, tmSeq, tmFc, wthrInfo;

	public WthrInfo() {
		setStnId(null);
		setTmSeq(null);
		setTmFc(null);
		setWthrInfo(null);
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

	public String getWthrInfo() {
		return wthrInfo;
	}

	public void setWthrInfo(String wthrInfo) {
		this.wthrInfo = wthrInfo;
	}

}
