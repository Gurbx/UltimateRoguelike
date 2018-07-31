package gurbx.ultimateroguelike.menu;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

import gurbx.ultimateroguelike.Application;
import gurbx.ultimateroguelike.components.InventoryItemComponent;
import gurbx.ultimateroguelike.inventory.InventoryStack;
import gurbx.ultimateroguelike.inventory.Inventory;
import gurbx.ultimateroguelike.inventory.InventoryRenderer;
import gurbx.ultimateroguelike.inventory.Item;
import gurbx.ultimateroguelike.utils.sound.SoundHandler;
import gurbx.ultimateroguelike.utils.sound.Sounds;

public class InventoryTestMenu {
	private final Application app;
	private Skin skin;
	private Stage stage;
	private float x, y;
	private TextureAtlas atlas;
	
	private Table table;
//	private InventoryList inventory;
//	private InventoryList sell;
	
	InventoryRenderer invRenderer1, invRenderer2;
	Inventory inventory1, inventory2;

	
	public InventoryTestMenu(float x, float y, Stage stage, Skin skin, Application app, final TextureAtlas atlas) {
		this.app = app;
		this.stage = stage;
		this.skin = skin;
		this.atlas = atlas;
		this.x = x;
		this.y = y;
		
		inventory1 = new Inventory(7, 2);
		inventory1.addItem(Item.COPPER, 64);
		inventory1.addItem(Item.SILVER, 5);
		inventory1.addItem(Item.CRYSTAL, 9);
		
		invRenderer1 = new InventoryRenderer(inventory1, 100, 100, atlas, app.font1);
		
		inventory2 = new Inventory(4, 2);
		inventory2.addItem(Item.IRON, 11);
		inventory2.addItem(Item.MAGIC_STONE, 85);
		
		invRenderer2 = new InventoryRenderer(inventory2, 500, 100, atlas, app.font1);
		
//		//init table
//		table = new Table(skin);
//		table.setFillParent(true);
//		stage.addActor(table);
//		
//		inventory = new List<Table>(skin);
//		sell = new List<Table>(skin);
//		inventory = new InventoryList(skin);
//		sell = new InventoryList(skin);
//		
//		inventory.setItems(new InventoryStack(Item.COPPER, 1, atlas), 
//				new InventoryStack(Item.CRYSTAL, 1, atlas),
//				new InventoryStack(Item.MAGIC_STONE, 1, atlas),
//				new InventoryStack(Item.IRON, 1, atlas),
//				new InventoryStack(Item.SILVER, 1, atlas));
//		
//		table.defaults();
//		table.add("Inventory");
//		table.add("Merchant").row();
//		table.add(inventory).expand().fill();
//		table.add(sell).expand().fill();
		
//		initDragAndDrop();
		
	}
	
	public void render(SpriteBatch batch) {
		invRenderer1.render(batch);
		invRenderer2.render(batch);
	}

//	private void initDragAndDrop() {
//		DragAndDrop dnd = new DragAndDrop();
//		dnd.addSource(new Source(inventory) {
//			final Payload payload = new Payload();
//			@Override
//			public Payload dragStart(InputEvent event, float x, float y, int pointer) {
//				InventoryStack item = inventory.getSelected();
//				payload.setObject(item);
//				inventory.getItems().removeIndex(inventory.getSelectedIndex());
////				payload.setDragActor(new Label(item, skin));
//				Table table = new Table(skin);
////				table.add(new Label("Selling", skin));
////				payload.setDragActor(item);
//				payload.setDragActor(new Image(item.texture));
////				payload.setInvalidDragActor(new Image(atlas.findRegion("copper")));
////				payload.setValidDragActor(new Image(atlas.findRegion("coin")));
//				payload.setValidDragActor(new Label("Sell for: " + item.item.sellPrice, skin));
//				return payload;
//			}
//
//			@Override
//			public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
//				if(target == null) {
//					inventory.getItems().add((InventoryStack) payload.getObject());
//				}
//			}
//		});
//		
//		dnd.addTarget(new Target(sell) {
//			@Override
//			public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
//				return !"Cucumber".equals(payload.getObject());
//			}
//
//			@Override
//			public void drop(Source source, Payload payload, float x, float y, int pointer) {
//				sell.getItems().add((InventoryStack) payload.getObject());
//				SoundHandler.playSound(Sounds.OUT_OF_AMMO);
//			}
//		});
//	}

//	public class InventoryList extends List<InventoryStack>{
//
//		public InventoryList(Skin skin) {
//			super(skin);
//		}
//		
//		@Override
//		protected GlyphLayout drawItem(Batch batch, BitmapFont font, int index, InventoryStack item, float x, float y) {
//			batch.draw(item.texture, x, y - 22);
//			return font.draw(batch, item.item.path, x + 42, y);
//		}
//	}
}
