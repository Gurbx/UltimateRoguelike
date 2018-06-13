package gurbx.ultimateroguelike.world;

import java.util.Random;

import gurbx.ultimateroguelike.world.generator.CaveGenerator;

public class WorldGenerator {
	
	public static World generateWorld() {
		Random random = new Random();
		World world = new World();
		//Tile dimensions
		int width = 200; 
		int height = 200;
	
		world.tiles = new String[width][height];
		
		world.tiles = CaveGenerator.generateCaves(world.tiles, (int) ((width*height)/2.3f), random);
		
		return world;
	}

}
