package gurbx.ultimateroguelike.world.generator;

import gurbx.ultimateroguelike.world.utils.DungeonConstants;

public class WallCreator {
	
	public static void createWalls(String[][] tiles) {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				if (isWall(i, j, tiles)) {
					tiles[i][j] = DungeonConstants.WALL;
				}
			}
		}
	}
	
	private static boolean isWall(int x, int y, String[][] tiles) {
		if (requiresWall(x, y, tiles)) return false; //IF tile is something else

		if (requiresWall(x+1, y+1, tiles)) return true;
		if (requiresWall(x, y+1, tiles)) return true;
		if (requiresWall(x-1, y+1, tiles)) return true;
		if (requiresWall(x-1, y, tiles)) return true;
		if (requiresWall(x, y, tiles)) return true;
		if (requiresWall(x+1, y, tiles)) return true;
		if (requiresWall(x-1, y-1, tiles)) return true;
		if (requiresWall(x, y-1, tiles)) return true;
		if (requiresWall(x+1, y-1, tiles)) return true;
		return false;
	}
	
	private static boolean requiresWall(int x, int y, String[][] tiles) {
		try {
			if ((tiles[x][y].equals(DungeonConstants.EMPTY) == false) && //NOT EMPTY
					tiles[x][y].equals(DungeonConstants.WALL) == false) { // NOT WALL 
					return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

}
