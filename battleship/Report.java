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

//>>>>>>>>>>DOCIFY-START - qlrbihipherk //>>>>>>>>>>
	/**
	* Returns the position of the line. This is equivalent to p. equals ( this ) except that it doesn't throw an exception if the line is not parallel to the origin.
	* 
	* 
	* @return the position of the line in the coordinate system of
	*/
//>>>>>>>>>>DOCIFY-END - qlrbihipherk //>>>>>>>>>>
	public Position getP() {
		return p;
	}

//>>>>>>>>>>DOCIFY-START - olealfduzapa //>>>>>>>>>>
	/**
	* Sets the position of the line. This is used to determine the direction of the line
	* 
	* @param p - the position of the
	*/
//>>>>>>>>>>DOCIFY-END - olealfduzapa //>>>>>>>>>>
	public void setP(Position p) {
		this.p = p;
	}

//>>>>>>>>>>DOCIFY-START - jromqhcogllc //>>>>>>>>>>
	/**
	* Metodo que permite indicar si el punto esta colpita
	*/
//>>>>>>>>>>DOCIFY-END - jromqhcogllc //>>>>>>>>>>
	public boolean isHit() {
		return colpita;
	}

//>>>>>>>>>>DOCIFY-START - hswsctvbovhi //>>>>>>>>>>
	/**
	* Sets the colpita flag. This is used to prevent an attacker from trying to reproduce a problem that is in the event that the user has changed his / her privileges.
	* 
	* @param colpita - True if the attacker is affected
	*/
//>>>>>>>>>>DOCIFY-END - hswsctvbovhi //>>>>>>>>>>
	public void setAffected(boolean colpita) {
		this.colpita = colpita;
	}

//>>>>>>>>>>DOCIFY-START - nyhxjwzvxjsv //>>>>>>>>>>
	/**
	* Returns true if affondata is sunk false otherwise. This is used to determine if we are dealing with a lot of data
	*/
//>>>>>>>>>>DOCIFY-END - nyhxjwzvxjsv //>>>>>>>>>>
	public boolean isSunk() {
		return affondata;
	}

//>>>>>>>>>>DOCIFY-START - xedupjrfkmdo //>>>>>>>>>>
	/**
	* Sets the sunk flag. This is used to mark the object as sunk in the event that it has no data to send to the client
	* 
	* @param affondata - true if the object is aff
	*/
//>>>>>>>>>>DOCIFY-END - xedupjrfkmdo //>>>>>>>>>>
	public void setSunk(boolean affondata) {
		this.affondata = affondata;
	}

//>>>>>>>>>>DOCIFY-START - fobeahjblcal //>>>>>>>>>>
	/**
	* Returns a String representation of this Coordination. The String representation is composed of the following : coordinate : p hit : colpita sunken : affondata
	* 
	* 
	* @return a String representation of this Coordination ( for debugging
	*/
//>>>>>>>>>>DOCIFY-END - fobeahjblcal //>>>>>>>>>>
	public String toString() {
		return "coordinate:" + p + " hit:" + colpita + " sunken:" + affondata;
	}
}
