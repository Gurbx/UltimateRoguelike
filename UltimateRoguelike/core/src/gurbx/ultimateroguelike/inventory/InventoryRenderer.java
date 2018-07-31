package gurbx.ultimateroguelike.inventory;

import com.badlogic.ashley.signals.Listener;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class InventoryRenderer {
	private static final int SLOT_SIZE = 32;
	private Inventory inventory;
	private TextureRegion[][] icons;
	private TextureRegion iconBackground;
	private float posX, posY;
	private float width, height;
	private TextureAtlas atlas;
	private BitmapFont font;
	
	public InventoryRenderer(Inventory inventory, float renderPositionX, float renderPositionY, TextureAtlas atlas,
			BitmapFont font) {
		this.inventory = inventory;
		this.atlas = atlas;
		this.posX = renderPositionX;
		this.posY = renderPositionY;
		this.width = inventory.getWidthTiles() * SLOT_SIZE;
		this.height = inventory.getHeightTIles() * SLOT_SIZE;
		this.font = font;
		iconBackground = atlas.findRegion("inventorySlot");
		this.icons = new TextureRegion[inventory.getWidthTiles()][inventory.getHeightTIles()];
		refreshIconTextures();
	}
	
	private void refreshIconTextures() {
		for (int y = 0; y < icons[0].length; y++) {
			for (int x = 0; x < icons.length; x++) {
				if (inventory.getinventorySlots()[x][y] == null) {
					icons[x][y] = null;
				} else {
					icons[x][y] = atlas.findRegion(inventory.getinventorySlots()[x][y].item.path);
				}
			}
		}
	}
	
	public void render(SpriteBatch batch) {
		//Render backgroubd
		for (int y = 0; y < icons[0].length; y++) {
			for (int x = 0; x < icons.length; x++) {
				batch.draw(iconBackground, posX+x*SLOT_SIZE,  posY-y*SLOT_SIZE);
			}
		}
		//Render icons and stack size font
		for (int y = 0; y < icons[0].length; y++) {
			for (int x = 0; x < icons.length; x++) {
				if (icons[x][y] == null) continue;
				float px = posX+x*SLOT_SIZE;
				float py = posY-y*SLOT_SIZE;
				batch.draw(icons[x][y], px, py);
				if (inventory.getinventorySlots()[x][y].ammount >= 10) px -= 9;
				font.draw(batch, "" + inventory.getinventorySlots()[x][y].ammount, px+21, py+13);
			}
		}
	}
	
	//TODO
	public InventoryStack getItemStack(float x, float y) {
		//Check if selection is within inventory
		if (((posX < x) && (x < (posX+width)))
				&& ((posY < y) && (y < (posY+height)))) {
			System.out.println("inside inventory");
		}
		return null;
		
	}
}
