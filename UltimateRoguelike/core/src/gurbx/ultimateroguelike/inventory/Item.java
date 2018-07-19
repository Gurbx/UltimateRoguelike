package gurbx.ultimateroguelike.inventory;

public enum Item {
	BONE ("bone", 1, 2, true, 32);
	
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
