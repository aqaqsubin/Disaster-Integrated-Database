package typhoonInfo;

public class TyphoonInfo {
	
	private String imgStr, typLoc, typDir, tmFc, typTm, typ15ed, typ25ed, typName, typEn, rem, other, typ15, typ15er, typ25, typ25er; 
	private byte[] imgByte;
	
	private String typSeq, tmSeq;
	private String typSp, typPs, typWs, typLat, typLon;
	
	public TyphoonInfo() {
		setImgStr(null);
		setTypLoc(null);
		setTypDir(null);
		setTmFc(null);
		setTypTm(null);
		setTypSeq(null);
		setTmSeq(null);
		setTypSp(null);
		setTypPs(null);
		setTypWs(null);
		setTypLat(null);
		setTypLon(null);
		setTyp15ed(null);
		setTyp25ed(null);
		setTypName(null);
		setTypEn(null);
		setRem(null);
		setOther(null);
		setTyp15(null);
		setTyp15er(null);
		setTyp25(null);
		setTyp25er(null);

	}

	public String getImgStr() {
		return imgStr;
	}

	public void setImgStr(String imgStr) {
		this.imgStr = imgStr;
	}

	public byte[] getImgByte() {
		return imgByte;
	}

	public void setImgByte(byte[] imgByte) {
		this.imgByte = imgByte;
	}
	
	public String getTypLoc() {
		return typLoc;
	}

	public void setTypLoc(String typLoc) {
		this.typLoc = typLoc;
	}

	public String getTypDir() {
		return typDir;
	}

	public void setTypDir(String typDir) {
		this.typDir = typDir;
	}

	public String getTmFc() {
		return tmFc;
	}

	public void setTmFc(String tmFc) {
		this.tmFc = tmFc;
	}

	public String getTypTm() {
		return typTm;
	}

	public void setTypTm(String typTm) {
		this.typTm = typTm;
	}

	public int getTypSeq() {
		return typSeq.equals("null") ? java.sql.Types.NULL : Integer.parseInt(typSeq);
	}

	public void setTypSeq(String typSeq) {
		this.typSeq = typSeq;
	}

	public int getTmSeq() {
		return tmSeq.equals("null") ? java.sql.Types.NULL : Integer.parseInt(tmSeq);
	}

	public void setTmSeq(String tmSeq) {
		this.tmSeq = tmSeq;
	}

	public double getTypSp() {
		return typSp.equals("null") ? java.sql.Types.NULL : Double.parseDouble(tmSeq);
	}

	public void setTypSp(String typSp) {
		this.typSp = typSp;
	}

	public double getTypPs() {
		return typPs.equals("null") ? java.sql.Types.NULL : Double.parseDouble(typPs);
	}

	public void setTypPs(String typPs) {
		this.typPs = typPs;
	}

	public double getTypWs() {
		return typWs.equals("null") ? java.sql.Types.NULL : Double.parseDouble(typWs);
	}

	public void setTypWs(String typWs) {
		this.typWs = typWs;
	}

	public double getTypLat() {
		return typLat.equals("null") ? java.sql.Types.NULL : Double.parseDouble(typLat);
	}

	public void setTypLat(String typLat) {
		this.typLat = typLat;
	}

	public double getTypLon() {
		return typLon.equals("null") ? java.sql.Types.NULL : Double.parseDouble(typLon);
	}

	public void setTypLon(String typLon) {
		this.typLon = typLon;
	}

	
	public String getTypName() {
		return typName;
	}

	public void setTypName(String typName) {
		this.typName = typName;
	}

	public String getTypEn() {
		return typEn;
	}

	public void setTypEn(String typEn) {
		this.typEn = typEn;
	}

	public String getRem() {
		return rem;
	}

	public void setRem(String rem) {
		this.rem = rem;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public double getTyp15() {
		return typ15.equals("null") ? java.sql.Types.NULL :  Double.parseDouble(typ15);
	}

	public void setTyp15(String typ15) {
		this.typ15 = typ15;
	}

	public double getTyp25() {
		return typ25.equals("null") ? java.sql.Types.NULL : Double.parseDouble(typ25);
	}

	public void setTyp25(String typ25) {
		this.typ25 = typ25;
	}

	public double getTyp15er() {
		return typ15er.equals("null") ? java.sql.Types.NULL : Double.parseDouble(typ15er);
	}

	public void setTyp15er(String typ15er) {
		this.typ15er = typ15er;
	}

	public double getTyp25er() {
		return typ25er.equals("null") ? java.sql.Types.NULL : Double.parseDouble(typ25er);
	}

	public void setTyp25er(String typ25er) {
		this.typ25er = typ25er;
	}
	public String getTyp15ed() {
		return typ15ed.equals("null") ? null : typ15ed;
	}

	public void setTyp15ed(String typ15ed) {
		this.typ15ed = typ15ed;
	}

	public String getTyp25ed() {
		return typ25ed.equals("null") ? null : typ25ed;
	}

	public void setTyp25ed(String typ25ed) {
		this.typ25ed = typ25ed;
	}


}
