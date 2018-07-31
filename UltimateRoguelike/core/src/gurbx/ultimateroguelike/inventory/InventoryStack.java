package gurbx.ultimateroguelike.inventory;

import java.io.Serializable;

public class InventoryStack implements Serializable {
	public Item item;
	public int ammount;
	
	public InventoryStack(Item item, int ammount) {
		this.item = item;
		this.ammount = ammount;
	}
}