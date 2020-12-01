package wthrPwn;

public class WthrPwn {
	private String stnId, tmFc, tmSeq, refNum, pwnExpl, other;

	public WthrPwn() {
		setStnId(null);
		setTmSeq(null);
		setTmFc(null);
		setRefNum(null);
		setPwnExpl(null);
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

	public String getPwnExpl() {
		return pwnExpl;
	}

	public void setPwnExpl(String pwnExpl) {
		this.pwnExpl = pwnExpl;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public int getRefNum() {
		return refNum.equals("null") ? java.sql.Types.NULL : Integer.parseInt(refNum);
	}

	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}

}
