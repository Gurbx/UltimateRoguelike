package gurbx.ultimateroguelike.world.generator;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;

import gurbx.ultimateroguelike.world.Tile;
import gurbx.ultimateroguelike.world.Dungeon;
import gurbx.ultimateroguelike.world.utils.Room;
import gurbx.ultimateroguelike.world.utils.TileTemp;
import gurbx.ultimateroguelike.world.utils.DungeonConstants;

public class DungeonGenerator {
	
	public static Dungeon generateWorld(TextureAtlas atlas, World b2world) {
		Random random = new Random();
		Dungeon world = new Dungeon();
		int width = 50; 
		int height = 50;
	
		world.tiles = new String[width][height];
		//Make tiles empty
		for (int i = 0; i < world.tiles.length; i++) {
			for (int j = 0; j < world.tiles[i].length; j++) {
				world.tiles[i][j] = DungeonConstants.EMPTY;
			}
		}
		
		ArrayList<Room> rooms = generateRooms(world.tiles, 500, random);
		
//		System.out.println("Number of rooms: " + rooms.size());
		
		MazeGenerator mazeGen = new MazeGenerator(random);
		mazeGen.generateMaze(world.tiles);
		
		DungeonConnector connector = new DungeonConnector(world.tiles, rooms);
		connector.connectDungeon(random);
		
		removeDeadEnds(world.tiles, 999);
		
		WallCreator.createWalls(world.tiles);
		
		initializeTiles(world, atlas, b2world);
		
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
					if (tiles[x+j][y+j2].equals(DungeonConstants.EMPTY) == false) {
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
							tiles[x+j][y+j2] = DungeonConstants.ROOM;
						}
					}
				}
			}
		}
		return rooms;
	}

	//Remove dead end tiles. amount is how many times map is looped through
	public static void removeDeadEnds(String[][] tiles, int amount) {
		ArrayList<TileTemp> deadEndTiles = new ArrayList<TileTemp>();
		
		int i = 0;
		while (true) {
			//Loop
			boolean tileIsRemoved = false;
			for (int j = 0; j < tiles.length; j++) {
				for (int j2 = 0; j2 < tiles.length; j2++) {
					
					if (TileTemp.isDeadEnd(j, j2, tiles)) {
						deadEndTiles.add(new TileTemp(j, j2));
						tileIsRemoved = true;
					}
				}
			}
			//Remove dead end tiles
			for (int j = 0; j < deadEndTiles.size(); j++) {
				tiles[deadEndTiles.get(j).x][deadEndTiles.get(j).y] = DungeonConstants.EMPTY;
			}
			deadEndTiles.clear();
			i++;
			
			if (tileIsRemoved == false || i >= amount) break;
		}
	}
	

	private static void initializeTiles(Dungeon world, TextureAtlas atlas, World b2World) {
		Tile[][] tilemap = new Tile[world.tiles.length][world.tiles[0].length];
		boolean isWall;
		
		for (int i = 0; i < tilemap.length; i++) {
			for (int j = 0; j < tilemap[0].length; j++) {
				if (world.tiles[j][i].equals(DungeonConstants.EMPTY)) {
					tilemap[j][i] = new Tile(j, i, null, true, false, b2World);
				} else {
					isWall = false;
					if (world.tiles[j][i].equals(DungeonConstants.WALL)) isWall = true;
					tilemap[j][i] = new Tile(j,i, atlas.findRegion(world.tiles[j][i]), false, isWall, b2World);
				}
			}
		}
		
		world.tileMap = tilemap;
	}
}
