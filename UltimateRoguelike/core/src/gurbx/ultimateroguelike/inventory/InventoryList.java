package gurbx.ultimateroguelike.inventory;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class InventoryList extends List<InventoryItem>{

	public InventoryList(Skin skin) {
		super(skin);
	}
	
	@Override
	protected GlyphLayout drawItem(Batch batch, BitmapFont font, int index, InventoryItem item, float x, float y) {
		batch.draw(item.texture, x, y - 22);
		return font.draw(batch, item.item.path, x + 42, y);
	}
}
