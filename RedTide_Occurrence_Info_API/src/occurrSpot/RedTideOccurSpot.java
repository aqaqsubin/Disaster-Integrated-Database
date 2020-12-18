package occurrSpot;

public class RedTideOccurSpot {
	private String srCode, causeOrganism, orgDensityMax, orgDensityMin, tempMax, tempMin, seaArea, regDate;

	public RedTideOccurSpot() {
		setSrCode(null);
		setCauseOrganism(null);
		setOrgDensityMax(null);
		setOrgDensityMin(null);
		setTempMax(null);
		setTempMin(null);
		setSeaArea(null);
		setRegDate(null);
	}

	public String getSrCode() {
		return srCode;
	}

	public void setSrCode(String srCode) {
		this.srCode = srCode;
	}

	public String getCauseOrganism() {
		return causeOrganism;
	}

	public void setCauseOrganism(String causeOrganism) {
		this.causeOrganism = causeOrganism;
	}

	public Double getOrgDensityMax() {
		return orgDensityMax.equals("null") ? java.sql.Types.NULL : Double.parseDouble(orgDensityMax);
	}

	public void setOrgDensityMax(String orgDensityMax) {
		this.orgDensityMax = orgDensityMax;
	}

	public Double getOrgDensityMin() {
		return orgDensityMin.equals("null") ? java.sql.Types.NULL : Double.parseDouble(orgDensityMin);
	}

	public void setOrgDensityMin(String orgDensityMin) {
		this.orgDensityMin = orgDensityMin;
	}

	public Double getTempMax() {
		return tempMax.equals("null") ? java.sql.Types.NULL : Double.parseDouble(tempMax);
	}

	public void setTempMax(String tempMax) {
		this.tempMax = tempMax;
	}

	public Double getTempMin() {
		return tempMin.equals("null") ? java.sql.Types.NULL : Double.parseDouble(tempMin);
	}

	public void setTempMin(String tempMin) {
		this.tempMin = tempMin;
	}

	public String getSeaArea() {
		return seaArea;
	}

	public void setSeaArea(String seaArea) {
		this.seaArea = seaArea;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

}
