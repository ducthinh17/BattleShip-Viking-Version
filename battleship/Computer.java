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
	* This method is called every turn. If you want to know what the player did you can use this method.
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
	* Shoot a random position from the list and return true if hit. This is used to check if we have hit an object
	*/
//>>>>>>>>>>DOCIFY-END - unupubqixcqe //>>>>>>>>>>
	private boolean shootRandom() {
		int shott = r.nextInt(listHit.size());
		Position p = listHit.remove(shott);
		lastShot = p;
		boolean hitt = plMap.hit1(p);
		return hitt;
	}

//>>>>>>>>>>DOCIFY-START - lkysuftejisv //>>>>>>>>>>
	/**
	* Shoot aimed 1 : Straight in colpita a
	*/
//>>>>>>>>>>DOCIFY-END - lkysuftejisv //>>>>>>>>>>
	private boolean shootAimed1() {
		boolean error = true;
		Position p = null;
		do {
			int shott = r.nextInt(possibility.size());
			String where = possibility.remove(shott);
			p = new Position(firstHit);
			p.move(where.charAt(0));
			direction = where;
//>>>>>>>>>>DOCIFY-START - lptdbcetuhdn //>>>>>>>>>>
			// Remove the given plMap from the hit list.
//>>>>>>>>>>DOCIFY-END - lptdbcetuhdn //>>>>>>>>>>
			if (!plMap.waterfall(p)) {
				listHit.remove(p);
				error = false;
			}
		} while (error);// verifica che non si cerchi di sparare su una
						// posizione già colpita
		lastShot = p;
		return plMap.hit1(p);
	}

//>>>>>>>>>>DOCIFY-START - pjrifekxgtwp //>>>>>>>>>>
	/**
	* Shoot aimed 2 ( second shot ) and return true if hit
	*/
//>>>>>>>>>>DOCIFY-END - pjrifekxgtwp //>>>>>>>>>>
	private boolean shootAimed2() {
		boolean hitable = false;
		Position p = new Position(lastShot);
		do {
			p.move(direction.charAt(0));

//>>>>>>>>>>DOCIFY-START - wmbsquzkzuyw //>>>>>>>>>>
			// Checks if the map is hitable.
//>>>>>>>>>>DOCIFY-END - wmbsquzkzuyw //>>>>>>>>>>
			if (p.outMap() || plMap.waterfall(p)) {
				invertDirection();
			} else {
//>>>>>>>>>>DOCIFY-START - jzhttonanivn //>>>>>>>>>>
				// If the map is hitable then it will be hitable.
//>>>>>>>>>>DOCIFY-END - jzhttonanivn //>>>>>>>>>>
				if (!plMap.hit(p)) {
					hitable = true;
				}

			}
		} while (!hitable);
		listHit.remove(p);
		lastShot = p;
		return plMap.hit1(p);
	}

//>>>>>>>>>>DOCIFY-START - djsjgljejyhy //>>>>>>>>>>
	/**
	* Removes outlines from the map. This is called when a ship is sunk from the map
	* 
	* @param sunk - Ship to remove outlines
	*/
