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
		for (int i = 0; i < Map.SIZE_MAP; i++) {
			for (int j = 0; j < Map.SIZE_MAP; j++) {
				Position p = new Position(i, j);
				listHit.add(p);
			}
		}
		r = new Random();
		hit = 0;
	}

	public Report myTurn() {

		Report rep = new Report();
		if (hit == 0) {
			boolean hitt = shootRandom();
			rep.setP(lastShot);
			rep.setAffected(hitt);
			Ship sunk;
			if (hitt) {
				hit++;
				sunk = plMap.sunk(lastShot);
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
		if (hit == 1) {
			boolean hitt = shootAimed1();
			Ship sunk;
			rep.setP(lastShot);
			rep.setAffected(hitt);
			rep.setSunk(false);
			if (hitt) {
				hit++;
				possibility = null;
				sunk = plMap.sunk(lastShot);
				if (sunk != null) {
					rep.setSunk(true);
					removeOutlines(sunk);
					hit = 0;
					direction = null;
				}
			}
			return rep;
		}
		if (hit >= 2) {
			boolean hitt = shootAimed2();
			Ship sunk;
			rep.setP(lastShot);
			rep.setAffected(hitt);
			rep.setSunk(false);
			if (hitt) {
				hit++;
				sunk = plMap.sunk(lastShot);
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

	private boolean shootRandom() {
		int shott = r.nextInt(listHit.size());
		Position p = listHit.remove(shott);
		lastShot = p;
		boolean hitt = plMap.hit1(p);
		return hitt;
	}

	private boolean shootAimed1() {
		boolean error = true;
		Position p = null;
		do {
			int shott = r.nextInt(possibility.size());
			String where = possibility.remove(shott);
			p = new Position(firstHit);
			p.move(where.charAt(0));
			direction = where;
			if (!plMap.waterfall(p)) {
				listHit.remove(p);
				error = false;
			}
		} while (error);// verifica che non si cerchi di sparare su una
						// posizione già colpita
		lastShot = p;
		return plMap.hit1(p);
	}

	private boolean shootAimed2() {
		boolean hitable = false;
		Position p = new Position(lastShot);
		do {
			p.move(direction.charAt(0));

			if (p.outMap() || plMap.waterfall(p)) {
				invertDirection();
			} else {
				if (!plMap.hit(p)) {
					hitable = true;
				}

			}
		} while (!hitable);
		listHit.remove(p);
		lastShot = p;
		return plMap.hit1(p);
	}

	private void removeOutlines(Ship sunk) {
		int Xin = sunk.getXin();
		int Xfin = sunk.getXfin();
		int Yin = sunk.getYin();
		int Yfin = sunk.getYfin();
		if (Xin == Xfin) {// orizzontale
			if (Yin != 0) {
				Position p = new Position(Xin, Yin - 1);
				if (!plMap.waterfall(p)) {
					listHit.remove(p);
					plMap.setWater(p);

				}
			}
			if (Yfin != Map.SIZE_MAP - 1) {
				Position p = new Position(Xin, Yfin + 1);
				if (!plMap.waterfall(p)) {
					listHit.remove(p);
					plMap.setWater(p);
				}
			}
			if (Xin != 0) {
				for (int i = 0; i <= Yfin - Yin; i++) {
					Position p = new Position(Xin - 1, Yin + i);
					if (!plMap.waterfall(p)) {
						listHit.remove(p);
						plMap.setWater(p);
					}
				}

			}
			if (Xin != Map.SIZE_MAP - 1) {
				for (int i = 0; i <= Yfin - Yin; i++) {
					Position p = new Position(Xin + 1, Yin + i);
					if (!plMap.waterfall(p)) {
						listHit.remove(p);
						plMap.setWater(p);
					}
				}
			}
		} else {
			if (Xin != 0) {
				Position p = new Position(Xin - 1, Yin);
				if (!plMap.waterfall(p)) {
					listHit.remove(p);
					plMap.setWater(p);
				}
			}
			if (Xfin != Map.SIZE_MAP - 1) {
				Position p = new Position(Xfin + 1, Yin);
				if (!plMap.waterfall(p)) {
					listHit.remove(p);
					plMap.setWater(p);
				}
			}
			if (Yin != 0) {
				for (int i = 0; i <= Xfin - Xin; i++) {
					Position p = new Position(Xin + i, Yin - 1);
					if (!plMap.waterfall(p)) {
						listHit.remove(p);
						plMap.setWater(p);
					}
				}

			}
			if (Yfin != Map.SIZE_MAP - 1) {
				for (int i = 0; i <= Xfin - Xin; i++) {
					Position p = new Position(Xin + i, Yin + 1);
					if (!plMap.waterfall(p)) {
						listHit.remove(p);
						plMap.setWater(p);
					}
				}
			}
		}
	}

	private void initializeList() {
		if (lastShot.getCoordX() != 0) {
			possibility.add("N");
		}
		if (lastShot.getCoordX() != Map.SIZE_MAP - 1) {
			possibility.add("S");
		}
		if (lastShot.getCoordY() != 0) {
			possibility.add("O");
		}
		if (lastShot.getCoordY() != Map.SIZE_MAP - 1) {
			possibility.add("E");
		}
	}

	private void invertDirection() {
		if (direction.equals("N")) {
			direction = "S";
		} else if (direction.equals("S")) {
			direction = "N";
		} else if (direction.equals("E")) {
			direction = "O";
		} else if (direction.equals("O")) {
			direction = "E";
		}
	}

}