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

public class Computer {
	private LinkedList<Position> listHit;
	private Random r;
	private int hit;
	private LinkedList<String> possibility;
	private Position lastShot;
	private String direction;
	private Map plMap;
	private Position firstHit;

	public Computer(Map mapAdversary) {
		listHit = new LinkedList<Position>();
		this.plMap = mapAdversary;
		// Add positions to the listHit.
		for (int i = 0; i < Map.SIZE_MAP; i++) {
			// Add a position to the list of positions.
			for (int j = 0; j < Map.SIZE_MAP; j++) {
				Position p = new Position(i, j);
				listHit.add(p);
			}
		}
		r = new Random();
		hit = 0;
	}

	/**
	 * This method is called every turn. If you want to know what the player did you
	 * can use this method.
	 * 
	 * 
	 * @return a report that can be sent to the player or null
	 */
	public Report myTurn() {

		Report rep = new Report();
		// This method will create a new rep object.
		if (hit == 0) {
			boolean hitt = shootRandom();
			rep.setP(lastShot);
			rep.setAffected(hitt);
			Ship sunk;
			// This method is called when the hitt is hitt.
			if (hitt) {
				hit++;
				sunk = plMap.sunk(lastShot);
				// Set the sunk to true.
				if (sunk != null) {
					rep.setSunk(true);
					removeOutlines(sunk);
					hit = 0;
					direction = null;
				} else {
					firstHit = lastShot;
					possibility = new LinkedList<String>();
					initializeList();
				}
			}
			return rep;
		}
		// hit is 1. Set the hit to 1.
		if (hit == 1) {
			boolean hitt = shootAimed1();
			Ship sunk;
			rep.setP(lastShot);
			rep.setAffected(hitt);
			rep.setSunk(false);
			// Called when a hitt occurs.
			if (hitt) {
				hit++;
				possibility = null;
				sunk = plMap.sunk(lastShot);
				// Set the sunk to true and remove outlines
				if (sunk != null) {
					rep.setSunk(true);
					removeOutlines(sunk);
					hit = 0;
					direction = null;
				}
			}
			return rep;
		}
		// hit is the number of shooted shoots and sunks.
		if (hit >= 2) {
			boolean hitt = shootAimed2();
			Ship sunk;
			rep.setP(lastShot);
			rep.setAffected(hitt);
			rep.setSunk(false);
			// hitt is true if the hitt is hitt.
			if (hitt) {
				hit++;
				sunk = plMap.sunk(lastShot);
				// Set the sunk to true and remove outlines
				if (sunk != null) {
					rep.setSunk(true);
					removeOutlines(sunk);
					hit = 0;
					direction = null;
				}
			} else {
				invertDirection();
			}
			return rep;
		}
		return null;
	}

	/**
	 * Shoot a random position from the list and return true if hit. This is used to
	 * check if we have hit an object
	 */
	private boolean shootRandom() {
		int shott = r.nextInt(listHit.size());
		Position p = listHit.remove(shott);
		lastShot = p;
		boolean hitt = plMap.hit1(p);
		return hitt;
	}

	/**
	 * Shoot aimed 1 : Straight in colpita a
	 */
	private boolean shootAimed1() {
		boolean error = true;
		Position p = null;
		do {
			int shott = r.nextInt(possibility.size());
			String where = possibility.remove(shott);
			p = new Position(firstHit);
			p.move(where.charAt(0));
			direction = where;
			// Remove the given plMap from the hit list.
			if (!plMap.waterfall(p)) {
				listHit.remove(p);
				error = false;
			}
		} while (error);// verifica che non si cerchi di sparare su una
						// posizione già colpita
		lastShot = p;
		return plMap.hit1(p);
	}

	/**
	 * Shoot aimed 2 ( second shot ) and return true if hit
	 */
	private boolean shootAimed2() {
		boolean hitable = false;
		Position p = new Position(lastShot);
		do {
			p.move(direction.charAt(0));

			// Checks if the map is hitable.
			if (p.outMap() || plMap.waterfall(p)) {
				invertDirection();
			} else {
				// If the map is hitable then it will be hitable.
				if (!plMap.hit(p)) {
					hitable = true;
				}

			}
		} while (!hitable);
		listHit.remove(p);
		lastShot = p;
		return plMap.hit1(p);
	}

