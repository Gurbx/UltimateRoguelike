package gurbx.ultimateroguelike.inventory;

public enum Item {
	IRON ("iron", 5, 2, true, 32),
	SILVER ("silver", 15, 2, true, 32),
	MAGIC_STONE ("stone", 200, 2, true, 32),
	CRYSTAL ("crystal", 100, 2, true, 32),
	COPPER ("copper", 2, 2, true, 32);
	
	public String path;
	public int sellPrice;
	public int buyPrice;
	public boolean isSellable;
	public int stackSize;
	
	private Item(String path, int sellPrice, int buyPrice, boolean isSellable, int stackSize) {
		this.path = path;
		this.sellPrice = sellPrice;
		this.buyPrice = buyPrice;
		this.isSellable = isSellable;
		this.stackSize = stackSize;
	}
}
