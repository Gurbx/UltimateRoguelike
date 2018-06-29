package gurbx.ultimateroguelike.world.generator;

import java.util.Random;

import gurbx.ultimateroguelike.world.utils.WorldConstants;

public class CaveGenerator {


	public static String[][] generateCaves(String[][] tiles, int caveDensity, Random random) {
//		int worldSize = tiles.length;
		int numberOfCavesToGenerate = caveDensity;
	
		//Fill the map with random holes (caves)
		int numberGenerated = 0;
		while (numberGenerated < numberOfCavesToGenerate) {
			int x = random.nextInt(tiles.length);
			int y = random.nextInt(tiles[0].length);
			if (tiles[x][y] == null) {
				tiles[x][y] = WorldConstants.GROUND;
				numberGenerated++;
			}
		}
		//Cellular automata
		for (int i = 0; i < 5; i++) {
			tiles = cellularAutomata(tiles);
		}
		return tiles;
	}
	
	//Make caves based on cellular automata
	private static String[][] cellularAutomata(String[][] tiles) {
		String[][] newMap = new String[tiles.length][tiles[0].length];
		
		String[] neigbours = new String[9];
		
		for (int i = 1; i < tiles[0].length-1; i++) {
			for (int j = 1; j < tiles.length-1; j++) {

				neigbours[0] = tiles[i-1][j+1];
				neigbours[1] = tiles[i][j+1];
				neigbours[2] = tiles[i+1][j+1];
				
				neigbours[3] = tiles[i-1][j];
				neigbours[4] = tiles[i][j];
				neigbours[5] = tiles[i+1][j];
				
				neigbours[6] = tiles[i-1][j-1];
				neigbours[7] = tiles[i][j-1];
				neigbours[8] = tiles[i+1][j-1];
				
				newMap[i][j] = rules(neigbours);
			}
		}
		return newMap;
	}

	//Check if block should be a ground or not, based on rules
	private static String rules(String[] neigbours) {
		int walls = 0;
		for (int i = 0; i < neigbours.length; i++) {
			if (neigbours[i] == null) walls++;
		}
		if (walls >= 5) return null;
		return WorldConstants.GROUND;
	}
}