	/**
	 * Removes outlines from the map. This is called when a ship is sunk from the
	 * map
	 * 
	 * @param sunk - Ship to remove outlines
	 */
	private void removeOutlines(Ship sunk) {
		int Xin = sunk.getXin();
		int Xfin = sunk.getXfin();
		int Yin = sunk.getYin();
		int Yfin = sunk.getYfin();
		// This method is used to determine the water water of the map.
		if (Xin == Xfin) {// orizzontale
			// Removes the position from the map if it is waterfalling.
			if (Yin != 0) {
				Position p = new Position(Xin, Yin - 1);
				// If the map is waterfalling the map.
				if (!plMap.waterfall(p)) {
					listHit.remove(p);
					plMap.setWater(p);

				}
			}
			// This method is called by the map to determine if the map is waterfalling.
			if (Yfin != Map.SIZE_MAP - 1) {
				Position p = new Position(Xin, Yfin + 1);
				// If the map is waterfalling the map.
				if (!plMap.waterfall(p)) {
					listHit.remove(p);
					plMap.setWater(p);
				}
			}
			// Removes all positions that are waterfalling.
			if (Xin != 0) {
				// Find the waterfalls in the map.
				for (int i = 0; i <= Yfin - Yin; i++) {
					Position p = new Position(Xin - 1, Yin + i);
					// If the map is waterfalling the map.
					if (!plMap.waterfall(p)) {
						listHit.remove(p);
						plMap.setWater(p);
					}
				}

			}
			// Removes all tiles in the map.
			if (Xin != Map.SIZE_MAP - 1) {
				// Find the waterfalls in the map.
				for (int i = 0; i <= Yfin - Yin; i++) {
					Position p = new Position(Xin + 1, Yin + i);
					// If the map is waterfalling the map.
					if (!plMap.waterfall(p)) {
						listHit.remove(p);
						plMap.setWater(p);
					}
				}
			}
		} else {
			// Removes the position at the given position if it is on the map.
			if (Xin != 0) {
				Position p = new Position(Xin - 1, Yin);
				// If the map is waterfalling the map.
				if (!plMap.waterfall(p)) {
					listHit.remove(p);
					plMap.setWater(p);
				}
			}
			// Move the map to the next position.
			if (Xfin != Map.SIZE_MAP - 1) {
				Position p = new Position(Xfin + 1, Yin);
				// If the map is waterfalling the map.
				if (!plMap.waterfall(p)) {
					listHit.remove(p);
					plMap.setWater(p);
				}
			}
			// Removes all the positions that are waterfalling.
			if (Yin != 0) {
				// Find the waterfalls in the map.
				for (int i = 0; i <= Xfin - Xin; i++) {
					Position p = new Position(Xin + i, Yin - 1);
					// If the map is waterfalling the map.
					if (!plMap.waterfall(p)) {
						listHit.remove(p);
						plMap.setWater(p);
					}
				}

			}
			// Removes all tiles in the map.
			if (Yfin != Map.SIZE_MAP - 1) {
				// Find the waterfalls in the map.
				for (int i = 0; i <= Xfin - Xin; i++) {
					Position p = new Position(Xin + i, Yin + 1);
					// If the map is waterfalling the map.
					if (!plMap.waterfall(p)) {
						listHit.remove(p);
						plMap.setWater(p);
					}
				}
			}
		}
	}

	/**
	 * Initializes the possibility list to contain possible positons that haven't
	 * been
	 */
	private void initializeList() {
		// Add N to possibility list if the last shot is not zero.
		if (lastShot.getCoordX() != 0) {
			possibility.add("N");
		}
		// Add S to possibility list if the last shot is not a map size map
		if (lastShot.getCoordX() != Map.SIZE_MAP - 1) {
			possibility.add("S");
		}
		// Add O to possibility list if the last shot is not zero
		if (lastShot.getCoordY() != 0) {
			possibility.add("O");
		}
		// Add E to possibility list if the last shot is not a map size map
		if (lastShot.getCoordY() != Map.SIZE_MAP - 1) {
			possibility.add("E");
		}
	}

	/**
	 * Inverts the direction of the maze. This is done by reversing the direction
	 */
	private void invertDirection() {
		// The direction of the direction.
		if (direction.equals("N")) {
			direction = "S";
			// The direction of the direction.
		} else if (direction.equals("S")) {
			direction = "N";
			// E or O are the direction of the direction.
		} else if (direction.equals("E")) {
			direction = "O";
			// Set direction to E.
		} else if (direction.equals("O")) {
			direction = "E";
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