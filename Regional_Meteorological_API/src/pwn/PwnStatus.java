package pwn;

public class PwnStatus {

	private String tmSeq, tmFc, tmEffect, titleExpl, effectExpl, preEffectExpl, other;

	public PwnStatus() {
		setTmSeq(null);
		setTmFc(null);
		setTmEffect(null);
		setTitleExpl(null);
		setEffectExpl(null);
		setPreEffectExpl(null);
		setOther(null);
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

	public String getTmEffect() {
		return tmEffect;
	}

	public void setTmEffect(String tmEffect) {
		this.tmEffect = tmEffect;
	}

	public String getTitleExpl() {
		return titleExpl;
	}

	public void setTitleExpl(String titleExpl) {
		this.titleExpl = titleExpl;
	}

	public String getEffectExpl() {
		return effectExpl;
	}

	public void setEffectExpl(String effectExpl) {
		this.effectExpl = effectExpl;
	}

	public String getPreEffectExpl() {
		return preEffectExpl;
	}

	public void setPreEffectExpl(String preEffectExpl) {
		this.preEffectExpl = preEffectExpl;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}
