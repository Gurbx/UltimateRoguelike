package gurbx.ultimateroguelike.utils.pathfinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import com.badlogic.gdx.scenes.scene2d.ui.List;

import gurbx.ultimateroguelike.world.Tile;

public class Pathfinder {
	public Node[][] nodes;
	
	public Pathfinder(Tile[][] dungeonTiles) {
		initializeNodes(dungeonTiles);
	}
	
	private void initializeNodes(Tile[][] dungeonTiles) {
		nodes = new Node[dungeonTiles.length][dungeonTiles.length];
		
		// init nodes and costs
		for (int i = 0; i < dungeonTiles.length; i++) {
			for (int j = 0; j < dungeonTiles.length; j++) {
				
				if (dungeonTiles[i][j] == null) continue;
				if (dungeonTiles[i][j].isWalkable() == false) continue; 
				nodes[i][j] = new Node();
				nodes[i][j].cost = dungeonTiles[i][j].getWalkCost();
			}
		}
		
		//Set neighbors
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes.length; j++) {
				if (nodes[i][j] == null) continue;
				nodes[i][j].neighbors = addNeighbours(i, j);
			}
		}
	}

	protected class Node {
		ArrayList<Node> neighbors = new ArrayList<Pathfinder.Node>();
		Node parent;
		int f;
		int g;
		int h;
		int x, y;
		int cost;
	}
	
	//A*
	public ArrayList<Node> getPath(Node start, Node goal) {
		Set<Node> open = new HashSet<Node>();
		Set<Node> closed = new HashSet<Node>();
		
		start.g = 0;
		start.h = estimateDistance(start, goal);
		start.f = start.h;
		
		open.add(start);
		
		while(true) {
			Node current = null;
			
			if (open.size() == 0) {
				throw new RuntimeException("no route to goal");
			}
			
			for (Node node : open) {
				if (current == null || node.f < current.f) {
					current = node;
				}
			}
			
			if (current == goal) {
				break;
			}
			
			open.remove(current);
			closed.add(current);
			
			for (Node neighbor : current.neighbors) {
				if (neighbor == null) {
					continue;
				}
				
				int nextG = current.g + neighbor.cost;
				
				if (nextG < neighbor.g) {
					open.remove(neighbor);
					closed.remove(neighbor);
				}
				
				if (!open.contains(neighbor) && !closed.contains(neighbor)) {
					neighbor.g = nextG;
					neighbor.h = estimateDistance(neighbor, goal);
					neighbor.f = neighbor.g + neighbor.h;
					neighbor.parent = current;
					open.add(neighbor);
				}
			}
		}
		
		ArrayList<Node> nodes = new ArrayList<Pathfinder.Node>();
		Node current = goal;
		while (current.parent != null) {
			nodes.add(current);
			current = current.parent;
		}
		nodes.add(start);
		return nodes;
	}

	private int estimateDistance(Node node1, Node node2) {
		return Math.abs(node1.x - node2.x) + Math.abs(node1.y - node2.y);
	}
	
	
	
	

	private ArrayList<Node> addNeighbours(int x, int y) {
		ArrayList<Node> neighbors = new ArrayList<Pathfinder.Node>();
		try { neighbors.add(nodes[x-1][y+1]); } catch (Exception e) {	}
		try { neighbors.add(nodes[x][y+1]); } catch (Exception e) {	}
		try { neighbors.add(nodes[x+1][y+1]); } catch (Exception e) {	}
		try { neighbors.add(nodes[x-1][y]); } catch (Exception e) {	}
		try { neighbors.add(nodes[x+1][y]); } catch (Exception e) {	}
		try { neighbors.add(nodes[x-1][y-1]); } catch (Exception e) {	}
		try { neighbors.add(nodes[x][y-1]); } catch (Exception e) {	}
		try { neighbors.add(nodes[x+1][y-1]); } catch (Exception e) {	}
		return neighbors;
	}
	
}
