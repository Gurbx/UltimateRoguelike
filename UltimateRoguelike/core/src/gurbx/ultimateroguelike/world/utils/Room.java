package gurbx.ultimateroguelike.world.utils;

import java.util.Random;

public class Room {
	public boolean connected = false;
	public int x, y;
	public int width, height;
	
	public void createDoors(String[][] tiles, Random random, int doorsToCreate) {
		int doorsCreated = 0;
		int side = random.nextInt(4); //Start side
		
		while (doorsCreated < doorsToCreate) {
			//Top
			if (side == 0) {
				if (createDoor(width-2, x+1, y+height-1, true, random, tiles)) {
					//If door created
					doorsCreated ++;
				}
			}
			//Bottom
			if (side == 1) {
				if (createDoor(width-2, x+1, y, true, random, tiles)) {
					//If door created
					doorsCreated ++;
				}
			}
			//Right
			if (side == 2) {
				if (createDoor(height-2, x+width-1, y+1, false, random, tiles)) {
					//If door created
					doorsCreated ++;
				}
			}
			//Left
			if (side == 2) {
				if (createDoor(height-2, x, y+1, false, random, tiles)) {
					//If door created
					doorsCreated ++;
				}
			}
			
			//Increment side to loop around sides
			side ++;
			if (side > 3) side = 0;
		}
	}
	
	//Returns true if door was successfully placed
	private boolean createDoor(int sideLenght, int x, int y, boolean vertical, Random random, String[][] tiles) {
		int doorX = x;
		int doorY = y;
		
		final int NUMBER_OF_ATEMPTS = sideLenght;
		for (int i = 0; i < NUMBER_OF_ATEMPTS; i++) {
			doorX = x;
			doorY = y;
			int position = random.nextInt(sideLenght);
			
			if (vertical) {
				doorX += position;
			} else {
				doorY += position;
			}
			
			if (canDoorBePlaced(doorX, doorY, tiles)) {
				tiles[doorX][doorY] = WorldConstants.DOOR;
				return true; // door successfully created
			}
		}
		return false;
 	}
	
	private boolean canDoorBePlaced(int x, int y, String[][] tiles) {
		try { if (tiles[x+1][y].equals(WorldConstants.GROUND)) return true;
		} catch (ArrayIndexOutOfBoundsException e) {}
		try { if (tiles[x-1][y].equals(WorldConstants.GROUND)) return true;
		} catch (ArrayIndexOutOfBoundsException e) {}
		try { if (tiles[x][y+1].equals(WorldConstants.GROUND)) return true;
		} catch (ArrayIndexOutOfBoundsException e) {}
		try { if (tiles[x][y-1].equals(WorldConstants.GROUND)) return true;
		} catch (ArrayIndexOutOfBoundsException e) {}

		return false;
	}
}