//>>>>>>>>>>DOCIFY-END - djsjgljejyhy //>>>>>>>>>>
	private void removeOutlines(Ship sunk) {
		int Xin = sunk.getXin();
		int Xfin = sunk.getXfin();
		int Yin = sunk.getYin();
		int Yfin = sunk.getYfin();
//>>>>>>>>>>DOCIFY-START - gzjusnemalfb //>>>>>>>>>>
		// This method is used to determine the water water of the map.
//>>>>>>>>>>DOCIFY-END - gzjusnemalfb //>>>>>>>>>>
		if (Xin == Xfin) {// orizzontale
//>>>>>>>>>>DOCIFY-START - bljegsaikndz //>>>>>>>>>>
			// Removes the position from the map if it is waterfalling.
//>>>>>>>>>>DOCIFY-END - bljegsaikndz //>>>>>>>>>>
			if (Yin != 0) {
				Position p = new Position(Xin, Yin - 1);
//>>>>>>>>>>DOCIFY-START - vxtijyqavvzw //>>>>>>>>>>
				// If the map is waterfalling the map.
//>>>>>>>>>>DOCIFY-END - vxtijyqavvzw //>>>>>>>>>>
				if (!plMap.waterfall(p)) {
					listHit.remove(p);
					plMap.setWater(p);

				}
			}
//>>>>>>>>>>DOCIFY-START - metiivvwodqa //>>>>>>>>>>
			// This method is called by the map to determine if the map is waterfalling.
//>>>>>>>>>>DOCIFY-END - metiivvwodqa //>>>>>>>>>>
			if (Yfin != Map.SIZE_MAP - 1) {
				Position p = new Position(Xin, Yfin + 1);
//>>>>>>>>>>DOCIFY-START - lrschuyaonwi //>>>>>>>>>>
				// If the map is waterfalling the map.
//>>>>>>>>>>DOCIFY-END - lrschuyaonwi //>>>>>>>>>>
				if (!plMap.waterfall(p)) {
					listHit.remove(p);
					plMap.setWater(p);
				}
			}
//>>>>>>>>>>DOCIFY-START - grgtqsjbjkjl //>>>>>>>>>>
			// Removes all positions that are waterfalling.
//>>>>>>>>>>DOCIFY-END - grgtqsjbjkjl //>>>>>>>>>>
			if (Xin != 0) {
//>>>>>>>>>>DOCIFY-START - njmdzwuuldxc //>>>>>>>>>>
				// Find the waterfalls in the map.
//>>>>>>>>>>DOCIFY-END - njmdzwuuldxc //>>>>>>>>>>
				for (int i = 0; i <= Yfin - Yin; i++) {
					Position p = new Position(Xin - 1, Yin + i);
//>>>>>>>>>>DOCIFY-START - byxrgsfhxjpe //>>>>>>>>>>
					// If the map is waterfalling the map.
//>>>>>>>>>>DOCIFY-END - byxrgsfhxjpe //>>>>>>>>>>
					if (!plMap.waterfall(p)) {
						listHit.remove(p);
						plMap.setWater(p);
					}
				}

			}
//>>>>>>>>>>DOCIFY-START - atjbtsbqryol //>>>>>>>>>>
			// Removes all tiles in the map.
//>>>>>>>>>>DOCIFY-END - atjbtsbqryol //>>>>>>>>>>
			if (Xin != Map.SIZE_MAP - 1) {
//>>>>>>>>>>DOCIFY-START - rdyritxxksnr //>>>>>>>>>>
				// Find the waterfalls in the map.
//>>>>>>>>>>DOCIFY-END - rdyritxxksnr //>>>>>>>>>>
				for (int i = 0; i <= Yfin - Yin; i++) {
					Position p = new Position(Xin + 1, Yin + i);
//>>>>>>>>>>DOCIFY-START - byvenenyhkhr //>>>>>>>>>>
					// If the map is waterfalling the map.
//>>>>>>>>>>DOCIFY-END - byvenenyhkhr //>>>>>>>>>>
					if (!plMap.waterfall(p)) {
						listHit.remove(p);
						plMap.setWater(p);
					}
				}
			}
		} else {
//>>>>>>>>>>DOCIFY-START - sgwnslvlubtl //>>>>>>>>>>
			// Removes the position at the given position if it is on the map.
//>>>>>>>>>>DOCIFY-END - sgwnslvlubtl //>>>>>>>>>>
			if (Xin != 0) {
				Position p = new Position(Xin - 1, Yin);
//>>>>>>>>>>DOCIFY-START - evbapoamtoap //>>>>>>>>>>
				// If the map is waterfalling the map.
//>>>>>>>>>>DOCIFY-END - evbapoamtoap //>>>>>>>>>>
				if (!plMap.waterfall(p)) {
					listHit.remove(p);
					plMap.setWater(p);
				}
			}
//>>>>>>>>>>DOCIFY-START - aedhjtbtzvjm //>>>>>>>>>>
			// Move the map to the next position.
//>>>>>>>>>>DOCIFY-END - aedhjtbtzvjm //>>>>>>>>>>
			if (Xfin != Map.SIZE_MAP - 1) {
				Position p = new Position(Xfin + 1, Yin);
//>>>>>>>>>>DOCIFY-START - olfgajmmdvtr //>>>>>>>>>>
				// If the map is waterfalling the map.
//>>>>>>>>>>DOCIFY-END - olfgajmmdvtr //>>>>>>>>>>
				if (!plMap.waterfall(p)) {
					listHit.remove(p);
					plMap.setWater(p);
				}
			}
//>>>>>>>>>>DOCIFY-START - ohynihgxapwm //>>>>>>>>>>
			// Removes all the positions that are waterfalling.
//>>>>>>>>>>DOCIFY-END - ohynihgxapwm //>>>>>>>>>>
			if (Yin != 0) {
//>>>>>>>>>>DOCIFY-START - orjyrsjwfctw //>>>>>>>>>>
				// Find the waterfalls in the map.
//>>>>>>>>>>DOCIFY-END - orjyrsjwfctw //>>>>>>>>>>
				for (int i = 0; i <= Xfin - Xin; i++) {
					Position p = new Position(Xin + i, Yin - 1);
//>>>>>>>>>>DOCIFY-START - njwrehibubwk //>>>>>>>>>>
					// If the map is waterfalling the map.
//>>>>>>>>>>DOCIFY-END - njwrehibubwk //>>>>>>>>>>
					if (!plMap.waterfall(p)) {
						listHit.remove(p);
						plMap.setWater(p);
					}
				}

			}
//>>>>>>>>>>DOCIFY-START - rfkihfirvdcr //>>>>>>>>>>
			// Removes all tiles in the map.
//>>>>>>>>>>DOCIFY-END - rfkihfirvdcr //>>>>>>>>>>
			if (Yfin != Map.SIZE_MAP - 1) {
//>>>>>>>>>>DOCIFY-START - bqkqbgbzedcv //>>>>>>>>>>
				// Find the waterfalls in the map.
//>>>>>>>>>>DOCIFY-END - bqkqbgbzedcv //>>>>>>>>>>
				for (int i = 0; i <= Xfin - Xin; i++) {
					Position p = new Position(Xin + i, Yin + 1);
//>>>>>>>>>>DOCIFY-START - vgknfgvfctkt //>>>>>>>>>>
					// If the map is waterfalling the map.
//>>>>>>>>>>DOCIFY-END - vgknfgvfctkt //>>>>>>>>>>
					if (!plMap.waterfall(p)) {
						listHit.remove(p);
						plMap.setWater(p);
					}
				}
			}
		}
	}

