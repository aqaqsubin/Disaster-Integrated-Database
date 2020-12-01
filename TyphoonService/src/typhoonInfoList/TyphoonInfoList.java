package typhoonInfoList;

public class TyphoonInfoList {
	private String tmFc, title; 
	private String tmSeq, typSeq;

	public TyphoonInfoList() {
		setTmFc(null);
		setTmSeq(null);
		setTypSeq(null);
		setTitle(null);
	}

	public String getTmFc() {
		return tmFc;
	}

	public void setTmFc(String tmFc) {
		this.tmFc = tmFc;
	}

	public int getTmSeq() {
		return tmSeq.equals("null")? java.sql.Types.NULL : Integer.parseInt(tmSeq);
	}

	public void setTmSeq(String tmSeq) {
		this.tmSeq = tmSeq;
	}

	public int getTypSeq() {
		return typSeq.equals("null")? java.sql.Types.NULL : Integer.parseInt(typSeq);
	}

	public void setTypSeq(String typSeq) {
		this.typSeq = typSeq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
