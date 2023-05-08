package battleship;

public class Ship {

	private int xin, yin;
	private int xfin, yfin;

	public Ship(int xin, int yin, int xfin, int yfin) {
		this.xin = xin;
		this.yin = yin;
		this.xfin = xfin;
		this.yfin = yfin;
	}

//>>>>>>>>>>DOCIFY-START - zhqeklxcuivc //>>>>>>>>>>
	/**
	* Returns the X - coordinate of the upper left corner of this coordinate system. The value is positive if the coordinate is right - handed and negative if it is left - handed
	*/
//>>>>>>>>>>DOCIFY-END - zhqeklxcuivc //>>>>>>>>>>
	public int getXin() {
		return xin;
	}

//>>>>>>>>>>DOCIFY-START - xxdtuainpqlt //>>>>>>>>>>
	/**
	* Returns the y - coordinate of the upper left corner of the rectangle. The value is clamped to the interval [ - 2^31 2^31 ]
	*/
//>>>>>>>>>>DOCIFY-END - xxdtuainpqlt //>>>>>>>>>>
	public int getYin() {
		return yin;
	}

//>>>>>>>>>>DOCIFY-START - xeyldduiblxk //>>>>>>>>>>
	/**
	* Returns the X coordinate of the upper left corner of the bounding box. This is used to draw the boundary
	*/
//>>>>>>>>>>DOCIFY-END - xeyldduiblxk //>>>>>>>>>>
	public int getXfin() {
		return xfin;
	}

//>>>>>>>>>>DOCIFY-START - rgstsbmrbefe //>>>>>>>>>>
	/**
	* Returns the y - coordinate of the bottom edge of the bounding box. This is used to draw the border
	*/
//>>>>>>>>>>DOCIFY-END - rgstsbmrbefe //>>>>>>>>>>
	public int getYfin() {
		return yfin;
	}

//>>>>>>>>>>DOCIFY-START - idnahxipixic //>>>>>>>>>>
	/**
	* Returns true if the coordinates are in the same region. This is used to determine if a point lies on the same line as another point
	* 
	* @param x - the x coordinate to check
	* @param y - the y coordinate to check ( may be null
	*/
//>>>>>>>>>>DOCIFY-END - idnahxipixic //>>>>>>>>>>
	public boolean same(int x, int y) {
//>>>>>>>>>>DOCIFY-START - eoavynhufvfz //>>>>>>>>>>
		// Checks if the point is within the bounds of the bounds of the point.
//>>>>>>>>>>DOCIFY-END - eoavynhufvfz //>>>>>>>>>>
		if (x <= xfin && x >= xin && y <= yfin && y >= yin) {
			return true;
		}
		return false;

	}

//>>>>>>>>>>DOCIFY-START - prxwqcfwrriv //>>>>>>>>>>
	/**
	* Returns a string representation of this Rect. The string representation is " xin - yin - xfin "
	* 
	* 
	* @return a string representation of this
	*/
//>>>>>>>>>>DOCIFY-END - prxwqcfwrriv //>>>>>>>>>>
	public String toString() {
		return xin + "-" + yin + "  " + xfin + "-" + yfin;
	}

//>>>>>>>>>>DOCIFY-START - edbhqqibagjw //>>>>>>>>>>
	/**
	* Returns true if this ship is equal to the specified object. This is the case if the class of the specified object is the same as the class of this ship.
	* 
	* @param obj - the ship to compare to this one null returns
	*/
//>>>>>>>>>>DOCIFY-END - edbhqqibagjw //>>>>>>>>>>
	@Override
	public boolean equals(Object obj) {
//>>>>>>>>>>DOCIFY-START - orywudaergof //>>>>>>>>>>
		// Returns true if this object is the same as this object.
//>>>>>>>>>>DOCIFY-END - orywudaergof //>>>>>>>>>>
		if (this == obj)
			return true;
//>>>>>>>>>>DOCIFY-START - tukfwwsithnf //>>>>>>>>>>
		// Returns true if the object is null.
//>>>>>>>>>>DOCIFY-END - tukfwwsithnf //>>>>>>>>>>
		if (obj == null)
			return false;
//>>>>>>>>>>DOCIFY-START - efzbwqxebfuz //>>>>>>>>>>
		// Returns true if the class of the object is equal to the class of the object.
//>>>>>>>>>>DOCIFY-END - efzbwqxebfuz //>>>>>>>>>>
		if (getClass() != obj.getClass())
			return false;
		Ship other = (Ship) obj;
//>>>>>>>>>>DOCIFY-START - tqhahgcmmhgk //>>>>>>>>>>
		// Returns true if the xfin field is equal to the other.
//>>>>>>>>>>DOCIFY-END - tqhahgcmmhgk //>>>>>>>>>>
		if (xfin != other.xfin)
			return false;
//>>>>>>>>>>DOCIFY-START - hcfitwuftoje //>>>>>>>>>>
		// Returns true if the xin is equal to the other.
//>>>>>>>>>>DOCIFY-END - hcfitwuftoje //>>>>>>>>>>
		if (xin != other.xin)
			return false;
//>>>>>>>>>>DOCIFY-START - rhdsfwcokgga //>>>>>>>>>>
		// Returns true if yfin is the same as other.
//>>>>>>>>>>DOCIFY-END - rhdsfwcokgga //>>>>>>>>>>
		if (yfin != other.yfin)
			return false;
//>>>>>>>>>>DOCIFY-START - qlwixmqpwprx //>>>>>>>>>>
		// Returns true if yin is equal to other. yin
//>>>>>>>>>>DOCIFY-END - qlwixmqpwprx //>>>>>>>>>>
		if (yin != other.yin)
			return false;
		return true;
	}

}
