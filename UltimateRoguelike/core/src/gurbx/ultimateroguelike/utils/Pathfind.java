package gurbx.ultimateroguelike.utils;

import java.util.LinkedList;

import gurbx.ultimateroguelike.world.Tile;

public class Pathfind {
	private Tile[][] tiles;
	private Tile start, target;

	private LinkedList<Tile> openList;
	private LinkedList<Tile> closedListe;
	
	private class PathTile {
		private int h;
		public int f;
		public int g;
		public PathTile parent;
	}
	
	public void findPath() {
		addNeighbours(start.getCoordX(), start.getCoordY());
	}

	private void addNeighbours(int x, int y) {
		openList.add(tiles[x-1][y+1]);
		openList.add(tiles[x][y+1]);
		openList.add(tiles[x+1][y+1]);
		openList.add(tiles[x-1][y]);
		openList.add(tiles[x+1][y]);
		openList.add(tiles[x-1][y-1]);
		openList.add(tiles[x][y-1]);
		openList.add(tiles[x+1][y-1]);
	}
	
}
