package gurbx.ultimateroguelike.world.generator;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import gurbx.ultimateroguelike.world.utils.TileTemp;
import gurbx.ultimateroguelike.world.utils.DungeonConstants;

public class MazeGenerator {
	private Random random;
	private Stack<TileTemp> stack;
	
	public MazeGenerator(Random random) {
		this.random = new Random();
	}

	public void generateMaze(String[][] tiles) {
		stack = new Stack<TileTemp>();
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				buildMaze(i, j, tiles);
			}
		}
	}
	
	//Build a maze from the given point
	private void buildMaze(int x, int y, String[][] tiles) {

		ArrayList<TileTemp> neighbours = getAvailableNeighbors(x, y, tiles);
		
		//If no neighbor is free, go a step back and check availability there
		if (neighbours == null || neighbours.isEmpty()) {
			//POP STACK
			if (stack.isEmpty()) {
				return; //Maze is done when stack is empty
			} else {
				TileTemp t = stack.pop();
				buildMaze(t.x, t.y, tiles);
			}
			return;

		} else {
			//Place next tile
			int n = random.nextInt(neighbours.size());
			TileTemp t = neighbours.get(n);
			tiles[t.x][t.y] = DungeonConstants.GROUND;
			stack.push(t); // push the tile to the stack
			buildMaze(t.x, t.y, tiles);
		}
	}
	
//	private void popStack(String[][] tiles) {
//		if (stack.isEmpty()) return; //Maze done
//		Tile t = stack.pop();
//	}
	

	private ArrayList<TileTemp> getAvailableNeighbors(int x, int y, String[][] tiles) {
		ArrayList<TileTemp> neighbours = new ArrayList<TileTemp>();
		if (TileTemp.isAccessible(x-1, y,	 x, y, tiles)) neighbours.add(new TileTemp(x-1, y));
		if (TileTemp.isAccessible(x+1, y,	 x, y, tiles)) neighbours.add(new TileTemp(x+1, y));
		if (TileTemp.isAccessible(x, y+1,	 x, y, tiles)) neighbours.add(new TileTemp(x, y+1));
		if (TileTemp.isAccessible(x, y-1,	 x, y, tiles)) neighbours.add(new TileTemp(x, y-1));		
		return neighbours;
	}
}
