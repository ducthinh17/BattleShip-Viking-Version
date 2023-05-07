package battleship;

public class Report {
	private Position p;
	private boolean colpita;
	private boolean affondata;

	public Report() {
	}

	public Report(Position p, boolean colpita, boolean affondata) {
		this.p = p;
		this.colpita = colpita;
		this.affondata = affondata;
	}

	public Position getP() {
		return p;
	}

	public void setP(Position p) {
		this.p = p;
	}

	public boolean isHit() {
		return colpita;
	}

	public void setAffected(boolean colpita) {
		this.colpita = colpita;
	}

	public boolean isSunk() {
		return affondata;
	}

	public void setSunk(boolean affondata) {
		this.affondata = affondata;
	}

	public String toString() {
		return "coordinate:" + p + " hit:" + colpita + " sunken:" + affondata;
	}
}
