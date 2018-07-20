package gurbx.ultimateroguelike.inventory;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class InventoryItem {
	public Item item;
	public int ammount;
	public TextureRegion texture;
	
	public InventoryItem(Item item, int ammount, TextureAtlas atlas) {
		this.item = item;
		this.ammount = ammount;
		this.texture = atlas.findRegion(item.path);
	}
}