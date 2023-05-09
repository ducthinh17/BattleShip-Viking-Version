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

public class Position {
	private int coordX, coordY;

	/**
	 * Returns the X coordinate of this Point2D. This is equivalent to getCoordX
	 */
	public int getCoordX() {
		return coordX;
	}

	/**
	 * Returns the y coordinate of this Point3D. This is equivalent to getCoordY
	 */
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

	/**
	 * Moves the coordinate according to the character. N S O E is the left or right
	 * of the coordinate.
	 * 
	 * @param where - character to move the coordinate by ( N S O E
	 */
	public void move(char where) {
		// The number of coordinates in the coordinate system.
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

	/**
	 * Returns a string representation of this Coordinate. The string is of the form
	 * x y where x is 1 - based and y is the ordinate of the upper left corner.
	 * 
	 * 
	 * @return a string representation of this Coordinate as defined by WKT
	 */
	public String toString() {
		char Y = (char) (coordY + 65);
		return "" + (coordX + 1) + " " + Y;
	}

	/**
	 * Returns true if this object is equal to the specified object. This is the
	 * case if the specified object is a Position and both the coordinates are equal
	 * 
	 * @param obj - the object to compare
	 */
	@Override
	public boolean equals(Object obj) {
		// Returns true if this object is the same as this object.
		if (this == obj)
			return true;
		// Returns true if the object is null.
		if (obj == null)
			return false;
		// Returns true if the class of the object is equal to the class of the object.
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		// Returns true if the coordX field is equal to the other.
		if (coordX != other.coordX)
			return false;
		// Returns true if the other coordinate is different from the other coordinate
		// system.
		if (coordY != other.coordY)
			return false;
		return true;
	}

	/**
	 * Returns true if the point is out of the map. This is used to prevent an
	 * obstacle
	 */
	public boolean outMap() {
		// Checks if the coordinates are within the map.
		if (coordX >= Map.SIZE_MAP || coordY >= Map.SIZE_MAP || coordX < 0 || coordY < 0)
			return true;
		return false;
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