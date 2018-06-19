package gurbx.ultimateroguelike.world.generator;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import gurbx.ultimateroguelike.world.WorldConstants;
import gurbx.ultimateroguelike.world.utils.Tile;

public class MazeGenerator {
	private Random random;
	private Stack<Tile> stack;
	
	public MazeGenerator(Random random) {
		this.random = new Random();
	}

	public void generateMaze(String[][] tiles) {
		stack = new Stack<Tile>();
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				buildMaze(i, j, tiles);
			}
		}
	}
	
	//Build a maze from the given point
	private void buildMaze(int x, int y, String[][] tiles) {

		ArrayList<Tile> neighbours = getAvailableNeighbors(x, y, tiles);
		
		//If no neighbor is free, go a step back and check availability there
		if (neighbours == null || neighbours.isEmpty()) {
			//POP STACK
			if (stack.isEmpty()) {
				return; //Maze is done when stack is empty
			} else {
				Tile t = stack.pop();
				buildMaze(t.x, t.y, tiles);
			}
			return;

		} else {
			//Place next tile
			int n = random.nextInt(neighbours.size());
			Tile t = neighbours.get(n);
			tiles[t.x][t.y] = WorldConstants.GROUND;
			stack.push(t); // push the tile to the stack
			buildMaze(t.x, t.y, tiles);
		}
	}
	
//	private void popStack(String[][] tiles) {
//		if (stack.isEmpty()) return; //Maze done
//		Tile t = stack.pop();
//	}
	

	private ArrayList<Tile> getAvailableNeighbors(int x, int y, String[][] tiles) {
		ArrayList<Tile> neighbours = new ArrayList<Tile>();
		if (Tile.isAccessible(x-1, y,	 x, y, tiles)) neighbours.add(new Tile(x-1, y));
		if (Tile.isAccessible(x+1, y,	 x, y, tiles)) neighbours.add(new Tile(x+1, y));
		if (Tile.isAccessible(x, y+1,	 x, y, tiles)) neighbours.add(new Tile(x, y+1));
		if (Tile.isAccessible(x, y-1,	 x, y, tiles)) neighbours.add(new Tile(x, y-1));		
		return neighbours;
	}
}
