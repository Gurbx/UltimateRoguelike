package gurbx.ultimateroguelike.world;

import java.util.ArrayList;
import java.util.Random;

import gurbx.ultimateroguelike.world.generator.CaveGenerator;
import gurbx.ultimateroguelike.world.generator.DungeonConnector;
import gurbx.ultimateroguelike.world.generator.MazeGenerator;
import gurbx.ultimateroguelike.world.generator.RoomGenerator;
import gurbx.ultimateroguelike.world.utils.Room;
import gurbx.ultimateroguelike.world.utils.Tile;

public class WorldGenerator {
	
	public static World generateWorld() {
		Random random = new Random();
		World world = new World();
		int width = 50; 
		int height = 50;
	
		world.tiles = new String[width][height];
		//Make tiles empty
		for (int i = 0; i < world.tiles.length; i++) {
			for (int j = 0; j < world.tiles[i].length; j++) {
				world.tiles[i][j] = WorldConstants.EMPTY;
			}
		}
		
		ArrayList<Room> rooms = generateRooms(world.tiles, 500, random);
		
//		System.out.println("Number of rooms: " + rooms.size());
		
		MazeGenerator mazeGen = new MazeGenerator(random);
		mazeGen.generateMaze(world.tiles);
		
		DungeonConnector connector = new DungeonConnector(world.tiles, rooms);
		connector.connectDungeon(random);
		
		removeDeadEnds(world.tiles, 999);
		
		return world;
	}

	//Draws the rooms to the given tile map and returns an array list of them
	private static ArrayList<Room> generateRooms(String[][] tiles, int numberOfRooms, Random random) {
		ArrayList<Room> rooms = new ArrayList<Room>();
		final int MIN_SIZE = 6, MAX_SIZE = 16;
		int x, y;
		int width, height;
		
		for (int i = 0; i < numberOfRooms; i++) {
			//Get a random world position
			x = random.nextInt(tiles[0].length);
			y = random.nextInt(tiles.length);
			//Get a random room size
			width = random.nextInt(MAX_SIZE-MIN_SIZE) + MIN_SIZE;
			height = random.nextInt(MAX_SIZE-MIN_SIZE) + MIN_SIZE;
			
			//If out of bounds, retry
			if ((x+width) > tiles[0].length || (y+height) > tiles.length) continue;
			
			//Check if position is free
			boolean positionIsFree = true;
			for (int j = 0; j < width; j++) {
				for (int j2 = 0; j2 < height; j2++) {
					if (tiles[x+j][y+j2].equals(WorldConstants.EMPTY) == false) {
						positionIsFree = false;
					} 
				}
			}
			//Place room if position is free
			if (positionIsFree) {
				//Add room to array
				Room room = new Room();
				room.x = x;
				room.y = y;
				room.width = width;
				room.height = height;
				rooms.add(room);
				
				//Draw room to the tile map
				for (int j = 0; j < width; j++) {
					for (int j2 = 0; j2 < height; j2++) {
						if (j == 0 || j2 == 0 || j == width-1 || j2 == height-1) {
//							tiles[x+j][y+j2] = WorldConstants.WALL;
						} else {
							tiles[x+j][y+j2] = WorldConstants.ROOM;
						}
					}
				}
			}
		}
		return rooms;
	}

	//Remove dead end tiles. amount is how many times map is looped through
	public static void removeDeadEnds(String[][] tiles, int amount) {
		ArrayList<Tile> deadEndTiles = new ArrayList<Tile>();
		
		int i = 0;
		while (true) {
			//Loop
			boolean tileIsRemoved = false;
			for (int j = 0; j < tiles.length; j++) {
				for (int j2 = 0; j2 < tiles.length; j2++) {
					
					if (Tile.isDeadEnd(j, j2, tiles)) {
						deadEndTiles.add(new Tile(j, j2));
						tileIsRemoved = true;
					}
				}
			}
			//Remove dead end tiles
			for (int j = 0; j < deadEndTiles.size(); j++) {
				tiles[deadEndTiles.get(j).x][deadEndTiles.get(j).y] = WorldConstants.EMPTY;
			}
			deadEndTiles.clear();
			i++;
			
			if (tileIsRemoved == false || i >= amount) break;
		}
	}
}
