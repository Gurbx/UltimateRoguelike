package gurbx.ultimateroguelike.inventory;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ItemIcon extends Actor {
	private InventoryStack itemStack;
	private TextureRegion texture;
	private Label label;
	
	public ItemIcon(InventoryStack itemStack, TextureAtlas atlas, Skin skin) {
		this.itemStack = itemStack;
		texture = atlas.findRegion(itemStack.item.path);
		setBounds(texture.getRegionX(), texture.getRegionY(), 
				texture.getRegionWidth(), texture.getRegionHeight());
		
		if (itemStack.ammount != 1) {
			label = new Label(""+itemStack.ammount, skin);
		} else {
			label = new Label("",skin);
		}
	}
	
	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		label.setPosition(x+16, y-7);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		label.draw(batch, parentAlpha);
	}
	
}
