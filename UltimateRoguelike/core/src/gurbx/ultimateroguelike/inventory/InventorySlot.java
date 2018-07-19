package gurbx.ultimateroguelike.inventory;

public class InventorySlot {
	public Item item;
	public int ammount;
	
	public InventorySlot(Item item, int ammount) {
		this.item = item;
		this.ammount = ammount;
	}
}
