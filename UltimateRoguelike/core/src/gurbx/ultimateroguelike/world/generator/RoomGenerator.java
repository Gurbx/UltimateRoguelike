package gurbx.ultimateroguelike.world.generator;

import java.util.Random;

import gurbx.ultimateroguelike.world.utils.Room;

public class RoomGenerator {
	private int min_width = 4, max_width = 12;
	private int min_height = 4, max_height = 12;
	
	private Random random;
	
	public RoomGenerator() {
		random = new Random();
	}
	
	public Room generateRoom() {
		int width = random.nextInt(max_width-min_width) + min_width;
		int height = random.nextInt(max_height-min_height) + min_height;
		
		Room room = new Room();
//		room.tiles = new String[width][height];
		
		return room;
	}
}
