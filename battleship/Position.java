package battleship;

public class Position {
	private int coordX, coordY;

//>>>>>>>>>>DOCIFY-START - cmhpgkdiuohb //>>>>>>>>>>
	/**
	* Returns the X coordinate of this Point2D. This is equivalent to getCoordX
	*/
//>>>>>>>>>>DOCIFY-END - cmhpgkdiuohb //>>>>>>>>>>
	public int getCoordX() {
		return coordX;
	}

//>>>>>>>>>>DOCIFY-START - qzkbdzduafpk //>>>>>>>>>>
	/**
	* Returns the y coordinate of this Point3D. This is equivalent to getCoordY
	*/
//>>>>>>>>>>DOCIFY-END - qzkbdzduafpk //>>>>>>>>>>
	public int getCoordY() {
		return coordY;
	}

	public Position(int coordX, int coordY) {
		this.coordX = coordX;
		this.coordY = coordY;
	}

	public Position(Position p) {
		this.coordX = p.coordX;
		this.coordY = p.coordY;
	}

//>>>>>>>>>>DOCIFY-START - gzisepapuwog //>>>>>>>>>>
	/**
	* Moves the coordinate according to the character. N S O E is the left or right of the coordinate.
	* 
	* @param where - character to move the coordinate by ( N S O E
	*/
//>>>>>>>>>>DOCIFY-END - gzisepapuwog //>>>>>>>>>>
	public void move(char where) {
//>>>>>>>>>>DOCIFY-START - osriomlaoeej //>>>>>>>>>>
		// The number of coordinates in the coordinate system.
//>>>>>>>>>>DOCIFY-END - osriomlaoeej //>>>>>>>>>>
		switch (where) {
			case 'N':
				coordX--;
				break;
			case 'S':
				coordX++;
				break;
			case 'O':
				coordY--;
				break;
			case 'E':
				coordY++;
				break;
		}
	}

//>>>>>>>>>>DOCIFY-START - agpprzxhbftu //>>>>>>>>>>
	/**
	* Returns a string representation of this Coordinate. The string is of the form x y where x is 1 - based and y is the ordinate of the upper left corner.
	* 
	* 
	* @return a string representation of this Coordinate as defined by WKT
	*/
//>>>>>>>>>>DOCIFY-END - agpprzxhbftu //>>>>>>>>>>
	public String toString() {
		char Y = (char) (coordY + 65);
		return "" + (coordX + 1) + " " + Y;
	}

//>>>>>>>>>>DOCIFY-START - kmjzeyhxedis //>>>>>>>>>>
	/**
	* Returns true if this object is equal to the specified object. This is the case if the specified object is a Position and both the coordinates are equal
	* 
	* @param obj - the object to compare
	*/
//>>>>>>>>>>DOCIFY-END - kmjzeyhxedis //>>>>>>>>>>
	@Override
	public boolean equals(Object obj) {
//>>>>>>>>>>DOCIFY-START - kqxsitnjqeyx //>>>>>>>>>>
		// Returns true if this object is the same as this object.
//>>>>>>>>>>DOCIFY-END - kqxsitnjqeyx //>>>>>>>>>>
		if (this == obj)
			return true;
//>>>>>>>>>>DOCIFY-START - tnvlnnxplzyc //>>>>>>>>>>
		// Returns true if the object is null.
//>>>>>>>>>>DOCIFY-END - tnvlnnxplzyc //>>>>>>>>>>
		if (obj == null)
			return false;
//>>>>>>>>>>DOCIFY-START - aviifxlghovv //>>>>>>>>>>
		// Returns true if the class of the object is equal to the class of the object.
//>>>>>>>>>>DOCIFY-END - aviifxlghovv //>>>>>>>>>>
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
//>>>>>>>>>>DOCIFY-START - cmyleyelmvzx //>>>>>>>>>>
		// Returns true if the coordX field is equal to the other.
//>>>>>>>>>>DOCIFY-END - cmyleyelmvzx //>>>>>>>>>>
		if (coordX != other.coordX)
			return false;
//>>>>>>>>>>DOCIFY-START - ihtlabzxzhky //>>>>>>>>>>
		// Returns true if the other coordinate is different from the other coordinate system.
//>>>>>>>>>>DOCIFY-END - ihtlabzxzhky //>>>>>>>>>>
		if (coordY != other.coordY)
			return false;
		return true;
	}

//>>>>>>>>>>DOCIFY-START - kqjejgfwnfar //>>>>>>>>>>
	/**
	* Returns true if the point is out of the map. This is used to prevent an obstacle
	*/
//>>>>>>>>>>DOCIFY-END - kqjejgfwnfar //>>>>>>>>>>
	public boolean outMap() {
//>>>>>>>>>>DOCIFY-START - yiqfwbanyuvk //>>>>>>>>>>
		// Checks if the coordinates are within the map.
//>>>>>>>>>>DOCIFY-END - yiqfwbanyuvk //>>>>>>>>>>
		if (coordX >= Map.SIZE_MAP || coordY >= Map.SIZE_MAP || coordX < 0 || coordY < 0)
			return true;
		return false;
	}

}
