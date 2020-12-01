package fcstZone;

public class FcstZoneCd {

	private String regId, tmEd, tmSt, regName, regEn, regSp, lat, lon, ht, regUp, stnWn, stnF3, stnFd, stnFw, seq;

	public FcstZoneCd() {
		setRegId(null);
		setTmEd(null);
		setTmSt(null);
		setRegName(null);
		setRegEn(null);
		setRegSp(null);
		setLat(null);
		setLon(null);
		setHt(null);
		setRegUp(null);
		setStnWn(null);
		setStnF3(null);
		setStnFd(null);
		setStnFw(null);
		setSeq(null);

	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getTmEd() {
		return tmEd;
	}

	public void setTmEd(String tmEd) {
		this.tmEd = tmEd;
	}

	public String getTmSt() {
		return tmSt;
	}

	public void setTmSt(String tmSt) {
		this.tmSt = tmSt;
	}

	public String getRegName() {
		return regName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}

	public String getRegEn() {
		return regEn;
	}

	public void setRegEn(String regEn) {
		this.regEn = regEn;
	}

	public String getRegSp() {
		return regSp;
	}

	public void setRegSp(String regSp) {
		this.regSp = regSp;
	}

	public Double getLat() {
		return lat.equals("null") ? java.sql.Types.NULL : Double.parseDouble(lat);
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon.equals("null") ? java.sql.Types.NULL : Double.parseDouble(lon);
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public Double getHt() {
		return ht.equals("null") ? java.sql.Types.NULL : Double.parseDouble(ht);
	}

	public void setHt(String ht) {
		this.ht = ht;
	}

	public String getRegUp() {
		return regUp;
	}

	public void setRegUp(String regUp) {
		this.regUp = regUp;
	}

	public String getStnWn() {
		return stnWn;
	}

	public void setStnWn(String stnWn) {
		this.stnWn = stnWn;
	}

	public String getStnF3() {
		return stnF3;
	}

	public void setStnF3(String stnF3) {
		this.stnF3 = stnF3;
	}

	public String getStnFd() {
		return stnFd;
	}

	public void setStnFd(String stnFd) {
		this.stnFd = stnFd;
	}

	public String getStnFw() {
		return stnFw;
	}

	public void setStnFw(String stnFw) {
		this.stnFw = stnFw;
	}

	public int getSeq() {
		return Integer.parseInt(seq);
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

}
