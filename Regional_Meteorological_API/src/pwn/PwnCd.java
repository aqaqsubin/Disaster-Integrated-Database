package pwn;

public class PwnCd {
	private String stnId, tmSeq, tmFc, areaCd, areaName, warnVar, warnStress, wrnCd, tmStart, tmEnd, tmEntireEnd, cancelCd;

	public PwnCd() {
		setStnId(null);
		setTmSeq(null);
		setTmFc(null);
		setAreaCd(null);
		setAreaName(null);
		setWarnVar(null);
		setWarnStress(null);
		setWrnCd(null);
		setTmStart(null);
		setTmEnd(null);
		setTmEntireEnd(null);
		setCancelCd(null);
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

	public String getAreaCd() {
		return areaCd;
	}

	public void setAreaCd(String areaCd) {
		this.areaCd = areaCd;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getWarnVar() {
		return warnVar.equals("null") ? java.sql.Types.NULL : Integer.parseInt(warnVar);
	}

	public void setWarnVar(String warnVar) {
		this.warnVar = warnVar;
	}

	public String getTmFc() {
		return tmFc;
	}

	public void setTmFc(String tmFc) {
		this.tmFc = tmFc;
	}

	public int getWarnStress() {
		return warnStress.equals("null") ? java.sql.Types.NULL : Integer.parseInt(warnStress);
	}

	public void setWarnStress(String warnStress) {
		this.warnStress = warnStress;
	}

	public int getWrnCd() {
		return wrnCd.equals("null") ? java.sql.Types.NULL : Integer.parseInt(wrnCd);
	}

	public void setWrnCd(String wrnCd) {
		this.wrnCd = wrnCd;
	}

	public String getTmStart() {
		return tmStart;
	}

	public void setTmStart(String tmStart) {
		this.tmStart = tmStart;
	}

	public String getTmEnd() {
		return tmEnd;
	}

	public void setTmEnd(String tmEnd) {
		this.tmEnd = tmEnd;
	}

	public String getTmEntireEnd() {
		return tmEntireEnd;
	}

	public void setTmEntireEnd(String tmEntireEnd) {
		this.tmEntireEnd = tmEntireEnd;
	}

	public int getCancelCd() {
		return cancelCd.equals("null") ? java.sql.Types.NULL : Integer.parseInt(cancelCd);
	}

	public void setCancelCd(String cancelCd) {
		this.cancelCd = cancelCd;
	}
}
