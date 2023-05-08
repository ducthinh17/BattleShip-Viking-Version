/* 
Group's Number: 11
Member names & IU code:
	PHẠM LÊ ĐỨC THỊNH - ITDSIU20085
	NGUYỄN THANH BÌNH - ITDSIU20085
	NGUYỄN SONG HÀO KIỆT - ITDSIU20098
	CHÂU KHẮC ĐÌNH PHONG - ITDSIU20076
Purpose: We gained a solid understanding of the DSA concepts and how to apply them to real-world scenarios. 
*/

package battleship;

public class Ship {
    private int xin; // X-coordinate of the upper left corner
    private int yin; // Y-coordinate of the upper left corner
    private int xfin; // X-coordinate of the lower right corner
    private int yfin; // Y-coordinate of the lower right corner

    // Constructor
    public Ship(int xin, int yin, int xfin, int yfin) {
        this.xin = xin;
        this.yin = yin;
        this.xfin = xfin;
        this.yfin = yfin;
    }

    // Getter methods
    public int getXin() {
        return xin;
    }

    public int getYin() {
        return yin;
    }

    public int getXfin() {
        return xfin;
    }

    public int getYfin() {
        return yfin;
    }

    // Check if the given point lies within the ship's bounds
    public boolean same(int x, int y) {
        if (x >= xin && x <= xfin && y >= yin && y <= yfin) {
            return true;
        }
        return false;
    }

    // String representation of the Ship object
    public String toString() {
        return xin + "-" + yin + "  " + xfin + "-" + yfin;
    }

    // Equals method to compare two Ship objects
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Ship other = (Ship) obj;
        return xin == other.xin && yin == other.yin && xfin == other.xfin && yfin == other.yfin;
    }
}

/*
 * Group's Number: 11
 * Member names & IU code:
 * PHẠM LÊ ĐỨC THỊNH - ITDSIU20085
 * NGUYỄN THANH BÌNH - ITDSIU20085
 * NGUYỄN SONG HÀO KIỆT - ITDSIU20098
 * CHÂU KHẮC ĐÌNH PHONG - ITDSIU20076
 * Purpose: We gained a solid understanding of the DSA concepts and how to apply
 * them to real-world scenarios.
 */