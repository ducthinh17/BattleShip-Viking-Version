/* 
Group's Number: 11
Member names & IU code:
	PHẠM LÊ ĐỨC THỊNH - ITDSIU20085
	NGUYỄN THANH BÌNH - ITDSIU20056
	NGUYỄN SONG HÀO KIỆT - ITDSIU20098
	CHÂU KHẮC ĐÌNH PHONG - ITDSIU20076
Purpose: We gained a solid understanding of the DSA concepts and how to apply them to real-world scenarios. 
*/

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

	/**
	 * Returns the position of the line. This is equivalent to p. equals ( this )
	 * except that it doesn't throw an exception if the line is not parallel to the
	 * origin.
	 * 
	 * 
	 * @return the position of the line in the coordinate system of
	 */
	public Position getP() {
		return p;
	}

	/**
	 * Sets the position of the line. This is used to determine the direction of the
	 * line
	 * 
	 * @param p - the position of the
	 */
	public void setP(Position p) {
		this.p = p;
	}

	/**
	 * Metodo que permite indicar si el punto esta colpita
	 */
	public boolean isHit() {
		return colpita;
	}

	/**
	 * Sets the colpita flag. This is used to prevent an attacker from trying to
	 * reproduce a problem that is in the event that the user has changed his / her
	 * privileges.
	 * 
	 * @param colpita - True if the attacker is affected
	 */
	public void setAffected(boolean colpita) {
		this.colpita = colpita;
	}

	/**
	 * Returns true if affondata is sunk false otherwise. This is used to determine
	 * if we are dealing with a lot of data
	 */
	public boolean isSunk() {
		return affondata;
	}

	/**
	 * Sets the sunk flag. This is used to mark the object as sunk in the event that
	 * it has no data to send to the client
	 * 
	 * @param affondata - true if the object is aff
	 */
	public void setSunk(boolean affondata) {
		this.affondata = affondata;
	}

	/**
	 * Returns a String representation of this Coordination. The String
	 * representation is composed of the following : coordinate : p hit : colpita
	 * sunken : affondata
	 * 
	 * 
	 * @return a String representation of this Coordination ( for debugging
	 */
	public String toString() {
		return "coordinate:" + p + " hit:" + colpita + " sunken:" + affondata;
	}
}

/*
 * Group's Number: 11
 * Member names & IU code:
 * PHẠM LÊ ĐỨC THỊNH - ITDSIU20085
 * NGUYỄN THANH BÌNH - ITDSIU20056
 * NGUYỄN SONG HÀO KIỆT - ITDSIU20098
 * CHÂU KHẮC ĐÌNH PHONG - ITDSIU20076
 * Purpose: We gained a solid understanding of the DSA concepts and how to apply
 * them to real-world scenarios.
 */
