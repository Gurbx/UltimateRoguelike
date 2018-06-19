package gurbx.ultimateroguelike.world.generator;

import gurbx.ultimateroguelike.world.WorldConstants;

/**
 * Connects all the regions in the dungeon and creating doors when doing so
 * 
 * @author Philip
 * 
 *
 */
public class DungeonConnector {
	private int numberOfConnectedTiles;
	private int totalTiles; //when fully connected, connectedTiles == totalTIles
	private boolean[][] connected; //Matrix of regions that are connected;
	private String[][] tiles;

	public DungeonConnector(String[][] tiles) {
		this.tiles = tiles;
		connected = new boolean[tiles.length][tiles[0].length];
		numberOfConnectedTiles = 0;
		totalTiles = 0;
		
		//count total tiles
		for (int i = 0; i < connected.length; i++) {
			for (int j = 0; j < connected.length; j++) {
				if (tiles[i][j].equals(WorldConstants.EMPTY) == false) totalTiles++;
			}
		}
	}
	
	public void connectArea() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].equals(WorldConstants.EMPTY) == false) {
					floodFill(i, j);
					System.out.println("CONEECTEED: " + numberOfConnectedTiles + ", Total: " + totalTiles);
					return;
					
				}
			}
		}
	}
	
	private void floodFill(int x, int y) {
		if (connected[x][y] == true) return;
		if (tiles[x][y].equals(WorldConstants.EMPTY)) return;
		tiles[x][y] = WorldConstants.DOOR; //TEST
		connected[x][y] = true;
		numberOfConnectedTiles ++;
		floodFill(x+1, y);
		floodFill(x-1, y);
		floodFill(x, y+1);
		floodFill(x, y-1);
	}
}
