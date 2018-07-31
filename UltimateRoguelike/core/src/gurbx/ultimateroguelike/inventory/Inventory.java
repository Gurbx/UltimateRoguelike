package gurbx.ultimateroguelike.inventory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Inventory implements Serializable {
	private InventoryStack[][] inventorySlots;
	
	public Inventory(int widthTiles, int heightTiles) {
		inventorySlots = new InventoryStack[widthTiles][heightTiles];
	}
	
	//Returns false if inventory is full
	public boolean addItem(Item item, int ammount) {
		int ammountStored = 0;
		//If there is an item of same type is in the inventory, increase the stack size of that item
		for (int y = 0; y < inventorySlots[0].length; y++) {
			for (int x = 0; x < inventorySlots.length; x++) {
				if (inventorySlots[x][y] != null && inventorySlots[x][y].item == item) {
					
					while (inventorySlots[x][y].ammount < item.stackSize) {
						inventorySlots[x][y].ammount++;
						ammountStored++;
						if (ammountStored >= ammount) return true;
					}
				}	
			}
		}
		//Create a new Stack if no item of the type exists in the inventory
		for (int y = 0; y < inventorySlots[0].length; y++) {
			for (int x = 0; x < inventorySlots.length; x++) {
				if (inventorySlots[x][y] == null) {
					inventorySlots[x][y] = new InventoryStack(item, ammount);
					return true;
				}
			}
		}
		//Inventory is full if item can't be placed
		return false;
	}
	
	public boolean addItem(Item item, int ammount, int x, int y) {
		if (x <= inventorySlots.length || y <= inventorySlots.length) return false;

		if (inventorySlots[x][y] == null) {
			inventorySlots[x][y] = new InventoryStack(item, ammount);
			return true;
		} else if (inventorySlots[x][y].item == item) {
			inventorySlots[x][y].ammount += ammount; //TODO 
			return true;
		}
		return false;
	}

	public InventoryStack[][] getinventorySlots() {return inventorySlots; }
	public int getWidthTiles() { return inventorySlots.length; }
	public int getHeightTIles() { return inventorySlots[0].length; }
}