//>>>>>>>>>>DOCIFY-START - sywyezylyjkj //>>>>>>>>>>
	/**
	* Initializes the possibility list to contain possible positons that haven't been
	*/
//>>>>>>>>>>DOCIFY-END - sywyezylyjkj //>>>>>>>>>>
	private void initializeList() {
//>>>>>>>>>>DOCIFY-START - zbwpwpvtujns //>>>>>>>>>>
		// Add N to possibility list if the last shot is not zero.
//>>>>>>>>>>DOCIFY-END - zbwpwpvtujns //>>>>>>>>>>
		if (lastShot.getCoordX() != 0) {
			possibility.add("N");
		}
//>>>>>>>>>>DOCIFY-START - lptmzrwisxsx //>>>>>>>>>>
		// Add S to possibility list if the last shot is not a map size map
//>>>>>>>>>>DOCIFY-END - lptmzrwisxsx //>>>>>>>>>>
		if (lastShot.getCoordX() != Map.SIZE_MAP - 1) {
			possibility.add("S");
		}
//>>>>>>>>>>DOCIFY-START - ajvbymdtrfqr //>>>>>>>>>>
		// Add O to possibility list if the last shot is not zero
//>>>>>>>>>>DOCIFY-END - ajvbymdtrfqr //>>>>>>>>>>
		if (lastShot.getCoordY() != 0) {
			possibility.add("O");
		}
//>>>>>>>>>>DOCIFY-START - phsiqxlfakgf //>>>>>>>>>>
		// Add E to possibility list if the last shot is not a map size map
//>>>>>>>>>>DOCIFY-END - phsiqxlfakgf //>>>>>>>>>>
		if (lastShot.getCoordY() != Map.SIZE_MAP - 1) {
			possibility.add("E");
		}
	}

//>>>>>>>>>>DOCIFY-START - iftymzmpxhek //>>>>>>>>>>
	/**
	* Inverts the direction of the maze. This is done by reversing the direction
	*/
//>>>>>>>>>>DOCIFY-END - iftymzmpxhek //>>>>>>>>>>
	private void invertDirection() {
//>>>>>>>>>>DOCIFY-START - ylzepwcynplo //>>>>>>>>>>
		// The direction of the direction.
//>>>>>>>>>>DOCIFY-END - ylzepwcynplo //>>>>>>>>>>
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