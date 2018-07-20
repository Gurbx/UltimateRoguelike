package gurbx.ultimateroguelike.inventory;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Inventory {
	private ArrayList<InventorySlot> inventorySlots = new ArrayList<InventorySlot>();
	private int inventorySize;
	
	private boolean addItem(Item item) {
		//If there is an item of same type is in the inventory, increase the stack size of that item
		for (int i = 0; i < inventorySlots.size(); i++) {
			if (inventorySlots.get(i).item == item) {
				if (inventorySlots.get(i).ammount < item.stackSize) {
					inventorySlots.get(i).ammount ++;
					return true;
				}
			}
		}
		//Return if inventory is full
		if (inventorySlots.size() >= inventorySize) return false;
		
		//add item to inventory
		inventorySlots.add(new InventorySlot(item, 1));
		return true;
	}
	
	public ArrayList<InventorySlot> getSlots() {
		return inventorySlots;
	}
}
