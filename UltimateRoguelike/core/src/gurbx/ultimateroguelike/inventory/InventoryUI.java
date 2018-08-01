package gurbx.ultimateroguelike.inventory;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class InventoryUI extends Actor {
	private static final int SLOT_SIZE = 32;
	private Inventory inventory;
	private TextureAtlas atlas;
	private Skin skin;
	private ItemIcon[][] itemIcons;
	
	public InventoryUI(final Inventory inventory, TextureAtlas atlas, Skin skin) {
		this.inventory = inventory;
		this.atlas = atlas;
		this.skin = skin;
		itemIcons = new ItemIcon[inventory.getWidthTiles()][inventory.getHeightTIles()];
		setWidth(inventory.getWidthTiles()*SLOT_SIZE);
		setHeight(inventory.getHeightTIles()*SLOT_SIZE);
		refreshIconTextures();
		
		addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				
				int cordX = (int) (x/SLOT_SIZE);
				int cordY = (int) ((getHeight()/SLOT_SIZE) - (y/SLOT_SIZE));
				
//				System.out.println(cordX + ", " + cordY);
				
				if (inventory.getinventorySlots()[cordX][cordY] != null) {
					System.out.println(inventory.getinventorySlots()[cordX][cordY].item);
				}

				return true;
			}
		});
	}
	
	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		setBounds(x, y, getWidth(), getHeight());
		
		for (int j = 0; j < itemIcons[0].length; j++) {
			for (int i = 0; i < itemIcons.length; i++) {
				if (inventory.getinventorySlots()[i][j] != null) {
					itemIcons[i][j].setPosition(getX()+i*SLOT_SIZE, getY() + getHeight()*0.5f-j*SLOT_SIZE);
				}
			}
		}
	}
	
	private void refreshIconTextures() {
		for (int y = 0; y < itemIcons[0].length; y++) {
			for (int x = 0; x < itemIcons.length; x++) {
				if (inventory.getinventorySlots()[x][y] == null) {
					itemIcons[x][y] = null;
				} else {
					itemIcons[x][y] = new ItemIcon(inventory.getinventorySlots()[x][y], atlas, skin);
				}
			}
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		for (int j = 0; j < itemIcons[0].length; j++) {
			for (int i = 0; i < itemIcons.length; i++) {
				if (inventory.getinventorySlots()[i][j] != null) {
					itemIcons[i][j].draw(batch, parentAlpha);
				}
			}
		}
	}
}
