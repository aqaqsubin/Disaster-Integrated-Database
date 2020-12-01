package wthrWrn;

public class WthrWrnMsg {
	private String stnId, tmFc, tmSeq, wrnCd, title, zoneReport, tmReport, expl, tmWrnReport, wrnReport, preWrnReport,
			other;

	public WthrWrnMsg() {
		setStnId(null);
		setTmFc(null);
		setTmSeq(null);
		setWrnCd(null);
		setTitle(null);
		setZoneReport(null);
		setTmReport(null);
		setExpl(null);
		setTmWrnReport(null);
		setWrnReport(null);
		setPreWrnReport(null);
		setOther(null);
	}

	public String getStnId() {
		return stnId;
	}

	public void setStnId(String stnId) {
		this.stnId = stnId;
	}

	public String getTmFc() {
		return tmFc;
	}

	public void setTmFc(String tmFc) {
		this.tmFc = tmFc;
	}

	public int getTmSeq() {
		return tmSeq.equals("null") ? java.sql.Types.NULL : Integer.parseInt(tmSeq);
	}

	public void setTmSeq(String tmSeq) {
		this.tmSeq = tmSeq;
	}

	public int getWrnCd() {
		return wrnCd.equals("null") ? java.sql.Types.NULL : Integer.parseInt(wrnCd.trim());
	}

	public void setWrnCd(String wrnCd) {
		this.wrnCd = wrnCd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getZoneReport() {
		return zoneReport;
	}

	public void setZoneReport(String zoneReport) {
		this.zoneReport = zoneReport;
	}

	public String getTmReport() {
		return tmReport;
	}

	public void setTmReport(String tmReport) {
		this.tmReport = tmReport;
	}

	public String getExpl() {
		return expl;
	}

	public void setExpl(String expl) {
		this.expl = expl;
	}

	public String getTmWrnReport() {
		return tmWrnReport;
	}

	public void setTmWrnReport(String tmWrnReport) {
		this.tmWrnReport = tmWrnReport;
	}

	public String getWrnReport() {
		return wrnReport;
	}

	public void setWrnReport(String wrnReport) {
		this.wrnReport = wrnReport;
	}

	public String getPreWrnReport() {
		return preWrnReport;
	}

	public void setPreWrnReport(String preWrnReport) {
		this.preWrnReport = preWrnReport;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}
