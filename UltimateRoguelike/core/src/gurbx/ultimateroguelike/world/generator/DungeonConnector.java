package gurbx.ultimateroguelike.world.generator;

import java.util.ArrayList;
import java.util.Random;

import gurbx.ultimateroguelike.world.utils.Room;
import gurbx.ultimateroguelike.world.utils.WorldConstants;

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
	private ArrayList<Room> rooms;

	public DungeonConnector(String[][] tiles, ArrayList<Room> rooms) {
		this.tiles = tiles;
		this.rooms = rooms;
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
	
	public void connectDungeon(Random random) {
		createDoors(random);
		
		//FLOOD FILL
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
	
	private void createDoors(Random random) {
		for (int i = 0; i < rooms.size(); i++) {
			rooms.get(i).createDoors(tiles, random, 2);
		}	
	}

	private void floodFill(int x, int y) {
		if (connected[x][y] == true) return;
		if (tiles[x][y].equals(WorldConstants.EMPTY)) return;
//		tiles[x][y] = WorldConstants.DOOR; //TEST
		connected[x][y] = true;
		numberOfConnectedTiles ++;
		floodFill(x+1, y);
		floodFill(x-1, y);
		floodFill(x, y+1);
		floodFill(x, y-1);
	}
}
