package gurbx.ultimateroguelike.world.utils;

import gurbx.ultimateroguelike.world.WorldConstants;

public class Tile {
	public int x, y;
	boolean visited = false;
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//Checks if a tile is available to build at when building form the given tile
	// fromX == previous tile
	public static boolean isAccessible(int x, int y, int fromX, int fromY, String[][] tiles) {
		try {
			if (tiles[x][y].equals(WorldConstants.EMPTY) == false) return false;
			
			//If it is the first tile that is being placed
			if (tiles[fromX][fromY].equals(WorldConstants.GROUND) == false) {
				if (tiles[x+1][y].equals(WorldConstants.EMPTY) == false) return false;
				if (tiles[x+1][y+1].equals(WorldConstants.EMPTY) == false) return false;
				if (tiles[x][y+1].equals(WorldConstants.EMPTY) == false) return false;
				if (tiles[x-1][y].equals(WorldConstants.EMPTY) == false) return false;
				if (tiles[x-1][y-1].equals(WorldConstants.EMPTY) == false) return false;
				if (tiles[x][y-1].equals(WorldConstants.EMPTY) == false) return false;
				if (tiles[x+1][y-1].equals(WorldConstants.EMPTY) == false) return false;
				if (tiles[x-1][y+1].equals(WorldConstants.EMPTY) == false) return false;
			} else 
				//Check if it can be placed in the spot but
				//ignore the side the path is coming from
				if (fromX == x-1 && fromY == y) {
				// x-1, y+1 	ignore them!
				// x-1, y-1 	ignore them!
				if (isTileEmpty(x+1, y, fromX, fromY, tiles) == false) return false;
				if (isTileEmpty(x+1, y+1, fromX, fromY, tiles) == false) return false;
				if (isTileEmpty(x, y+1, fromX, fromY, tiles) == false) return false;
				if (isTileEmpty(x-1, y, fromX, fromY, tiles) == false) return false;
				//if (isTileEmpty(x-1, y-1, fromX, fromY, tiles) == false) return false;
				if (isTileEmpty(x, y-1, fromX, fromY, tiles) == false) return false;
				if (isTileEmpty(x+1, y-1, fromX, fromY, tiles) == false) return false;
				//if (isTileEmpty(x-1, y+1, fromX, fromY, tiles) == false) return false;
			} else if (fromX == x+1 && fromY == y) {
				// x+1, y+1 	ignore
				// x+1, y-1 	ignore
				if (isTileEmpty(x+1, y, fromX, fromY, tiles) == false) return false;
				//if (isTileEmpty(x+1, y+1, fromX, fromY, tiles) == false) return false;
				if (isTileEmpty(x, y+1, fromX, fromY, tiles) == false) return false;
				if (isTileEmpty(x-1, y, fromX, fromY, tiles) == false) return false;
				if (isTileEmpty(x-1, y-1, fromX, fromY, tiles) == false) return false;
				if (isTileEmpty(x, y-1, fromX, fromY, tiles) == false) return false;
				//if (isTileEmpty(x+1, y-1, fromX, fromY, tiles) == false) return false;
				if (isTileEmpty(x-1, y+1, fromX, fromY, tiles) == false) return false;
			} else if (fromX == x && fromY == y+1) {
				//x-1, y+1 		ignore
				//x+1, y+1 		ignore
				if (isTileEmpty(x+1, y, fromX, fromY, tiles) == false) return false;
				//if (isTileEmpty(x+1, y+1, fromX, fromY, tiles) == false) return false;
				if (isTileEmpty(x, y+1, fromX, fromY, tiles) == false) return false;
				if (isTileEmpty(x-1, y, fromX, fromY, tiles) == false) return false;
				if (isTileEmpty(x-1, y-1, fromX, fromY, tiles) == false) return false;
				if (isTileEmpty(x, y-1, fromX, fromY, tiles) == false) return false;
				if (isTileEmpty(x+1, y-1, fromX, fromY, tiles) == false) return false;
				//if (isTileEmpty(x-1, y+1, fromX, fromY, tiles) == false) return false;
			} else if (fromX == x && fromY == y-1) {
				//x-1, y-1 		ignore
				//x+1, y-1 		ignore
				if (isTileEmpty(x+1, y, fromX, fromY, tiles) == false) return false;
				if (isTileEmpty(x+1, y+1, fromX, fromY, tiles) == false) return false;
				if (isTileEmpty(x, y+1, fromX, fromY, tiles) == false) return false;
				if (isTileEmpty(x-1, y, fromX, fromY, tiles) == false) return false;
				//if (isTileEmpty(x-1, y-1, fromX, fromY, tiles) == false) return false;
				if (isTileEmpty(x, y-1, fromX, fromY, tiles) == false) return false;
				//if (isTileEmpty(x+1, y-1, fromX, fromY, tiles) == false) return false;
				if (isTileEmpty(x-1, y+1, fromX, fromY, tiles) == false) return false;
			} else {
				return false;
			}
////			if (tiles[x][y].equals(WorldConstants.EMPTY) == false) return false;
//			if (isTileEmpty(x+1, y, fromX, fromY, tiles) == false) return false;
//			if (isTileEmpty(x+1, y+1, fromX, fromY, tiles) == false) return false;
//			if (isTileEmpty(x, y+1, fromX, fromY, tiles) == false) return false;
//			if (isTileEmpty(x-1, y, fromX, fromY, tiles) == false) return false;
//			if (isTileEmpty(x-1, y-1, fromX, fromY, tiles) == false) return false;
//			if (isTileEmpty(x, y-1, fromX, fromY, tiles) == false) return false;
//			if (isTileEmpty(x+1, y-1, fromX, fromY, tiles) == false) return false;
//			if (isTileEmpty(x-1, y+1, fromX, fromY, tiles) == false) return false;
		} catch (IndexOutOfBoundsException e) { //If outside tile map
			return false;
		}
		return true;
	}
	//Helper function for 'isAccessible'
	private static boolean isTileEmpty(int x, int y, int fromX, int fromY, String[][] tiles) {
		if (x == fromX && y == fromY) return true; //Ignore tile that is being built from
		if (tiles[x][y].equals(WorldConstants.EMPTY) == false) return false;
		return true;
	}

	//Used when removing dead ends from World generator
	public static boolean isDeadEnd(int x, int y, String[][] tiles) {
		if (tiles[x][y].equals(WorldConstants.GROUND) == false) return false; // can't be dead end if not ground
		
		int connections = 0;
		try { if (tiles[x+1][y].equals(WorldConstants.EMPTY) == false) connections++;
		} catch (IndexOutOfBoundsException e) {}
		try { if (tiles[x-1][y].equals(WorldConstants.EMPTY) == false) connections++;
		} catch (IndexOutOfBoundsException e) {}
		try { if (tiles[x][y+1].equals(WorldConstants.EMPTY) == false) connections++;
		} catch (IndexOutOfBoundsException e) {}
		try { if (tiles[x][y-1].equals(WorldConstants.EMPTY) == false) connections++;
		} catch (IndexOutOfBoundsException e) {}
		
		if (connections < 2) return true;
		return false;
	}
}
