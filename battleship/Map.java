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

import java.util.LinkedList;
import java.util.Random;

public class Map {
	public static final int SIZE_MAP = 10;
	private final char NULL = '0', NAVE = 'X', WATERFALL = 'A', HIT = 'C';
	private char[][] map;
	private LinkedList<Ship> listShips;

	public Map() {
		listShips = new LinkedList<Ship>();
		map = new char[SIZE_MAP][SIZE_MAP];
		for (int i = 0; i < SIZE_MAP; i++)
			for (int j = 0; j < SIZE_MAP; j++)
				map[i][j] = NULL;
	}

	public void fillMapRandom() {
		clear();
		Random r = new Random();
		enterShipRandom(r, 4);
		enterShipRandom(r, 3);
		enterShipRandom(r, 3);
		enterShipRandom(r, 2);
		enterShipRandom(r, 2);
		enterShipRandom(r, 2);
		enterShipRandom(r, 1);
		enterShipRandom(r, 1);
		enterShipRandom(r, 1);
		enterShipRandom(r, 1);
	}

	private void clear() {
		for (int i = 0; i < SIZE_MAP; i++)
			for (int j = 0; j < SIZE_MAP; j++)
				map[i][j] = NULL;

	}

	public boolean enterShip(int x, int y, int dim, int dir) {
		if (dir == 1 && x + dim > SIZE_MAP) {
			return false;
		} // vertical
		if (dir == 0 && y + dim > SIZE_MAP) {
			return false;
		} // horizontal
		boolean inserted;

		if (dir == 0)
			inserted = verificationHorizontal(x, y, dim); // put ship horizon
		else
			inserted = verificationVertical(x, y, dim);

		if (!inserted)
			return false;
		if (dir == 0) {
			Ship n = new Ship(x, y, x, y + dim - 1);
			listShips.add(n);
		} else {
			Ship n = new Ship(x, y, x + dim - 1, y);
			listShips.add(n);
		}
		for (int i = 0; i < dim; i++) {
			if (dir == 0) {
				map[x][y + i] = NAVE;
			} else
				map[x + i][y] = NAVE;
		}
		return true;
	}

	public int[] enterShipRandom(Random random, int dimension) {
		boolean inserted;
		int[] data = new int[4];
		int direction, line, column;
		do {
			inserted = true;
			direction = random.nextInt(2); // 0 = Horizontal, 1 = Vertical
			if (direction == 0) {
				column = random.nextInt(SIZE_MAP - dimension + 1);
				line = random.nextInt(SIZE_MAP);
			} else {
				column = random.nextInt(SIZE_MAP);
				line = random.nextInt(SIZE_MAP - dimension + 1);
			}
			if (direction == 0)
				inserted = verificationHorizontal(line, column, dimension);
			else
				inserted = verificationVertical(line, column, dimension);
		} while (!inserted);
		if (direction == 0) {
			Ship n = new Ship(line, column, line, column + dimension - 1);
			listShips.add(n);
		} else {
			Ship n = new Ship(line, column, line + dimension - 1, column);
			listShips.add(n);
		}
		for (int i = 0; i < dimension; i++) {
			if (direction == 0) {
				map[line][column + i] = NAVE;
			} else
				map[line + i][column] = NAVE;
		}
		data[0] = line;
		data[1] = column;
		data[2] = dimension;
		data[3] = direction;
		return data;
	}

	public boolean verificationVertical(int line, int column, int dimension) {
		if (line != 0)
			if (map[line - 1][column] == NAVE)
				return false;
		if (line != SIZE_MAP - dimension)
			if (map[line + dimension][column] == NAVE)
				return false;
		for (int i = 0; i < dimension; i++) {
			if (column != 0)
				if (map[line + i][column - 1] == NAVE)
					return false;
			if (column != SIZE_MAP - 1)
				if (map[line + i][column + 1] == NAVE)
					return false;
			if (map[line + i][column] == NAVE)
				return false;
		}
		return true;
	}

	public boolean verificationHorizontal(int line, int column, int dimension) {
		if (column != 0)
			if (map[line][column - 1] == NAVE)
				return false;
		if (column != SIZE_MAP - dimension)
			if (map[line][column + dimension] == NAVE)
				return false;
		for (int i = 0; i < dimension; i++) {
			if (line != 0)
				if (map[line - 1][column + i] == NAVE)
					return false;
			if (line != SIZE_MAP - 1)
				if (map[line + 1][column + i] == NAVE)
					return false;
			if (map[line][column + i] == NAVE)
				return false;
		}
		return true;
	}

	public boolean hit1(Position p) {
		int line = p.getCoordX();
		int column = p.getCoordY();
		if (map[line][column] == NAVE) {
			map[line][column] = HIT;
			return true;
		}
		map[line][column] = WATERFALL;
		return false;
	}

	public Ship sunk(Position p) {
		int line = p.getCoordX();
		int col = p.getCoordY();
		Ship nave = null;
		for (int i = 0; i < listShips.size(); i++) {
			if (listShips.get(i).same(line, col)) {
				nave = listShips.get(i);
				break;
			}
		}
		for (int i = nave.getXin(); i <= nave.getXfin(); i++) {
			for (int j = nave.getYin(); j <= nave.getYfin(); j++) {
				if (map[i][j] != HIT) {
					return null;
				}
			}
		}
		listShips.remove(nave);
		return nave;
	}

	public void setWater(Position p) {
		map[p.getCoordX()][p.getCoordY()] = WATERFALL;
	}

	public boolean waterfall(Position p) {
		return map[p.getCoordX()][p.getCoordY()] == WATERFALL;
	}

	public boolean hit(Position p) {
		return map[p.getCoordX()][p.getCoordY()] == HIT;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < SIZE_MAP; i++) {
			for (int j = 0; j < SIZE_MAP; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public void setAdvShips(LinkedList<int[]> advShips) {
		listShips.clear();
		for (int[] a : advShips) {
			enterShip(a[0], a[1], a[2], a[3]);
			System.out.println("i am inserting" + a[0] + a[1] + a[2] + a[3]);
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++)
				System.out.print(map[i][j]);
			System.out.println("");
		}
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